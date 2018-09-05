package cn.slkj.sloa.Entity.stock;

public class Prove {
	private String id;
	private String number; // 证明编号
	private String ustype; // 使用类型
	private String usman; // 领用人
	private String usriqi; // 领用or出库时间
	private String wangjian; // 网监员
	private String inspector; // 安装人
	private String riqi; // 测试日期
	private int state; // 出库状态
	private int ustate; // 使用状态
	private String remark; // 备注
	private String listNo; // 批量添加相同数字
	private int addType; // 添加类型
	private String listnum_begin; // 添加始数字
	private String listnum_end; // 添加末数字
	private String uid; // 操作人

	private String purpose; // 用途
	private String carNumber; // 车牌号
	private String department; // 所在部门编码
	private String departmentName; // 所在部门名称

	private float colAmounts; // 代收使用费
	private float amounts; // 使用费
	private String startTime; // 服务开始时间
	private String endTime; // 服务结束时间
	private String payee; // 收款人
	private int fees; // 收款状态
	private String imgPath; // 证明照片

	private String owner;// 业户名称
	private String ownerId;// 业户名称

	private String recycleTime;// 回收时间

	public String getRecycleTime() {
		return recycleTime;
	}

	public void setRecycleTime(String recycleTime) {
		this.recycleTime = recycleTime;
	}

	public float getColAmounts() {
		return colAmounts;
	}

	public void setColAmounts(float colAmounts) {
		this.colAmounts = colAmounts;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUstype() {
		return ustype;
	}

	public void setUstype(String ustype) {
		this.ustype = ustype;
	}

	public String getUsman() {
		return usman;
	}

	public void setUsman(String usman) {
		this.usman = usman;
	}

	public String getUsriqi() {
		return usriqi;
	}

	public void setUsriqi(String usriqi) {
		this.usriqi = usriqi;
	}

	public String getWangjian() {
		return wangjian;
	}

	public void setWangjian(String wangjian) {
		this.wangjian = wangjian;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public int getAddType() {
		return addType;
	}

	public void setAddType(int addType) {
		this.addType = addType;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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

	public float getAmounts() {
		return amounts;
	}

	public void setAmounts(float amounts) {
		this.amounts = amounts;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Prove [id=" + id + ", number=" + number + "" + ", ustype="
				+ ustype + ", usman=" + usman + "" + ", usriqi=" + usriqi
				+ ", wangjian=" + wangjian + "" + ", inspector=" + inspector
				+ ", riqi=" + riqi + "" + ", state=" + state + ", ustate="
				+ ustate + "" + ", remark=" + remark + ", listNo=" + listNo
				+ "" + ", addType=" + addType + ", listnum_begin="
				+ listnum_begin + "" + ", listnum_end=" + listnum_end
				+ ", uid=" + uid + "" + ", purpose=" + purpose + ", carNumber="
				+ carNumber + "" + ", department=" + department
				+ ", departmentName=" + departmentName + "" + ", colAmounts="
				+ colAmounts + ", amounts=" + amounts + "" + ", startTime="
				+ startTime + ", endTime=" + endTime + "" + ", payee=" + payee
				+ ", fees=" + fees + "" + ", imgPath=" + imgPath + ", owner="
				+ owner + "" + ", ownerId=" + ownerId + ", recycleTime="
				+ recycleTime + "]";
	}
}
