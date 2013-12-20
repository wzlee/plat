package com.eaglec.plat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.StaffMenu;
import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StaffTree;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
/**
 * 权限信息
 * @author chens
 */
@Controller
@RequestMapping(value = "/smenu")
public class StaffMenuController extends BaseController {

	@Autowired
	private StaffMenuBiz menuMangerBiz;
	@Autowired
	private StaffRoleBiz roleManagerBiz;
	@Autowired
	private ManagerBiz managerBiz;
	
	/**
	 * 通过角色主键id返回所有菜单，并且与角色拥有的权限进行对比，如果角色拥有此菜单，则返回前台的时候设置select属性为true
	 *@author chens
	 *@since 2013-08-15 
	 *@param id 角色的主键
	 *@return 根据传过来的角色主键返回所有的菜单，并设置菜单是否选中 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/allLoad", method = RequestMethod.GET)
	@ResponseBody
	public void allAuthQuery(HttpServletRequest request,HttpServletResponse response,Integer id,Integer type){
		List<StaffMenu> allrights =menuMangerBiz.findAll(type);
		String menuids= roleManagerBiz.queryRoleById(id).getMenuIds();
		if(menuids!=null&&!"".equals(menuids)){
			List<StaffMenu> rolerights = menuMangerBiz.findMenus(menuids.toString());
			for(StaffMenu userrole:rolerights){
				for(StaffMenu all:allrights){
					if(userrole.equals(all)){
						all.setSelect(true);
					}
				}
			}
			this.outJson(response, StaffTree.getResult(allrights));
			return;
		}else{
			this.outJson(response, StaffTree.getResult(allrights));
			return;
		}
	}

	/**
	 * 查询所有角色
	 *@author chens
	 *@since 2013-08-15 
	 *@return 所有的角色列表 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryrole", method = RequestMethod.POST)
	@ResponseBody
	public void queryrole(HttpServletRequest request,HttpServletResponse response,String rolename,Integer limit,Integer page,Integer start,Integer type){
		if(rolename!=null&&!"".equals(rolename)){
			this.outJson(response, roleManagerBiz.queryRoleByName(rolename,type,start,limit));
		}else{
			List<StaffRole> list =roleManagerBiz.queryRole(type);
			this.outJson(response, new JSONData<StaffRole>(true, list, list.size()));
		}
	}
	
	
	/**
	 * 更新或新增角色
	 *@author chens
	 *@since 2013-08-15 
	 *@param menutree 权限菜单id组成的数组
	 *@param rolename 角色名称
	 *@param roledesc 角色描述
	 *@param id 角色主键
	 *@return 如果id为空则是新增一个角色   否则是更新一个角色    两者都需要判断角色名唯一 
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/updaterole")
	@ResponseBody
	public void updaterole(String menutree,HttpServletRequest request,HttpServletResponse response,String rolename,Integer id,String roledesc,Integer type,Integer checkeda){
		boolean apply = false;
		if(checkeda == null || checkeda == 0){
			apply = false;
		}else if (checkeda == 1){
			apply = true;
		}
		if(id!=null&&!"".equals(id)){
			//修改
			try {
				StaffRole old = roleManagerBiz.queryRoleById(id);
				if (rolename != null && !"".equals(rolename)) {
					StaffRole role = roleManagerBiz.queryRoleByName(rolename,type);
					if (role != null&& !role.getRolename().equals(old.getRolename())) {
						this.outJson(response, new JSONResult(false,"角色名已经存在，不能创建相同角色"));
					} else {
						old.setRoledesc(roledesc);
						old.setRolename(rolename);
						old.setApply(apply);
						old.setRoleType(type);
						//一下if用于比对哪些菜单要删除，哪些菜单要增加
						if(menutree!=null&&menutree.length()!=0){
							old.setMenuIds(menutree.toString());
						}
						roleManagerBiz.saveRole(old);
						this.outJson(response, new JSONResult(true, "修改角色完成"));
					}
				} else {
					this.outJson(response, new JSONResult(false, "角色名不能为空"));
				}
			} catch (Exception e) {
				this.outJson(response, new JSONResult(false, "修改失败"));
				e.printStackTrace();
			}
		}else{
			//新增
			StaffRole r = new StaffRole();
			if (rolename != null && !"".equals(rolename)) {
				StaffRole role = roleManagerBiz.queryRoleByName(rolename,type);
				if (role != null) {
					this.outJson(response, new JSONResult(false,"角色名已经存在，不能创建相同角色"));
				} else {
					r.setRoledesc(roledesc);
					r.setRolename(rolename);
					r.setCreateTime(new java.util.Date());
					r.setRoleType(type);
					r.setApply(apply);
					roleManagerBiz.saveRole(r);
					this.outJson(response, new JSONResult(true, "创建角色完成"));
				}
			} else {
				this.outJson(response, new JSONResult(false, "角色名不能为空"));
			}
		}
	}
	
	/**
	 * 删除角色
	 *@author chens
	 *@since 2013-08-15 
	 *@param id 角色主键
	 *@return 根绝角色id删除角色   如果关联到用户就会报异常   
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/deleterole")
	@ResponseBody
	public void deleterole(HttpServletRequest request,HttpServletResponse response,Integer id){
		try {
			StaffRole role = roleManagerBiz.queryRoleById(id);
			roleManagerBiz.deleteRole(role);
			this.outJson(response, new JSONResult(true, "已删除角色"));
		} catch (Throwable e) {
			e.printStackTrace();
			this.outJson(response, new JSONResult(false, "无法删除该角色，请确保该角色下没有用户"));
		}
	}
	
	/**
	 * 根据角色id查询属于这个角色的所有用户，如果角色id为空 则查询所有的用户
	 *@author chens
	 *@since 2013-08-15 
	 *@param roleid 角色主键
	 *@return 用户列表
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryusers", method = RequestMethod.POST)
	@ResponseBody
	public void queryusers(HttpServletRequest request,HttpServletResponse response,String username,Integer limit,Integer page,Integer start){
		List<Manager> list=new ArrayList<Manager>();
		if(username!=null&&!"".equals(username)){
			this.outJson(response, managerBiz.queryManagerByName(username, start, limit));
		}else{
			list=managerBiz.findUser();
			this.outJson(response, new JSONData<Manager>(true, list, list.size()));
		}
	}
		
	/**
	 * 前台 子帐号管理 中加载所属角色
	 * @author pangyf
	 * @since 2013-10-18
	 * @param type
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/queryStaffRole")	
	public void queryStaffRole(@RequestParam(value="type",required=false)Integer type,HttpServletRequest request,HttpServletResponse response){
		if(type <= 2){
			type = Constant.ROLE_BUYER_ENTERPRISE;
		}else {
			type = Constant.ROLE_SELLER_ENTERPRISE;
		}
		List<StaffRole> list = roleManagerBiz.queryRole(type);
		list.add(0, new StaffRole(0,"全部"));
		this.outJson(response, list);		
	}
}
