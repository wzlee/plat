package com.eaglec.plat.biz.impl.user;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.dao.user.StaffDao;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;
@Service
public class StaffBizImpl implements StaffBiz {
	
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public void save(Staff staff) {
		staffDao.save(staff);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public JSONData<Staff> findStaff(Integer parentId, int start, int limit) {
		String hql = "from Staff s where 1=1";
		if(null != parentId){
			hql += " and s.parent.id = "+ parentId ;
		}
		hql += " order by s.assignTime desc";
		return staffDao.outJSONData(hql, start, limit);
	}
	
	public List<Staff> findByParent(User user){
		String hql = "from Staff s where s.parent.id =:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", user.getId());
		return staffDao.findList(hql, params);
	}

	@Override
	public Staff findByUserName(String userName) {
		String hql = "from Staff s where s.staffStatus != "+Constant.DELETED+" and s.userName = '"+ userName+"'";
//		if(null != parentId){
//			hql += " and s.parent.id = "+ parentId + " order by s.assignTime desc";
//		}
		return staffDao.get(hql);
	}
	
	@Override
	public Long getTotal(Integer parentId){
		String hql = "select count(s.id) from Staff s where s.parent.id = "+ parentId;
		return (Long)staffDao.uniqueResult(hql);
	}

	@Override
	public JSONRows<Staff> findStaffByPid(Integer pid,Integer staffStatus,Integer staffRoleId,int page,int rows) {
		String hql = "select new com.eaglec.plat.domain.base.Staff(s.id,s.userName,s.staffName,s.sex,s.assignTime,s.mobile,s.staffStatus,s.staffRole,s.parent) from Staff s where s.parent.id = " + pid;
		if(staffStatus != null){
			hql += " and s.staffStatus =" + staffStatus;
		}
		if(staffRoleId != null && staffRoleId != 0){
			hql += " and s.staffRole.id=" + staffRoleId;
		}
		hql += " order by assignTime desc";
		return staffDao.outJSONRows(hql, page, rows);
	}

	@Override
	public Staff getStaff(Integer id) {		
		return staffDao.get(id);
	}

	@Override
	public List<Staff> findStaffByUserName(String userName) {
		String hql = "from Staff s where s.userName =:userName";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		return staffDao.findList(hql, params);
	}

	@Override
	public Staff findByOpenID(String openid) {
		String hql = "from Staff s where s.openID = '"+openid+"'";
		return staffDao.get(hql);
	}

	@Override
	public Staff findByCodeAndStatus(String code, String status) {
		String hql = "from Staff s where s.code = '"+code+"' and status = '"+status+"'";
		return staffDao.get(hql);
	}

	@Override
	public List<Staff> find() {
		// TODO Auto-generated method stub
		return staffDao.findList("from Staff");
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID和以传过来的子帐号id组，查询子帐号不属于这些已传过来的子帐号的子帐号集合
	 */
	@Override
	public List<?> findStaffByPidAndUid(Integer eid, String userid){
		String sql = "select id, username, staffname, email from staff where ";
		if (!"".equals(userid)) {
			sql += "userName not in ("+ userid +") and ";
		}
		sql += "parent_id in (select id from user where enterprise_id = '"+ eid +"')";
		return staffDao.executeSqlQueryMapList(sql);
	}

	@Override
	public Staff findByStid(String stid) {
		String hql = "from Staff s where s.stid =:stid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stid", stid);
		List<Staff> staffs = staffDao.findList(hql, params);
		return staffs.size() == 0?null:staffs.get(0);
	}
}
