package com.eaglec.plat.view;

import java.util.List;

import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.utils.Common;

public class PolicyView {
	private PolicyCategory policyCategory;
	private List<Policy> policyList;
	
	private String title;	//标题
	private Integer id;		//id
	private String url;		//链接

	public PolicyView() {
	}

	public PolicyView(PolicyCategory policyCategory, List<Policy> policyList) {
		super();
		this.policyCategory = policyCategory;
		this.policyList = policyList;
	}

	public PolicyView(String title, Integer id) {
		super();
		this.title = title;
		this.id = id;
		StringBuilder sb = new StringBuilder();
		this.url = sb.append(Common.PLAT_DOMAIN).append("policy/detail?id=").append(id).toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PolicyCategory getPolicyCategory() {
		return policyCategory;
	}

	public void setPolicyCategory(PolicyCategory policyCategory) {
		this.policyCategory = policyCategory;
	}

	public List<Policy> getPolicyList() {
		return policyList;
	}

	public void setPolicyList(List<Policy> policyList) {
		this.policyList = policyList;
	}

	@Override
	public String toString() {
		return "PolicyView [policyCategory=" + policyCategory + ", policyList="
				+ policyList + "]";
	}
}
