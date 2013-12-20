package com.eaglec.plat.biz.impl.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.auth.AccessOauthBiz;
import com.eaglec.plat.dao.auth.AccessOauthDao;
import com.eaglec.plat.domain.base.AccessOauth;

@Service
public class AccessOauthBizImpl implements AccessOauthBiz {
	
	@Autowired
	private AccessOauthDao accessOauthDao;

	@Override
	public List<AccessOauth> findAccessOauthByAccessToken(String accessToken) {
		return accessOauthDao.findList("from AccessOauth where accessToken ='"+accessToken+"'");
	}

	@Override
	public void addOrUpdate(AccessOauth accessOauth) {
		accessOauthDao.save(accessOauth);
	}

}
