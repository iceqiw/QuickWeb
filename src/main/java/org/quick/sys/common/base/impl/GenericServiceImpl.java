package org.quick.sys.common.base.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quick.sys.common.base.GenericService;

public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {

	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of child classes
	 */
	protected GenericDao<T, PK> dao;

	private Class<T> persistentClass;

	public GenericServiceImpl() {
	}

	public GenericServiceImpl(GenericDao<T, PK> genericDao, final Class<T> persistentClass) {
		this.dao = genericDao;
		this.persistentClass = persistentClass;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Override
	public List<T> getAll() {
		return dao.getAll();
	}

	@Override
	public T get(PK id) {
		return dao.get(id);
	}

	@Override
	public boolean exists(PK id) {
		return dao.exists(id);
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
		return dao.findByNamedQuery(queryName, queryParams);
	}

	@Override
	public List<T> findByMap(Map<String, Object> queryParams) {
		return dao.findByMapByEq(queryParams);
	}
	
	@Override
	public List<T> findByMapByLike(Map<String, Object> queryParams) {
		return dao.findByMapByLike(queryParams);
	}

	@Override
	public List<T> find(String hql) {
		return dao.find(hql);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		return dao.find(hql, params);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		return dao.find(hql, params, page, rows);
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		return dao.find(hql, page, rows);
	}

	@Override
	public BigDecimal sum(String sumkey, String orderhql, Map<String, Object> params) {
		String hql = "from " + persistentClass.getSimpleName();
		hql += " where 1=1";
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				hql += " and " + key + "= :" + key;
			}
		}
		if (StringUtils.isNotBlank(orderhql)) {
			hql += orderhql;
		}
		return dao.sum(sumkey, hql, params);
	}

	@Override
	public Long count(String orderhql, Map<String, Object> params) {
		String hql = "from " + persistentClass.getSimpleName();
		hql += " where 1=1";
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				hql += " and " + key + "= :" + key;
			}
		}
		if (StringUtils.isNotBlank(orderhql)) {
			hql += orderhql;
		}
		return dao.count(hql, params);
	}

}
