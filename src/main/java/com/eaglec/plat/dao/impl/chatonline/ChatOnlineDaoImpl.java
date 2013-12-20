package com.eaglec.plat.dao.impl.chatonline;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.chatonline.ChatOnlineDao;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.DBUtils;
import com.eaglec.plat.utils.RandomUtils;
import com.mysql.jdbc.Connection;

@Repository
public class ChatOnlineDaoImpl implements ChatOnlineDao {
	
	/**
	 * @date: 2013-11-9
	 * @author：lwch
	 * @description：企业用户注册的时候，如果注册成功那么就到在线客服系统也注册该企业
	 */
	@Override
	public int addChatEnterprise(Map<String, Object> eMap){
		String sql = "insert into `shop_webim_tenants` (`name`, `apikey`, `status`, `company`, `contact`, `signat`, `plateid`) values (?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, "www.eaglec.com");
			ps.setString(2, RandomUtils.generateMixString(13));
			ps.setInt(3, 1);
			ps.setString(4, (String)eMap.get("company"));
			ps.setString(5, (String)eMap.get("email"));
			ps.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
			ps.setInt(7, (Integer)eMap.get("plateid"));
			ps.executeUpdate();
			int chateid = 0;
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			chateid = rs.getInt(1);
			DBUtils.closeConnection(conn);
			return chateid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-9
	 * @author：lwch
	 * @description：注册企业注册成功后，马上给该企业添加一个客服管理员
	 */
	@Override
	public void addChatAdmin(String sql){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据本枢纽平台的ID去获取到在线客服映射的企业ID
	 */
	@Override
	public int getChatEidByPlatEid(Integer plateid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select id from shop_webim_tenants where plateid=?");
			ps.setInt(1, plateid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			int chateid = 0;
			while (rs.next()) {
				chateid = rs.getInt("id");
			}
			return chateid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据企业ID查询该企业下的客服分组
	 */
	@Override
	public List<Map<String, Object>> getChatGroupByEid(Integer eid, String username){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from `chat_customgroup` g where g.uid in (select `uid` from `chat_users` where `u_enterprid`=? and `u_code`=? and `u_role`='admin')");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ps.setString(2, username);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("uid", rs.getString("uid"));
				resMap.put("groupName", rs.getString("groupName"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：根据组ID查询该组下用户总数
	 */
	@Override
	public Long getChatCountByGroupid(Integer groupid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select COUNT(*) num from shop_webim_users swu where swu.group_id=?");
			ps.setInt(1, groupid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			Long i = null;
			while (rs.next()) {
				i = (long) rs.getInt("num");
			}
			return i;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服组
	 */
	@Override
	public int addGroup(Map<String, Object> groupMap){
		String sql = "insert into shop_webim_groups (`eid`,`name`,`pic`,`g_type`,`desc`,`rid`) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, (Integer)groupMap.get("eid"));
			ps.setString(2, (String)groupMap.get("name"));
			ps.setString(3, (String)groupMap.get("pic"));
			ps.setBoolean(4, (Boolean) groupMap.get("g_type"));
			ps.setString(5, (String)groupMap.get("desc"));
			ps.setInt(6, (Integer)groupMap.get("rid"));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int groupid = 0;
			groupid = rs.getInt(1);
			DBUtils.closeConnection(conn);
			return groupid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服组信息
	 */
	@Override
	public void updateGroup(Map<String, Object> groupMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update shop_webim_groups swg set swg.name=?, swg.pic=?, swg.desc=?, rid=? where swg.id=?");
			ps.setString(1, (String)groupMap.get("name"));
			ps.setString(2, (String)groupMap.get("pic"));
			ps.setString(3, (String)groupMap.get("desc"));
			ps.setInt(4, (Integer)groupMap.get("rid"));
			ps.setInt(5, (Integer)groupMap.get("id"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服组
	 */
	@Override
	public void deleteGroup(Integer id){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from shop_webim_groups where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：根据用户的角色和所属企业ID查询该企业下的客服信息
	 */
	@Override
	public List<Map<String, Object>> getChatUsersByEidAndRole(Integer eid, String role){
		StringBuffer sql = new StringBuffer();
		sql.append("select `uid`, `u_code`, `u_name`, `u_enterprid`, `u_role`, `u_email` from `chat_users` where `u_enterprid`=? and `u_role`=?");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ps.setString(2, role);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("uid"));
				resMap.put("username", rs.getString("u_code"));
				resMap.put("staffname", rs.getString("u_name"));
				resMap.put("eid", rs.getString("u_enterprid"));
				resMap.put("role", rs.getString("u_role"));
				resMap.put("email", rs.getString("u_email"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：根据企业id和用户登录名获取该客服
	 */
	@Override
	public List<?> getOneChatUser(Integer eid, String username){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from `chat_users` where `u_code`=? and `u_enterprid`=?");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, username);
			ps.setInt(2, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("uid", rs.getInt("uid"));
				resMap.put("u_code", rs.getString("u_code"));
				resMap.put("u_password", rs.getString("u_password"));
				resMap.put("u_name", rs.getString("u_name"));
				resMap.put("u_enterprid", rs.getInt("u_enterprid"));
				resMap.put("u_role", rs.getString("u_role"));
				resMap.put("u_email", rs.getString("u_email"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resMap.put("u_intro", rs.getString("u_intro"));
				resMap.put("u_ico", rs.getString("u_ico"));
				resMap.put("line_status", rs.getInt("line_status"));
				resMap.put("msg_wav", rs.getBoolean("msg_wav"));
				resMap.put("last_time", rs.getInt("last_time"));
				resMap.put("im_peerid", rs.getString("im_peerid"));
				resMap.put("online_time", rs.getInt("online_time"));
				resMap.put("acceptStrange", rs.getBoolean("acceptStrange"));
				resMap.put("addFriends", rs.getInt("addFriends"));
				resMap.put("question", rs.getString("question"));
				resMap.put("answer", rs.getString("answer"));
				resMap.put("contacted", rs.getString("contacted"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-2
	 * @author：lwch
	 * @description：根据用户的角色和所属企业ID查询该企业下的客服信息
	 */
	public List<?> getChatUsersByEidAndRole_bak(Integer eid, String role){
		StringBuffer sql = new StringBuffer();
		sql.append("select swu.`id`, swu.`eid`, swu.`login`, swu.`nick`, swu.`email`, swu.`group_id`, swu.`role`, swu.`created_at`, swg.`name` ");
		sql.append("from `shop_webim_users` swu, `shop_webim_groups` swg ");
		sql.append("where swu.`eid`=? and swu.`group_id`=swg.`id`");
		if (!role.equals("admin")) {
			sql.append("  and swu.role=?");
		}
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			if(!role.equals("admin")){
				ps.setString(2, role);
			}
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("login", rs.getString("login"));
				resMap.put("nick", rs.getString("nick"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("group", rs.getString("name"));
				resMap.put("role", rs.getString("role"));
				resMap.put("email", rs.getString("email"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-12-3
	 * @author：lwch
	 * @description：更新用户的在线状态
	 */
	@Override
	public void updateChatUserStatus(Integer uid, Integer line_status){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update `chat_users` set `line_status`=? where `uid`=?");
			ps.setInt(1, line_status);
			ps.setInt(2, uid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服或客服管理员用户
	 */
	@Override
	public int addUsers(Map<String, Object> userMap){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into `chat_users` (`u_code`, `u_name`, `u_password`, `u_role`, `u_enterprid`, `u_email`, `u_ico`, `created_at`) values (");
		sql.append("'"+ userMap.get("username") +"', ");
		sql.append("'"+ userMap.get("staffname") +"', ");
		sql.append("'"+ userMap.get("password") +"', ");
		sql.append("'"+ userMap.get("role") +"', ");
		sql.append("'"+ userMap.get("eid") +"', ");
		sql.append("'"+ userMap.get("email") +"', ");
		sql.append("'"+ Common.defaultUserImg +"', ");
		sql.append("'"+ new Timestamp(new Date().getTime()) +"')");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int i = ps.executeUpdate();
			DBUtils.closeConnection(conn);
			return i;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：给某个企业添加客服或客服管理员用户
	 */
	public int addUsers_bak(Map<String, Object> userMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into shop_webim_users (`eid`, `login`, `password`, `nick`, `email`, `group_id`, `role`, `created_at`) values (?,?,?,?,?,?,?,?)");
			ps.setInt(1, (Integer)userMap.get("eid"));
			ps.setString(2, (String)userMap.get("login"));
			ps.setString(3, (String)userMap.get("password"));
			ps.setString(4, (String)userMap.get("nick"));
			ps.setString(5, (String)userMap.get("email"));
			ps.setInt(6, (Integer)userMap.get("group_id"));
			ps.setString(7, (String)userMap.get("role"));
			ps.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));
			int i = ps.executeUpdate();
			DBUtils.closeConnection(conn);
			return i;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-12-3
	 * @author：lwch
	 * @description：修改某个企业的客服信息
	 */
	@Override
	public void updateUsers(Map<String, Object> userMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update `chat_users` set `u_name`=?, `u_role`=? where `u_code`=? and `u_enterprid`=?");
			ps.setString(1, (String)userMap.get("staffname"));
			ps.setString(2, (String)userMap.get("role"));
			ps.setString(3, (String)userMap.get("username"));
			ps.setInt(4, (Integer)userMap.get("eid"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：修改某个企业的客服信息
	 */
	public void updateUsers_bak(Map<String, Object> userMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update shop_webim_users swu set swu.nick=?, swu.email=?, swu.group_id=?, swu.role=? where id=?");
			ps.setString(1, (String)userMap.get("nick"));
			ps.setString(2, (String)userMap.get("email"));
			ps.setInt(3, (Integer)userMap.get("group_id"));
			ps.setString(4, (String)userMap.get("role"));
			ps.setInt(5, (Integer)userMap.get("id"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	@Override
	public void deleteUsers(Integer id){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from `chat_users` where `uid`=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：删除某个企业的客服信息
	 */
	public void deleteUsers_bak(Integer id){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from shop_webim_users where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-12-2
	 * @author：lwch
	 * @description：删除某个所有在线客服用户
	 */
	public void deleteAllUsers(Integer eid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from `chat_users` where `u_enterprid`=?");
			ps.setInt(1, eid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id获取该企业下所有的机器人列表
	 */
	@Override
	public List<?> getChatRobotsByEid(Integer eid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from `shop_webim_robots` where `eid`=? order by id");
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("name", rs.getString("name"));
				resMap.put("status", rs.getString("status"));
				resMap.put("role", rs.getString("role"));
				resMap.put("remark", rs.getString("remark"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-20
	 * @author：lwch
	 * @description：添加机器人
	 */
	public int addRobot(Map<String, Object> robotMap){
		String sql = "insert into `shop_webim_robots` (`eid`,`name`,`role`,`remark`) values (?,?,?,?)";
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, (Integer)robotMap.get("eid"));
			ps.setString(2, (String)robotMap.get("name"));
			ps.setString(3, (String)robotMap.get("role"));
			ps.setString(4, (String) robotMap.get("remark"));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int groupid = 0;
			groupid = rs.getInt(1);
			DBUtils.closeConnection(conn);
			return groupid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改机器人
	 */
	public void updateRobot(Map<String, Object> robotMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update `shop_webim_robots` swr set swr.`name`=?, swr.`remark`=? where swr.`id`=?");
			ps.setString(1, (String)robotMap.get("name"));
			ps.setString(2, (String)robotMap.get("remark"));
			ps.setInt(3, (Integer)robotMap.get("id"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除机器人
	 */
	public void deleteRobot(Integer rid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from `shop_webim_robots` where id=?");
			ps.setInt(1, rid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改客服组关联了机器人为rid的，将该客服组关联默认机器人
	 */
	public void modifyGroupToRobot(Integer eid, Integer rid){
		StringBuffer sql = new StringBuffer();
		sql.append("update `shop_webim_groups` swg2, (select swg1.`rid` from `shop_webim_groups` swg1 where swg1.`eid`=? and swg1.`g_type`=0) swg3 ");
		sql.append("set swg2.`rid`=swg3.`rid` where swg2.`eid`=? and swg2.`rid`=?");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ps.setInt(2, eid);
			ps.setInt(3, rid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业id和机器人id去查询该企业下机器人的问题
	 */
	public List<?> getChatRobotsQuestion(Integer eid, Integer rid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from `shop_webim_questions` where `eid`=? and `rid`=?");
			ps.setInt(1, eid);
			ps.setInt(2, rid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("rid", rs.getInt("rid"));
				resMap.put("qid", rs.getString("qid"));
				resMap.put("question", rs.getString("question"));
				resMap.put("answer", rs.getString("answer"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：添加某个企业下的某个机器人的问题
	 */
	public int addRobotQuestion(Map<String, Object> qMap){
		String sql = "insert into `shop_webim_questions` (`eid`,`rid`,`qid`,`question`,`answer`,`created_at`) values (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, (Integer)qMap.get("eid"));
			ps.setInt(2, (Integer)qMap.get("rid"));
			ps.setInt(3, (Integer)qMap.get("qid"));
			ps.setString(4, (String)qMap.get("question"));
			ps.setString(5, (String)qMap.get("answer"));
			ps.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int groupid = 0;
			groupid = rs.getInt(1);
			DBUtils.closeConnection(conn);
			return groupid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：修改某个企业下的某个机器人的问题
	 */
	public void updateRobotQuestion(Map<String, Object> qMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update `shop_webim_questions` swq set swq.`question`=?, swq.`answer`=? where swq.`id`=? and eid=?");
			ps.setString(1, (String)qMap.get("question"));
			ps.setString(2, (String)qMap.get("answer"));
			ps.setInt(3, (Integer)qMap.get("id"));
			ps.setInt(4, (Integer)qMap.get("eid"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-21
	 * @author：lwch
	 * @description：删除某个企业下的某个机器人的问题
	 */
	public void deleteRobotQuestion(Integer eid, Integer rid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from `shop_webim_questions` where id=? and eid=?");
			ps.setInt(1, rid);
			ps.setInt(2, eid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询该企业下每个群组的常用语
	 */
	@Override
	public List<?> getChatSentencesByEid(Integer eid){
		StringBuffer sql = new StringBuffer();
		sql.append("select sws.`id`, sws.`eid`, sws.`user_id`, sws.`group_id`, sws.`content`, swg.`name`, swu.`nick` ");
		sql.append("from `shop_webim_sentences` sws, `shop_webim_groups` swg, `shop_webim_users` swu ");
		sql.append("where sws.`eid`=? and sws.`group_id`=swg.`id` and sws.`user_id`=swu.`id`");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("eid", rs.getInt("eid"));
				resMap.put("user_id", rs.getString("user_id"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("user", rs.getString("nick"));
				resMap.put("group", rs.getString("name"));
				resMap.put("content", rs.getString("content"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：添加常用语
	 */
	public int addSentences(Map<String, Object> sMap){
		StringBuffer sql = new StringBuffer();
		sql.append("insert into `shop_webim_sentences` (`eid`, `user_id`, `group_id`, `content`) values ");
		sql.append("(?, (select swu.`id` from `shop_webim_users` swu where swu.`eid`=? and swu.`login`=?), ?, ?)");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, (Integer)sMap.get("eid"));
			ps.setInt(2, (Integer)sMap.get("eid"));
			ps.setString(3, (String)sMap.get("username"));
			ps.setInt(4, (Integer)sMap.get("group_id"));
			ps.setString(5, (String)sMap.get("content"));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int groupid = 0;
			groupid = rs.getInt(1);
			DBUtils.closeConnection(conn);
			return groupid;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：修改常用语
	 */
	public void updateSentences(Map<String, Object> sMap){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("update `shop_webim_sentences` sws set sws.`group_id`=?, sws.`content`=? where sws.`id`=?");
			ps.setInt(1, (Integer)sMap.get("group_id"));
			ps.setString(2, (String)sMap.get("content"));
			ps.setInt(3, (Integer)sMap.get("id"));
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：删除常用语
	 */
	public void deleteSentences(Integer sid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from `shop_webim_sentences` where id=?");
			ps.setInt(1, sid);
			ps.executeUpdate();
			DBUtils.closeConnection(conn);
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：根据企业ID去查询访客检索的信息
	 */
	public List<?> getChatVisitorsByEid(Integer eid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from `shop_webim_visitors` where `eid`=?");
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("name", rs.getString("name"));
				resMap.put("nick", rs.getString("nick"));
				resMap.put("ipaddr", rs.getString("ipaddr"));
				resMap.put("referer", rs.getString("referer"));
				resMap.put("url", rs.getString("url"));
				resMap.put("location", rs.getString("location"));
				resMap.put("signat", rs.getTimestamp("signat"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：根据条件查询访客检索的内容
	 */
	public List<?> searchVisitors(String sql){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("name", rs.getString("name"));
				resMap.put("nick", rs.getString("nick"));
				resMap.put("ipaddr", rs.getString("ipaddr"));
				resMap.put("referer", rs.getString("referer"));
				resMap.put("url", rs.getString("url"));
				resMap.put("location", rs.getString("location"));
				resMap.put("signat", rs.getTimestamp("signat"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下留言信息
	 */
	@Override
	public List<?> getChatNotesByEid(Integer eid){
		StringBuffer sql = new StringBuffer();
		sql.append("select swn.`id`, swn.`eid`, swn.`user_id`, swn.`group_id`, swn.`visitor_id`, swn.`name`, swn.`contact`, ");
		sql.append("swn.`content`, swn.`created_at`, swn.`status`, swn.`remark`, swg.`name` groupname, swu.`nick` ");
		sql.append("from `shop_webim_notes` swn, `shop_webim_groups` swg, `shop_webim_users` swu ");
		sql.append("where swn.`eid`=? and swn.`group_id`=swg.`id` and swn.`user_id`=swu.`id`");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("eid", rs.getInt("eid"));
				resMap.put("user_id", rs.getString("user_id"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("visitor", rs.getString("visitor_id"));
				resMap.put("group", rs.getString("groupname"));
				resMap.put("user", rs.getString("nick"));
				resMap.put("name", rs.getString("name"));
				resMap.put("contact", rs.getString("contact"));
				resMap.put("content", rs.getString("content"));
				resMap.put("status", rs.getString("status"));
				resMap.put("remark", rs.getString("remark"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：根据条件查询访客检索的内容
	 */
	public List<?> searchNotes(String sql){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("eid", rs.getInt("eid"));
				resMap.put("user_id", rs.getString("user_id"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("visitor", rs.getString("visitor_id"));
				resMap.put("group", rs.getString("groupname"));
				resMap.put("user", rs.getString("nick"));
				resMap.put("name", rs.getString("name"));
				resMap.put("contact", rs.getString("contact"));
				resMap.put("content", rs.getString("content"));
				resMap.put("status", rs.getString("status"));
				resMap.put("remark", rs.getString("remark"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-19
	 * @author：lwch
	 * @description：获取某个企业下客服评价信息
	 */
	public List<?> getChatEvalsByEid(Integer eid){
		StringBuffer sql = new StringBuffer();
		sql.append("select swe.`id`, swe.`eid`, swe.`user_id`, swe.`group_id`, swe.`visitor_id`, swe.`grade`, swe.`suggestion`, swe.`created_at`, swg.`name`, swu.`nick` ");
		sql.append("from `shop_webim_evals` swe, `shop_webim_groups` swg, `shop_webim_users` swu ");
		sql.append("where swe.`eid`=? and swe.`group_id`=swg.`id` and swe.`user_id`=swu.`id`");
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("eid", rs.getInt("eid"));
				resMap.put("grade", rs.getString("grade"));
				resMap.put("suggestion", rs.getString("suggestion"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("user_id", rs.getString("user_id"));
				resMap.put("group", rs.getString("name"));
				resMap.put("user", rs.getString("nick"));
				resMap.put("visitor", rs.getString("visitor_id"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-22
	 * @author：lwch
	 * @description：根据条件查询客户评价内容
	 */
	public List<?> searchEvals(String sql){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("eid", rs.getInt("eid"));
				resMap.put("grade", rs.getString("grade"));
				resMap.put("suggestion", rs.getString("suggestion"));
				resMap.put("group_id", rs.getString("group_id"));
				resMap.put("user_id", rs.getString("user_id"));
				resMap.put("group", rs.getString("name"));
				resMap.put("user", rs.getString("nick"));
				resMap.put("visitor", rs.getString("visitor_id"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @date: 2013-11-1
	 * @author：lwch
	 * @description：根据企业ID, 用户登录名称查询历史聊天记录
	 */
	@Override
	public List<?> getChatHistoryByEidAndUsername(Integer eid, String username, Integer limit) {
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from shop_webim_histories seh where tid=? and (seh.to = ? or seh.from = ?)");
			ps.setInt(1, eid);
			ps.setString(2, username);
			ps.setString(3, username);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("to", rs.getString("to"));
				resMap.put("from", rs.getString("from"));
				resMap.put("body", rs.getString("body"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID查询该企业下所有的用户，返回用户的ID格式为"1,2,3,4,5"
	 */
	@Override
	public String getUseridsByEid(Integer eid, String role){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select u_code from `chat_users` where u_enterprid=? and `u_role`=?");
			ps.setInt(1, eid);
			ps.setString(2, role);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			StringBuffer login = new StringBuffer();
			while (rs.next()) {
				login.append("'" +rs.getString("u_code") + "',");
			}
			String username = "";
			if (!"".equals(login.toString())) {
				username = login.substring(0, login.length() - 1);
			}
			return username;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @date: 2013-11-5
	 * @author：lwch
	 * @description：根据企业ID查询该企业下所有的用户，返回用户的ID格式为"1,2,3,4,5"
	 */
	public String getUseridsByEid_bak(Integer eid){
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement("select login from shop_webim_users where eid=?");
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			StringBuffer login = new StringBuffer();
			while (rs.next()) {
				login.append("'" +rs.getString("login") + "',");
			}
			String username = "";
			if (!"".equals(login.toString())) {
				username = login.substring(0, login.length() - 1);
			}
			return username;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return "";
		}
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
		String sql = "select * from shop_webim_histories seh where seh.tid='"+ chateid +"' ";
		if (!"".equals(content)) {
			sql += " and (seh.to like '%"+ content +"%' or seh.from like '%"+ content +"%' or seh.body like '%"+ content +"%') ";
		}
		if(!"".equals(sendtime)){
			sql += " and seh.created_at like '"+ sendtime +"%' ";
		}
		if ("".equals(content) && "".equals(sendtime)) {
			sql += " and (seh.to='"+ username +"' or seh.from='"+ username +"')";
		}
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> resMap = new HashMap<String, Object>();
				resMap.put("id", rs.getInt("id"));
				resMap.put("to", rs.getString("to"));
				resMap.put("from", rs.getString("from"));
				resMap.put("body", rs.getString("body"));
				resMap.put("created_at", rs.getTimestamp("created_at"));
				resList.add(resMap);
			}
			return resList;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @date: 2013-11-11
	 * @author：lwch
	 * @description：如果某个服务还未指派客服组，那么就根据企业id去在线客服系统中去查找默认的客服组ID
	 */
	public Map<String, Object> getChatDefaultGroupIDByEid(Integer eid){
		String sql = "select swg.id, swg.eid from shop_webim_groups swg where swg.g_type=0 and swg.eid in (select swt.id from shop_webim_tenants swt where swt.plateid=?)";
		Map<String, Object> egMap = new HashMap<String, Object>();
		Connection conn = null;
		try {
			conn = (Connection) DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			DBUtils.closeConnection(conn);
			while (rs.next()) {
				egMap.put("groupid", rs.getInt("id"));
				egMap.put("eid", rs.getInt("eid"));
			}
			return egMap;
		} catch (SQLException e) {
			DBUtils.closeConnection(conn);
			e.printStackTrace();
			return null;
		}
	}
	
}



