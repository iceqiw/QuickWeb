package org.quick.sys.dao;


import java.util.List;

import org.quick.sys.common.base.impl.GenericDao;
import org.quick.sys.model.WebUser;
import org.springframework.stereotype.Repository;


@Repository
public class WebUserDao extends GenericDao<WebUser,String> {
	
	public WebUserDao() {
		super(WebUser.class);
	}

	public WebUser loadUserByloginNo(String loginno) throws Exception{
		List<WebUser> users=this.find("from WebUser where loginno='"+loginno+"'");
		return users.get(0);
	}
}
