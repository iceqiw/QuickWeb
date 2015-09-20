package org.quick.sys.common.base.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;




import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;


public class GenericDao<T, PK extends Serializable> {

	protected final Log log = LogFactory.getLog(getClass());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Class<T> persistentClass;

	public GenericDao(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		Session sess = null;
		try {
			sess = getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			log.warn("当前无session----------------------------" +e.getMessage());
		}
		if (sess == null) {
			log.info("新增session-------------------------");
			sess = getSessionFactory().openSession();
		}
		return sess;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Session sess = getSession();
		return sess.createCriteria(persistentClass).list();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> getAllDistinct() {
		Collection<T> result = new LinkedHashSet<T>(getAll());
		return new ArrayList<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);

		if (entity == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '"
					+ id + "' not found...");
			throw new ObjectRetrievalFailureException(this.persistentClass, id);
		}

		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T save(T object) {
		Session sess = getSession();
		return (T) sess.merge(object);
	}

	public void saveOrUpdate(T o) {
		Session sess = getSession();
		sess.saveOrUpdate(o);
	}

	public void update(T object) {
		Session sess = getSession();
		sess.update(object);
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(T object) {
		Session sess = getSession();
		sess.delete(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void remove(PK id) {
		Session sess = getSession();
		IdentifierLoadAccess byId = sess.byId(persistentClass);
		T entity = (T) byId.load(id);
		sess.delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		Session sess = getSession();
		Query namedQuery = sess.getNamedQuery(queryName);

		for (String s : queryParams.keySet()) {
			namedQuery.setParameter(s, queryParams.get(s));
		}

		return namedQuery.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByMapByLike(Map<String, Object> queryParams) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		if (queryParams != null) {
			Set<String> keys = queryParams.keySet();
			for (String key : keys) {
				cr.add(Restrictions.like(key, queryParams.get(key)));
			}

		}
		return cr.list();

	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByMapByEq(Map<String, Object> queryParams) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		if (queryParams != null) {
			Set<String> keys = queryParams.keySet();
			for (String key : keys) {
				cr.add(Restrictions.eq(key, queryParams.get(key)));
			}

		}
		return cr.list();

	}

	/**
	 * 根据字段值地查询
	 * @param entityClass 实体原始类型
	 * @param propertyName 属性名称 
	 * @param value 值 
	 * @return 实体类对象

	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName,Object value) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		cr.add(Restrictions.like(propertyName, value));
		return cr.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Map<String, Object> params, int page,int rows) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				cr.add(Restrictions.eq(key,  params.get(key)));
			}
		}
		cr.setMaxResults(rows);
		cr.setFirstResult((page - 1) * rows);
		return cr.list();
	}
	
	public Long countByProperty(Map<String, Object> params) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				cr.add(Restrictions.eq(key,  params.get(key)));
			}
		}
		return (Long) cr.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Map<String, Object> params) {
		Session sess = getSession();
		Criteria cr = sess.createCriteria(persistentClass);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				cr.add(Restrictions.eq(key,  params.get(key)));
			}
		}
		return cr.list();
	}
	

	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		Query q = getSession().createQuery(hql);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findToPage(String hql, Map<String, Object> params, int start,
			int rows) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult(start).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, int page, int rows) {
		Query q = getSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public Long count(String hql) {
		if(hql.startsWith("from")||hql.startsWith("FROM")){
			hql="SELECT COUNT(*) "+hql;
		}
		Query q = getSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		if(hql.startsWith("from")||hql.startsWith("FROM")){
			hql="SELECT COUNT(*) "+hql;
		}
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	public BigDecimal sum(String sumkey,String hql, Map<String, Object> params) {
		if(hql.startsWith("from")||hql.startsWith("FROM")){
			hql="SELECT SUM("+sumkey+") "+hql;
		}
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigDecimal) q.uniqueResult();
	}
	public int executeHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	public int executeHql(String hql, Map<String, Object> params) {
		Query q = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}
	
	public String findBySqlSeq(String cmdStr) {
		log.debug("getting with command: " + cmdStr);
		Session sess = getSession();
		try {
			SQLQuery query = sess.createSQLQuery(cmdStr);
			return  query.list().get(0).toString();
		} catch (RuntimeException re) {
			log.error("getting failed", re);
			throw re;
		} 
	}
	
	@SuppressWarnings("rawtypes")
	public List findBySql(String cmdStr) {
		log.debug("getting with command: " + cmdStr);
		Session sess = getSession();
		try {
			SQLQuery query = sess.createSQLQuery(cmdStr);
			return  query.list();
		} catch (RuntimeException re) {
			log.error("getting failed", re);
			throw re;
		} 
	}
}
