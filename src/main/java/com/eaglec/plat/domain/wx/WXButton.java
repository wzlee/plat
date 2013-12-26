package com.eaglec.plat.domain.wx;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 微信菜单按钮(包括普通按钮,链接按钮,父按钮)
 * 
 * @author liuliping
 * @since 2013-12-24
 */
@Entity
@Table(name = "wxbutton")
public class WXButton implements Serializable{
	
	private static final long serialVersionUID = -3926004300092359605L;
	
	/**
	 * 按钮类型 
	 * 0:父按钮, 1:普通按钮(点击后服务端返回响应信息), 2:链接按钮(点击后跳转链接) 
	 * */
	private Integer type;    
	
	private Integer id;     // 主键
	
	/**
	 * 普通按钮唯一标识,内容为字母组合,作为在微信端普通按钮的唯一标识
	 * */
	private String key;     
	
	private AutoMessage autoMessage;     // 普通按钮所对应的响应信息
	
	private String url;     // 链接按钮的链接地址
	
	/**
	 * 按钮的次序排列
	 * 父按钮最多三个,父按钮的次序有 0,1,2
	 * 自按钮最多5个,普通按钮和链接按钮都是子按钮,排列次序为0,1,2,3,4
	 * */
	private Integer sequence;     
	
	private WXButton parent;     // 父按钮

	
	public WXButton() {
		super();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@ManyToOne
	public AutoMessage getAutoMessage() {
		return autoMessage;
	}

	public void setAutoMessage(AutoMessage autoMessage) {
		this.autoMessage = autoMessage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@ManyToOne
	public WXButton getParent() {
		return parent;
	}

	public void setParent(WXButton parent) {
		this.parent = parent;
	}

}