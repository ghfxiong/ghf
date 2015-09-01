package com.ghf.core.dao;
/**
 * 数据库 字段属性
 * @author ghf
 *
 */
public class ColAttr {
	private String name;  //字段名称
	private String value; //字段值
	private String type; //字段类型
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getValue() {
		return value;
	}
	protected void setValue(String value) {
		this.value = value;
	}
	protected String getType() {
		return type;
	}
	protected void setType(String type) {
		this.type = type;
	}
	
	
}
