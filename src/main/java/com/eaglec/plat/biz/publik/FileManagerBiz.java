package com.eaglec.plat.biz.publik;

import java.util.List;

import com.eaglec.plat.domain.base.FileManager;

public interface FileManagerBiz {
	
	/**
	 * 新增文件对象
	 * 
	 * @author liuliping
	 * @since 2013-07-25
	 * 
	 * @param fileManager
	 *            文件对象
	 * @return 无返回值
	 */
	public void saveFileManager(FileManager fileManager);

	/**
	 * 通过主键id删除单个文件对象
	 * 
	 * @author liuliping
	 * @since 2013-07-25
	 * 
	 * @param id
	 * @return 无返回值
	 */
	public void deleteFileManager(int id);

	/**
	 * 更新文件对象
	 * 
	 * @author liuliping
	 * @since 2013-07-25
	 * 
	 * @param fileManager
	 *            文件对象
	 * @return 无返回值
	 */
	public void updateFileManager(FileManager fileManager);

	/**
	 * 通过主键id获取单个文件对象
	 * 
	 * @author liuliping
	 * @since 2013-07-25
	 * 
	 * @param id
	 * @return FileManager对象
	 */
	public void get(int id);

	/**
	 * 根据文件名查找List
	 * @author Xiadi
	 * @since 2013-9-2 
	 *
	 * @param fileName
	 * @return
	 */
	public List<FileManager> findByName(String fileName);

	/**
	 * 查找单个文件
	 * @author Xiadi
	 * @since 2013-9-2 
	 *
	 * @param fileName
	 * @return
	 */
	public FileManager findOne(String fileName);

	/**
	 * 为指定文件添加关联Class
	 * 
	 * @author Xiadi
	 * @since 2013-9-2
	 * 
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	public FileManager addRelateClazz(String fileName, String clazz);
}
