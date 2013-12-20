package com.eaglec.plat.biz.impl.publik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.dao.publik.CategoryDao;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.Breadcrumb;

@Service
public class CategoryBizImpl implements CategoryBiz {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		String hql = "from Category where pid is null";
		return categoryDao.findList(hql);
	}

	@Override
	public List<Category> findAllCategory() {
		String hql = "from Category where pid =1 and clazz='SERVICE'";
		return categoryDao.findList(hql);
	}

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：查询所有服务类别的父级别
	 */
	@Override
	public List<Category> findCategoryParent() {
		String sql = "select * from category where pid in (select id from category where pid is null and clazz='SERVICE')";
		return categoryDao.executeSqlQueryEntityList(sql);
	}

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：根据父类别ID，查找子类别
	 */
	@Override
	public List<Category> findCategoryChildren(int id) {
		return categoryDao.findList("from Category where pid = '" + id + "'");
	}

	@Override
	public List<Category> findCategoryByClazzAndPid(String clazz, int pid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clazz", clazz);
		map.put("pid", pid);
		return categoryDao.findList(
				"from Category where clazz=:clazz and pid=:pid", map);
	}

	@Override
	public Category addOrUpdate(Category category) {
		return categoryDao.saveOrUpdate(category);
	}

	@Override
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	@Override
	public void deleteById(Integer id) {
		categoryDao.delete(id);
	}

	@Override
	public Category findById(String clazz, Integer id) {
		String hql = "from Category where clazz=:clazz and id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clazz", clazz);
		params.put("id", id);
		return categoryDao.get(hql, params);
	}
	
	/**
	 * @date: 2013-10-18
	 * @author：lwch
	 * @description：根据类别ID去查询服务表里面对应的服务总数量
	 */
	@Override
	public int loadCategoryToServerCount(int cid){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from Service where category.id = ").append(cid)
		  .append(" and currentStatus in (")
		  .append(Constant.SERVICE_STATUS_ADDED).append(",")
		  .append(Constant.SERVICE_STATUS_CHANGE_AUDIT).append(",")
		  .append(Constant.SERVICE_STATUS_DOWN_AUDIT).append(")")
		  .append(" and enterprise.status = ").append(Constant.ENTERPRISE_STATUS_EFFECTIVE);
//		String sql = "select count(*) from service where category_id = "+ cid + " and currentStatus = " + Constant.SERVICE_STATUS_ADDED;
		Long count = categoryDao.count(sb.toString());
		return count.intValue();
	}

	@Override
	public Category findRootByClazz(String clazz) {
		clazz = "service";
		// TODO Auto-generated method stub
		String hql = "from Category where pid is null and clazz=:clazz ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clazz", clazz);
		return categoryDao.get(hql, params);
	}

	/**
	 * 获得服务一级类别和二级类别
	 * 
	 * @return result 返回map,分别封装了一级服务类别链表和二级服务类别链表
	 * */
	public Map<String, Object> getServiceCategorys() {
		Map<String, Object> result = new HashMap<String, Object>(); // 返回值
		String hql = "FROM Category WHERE clazz = 'service' AND pid = (SELECT id FROM Category WHERE clazz = 'service' AND pid IS NULL)";
		List<Category> parents = categoryDao.findList(hql); // 获取所有一级服务类别
		Category parent = null; // 临时变量
		List<List<Category>> children = new ArrayList<List<Category>>(); // 包含所有的二级服务类别

		/* 通过遍历一级服务类别,将二级服务类别放在集中在一个链表里,一级列表的元素顺序和二级列表的元素顺序是一致的. */
		for (int i = 0; i < parents.size(); i++) {
			parent = parents.get(i);
			children.add(parent.getChildren());
		}
		result.put("parents", parents);
		result.put("children", children);
		return result;
	}

	/**
	 * 根据类别id,获取面包屑.面包屑在页面中广泛使用,用来定位当前页面的位置.例如"首页>服务类别A>政企服务"
	 * 
	 * @author liuliping
	 * @since 2013-10-09
	 * @param cid
	 *            类别id
	 * @param clazz
	 *            类别所属的实体类
	 * */
	public Breadcrumb getBreadcrumbByCid(Integer cid, String clazz) {
		List<String> names = new ArrayList<String>(); // 面包屑内容
		Category category = findById(clazz, cid); // cid对应的服务类别

		/*
		 * cid对应的是父级类别,面包屑为一层; cid对应的是二级类别,查找父类别来构造面包屑,面包屑为两层
		 */
		Category parent = findById(clazz, category.getPid());
		if ((parent.getPid() != null) && (parent.getPid().intValue() != 0)) { // cid对应的类别是二级类别时候的时候
			names.add(parent.getText());
		}
		names.add(category.getText());

		return new Breadcrumb(names);
	}

	@Override
	public List<Category> findCategoriesOfEnterprise() {
		String hql = "FROM Category c WHERE c.pid IN(2,3,4,5,6,7,8,9,10)";	//服务大类的id是2~10,基本不会改变,因此就写具体的id,不做查询
		return categoryDao.findList(hql);
	}

}
