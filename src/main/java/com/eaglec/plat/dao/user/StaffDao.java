package com.eaglec.plat.dao.user;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.base.Staff;

/**
 * 子账号Dao层
 * 提供对子账号的数据操作
 * <br/>
 * 注：该层已继承了BaseDao，所有基本方法不须再写
 * @author xuwf
 * @since 2013-8-23
 * 
 */
public interface StaffDao extends BaseDao<Staff> {
	public abstract String getStaffIdsByParentId(int parentId);
}
