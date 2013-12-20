package com.eaglec.plat.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.business.GoodsOrder;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.sync.inteface.SyncTask;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.Dao;

public class SyncFactory {
	private static Dao dao;
	
	private static Map<Integer, WindowDao> daoMap = new HashMap<Integer, WindowDao>();
	private static Map<Integer, String> windowNames= new HashMap<Integer, String>();
	
	public static void putWindowDao(Integer wid, WindowDao windowDao){
		daoMap.put(wid, windowDao);
	}
	
	public static WindowDao getWindowDao(Integer wid){
		return daoMap.get(wid);
	}
	
	public static void putWindowName(Integer wid, String name){
		windowNames.put(wid, name);
	}
	
	public static String getWindowName(Integer wid){
		return windowNames.get(wid);
	}
	
	public static Dao getDao() {
		return dao;
	}

	public static void setDao(Dao dao) {
		SyncFactory.dao = dao;
	}
	
	public static Set<Integer> getDaoMapKeySet(){
		return daoMap.keySet();
	}

	/**
	 * 执行同步任务
	 * @author Xiadi
	 * @since 2013-12-3 
	 *
	 * @param syncTask
	 */
	public static void executeTask(SyncTask syncTask){
		// 同步开关
		boolean isSync = Boolean.parseBoolean(ResourceBundle.getBundle("sync-config").getString("isSync"));
		if (isSync){
			// 建立一个线程单独执行该任务
			Thread syncThread = new Thread(syncTask);
			syncThread.start();
		}
	}
	
	/**
	 * 按照优先级执行同步任务，该方法不一定有效
	 * @author Xiadi
	 * @since 2013-12-3 
	 *
	 * @param syncTask
	 * @param priority Thread.MAX_PRIORITY Or Thread.MIN_PRIORITY
	 */
	public static void executeTask(SyncTask syncTask, int priority){
		// 同步开关
		boolean isSync = Boolean.parseBoolean(ResourceBundle.getBundle("sync-config").getString("isSync"));
		if (isSync){
			// 建立一个线程单独执行该任务
			Thread syncThread = new Thread(syncTask);
			syncThread.setPriority(priority);
			syncThread.start();
		}
	}
	
	/**
	 * 执行同步任务，若 isJoin = true,则执行完该任务主线程再执行<br/>
	 * @author Xiadi
	 * @since 2013-12-3 
	 *
	 * @param syncTask
	 * @param isJoin
	 */
	public static void executeTask(SyncTask syncTask, boolean isJoin){
		// 同步开关
		boolean isSync = Boolean.parseBoolean(ResourceBundle.getBundle("sync-config").getString("isSync"));
		if (isSync){
			// 建立一个线程单独执行该任务
			Thread syncThread = new Thread(syncTask);
			syncThread.start();
			// 是否让主线程等待该线程结束
			if (isJoin) {
				try {
					syncThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static SyncType getSyncType(Enterprise enterprise){
		if (enterprise == null 
				|| enterprise.getType() == null
				|| enterprise.getIndustryType() == null) {
			return SyncType.NONE;
		}
		// 企业不是服务机构且企业属于枢纽或其他 不同步
		if (enterprise.getType() != Constant.ENTERPRISE_TYPE_ORG && 
				(enterprise.getIndustryType() == 16 || enterprise.getIndustryType() == 15)) {
			return SyncType.NONE;
		}
		if (enterprise.getType() == Constant.ENTERPRISE_TYPE_ORG) {
			return SyncType.ALL;
		}
		return SyncType.ONE;
	}
	
	public static SyncType getSyncType(Service service){
		if (service == null 
				|| service.getCurrentStatus() == null) {
			return SyncType.NONE;
		}
		if (service.getCurrentStatus() == Constant.SERVICE_STATUS_ADDED 
				|| (service.getLastStatus() != null
						&& service.getLastStatus() == Constant.SERVICE_STATUS_DOWN) // 针对删除
				|| service.getCurrentStatus() == Constant.SERVICE_STATUS_DOWN) {
			return SyncType.ALL;
		}
		if (service.getEnterprise().getIndustryType() == 16){// 枢纽不同步
			return SyncType.NONE;
		}
		return SyncType.ONE;
	}
	
	public static SyncType getBuyerSyncType(GoodsOrder goodsOrder){//买方
		//买方企业
		Enterprise buyerEnterprise = goodsOrder.getBuyer().getEnterprise();
		if (goodsOrder == null || buyerEnterprise.getIndustryType() == 16) {
			return SyncType.NONE;
		}
		return SyncType.ONE;
	}
	
	public static SyncType getSellerSyncType(GoodsOrder goodsOrder){//卖方
		//卖方企业
		Enterprise sellerEnterprise = goodsOrder.getService().getEnterprise();
		if (goodsOrder == null || sellerEnterprise.getIndustryType() == 16) {
			return SyncType.NONE;
		}
		return SyncType.ONE;
	}
}
