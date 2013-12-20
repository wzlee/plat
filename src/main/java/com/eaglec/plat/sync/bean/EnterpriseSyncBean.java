package com.eaglec.plat.sync.bean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.SyncException;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;

/**
 * 企业同步类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class EnterpriseSyncBean extends AbstractSyncBean<Enterprise> {

	public EnterpriseSyncBean(Enterprise syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}
	
	public EnterpriseSyncBean(Enterprise syncObj, int wid, SyncType syncType) {
		super(syncObj, syncType);
		super.wid = wid;
	}

	@Override
	protected void buildingBean(Enterprise syncObj) throws Exception {
		if (syncObj.getIndustryType() == null) {
			throw new Exception("enterprise.getIndustryType() is null");
		}
		if (wid == null) {
			wid = syncObj.getIndustryType();
		}
		if (ignoreFields == null) {
			ignoreFields = "id";
		}
		foreignKeyMap = new HashMap<String, String[]>();

		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "eid";
		uniqueKeyValue = syncObj.getEid();

		selectSql = "SELECT * FROM enterprise WHERE eid = '" + uniqueKeyValue + "'";

		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
	
	@Override
	public void saveOrUpdateToAll() {
		
		super.saveOrUpdateToAll();
		
		// 同步我的服务领域
		if (windowdao == null || uniqueKeyValue == null || platRecord == null) {
			return;
		}
		
		// STEP1 .查询对应窗口的Enterprise
		String sql = "SELECT id FROM enterprise WHERE eid = '" + uniqueKeyValue + "'";
		Map<String, Object> enterprsie = null;
		try {
			enterprsie = windowdao.findFirst(sql);
			if (enterprsie == null) {
				return;
			}
		} catch (SQLException e) {
			this.addSyncException(new SyncException("连接窗口数据库", wid, syncObj.getClass().getSimpleName(), 
					e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
		}
		
		// STEP2 .查询本地 enterprise_category 的该企业记录
		sql = "SELECT * FROM enterprise_category WHERE enterprise_id = " + platRecord.get("id");
		List<Map<String, Object>> list = SyncFactory.getDao().find(sql);
		if (list.size() < 1) {
			// 我的服务领域 为空
			// 删除对应窗口 enterprise_category 的该企业记录
			try {
				windowdao.update("DELETE FROM enterprise_category WHERE enterprise_id = " + enterprsie.get("id"));
			} catch (SQLException e) {
				// 记录异动表
				super.addSyncException(new SyncException("清空服务领域", wid, syncObj.getClass().getSimpleName(),
						e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
			}
			return;
		}
		
		// STEP3 .删除对应窗口 enterprise_category 的该企业记录, 拼接sql
		StringBuffer sb = new StringBuffer("INSERT INTO enterprise_category VALUES");
		for (Map<String, Object> record : list) {
			sb.append("(").append(enterprsie.get("id")).append(",")
				.append(record.get("category_id")).append("),");
		}
		sb.delete(sb.length()-1, sb.length()); // 删除末尾的“,”
		sql = "DELETE FROM enterprise_category WHERE enterprise_id = " + enterprsie.get("id");
		
		// STEP4 .先删除 后 插入
		try {
			windowdao.update(sql);
		} catch (SQLException e) {
			// 记录异动表
			super.addSyncException(new SyncException("删除服务领域", wid, syncObj.getClass().getSimpleName(),
					e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
		}
		try {
			windowdao.update(sb.toString());
		} catch (SQLException e) {
			// 记录异动表
			super.addSyncException(new SyncException("插入服务领域", wid, syncObj.getClass().getSimpleName(),
					e.getLocalizedMessage(), JSON.toJSONString(platRecord)));
		}
	}
}
