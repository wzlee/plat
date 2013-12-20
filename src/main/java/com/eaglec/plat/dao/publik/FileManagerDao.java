package com.eaglec.plat.dao.publik;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.domain.base.FileManager;

public interface FileManagerDao extends BaseDao<FileManager>{
	public FileManager get(int id);
	
	public FileManager findOne(String fileName);
}
