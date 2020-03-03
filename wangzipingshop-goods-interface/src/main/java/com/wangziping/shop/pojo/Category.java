package com.wangziping.shop.pojo;

import java.io.Serializable;

/**
 * @ClassName: Category
 * @Description: 类型
 * @author: wangziping
 * @date: 2020年3月3日 上午9:03:51
 */
public class Category implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 2150761973088850675L;

	private Integer id;
	private Integer parentId; // 上一级别分类的id
	private String name; // 分类名称
	private String path; // 从根分类到当前分类的路径

	public Category() {
		super();
	}

	public Category(Integer id, Integer parentId, String name, String path) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", name=" + name + ", path=" + path + "]";
	}

}
