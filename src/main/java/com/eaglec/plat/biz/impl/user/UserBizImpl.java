package com.eaglec.plat.biz.impl.user;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.dao.user.EnterpriseDao;
import com.eaglec.plat.dao.user.SendEmailDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.SendEmail;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;

@Service
public class UserBizImpl implements UserBiz {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	@Autowired
	private SendEmailDao sendEmailDao;

	@Override
	public JSONData<User> queryMultiple(String username, String entershort,
			String entername,Integer enterpriseType,String startTime,
			String endTime, Integer industryType, int start, int limit)  {
		String hql = "from User u where 1=1";
		if(!"".equals(username) && null != username){
			hql += " and u.userName like '%"+ username + "%'";
		}
		if(!"".equals(entershort) && null != entershort){
			hql += " and u.enterprise.forShort like '%"+ entershort +"%'";
		}
		if(!"".equals(entername) && null != entername){
			hql += " and u.enterprise.name like '%"+ entername +"%'";
		}
		if(!"".equals(startTime) && null != startTime){
			startTime = startTime.substring(0, startTime.indexOf("T"));
			hql += " and u.regTime >= '"+ startTime+" 00:00:00'";	
		}
		if(!"".equals(endTime) && null != endTime){
			endTime = endTime.substring(0, endTime.indexOf("T"));
			hql += " and u.regTime <= '"+ endTime+" 23:59:59'";
		}
		if ((enterpriseType != null) && (enterpriseType.intValue() != 0)) {
			if(enterpriseType == Constant.PERSONERAL_TYPE){
				hql += " and u.isPersonal = true";
			}else{
				hql += " and u.enterprise.type = " + enterpriseType;
			}
		}
		if ((industryType != null) && (industryType.intValue() != 0)) {
			hql += " and u.enterprise.industryType = " + industryType;
		}
		hql += " order by u.regTime desc";
		return userDao.outJSONData(hql, start, limit);
	}

	/**
	 * @author pangyf
	 * @since 2013-08-22
	 */
	public JSONData<User> findAll(String uName,String enterpriseName,Integer enterpriseType,
			Integer industryType,String userStatus,int start, int limit) {
		String hql = "from User where userStatus in (" + userStatus +") ";
		if(!StringUtils.isEmpty(uName)){
			hql += " and userName like '%" + uName + "%'";
		}
		if(!StringUtils.isEmpty(enterpriseName)){
			hql += " and enterprise.name like '%" + enterpriseName + "%')";
		}
		if ((enterpriseType != null) && (enterpriseType.intValue() != 0)) {
			if(enterpriseType == Constant.PERSONERAL_TYPE){
				hql += " and isPersonal = true";
			}else{
				hql += " and enterprise.type = "+enterpriseType;
			}
		}
		if ((industryType != null) && (industryType.intValue() != 0)) {
			hql += " and enterprise.industryType = " + industryType;
		}
		hql +=" order by regTime desc";
		return userDao.outJSONData(hql,start, limit);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}
	
	@Override
	public User add(User user) {
		return userDao.save(user);
	}
	
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public List<User> findUserByName(String name) {
		String hql="from User u where userStatus != "+Constant.DELETED+" and u.userName='"+name+"'";
		return userDao.findList(hql);
	}
	
	/**
	 * @author pangyf
	 * @since 2013-08-22
	 */
	@Override
	public List<User> findUserListByName(String userName) {
		
		String hql = "from User where userStatus != "+Constant.DELETED+" and userName = '" + userName + "'";	
		return userDao.findList(hql);		
	}
	
	/**
	 * @date: 2013-12-13
	 * @author：lwch
	 * @description：根据用户名查询企业用户（忽略用户的系统状态）
	 */
	@Override
	public List<User> getUserInfo(String userName){
		String hql = "from User where userName = '" + userName + "'";	
		return userDao.findList(hql);
	}

	@Override
	public List<User> findUserByEmail(String email) {
		String hql = "from User where email = '" + email + "'";	
		return userDao.findList(hql);
	}
	@Override
	public User updatUserInfo(User user) {
		return userDao.update(user);
	}

	@Override
	public User findUserByCodeAndStatus(String code, String status) {
		return userDao.get("from User where code ='"+code+"' and status='"+status+"'");
	}

	@Override
	public User findUserByUid(String uid) {
		return userDao.get("from User where uid ='"+uid+"'");
	}

	@Override
	public List<User> find() {
		return userDao.findList("from User");
	}

	@Override
	public User findUserByIcRegNumber(String icRegNumber) {
		String hql = "from User where enterprise.icRegNumber ='" + icRegNumber + "'";
		return userDao.get(hql);
	}

	@Override
	public User findUserByEnterprise(Integer enterpriseId) {
		String hql = "from User where enterprise.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", enterpriseId);
		return userDao.get(hql, params);
	}

	@Override
	public void updateSql(String sql) throws Exception {
		userDao.executeSql(sql);
	}
	
	@Override
	public User findUserByUuid(String uuid) {
		return userDao.get("from User where uid ='" + uuid + "'");
	}
	
	@Override
	public User findUserByCode(String code) {
		//TODO 此种方式存在被sql注入的危险
		String hql = "from User where code ='" + code + "'";
		return userDao.get(hql);
	}

	@Override
	public User saveRegisterTransaction(SendEmail sendEmail,Enterprise enterprise, User user, int type) {

		sendEmailDao.saveOrUpdate(sendEmail);
		user.setSendEmail(sendEmail);
		
		if(enterprise!=null){
			enterpriseDao.add(enterprise);
			user.setEnterprise(enterprise);
		}
		
		User u =  userDao.save(user);
		//保存社区一些表
		String tsusersql = "INSERT INTO ts_user(uid,login,uname,email,PASSWORD,openid) VALUES("+
							user.getId()+",'"+user.getEmail()+"','"+user.getUserName()+"','"+
							user.getEmail()+"','"+user.getPassword()+"','"+user.getUid()+"')";
		
		String tsgroupsql = "INSERT INTO ts_user_group_link (uid,user_group_id) VALUES("+user.getId()+","+type+")";
		userDao.executeSql(tsusersql);
		userDao.executeSql(tsgroupsql);
		
		return u;
	}

	@Override
	public List<User> findUserByWX() {
		String hql = "from User where 1=1 and userStatus=1 order by regTime desc";
		return userDao.findList(hql,0,4);
	}

	@Override
	public User findUserByUidAndModifyTime(String uid, String modifyTime) {
		return userDao.get("FROM User WHERE uid ='"+uid+"' and modifyTime='"+modifyTime+"'");
	}

}
