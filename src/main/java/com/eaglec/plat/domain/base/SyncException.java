package com.eaglec.plat.domain.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 同步异动实体类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
/**
 * @author Xiadi
 * @since 2013-12-2
 */
@Entity
@Table(name="syncexception")
public class SyncException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String  action;	// 同步的动作：添加 or更新
	private Integer	wid;	// 对应窗口
	private String	className; // 同步类
	private String 	exceptionMsg;	// 异常原因
	private Long	syncTime = System.currentTimeMillis();
	private String	jsonData;	// 同步数据
	
	public SyncException(String action, Integer wid, String className,
			String 	exceptionMsg, String jsonData) {
		super();
		this.action = action;
		this.wid = wid;
		this.className = className;
		this.exceptionMsg = exceptionMsg;
		this.jsonData = jsonData;
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Long getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Long syncTime) {
		this.syncTime = syncTime;
	}
	@Column(length = 5000)
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	@Column(length = 5000)
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "SyncException [id=" + id + ", action=" + action + ", wid="
				+ wid + ", className=" + className + ", exceptionMsg="
				+ exceptionMsg + ", syncTime=" + syncTime + ", jsonData="
				+ jsonData + "]";
	}
	
}
