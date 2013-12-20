package com.eaglec.plat.domain.base;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 资源文件类,方便管理项目中上传的文件
 * 
 * @author liuliping
 * @since 2013-07-25
 */
@Entity
@Table(name = "filemanager", uniqueConstraints = @UniqueConstraint(columnNames = "ID"))
public class FileManager implements Serializable {
	private static final long serialVersionUID = -8752317896336873426L;
	
	private Integer id;	
	
	private String fname;	//文件名
	
	private String clazz;	//文件关联的模块类名称,如新闻"News"
	
	private String date;	//日期
	
	private boolean status = false;	//关联状态,true表示文件已被使用;反之未被使用可以删除

	public FileManager () {}

	public FileManager (String fname, String clazz, String date, boolean status) {
		this.fname = fname;
		this.clazz = clazz;
		this.date = date;
		this.status = status;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FileManager [id=" + id + ", fname=" + fname + ", clazz="
				+ clazz + ", date=" + date + ", status=" + status + "]";
	}

}
