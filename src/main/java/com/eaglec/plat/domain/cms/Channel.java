package com.eaglec.plat.domain.cms;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "channel", uniqueConstraints = @UniqueConstraint(columnNames = "CCODE"))
public class Channel implements Serializable{
	
	private static final long serialVersionUID = 265145309761620980L;
	
	private Integer id;	//频道ID
	private String ccode;	//频道编码
	private Integer pid;		//频道父编码
	private String cname;	//频道名称
	private Boolean leaf = true; //叶子节点
	private String mtcode;	//所属模版
	private Integer cindex;	//频道位置
	private String chttp;	//频道连接地址
	private String cdesc;	//频道描述
	private String linktype;//"_blank"、新窗口打开；"_self"、当前窗口打开
	private Boolean isChannel = true;	//是否为频道
	private Integer mnumber = 0;	//模块个数
	private List<Channel> children;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	@ForeignKey(name="pid")
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getMtcode() {
		return mtcode;
	}
	public void setMtcode(String mtcode) {
		this.mtcode = mtcode;
	}
	public Integer getCindex() {
		return cindex;
	}
	public void setCindex(Integer cindex) {
		this.cindex = cindex;
	}
	public String getChttp() {
		return chttp;
	}
	public void setChttp(String chttp) {
		this.chttp = chttp;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	public String getLinktype() {
		return linktype;
	}
	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}
	public Boolean getIsChannel() {
		return isChannel;
	}
	public void setIsChannel(Boolean isChannel) {
		this.isChannel = isChannel;
	}
	public Integer getMnumber() {
		return mnumber;
	}
	public void setMnumber(Integer mnumber) {
		this.mnumber = mnumber;
	}
	//  @JsonInclude(Include.NON_NULL)序列化时的标记 如果为空则不参与序列化
	@OneToMany(mappedBy = "pid", cascade = CascadeType.ALL)
	public List<Channel> getChildren() {
		return children;
	}
	public void setChildren(List<Channel> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "Channel [id=" + id + ", ccode=" + ccode + ", pid=" + pid
				+ ", cname=" + cname + ", leaf=" + leaf + ", mtcode=" + mtcode
				+ ", cindex=" + cindex + ", chttp=" + chttp + ", cdesc="
				+ cdesc + ", linktype=" + linktype + ", isChannel=" + isChannel
				+ ", mnumber=" + mnumber + ", children=" + children + "]";
	}
}



