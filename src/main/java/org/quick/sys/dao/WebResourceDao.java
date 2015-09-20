package org.quick.sys.dao;

import org.quick.sys.common.base.impl.GenericDao;
import org.quick.sys.model.WebResource;
import org.springframework.stereotype.Repository;


@Repository
public class WebResourceDao extends GenericDao<WebResource,String> {
	
	public WebResourceDao() {
		super(WebResource.class);
	}

}
