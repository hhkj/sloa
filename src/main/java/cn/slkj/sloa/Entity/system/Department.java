package cn.slkj.sloa.Entity.system;

import java.util.List;

/**
 * 
 * @ClassName: Department
 * @Description: 部门管理
 * @author maxuhui
 *
 */

public class Department {

	private String	id;
	/**
	 * 编码
	 */
	private String	d_code;
	/**
	 * 部门 名 称
	 */
	private String	d_name;
	/**
	 * 父id
	 */
	private String	parentid;

	/**
	 * 上级机构名称
	 */
	private String	parentname;
	/**
	 * 机构类型
	 */
	private String	d_type;
	/**
	 * 图标
	 */
	private String	d_icon;
	/**
	 * 负责人
	 */
	private String	d_fuzeren;
	/**
	 * 联 系 电 话：
	 */
	private String	d_tel;

	/**
	 * 传真
	 */
	private String	d_fax;
	/**
	 * 地址
	 */
	private String	d_add;
	/**
	 * email
	 */
	private String	d_email;
	/**
	 * 描述
	 */
	private String	d_miaoshu;
	/**
	 * 排序
	 */
	private String	d_order;
	
	private String state;
	private List<Department> children;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getD_type() {
		return d_type;
	}

	public void setD_type(String d_type) {
		this.d_type = d_type;
	}

	public String getD_icon() {
		return d_icon;
	}

	public void setD_icon(String d_icon) {
		this.d_icon = d_icon;
	}

	public String getD_fuzeren() {
		return d_fuzeren;
	}

	public void setD_fuzeren(String d_fuzeren) {
		this.d_fuzeren = d_fuzeren;
	}

	public String getD_tel() {
		return d_tel;
	}

	public void setD_tel(String d_tel) {
		this.d_tel = d_tel;
	}

	public String getD_fax() {
		return d_fax;
	}

	public void setD_fax(String d_fax) {
		this.d_fax = d_fax;
	}

	public String getD_add() {
		return d_add;
	}

	public void setD_add(String d_add) {
		this.d_add = d_add;
	}

	public String getD_email() {
		return d_email;
	}

	public void setD_email(String d_email) {
		this.d_email = d_email;
	}

	public String getD_miaoshu() {
		return d_miaoshu;
	}

	public void setD_miaoshu(String d_miaoshu) {
		this.d_miaoshu = d_miaoshu;
	}

	public String getD_order() {
		return d_order;
	}

	public void setD_order(String d_order) {
		this.d_order = d_order;
	}

	public String getD_code() {
		return d_code;
	}

	public void setD_code(String d_code) {
		this.d_code = d_code;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", d_code=" + d_code + ", d_name=" + d_name + ", parentid="
				+ parentid + ", parentname=" + parentname + ", d_type=" + d_type + ", d_icon="
				+ d_icon + ", d_fuzeren=" + d_fuzeren + ", d_tel=" + d_tel + ", d_fax=" + d_fax
				+ ", d_order=" + d_order + "]";
	}

}
