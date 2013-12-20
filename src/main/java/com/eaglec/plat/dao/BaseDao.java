package com.eaglec.plat.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

/**
 * 数据操作基类
 * @author Xiadi
 * @since 2013-8-12
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * 保存对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param o
	 *@return
	 */
	public abstract T save(T o);
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @param o 需要保存的对象
	 * @param obj 这个参数没什么用，只是为了来区别save这个方法的定义多态
	 * @description：保存对象，返回该添加的对象和添加后的id
	 */
	public Map<String, Object> save(T o, Object obj);

	/**
	 * @date: 2013-8-28
	 * @author：lwch
	 * @description：添加对象
	 */
	public int add(T o);
	/**
	 * 保存列表对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param its
	 *@return
	 */
	public abstract List<T> save(Iterable<T> its);

	/**
	 * 删除对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param o 目标对象
	 */
	public abstract void delete(T o);

	/**
	 * 根据id删除对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param id 对象id
	 */
	public abstract void delete(Serializable id);

	/**
	 * 删除列表对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param its 列表
	 */
	public abstract void delete(Iterable<T> its);

	/**
	 * 更新对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param o 目标对象
	 *@return T
	 */
	public abstract T update(T o);

	/**
	 * 保存或更新对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param o 目标对象
	 *@return T
	 */
	public abstract T saveOrUpdate(T o);

	/**
	 * 根据id查询实体对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param id
	 *@return T
	 */
	public abstract T get(Serializable id);

	/**
	 * 根据hql查询单一实体对象(不带参数注入)
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@return T
	 */
	public abstract T get(String hql);

	/**
	 * 根据hql查询单一实体对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@return T
	 */
	public abstract T get(String hql, Map<String, Object> params);

	/**
	 * 根据Hql查询Object列表(不带参数注入)
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@return List  
	 */
	public abstract List<Object> findObjects(String hql);
	
	/**
	 * 根据Hql查询实体列表(不带参数注入)
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@return List  
	 */
	public abstract List<T> findList(String hql);

	/**
	 * 根据Hql查询实体列表
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@return List  
	 */
	public abstract List<T> findList(String hql, Map<String, Object> params);

	/**
	 * 根据Hql分页查询实体列表(不带参数注入)
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@param start 开始行数
	 *@param limit 限定行数
	 *@return List  
	 */
	public abstract List<T> findList(String hql, int start, int limit);

	/**
	 * 根据Hql分页查询实体列表
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@param start 开始行数
	 *@param limit 限定行数
	 *@return List  
	 */
	public abstract List<T> findList(String hql, Map<String, Object> params,
			int start, int limit);

	/**
	 * 根据查询条件输出JSONData
	 *@author lizhiwei
	 *@since 2013-8-15 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@param start 开始行数
	 *@param limit 限定行数
	 *@return JSONData  
	 */
	public abstract JSONData<T> outJSONData(String hql,int start, int limit);
	
	/**
	 * 根据查询条件输出JSONData
	 *@author cs
	 *@since 2013-10-26 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@param start 开始行数
	 *@param limit 限定行数
	 *@return JSONData  
	 */
	public JSONData<Object> outJSONDataO(String hql, int start, int limit);
	
	/**
	 * 根据查询条件输出JSONRows
	 *@author pangyf
	 *@since 2013-9-7
	 *@param hql 查询语句
	 *@param page 开始行数
	 *@param rows 限定行数
	 *@return JSONRows  
	 */
	public abstract JSONRows<T> outJSONRows(String hql,int page, int rows);
	
	/**
	 * 根据查询条件输出JSONRows
	 *@author cs
	 *@since 2013-9-7
	 *@param hql 查询语句
	 *@param page 开始行数
	 *@param rows 限定行数
	 *@return JSONRows  
	 */
	public abstract JSONRows<Object> outJSONRowsO(String hql,int page, int rows);
	
	/**
	 * 返回该实体的所有总数
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@return Long 总数
	 */
	public abstract Long count();

	/**
	 * 根据hql查询返回符合条件的总数(不带Hql参数注入)
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句  如：select count(*) from User user
	 *@return Long 总数
	 */
	public abstract Long count(String hql);

	/**
	 * 根据hql查询返回符合条件的总数
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句 
	 *@param params 注入参数,如：<br/>
	 *		hql：select count(*) from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@return Long 总数
	 */
	public abstract Long count(String hql, Map<String, Object> params);

	/**
	 * 执行Hql语句
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 执行语句 
	 *@param params 注入参数,如：<br/>
	 *		hql：update User set sex = '女' where name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@return int 返回受影响行数
	 */
	public abstract int executeHql(String hql, Map<String, Object> params);	

	/**
	 * 根据Hql查询单一对象
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param hql 查询语句 
	 *@return Object 
	 */
	public abstract Object uniqueResult(String hql);

	/**
	 * 执行Sql语句
	 *@author Xiadi
	 *@since 2013-8-12 
	 *
	 *@param sql 执行语句 
	 *@return int 返回受影响行数
	 */
	public abstract int executeSql(String sql);
	
	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @description：执行sql语句的查询，比如获取某个字段的最大数……
	 */
	public Object executeSqlQuery(String sql);
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：执行sql语句的查询，返回某个实体数据集合
	 */
	public List<T> executeSqlQueryEntityList(String sql);
	
	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：执行sql语句的查询，返回Map格式的数据集合
	 */
	public List<?> executeSqlQueryMapList(String sql);

	/**
	 * @date: 2013-8-21
	 * @author：cs
	 * @description：执行sql语句的查询
	 */
	public List<T> executeSqlQuerys(String sql);

	/**
	 * 根据查询条件输出JSONData
	 *@author Xiadi
	 *@since 2013-8-28 
	 *
	 *@param hql 查询语句
	 *@param params 注入参数,如：<br/>
	 *		hql：from User user where user.name=:username and user.age=:age<br/> 
	 *		map：
	 *		map.put("username",args0);
	 *		map.put("age",args0);
	 *@param start 开始行数
	 *@param limit 限定行数
	 *@return JSONData  
	 */
	public JSONData<T> outJSONData(String hql, Map<String, Object> params, int start, int limit);

	/**
	 * 读取easyui准备的（同jsondata类似）
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 */
	JSONRows<T> outJSONRows(String hql, Map<String, Object> params, int page,int rows);
	
	/**
	 * 无参数执行Hql语句
	 * @author pangyf
	 * @since 2013-10-10
	 * @param hql
	 * @return 对象的list
	 */
	public abstract List<T> executeHql(String hql);


	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-28
	 * @description TODO actionPath eg:
	 * @param hql
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract List<T> executeListSql(String sql, int start, int limit);

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-28
	 * @description TODO actionPath eg:
	 * @param hql
	 * @return
	 */
	public abstract int executeListSql(String sql);
}
