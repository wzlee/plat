package com.eaglec.plat.dao.auth;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.view.FlatManagerView;
import com.eaglec.plat.view.JSONData;

/**
 * 用户Dao层
 * 提供对用户的数据操作
 * <br/>
 * 注：该层已继承了BaseDao，所有基本方法不须再写
 * @author chens
 * @since 2013-8-12
 * 
 */
public interface ManagerDao extends BaseDao<Manager> {
	public JSONData<FlatManagerView> findObjects(String hql, int start, int limit);
}
