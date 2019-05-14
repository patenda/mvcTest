package com.shi.springmvc.handlers;

public class PoolInfo implements Comparable<PoolInfo>{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	String name="";
	int value;
	
	public int compareTo(PoolInfo poolInfo) {
		return this.getValue()-poolInfo.getValue();
	}
}
