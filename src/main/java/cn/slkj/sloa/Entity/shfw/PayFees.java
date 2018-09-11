package cn.slkj.sloa.Entity.shfw;

/**
 * 车辆缴费记录
 * 
 * @author maxh
 *
 */
public class PayFees {
	public int id;
	public String carNumber;
	public String owner;
	public int fees;
	public String sfry;
	public String sfriqi;
	public String sfriqi1;
	public String initriqi;
	private String startTime;// 服务开始时间
	private String endTime;// 服务结束时间
	private String endTime1;// 服务结束时间
	private int purpose;
	public String companyName;
	public String ownerTel;
	public String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getSfry() {
		return sfry;
	}

	public void setSfry(String sfry) {
		this.sfry = sfry;
	}

	public String getSfriqi() {
		return sfriqi;
	}

	public void setSfriqi(String sfriqi) {
		this.sfriqi = sfriqi;
	}

	public String getInitriqi() {
		return initriqi;
	}

	public void setInitriqi(String initriqi) {
		this.initriqi = initriqi;
	}

	public String getSfriqi1() {
		return sfriqi1;
	}

	public void setSfriqi1(String sfriqi1) {
		this.sfriqi1 = sfriqi1;
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

	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

	@Override
	public String toString() {
		return "PayFees [id=" + id + ", carNumber=" + carNumber + ", owner=" + owner + ", fees=" + fees + ", sfry=" + sfry + ", sfriqi=" + sfriqi + ", initriqi=" + initriqi
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", purpose=" + purpose + ", companyName=" + companyName + ", ownerTel=" + ownerTel + "]";
	}

}
