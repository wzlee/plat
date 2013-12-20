package com.eaglec.plat.biz.impl.wx;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.domain.wx.AutoMessage;
import com.eaglec.plat.view.JSONData;


@Service
public class AutoMessageBizImpl implements com.eaglec.plat.biz.wx.AutoMessageBiz {

	@Autowired
	com.eaglec.plat.dao.wx.AutoMessageDao autoMessageDao;

	@Override
	public List<AutoMessage> getAll() {
		String hql = "from AutoMessage where 1=1";
		return autoMessageDao.findList(hql);
	}

	@Override
	public AutoMessage get(Serializable id) {
		return autoMessageDao.get(id);
	}

	@Override
	public AutoMessage add(AutoMessage autoMsg) {
		return autoMessageDao.save(autoMsg);
	}

	public void delete(String idStr) throws org.hibernate.exception.ConstraintViolationException{
//		// 检查是否有
		String hql = "DELETE FROM AutoMessage WHERE id IN(" + idStr + ")";
		autoMessageDao.executeHql(hql, null);
	}
	
	@Override
	public void delete(AutoMessage autoMsg) {
		autoMessageDao.delete(autoMsg);
	}

	@Override
	public AutoMessage update(AutoMessage autoMsg) {
		return autoMessageDao.update(autoMsg);
	}

	@Override
	public List<AutoMessage> getByType(String msgType) {
		String hql = "from AutoMessage where msgType='" + msgType + "'";
		return autoMessageDao.findList(hql);
	}

	@Override
	public AutoMessage findByReqKey(String name, String value) {
		// from AutoMessage where reqKey='?'
		String hql = "from AutoMessage where " + name + " = '" + value + "'";
		return autoMessageDao.get(hql);
	}

	@Override
	public JSONData<AutoMessage> query(String reqKey, String clickKey, int start, int limit) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT NEW AutoMessage(a) FROM AutoMessage a WHERE 1 = 1");
		if(!StringUtils.isEmpty(reqKey)) {
			sb.append(" AND a.reqKey LIKE '%").append(reqKey).append("%'");
		}
		if(!StringUtils.isEmpty(clickKey)) {
			sb.append(" AND a.clickKey LIKE '%").append(clickKey).append("%'");
		}
		sb.append("ORDER BY a.id DESC");
		return autoMessageDao.outJSONData(sb.toString(), null, start, limit);
	}
}
