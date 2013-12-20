package com.eaglec.plat.biz.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.dao.user.EnterpriseDao;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.CategoryGroup;
import com.eaglec.plat.view.JSONData;

@Service
public class EnterpriseBizImpl implements EnterpriseBiz {
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private CategoryBiz categoryBiz;
	
	@Autowired
	private UserBiz userBiz;
	
	@Override
	public JSONData<Enterprise> findEnterpriseListById(String id,int start, int limit) {
		String hql = "from Enterprise where id in("+ id + ") and type="+Constant.ENTERPRISE_TYPE_ORG+
				" and status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE;
		return enterpriseDao.outJSONData(hql,start, limit);
	}

	@Override
	public Enterprise loadEnterpriseByEid(int eid) {
		return enterpriseDao.get(eid);
	}
	
	@Override
	public int add(Enterprise e) {
		return enterpriseDao.add(e);
	}
	
	@Override
	public Enterprise save(Enterprise e) {
		return enterpriseDao.saveOrUpdate(e);
	}

	@Override
	public List<Enterprise> findEnterpriseByIcRegNumber(String icRegNumber) {
		String hql = "from Enterprise where status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE+" and type = "+
				Constant.ENTERPRISE_TYPE_ORG+" and icRegNumber= '" + icRegNumber + "'";
		return enterpriseDao.findList(hql);
	}
	
	@Override
	public List<Enterprise> findEnterpriseByEnterName(String enterpriseName) {
		String hql = "from Enterprise where status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE+" and type = "+
				Constant.ENTERPRISE_TYPE_ORG+" and name= '" + enterpriseName + "'";
		return enterpriseDao.findList(hql);
	}
	
	@Override
	public List<CategoryGroup> findCategoryGroupByEid(String id) {
		String hql = "select new com.eaglec.plat.view.CategoryGroup(c.text,s.category.id,count(s.id)) "
				+ "from Service s,Category c where s.currentStatus in(" +
				Constant.SERVICE_STATUS_ADDED + "," +
				Constant.SERVICE_STATUS_CHANGE_AUDIT +	"," +
				Constant.SERVICE_STATUS_DOWN_AUDIT + ")"
				+ " and s.enterprise.status = "+Constant.ENTERPRISE_STATUS_EFFECTIVE
				+ " and c.id=s.category.id and s.enterprise.id='"
				+ id + "' GROUP BY s.category.id ";
		return enterpriseDao.getCategoryGroupByEid(hql);
	}

