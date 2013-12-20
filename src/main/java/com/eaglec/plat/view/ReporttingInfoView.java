package com.eaglec.plat.view;

import java.util.List;

import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.policy.PolicyCategory;

/**
 * 三级类别及类别下政策的显示
 * 
 * @author huyj
 * 
 */
public class ReporttingInfoView {

	private PolicyCategory policyCategory; // 三级类别
	private List<Policy> financialReportings; // 信息集合

	public ReporttingInfoView() {
	}

	public ReporttingInfoView(PolicyCategory policyCategory,
			List<Policy> financialReportings) {
		super();
		this.policyCategory = policyCategory;
		this.financialReportings = financialReportings;
	}

	public PolicyCategory getPolicyCategory() {
		return policyCategory;
	}

	public void setPolicyCategory(PolicyCategory policyCategory) {
		this.policyCategory = policyCategory;
	}

	public List<Policy> getFinancialReportings() {
		return financialReportings;
	}

	public void setFinancialReportings(List<Policy> financialReportings) {
		this.financialReportings = financialReportings;
	}

}
