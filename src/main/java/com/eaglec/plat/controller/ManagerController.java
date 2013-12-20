package com.eaglec.plat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.biz.auth.RoleBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.view.FlatManagerView;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
/**
 * 用户管理
 * @author chens
 */
@Controller
@RequestMapping(value = "/manage")
public class ManagerController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private ManagerBiz managerBiz;
	@Autowired
	private RoleBiz roleManagerBiz;
	
	/**
	 * 更新用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param rolename 角色名字
	 *@param username 用户名称
	 *@return 通过用户名和角色名 来改变用户所属的角色
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/update")
	@ResponseBody
	public void userupdate(Manager manager,Integer roleId,String username,HttpServletRequest request,HttpServletResponse response){
		try {
			if(roleId!=null&&!roleId.equals("")){
				manager.setRole(roleManagerBiz.queryRoleById(roleId));
			}else{
				manager.setRole(null);
			}
			manager.setUsername(username);
			managerBiz.saveManager(manager);
			this.outJson(response, new JSONResult(true, "修改成功"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "修改失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param username 用户名称
	 *@return 通过用户名来删除一个用户 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void userdelete(String username,HttpServletRequest request,HttpServletResponse response){
		try {
			Manager manager = managerBiz.findUserByUsername(username);
			manager.setRole(null);
			manager.setUserStatus(Constant.DELETED);
			managerBiz.saveManager(manager);
			this.outJson(response, new JSONResult(true, "删除成功"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "删除失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 禁用用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param username 用户名称
	 *@return 通过用户名来删除一个用户 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/forbid")
	@ResponseBody
	public void forbid(String username,HttpServletRequest request,HttpServletResponse response){
		try {
			Manager manager = managerBiz.findUserByUsername(username);
			manager.setUserStatus(Constant.DISABLED);
			managerBiz.saveManager(manager);
			this.outJson(response, new JSONResult(true, "禁止成功"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "禁止失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 还原用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param username 用户名称
	 *@return 通过用户名来还原一个用户 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/restore")
	@ResponseBody
	public void restore(String username,HttpServletRequest request,HttpServletResponse response){
		try {
			Manager manager = managerBiz.findUserByUsername(username);
			manager.setUserStatus(1);
			managerBiz.saveManager(manager);
			this.outJson(response, new JSONResult(true, "禁止成功"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "禁止失败"));
			e.printStackTrace();
		}
	}
	 
	
	
	/**
	 * 新增一个用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param passwordvalidate 二次密码
	 *@param password 密码
	 *@param username 用户名
	 *@param rolename 角色名称
	 *@return 通过参数创建一个新的用户    需要验证用户名唯一性 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/save")
	@ResponseBody
	public void usersave(Manager manager,String passwordvalidate,Integer roleId,HttpServletRequest request,HttpServletResponse response){
		try {
				if(manager.getPassword()==null||"".equals(manager.getPassword())){
					this.outJson(response, new JSONResult(false, "密码不能为空"));
					return;
				}else if(passwordvalidate==null||"".equals(passwordvalidate)){
					this.outJson(response, new JSONResult(false, "请填写密码验证"));
					return;
				}else if(!manager.getPassword().equals(passwordvalidate)){
					this.outJson(response, new JSONResult(false, "两次密码不一致"));
					return;
				}else{
					if(manager.getUsername()!=null&&!"".equals(manager.getUsername())){
						Manager managercom = managerBiz.findUserByUsername(manager.getUsername());
							if(managercom!=null){
						this.outJson(response, new JSONResult(false, "你所添加的用户"
								+ manager.getUsername() + "已存在！"));
								return;
							}else{
								manager.setPassword(manager.getPassword());
								if(roleId!=null&&!roleId.equals("")){
									manager.setRole(roleManagerBiz.queryRoleById(roleId));
								}else{
									manager.setRole(null);
								}
								manager.setUserStatus(1);
								managerBiz.saveManager(manager);
								this.outJson(response, new JSONResult(true, "恭喜你注册成功"));
								return;
							}
					}
				}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "悲剧，注册失败"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改当前登录的运营用户的密码
	 * 
	 * @author Xiadi
	 * @since 2013-9-13
	 * 
	 * @param newpwd
	 *            新密码
	 * @param oldpwd
	 *            旧密码
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/modifyCurrentPwd")
	public void modifyPwd(String newpwd, String oldpwd,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Manager manager = (Manager) request.getSession().getAttribute(
					"manager");
			if (manager.getPassword().equals(oldpwd)) {
				manager.setPassword(newpwd);
				request.getSession().setAttribute("manager", manager);
				managerBiz.saveManager(manager);
				logger.info("用户" + manager.getUsername() + "修改密码成功");
				this.outJson(response, new JSONResult(true, "密码修改成功！"));
			} else {
				this.outJson(response, new JSONResult(false, "原密码不正确！"));
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "服务器错误！"));
			e.printStackTrace();
		}
	}

	/**
	 * 运营主界面解锁
	 * 
	 * @author Xiadi
	 * @since 2013-9-13
	 * 
	 * @param pwd 解锁密码
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/unlock")
	public void unlock(String password, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Manager manager = (Manager) request.getSession().getAttribute(
					"manager");
			if (manager.getPassword().equals(password)) {
				this.outJson(response, new JSONResult(true, "解锁成功！"));
			} else {
				this.outJson(response, new JSONResult(false, "密码不正确！解锁失败"));
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "服务器错误！"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 管理员密码重置 设置为默认123456
	 * 
	 * @author Xiadi
	 * @since 2013-9-13
	 * 
	 * @param pwd
	 *            解锁密码
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/repwd")
	public void repwd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("userId");
			Manager manager = managerBiz.findUserById(Integer.parseInt(id));
			if (null != manager) {
				manager.setPassword(MD5.toMD5("123456"));
				managerBiz.saveManager(manager);
				this.outJson(response, new JSONResult(true,
						"密码重置成功，重置后的密码为【123456】"));
			}
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "服务器错误！"));
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过平台id查询子窗口管理员
	 * @author liuliping
	 * @since 2013-10-15
	 * @param flatId 平台id
	 * @param start 查询结果起始数
	 * @param limit 每页记录条数
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findByFlatId")
	public void findByFlatId(Integer flatId, Integer userStatus, Integer roleId,
			@RequestParam(value = "start", defaultValue = "0", required = true)int start, 
			@RequestParam(value = "limit", defaultValue = "30", required = true)int limit,
			HttpServletRequest request, HttpServletResponse response) {
		JSONData<FlatManagerView> jd = managerBiz.findByFlatId(flatId, userStatus, roleId, start, limit);
		jd.setSuccess(true);
		outJson(response, jd);
	}
	
	/**
	 * 通过平台名称查询平台管理员
	 * @author liuliping
	 * @since 2013-10-15
	 * @param flatName 平台名称
	 * @param start 查询结果起始数
	 * @param limit 每页记录条数
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findByFlatName")
	public void findByFlatName(String flatName, 
			@RequestParam(value = "start", defaultValue = "0", required = true)int start, 
			@RequestParam(value = "limit", defaultValue = "30", required = true)int limit,
			HttpServletRequest request, HttpServletResponse response) {
		JSONData<Manager> jd = managerBiz.findByFlatName(flatName, start, limit);
		outJson(response, jd);
	}
}
