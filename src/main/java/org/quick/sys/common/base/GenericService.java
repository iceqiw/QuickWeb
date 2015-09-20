package org.quick.sys.common.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) managers
 * for your domain objects.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         jgarcia: added full text search + reindexing
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericService<T, PK extends Serializable> {

	List<T> getAll();

	T get(PK id);

	boolean exists(PK id);

	List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

	List<T> findByMap(Map<String, Object> queryParams);
	
	List<T> findByMapByLike(Map<String, Object> queryParams);

	List<T> find(String hql);

	List<T> find(String hql, Map<String, Object> params);

	List<T> find(String hql, Map<String, Object> params, int page, int rows);

	List<T> find(String hql, int page, int rows);

	BigDecimal sum(String sumkey, String hql, Map<String, Object> params);

	Long count(String orderhql, Map<String, Object> params);

	

}
