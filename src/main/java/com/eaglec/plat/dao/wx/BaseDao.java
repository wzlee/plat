package com.eaglec.plat.dao.wx;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao<T> {

	public abstract T save(T o);
	
	public abstract Integer add(T o);

	public abstract List<T> save(Iterable<T> its);

	public abstract void delete(T o);

	public abstract void delete(Serializable id);

	public abstract void delete(Iterable<T> its);

	public abstract T update(T o);

	public abstract T saveOrUpdate(T o);

	public abstract T get(Serializable id);

	public abstract T get(String hql);

	public abstract T get(String hql, Map<String, Object> params);

	public abstract List<Object> findObjects(String hql);

	public abstract List<T> findList(String hql);

	public abstract List<T> findList(String hql, Map<String, Object> params);

	public abstract List<T> findList(String hql, int start, int limit);

	public abstract List<T> findList(String hql, Map<String, Object> params, int start, int limit);

	public abstract Long count();

	public abstract Long count(String hql);

	public abstract Long count(String hql, Map<String, Object> params);

	public abstract int executeHql(String hql, Map<String, Object> params);

	public abstract Object uniqueResult(String hql);

	public abstract int executeSql(String sql);
	
	public Object executeSqlQuery(String sql);

}