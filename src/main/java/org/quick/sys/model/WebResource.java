package org.quick.sys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table; 

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name="sys_resource")
public class WebResource  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** id */
	@Id 
	@Column(name="id")
	private String id;
	
	/** name */
	@Column(name="name")
	private String name;
	
	/** type */
	@Column(name="type")
	private String type;
	
	/** menuid */
	@Column(name="menuid")
	private String menuid;
	
	/** url */
	@Column(name="url")
	private String url;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}