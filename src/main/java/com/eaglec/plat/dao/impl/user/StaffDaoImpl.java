package com.eaglec.plat.dao.impl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.user.StaffDao;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.utils.StrUtils;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

	@Override
	public String getStaffIdsByParentId(int parentId) {
		String hql = "select id from Staff where parent.id = " + parentId;
		List<Object> list = super.findObjects(hql);
		String ret = "";
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				ret += StrUtils.toInt(obj) + ",";
			}
		}
		return ret.equals("") ? ret :ret.substring(0, ret.length() - 1);
	}

}
