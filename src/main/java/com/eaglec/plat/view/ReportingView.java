package com.eaglec.plat.view;

import java.util.List;

import com.eaglec.plat.domain.policy.PolicyCategory;

/**
 * 二级类别显示
 * 
 * @author huyj
 * 
 */
public class ReportingView {
	private PolicyCategory policyCategory; // 二级类别
	private List<ReporttingInfoView> infoList; // 三级级类别及相关信息

	public ReportingView() {
	}

	public ReportingView(PolicyCategory policyCategory,
			List<PolicyCategory> childrenCategory,
			List<ReporttingInfoView> infoList) {
		super();
		this.policyCategory = policyCategory;
		this.infoList = infoList;
	}

	public PolicyCategory getPolicyCategory() {
		return policyCategory;
	}

	public void setPolicyCategory(PolicyCategory policyCategory) {
		this.policyCategory = policyCategory;
	}


	public List<ReporttingInfoView> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<ReporttingInfoView> infoList) {
		this.infoList = infoList;
	}

	@Override
	public String toString() {
		return "ReportingView [policyCategory=" + policyCategory
				+ ", infoList="
				+ infoList + "]";
	}

}
