package com.ghf.bean;

public class CityInfo {
	private static final String tableName = "cityInfo";
	public String cityName;
	public String lev1; //国家
	public String lev2; //省级
	public String lev3; //市级
	public String lev4; //县级
	public String sort;
	public String cityCode; 
	public String ext1;
	public String ext2;
	public int type;  //级别  1-省 2-市 3-县 
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLev4() {
		return lev4;
	}
	public void setLev4(String lev4) {
		this.lev4 = lev4;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getLev1() {
		return lev1;
	}
	public void setLev1(String lev1) {
		this.lev1 = lev1;
	}
	public String getLev2() {
		return lev2;
	}
	public void setLev2(String lev2) {
		this.lev2 = lev2;
	}
	public String getLev3() {
		return lev3;
	}
	public void setLev3(String lev3) {
		this.lev3 = lev3;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
	
	
	
}
