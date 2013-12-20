package com.eaglec.plat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.WindowDao;
import com.eaglec.plat.sync.bean.EnterpriseSyncBean;
import com.eaglec.plat.sync.bean.ServiceSyncBean;
import com.eaglec.plat.sync.bean.UserSyncBean;
import com.eaglec.plat.sync.impl.SaveOrUpdateToWinImpl;
import com.eaglec.plat.utils.Dao;


/**
 * 数据同步对外接口
 * 
 * @author Xiadi
 * @since 2013-11-27
 */
@Controller
@RequestMapping(value = "/sync")
public class SyncController {
	
	@Autowired
	EnterpriseBiz enterpriseBiz;
	@Autowired
	UserBiz userBiz;
	@Autowired
	ServiceBiz serviceBiz;	
	
	private String uniqueKey;
	private String ignoreFields;
	private Map<String, String[]> foreignKeyMap;
	private StringBuffer sql_base;
	private StringBuffer sql_insert;
	private StringBuffer sql_update;
	private StringBuffer syncFields;
	private StringBuffer values;
	private StringBuffer updateString ;
	private String updateKey = "modifyTime";
	private Map<String, String> uuid_foreignkey_map; //map(foreignFieldName, map(uuid, foreignkey))
	private WindowDao windowDao;
	private Dao dao = SyncFactory.getDao();	
	
