package com.eaglec.plat.biz.auth;

import java.util.List;

import com.eaglec.plat.domain.base.AccessOauth;

public interface AccessOauthBiz {
	public void addOrUpdate(AccessOauth accessOauth);
	public List<AccessOauth> findAccessOauthByAccessToken(String accessToken);
}
