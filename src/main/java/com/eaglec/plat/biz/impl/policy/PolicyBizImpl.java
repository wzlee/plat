package com.eaglec.plat.biz.impl.policy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.dao.policy.PolicyCategoryDao;
import com.eaglec.plat.dao.policy.PolicyDao;
import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.view.JSONData;

@Service
public class PolicyBizImpl implements PolicyBiz {

	@Autowired
	PolicyDao policyDao;
	@Autowired
	PolicyCategoryDao categoryDao;

	@Override
	public Policy findById(Integer id) {
		return policyDao.get(id);
	}

	@Override
	public Policy add(Policy policy) {
		return policyDao.save(policy);
	}

	@Override
	public Policy update(Policy policy) {
		return policyDao.update(policy);
	}

	@Override
	public void delete(Integer id) {
		policyDao.delete(id);
	}

	@Override
	public List<Policy> findByCid(Integer id, String policyName, int start,
			int limit) {

		String hql = "from Policy where 1=1 ";
		PolicyCategory category = categoryDao.get(id);

		if (!category.getChildren().isEmpty()) {
			List<Integer> ids = new ArrayList<Integer>();
			ids = findIds(ids, category);
			if (!ids.isEmpty()) {
				hql += " and policyCategory.id in(";
				for (int j = 0; j < ids.size(); j++) {
					hql += ids.get(j) + ",";
				}
			}
			if (hql.endsWith(",")) {
				hql = hql.substring(0, hql.length() - 1);
			}
			hql += ")";
		} else {
			hql += " and policyCategory.id =" + id;
		}
		if (!StringUtils.isEmpty(policyName)) {
			hql += " and ( title like '%" + policyName + "%'" +" or text like '%" + policyName + "%' )" ;
		}
		hql += " order by top desc, pv desc";
		return policyDao.findList(hql, start, limit);
	}

	@Override
	public List<Policy> findHotPolicy() {
		String hql = "from Policy order by top desc, pv desc";
		return policyDao.findList(hql, 0, 10);
	}

	@Override
	public Long findCountByCid(Integer id, String policyName) {
		String hql = "SELECT COUNT(*) from Policy where 1=1 and policyCategory.id ="
				+ id;
		if (!StringUtils.isEmpty(policyName)) {
			hql += " and  title like '%" + policyName + "%'";
		}
		hql += " order by top desc, pv desc";
		return policyDao.count(hql);
	}

	@Override
	public JSONData<Policy> queryByParams(String title, Integer pcid,
			Integer type,
			int start, int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Policy WHERE 1=1 ");
		if (!StringUtils.isEmpty(title)) {
			sb.append("AND title like '%").append(title).append("%' ");
		}
		if ((pcid != null) && (pcid.intValue() > 0)) {
			sb.append("AND policyCategory.id = ").append(pcid);
		}
		if ((null != type)) {
			sb.append("AND policyCategory.type = ").append(type);
		}
		sb.append("ORDER BY id DESC");
		return policyDao.outJSONData(sb.toString(), start, limit);
	}

	@Override
	public List<Policy> findPolicyNoReport() {
		String hql = "from Policy where 1=1 and reportingId is null";
		hql += " order by top desc, pv desc";
		return policyDao.findList(hql);
	}

	@Override
	public List<Policy> findAllReportingPolicy() {
		String hql = "from Policy where 1=1 and policyCategory.type = 1";
		hql += " order by top desc, pv desc";
		return policyDao.findList(hql);
	}

	/**
	 * 递归获取所有子节点的id
	 * 
	 * @author huyj
	 * @sicen 2013-11-23
	 * @description TODO actionPath eg:
	 * @param ids
	 * @param category
	 * @return
	 */
	private List<Integer> findIds(List<Integer> ids, PolicyCategory category) {
		for (PolicyCategory ccategory : category.getChildren()) {
			if(ccategory.getChildren().isEmpty()){
				ids.add(ccategory.getId());
			} else {
				findIds(ids, ccategory);
			}
		}
		return ids;
	}

	@Override
	public Policy findTopPlicy(Integer pid) {
		String hql = "from Policy where top = 1 and policyCategory.id =" + pid;
		return policyDao.get(hql);
	}

	@Override
	public List<Object> findByPCid(Integer cid) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEW com.eaglec.plat.view.PolicyView(p.title, p.id) FROM Policy p WHERE p.policyCategory.id IN(SELECT c.id FROM PolicyCategory c WHERE c.pid = ").
			append(cid).append(")");
		return policyDao.findObjects(sb.toString());
	}

	@Override
	public List<Policy> findPolicyByWX() {
		String hql = "from Policy where 1=1 and policyCategory.type = 1";
		hql += " order by time desc";
		return policyDao.findList(hql,0,4);
	}
}
