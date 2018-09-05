package cn.slkj.sloa.Entity.stock;

/**
 * 
 * @ClassName: Sim
 * @Description: SIM卡信息
 * @author maxuhui
 *
 */
public class Sim {

	private String id;
	/**
	 * 运营商
	 */
	private String cardType;//
	/**
	 * 序列号
	 */
	private String listnum;//
	/**
	 * SIM卡号
	 */
	private String telnum;//
	/**
	 * 状态 1= 已用 else 未用
	 */
	private int state;// 状态 value = 1 表示已用 else 未用
	private int ustate;// 状态 value = 1 表示 出库 else 未出库
	private String intime;// 入库时间
	private String outtime;// 出库时间
	private String kktime;// 开卡日期
	private String fhtime;// 返回时间
	private String renewtime;// 交费时间
	private String business;// 金额
	private String beizhu;// 备注
	private String gys;// 供应商
	private String lyr;// 领用人
	private String department;// 领用部门
	private String departmentName;// 领用部门
	private String lrr;// 操作人
	private String installers;// 安装人
	private String installtime;// 安装时间
	private String carNumber;// 车牌号
	private String companyId;// 所属公司
	private String companyName;// 所属公司
	private String remark;//
	private int addType; // 添加类型
	private String listNo; // 批量添加相同数字
	private String listnum_begin; // 添加始数字
	private String listnum_end; // 添加末数字

	public int getAddType() {
		return addType;
	}

	public void setAddType(int addType) {
		this.addType = addType;
	}

	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public String getListnum_begin() {
		return listnum_begin;
	}

	public void setListnum_begin(String listnum_begin) {
		this.listnum_begin = listnum_begin;
	}

	public String getListnum_end() {
		return listnum_end;
	}

	public void setListnum_end(String listnum_end) {
		this.listnum_end = listnum_end;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInstallers() {
		return installers;
	}

	public void setInstallers(String installers) {
		this.installers = installers;
	}

	public String getInstalltime() {
		return installtime;
	}

	public void setInstalltime(String installtime) {
		this.installtime = installtime;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getListnum() {
		return listnum;
	}

	public void setListnum(String listnum) {
		this.listnum = listnum;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	public String getKktime() {
		return kktime;
	}

	public void setKktime(String kktime) {
		this.kktime = kktime;
	}

	public String getFhtime() {
		return fhtime;
	}

	public void setFhtime(String fhtime) {
		this.fhtime = fhtime;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}

	public String getGys() {
		return gys;
	}

	public void setGys(String gys) {
		this.gys = gys;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getRenewtime() {
		return renewtime;
	}

	public void setRenewtime(String renewtime) {
		this.renewtime = renewtime;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Sim [id=" + id + ", cardType=" + cardType + "" + ", listnum="
				+ listnum + ", telnum=" + telnum + "" + ", state=" + state
				+ ", ustate=" + ustate + "" + ", intime=" + intime
				+ ", outtime=" + outtime + "" + ", kktime=" + kktime
				+ ", fhtime=" + fhtime + "" + ", renewtime=" + renewtime
				+ ", business=" + business + "" + ", beizhu=" + beizhu
				+ ", gys=" + gys + "" + ", lyr=" + lyr + ", department="
				+ department + "" + ", departmentName=" + departmentName
				+ ", lrr=" + lrr + "" + ", installers=" + installers
				+ ", installtime=" + installtime + "" + ", carNumber="
				+ carNumber + ", companyId=" + companyId + ""
				+ ", companyName=" + companyName + ", remark=" + remark + ""
				+ ", addType=" + addType + ", listNo=" + listNo + ""
				+ ", listnum_begin=" + listnum_begin + ", listnum_end="
				+ listnum_end + "]";
	}

}
