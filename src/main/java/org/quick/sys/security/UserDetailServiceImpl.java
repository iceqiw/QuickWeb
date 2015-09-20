package org.quick.sys.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quick.sys.dao.WebUserDao;
import org.quick.sys.model.WebMenu;
import org.quick.sys.model.WebResource;
import org.quick.sys.model.WebRole;
import org.quick.sys.model.WebUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserDetailServiceImpl.class);

	private WebUserDao webUserDao;

	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loginno is " + username);
		// 这里应该可以不用再查了
		WebUser user = null;
		try {
			user = webUserDao.loadUserByloginNo(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("用户[" + username + "]不存在");
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		boolean enables = true;// 帐号是否启用
		if (user.getStatus().equals("1")) {
			enables = false;
		}
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		// 封装成spring security的UserDetails的扩展类UserInfo
		// User user = new User(users.getUsername(), users.getPwd(), enables,
		// accountNonExpired, credentialsNonExpired, accountNonLocked,
		// grantedAuths);
		UserInfo userdetail = new UserInfo(user.getLoginno(), user.getPassword(), enables, accountNonExpired,
				credentialsNonExpired, accountNonLocked, grantedAuths);
		// 存入自定义扩展属性
		BeanUtils.copyProperties(user, userdetail);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(WebUser user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (WebRole role : user.getRoles())
			for (WebMenu menu : role.getMenus())
				for (WebResource res : menu.getResources())
					if (StringUtils.isNotBlank(res.getUrl()))
						authSet.add(new SimpleGrantedAuthority("AUTH_" + res.getId()));
		return authSet;
	}

	public WebUserDao getWebUserDao() {
		return webUserDao;
	}

	public void setWebUserDao(WebUserDao webUserDao) {
		this.webUserDao = webUserDao;
	}

}
