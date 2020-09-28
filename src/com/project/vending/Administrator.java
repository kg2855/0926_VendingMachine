package com.project.vending;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

public class Administrator extends JFrame implements ActionListener{

	private String adminPassword = "qwe123";
	
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel centerBottomPanel = new JPanel(new BorderLayout());
	JPanel centerTopPanel = new JPanel(new GridLayout(1, 3));
	 
	JPanel centerViewPanel = new JPanel(new GridLayout(1, 3));
	JPanel centerTextFieldPanel = new JPanel(new GridLayout(3, 1));
	JPanel bottomPanel = new JPanel(new GridLayout(1, 4));
	
	JLabel nameViewLabel = new JLabel("제품 이름");
	JLabel priceViewLabel = new JLabel("제품 가격");
	JLabel numViewLabel = new JLabel("제품 수량");
	JPanel nameViewPanel = new JPanel();
	JPanel priceViewPanel = new JPanel();
	JPanel numViewPanel = new JPanel();
	
	JPanel addPanel = new JPanel();
	JPanel changePanel = new JPanel();
	JPanel viewPanel = new JPanel();
	JPanel removePanel = new JPanel();
	
	JButton addButton = new JButton("추가");
	JButton changeButton = new JButton("수정");
	JButton viewButton = new JButton("보기");
	JButton removeButton = new JButton("삭제");
	
	TextField nameTextField = new TextField();
	JPasswordField passwordField = new JPasswordField();
	TextField numTextField = new TextField();
	
	JLabel nameLabel = new JLabel("제품 이름");
	JLabel passwordLabel = new JLabel("관리자 비밀번호");
	JLabel numLabel = new JLabel("추가 및 수정 수량");
	
	JPanel labelPanel = new JPanel(new GridLayout(3, 1));
	
	private String name;
	private String password;
	private int num;
	
	public Administrator() {
		
		setTitle("관리자 옵션");
		setBounds(600, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
//		centerBottom에 위치한 부분을 왼편에는 레이블(이름) 오른쪽에는 텍스트 상자를 적당한 크기로 넣어준다.
		labelPanel.add(nameLabel);
		labelPanel.add(passwordLabel);
		labelPanel.add(numLabel);
		
		
		nameLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		passwordLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		numLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		
		centerTextFieldPanel.setPreferredSize(new Dimension(300, 80));

		centerTextFieldPanel.add(nameTextField);
		centerTextFieldPanel.add(passwordField);
		centerTextFieldPanel.add(numTextField);
		
		centerBottomPanel.add(labelPanel, BorderLayout.WEST);
		centerBottomPanel.add(centerTextFieldPanel, BorderLayout.CENTER);
		
//		Button들의 각자 ActionListener를 받고 버튼의 크기를 수정해준다.
		addButton.addActionListener(this);
		changeButton.addActionListener(this);
		viewButton.addActionListener(this);
		removeButton.addActionListener(this);
		
//		추가, 수정, 보기, 삭제 버튼을 SOUTH에 넣어준다.
		addPanel.add(addButton);
		changePanel.add(changeButton);
		viewPanel.add(viewButton);
		removePanel.add(removeButton);
		
		bottomPanel.add(addPanel);
		bottomPanel.add(changePanel);
		bottomPanel.add(viewPanel);
		bottomPanel.add(removePanel);
		
//		Center 보기창에 name, price, num을 표 형태로 넣어준다.
		nameViewPanel.add(nameViewLabel);
		priceViewPanel.add(priceViewLabel);
		numViewPanel.add(numViewLabel);
		
		nameViewLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		priceViewLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		numViewLabel.setFont(new Font("D2Coding", Font.BOLD, 10));
		
		centerTopPanel.add(nameViewPanel);
		centerTopPanel.add(priceViewPanel);
		centerTopPanel.add(numViewPanel);
		
		
//		Center에 수량 표기 창과 데이터 입력창을 상,하에 넣어준다.
		centerPanel.add(centerTopPanel, BorderLayout.NORTH);
		centerPanel.add(centerViewPanel, BorderLayout.CENTER);
		centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);
		
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		nameTextField.setText("");
		passwordField.setText("");
		numTextField.setText("");
		nameTextField.requestFocus();
		
//		setEditable
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case "추가":
			getTyped();
//			addVending();
			break;
		case "수정":
			getTyped();
//			changeVendingName();
//			changeVendingPrice();
//			changeVendingNum();
			break;
		case "보기":
			getTyped();
//			viewVendingAll();
//			viewVendingSelected();
			break;
		case "삭제":
			getTyped();
//			removeVending();
//			removeVendingAll();
			break;

		default:
			
			break;
		}
		
	}

	private void getTyped() {
		if(nameTextField.getText().trim().length() <= 0) {
			JOptionPane.showInternalMessageDialog(null, "제품명이 입력되지 않았습니다.", "오류", JOptionPane.WARNING_MESSAGE);
		}
		if(!passwordField.getText().trim().equals(adminPassword)){
			JOptionPane.showInternalMessageDialog(null, "비밀번호가 잘못되었습니다.", "오류", JOptionPane.WARNING_MESSAGE);
		}else {
			this.name = nameTextField.getText().trim();
			this.password = passwordField.getText().trim();
			try {
				this.num = Integer.parseInt(numTextField.getText().trim());
			}catch(NumberFormatException e2) {
				JOptionPane.showInternalMessageDialog(null, "수량이 잘못 입력되었습니다", "오류", JOptionPane.WARNING_MESSAGE);
			}
		}
		System.out.println("정상적으로 값을 가져왔습니당ㅎㅎ" + nameTextField.getText() + passwordField.getText());
	}
	
	
	
	
	
}
