package com.project.vending;

public class VendingSelectVO {
	
	private int index;
	private int selectNum;
	
	
	public VendingSelectVO(int index, int selectNum) {
		this.index = index;
		this.selectNum = selectNum;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getSelectNum() {
		return selectNum;
	}


	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}


	@Override
	public String toString() {
		return "VendingSelectList [index=" + index + ", selectNum=" + selectNum + "]";
	}
	
		
}
