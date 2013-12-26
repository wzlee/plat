package com.eaglec.plat.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.eaglec.plat.dao.BaseDao;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> entityClass;

	public BaseDaoImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	SessionFactory sessionFactory;

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public T save(T o) {
		this.getCurrentSession().save(o);
		return o;
	}
	
	/**
	 * @date: 2013-11-13
	 * @author：lwch
	 * @param o 需要保存的对象
	 * @param obj 这个参数没什么用，只是为了来区别save这个方法的定义了多态而已
	 * @description：保存对象，返回该添加的对象和添加后的id
	 */
	@Override
	public Map<String, Object> save(T o, Object obj) {
		Map<String, Object> objMap = new HashMap<String, Object>();
		Serializable id = this.getCurrentSession().save(o);
		objMap.put("id", (Integer)id);
		objMap.put("class", o);
		return objMap;
	}

	@Override
	public int add(T o) {
		Serializable id = this.getCurrentSession().save(o);
		return (Integer) id;
	}

	@Override
	public List<T> save(Iterable<T> its) {
		List<T> result = new ArrayList<T>();
		for (T entity : its) {
			this.save(entity);
			result.add(entity);
		}
		return result;
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void delete(Serializable id) {
		this.delete(this.get(id));
	}

	@Override
	public void delete(Iterable<T> its) {
		for (T entity : its) {
			this.delete(entity);
		}
	}

	@Override
	public T update(T o) {
		this.getCurrentSession().update(o);
		return o;
	}

	@Override
	public T merge(T o) {
		this.getCurrentSession().merge(o);
		return o;
	}
	
	@Override
	public T saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
		return o;
	}

	@Override
	public T get(Serializable id) {
		return (T) this.getCurrentSession().get(entityClass, id);
	}

	@Override
	public T get(String hql) {
		return this.get(hql, null);
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		Object o = q.uniqueResult();
		return o == null ? null : (T) o;
	}

	@Override
	public List<Object> findObjects(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<T> findList(String hql) {
		return this.findList(hql, null);
	}

	@Override
	public List<T> findList(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		return list;
	}

	@Override
	public List<T> findList(String hql, int start, int limit) {
		return this.findList(hql, null, start, limit);
	}

	@Override
	public List<T> findList(String hql, Map<String, Object> params, int start, int limit) {
		Query q = this.getCurrentSession().createQuery(hql).setFirstResult(start).setMaxResults(limit);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		return list;
	}

	@Override
	public Long count() {
		return this.count("select count(*) from " + entityClass.getName());
	}

	@Override
	public Long count(String hql) {
		return this.count(hql, null);
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		Object obj = q.uniqueResult();
		return obj != null ? (Long) obj : 0;
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		if (params == null || params.isEmpty()){
			return this.getCurrentSession().createQuery(hql).executeUpdate();
		}
		Query q = this.getCurrentSession().createQuery(hql);
		for (String key : params.keySet()) {
			q.setParameter(key, params.get(key));
		}
		return q.executeUpdate();
	}

	@Override
	public Object uniqueResult(String hql) {
		return this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public int executeSql(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @description：执行sql语句的查询，比如获取某个字段的最大数……
	 */
	@Override
	public Object executeSqlQuery(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：执行sql语句的查询，返回某个实体数据集合
	 */
	@Override
	public List<T> executeSqlQueryEntityList(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).addEntity(entityClass).list();
	}

	/**
	 * @date: 2013-8-24
	 * @author：lwch
	 * @description：执行sql语句的查询，返回Map格式的数据集合
	 */
	@Override
	public List<?> executeSqlQueryMapList(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * @date: 2013-8-21
	 * @author：lwch
	 * @description：执行sql语句的查询，比如获取某个字段的最大数……
	 */
	@Override
	public List<T> executeSqlQuerys(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).addEntity(entityClass).list();
	}

	@Override
	public JSONData<T> outJSONData(String hql, int start, int limit) {
		Query query = this.getCurrentSession().createQuery(hql);
		int total = query.list().size();
		List<T> list = query.setFirstResult(start).setMaxResults(limit).list();
		return new JSONData<T>(true, list, total);
	}
	
	/**
	 * @author cs
	 * 返回object
	 */
	@Override
	public JSONData<Object> outJSONDataO(String hql, int start, int limit) {
		Query query = this.getCurrentSession().createQuery(hql);
		int total = query.list().size();
		List<Object> list = query.setFirstResult(start).setMaxResults(limit).list();
		return new JSONData<Object>(true, list, total);
	}

	/**
	 * 根据查询条件输出JSONRows
	 * @author pangyf
	 * @since 2013-9-7
	 * @param hql  查询语句
	 * @param page 开始行数
	 * @param rows 限定行数
	 * @return JSONRows
	 */
	@Override
	public JSONRows<T> outJSONRows(String hql, int page, int rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		int total = query.list().size();
		List<T> list = query.setFirstResult(page).setMaxResults(rows).list();
		return new JSONRows<T>(true, list, total);
	}
	
	/**
	 * @author cs
	 * 返回object
	 */
	@Override
	public JSONRows<Object> outJSONRowsO(String hql, int page, int rows) {
		List<Object> list = new ArrayList<Object>();
		Query query = this.getCurrentSession().createQuery(hql);
		int total = query.list().size();
		if( page==0 && rows==0){
			list = query.list();
		}else{
			list = query.setFirstResult(page).setMaxResults(rows).list();
		}
		return new JSONRows<Object>(true, list, total);
	}

	@Override
	public JSONData<T> outJSONData(String hql, Map<String, Object> params,
			int start, int limit) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		int total = query.list().size();
		List<T> list = query.setFirstResult(start).setMaxResults(limit).list();
		return new JSONData<T>(true, list, total);
	}

	@Override
	public JSONRows<T> outJSONRows(String hql, Map<String, Object> params,
			int page, int rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		int total = query.list().size();
		List<T> list = query.setFirstResult(page).setMaxResults(rows).list();
		return new JSONRows<T>(true, list, total);
	}

	@Override
	public List<T> executeHql(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<T> executeListSql(String sql, int start, int limit) {
		sql += " limit " + start + "," + limit;
		return this.getCurrentSession().createSQLQuery(sql)
				.addEntity(entityClass).list();
	}

	@Override
	public int executeListSql(String sql) {
		List<T> list = this.getCurrentSession().createSQLQuery(sql)
				.addEntity(entityClass).list();
		return list.size();
	}
}
