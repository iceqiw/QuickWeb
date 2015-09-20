package org.quick.sys.model;
 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_user")
public class WebUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** id */
	@Id 
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="id")
	private String id;
	
	/** loginno */
	@Column(name="loginno")
	private String loginno;
	
	/** password */
	@Column(name="password")
	private String password;
	
	/** username */
	@Column(name="username")
	private String username;
	
	/** organcode */
	@Column(name="organcode")
	private String organcode;
	
	/** sex */
	@Column(name="sex")
	private String sex;
	
	/** tel */
	@Column(name="tel")
	private String tel;
	
	/** status */
	@Column(name="status")
	private String status;
	
	/** remark */
	@Column(name="remark")
	private String remark;
	
	/** checktime */
	@Column(name="checktime")
	private String checktime;
	
	/** lpctime */
	@Column(name="lpctime")
	private String lpctime;
	
	/** pwdstatus */
	@Column(name="pwdstatus")
	private String pwdstatus;
	
	/** loginflag */
	@Column(name="loginflag")
	private String loginflag;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<WebRole> roles = new HashSet<WebRole>(0);
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getLoginno() {
		return loginno;
	}

	public void setLoginno(String loginno) {
		this.loginno = loginno;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrgancode() {
		return organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getLpctime() {
		return lpctime;
	}

	public void setLpctime(String lpctime) {
		this.lpctime = lpctime;
	}
	public String getPwdstatus() {
		return pwdstatus;
	}

	public void setPwdstatus(String pwdstatus) {
		this.pwdstatus = pwdstatus;
	}
	public String getLoginflag() {
		return loginflag;
	}

	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}
	public Set<WebRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<WebRole> roles) {
		this.roles = roles;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}