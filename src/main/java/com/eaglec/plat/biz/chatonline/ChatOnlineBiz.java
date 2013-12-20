package com.eaglec.plat.biz.chatonline;

import java.util.List;
import java.util.Map;

public interface ChatOnlineBiz {
	
	/**
	 * @date: 2013-11-9
	 * @author：lwch
	 * @description：企业用户注册的时候，如果注册成功那么就到在线客服系统也注册该企业
	 */
	public void addChatEnterpriseRegister(Map<String, Object> eMap);
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据本枢纽平台的ID去获取到在线客服映射的企业ID
	 */
	public int getChatEidByPlatEid(Integer plateid);

	/**
	 * @date: 2013-11-1
	 * @author：lwch
	 * @description：根据企业ID, 用户登录名称查询历史聊天记录
	 */
	public List<?> getChatHistoryByEidAndUsername(Integer eid, String username, Integer limit);
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据企业ID查询该企业下的客服分组
	 */
	public List<Map<String, Object>> getChatGroupByEid(Integer eid, String username);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：根据组ID查询该组下用户总数
	 */
	public Long getChatCountByGroupid(Integer groupid);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服组
	 */
	public int addGroup(Map<String, Object> groupMap);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服组信息
	 */
	public void updateGroup(Map<String, Object> groupMap);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服组
	 */
	public void deleteGroup(Integer id);
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据用户的角色和所属企业ID查询该企业下的客服信息
	 */
	public List<Map<String, Object>> getChatUsersByEidAndRole(Integer eid, String role);
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：根据企业id和用户登录名获取该客服
	 */
	public List<?> getOneChatUser(Integer eid, String username);
	
	/**
	 * @date: 2013-12-3
	 * @author：lwch
	 * @description：更新用户的在线状态
	 */
	public void updateChatUserStatus(Integer uid, Integer line_status);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服或客服管理员用户
	 */
	public int addUsers(Map<String, Object> userMap);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服信息
	 */
	public void updateUsers(Map<String, Object> userMap);
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	public void deleteUsers(Integer id);
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：删除某个所有在线客服用户
	 */
	public void deleteAllUsers(Integer eid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id获取该企业下所有的机器人列表
	 */
	public List<?> getChatRobotsByEid(Integer eid);
	
	/**
	 * @date: 2013-11-20
	 * @author：lwch
	 * @description：添加机器人
	 */
	public int addRobot(Map<String, Object> robotMap);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改机器人
	 */
	public void updateRobot(Map<String, Object> robotMap);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除机器人
	 */
	public void deleteRobot(Integer rid);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改客服组关联了机器人为rid的，将该客服组关联默认机器人
	 */
	public void modifyGroupToRobot(Integer eid, Integer rid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id和机器人id去查询该企业下机器人的问题
	 */
	public List<?> getChatRobotsQuestion(Integer eid, Integer rid);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：添加某个企业下的某个机器人的问题
	 */
	public int addRobotQuestion(Map<String, Object> qMap);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改某个企业下的某个机器人的问题
	 */
	public void updateRobotQuestion(Map<String, Object> qMap);
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除某个企业下的某个机器人的问题
	 */
	public void deleteRobotQuestion(Integer eid, Integer rid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询该企业下每个群组的常用语
	 */
	public List<?> getChatSentencesByEid(Integer eid);
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：添加常用语
	 */
	public int addSentences(Map<String, Object> sMap);
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：修改常用语
	 */
	public void updateSentences(Map<String, Object> sMap);
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：删除常用语
	 */
	public void deleteSentences(Integer sid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	public List<?> getChatVisitorsByEid(Integer eid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	public List<?> searchVisitors(Integer chateid, String visitnick, String visittime);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	public List<?> getChatNotesByEid(Integer eid);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客留言的信息
	 */
	public List<?> searchNotes(Integer chateid, String status, String group_id);
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	public List<?> getChatEvalsByEid(Integer eid);
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：根据条件查询客户评价内容
	 */
	public List<?> searchEvals(Integer chateid, String grade, String group_id);
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID查询该企业下所有的用户，返回用户的ID格式为"1,2,3,4,5"
	 */
	public String getUseridsByEid(Integer eid, String role);
	
	/**
	 * @date: 2013-11-8
	 * @author：lwch
	 * @param content 要查询的过滤条件
	 * @param sendtime 要查询聊天的日期
	 * @description：
	 */
	public List<?> searchHistory(Integer chateid, String username, String content, String sendtime);
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：如果某个服务还为指派客服组，那么就根据企业id去在线客服系统中去查找默认的客服组ID
	 */
	public Map<String, Object> getChatDefaultGroupIDByEid(Integer eid);
	
	
}
