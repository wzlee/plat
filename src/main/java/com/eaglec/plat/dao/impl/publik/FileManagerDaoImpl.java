package com.eaglec.plat.dao.impl.publik;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.publik.FileManagerDao;
import com.eaglec.plat.domain.base.FileManager;

/**
 * 资源文件持久层实现类
 * 
 * @author liuliping
 * @since 2013-07-25
 * */
@Repository
public class FileManagerDaoImpl extends BaseDaoImpl<FileManager> implements
		FileManagerDao {

	/**
	 * 通过主键id获取单个文件对象
	 * 
	 * @author liuliping
	 * @since 2013-07-25
	 * 
	 * @param id
	 * @return FileManager对象
	 * */
	public FileManager get(int id) {
		return super.get(id);
	}

	@Override
	public FileManager findOne(String fileName) {
		// TODO Auto-generated method stub
		return (FileManager) super
				.uniqueResult("from FileManager where fname = '" + fileName
						+ "'");
	}
}
