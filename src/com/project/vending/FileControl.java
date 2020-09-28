package com.project.vending;

import java.awt.Choice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileControl extends JFrame{
	
	private String vendingType;
	JPanel comboPanel = new JPanel();
	Choice comboBox =  new Choice();
	ArrayList<String> list;
	private int index;
	
	public FileControl() {
		
		this.list = new ArrayList<String>();
		this.list.add("Book");
		this.list.add("Drink");
		this.list.add("Performance");
				
	}
	
	public ArrayList<String> readNameFile() {
		
		this.setVendingType(list.get(index));
		String nameFilePath = String.format("./src/com/project/vending/VendingName_%s.txt" , vendingType);
		File nameFile = new File(nameFilePath);
		
		ArrayList<String> names = new ArrayList<String>();
		Scanner sc;
		try {
			sc = new Scanner(nameFile);
			while(sc.hasNext()) {
				names.add(sc.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
		}
		return names;
	}
	public ArrayList<Integer> readPriceFile() {
		
		this.setVendingType(list.get(index));
		String priceFilePath = String.format("./src/com/project/vending/VendingPrice_%s.txt" , vendingType);
		File priceFile = new File(priceFilePath);
		
		ArrayList<Integer> prices = new ArrayList<Integer>();
		Scanner sc;
		try {
			sc = new Scanner(priceFile);
			while(sc.hasNext()) {
				prices.add(Integer.parseInt(sc.nextLine().trim()));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
		}
		
		
		return prices;
	}
	public ArrayList<Integer> readNumFile() {
		
		this.setVendingType(list.get(index));
		String numFilePath = String.format("./src/com/project/vending/VendingNum_%s.txt" , vendingType);
		File numFile = new File(numFilePath);
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		Scanner sc;
		try {
			sc = new Scanner(numFile);
			while(sc.hasNext()) {
				nums.add(Integer.parseInt(sc.nextLine().trim()));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
		}
		
		return nums;
	}
	
	public void writeNameFile(String name) {
		this.setVendingType(list.get(index));
		String nameFilePath = String.format("./src/com/project/vending/VendingName_%s.txt" , vendingType);
		File nameFile = new File(nameFilePath);
		FileControl fc = new FileControl();
		VendingList vl = new VendingList(fc);
		for (int i = 0; i < vl.getVendingList().size(); i++) {
			if(vl.getVendingList().get(i).getName().equals(name)) {
				try {
					vl.getVendingList().get(i).setName(name);
					FileWriter fw = new FileWriter(nameFile);
					for(VendingVO vo : vl.getVendingList()) {
						fw.write(vo.getName() + "\n");
					}
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			} else if(vl.getVendingList().size() - 1 <= i) {
				try {
					FileWriter fw = new FileWriter(nameFile, true);
					fw.write(name + "\n");
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			}
		}
		
	
	}
	public void writePriceFile(String name, int price) {
		this.setVendingType(list.get(index));
		String priceFilePath = String.format("./src/com/project/vending/VendingPrice_%s.txt" , vendingType);
		File priceFile = new File(priceFilePath);
		FileControl fc = new FileControl();
		VendingList vl = new VendingList(fc);
		for (int i = 0; i < vl.getVendingList().size(); i++) {
			if(vl.getVendingList().get(i).getName().equals(name)) {
				try {
					vl.getVendingList().get(i).setPrice(price);
					FileWriter fw = new FileWriter(priceFile);
					for(VendingVO vo : vl.getVendingList()) {
						fw.write(vo.getPrice() + "\n");
					}
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			} else if(vl.getVendingList().size() - 1 <= i) {
				try {
					FileWriter fw = new FileWriter(priceFile, true);
					fw.write(price + "\n");
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			}
		}
	}
	public void writeNumFile(String name, int num) {
		this.setVendingType(list.get(index));
		String numFilePath = String.format("./src/com/project/vending/VendingNum_%s.txt" , vendingType);
		File numFile = new File(numFilePath);
		FileControl fc = new FileControl();
		VendingList vl = new VendingList(fc);
		for (int i = 0; i < vl.getVendingList().size(); i++) {
			if(vl.getVendingList().get(i).getName().equals(name)) {
				try {
					vl.getVendingList().get(i).setNum(num);
					FileWriter fw = new FileWriter(numFile);
					for(VendingVO vo : vl.getVendingList()) {
						fw.write(vo.getNum() + "\n");
					}
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			} else if(vl.getVendingList().size() - 1 <= i) {
				try {
					FileWriter fw = new FileWriter(numFile, true);
					fw.write(num + "\n");
					fw.close();
					break;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "데이터가 손상되었습니다...");
					break;
				}
			}
		}
	}

	public String getVendingType() {
		return vendingType;
	}

	public void setVendingType(String vendingType) {
		this.vendingType = vendingType;
	}

	public ArrayList<String> getList() {
		return this.list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
}
