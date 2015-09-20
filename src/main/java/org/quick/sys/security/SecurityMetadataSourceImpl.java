package org.quick.sys.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quick.sys.dao.WebResourceDao;
import org.quick.sys.model.WebResource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


//1 加载资源与权限的对应关系
/**
 * @author qiwei
 *
 */
/**
 * @author qiwei
 *
 */
public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
	/**
	 * Logger for this class
	 */
	private static final  Log logger = LogFactory.getLog(SecurityMetadataSourceImpl.class);
	
	private WebResourceDao webResourceDao;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<WebResource> resources = this.webResourceDao.getAll();
			for (WebResource resource : resources) {
				if (resource.getUrl() != null && !resource.getUrl().equals("")) {
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					// 以权限名封装为Spring的security Object
					ConfigAttribute configAttribute = new SecurityConfig("AUTH_" + resource.getId());
					configAttributes.add(configAttribute);
					resourceMap.put("/" + resource.getUrl(), configAttributes);
				}
			}
		}

		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
		@SuppressWarnings("unused")
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();

	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.info("requestUrl is ~~~-------------------------------------------" + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		return resourceMap.get(requestUrl);
	}

	public WebResourceDao getWebResourceDao() {
		return webResourceDao;
	}

	public void setWebResourceDao(WebResourceDao webResourceDao) {
		this.webResourceDao = webResourceDao;
	}
}
