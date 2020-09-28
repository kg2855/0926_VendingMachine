package com.project.vending;

public class VendingVO {

	private String name;
	private int price;
	private int num;
	
	public VendingVO(String name, int price, int num) {
		this.name = name;
		this.price = price;
		this.num = num;
		
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}




	@Override
	public String toString() {
		return "VendingVO [name=" + name + ", price=" + price + ", num=" + num + "]";
	}


	
}
