package org.quick.sys.dao;


import org.quick.sys.common.base.impl.GenericDao;
import org.quick.sys.model.WebMenu;
import org.springframework.stereotype.Repository;


@Repository
public class WebMenuDao extends GenericDao<WebMenu,String> {
	
	public WebMenuDao() {
		super(WebMenu.class);
	}

}
