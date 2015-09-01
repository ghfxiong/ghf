package com.ghf.bean;

public class DbBallTime {
	private static final String tableName = "dbBall_time";
	
	public int num;
	public int times;
	public int loc;
	public int is_blue;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getLoc() {
		return loc;
	}
	public void setLoc(int loc) {
		this.loc = loc;
	}
	public int getIs_blue() {
		return is_blue;
	}
	public void setIs_blue(int is_blue) {
		this.is_blue = is_blue;
	}
}
