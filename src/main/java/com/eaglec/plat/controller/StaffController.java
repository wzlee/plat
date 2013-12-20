package com.eaglec.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.chatonline.ChatOnlineBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;

@Controller
@RequestMapping(value="/staff")
public class StaffController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffRoleBiz staffRoleBiz;
	@Autowired
	private ChatOnlineBiz chatOnlineBiz;
	/**
	 * 添加子账号
	 * @author xuwf
	 * @since 2013-8-23
	 * 
	 * @param staff
	 * @param parentId	主账号id(查询在该主账号下面是否存在相同用户名的子账号)
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value = "/save")
	public void add(
			@RequestParam(value="parentId",required=false)Integer parentId,
			@RequestParam(value="staffRoleId",required=false)Integer staffRoleId,
			@RequestParam(value="managerId",required=false)Integer managerId,
			Staff staff,HttpServletRequest request,HttpServletResponse response) {
		logger.info("queryParams[parentId:"+parentId+",managerId:"+managerId+"]");
		try {
			Long staffTotal = staffBiz.getTotal(parentId);
			logger.info("子账号个数："+staffTotal);
			Integer staffNum = Integer.valueOf(String.valueOf(staffTotal));	//子账号个数
			if(staffNum >=Constant.CUSER_NUM){
				this.outJson(response, new JSONResult(false, "每个企业子账号数量限制20个,如需添加,请联系管理员"));
				return;
			}
			if("".equals(staff.getUserName().trim())){//服务器端验证不能为空
				this.outJson(response, new JSONResult(false, "用户名不能为空"));
				return;
			}
			//得到子账号所属的主账号
			User parent = userBiz.findUserById(parentId);
			staff.setParent(parent);
//			staff.setEnterprise(parent.getEnterprise());//设置子账号对应企业
			//查看是否有相同的子账号
			if(!StringUtils.isEmpty(staffBiz.findByUserName(parent.getUserName()+"_"+staff.getUserName())) 
				&& StringUtils.isEmpty(staffBiz.findByUserName(parent.getUserName()))){
				this.outJson(response, new JSONResult(false, "该用户名已经存在,请重新输入"));
				return;
			}
			if(!StringUtils.isEmpty(staffRoleId)){//子账号角色
				StaffRole staffRole = staffRoleBiz.queryRoleById(staffRoleId);
				staff.setStaffRole(staffRole);
			}
			if(!StringUtils.isEmpty(managerId)){//分配人为平台人员
			//得到子账号的分配人(登录用户)
				Manager manager= (Manager) request.getSession().getAttribute("manager");
				staff.setManager(manager);
			}else{//分配人为主账号
				staff.setAssigner(parent); 
			}
			//密码md5加密
			staff.setPassword(MD5.toMD5(staff.getPassword()));	
			//分配时间为当前时间
			String assignTime = DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
			staff.setAssignTime(assignTime);
			
			//子账号用户名=主账号用户名+自己填写的个性化名称
			staff.setUserName(parent.getUserName()+"_"+staff.getUserName());	
			staffBiz.save(staff);
			this.outJson(response,new JSONResult(true,"子账号添加成功!"));
			logger.info("[ "+staff.getUserName()+" ]添加成功!");
			/**
			 * @date: 2013-12-3
			 * @author：lwch
			 * @description：添加子帐号完成后，立刻向在线客服user表中添加该帐号
			 * =======================================================================================
			 */
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("username", staff.getUserName());
			if ("".equals(staff.getStaffName())) {
				userMap.put("staffname", staff.getUserName());
			} else {
				userMap.put("staffname", staff.getStaffName());
			}
			userMap.put("password", staff.getPassword());
			userMap.put("role", Constant.CHAT_ROLE_USER);	//普通用户
			Enterprise enterprise = (Enterprise)request.getSession().getAttribute("loginEnterprise");
			userMap.put("eid", enterprise.getId());
			userMap.put("email", staff.getEmail());
			chatOnlineBiz.addUsers(userMap);
			/**
			 * =======================================================================================
			 */
			
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"子账号保存失败!异常信息:"+e.getLocalizedMessage()));
			logger.info("子账号保存失败!异常信息:"+e.getMessage());
		}
	}
	/**
	 * 检查子账号用户名
	 * @param request
	 * @param model
	 * @param response
	 * @param username
	 */
	@RequestMapping(value = "/checkStaffName")
	public void checkStaffName(HttpServletRequest request, Model model,HttpServletResponse response, 
			String username,Integer parentId) {
		User parent = userBiz.findUserById(parentId);
		//子账号
		if(StringUtils.isEmpty(staffBiz.findByUserName(parent.getUserName()+"_"+username)) 
			&& StringUtils.isEmpty(staffBiz.findByUserName(parent.getUserName()))){
			this.outJson(response, new JSONResult(true, "一旦注册成功不能修改"));
		}else{
			this.outJson(response, new JSONResult(false, "该用户名已被注册，请更换用户名"));
		}
	}
	/**
	 * 查询子账号列表
	 * @author xuwf
	 * @since 2013-8-23
	 * 
	 * @param parentId	主账号id	
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="query")
	public void find(
			@RequestParam(value="parentId",required=false)Integer parentId,
			@RequestParam("start")Integer start,
			@RequestParam("limit")Integer limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("queryParams[parentId:"+parentId+",start:"+start+",limit:"+limit+"]");
		
		JSONData<Staff> services = staffBiz.findStaff(parentId, start, limit);
		this.outJson(response,services);
	}
	
	/**
	 * 子账号用户状态更改(禁用,删除,恢复)
	 *@author xuwf
	 *@since 2013-8-23
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/update")
	public void apply(Staff staff,HttpServletRequest request,
			HttpServletResponse response) {
			try {
//				Staff _staff = JSON.parseObject(staff, Staff.class);
				if(staff.getManager().getId() == 0){
					staff.setManager(null);
				}
				if(staff.getAssigner().getId() == 0){
					staff.setAssigner(null);
				}
				staffBiz.update(staff);
				logger.info("[ "+staff.getUserName()+" ]更新成功!");
				this.outJson(response,new JSONResult(true,"子账号更新成功!"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"子账号更新失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("子账号更新失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 子账号编辑	支撑平台
	 *@author xuwf
	 *@since 2013-8-28
	 *
	 *@param Staff 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/edit")
	public void update(Staff staff,HttpServletRequest request,HttpServletResponse response) {
			logger.info("staff:"+staff.toString());
			try {
				if(staff.getManager().getId() == 0){
					staff.setManager(null);
				}
				if(staff.getAssigner().getId() == 0){
					staff.setAssigner(null);
				}
				staffBiz.update(staff);	
				logger.info("[ "+staff.getUserName()+" ]修改成功!");
				this.outJson(response,new JSONResult(true,"子账号[" + staff.getUserName() + "]修改成功"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"子账号[" + staff.getUserName() + "]修改失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("账号修改失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	/**
	 * 运营平台加载子账号角色
	 * @author xuwf
	 * @since 2013-10-19
	 *
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/loadStaffRole")
	public void loadStaffRole(@RequestParam(value="enterpriseType",defaultValue="",required=false)Integer enterpriseType, HttpServletRequest request,HttpServletResponse response) {
		logger.info(request.getParameterMap().toString());
		try {
			
			if(!StringUtils.isEmpty(enterpriseType)){
				List<StaffRole> staffRoleList = new ArrayList<StaffRole>();
				if(enterpriseType >= Constant.ENTERPRISE_TYPE_ORG){//机构用户
					staffRoleList = staffRoleBiz.queryRole(Constant.ROLE_SELLER_ENTERPRISE);
				}else{//企业用户
					staffRoleList = staffRoleBiz.queryRole(Constant.ROLE_BUYER_ENTERPRISE);
				}		
				
				this.outJson(response,staffRoleList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("类别加载失败:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "类别加载失败!"));
		}
	}
	
	/**
	 * 用户中心编辑子账号信息
	 * @author xuwf
	 * @since 2013-10-19
	 * 
	 * @param staff
	 * @param flag 密码是否已修改的标记，0未修改	1已修改
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value="modifyStaff")
	public void modifyStaff(Staff staff,
			@RequestParam(value="flag",required=false,defaultValue="0")Integer flag,
			Integer staffRoleId,Integer parentId,
			HttpServletRequest request,HttpServletResponse response) {
			logger.info("staff:"+staff.toString());
			try {
				if(flag == 1){//密码是否已修改的标记 1.已修改
					staff.setPassword(MD5.toMD5(staff.getPassword()));
				}
				StaffRole sr = staffRoleBiz.queryRoleById(staffRoleId);
				User user = userBiz.findUserById(parentId);
				staff.setParent(user);
				staff.setAssigner(user);
				staff.setStaffRole(sr);
				staffBiz.update(staff);	
				logger.info("[ "+staff.getUserName()+" ]修改成功!");
				this.outJson(response,new JSONResult(true,"子账号[" + staff.getUserName() + "]修改成功"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"子账号[" + staff.getUserName() + "]修改失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("账号修改失败!异常信息:"+e.getLocalizedMessage());
			}
	}
	
	/**
	 * 前台根据用户账号、子账号状态、子账号角色查找子账号
	 * @author pangyf
	 * @since 2013-10-17
	 * @param pid
	 * @param status
	 * @param name
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryUserStaff")
	public void queryUserStaff(
			@RequestParam(value="pid",required=false)Integer pid,
			@RequestParam(value="staffStatus",defaultValue="",required=false)Integer staffStatus,
			@RequestParam(value="staffRoleId",defaultValue="",required=false)Integer staffRoleId,
			@RequestParam(value="page",required=false)int page,
			@RequestParam(value="rows",required=false)int rows,
			HttpServletRequest request,HttpServletResponse response) {
		logger.info("pid:"+pid);
		try {
			User user = (User)request.getSession().getAttribute("user");
			user = userBiz.findUserById(user.getId());
			request.getSession().setAttribute("user", user);
			this.outJson(response, staffBiz.findStaffByPid(pid, staffStatus, staffRoleId, rows * (page - 1), rows));			
		} catch (Exception e) {
			logger.info("账号修改失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 前台修改子账号状态（禁用，删除，恢复）
	 * @author pangyf
	 * @since 2013-10-17
	 * @param id
	 * @param status
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/changeUserStaff")
	public void changeUserStaff(@RequestParam(value="id",required=false)Integer id,@RequestParam(value="staffStatus",required=false)Integer staffStatus,HttpServletRequest request,HttpServletResponse response) {
		logger.info("id:"+id);
		User user = (User)request.getSession().getAttribute("user");
		user = userBiz.findUserById(user.getId());
		request.getSession().setAttribute("user", user);
		Staff staff = staffBiz.getStaff(id);
		String tips = null;
		switch(staffStatus){
			case 1 : tips ="还原";
				break;
			case 2 : tips ="禁用";
				break;
			case 3 : tips ="删除";
				break;
		}
		try {			
			staff.setStaffStatus(staffStatus);
			staffBiz.update(staff);
			this.outJson(response,new JSONResult(true,"子账号[" + staff.getUserName() + "]"+ tips +"成功"));
			logger.info("子账号修改成功!");
		} catch (Exception e) {
			this.outJson(response,new JSONResult(false,"子账号[" + staff.getUserName() + "]"+ tips +"失败"));
			logger.info("子账号修改失败!异常信息:"+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 子账号登录首页编辑子账号信息
	 * @author pangyf
	 * @since 2013-10-19
	 * @param staff
	 * @param flag 密码是否已修改的标记，0未修改	1已修改
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value="editStaff")
	public void editStaff(Staff staff,@RequestParam(value="flag",required=false,defaultValue="0")Integer flag,
			HttpServletRequest request,HttpServletResponse response) {
			logger.info("staff = "+staff.toString()+" and flag =" + flag);
			Staff _staff = staffBiz.getStaff(staff.getId());
			try {				
				if(flag == 1){//密码是否已修改的标记 1.已修改
					_staff.setPassword(MD5.toMD5(staff.getPassword()));
				}
				_staff.setStaffName(staff.getStaffName());
				_staff.setSex(staff.getSex());
				_staff.setMobile(staff.getMobile());
				staffBiz.update(_staff);
				request.getSession().setAttribute("user",_staff);
				logger.info("[ "+_staff.getUserName()+" ]修改成功!");
				this.outJson(response,new JSONResult(true,"子账号[" + _staff.getUserName() + "]修改成功"));
			} catch (Exception e) {
				this.outJson(response,new JSONResult(false,"子账号[" + _staff.getUserName() + "]修改失败!异常信息:"+e.getLocalizedMessage()));
				logger.info("子账号修改失败!异常信息:"+e.getLocalizedMessage());
			}
	}
}