	@RequestMapping("do")
	public void syncInterface(HttpServletRequest request,HttpServletResponse response){
		try {			
			/**
			 * @param className		类名
			 * @param wid			窗口id
			 * @param uniqueKey		唯一键名
			 * @param uuid			唯一键值
			 * @param isSync		是否同步至其余窗口	
			 */			
			JSONObject json = new JSONObject(JSON.parseObject(UriUtils.decode(request.getQueryString(),"utf-8")));
			String className = json.getString("className");
			String wid = json.getString("wid");
			String uniqueKey = json.getString("uniqueKey");
			String uuid = json.getString("uuid");
			String isSync = json.getString("isSync");
			StringBuffer sql = new StringBuffer("SELECT * FROM " + className + " WHERE " + uniqueKey + " = '" + uuid +"'");
			//初始化数据
			this.findClass(className);
			//根据WID得到windowDAO
			windowDao = SyncFactory.getWindowDao(Integer.parseInt(wid));
			//完成枢纽的插入与修改
			this.buildingSyncBean(windowDao.findFirst(sql.toString()), wid, uniqueKey);
			//完成同步至各个窗口
			if(className.equals("enterprise")){				
				EnterpriseSyncBean sb = new EnterpriseSyncBean(enterpriseBiz.findEnterpriseByUuid(uuid), isSync.equals("true") ? SyncType.OTHER:SyncType.NONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Enterprise>(sb));
			}else if(json.getString("className").equals("user")){
				User user = userBiz.findUserByUuid(uuid);
				UserSyncBean sb = new UserSyncBean(user, isSync.equals("true") ? SyncType.OTHER:SyncType.NONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<User>(sb));
				// plat社区保存
				String tsusersql = "INSERT INTO ts_user(uid,login,uname,email,PASSWORD,openid) VALUES("+
									user.getId()+",'"+user.getEmail()+"','"+user.getUserName()+"','"+
									user.getEmail()+"','"+user.getPassword()+"','"+user.getUid()+"')";
				String tsgroupsql = "INSERT INTO ts_user_group_link (uid,user_group_id) VALUES("+user.getId()+", 10)";
				userBiz.updateSql(tsusersql);
				userBiz.updateSql(tsgroupsql);
			}else if(json.getString("className").equals("service")){
				Service serivce = serviceBiz.findServiceByUuid(uuid);
				ServiceSyncBean sb = new ServiceSyncBean(serivce, isSync.equals("true") ? SyncType.OTHER:SyncType.NONE);
				SyncFactory.executeTask(new SaveOrUpdateToWinImpl<Service>(sb));
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表名初始化数据
	 * @author pangyf
	 * @since 2013-11-28
	 * @param className
	 * @return
	 */
	public void findClass(String className){
		
		this.sql_insert = new StringBuffer("INSERT INTO " + className);
		this.sql_update = new StringBuffer("UPDATE " + className);
		this.sql_base = new StringBuffer("SELECT ");
		this.syncFields = new StringBuffer();
		this.values = new StringBuffer();
		this.updateString = new StringBuffer();
		this.foreignKeyMap = new HashMap<String, String[]>();
		if(className.equals("enterprise")){			
			this.uniqueKey = "eid";
			this.ignoreFields = "id";
		}else if(className.equals("user")){
			this.uniqueKey = "uid";
			this.ignoreFields = "id,enterprise_id";			
			this.foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		}else if(className.equals("service")){
			this.uniqueKey = "sid";
			this.ignoreFields = "id,enterprise_id";			
			this.foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		}else if(className.equals("servicedetail")){
			this.uniqueKey = "sdid";
			this.ignoreFields = "id,serviceId";			
			this.foreignKeyMap.put("serviceId", new String[]{"service", "sid", "id"});
		}else if(className.equals("approveddetail")){
			this.uniqueKey = "adid";
			this.ignoreFields = "id,user_id,manager_id,enterprise_id";			
			this.foreignKeyMap.put("user_id", new String[]{"user", "uid", "id"});
			this.foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		}else if(className.equals("orgregisterapproval")){
			this.uniqueKey = "oraid";
			this.ignoreFields = "id,manager_id";			
		}else if(className.equals("goodsorder")){
			this.uniqueKey = "oid";
			this.ignoreFields = "id,staff_id,biddingService_id,buyer_id,service_id,seller_id,purchaser_id";
			this.foreignKeyMap.put("buyer_id", new String[]{"user","uid","id"});
			this.foreignKeyMap.put("service_id", new String[]{"service","sid","id"});
		}else if(className.equals("orderinfo")){
			this.uniqueKey = "oiid";
			this.ignoreFields = "id,order_id,processor_id,manager_id,staff_id";
			this.foreignKeyMap.put("order_id", new String[]{"goodsorder","oid","id"});
		}else if(className.equals("appeal")){
			this.uniqueKey = "aid";
			this.ignoreFields = "id,staff_id,manager_id,user_id,order_id";
			this.foreignKeyMap.put("order_id", new String[]{"goodsorder","oid","id"});
			this.foreignKeyMap.put("user_id", new String[]{"user","uid","id"});
		}
		this.sql_update.append(" SET ");
		this.sql_base.append(this.uniqueKey).append(",").append(this.updateKey).append(" FROM ").append(className).append(" WHERE ").append(this.uniqueKey).append(" = '");
	}
	
//	/**
//	 * Object转MAP
//	 * @author pangyf
//	 * @since 2013-11-28
//	 */
//    public Map<String, Object> toMap(Object object)  
//    {  
//    	Map<String, Object> result = new HashMap<String, Object>();
//		Method[] methods = object.getClass().getDeclaredMethods();
//		for (Method method : methods) {
//			try {
//				if (method.getName().startsWith("get")) {
//					String field = method.getName();
//					field = field.substring(field.indexOf("get") + 3);
//					field = field.toLowerCase().charAt(0) + field.substring(1);
//
//					Object value = method.invoke(object, (Object[]) null);
//					result.put(field, value);
//				}
//			} catch (Exception e) {
//			}
//		}
//		return result;
//    }
    
	/**
	 * 创建同步（完成枢纽的插入与修改）
	 * @author pangyf
	 * @since 2013-12-3
	 * @param map
	 * @param wid
	 * @param uniqueKey
	 * @throws Exception
	 */
    public void buildingSyncBean(Map<String, Object> map, String wid, String uniqueKey) throws Exception {
    	
    	//处理外键
    	if(this.foreignKeyMap.size() > 0){
			this.uuid_foreignkey_map = new HashMap<String, String>();
			for(String foreignkey : this.foreignKeyMap.keySet()){
				String table = this.foreignKeyMap.get(foreignkey)[0];
				String uuidName = this.foreignKeyMap.get(foreignkey)[1];
				String idName = this.foreignKeyMap.get(foreignkey)[2];				
				String sql = "SELECT " + idName + ", " + uuidName + " FROM " + table + " WHERE " + idName + " = '" + map.get(foreignkey) + "'";				
				String uuid = windowDao.findFirst(sql).get(uuidName).toString();
				sql = "SELECT " + idName + ", " + uuidName + " FROM " + table + " WHERE " + uuidName + " = '" + uuid + "'";							
				this.uuid_foreignkey_map.put(foreignkey, SyncFactory.getDao().findFirst(sql).get(idName).toString());
			}
		}    	
    	String sqlStr = this.sql_base.append(map.get(uniqueKey)).append("'").toString();
    	List<Map<String, Object>>  list = SyncFactory.getDao().find(sqlStr);    	
    	String sql_execute = null;
    	//插入
    	if(list.isEmpty()){    		
    		for(Object keyName : map.keySet()){    			
    			Object value = map.get(keyName);
    			//如果不是忽略的KEY
        		if(!ignoreFields.contains(keyName.toString())){    			
        			if(syncFields.length() != 0){
        				syncFields.append(",");
        				values.append(",");
        			}
        			// if value is String
					if (value != null && value.getClass().getSimpleName().equals("String")) {
						value = "'" + value.toString().replaceAll("\'", "") + "'";
					}
        			syncFields.append(keyName);    			
        			values.append(value);        			
        		}
        		if(foreignKeyMap.keySet().contains(keyName.toString())){
        			if(syncFields.length() != 0){
        				syncFields.append(",");
        				values.append(",");
        			}
        			syncFields.append(keyName);    			
        			values.append(uuid_foreignkey_map.get(keyName));
        		}
        	}
    		this.sql_insert.append(" (").append(syncFields).append(") VALUES (").append(values).append(")");
    		sql_execute = this.sql_insert.toString();
    	//修改
    	}else {
    		//Map<String, Object> platMap = new HashMap<String, Object>(list.get(0));
    		for(Object keyName : map.keySet()){
    			Object value = map.get(keyName);
        		if(!ignoreFields.contains(keyName.toString())){
        			if(updateString.length() != 0){
        				updateString.append(",");
        			}
        			// if value is String
					if (value != null && value.getClass().getSimpleName().equals("String")) {
						value = "'" + value.toString().replaceAll("\'", "") + "'";
					}
        			updateString.append(keyName).append(" = ").append(value);
        		}
        	}
    		this.sql_update.append(updateString).append(" WHERE ").append(uniqueKey).append(" = '").append(map.get(uniqueKey)).append("'");
    		sql_execute = this.sql_update.toString();
    	}
    	dao.update(sql_execute);
    	System.out.println("执行成功");
	}
    
}
