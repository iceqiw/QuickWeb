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
@Table(name="sys_role")
public class WebRole implements Serializable{
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
	
	/** name */
	@Column(name="name")
	private String name;
	
	/** remark */
	@Column(name="remark")
	private String remark;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_menu", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = @JoinColumn(name = "menuid"))
	private Set<WebMenu> menus = new HashSet<WebMenu>(0);
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Set<WebMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<WebMenu> menus) {
		this.menus = menus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}