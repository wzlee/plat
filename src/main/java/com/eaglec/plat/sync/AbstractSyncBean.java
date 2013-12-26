package com.eaglec.plat.sync;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.domain.base.SyncException;
import com.eaglec.plat.utils.StrUtils;

/**
 * 抽象同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 * @param <T>
 */
public abstract class AbstractSyncBean<T> {
	protected T 		syncObj;
	protected SyncType 	syncType;
	
	// need init
	protected WindowDao 	windowdao;	
	protected Integer 		wid;
	protected String		ignoreFields;
	protected String		syncFields;
	protected String 		tableName;
	protected String 		uniqueKeyName;
	protected String 		uniqueKeyValue;
	protected Map<String, Object> platRecord;
	protected Map<String, Object> winRecord;
	protected Map<String, String[]> foreignKeyMap;	//map(foreignFieldName, {foreignTableName, uuidName, idName})
	protected String selectSql;						
	
	protected Map<String, Object> win_foreignkey_value;	//map(foreignFieldName, foreignValue)
	
	protected AbstractSyncBean(T syncOjb, SyncType syncType){
		this.syncObj = syncOjb;
		this.syncType = syncType;
	}
	
	protected abstract void buildingBean(T syncObj) throws Exception ;
	
	public boolean init() {
		try{
			if(syncObj == null){
				throw new Exception("Nothing to Synchronizing, syncObj is null");
			}
			tableName = syncObj.getClass().getSimpleName().toLowerCase();
			
			this.buildingBean(syncObj);
			
			if (this.syncType == SyncType.NONE
					|| platRecord == null) {
				return false;
			}
			
			Set<String> allFields = new HashSet<String>(platRecord.keySet());
			// remove the field of ignoreFields
			for(String field : ignoreFields.split(",")){
				allFields.remove(field); 
			}
			syncFields = StrUtils.join(allFields.toArray(new String[allFields.size()]), ",");
			return true;
		} catch (Exception e) {
			// 记录异动表
			this.addSyncException(new SyncException("初始化同步", wid, syncObj.getClass().getSimpleName(), 
					e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
			return false;
		}
	}
	
	/**
	 * 同步至某个窗口
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	public void saveOrUpdateToOne(){
		if (windowdao == null) {
			return;
		}
		
		try {
			this.reloadWinForeignKeyValue(windowdao);
			winRecord = windowdao.findFirst(selectSql);
		} catch (SQLException e) {
			this.addSyncException(new SyncException("连接窗口数据库", wid, syncObj.getClass().getSimpleName(), 
					e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
			return;
		}
		
		if (winRecord == null) {
			try {
				windowdao.update(getInsertSql());
			} catch (SQLException e) {
				// 记录异动表
				this.addSyncException(new SyncException("同步添加", wid, syncObj.getClass().getSimpleName(),
						e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
			}
		} else {
			try {
				windowdao.update(getUpdateSql());
			} catch (SQLException e) {
				// 记录异动表
				this.addSyncException(new SyncException("同步更新", wid, syncObj.getClass().getSimpleName(),
						e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
			}
		}
	}
	
	/**
	 * 同步至其余窗口
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	public void saveOrUpdateToOther() {
		for (Integer id : SyncFactory.getDaoMapKeySet()) {
			if (id == wid) {
				continue;
			}
			WindowDao wdao = SyncFactory.getWindowDao(id);
			if (wdao == null) {
				continue;
			}
			
			try {
				this.reloadWinForeignKeyValue(wdao);
				winRecord = wdao.findFirst(selectSql);
			} catch (SQLException e) {
				this.addSyncException(new SyncException("连接窗口数据库", id, syncObj.getClass().getSimpleName(), 
						e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				continue;
			}
			
			if (winRecord == null) {
				try {
					wdao.update(getInsertSql());
				} catch (SQLException e) {
					// 记录异动表
					this.addSyncException(new SyncException("其余同步添加", id, syncObj.getClass().getSimpleName(),
							e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				}
			} else {
				try{
					wdao.update(getUpdateSql());
				} catch (SQLException e) {
					// 记录异动表
					this.addSyncException(new SyncException("其余同步更新", id, syncObj.getClass().getSimpleName(),
							e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				}
			}
		}
	}
	
	/**
	 * 同步至所有窗口
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	public void saveOrUpdateToAll() {
		for (Integer id : SyncFactory.getDaoMapKeySet()) {
			WindowDao wdao = SyncFactory.getWindowDao(id);
			if (wdao == null) {
				continue;
			}
			
			try {
				this.reloadWinForeignKeyValue(wdao);
				winRecord = wdao.findFirst(selectSql);
			} catch (SQLException e) {
				this.addSyncException(new SyncException("连接窗口数据库", id, syncObj.getClass().getSimpleName(), 
						e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				continue;
			}
			
			if (winRecord == null) {
				try {
					wdao.update(getInsertSql());
				} catch (SQLException e) {
					// 记录异动表
					this.addSyncException(new SyncException("所有同步添加", id, syncObj.getClass().getSimpleName(),
							e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				}
			} else {
				try {
					wdao.update(getUpdateSql());
				} catch (Exception e) {
					// 记录异动表
					this.addSyncException(new SyncException("所有同步更新", id, syncObj.getClass().getSimpleName(),
							e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
				}
			}
		}
	}
	
	/**
	 * 得到插入sql
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	protected String getInsertSql(){
		StringBuffer updateSql = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for (String field : syncFields.split(",")) {
			Object value = platRecord.get(field);
			// if the value is foreign key
			if (foreignKeyMap.keySet().contains(field)) {
				value = win_foreignkey_value.get(field);
			}
			
			// if value is String
			if (value != null && value.getClass().getSimpleName().equals("String")) {
				value = "'" + value.toString().replaceAll("\'", "") + "'";
			}
			values.append(",").append(value == null ? "null" : value);
		}
		values.delete(0, 1);
		updateSql.append("INSERT INTO ").append(tableName).append(" (")
				.append(syncFields).append(") VALUES (").append(values)
				.append(")");
		return updateSql.toString();
	}
	
	/**
	 * 得到更新sql
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	protected String getUpdateSql(){
		StringBuffer updateSql = new StringBuffer();
		StringBuffer values = new StringBuffer();
		for (String field : syncFields.split(",")) {
			Object value = platRecord.get(field);
			Object winValue = winRecord.get(field);
			
			if (value != null && value.equals(winValue)){
				continue;
			}
			
			// foreign key don't update
			if (foreignKeyMap.keySet().contains(field)) {
				continue;
			}

			// uniqeKey don't update
			if (field.equals(uniqueKeyName)) {
				continue;
			}
			
			// if value is String
			if (value != null && value.getClass().getSimpleName().equals("String")) {
				value = "'" + value.toString().replaceAll("\'", "") + "'";
			}
			values.append(",").append(field).append("=").append(value);
		}
		values.delete(0, 1);
		updateSql.append("UPDATE ").append(tableName).append(" SET ")
				.append(values).append(" WHERE ").append(uniqueKeyName)
				.append("='").append(uniqueKeyValue).append("'");
		return updateSql.toString();
	}
	
	/**
	 * 加载窗口记录的外键
	 * @author Xiadi
	 * @since 2013-12-3 
	 *
	 * @param wdao
	 * @throws SQLException 
	 */
	protected void reloadWinForeignKeyValue(WindowDao wdao) throws SQLException{
		if (foreignKeyMap != null && foreignKeyMap.size() > 0) {
			win_foreignkey_value = new HashMap<String, Object>();
			for(String foreignkey : foreignKeyMap.keySet()){
				String table = foreignKeyMap.get(foreignkey)[0];
				String uuidName = foreignKeyMap.get(foreignkey)[1];
				String idName = foreignKeyMap.get(foreignkey)[2];

				String winSql = "SELECT " + idName + " FROM " + table
						+ " WHERE " + uuidName + " = '"
						+ platRecord.get(uuidName) 
						+ "'";
				Map<String, Object> ret = wdao.findFirst(winSql);
				if (ret != null && ret.size() > 0) {
					win_foreignkey_value.put(foreignkey, ret.get(idName));
				}
			}
		}
	}
	
	/**
	 * 异动表记录
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	public void addSyncException(SyncException syncException){
		// 打印异常
		System.out.println(syncException);
		String sql = "INSERT INTO syncexception(action,wid,className,exceptionMsg,syncTime,jsonData) VALUES(?,?,?,?,?,?)";
		Object[] param = new Object[]{
				syncException.getAction(),
				syncException.getWid(),
				syncException.getClassName(),
				syncException.getExceptionMsg(),
				syncException.getSyncTime(),
				syncException.getJsonData()
		};
		try {
			SyncFactory.getDao().update(sql, param);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 根据对象得到Map
	 * @author Xiadi
	 * @since 2013-12-2 
	 *
	 */
	protected Map<String, Object> toMap(Object javaBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = javaBean.getClass().getDeclaredMethods();

		for (Method method : methods) {
			try {
				if (method.getName().startsWith("get")) {
					String field = method.getName();
					field = field.substring(field.indexOf("get") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);

					Object value = method.invoke(javaBean, (Object[]) null);
					result.put(field, value);
				}
			} catch (Exception e) {
			}
		}
		return result;
	}
	
	public Object getSyncObj() {
		return syncObj;
	}

	public SyncType getSyncType() {
		return syncType;
	}

	public WindowDao getWindowdao() {
		return windowdao;
	}

	public Integer getWid() {
		return wid;
	}

	public String getIgnoreFields() {
		return ignoreFields;
	}

	public String getTableName() {
		return tableName;
	}

	public String getUniqueKeyName() {
		return uniqueKeyName;
	}

	public String getUniqueKeyValue() {
		return uniqueKeyValue;
	}

	public Map<String, Object> getPlatRecord() {
		return platRecord;
	}

	public Map<String, Object> getWinRecord() {
		return winRecord;
	}

	public Map<String, String[]> getForeignKeyMap() {
		return foreignKeyMap;
	}

	public Map<String, Object> getWin_foreignkey_value() {
		return win_foreignkey_value;
	}

	public String getSyncFields() {
		return syncFields;
	}

}
