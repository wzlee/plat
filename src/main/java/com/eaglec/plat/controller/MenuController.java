package com.eaglec.plat.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.biz.auth.MenuBiz;
import com.eaglec.plat.biz.auth.RoleBiz;
import com.eaglec.plat.domain.auth.Menu;
import com.eaglec.plat.domain.auth.Role;
import com.eaglec.plat.utils.RecursionTree;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONResult;
/**
 * 权限信息
 * @author chens
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

	@Resource
	private MenuBiz menuMangerBiz;
	@Autowired
	private RoleBiz roleManagerBiz;
	@Autowired
	private ManagerBiz managerBiz;
	
	/**
	 * 通过pid来装载菜单
	 *@author chens
	 *@since 2013-08-15 
	 *@param pid 菜单的pid
	 *@return outlist 根据传过来的pid不同返回不同的菜单给页面	 
	 */
/*	@RequestMapping(value = "/load", method = RequestMethod.GET)
	@ResponseBody
	public void userAuthQuery(@RequestParam Integer pid,HttpServletRequest request,HttpServletResponse response){
		Manager manager =(Manager) request.getSession().getAttribute("manager");
		if(manager!=null&&manager.getRole()!=null){
			List<Menu> list = manager.getRole().getMenu();
			List<Menu> outlist = new ArrayList<Menu>();
			if(list!=null&&list.size()!=0){
				for(int i = 0 ;i<list.size();i++){
					if(list.get(i).getPid().equals(pid)){
						outlist.add(list.get(i));
					}
				}
			}
			this.outJson(response, outlist);
		}
	}*/

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
	public void allAuthQuery(HttpServletRequest request,HttpServletResponse response,Integer id){
		List<Menu> allrights =menuMangerBiz.findAll();
			if(id!=null&&!id.equals("")){
				String menuids= roleManagerBiz.queryRoleById(id).getMenuIds();
				if(menuids!=null&&!"".equals(menuids)){
					List<Menu> rolerights = menuMangerBiz.findMenus(menuids.toString());
					for(Menu userrole:rolerights){
						for(Menu all:allrights){
							if(userrole.equals(all)){
								all.setSelect(true);
							}
						}
					}
					this.outJson(response, RecursionTree.getResult(allrights));
					return;
				}else{
					this.outJson(response, RecursionTree.getResult(allrights));
					return;
				}
			}else{
				this.outJson(response, RecursionTree.getResult(allrights));
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
	public void queryrole(HttpServletRequest request,HttpServletResponse response,String rolename,Integer limit,Integer page,Integer start){
		if(rolename!=null&&!"".equals(rolename)){
			this.outJson(response, roleManagerBiz.queryRoleByName(rolename, start, limit));
		}else{
			List<Role> list =roleManagerBiz.queryRole();
			this.outJson(response, new JSONData<Role>(true, list, list.size()));
		}
	}
	
//	//查询角色
//	@RequestMapping(value = "/queryrole", method = RequestMethod.POST)
//	@ResponseBody
//	public void queryroleByRolename(
//			@RequestParam(value="rolename",defaultValue="",required=false) String rolename,
//			@RequestParam(value="start",defaultValue="",required=false) int start,
//			@RequestParam(value="limit",defaultValue="",required=false) int limit,HttpServletRequest request,HttpServletResponse response){
//		this.outJson(response, roleManagerBiz.queryRoleByName(rolename, start, limit));
//	}
	
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
	public void updaterole(String menutree,HttpServletRequest request,HttpServletResponse response,String rolename,Integer id,String roledesc){
		if(id!=null&&!"".equals(id)){
			try {
				Role old = roleManagerBiz.queryRoleById(id);
				if (rolename != null && !"".equals(rolename)) {
					Role role = roleManagerBiz.queryRoleByName(rolename);
					if (role != null&& !role.getRolename().equals(old.getRolename())) {
						this.outJson(response, new JSONResult(false,"角色名已经存在，不能创建相同角色"));
					} else {
						old.setRoledesc(roledesc);
						old.setRolename(rolename);
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
			Role r = new Role();
			if (rolename != null && !"".equals(rolename)) {
				Role role = roleManagerBiz.queryRoleByName(rolename);
				if (role != null) {
					this.outJson(response, new JSONResult(false,"角色名已经存在，不能创建相同角色"));
				} else {
					r.setRoledesc(roledesc);
					r.setRolename(rolename);
					r.setCreateTime(new java.util.Date());
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
			Role role = roleManagerBiz.queryRoleById(id);
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
		this.outJson(response, managerBiz.queryManagerByName(username, start, limit));
	}
}
