package com.eaglec.plat.sync.bean;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.domain.base.SyncException;
import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncFactory;
import com.eaglec.plat.sync.SyncType;
/**
 * 服务机构注册审核同步
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
public class OrgRegisterSyncBean extends AbstractSyncBean<OrgRegisterApproval> {

	public OrgRegisterSyncBean(OrgRegisterApproval syncObj, SyncType syncType) {
		super(syncObj, syncType);
	}

	@Override
	protected void buildingBean(OrgRegisterApproval syncObj) throws Exception {
		if (syncObj.getIndustryType() == null) {
			throw new Exception("OrgRegisterApproval.getIndustryType() is null");
		}
		wid = syncObj.getIndustryType();
		if (ignoreFields == null) {
			ignoreFields = "id,manager_id,eid";
		}
		foreignKeyMap = new HashMap<String, String[]>();
		
		if(!StringUtils.isEmpty(syncObj.getEnterprise())){
			foreignKeyMap.put("enterprise_id", new String[]{"enterprise", "eid", "id"});
		}
		windowdao = SyncFactory.getWindowDao(wid);
		uniqueKeyName = "oraid";
		uniqueKeyValue = syncObj.getOraid();
		
		if(!StringUtils.isEmpty(syncObj.getEnterprise())){
			selectSql = "SELECT o.*,e.eid FROM orgregisterapproval o,enterprise e "
				+"where o.enterprise_id = e.id AND o.oraid = '" + uniqueKeyValue + "'";
		}else{
			selectSql = "SELECT * FROM orgregisterapproval WHERE oraid = '" + uniqueKeyValue + "'";
		}
		platRecord = SyncFactory.getDao().findFirst(selectSql);
	}
	
	/**
	 * 同步至某个窗口(重写同步方法)
	 * @author xuwf
	 * @since 2013-12-19
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
				windowdao.update(getDeleteSql());
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
	 * 得到删除sql
	 * @author xuwf
	 * @since 2013-12-20 
	 *
	 */
	public String getDeleteSql(){
		StringBuffer updateSql = new StringBuffer();
		updateSql.append("DELETE FROM ").append(tableName).append(" WHERE ")
				.append(uniqueKeyName).append(" = '").append(uniqueKeyValue).append("'");
		return updateSql.toString();
	}
}
