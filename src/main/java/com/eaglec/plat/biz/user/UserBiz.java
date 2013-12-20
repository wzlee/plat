package com.eaglec.plat.biz.user;
import java.util.List;

import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.SendEmail;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.view.JSONData;

/**
 * 注册用户Service<br/>
 * 封装对注册用户的相关操作
 * 
 * @author Xiadi
 * @since 2013-8-22
 * 
 */
public interface UserBiz {
	
	/**
	 * 根据条件分页查找企业用户
	 * @author xuwf
	 * @since 2013-8-22
	 * 
	 * @param username	会员名
	 * @param entershort 企业简称
	 * @param entername  企业全称
	 * @param entertype 企业类型
	 * @param startTime 起始时间
	 * @param lastTime 截止时间
	 * @param industryType 行业类型
	 * @param start	
	 * @param limit
	 * @return	JSONData<User> user列表 JSON格式
	 */
	public abstract JSONData<User> queryMultiple(String username,String entershort,
			String entername,Integer enterpriseType,String startTime,String endTime, Integer industryType,
			int start,int limit);
	
	/**
	 * 查询所有的企业用户
	 * @author pangyf
	 * @since 2013-08-22
	 */
	public abstract JSONData<User> findAll(String uName,String enterpriseName,
			Integer enterpriseType,Integer industryType,String userStatus,int start, int limit);
	
	/**
	 * 更新企业用户
	 * @author pangyf
	 * @since 2013-08-22
	 */
	public abstract User update(User user);
	
	/**
	 * 添加企业用户
	 * @author pangyf
	 * @since 2013-08-22
	 */
	public abstract User add(User user);
	/**
	 * 根据ID查询用户
	 * @author pangyf
	 * @since 2013-08-22
	 */
	public User findUserById(Integer id);
	
	/**
	 * 根据用户名查询企业用户
	 * @author pangyf
	 * @since 2013-08-27
	 */
	public abstract List<User> findUserListByName(String userName);

	/**
	 * @date: 2013-12-13
	 * @author：lwch
	 * @description：根据用户名查询企业用户（忽略用户的系统状态）
	 */
	public abstract List<User> getUserInfo(String userName);
	
	/**
	 * 根据用户名返回用户
	 * @param name
	 * @return
	 */
	public List<User> findUserByName(String name);
	
	/**
	 * 根据邮箱返回用户
	 * @param name
	 * @return
	 */
	public List<User> findUserByEmail(String email);

	/**
	 * 用户管理中心，用户主账号管理保存用户基本信息
	 * 
	 * @author huyj
	 * @sicen 2013-9-30
	 * @param user
	 * @return
	 */
	public User updatUserInfo(User user);

	public User findUserByCodeAndStatus(String code,String status);
	
	public User findUserByUid(String uid);;
	
	public User findUserByCode(String code);
	
	/**
	 * 查询所有user
	 */
	public List<User> find();
	
	/**
	 * 根据组织机构号查找用户
	 * @author pangyf
	 * @since 2013-10-31
	 * @param icRegNumber
	 * @return
	 */
	public User findUserByIcRegNumber(String icRegNumber);
	
	/**
	 * 根据企业id查找企业主账号
	 * @author xuwf
	 * @since 2013-11-07
	 * 
	 * @param enterpriseId
	 * @return
	 */
	public abstract User findUserByEnterprise(Integer enterpriseId);
	
	/**
	 * 
	 * cs添加一个跳执行sql语句的，供注册的时候插入夏文龙表使用
	 */
	public abstract void updateSql (String sql) throws Exception;
	
	public abstract User saveRegisterTransaction(SendEmail sendEmail,Enterprise enterprise,User user,int type);
	
	public abstract User findUserByUuid(String uuid);

	/**
	 * 获取微信首页加载机构所对应的用户
	 * @author huyj
	 * @sicen 2013-12-18
	 * @description TODO
	 * actionPath eg:
	 * @return
	 */
	public abstract List<User> findUserByWX();

	public abstract User findUserByUidAndModifyTime(String string,
			String string2);

}
