package cn.slkj.sloa.Entity;

/**
 * 
 * @ClassName: Vehicle
 * @Description: 车辆基本属性
 * @author maxuhui
 *
 */
public class Vehicle {

	private String id;
	private String companyId; // 所属公司id
	private String companyName; // 所属公司名称
	private String carOwner; // 业户/车主
	private String contacts; // 联系人
	private String contactsTel; // 联系方式
	private String carNumber; // 车牌号(主)
	private String plateColor; // 车牌颜色
	private String issuingUnit;// 发证机关
	private String retDate;// 登记时间
	private String carType; // 车辆类型
	private String carBrand; // 车辆品牌
	private String carModel; // 辆型号
	private String carColor; // 车身颜色
	private String carVin; // 车辆识别代号/车架号
	private String made; // 国产/进口
	private String carEngNum; // 发动机号
	private String carEngModel; // 发动机型号
	private String carFuelType; // 燃料种类
	private String power; // 功率排量
	private String factory; // 制造厂
	private String steering;// 转向方式
	private String wheelTrack;// 轮距
	private String tyreNumber;// 轮胎数
	private String tyreModel;// 轮胎规格
	private String springNumber;// 钢板弹簧片数
	private String wheelBase;// 轴距
	private String axleNumber;// 轴数
	private String carOutLength; // 外廓尺寸(长)
	private String carOutWidth; // 外廓尺寸(宽)
	private String carOutHeight; // 外廓尺寸(高)
	private String carContLength; // 货箱内部尺寸(长)
	private String carContWidth; // 货箱内部尺寸(宽)
	private String carContHeight; // 货箱内部尺寸(高)
	private String carTotalmass; // 总质量
	private String carApproved; // 核定载质量(吨位/座位)
	private String carApprGuest; // 核定载客
	private String carTrac; // 准牵引总质量
	private String cabGuest;// 驾驶室载客
	private String useCharacter;// 使用性质
	private String access;// 车辆获取方式
	private String produce;// 出厂日期
	private String equitment; // 设备号
	private String simNumber; // sim 卡号
	private String installers; // 安装人
	private String installtime; // 安装日期
	private String networkNo; // 入网证明编号
	private String feeseEnd; // 服务到期时间
	private int uprice;// 收费单价 例如1000/年
	private int depId;// 部门ID
	private String seasonExamS; // 季审日期
	private String seasonExamE; // 季审到期时间
	private String yearExamS; // 年审日期
	private String yearExamE; // 年审到期时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsTel() {
		return contactsTel;
	}

	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public String getIssuingUnit() {
		return issuingUnit;
	}

	public void setIssuingUnit(String issuingUnit) {
		this.issuingUnit = issuingUnit;
	}

	public String getRetDate() {
		return retDate;
	}

	public void setRetDate(String retDate) {
		this.retDate = retDate;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public String getCarVin() {
		return carVin;
	}

	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}

	public String getMade() {
		return made;
	}

	public void setMade(String made) {
		this.made = made;
	}

	public String getCarEngNum() {
		return carEngNum;
	}

	public void setCarEngNum(String carEngNum) {
		this.carEngNum = carEngNum;
	}

	public String getCarEngModel() {
		return carEngModel;
	}

	public void setCarEngModel(String carEngModel) {
		this.carEngModel = carEngModel;
	}

	public String getCarFuelType() {
		return carFuelType;
	}

	public void setCarFuelType(String carFuelType) {
		this.carFuelType = carFuelType;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getSteering() {
		return steering;
	}

	public void setSteering(String steering) {
		this.steering = steering;
	}

	public String getWheelTrack() {
		return wheelTrack;
	}

	public void setWheelTrack(String wheelTrack) {
		this.wheelTrack = wheelTrack;
	}

	public String getTyreNumber() {
		return tyreNumber;
	}

	public void setTyreNumber(String tyreNumber) {
		this.tyreNumber = tyreNumber;
	}

	public String getTyreModel() {
		return tyreModel;
	}

	public void setTyreModel(String tyreModel) {
		this.tyreModel = tyreModel;
	}

	public String getSpringNumber() {
		return springNumber;
	}

	public void setSpringNumber(String springNumber) {
		this.springNumber = springNumber;
	}

	public String getWheelBase() {
		return wheelBase;
	}

	public void setWheelBase(String wheelBase) {
		this.wheelBase = wheelBase;
	}

	public String getAxleNumber() {
		return axleNumber;
	}

	public void setAxleNumber(String axleNumber) {
		this.axleNumber = axleNumber;
	}

	public String getCarOutLength() {
		return carOutLength;
	}

	public void setCarOutLength(String carOutLength) {
		this.carOutLength = carOutLength;
	}

	public String getCarOutWidth() {
		return carOutWidth;
	}

	public void setCarOutWidth(String carOutWidth) {
		this.carOutWidth = carOutWidth;
	}

	public String getCarOutHeight() {
		return carOutHeight;
	}

	public void setCarOutHeight(String carOutHeight) {
		this.carOutHeight = carOutHeight;
	}

	public String getCarContLength() {
		return carContLength;
	}

	public void setCarContLength(String carContLength) {
		this.carContLength = carContLength;
	}

	public String getCarContWidth() {
		return carContWidth;
	}

	public void setCarContWidth(String carContWidth) {
		this.carContWidth = carContWidth;
	}

	public String getCarContHeight() {
		return carContHeight;
	}

	public void setCarContHeight(String carContHeight) {
		this.carContHeight = carContHeight;
	}

	public String getCarTotalmass() {
		return carTotalmass;
	}

	public void setCarTotalmass(String carTotalmass) {
		this.carTotalmass = carTotalmass;
	}

	public String getCarApproved() {
		return carApproved;
	}

	public void setCarApproved(String carApproved) {
		this.carApproved = carApproved;
	}

	public String getCarApprGuest() {
		return carApprGuest;
	}

	public void setCarApprGuest(String carApprGuest) {
		this.carApprGuest = carApprGuest;
	}

	public String getCarTrac() {
		return carTrac;
	}

	public void setCarTrac(String carTrac) {
		this.carTrac = carTrac;
	}

	public String getCabGuest() {
		return cabGuest;
	}

	public void setCabGuest(String cabGuest) {
		this.cabGuest = cabGuest;
	}

	public String getUseCharacter() {
		return useCharacter;
	}

	public void setUseCharacter(String useCharacter) {
		this.useCharacter = useCharacter;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getProduce() {
		return produce;
	}

	public void setProduce(String produce) {
		this.produce = produce;
	}

	public String getEquitment() {
		return equitment;
	}

	public void setEquitment(String equitment) {
		this.equitment = equitment;
	}

	public String getSimNumber() {
		return simNumber;
	}

	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
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

	public String getNetworkNo() {
		return networkNo;
	}

	public void setNetworkNo(String networkNo) {
		this.networkNo = networkNo;
	}

	public String getFeeseEnd() {
		return feeseEnd;
	}

	public void setFeeseEnd(String feeseEnd) {
		this.feeseEnd = feeseEnd;
	}

	public int getUprice() {
		return uprice;
	}

	public void setUprice(int uprice) {
		this.uprice = uprice;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getSeasonExamS() {
		return seasonExamS;
	}

	public void setSeasonExamS(String seasonExamS) {
		this.seasonExamS = seasonExamS;
	}

	public String getSeasonExamE() {
		return seasonExamE;
	}

	public void setSeasonExamE(String seasonExamE) {
		this.seasonExamE = seasonExamE;
	}

	public String getYearExamS() {
		return yearExamS;
	}

	public void setYearExamS(String yearExamS) {
		this.yearExamS = yearExamS;
	}

	public String getYearExamE() {
		return yearExamE;
	}

	public void setYearExamE(String yearExamE) {
		this.yearExamE = yearExamE;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", companyId=" + companyId + ", equitment=" + equitment + ", simNumber=" + simNumber + ", networkNo=" + networkNo + "]";
	}

}
