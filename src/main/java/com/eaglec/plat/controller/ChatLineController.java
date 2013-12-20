package com.eaglec.plat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.chatonline.ChatOnlineBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONResult;


@Controller
@RequestMapping(value = "/chat")
public class ChatLineController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatLineController.class);
	
	@Autowired
	private ChatOnlineBiz chatOnlineBiz;
	
	@Autowired
	private StaffBiz staffBiz;
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：系统用户登录后，立即向在线客服消息路由器登录
	 */
	@RequestMapping(value = "/chatlogin")
	public void chatlogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			//企业管理员登录
			User u = (User)request.getSession().getAttribute("user");
			if (u != null) {
				request.getSession().setAttribute("chatrole", "admin");
			}
		} catch (Exception e) {
			try {
				//企业子帐号登录
				Staff s = (Staff)request.getSession().getAttribute("user");
				if (s != null) {
					request.getSession().setAttribute("chatrole", "user");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	

	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：系统用户登录后，立即向在线客服消息路由器登录
	 */
	@RequestMapping(value = "/chatlogin_bak")
	public String chatlogin_bak(HttpServletRequest request, HttpServletResponse response) {
		try {
			//企业管理员登录
			User u = (User)request.getSession().getAttribute("user");
			if (u != null) {
				//从session中获取当前企业的信息
				Enterprise ue = (Enterprise)request.getSession().getAttribute("loginEnterprise");
				if (ue != null) {
					int plateid = ue.getId();
					//根据当前用户所属枢纽平台企业id去在线客服服务器系统查询映射的企业id
					int chateid = chatOnlineBiz.getChatEidByPlatEid(plateid);
					request.getSession().setAttribute("chateid", chateid);
					//设置客服角色，这里为客服管理员
					request.getSession().setAttribute("chatrole", "admin");
					request.getSession().setAttribute("userLogin", true);
				} else {
					request.getSession().setAttribute("userLogin", false);
				}
			} else {
				request.getSession().setAttribute("userLogin", false);
			}
			return "chatlogin";
		} catch (Exception e) {
			try {
				//企业子帐号登录
				Staff s = (Staff)request.getSession().getAttribute("user");
				if (s != null) {
					//从session中获取当前企业的信息
					Enterprise ue = (Enterprise)request.getSession().getAttribute("loginEnterprise");
					if (ue != null) {
						int plateid = ue.getId();
						//根据当前用户所属枢纽平台企业id去在线客服服务器系统查询映射的企业id
						int chateid = chatOnlineBiz.getChatEidByPlatEid(plateid);
						request.getSession().setAttribute("chateid", chateid);
						//设置客服角色，这里为普通客服
						request.getSession().setAttribute("chatrole", "user");
						request.getSession().setAttribute("userLogin", true);
					} else {
						request.getSession().setAttribute("userLogin", false);
					}
				} else {
					request.getSession().setAttribute("userLogin", false);
				}
				return "chatlogin";
			} catch (Exception e1) {
				e1.printStackTrace();
				return "error/500";
			}
		}
	}
	
	/**
	 * @date: 2013-10-21
	 * @author：lwch
	 * @description：加载在线客服端管理界面
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value="/chatonline")
	public String chatList(HttpServletRequest request, HttpServletResponse response){
		try {
			//企业管理员登录
			User u = (User)request.getSession().getAttribute("user");
			if (u != null) {
				List<?> cuser = chatOnlineBiz.getOneChatUser(u.getEnterprise().getId(), u.getUserName());
				if (cuser.size() > 0) {
					Map<String, Object> cMap = (Map<String, Object>)cuser.get(0);
					Integer uid = (Integer)cMap.get("uid");
					chatOnlineBiz.updateChatUserStatus(uid, Constant.CHAT_USER_STATUS_ONLINE);
					request.getSession().setAttribute("chatuserid", uid);
				}
			}
		} catch (Exception e) {
			try {
				//企业子帐号登录
				Staff s = (Staff)request.getSession().getAttribute("user");
				if (s != null) {
					Enterprise enterprise = (Enterprise)request.getSession().getAttribute("loginEnterprise");
					List<?> cuser = chatOnlineBiz.getOneChatUser(enterprise.getId(), s.getUserName());
					if (cuser.size() > 0) {
						Map<String, Object> cMap = (Map<String, Object>)cuser.get(0);
						Integer uid = (Integer)cMap.get("uid");
						chatOnlineBiz.updateChatUserStatus(uid, Constant.CHAT_USER_STATUS_ONLINE);
						request.getSession().setAttribute("chatuserid", uid);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "ucenter/chatonline/chatonline";
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据企业ID查询该企业下的客服分组
	 */
	@RequestMapping(value = "/getChatGroup")
	public void getChatGroup(
			@RequestParam("eid")Integer eid,
			@RequestParam("username")String username,
			@RequestParam("allGroup")Boolean allGroup,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<Map<String, Object>> list = chatOnlineBiz.getChatGroupByEid(eid, username);
			if (allGroup) {
				String tempStr = "{\"id\":\"0\", \"groupName\":\"所有分组\"}";
				JSONObject tempList = JSON.parseObject(tempStr);
				list.add(0, tempList);
			}
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"企业分组信息加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：给某个企业添加分组
	 */
	@RequestMapping(value = "/addGroup")
	public void addGroup(
			@RequestParam("eid")Integer eid,	//企业ID
			@RequestParam("name")String name, 	//组名称
			@RequestParam("pic")String pic, 	//组图标
			@RequestParam("rid")Integer rid, 	//所属机器人id
			@RequestParam("desc")String desc, 	//组描述
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> groupMap = new HashMap<String, Object>();
		groupMap.put("eid", eid);
		groupMap.put("name", name);
		groupMap.put("pic", pic);
		groupMap.put("g_type", true);
		groupMap.put("rid", rid);
		groupMap.put("desc", desc);
		try {
			chatOnlineBiz.addGroup(groupMap);
			logger.info("添加分组成功！");
			this.outJson(response,new JSONResult(true, "添加分组成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "添加分组失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服组信息
	 */
	@RequestMapping(value = "/updateGroup")
	public void updateGroup(
			@RequestParam("id")Integer id,		//组ID
			@RequestParam("name")String name, 	//组名称
			@RequestParam("pic")String pic, 	//组图标
			@RequestParam("rid")Integer rid, 	//所属机器人id
			@RequestParam("desc")String desc, 	//组描述
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> groupMap = new HashMap<String, Object>();
		groupMap.put("id", id);
		groupMap.put("name", name);
		groupMap.put("pic", pic);
		groupMap.put("rid", rid);
		groupMap.put("desc", desc);
		try {
			chatOnlineBiz.updateGroup(groupMap);
			this.outJson(response,new JSONResult(true, "修改分组成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "修改分组失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服组
	 */
	@RequestMapping(value = "/deleteGroup")
	public void deleteGroup(@RequestParam("id")Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			Long l = chatOnlineBiz.getChatCountByGroupid(id);
			if (l > 0) {
				this.outJson(response,new JSONResult(false, "该组下还有客服信息，不能被删除！"));
			} else {
				chatOnlineBiz.deleteGroup(id);
				this.outJson(response,new JSONResult(true, "删除分组成功！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "删除分组失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据用户的角色和所属企业ID查询该企业下的客服信息
	 */
	@RequestMapping(value = "/getChatUsers")
	public void getChatUsers(
			@RequestParam("eid")Integer eid,
			@RequestParam("allUsers")Boolean allUsers,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<Map<String, Object>> list = chatOnlineBiz.getChatUsersByEidAndRole(eid, Constant.CHAT_ROLE_SERVICE);
			if (allUsers) {
				String tempStr = "{\"id\":\"0\", \"staffname\":\"请选择客服\"}";
				JSONObject tempList = JSON.parseObject(tempStr);
				list.add(0, tempList);
			}
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"企业分组信息加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：批量保存在线客服用户
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/batchSaveUser")
	public void batchSaveUser(
			@RequestParam("eid")Integer eid,
			@RequestParam("deludata")String deludata,
			@RequestParam("userdata")String userdata,
			HttpServletRequest request, HttpServletResponse response){
		try {
			//删除的客服
			JSONArray delArray = JSON.parseArray("["+ deludata +"]");
			for (int i = 0; i < delArray.size(); i++) {
				Map<String, Object> deluMap = (Map<String, Object>)delArray.get(i);
				deluMap.put("eid", eid);
				deluMap.put("role", Constant.CHAT_ROLE_USER);
				chatOnlineBiz.updateUsers(deluMap);
			}
			//新增客服
			if(!"".equals(userdata)){
				userdata = userdata.substring(1);
			}
			JSONArray uArray = JSON.parseArray("["+ userdata +"]");
			for (int j = 0; j < uArray.size(); j++) {
				Map<String, Object> uMap = (Map<String, Object>)uArray.get(j);
				uMap.put("eid", eid);
				uMap.put("role", Constant.CHAT_ROLE_SERVICE); //客服用户
				chatOnlineBiz.updateUsers(uMap);
			}
			this.outJson(response,new JSONResult(true, "批量配置客服成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "批量配置客服失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服或客服管理员用户
	 */
	@RequestMapping(value = "/addUsers")
	public void addUsers(
			@RequestParam("id")Integer id,	//这个id是枢纽平台子帐号的id
			@RequestParam("login")String login,
			@RequestParam("eid")Integer eid,
			@RequestParam("email")String email,
			@RequestParam("group_id")Integer group_id,
			@RequestParam("nick")String nick,
			@RequestParam("role")String role,
			HttpServletRequest request, HttpServletResponse response){
		try {
			Staff s = staffBiz.getStaff(id);
			if (s != null) {
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("login", s.getUserName());
				userMap.put("nick", nick);
				userMap.put("password", s.getPassword());
				userMap.put("email", email);
				userMap.put("eid", eid);
				userMap.put("group_id", group_id);
				userMap.put("role", role);
				chatOnlineBiz.addUsers(userMap);
				this.outJson(response,new JSONResult(true, "添加客服成功！"));
			} else {
				this.outJson(response,new JSONResult(false, "枢纽平台系统中不存在该用户信息！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "添加客服失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服信息
	 */
	@RequestMapping(value = "/updateUsers")
	public void updateUsers(
			@RequestParam("id")Integer id,	//这个id是在线客服平台用户表的id
			@RequestParam("email")String email,
			@RequestParam("group_id")Integer group_id,
			@RequestParam("nick")String nick,
			@RequestParam("role")String role,
			HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("id", id);
			userMap.put("nick", nick);
			userMap.put("email", email);
			userMap.put("group_id", group_id);
			userMap.put("role", role);
			chatOnlineBiz.updateUsers(userMap);
			this.outJson(response,new JSONResult(true, "修改客服成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "修改客服失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	@RequestMapping(value = "/deleteUsers")
	public void deleteUsers(@RequestParam("id")Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			chatOnlineBiz.deleteUsers(id);
			this.outJson(response,new JSONResult(true, "删除客服成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "删除客服失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下机器人类别
	 */
	@RequestMapping(value = "/getChatRobots")
	public void getChatRobots(@RequestParam("eid")Integer eid, HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatRobotsByEid(eid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"机器人列表加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-20
	 * @author：lwch
	 * @description：添加机器人
	 */
	@RequestMapping(value = "/addRobot")
	public void addRobot(
			@RequestParam("eid")Integer eid, 
			@RequestParam("name")String name, 
			@RequestParam("remark")String remark, 
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> robotMap = new HashMap<String, Object>();
		robotMap.put("eid", eid);
		robotMap.put("name", name);
		robotMap.put("role", "user");
		robotMap.put("remark", remark);
		try {
			chatOnlineBiz.addRobot(robotMap);
			logger.info("添加分组成功！");
			this.outJson(response,new JSONResult(true, "添加机器人成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "添加机器人失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改机器人
	 */
	@RequestMapping(value = "/updateRobot")
	public void updateRobot(
			@RequestParam("id")Integer id,
			@RequestParam("name")String name, 
			@RequestParam("remark")String remark, 
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> robotMap = new HashMap<String, Object>();
		robotMap.put("id", id);
		robotMap.put("name", name);
		robotMap.put("remark", remark);
		try {
			chatOnlineBiz.updateRobot(robotMap);
			logger.info("添加分组成功！");
			this.outJson(response,new JSONResult(true, "修改机器人成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "修改机器人失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除机器人
	 */
	@RequestMapping(value = "/deleteRobot")
	public void deleteRobot(
			@RequestParam("id")Integer rid,
			@RequestParam("eid")Integer eid,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatRobotsQuestion(eid, rid);
			if (list.size() > 0) {
				this.outJson(response,new JSONResult(false, "该机器人下还有机器人问题，不能被删除！"));
			} else {
				//修改客服组关联了机器人为rid的，将该客服组关联默认机器人
				chatOnlineBiz.modifyGroupToRobot(eid, rid);
				chatOnlineBiz.deleteRobot(rid);
				this.outJson(response,new JSONResult(true, "删除机器人成功！"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "删除机器人失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id和机器人id去查询该企业下机器人的问题
	 */
	@RequestMapping(value = "/getChatRobotsQuestion")
	public void getChatRobotsQuestion(
			@RequestParam("eid")Integer eid,
			@RequestParam("rid")Integer rid,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatRobotsQuestion(eid, rid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"机器人问题列表加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：添加某个企业下的某个机器人的问题
	 */
	@RequestMapping(value = "/addRobotQuestion")
	public void addRobotQuestion(
			@RequestParam("eid")Integer eid,	//企业ID
			@RequestParam("rid")Integer rid,	//机器人ID
			@RequestParam("qid")Integer qid,		//问题ID(自定义的)
			@RequestParam("question")String question,	//问题
			@RequestParam("answer")String answer,		//答案
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> robotMap = new HashMap<String, Object>();
		robotMap.put("eid", eid);
		robotMap.put("rid", rid);
		robotMap.put("qid", qid);
		robotMap.put("question", question);
		robotMap.put("answer", answer);
		try {
			chatOnlineBiz.addRobotQuestion(robotMap);
			logger.info("添加机器人问题成功！");
			this.outJson(response,new JSONResult(true, "添加机器人问题成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "添加机器人问题失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改某个企业下的某个机器人的问题
	 */
	@RequestMapping(value = "/updateRobotQuestion")
	public void updateRobotQuestion(
			@RequestParam("id")Integer id,		//ID
			@RequestParam("eid")Integer eid,	//企业ID
			@RequestParam("question")String question,	//问题
			@RequestParam("answer")String answer,		//答案
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> robotMap = new HashMap<String, Object>();
		robotMap.put("id", id);
		robotMap.put("eid", eid);
		robotMap.put("question", question);
		robotMap.put("answer", answer);
		try {
			chatOnlineBiz.updateRobotQuestion(robotMap);
			logger.info("修改机器人问题成功！");
			this.outJson(response,new JSONResult(true, "修改机器人问题成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "修改机器人问题失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除某个企业下的某个机器人的问题
	 */
	@RequestMapping(value = "/deleteRobotQuestion")
	public void deleteRobotQuestion(
			@RequestParam("id")Integer rid,
			@RequestParam("eid")Integer eid,
			HttpServletRequest request, HttpServletResponse response){
		try {
			chatOnlineBiz.deleteRobotQuestion(eid, rid);
			logger.info("删除机器人问题成功！");
			this.outJson(response,new JSONResult(true, "删除机器人问题成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "删除机器人问题失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询该企业下每个群组的常用语
	 */
	@RequestMapping(value = "/getChatSentences")
	public void getChatSentences(@RequestParam("eid")Integer eid, HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatSentencesByEid(eid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"常用语列表加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：添加常用语
	 */
	@RequestMapping(value = "/addSentences")
	public void addSentences(
			@RequestParam("eid")Integer eid,
			@RequestParam("username")String username,
			@RequestParam("group_id")Integer group_id,
			@RequestParam("content")String content,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> sMap = new HashMap<String, Object>();
		sMap.put("eid", eid);
		sMap.put("username", username);
		sMap.put("group_id", group_id);
		sMap.put("content", content);
		try {
			chatOnlineBiz.addSentences(sMap);
			logger.info("添加常用语成功！");
			this.outJson(response,new JSONResult(true, "添加常用语成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "添加常用语失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改某个企业下的某个机器人的问题
	 */
	@RequestMapping(value = "/updateSentences")
	public void updateSentences(
			@RequestParam("id")Integer id,
			@RequestParam("group_id")Integer group_id,
			@RequestParam("content")String content,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> sMap = new HashMap<String, Object>();
		sMap.put("id", id);
		sMap.put("group_id", group_id);
		sMap.put("content", content);
		try {
			chatOnlineBiz.updateSentences(sMap);
			logger.info("修改常用语成功！");
			this.outJson(response,new JSONResult(true, "修改常用语成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "修改常用语失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除某个企业下的某个机器人的问题
	 */
	@RequestMapping(value = "/deleteSentences")
	public void deleteSentences(
			@RequestParam("id")Integer sid,
			HttpServletRequest request, HttpServletResponse response){
		try {
			chatOnlineBiz.deleteSentences(sid);
			logger.info("删除常用语成功！");
			this.outJson(response,new JSONResult(true, "删除常用语成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "删除常用语失败！", e.getLocalizedMessage()));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	@RequestMapping(value = "/getChatVisitors")
	public void getChatVisitors(@RequestParam("eid")Integer eid, HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatVisitorsByEid(eid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"访客检索列表加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @param: chateid 客服企业ID
	 * @param: visitnick 访客昵称
	 * @param: visittime 访问时间
	 * @description：根据条件查询访客检索的内容
	 */
	@RequestMapping(value = "/searchVisitors")
	public void searchVisitors(
			@RequestParam("chateid")Integer chateid,
			@RequestParam("visitnick")String visitnick,
			@RequestParam("visittime")String visittime,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.searchVisitors(chateid, visitnick, visittime);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"查询访客检索内容失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	@RequestMapping(value = "/getChatNotes")
	public void getChatNotes(@RequestParam("eid")Integer eid, HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatNotesByEid(eid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"留言列表加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @param: chateid 客服企业ID
	 * @param: status 访客留言状态
	 * @param: group_id 所属组
	 * @description：根据条件查询访客留言的内容
	 */
	@RequestMapping(value = "/searchNotes")
	public void searchNotes(
			@RequestParam("chateid")Integer chateid,
			@RequestParam("status")String status,
			@RequestParam("group_id")String group_id,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.searchNotes(chateid, status, group_id);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"查询访客留言内容失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	@RequestMapping(value = "/getChatEvals")
	public void getChatEvals(@RequestParam("eid")Integer eid, HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.getChatEvalsByEid(eid);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"客户评价信息加载失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @param: chateid 客服企业ID
	 * @param: grade 评分等级
	 * @param: group_id 所属组
	 * @description：根据条件查询客户评价内容
	 */
	@RequestMapping(value = "/searchEvals")
	public void searchEvals(
			@RequestParam("chateid")Integer chateid,
			@RequestParam("grade")String grade,
			@RequestParam("group_id")String group_id,
			HttpServletRequest request, HttpServletResponse response){
		try {
			List<?> list = chatOnlineBiz.searchEvals(chateid, grade, group_id);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false,"查询访客留言内容失败！"));
		}
	}
	
	/**
	 * @date: 2013-11-1
	 * @author：lwch
	 * @description：根据用户的ID查询该用户的聊天历史记录
	 */
	@RequestMapping(value = "/getChatHistory")
	public void getChatHistory(
			@RequestParam("eid")Integer eid,
			@RequestParam("username")String username,
			@RequestParam("rows")Integer limit,
			HttpServletRequest request, HttpServletResponse response){
		
		try {
			List<?> list = chatOnlineBiz.getChatHistoryByEidAndUsername(eid, username, limit);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "历史聊天记录加载失败！"));
		}
	}

	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description： 在枢纽平台中查询某个企业下还未分配为客服的所有子帐号
	 */
	@RequestMapping(value = "/getEnterpriseUser")
	public void getEnterpriseUser(
			@RequestParam("eid")Integer eid,
			@RequestParam("chateid")Integer chateid,
			HttpServletRequest request, HttpServletResponse response){
		try {
			String userid = chatOnlineBiz.getUseridsByEid(eid, Constant.CHAT_ROLE_SERVICE);
			List<?> userList = staffBiz.findStaffByPidAndUid(eid, userid);
			this.outJson(response, userList);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "用户数据加载失败"));
		}
	}
	
	/**
	 * @date: 2013-11-8
	 * @author：lwch
	 * @param chateid 在线客服系统中用户所属的企业id
	 * @param username 当前登录的用户名
	 * @param content 要查询的过滤条件
	 * @param sendtime 要查询聊天的日期
	 * @description：
	 */
	@RequestMapping(value = "/searchHistory")
	public void searchHistory(
			@RequestParam("chateid")Integer chateid,
			@RequestParam("username")String username,
			@RequestParam("content")String content,
			@RequestParam("sendtime")String sendtime,
			HttpServletRequest request, HttpServletResponse response){
		
		try {
			List<?> list = chatOnlineBiz.searchHistory(chateid, username, content, sendtime);
			this.outJson(response, list);
		} catch (Exception e) {
			e.printStackTrace();
			this.outJson(response,new JSONResult(false, "查询聊天记录失败！"));
		}
	}
}
