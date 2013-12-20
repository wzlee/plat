package com.eaglec.plat.biz.mall;

import java.util.List;

import com.eaglec.plat.domain.mall.MallCategory;

/**
 * 服务商城类别MallCategory 封装了对服务类别的操作
 * 
 * @author huyj
 * @since 2013-09-23
 * 
 */
public interface MallCategoryBiz {

	/**
	 * 查询所有服务类别
	 * 
	 * @author huyj
	 * @since 2013-8-13
	 * 
	 * @return 类别列表
	 */
	public List<MallCategory> findAll();

	/**
	 * @date: 2013-9-23
	 * @author：huyj
	 * @description：根据父类别ID，查找子类别
	 */
	public List<MallCategory> findMallCategoryChildren(Integer id);

	/**
	 * 保存或修改类别
	 * 
	 * @author huyj
	 * @since 2013-9-23
	 * 
	 * @param MallCategory
	 * @return MallCategory对象
	 */
	public MallCategory addOrUpdate(MallCategory MallCategory);

	/**
	 * 删除类别对象，包括子类别
	 * 
	 * @author huyj
	 * @since 2013-9-23
	 * 
	 * @param MallCategory
	 * @return MallCategory对象
	 */
	public void delete(MallCategory MallCategory);

	/**
	 * 根据Id删除类别，包括子类别
	 * 
	 * @author huyj
	 * @since 2013-9-23
	 * 
	 * @param MallCategory
	 * @return MallCategory对象
	 */
	public void deleteById(Integer id);

	/**
	 * 根据clazz和id得到该类别
	 * 
	 * @author huyj
	 * @since 2013-9-2
	 * 
	 * @param clazz
	 *            对应实体
	 * @param id
	 *            类别ID
	 * @return
	 */
	public MallCategory findById(String clazz, Integer id);

	/**
	 * 查找类别树，即查找根节点
	 * 
	 * @author huyj
	 * @since 2013-9-23
	 * 
	 * @return 以树形结构返回
	 */
	public MallCategory findRootByClazz(String clazz);

	/**
	 * 获取所有类别
	 * 
	 * @author huyj
	 * @sicen 2013-9-29
	 * @description TODO actionPath eg:
	 * @return
	 */
	public List<MallCategory> findAllMallCategory();

	/**
	 * 根据mallid查询服务
	 * 
	 * @author huyj
	 * @sicen 2013-9-29
	 * @description TODO actionPath eg:
	 * @param mallId
	 * @param start
	 * @param limit
	 * @return
	 */
	public String findServiceByMall(Integer mallId, int start, int limit);

	/**
	 * 根据父类id查询子类
	 * 
	 * @author huyj
	 * @sicen 2013-9-29
	 * @description TODO actionPath eg:
	 * @param pid
	 * @return
	 */
	public List<MallCategory> findAllMallCatetoryByPid(Integer pid);

	/**
	 * 修改Mallcategory
	 * 
	 * @author huyj
	 * @sicen 2013-10-18
	 * @description TODO actionPath eg:
	 * @param mallCategory
	 */
	public void update(MallCategory mallCategory);

	/**
	 * 通过频道查询一级分类 注意此处的pid不是MallCategory的id 而是频道id 由于没有关联，实现上面写死
	 * 
	 * @author huyj
	 * @sicen 2013-10-18
	 * @param pid
	 * @return
	 */
	public List<MallCategory> findChildren(Integer pid);

}
