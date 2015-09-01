package com.ghf.core.dao;

import java.util.List;
import java.util.Map;

public class DbBean {
	private String[] cols ;
	private List<Map<String,Object>> params;
	public String[] getCols() {
		return cols;
	}
	public void setCols(String[] cols) {
		this.cols = cols;
	}
	public List<Map<String, Object>> getParams() {
		return params;
	}
	public void setParams(List<Map<String, Object>> params) {
		this.params = params;
	}
}
