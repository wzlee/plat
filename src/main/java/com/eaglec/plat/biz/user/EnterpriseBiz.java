package com.eaglec.plat.biz.user;

import java.util.List;
import java.util.Map;

import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.view.CategoryGroup;
import com.eaglec.plat.view.JSONData;

/**
 * 
 * 封装对企业的相关操作
 * 
 * @author pangyf
 * @since 2013-8-22
 * 
 */
public interface EnterpriseBiz {
	
	public abstract JSONData<Enterprise> findEnterpriseListById(String id,int start, int limit);
	
	public abstract List<Enterprise> findEnterpriseListByCidWX(Integer cid, String name, Integer industryType, String businessPattern, int start, int limit);
	
	public abstract JSONData<Enterprise> findEnterpriseListByCid(Integer cid, String name, Integer industryType, String businessPattern, int start, int limit);
	
	public abstract Enterprise loadEnterpriseByEid(int eid);
	
	public int add(Enterprise e);
	
	public Enterprise save(Enterprise e);
	
	/**
	 * 根据组织机构号查找企业(List)
	 * @author pangyf
	 * @since 2013-10-30
	 * @param icRegNumber
	 * @return
	 */
	public abstract List<Enterprise> findEnterpriseByIcRegNumber(String icRegNumber);
	
	/**
	 * 根据服务机构名称查找企业(List)(数据乱-----)
	 * @author xuwf
	 * @since 2013-11-28
	 * @param enterpriseName	机构名称
	 * @return  
	 */
	public abstract List<Enterprise> findEnterpriseByEnterName(String enterpriseName);	
	
	public abstract List<Enterprise> findEnterpriseByName(String name);
	
	public List<CategoryGroup> findCategoryGroupByEid(String id);
	
	public JSONData<Enterprise> findByName(String enterpName, int limit, int start);

	public String findServiceCategories(Integer eid);
	
	/**
	 * 查找行业机构Top10(子窗口首页显示)	按年营业额降序排序
	 * @author xuwf
	 * @since 2013-9-13
	 * 
	 * @param type	企业类型(服务机构)	
	 * @return
	 */
	public List<Enterprise> findTopTen(Integer type);
	
	/**
	 * 查招某个企业
	 * @author xuwf
	 * @since 2013-10-18
	 * @param id
	 * @return
	 */
	public abstract Enterprise findByEid(Integer id);
	
	/**
	 * 根据组织机构号查找企业(对象)
	 * @author pangyf
	 * @since 2013-10-30
	 * @param icRegNumber
	 * @return
	 */
	public abstract Enterprise findByIcRegNumber(String icRegNumber);
	
	public abstract Enterprise update(Enterprise e);

	/**
	 * 按类型分组,获取所有企业
	 * @author liuliping
	 * @since 2013-11-26
	 * @param 
	 * @return 
	 */
	public abstract List<Map<String, Object>> getEnterprisesOfAllCategories();
	
	/**
	 * 根据服务类别,获取所有机构
	 * @author liuliping
	 * @since 2013-11-26
	 * @param 
	 * @return 
	 */
	List<Object> findEnterpriseByCid(Integer cid);
	
	public abstract Enterprise findEnterpriseByUuid(String uuid);

	/**
	 * 获取微信首页加载机构
	 * @author huyj
	 * @sicen 2013-12-18
	 * @return
	 */
	public abstract List<Enterprise> findEnterprieByWX();
}