	/**
	 * 分页查询服务机构
	 * @param enterpName 企业名称
	 * @param limit 分页查询条数
	 * @param start 分页查询起始
	 * @return 匹配的服务机构
	 * */
	public JSONData<Enterprise> findByName(String enterpName, int limit, int start) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Enterprise WHERE 1=1 and status = "); 
		sb.append(Constant.ENTERPRISE_STATUS_EFFECTIVE);//有效的服务机构
		if (!StringUtils.isEmpty(enterpName)) {
			sb.append(" AND name like '%").append(enterpName).append("%'");
		}
		sb.append("AND type = ").append(Constant.ENTERPRISE_TYPE_ORG).append(" ORDER BY id DESC");    //默认按ID降序
		JSONData<Enterprise> jd = enterpriseDao.outJSONData(sb.toString(), start, limit);
		return jd;
	}

	/**
	 * 根据服务机构id查找出服务机构所提供的服务类别
	 * */
	public String findServiceCategories(Integer eid) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.text FROM Category c WHERE c.id in(SELECT DISTINCT category_id FROM Service WHERE enterprise_id =")
			.append(eid).append(" AND currentStatus =").append(Constant.SERVICE_STATUS_ADDED).append(")");
		List<?> list = enterpriseDao.findObjects(sb.toString());
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			result.append(list.get(i)).append(",");
		}
		int length = result.length();
		result.delete(length - 1, length);
		return result.toString();
	}
	
	@Override
	public List<Enterprise> findTopTen(Integer type) {
		String hql = "from Enterprise e where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(type)){
			hql += " and type = :type";
			params.put("type", type);
		}
		hql += " order by e.salesRevenue desc";
		return enterpriseDao.findList(hql, params);
	}	
	
	@Override
	public List<Enterprise> findEnterpriseByName(String name) {
		String hql = "from Enterprise where status != "+Constant.ENTERPRISE_STATUS_DELETED+
				" and isApproved = true and name= '" + name + "'";
		return enterpriseDao.findList(hql);
	}

	/**
	 * 根据服务分类id,名称,所属行业,经营模式查询服务机构列表
	 * */
	public List<Enterprise> findEnterpriseListByCidWX(Integer cid,
			String name, Integer industryType, String businessPattern, int start,	int limit) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * from enterprise where 1=1 and id in (select enterprise_id from enterprise_category "
				+ "	where category_id ");
		// 获取到当前类别
		Category categoty = categoryBiz.findById("service", cid);

		// 如果没有子类，直接查出类别为当前类比的服务结构
		if (categoty.getChildren().isEmpty()) {
			sql.append("=" + cid + ")");
		} else {// 如果有子类查找该类别下所有子类
			sql.append(" in (select id from  category "
					+ "	where pid = " + cid + "))");
		}

		if (!StringUtils.isEmpty(name)) {
			sql.append(" AND name like '%").append(name)
					.append("%'");
		}
		if (industryType != null) {
			sql.append(" AND industryType = ")
					.append(
					industryType);
		}
		if (!StringUtils.isEmpty(businessPattern)) {
			sql.append(" AND businessPattern = '")
					.append(businessPattern).append("'");
		}

		sql.append(" and status = " + Constant.ENTERPRISE_STATUS_EFFECTIVE);// 有效的服务机构
		List<Enterprise> data = enterpriseDao.executeListSql(sql.toString(),
				start, limit);
		int total = enterpriseDao.executeListSql(sql.toString());
	
		return data;
	}
	/**
	 * 根据服务分类id,名称,所属行业,经营模式查询服务机构列表
	 * */
	public JSONData<Enterprise> findEnterpriseListByCid(Integer cid,
			String name, Integer industryType, String businessPattern, int start,	int limit) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * from enterprise where 1=1 and id in (select enterprise_id from enterprise_category "
				+ "	where category_id ");
		// 获取到当前类别
		Category categoty = categoryBiz.findById("service", cid);

		// 如果没有子类，直接查出类别为当前类比的服务结构
		if (categoty.getChildren().isEmpty()) {
			sql.append("=" + cid + ")");
		} else {// 如果有子类查找该类别下所有子类
			sql.append(" in (select id from  category "
					+ "	where pid = " + cid + "))");
		}

		if (!StringUtils.isEmpty(name)) {
			sql.append(" AND name like '%").append(name)
					.append("%'");
		}
		if (industryType != null) {
			sql.append(" AND industryType = ")
					.append(
					industryType);
		}
		if (!StringUtils.isEmpty(businessPattern)) {
			sql.append(" AND businessPattern = '")
					.append(businessPattern).append("'");
		}

		sql.append(" and status = " + Constant.ENTERPRISE_STATUS_EFFECTIVE);// 有效的服务机构
		List<Enterprise> data = enterpriseDao.executeListSql(sql.toString(),
				start, limit);
		int total = enterpriseDao.executeListSql(sql.toString());
		JSONData<Enterprise> list = new JSONData<Enterprise>(true, data, total);
		return list;
	}

	@Override
	public Enterprise findByEid(Integer id) {
		return enterpriseDao.get(id);
	}

	@Override
	public Enterprise findByIcRegNumber(String icRegNumber) {
		String hql = "from Enterprise where icRegNumber ='" + icRegNumber +"'";
		return enterpriseDao.get(hql);
	}

	@Override
	public Enterprise update(Enterprise e) {		
		return enterpriseDao.update(e);
	}

	public List<Object> findEnterpriseByCid(Integer cid){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEW com.eaglec.plat.view.EnterpriseView(e.id, e.name) FROM Enterprise e WHERE e.type = ").
		append(Constant.ENTERPRISE_TYPE_ORG).append(" AND ").append(cid).append("IN(SELECT m.id FROM e.myServices m)");
		return enterpriseDao.findObjects(sb.toString());
	}
	
	@Override
	public List<Map<String, Object>> getEnterprisesOfAllCategories() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();	//返回值
		//1.获取服务机构中类
		//2.按中类查找服务机构
		List<Category> categories = categoryBiz.findCategoriesOfEnterprise();
		for(int i = 0; i < categories.size(); i++) {
			Category category = categories.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", category.getId());
			map.put("text", category.getText());
			map.put("enterprises", findEnterpriseByCid(category.getId()));
			result.add(map);
		}
		return result;
	}

	@Override
	public Enterprise findEnterpriseByUuid(String uuid) {
		String hql = "from Enterprise where eid ='" + uuid +"'";
		return enterpriseDao.get(hql);
	}

	@Override
	public List<Enterprise> findEnterprieByWX() {
			List<User> userList =  userBiz.findUserByWX();
			List<Enterprise> list = new ArrayList<Enterprise>();
			for(User u:userList){
				list.add(u.getEnterprise());
			}
		return list;
	}
}
