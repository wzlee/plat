package com.eaglec.plat.view;

/**
 * 资金资助参数
 * 
 * @author huyj
 * 
 */
public class PolicyParam {

	private String time; // 注册时间
	private String addr; // 公司地址
	private String enterprise; // 所属行业
	private Integer businessIncome; // 上年度主营业务收入
	private Integer totalIncome; // 上年度销售总收入

	public PolicyParam() {
	}

	public PolicyParam(String time, String addr, String enterprise,
			Integer businessIncome, Integer totalIncome) {
		super();
		this.time = time;
		this.addr = addr;
		this.enterprise = enterprise;
		this.businessIncome = businessIncome;
		this.totalIncome = totalIncome;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public Integer getBusinessIncome() {
		return businessIncome;
	}

	public void setBusinessIncome(Integer businessIncome) {
		this.businessIncome = businessIncome;
	}

	public Integer getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		return "PolicyParam [time=" + time + ", addr=" + addr + ", enterprise="
				+ enterprise + ", businessIncome=" + businessIncome
				+ ", totalIncome=" + totalIncome + "]";
	}

}
