package com.eaglec.plat.domain.cms;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mtemplate", uniqueConstraints = @UniqueConstraint(columnNames = "MTCODE"))
public class ModuleTemplate implements Serializable{

	private static final long serialVersionUID = 2202277753802898753L;
	
	private int mtid;		//模版ID
	private String mtcode;	//模版编码
	private String mtname;	//模版名称
	private String mtdesc;	//模版描述
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMtid() {
		return mtid;
	}
	public void setMtid(int mtid) {
		this.mtid = mtid;
	}
	public String getMtcode() {
		return mtcode;
	}
	public void setMtcode(String mtcode) {
		this.mtcode = mtcode;
	}
	public String getMtname() {
		return mtname;
	}
	public void setMtname(String mtname) {
		this.mtname = mtname;
	}
	public String getMtdesc() {
		return mtdesc;
	}
	public void setMtdesc(String mtdesc) {
		this.mtdesc = mtdesc;
	}
	
	@Override
	public String toString() {
		return "ModuleTemplate [mtid=" + mtid + ", mtcode=" + mtcode
				+ ", mtname=" + mtname + ", mtdesc=" + mtdesc + "]";
	}

}
