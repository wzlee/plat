package com.eaglec.plat.view;

import java.io.Serializable;

public class GoodsOrderView implements Serializable {
	private static final long serialVersionUID = -6004290254824308154L;

	private String sName; // 订单服务名称
	private Integer oid; // 订单ID
	private Integer sid; // 订单服务ID
	private Integer bsid; // 招标服务ID
	private Integer orderStatus; // 状态代码（1、等待卖家确认 6、交易进行中 7、等待买家关闭 8、等待卖家关闭
									// 9、申诉处理中 10、交易结束 11、订单取消）
	private String time; // 生成订单时间

	public GoodsOrderView() {
		super();
	}

	public GoodsOrderView(String sName, Integer oid, Integer sid, Integer bsid,
			Integer orderStatus, String time) {
		super();
		this.sName = sName;
		this.oid = oid;
		this.sid = sid;
		this.bsid = bsid;
		this.orderStatus = orderStatus;
		this.time = time;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

}