package com.project.vending;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class VendingList {
	
	
	private ArrayList<VendingVO> vendingList = new ArrayList<VendingVO>();
	private ArrayList<String> names;
	private ArrayList<Integer> prices;
	private ArrayList<Integer> nums;
	private int size;

	public VendingList(FileControl fc) {
		
		names = fc.readNameFile();
		prices = fc.readPriceFile();
		nums = fc.readNumFile();
		
		this.size = (int) Math.pow( (int)Math.sqrt(names.size() - 1) + 1, 2);
		for (int i = 0; i < size; i++) {
			
//			제품이 존재하는지에 대한 여부를 확인한다.
			if(names.size() - 1 < i) {
//				제품이 존재하지 않으면 빈칸을 출력하고 아무 값도 넣지 않는다.
				vendingList.add(new VendingVO(null, 0, 0));
			} else {
//				존재하는 제품이 매진인지 확인한다.
				if(nums.get(i) <= 0) {
					vendingList.add(new VendingVO("<매진>", prices.get(i), nums.get(i)));					
				} else {
//				존재하는 제품이 매진이라면, 이름 대신 "매진"을 넣어주고, 
//				매진이 아니라면 이름을 그대로 넣어준다.
					vendingList.add(new VendingVO(names.get(i), prices.get(i), nums.get(i)));
				}
			}
			
		}
//		System.out.println(size);
	}

	public VendingList(ArrayList<VendingVO> vendingList) {
		this.vendingList = vendingList;
	}

	public ArrayList<VendingVO> getVendingList() {
		return vendingList;
	}

	public void setVendingList(ArrayList<VendingVO> vendingList) {
		this.vendingList = vendingList;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
