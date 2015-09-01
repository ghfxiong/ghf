package com.ghf.core.bean;

public class InterCfgBean {
	private static final String tableName = "intercfg";
	public long interfaceid;
	public String intercode;
	public String implclass;
	public String implfunc;
	public String remark;
	public String ext1;
	public String ext2;
	public String ext3;
	public long getInterfaceid() {
		return interfaceid;
	}
	public void setInterfaceid(long interfaceid) {
		this.interfaceid = interfaceid;
	}
	public String getIntercode() {
		return intercode;
	}
	public void setIntercode(String intercode) {
		this.intercode = intercode;
	}
	public String getImplclass() {
		return implclass;
	}
	public void setImplclass(String implclass) {
		this.implclass = implclass;
	}
	public String getImplfunc() {
		return implfunc;
	}
	public void setImplfunc(String implfunc) {
		this.implfunc = implfunc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
}
