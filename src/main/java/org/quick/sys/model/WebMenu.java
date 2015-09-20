package org.quick.sys.model; 
 
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table; 

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name="sys_menu")
public class WebMenu implements Serializable{
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
	
	/** type */
	@Column(name="type")
	private Long type;
	
	/** parentid */
	@Column(name="parentid")
	private String parentid;
	
	/** sort */
	@Column(name="sort")
	private Long sort;
	
	/** icon */
	@Column(name="icon")
	private String icon;
	
	/** url */
	@Column(name="url")
	private String url;
	
   @OneToMany(mappedBy = "menuid")
   private  Set<WebResource> resources= new HashSet<WebResource>(0);
   
   
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
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public Set<WebResource> getResources() {
		return resources;
	}

	public void setResources(Set<WebResource> resources) {
		this.resources = resources;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}