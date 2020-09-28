package com.project.vending;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.sound.sampled.ReverbType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VendingProject extends JFrame implements ActionListener{

	FileControl fc;
	VendingList vl;
	int itemNum;
	
	ArrayList<VendingSelectVO> vendingSelectList = new ArrayList<VendingSelectVO>();
	
	JLabel topLabel;
	JLabel mainLabel;
	JLabel bottomLabel;
	
	JPanel mainPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	
	JButton itemButton;
	
	ArrayList<JButton> itemButtonList = new ArrayList<JButton>();
	
	JButton selectButton;
	JButton removeButton;
	JButton payButton;
	
	Color color;
	
	public VendingProject() {
//		리스트를 만든다.
		fc = new FileControl();
		
//		Optionpanel로 index 번호를 가져온다.
		String[] vt = new String[fc.getList().size()];
		for (int i = 0; i < fc.getList().size(); i++) {
			vt[i] = fc.getList().get(i);
		}
		int index = (JOptionPane.showOptionDialog(null, "메뉴를 선택해주세요", 
				"자판기 메뉴", JOptionPane.OK_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, vt, vt[0]));		
//		System.out.println(index);
		fc.setIndex(index);

		vl = new VendingList(fc);
		itemNum = (int) Math.sqrt(vl.getSize());
		
		setTitle("Vending Machine");
		setBounds(600, 50, 600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());		
		
		mainLabel = new JLabel();
		topLabel = new JLabel();
		bottomLabel = new JLabel();
		
		mainPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		mainPanel.setLayout(new GridLayout(itemNum, itemNum));
				
		selectButton = new JButton("장바구니");
		removeButton = new JButton("필요없어졋어...");
		payButton = new JButton("계산하자~");
		
		selectButton.setPreferredSize(new Dimension(150, 100));
		removeButton.setPreferredSize(new Dimension(150, 100));
		payButton.setPreferredSize(new Dimension(150, 100));
		
		bottomPanel.add(selectButton);
		bottomPanel.add(removeButton);
		bottomPanel.add(payButton);
		
//		mainPanel.add(mainLabel, JLabel.CENTER);
//		topPanel.add(topLabel,JLabel.CENTER);
//		bottomPanel.add(bottomLabel, JLabel.CENTER);
				
		setGridButtons();
		
		selectButton.addActionListener(this);
		removeButton.addActionListener(this);
		payButton.addActionListener(this);
		
		add(mainPanel, BorderLayout.CENTER);
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}






	public static void main(String[] args) {
		
		VendingProject window = new VendingProject();
		
	}	
	
	private void setGridButtons() {
		for (int i = 0; i < vl.getVendingList().size(); i++) {
			String vn = vl.getVendingList().get(i).getName();
//			JLabel buttonLabel = new JLabel(vn);
			itemButton = new JButton();
			itemButton.setText(vn);
			itemButton.setFont(new Font("D2Coding", Font.BOLD, 20));
			itemButton.addActionListener(this);
			if(vn != null) {
				itemButton.setName(vn);
			}
			itemButtonList.add(itemButton);
			mainPanel.add(itemButtonList.get(i));
			VendingSelectVO vsvo = new VendingSelectVO(i, 0);
			vendingSelectList.add(vsvo);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str1 = "";
		
		switch (e.getActionCommand()) {
		case "장바구니":
			vendingView(str1);
			break;
		case "필요없어졋어...":
			vendingRemove();
			break;
		case "계산하자~":
			
			int total = 0;
			int change = 0;
			
			for (int i = 0; i < vendingSelectList.size(); i++) {
				total += vl.getVendingList().get(i).getPrice() * vendingSelectList.get(i).getSelectNum();
			}
			
			String pay = JOptionPane.showInputDialog(null, 
					String.format("%s총 가격은 %d원 입니다!\r\n계산할 금액을 입력해주세요", str1, total),
						"계산", JOptionPane.PLAIN_MESSAGE).trim(); 
			
			try {
				vendingPay(total, pay);
			} catch (NumberFormatException e2) {
				adminOption(pay);
			}
			
			break;
		case "<매진>":
			JOptionPane.showMessageDialog(null, "벌써 매진이넹 ㅠㅠ");
			break;
		case "관리자 옵션":
			
			break;
		default:

			Object object = e.getSource();
			String str2 = ((JButton) object).getName();
			
			vendingSelect(str2);
			
			break;
		}
		
	}






	private void adminOption(String pay) {
		String security = "admin";
		if(pay.toLowerCase().equals(security)) {
			Administrator admin = new Administrator();
		} else {
			JOptionPane.showMessageDialog(null, "먹튀는 안되~ 경찰서가자 ㅎㅎ", "오류", JOptionPane.WARNING_MESSAGE);
		}
	}






	private void vendingPay(int total, String pay) {
		int change;
		change = Integer.parseInt(pay) - total;
		if(change < 0) {
			JOptionPane.showMessageDialog(null, "돈이 모자르네~?\n어머니 모셔와~^^", "계산 실패", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, change == 0 ? "금액이 딱 맞네요!\n계산 성공!" 
					: String.format("거스름돈 %d 원 입니다!\n계산 성공!", change), "계산 성공", JOptionPane.PLAIN_MESSAGE); 
		}
	}






	private void vendingSelect(String str2) {
		if(str2 != null) {
		
			for(int i = 0; i < vl.getSize(); i++) {
				if(str2.equals(vl.getVendingList().get(i).getName())) {
					if (vl.getVendingList().get(i).getNum() <= 0) {
						JOptionPane.showMessageDialog(null, "현재 물품의 재고가 더이상 없습니다ㅠㅠ");
						itemButtonList.get(i).setText("<매진>");;
					} else {
						VendingSelectVO vsvo = new VendingSelectVO(i, 1);
						vl.getVendingList().get(i).setNum(vl.getVendingList().get(i).getNum() - 1);
						vendingSelectList.get(i).setSelectNum(vendingSelectList.get(i).getSelectNum() + 1);
					}
				}
			}
		}
	}






	private void vendingRemove() {
		String str1;
		for(int i = 0; i < vendingSelectList.size(); i++) {
			if(vl.getVendingList().get(i).getNum() <= 0) {
				itemButtonList.get(i).setText(vl.getVendingList().get(i).getName());
			}
			vl.getVendingList().get(i).setNum(vendingSelectList.get(i).getSelectNum() + vl.getVendingList().get(i).getNum());
			vendingSelectList.get(i).setSelectNum(0);
		}
		str1 = "";
		JOptionPane.showMessageDialog(null, "장바구니를 비웠습니다!");
	}






	private void vendingView(String str1) {
		for (int i = 0; i < vendingSelectList.size(); i++) {
			if(vendingSelectList.get(i).getSelectNum() > 0) {
				str1 += vl.getVendingList().get(i).getName() + " : " + vendingSelectList.get(i).getSelectNum() + "\n";
			}
			
		}
		JOptionPane.showInternalMessageDialog(null, str1, "장바구니", JOptionPane.PLAIN_MESSAGE);
	}
	
	
	
	
}













