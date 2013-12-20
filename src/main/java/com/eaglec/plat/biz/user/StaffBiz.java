package com.eaglec.plat.biz.user;

import java.util.List;

import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

/**
 * 子账号Biz<br/>
 * 封装对子账号的相关操作
 * 
 * @author xuwf
 * @since 2013-8-23
 * 
 */
public interface StaffBiz {
	
	/**
	 * 新增子账号
	 * @author xuwf
	 * @since 2013-8-23
	 * 
	 * @param staff
	 */
	public abstract void save(Staff staff);
	
	/**
	 * 修改子账号
	 * @author xuwf
	 * @since 2013-8-23
	 * 
	 * @param staff
	 */
	public abstract void update(Staff staff);
	
	/**
	 * 根据用户名查找子账号对象(判断子账号用户名是否重复)
	 * @author xuwf
	 * @since 2013-8-24
	 * 
	 * @param userName	用户名
	 * @param parentId	主账号ID
	 * @return
	 */
	public abstract Staff findByUserName(String userName);
	
	/**
	 * 根据用户名查找子账号对象(判断子账号用户名是否重复)
	 * @author xuwf
	 * @since 2013-8-24
	 * 
	 * @param userName	用户名
	 * @param parentId	主账号ID
	 * @return
	 */
	public abstract List<Staff> findStaffByUserName(String userName);
	
	/**
	 * 根据主账号id分页查询子账号列表
	 * @author xuwf
	 * @author 2013-8-23
	 * 
	 * @param parentId	主账号id
	 * @param start
	 * @param limit
	 * @returns
	 */
	public abstract JSONData<Staff> findStaff(Integer parentId,int start,int limit);
	
	/**
	 * 查询某个主账号下的所有子账号
	 * @author xuwf
	 * @since 2013-9-9
	 * @param user	主账号
	 * @return	对应子账号集合
	 */
	public List<Staff> findByParent(User user);
	
	/**
	 * 计算某个主账号下面的子账号的数量(限制20)
	 * @param parentId
	 * @author xuwf
	 * @since 2013-8-28
	 * 
	 * @return
	 */
	public abstract Long getTotal(Integer parentId);
	
	/**
	 * 前台根据用户主账号查找子账号
	 * @author pangyf
	 * @since 2013-10-17
	 * @param pid
	 * @param page
	 * @param rows
	 * @return
	 */
	public abstract JSONRows<Staff> findStaffByPid(Integer pid,Integer staffStatus,Integer staffRoleId,int page,int rows);
	
	/**
	 * 根据子账号ID得到子账号
	 * @author pangyf
	 * @since 2013-10-17
	 * @param id
	 * @return
	 */
	public abstract Staff getStaff(Integer id);

	public abstract Staff findByCodeAndStatus(String code,String status);
	
	public abstract Staff findByOpenID(String openid);
	
	/**
	 * 查找所有子账号
	 */
	public List<Staff> find();
	
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID和以传过来的子帐号id组，查询子帐号不属于这些已传过来的子帐号的子帐号集合
	 */
	public List<?> findStaffByPidAndUid(Integer eid, String userid);
	
	public Staff findByStid(String stid);
}
