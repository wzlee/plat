package com.eaglec.plat.sync.impl;

import com.eaglec.plat.sync.AbstractSyncBean;
import com.eaglec.plat.sync.SyncType;
import com.eaglec.plat.sync.inteface.SaveOrUpdateToWin;

public class SaveOrUpdateToWinImpl<T> implements SaveOrUpdateToWin {
	private AbstractSyncBean<T> syncBean;

	public SaveOrUpdateToWinImpl(AbstractSyncBean<T> syncBean) {
		this.syncBean = syncBean;
	}

	@Override
	public void run() {
		// 根据 syncBean中的参数 开始执行同步任务
		if (syncBean.getSyncType() == SyncType.NONE) {
			return;
		}
		if (syncBean.init()) {
			if (syncBean.getSyncType() == SyncType.ONE) {
				syncBean.saveOrUpdateToOne();
			} else if (syncBean.getSyncType() == SyncType.OTHER) {
				syncBean.saveOrUpdateToOther();
			} else if (syncBean.getSyncType() == SyncType.ALL) {
				syncBean.saveOrUpdateToAll();
			}
		}
	}
}
