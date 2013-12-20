package com.eaglec.plat.biz.impl.mobileApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.mobileApp.AppMessageBiz;
import com.eaglec.plat.dao.mobileApp.AppMessageDao;
import com.eaglec.plat.domain.mobileApp.AppMessage;
import com.eaglec.plat.view.JSONData;

@Service
public class AppMessageBizImpl implements AppMessageBiz {
	@Autowired
	private AppMessageDao appMessageDao;
	
	@Override
	public int save(AppMessage appMessage) {
		return appMessageDao.add(appMessage);
	}

	@Override
	public JSONData<AppMessage> find(int start, int limit, String title) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM AppMessage WHERE 1=1 ");
		if((title != null) && (!"".equals(title))) {
			hql.append(" AND title like '%").append(title).append("%'");
		}
		hql.append(" ORDER BY time DESC");
		return appMessageDao.outJSONData(hql.toString(), start, limit);
	}

	@Override
	public AppMessage get(Integer id) {
		return appMessageDao.get(id);
	}

	@Override
	public void update(AppMessage appMessage) {
		appMessageDao.update(appMessage);
	}

	@Override
	public void removeMessages(String idStr) throws Exception{
		String sql = "DELETE FROM appmessage WHERE id in(" + idStr + ")";
		appMessageDao.executeSql(sql);
	}

}
