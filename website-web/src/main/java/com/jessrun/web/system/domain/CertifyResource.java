package com.jessrun.web.system.domain;

import java.io.Serializable;

/**
 * @className CertifyResource
 * @depiction 资源表
 * @createTime 2013-5-30
 * @author huanko
 */
public class CertifyResource implements Serializable{

	private static final long serialVersionUID = -8365710713601496264L;
	private Integer 			id;
	private String 				name;
	private Integer 			type;
	private String				val;
	private Integer 			parentId;
	private Integer 			sort;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
