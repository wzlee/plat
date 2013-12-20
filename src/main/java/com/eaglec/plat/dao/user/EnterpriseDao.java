package com.eaglec.plat.dao.user;

import java.util.List;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.view.CategoryGroup;

/**
 * 注册用户Dao层
 * 提供对用户的数据操作
 * <br/>
 * 注：该层已继承了BaseDao，所有基本方法不须再写
 * @author Xiadi
 * @since 2013-8-22
 * 
 */
public interface EnterpriseDao extends BaseDao<Enterprise> {
	public List<CategoryGroup> getCategoryGroupByEid(String hql);

}
