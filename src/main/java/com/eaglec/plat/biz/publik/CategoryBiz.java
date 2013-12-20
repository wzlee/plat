package com.eaglec.plat.biz.publik;

import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.view.Breadcrumb;

/**
 * 服务类别Service
 * 封装了对服务类别的操作
 * 
 * @author xuwf
 * @since 2013-8-13
 *
 */
public interface CategoryBiz {
	
	/**
	 * 查询所有服务类别
	 *
	 *@author xuwf
	 *@since 2013-8-13
	 *
	 * @return 类别列表
	 */
	public List<Category> findAll();
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：查询所有服务类别的父级别
	 */
	public List<Category> findCategoryParent();
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：根据父类别ID，查找子类别
	 */
	public List<Category> findCategoryChildren(int id);
	
	/**
	 * 根据clazz加载类别
	 *
	 *@author lizw
	 *@since 2013-8-14
	 *
	 * @return 类别列表
	 */
	public List<Category> findCategoryByClazzAndPid(String clazz,int pid);
	
	/**
	 * 保存或修改类别
	 * 
	 * @author Xiadi
	 * @since 2013-8-16
	 * 
	 * @param category
	 * @return Category对象
	 */
	public Category addOrUpdate(Category category);
	
	/**
	 * 删除类别对象，包括子类别
	 * 
	 * @author Xiadi
	 * @since 2013-8-16
	 * 
	 * @param category
	 * @return Category对象
	 */
	public void delete(Category category);
	
	/**
	 * 根据Id删除类别，包括子类别
	 * 
	 * @author Xiadi
	 * @since 2013-8-16
	 * 
	 * @param category
	 * @return Category对象
	 */
	public void deleteById(Integer id);
	
	/**
	 * 根据clazz和id得到该类别
	 * @author xuwf
	 * @since  2013-9-2
	 * 
	 * @param clazz	对应实体
	 * @param id   类别ID
	 * @return
	 */
	public Category findById(String clazz,Integer id);
	
	/**
	 * @date: 2013-10-18
	 * @author：lwch
	 * @description：根据类别ID去查询服务表里面对应的服务总数量
	 */
	public int loadCategoryToServerCount(int cid);
	
	/**
	 * 查找类别树，即查找根节点
	 * @author Xiadi
	 * @since 2013-9-4 
	 * @return 以树形结构返回
	 */
	public Category findRootByClazz(String clazz);

	public List<Category> findAllCategory();
	
	public Map<String, Object> getServiceCategorys();
	
	/**
	 * 根据类别id,获取面包屑
	 * */
	public Breadcrumb getBreadcrumbByCid(Integer cid, String clazz);
	
	/**
	 * 获取服务机构中类
	 * */
	List<Category> findCategoriesOfEnterprise(); 
}
