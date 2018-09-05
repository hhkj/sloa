package cn.slkj.sloa.Entity.system;

/**
 * 
 * @ClassName: Company
 * @Description:
 * @author maxuhui
 *
 */
public class Company {
	private String	id;
	/**
	 * 公 司 名 称
	 */
	private String	compName;
	/**
	 * 企业组织机构代码
	 */
	private String	organizationCode;
	/**
	 * 企 业 法 人
	 */
	private String	corporate;
	/**
	 * 联系人
	 */
	private String	contactMenber;
	/**
	 * 联系方式
	 */
	private String	contactWay;
	/**
	 * 联系方式
	 */
	private String	email;
	/**
	 * 公司级别
	 */
	private int		level;
	/**
	 * 上级公司
	 */
	private String	bCompany;
	/**
	 * 营业执照号
	 */
	private String	businessLicense;
	/**
	 * 企业注册资金
	 */
	private String	registeredCapital;
	/**
	 * 公司地址
	 */
	private String	compAddress;
	/**
	 * 简介（公司营业范围）
	 */
	private String	introduction;
	/**
	 * 备注
	 */
	private String	remarks;
	/**
	 * QQ
	 */
	private String	qq;
	/**
	 * 手机号码
	 */
	private String	mobile;
	/**
	 * 许可证号
	 */
	private String	xkz;
	
	private Double sfbz;//收费标准
	

	public Double getSfbz() {
		if(sfbz == null){
			return 0.0;
		}
		return sfbz;
	}

	public void setSfbz(Double sfbz) {
		this.sfbz = sfbz;
	}

	public String getXkz() {
		return xkz;
	}

	public void setXkz(String xkz) {
		this.xkz = xkz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	public String getContactMenber() {
		return contactMenber;
	}

	public void setContactMenber(String contactMenber) {
		this.contactMenber = contactMenber;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getbCompany() {
		return bCompany;
	}

	public void setbCompany(String bCompany) {
		this.bCompany = bCompany;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getCompAddress() {
		return compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", organizationCode="
				+ organizationCode + ", corporate=" + corporate + ", contactMenber="
				+ contactMenber + ", contactWay=" + contactWay + ", email=" + email + ", level="
				+ level + ", bCompany=" + bCompany + ", businessLicense=" + businessLicense
				+ ", registeredCapital=" + registeredCapital + ", compAddress=" + compAddress
				+ ", introduction=" + introduction + ", remarks=" + remarks + "]";
	}

}
