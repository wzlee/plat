package com.eaglec.plat.dao.impl.wx;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eaglec.plat.dao.wx.BaseDao;

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
	
	@Override
	public Integer add(T o) {
		return (Integer)this.getCurrentSession().save(o);
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
		if (params != null && !params.isEmpty()){
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
		return list == null ? null : list;
	}

	@Override
	public List<T> findList(String hql, int start, int limit) {
		return this.findList(hql, null, start, limit);
	}

	@Override
	public List<T> findList(String hql, Map<String, Object> params, int start, int limit) {
		Query q = this.getCurrentSession().createQuery(hql).setFirstResult(start)
				.setMaxResults(limit);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		return list == null ? null : list;
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
		return obj != null ? (Long)obj : 0;
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

}
