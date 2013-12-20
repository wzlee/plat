package com.eaglec.plat.domain.cms;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "module")
public class Module implements Serializable {

	private static final long serialVersionUID = 2114328435255915600L;

	private Integer mid; // 主键ID
	private String mcode; // 模块编码
	private String mname; // 模块名称
	private String mchannel; // 模块所属频道
	private Integer mposition; // 模块位置索引
	private String mdesc; // 模块描述
	private String micon = ""; // 色块图标
	private String mindex; // 模块链接首页
	private String linktype;// _blank、新窗口打开；_self、当前窗口打开
	private String mclass; // 模块样式
	private String industryType; // 服务机构所属行业代码

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMchannel() {
		return mchannel;
	}

	public void setMchannel(String mchannel) {
		this.mchannel = mchannel;
	}

	public Integer getMposition() {
		return mposition;
	}

	public void setMposition(Integer mposition) {
		this.mposition = mposition;
	}

	public String getMdesc() {
		return mdesc;
	}

	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}

	public String getMicon() {
		return micon;
	}

	public void setMicon(String micon) {
		this.micon = micon;
	}

	public String getMindex() {
		return mindex;
	}

	public String getMclass() {
		return mclass;
	}

	public void setMclass(String mclass) {
		this.mclass = mclass;
	}

	public void setMindex(String mindex) {
		this.mindex = mindex;
	}

	public String getLinktype() {
		return linktype;
	}

	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	@Override
	public String toString() {
		return "Module [mid=" + mid + ", mcode=" + mcode + ", mname=" + mname
				+ ", mchannel=" + mchannel + ", mposition=" + mposition
				+ ", mdesc=" + mdesc + ", micon=" + micon + ", mindex="
				+ mindex + ", linktype=" + linktype + ", mclass=" + mclass
				+ ", industryType=" + industryType + "]";
	}
}
