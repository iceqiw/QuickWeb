package org.quick.sys.dao;


import org.quick.sys.common.base.impl.GenericDao;
import org.quick.sys.model.WebRole;
import org.springframework.stereotype.Repository;


@Repository
public class WebRoleDao extends GenericDao<WebRole,String> {
	
	public WebRoleDao() {
		super(WebRole.class);
	}

}
