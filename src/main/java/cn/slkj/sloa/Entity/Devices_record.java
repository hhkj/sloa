package cn.slkj.sloa.Entity;

/**
 * 
* @ClassName: Devices_record 
* @Description:  设备安装记录
* @author maxuhui
*
 */
public class Devices_record {
	private String devices_id;//设备id
	private String site;//库存网点
	private String cktime;//出库时间
	private String lydepartment;//领用部门
	private String departmentname;//领用部门
	private String lyr;//领用人
	private String qsstate;//签收状态 0未签收 1已签收
	private String qsr;//签收人
	private String qstime;//签收时间	
	private String ustate;//使用状态  -1退回  1出库
	private String remark;//备注
	private String type_id;//记录类型 1设备 2SIM卡 3证明
	public String getDevices_id() {
		return devices_id;
	}
	public void setDevices_id(String devices_id) {
		this.devices_id = devices_id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCktime() {
		return cktime;
	}
	public void setCktime(String cktime) {
		this.cktime = cktime;
	}
	public String getLydepartment() {
		return lydepartment;
	}
	public void setLydepartment(String lydepartment) {
		this.lydepartment = lydepartment;
	}
	public String getLyr() {
		return lyr;
	}
	public void setLyr(String lyr) {
		this.lyr = lyr;
	}
	
	public String getQsstate() {
		return qsstate;
	}
	public void setQsstate(String qsstate) {
		this.qsstate = qsstate;
	}
	public String getQsr() {
		return qsr;
	}
	public void setQsr(String qsr) {
		this.qsr = qsr;
	}
	public String getQstime() {
		return qstime;
	}
	public void setQstime(String qstime) {
		this.qstime = qstime;
	}
	
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String ustate) {
		this.ustate = ustate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	
	
}
