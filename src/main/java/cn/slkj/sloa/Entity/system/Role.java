package cn.slkj.sloa.Entity.system;

import java.io.Serializable;

/**
 * @ClassName: Role
 * @Description: 角色
 * @author maxh
 * 
 */
public class Role implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * guid
	 */
	private String id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String dscript;
	/**
	 * 排序
	 */
	private int sort;

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

	public String getDscript() {
		return dscript;
	}

	public void setDscript(String dscript) {
		this.dscript = dscript;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", dscript=" + dscript + ", sort=" + sort + "]";
	}

}
