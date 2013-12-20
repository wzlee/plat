package com.eaglec.plat.biz.impl.publik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.dao.publik.FileManagerDao;
import com.eaglec.plat.domain.base.FileManager;

/**
 * 资源文件业务层实现类
 * 
 * @author liuliping
 * @since 2013-07-25
 * */
@Service
public class FileManagerBizImpl implements FileManagerBiz {
	@Autowired
	private FileManagerDao fileManagerDao;

	public FileManagerDao getFileManagerDao() {
		return fileManagerDao;
	}

	public void setFileManagerDao(FileManagerDao fileManagerDao) {
		this.fileManagerDao = fileManagerDao;
	}

	public void saveFileManager(FileManager fileManager) {
		fileManagerDao.save(fileManager);
	}

	public void deleteFileManager(int id) {
		fileManagerDao.delete(id);
	}

	public void updateFileManager(FileManager fileManager) {
		fileManagerDao.update(fileManager);
	}

	public void get(int id) {
		fileManagerDao.get(id);
	}

	@Override
	public List<FileManager> findByName(String fileName) {
		String hql = "from FileManager where fname='" + fileName + "'";
		return fileManagerDao.findList(hql);
	}

	@Override
	public FileManager findOne(String fileName) {
		return fileManagerDao.findOne(fileName);
	}

	@Override
	public FileManager addRelateClazz(String fileName, String clazz) {
		// 与文件类关联
		FileManager file = null;
		if (fileName != null && !"".equals(fileName)) {
			file = this.findOne(fileName);
			if (file != null) {
				file.setClazz(clazz);
				file.setStatus(true);
				this.updateFileManager(file);
			}
		}
		return file;
	}

}
