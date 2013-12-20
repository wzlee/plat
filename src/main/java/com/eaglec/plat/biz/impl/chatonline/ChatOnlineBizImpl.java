package com.eaglec.plat.biz.impl.chatonline;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.chatonline.ChatOnlineBiz;
import com.eaglec.plat.dao.chatonline.ChatOnlineDao;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;

@Service
public class ChatOnlineBizImpl implements ChatOnlineBiz {

	@Autowired
	private ChatOnlineDao chatOnlineDao;
	
	/**
	 * @date: 2013-11-9
	 * @author：lwch
	 * @description：企业用户注册的时候，如果注册成功那么就到在线客服系统也注册该企业
	 */
	@Override
	public void addChatEnterpriseRegister(Map<String, Object> eMap){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into `chat_users` (`u_code`, `u_name`, `u_password`, `u_role`, `u_enterprid`, `u_email`, `u_ico`, `created_at`) values (");
		sql.append("'"+ eMap.get("login") +"', ");
		sql.append("'"+ eMap.get("login") +"', ");
		sql.append("'"+ eMap.get("password") +"', ");
		sql.append("'"+ Constant.CHAT_ROLE_ADMIN +"', ");	//管理员用户
		sql.append("'"+ eMap.get("plateid") +"', ");
		sql.append("'"+ eMap.get("email") +"', ");
		sql.append("'"+ Common.defaultUserImg +"', ");
		sql.append("'"+ new Timestamp(new Date().getTime()) +"')");
		chatOnlineDao.addChatAdmin(sql.toString());
		/*int chateid = chatOnlineDao.addChatEnterprise(eMap);
		if (chateid > 0) {
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("eid", chateid);
			groupMap.put("name", Constant.CHAT_DEFAULTGROUP_NAME);
			groupMap.put("pic", Constant.CHAT_DEFAULTGROUP_IMG);
			groupMap.put("desc", Constant.CHAT_DEFAULTGROUP_DESC);
			groupMap.put("g_type", false);
			groupMap.put("rid", 0);
			//添加默认的组
			int groupid = chatOnlineDao.addGroup(groupMap);
			eMap.put("chateid", chateid);
			eMap.put("group_id", groupid);
			//添加企业管理员
			chatOnlineDao.addChatAdmin(eMap);
			
			Map<String, Object> robotMap = new HashMap<String, Object>();
			robotMap.put("eid", chateid);
			robotMap.put("name", Constant.CHAT_DEFAULT_ROBOT_NAME);
			robotMap.put("role", Constant.CHAT_DEFAULT_ROBOT_ROLE);
			robotMap.put("remark", Constant.CHAT_DEFAULT_ROBOT_REMARK);
			//添加默认机器人
			int rid = chatOnlineDao.addRobot(robotMap);
			if (rid > 0) {
				groupMap.put("rid", rid);
				groupMap.put("id", groupid);
				//将默认机器人和默认组关联起来
				chatOnlineDao.updateGroup(groupMap);
			}
		}*/
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据本枢纽平台的ID去获取到在线客服映射的企业ID
	 */
	public int getChatEidByPlatEid(Integer plateid){
		return chatOnlineDao.getChatEidByPlatEid(plateid);
	}
	
	/**
	 * @date: 2013-11-1
	 * @author：lwch
	 * @description：根据企业ID, 用户登录名称查询历史聊天记录
	 */
	@Override
	public List<?> getChatHistoryByEidAndUsername(Integer eid, String username, Integer limit) {
		return chatOnlineDao.getChatHistoryByEidAndUsername(eid, username, limit);
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据企业ID查询该企业下的客服分组
	 */
	@Override
	public List<Map<String, Object>> getChatGroupByEid(Integer eid, String username){
		return chatOnlineDao.getChatGroupByEid(eid, username);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：根据组ID查询该组下用户总数
	 */
	@Override
	public Long getChatCountByGroupid(Integer groupid){
		return chatOnlineDao.getChatCountByGroupid(groupid);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服组
	 */
	@Override
	public int addGroup(Map<String, Object> groupMap){
		return chatOnlineDao.addGroup(groupMap);
	}

	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服组信息
	 */
	@Override
	public void updateGroup(Map<String, Object> groupMap){
		chatOnlineDao.updateGroup(groupMap);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服组
	 */
	@Override
	public void deleteGroup(Integer id){
		chatOnlineDao.deleteGroup(id);
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据用户的角色和所属企业ID查询该企业下的客服信息
	 */
	@Override
	public List<Map<String, Object>> getChatUsersByEidAndRole(Integer eid, String role){
		return chatOnlineDao.getChatUsersByEidAndRole(eid, role);
	}
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：根据企业id和用户登录名获取该客服
	 */
	@Override
	public List<?> getOneChatUser(Integer eid, String username){
		return chatOnlineDao.getOneChatUser(eid, username);
	}
	
	/**
	 * @date: 2013-12-3
	 * @author：lwch
	 * @description：更新用户的在线状态
	 */
	@Override
	public void updateChatUserStatus(Integer uid, Integer line_status){
		chatOnlineDao.updateChatUserStatus(uid, line_status);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服或客服管理员用户
	 */
	public int addUsers(Map<String, Object> userMap){
		return chatOnlineDao.addUsers(userMap);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服信息
	 */
	@Override
	public void updateUsers(Map<String, Object> userMap){
		chatOnlineDao.updateUsers(userMap);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	@Override
	public void deleteUsers(Integer id){
		chatOnlineDao.deleteUsers(id);
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	@Override
	public void deleteAllUsers(Integer eid){
		chatOnlineDao.deleteAllUsers(eid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id获取该企业下所有的机器人列表
	 */
	@Override
	public List<?> getChatRobotsByEid(Integer eid){
		return chatOnlineDao.getChatRobotsByEid(eid);
	}
	
	/**
	 * @date: 2013-11-20
	 * @author：lwch
	 * @description：添加机器人
	 */
	public int addRobot(Map<String, Object> robotMap){
		return chatOnlineDao.addRobot(robotMap);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改机器人
	 */
	public void updateRobot(Map<String, Object> robotMap){
		chatOnlineDao.updateRobot(robotMap);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除机器人
	 */
	public void deleteRobot(Integer rid){
		chatOnlineDao.deleteRobot(rid);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改客服组关联了机器人为rid的，将该客服组关联默认机器人
	 */
	public void modifyGroupToRobot(Integer eid, Integer rid){
		chatOnlineDao.modifyGroupToRobot(eid, rid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id和机器人id去查询该企业下机器人的问题
	 */
	@Override
	public List<?> getChatRobotsQuestion(Integer eid, Integer rid){
		return chatOnlineDao.getChatRobotsQuestion(eid, rid);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：添加某个企业下的某个机器人的问题
	 */
	public int addRobotQuestion(Map<String, Object> qMap){
		return chatOnlineDao.addRobotQuestion(qMap);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改某个企业下的某个机器人的问题
	 */
	public void updateRobotQuestion(Map<String, Object> qMap){
		chatOnlineDao.updateRobotQuestion(qMap);
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除某个企业下的某个机器人的问题
	 */
	public void deleteRobotQuestion(Integer eid, Integer rid){
		chatOnlineDao.deleteRobotQuestion(eid, rid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询该企业下每个群组的常用语
	 */
	@Override
	public List<?> getChatSentencesByEid(Integer eid){
		return chatOnlineDao.getChatSentencesByEid(eid);
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：添加常用语
	 */
	public int addSentences(Map<String, Object> sMap){
		return chatOnlineDao.addSentences(sMap);
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：修改常用语
	 */
	public void updateSentences(Map<String, Object> sMap){
		chatOnlineDao.updateSentences(sMap);
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：删除常用语
	 */
	public void deleteSentences(Integer sid){
		chatOnlineDao.deleteSentences(sid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	@Override
	public List<?> getChatVisitorsByEid(Integer eid){
		return chatOnlineDao.getChatVisitorsByEid(eid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	public List<?> searchVisitors(Integer chateid, String visitnick, String visittime){
		StringBuffer sql = new StringBuffer("select * from `shop_webim_visitors` swv where swv.`eid`='"+ chateid +"'");
		if (!"".equals(visitnick)) {
			sql.append(" and swv.`nick` like '%"+ visitnick +"%' ");
		}
		if (!"".equals(visittime)) {
			sql.append(" and swv.`signat` like '%"+ visittime +"%'");
		}
		return chatOnlineDao.searchVisitors(sql.toString());
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	@Override
	public List<?> getChatNotesByEid(Integer eid){
		return chatOnlineDao.getChatNotesByEid(eid);
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客留言的信息
	 */
	public List<?> searchNotes(Integer chateid, String status, String group_id){
		StringBuffer sql = new StringBuffer();
		sql.append("select swn.`id`, swn.`eid`, swn.`user_id`, swn.`group_id`, swn.`visitor_id`, swn.`name`, swn.`contact`, ");
		sql.append("swn.`content`, swn.`created_at`, swn.`status`, swn.`remark`, swg.`name` groupname, swu.`nick` ");
		sql.append("from `shop_webim_notes` swn, `shop_webim_groups` swg, `shop_webim_users` swu ");
		sql.append("where swn.`eid`='"+ chateid +"' and swn.`group_id`=swg.`id` and swn.`user_id`=swu.`id`");
		if (!"".equals(status)) {
			sql.append(" and swn.`status`='"+ status +"' ");
		}
		if (!"".equals(group_id)) {
			sql.append(" and swn.`group_id`='"+ group_id +"'");
		}
		return chatOnlineDao.searchNotes(sql.toString());
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	public List<?> getChatEvalsByEid(Integer eid){
		return chatOnlineDao.getChatEvalsByEid(eid);
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：根据条件查询客户评价内容
	 */
	public List<?> searchEvals(Integer chateid, String grade, String group_id){
		StringBuffer sql = new StringBuffer();
		sql.append("select swe.`id`, swe.`eid`, swe.`user_id`, swe.`group_id`, swe.`visitor_id`, swe.`grade`, swe.`suggestion`, swe.`created_at`, swg.`name`, swu.`nick` ");
		sql.append("from `shop_webim_evals` swe, `shop_webim_groups` swg, `shop_webim_users` swu ");
		sql.append("where swe.`eid`='"+ chateid +"' and swe.`group_id`=swg.`id` and swe.`user_id`=swu.`id`");
		if (!"".equals(grade)) {
			sql.append(" and swe.`grade`='"+ grade +"' ");
		}
		if (!"".equals(group_id)) {
			sql.append(" and swe.`group_id`='"+ group_id +"'");
		}
		return chatOnlineDao.searchEvals(sql.toString());
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID查询该企业下所有的用户，返回用户的ID格式为"1,2,3,4,5"
	 */
	public String getUseridsByEid(Integer eid, String role){
		return chatOnlineDao.getUseridsByEid(eid, role);
	}

	/**
	 * @date: 2013-11-8
	 * @author：lwch
	 * @param content 要查询的过滤条件
	 * @param sendtime 要查询聊天的日期
	 * @description：
	 */
	@Override
	public List<?> searchHistory(Integer chateid, String username, String content, String sendtime){
		return chatOnlineDao.searchHistory(chateid, username, content, sendtime);
	}
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：如果某个服务还为指派客服组，那么就根据企业id去在线客服系统中去查找默认的客服组ID
	 */
	public Map<String, Object> getChatDefaultGroupIDByEid(Integer eid){
		return chatOnlineDao.getChatDefaultGroupIDByEid(eid);
	}
	
}
