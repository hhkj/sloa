package cn.slkj.sloa.Entity;

/**
 * 
 * @ClassName: Devices
 * @Description: 设备属性
 * @author maxuhui
 *
 */
public class Devices {

	private String id;
	private String listnum; // 设备编号
	private String firm; // 制造厂商
	private String model; // 型号
	private String rktime; // 入库时间开始
	private String rktime1; // 入库时间结束
	private int state; // 状态1已出库2未出库3退回
	private String fhtime; // 返回时间开始
	private String fhtime1; // 返回时间结束
	private String lyr; // 录入人
	private String lytime; // 录入时间开始
	private String lytime1; // 录入时间结束
	private int test; // 测试状态0未测试1已检测
	private int tresult; // 测试结果
	private String cstime; // 测试时间
	private String remark; // 备注
	private String pack; // 包装
	private String listNo; // 批量添加相同数字
	private int addType; // 添加类型
	private String listnum_begin; // 添加始数字
	private String listnum_end; // 添加末数字
	private String inspector; // 安检员
	/** 设备配件 **/
	private String gpszj; // 设备配件
	private String gpstx; //
	private String gsmtx; //
	private String dy; //
	private String fsd; //
	private String jsq; //
	private String sxy; //
	private String ysq; //
	private String mkf; //
	private String department; // 所在部门
	private String departmentName; // 部门名称
	private String simNumber; // SIM卡号
	private String networkNo; // 入网证明编号
	private String installers; // 安装人
	private String installtime; // 安装时间
	private String carNumber; // 车牌号
	private String companyId; // 所属公司
	private String companyName; // 所属公司
	private String packBm; // 包装编码

	private int ustate; // 使用状态1入网使用0未使用
	private String ustype; // 普货，新平台危货，老平台危货，北京平台，客车，旅游车
	private String receive;// 签收
	private String receiveTime;// 签收时间
	private String recipient;// 签收人

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getUstype() {
		return ustype;
	}

	public void setUstype(String ustype) {
		this.ustype = ustype;
	}

	public int getUstate() {
		return ustate;
	}

	public void setUstate(int ustate) {
		this.ustate = ustate;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListnum() {
		return listnum;
	}

	public void setListnum(String listnum) {
		this.listnum = listnum;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRktime() {
		return rktime;
	}

	public void setRktime(String rktime) {
		this.rktime = rktime;
	}

	public String getRktime1() {
		return rktime1;
	}

	public void setRktime1(String rktime1) {
		this.rktime1 = rktime1;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getFhtime() {
		return fhtime;
	}

	public void setFhtime(String fhtime) {
		this.fhtime = fhtime;
	}

	public String getFhtime1() {
		return fhtime1;
	}

	public void setFhtime1(String fhtime1) {
		this.fhtime1 = fhtime1;
	}

	public String getLyr() {
		return lyr;
	}

	public void setLyr(String lyr) {
		this.lyr = lyr;
	}

	public String getLytime() {
		return lytime;
	}

	public void setLytime(String lytime) {
		this.lytime = lytime;
	}

	public String getLytime1() {
		return lytime1;
	}

	public void setLytime1(String lytime1) {
		this.lytime1 = lytime1;
	}

	public int getTest() {
		return test;
	}

	public void setTest(int test) {
		this.test = test;
	}

	public int getTresult() {
		return tresult;
	}

	public void setTresult(int tresult) {
		this.tresult = tresult;
	}

	public String getCstime() {
		return cstime;
	}

	public void setCstime(String cstime) {
		this.cstime = cstime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
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

	public String getGpszj() {
		return gpszj;
	}

	public void setGpszj(String gpszj) {
		this.gpszj = gpszj;
	}

	public String getGpstx() {
		return gpstx;
	}

	public void setGpstx(String gpstx) {
		this.gpstx = gpstx;
	}

	public String getGsmtx() {
		return gsmtx;
	}

	public void setGsmtx(String gsmtx) {
		this.gsmtx = gsmtx;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	public String getFsd() {
		return fsd;
	}

	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	public String getJsq() {
		return jsq;
	}

	public void setJsq(String jsq) {
		this.jsq = jsq;
	}

	public String getSxy() {
		return sxy;
	}

	public void setSxy(String sxy) {
		this.sxy = sxy;
	}

	public String getYsq() {
		return ysq;
	}

	public void setYsq(String ysq) {
		this.ysq = ysq;
	}

	public String getMkf() {
		return mkf;
	}

	public void setMkf(String mkf) {
		this.mkf = mkf;
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

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}

	public String getNetworkNo() {
		return networkNo;
	}

	public void setNetworkNo(String networkNo) {
		this.networkNo = networkNo;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPackBm() {
		return packBm;
	}

	public void setPackBm(String packBm) {
		this.packBm = packBm;
	}

	@Override
	public String toString() {
		return "Devices [id=" + id + ", listnum=" + listnum + "" + ", firm="
				+ firm + ", model=" + model + "" + ", rktime=" + rktime
				+ ", rktime1=" + rktime1 + "" + ", state=" + state
				+ ", fhtime=" + fhtime + "" + ", fhtime1=" + fhtime1 + ", lyr="
				+ lyr + "" + ", lytime=" + lytime + ", lytime1=" + lytime1 + ""
				+ ", test=" + test + ", tresult=" + tresult + "" + ", cstime="
				+ cstime + ", remark=" + remark + "" + ", pack=" + pack
				+ ", addType=" + addType + "" + ", listnum_begin="
				+ listnum_begin + ", listnum_end=" + listnum_end + ""
				+ ", inspector=" + inspector + ", gpszj=" + gpszj + ""
				+ ", gpstx=" + gpstx + ", gsmtx=" + gsmtx + "" + ", dy=" + dy
				+ ", fsd=" + fsd + "" + ", jsq=" + jsq + ", sxy=" + sxy + ""
				+ ", ysq=" + ysq + ", mkf=" + mkf + "" + ", department="
				+ department + ", departmentName=" + departmentName + ""
				+ ", simNumber=" + simNumber + ", networkNo=" + networkNo + ""
				+ ", installers=" + installers + ", installtime=" + installtime
				+ "" + ", carNumber=" + carNumber + ", companyId=" + companyId
				+ "" + ", companyName=" + companyName + ", packBm=" + packBm
				+ "" + ", ustate=" + ustate + ", ustype=" + ustype + ""
				+ ", receive=" + receive + "" + ", receiveTime=" + receiveTime
				+ ", recipient=" + recipient + "]";
	}
}
