package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class mBankOption extends JFrame{
	MyDialog errorDialog;
	JButton btn, OKbtn;
	JPanel mainPanel, btnPanel, questionPanel;
	JLabel[] label, nullLabel;
	JTextField[] text;
	String Title, okbtn, Text1, Text2;
	
	public mBankOption() {
		Title = "마이너스 계좌 개설하기";
		okbtn = "개설하기";
		Text1 = "최초 예금액을 정해주세요!";
		Text2 = "마이너스 한도를 정해주세요!";
			
		this.setTitle(Title);
		this.setSize(400, 250);
		btn = new JButton("돌아가기");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				mBankOption.this.setVisible(false);
			}
			
		});
		
		OKbtn = new JButton(okbtn); 
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Bank(text[0].getText(),Integer.parseInt(text[1].getText()), Integer.parseInt(text[2].getText()), 1);  //고객 이름, 최초입금액  bank클래스로 전달 
					mBankOption.this.setVisible(false);
					
				} catch(IllegalArgumentException err){
	            	errorDialog = new MyDialog(mBankOption.this, "개좌 개설 오류", "고객 이름과 금액을 다시한번 확인해 주세요!");
	            	errorDialog.setVisible(true);
				}
			}
			
		});
		
		label = new JLabel[3];
		label[0] = new JLabel("이름을 입력해 주세요!");
		label[1] = new JLabel(Text1);
		label[2] = new JLabel(Text2);
		
		nullLabel = new JLabel[5];
		for(int i=0;i<5;i++)
			nullLabel[i] = new JLabel("  ");

		text = new JTextField[3];
		text[0] = new JTextField("");
		text[1] = new JTextField("");
		text[2] = new JTextField("");
		
		mainPanel = new JPanel();
		btnPanel = new JPanel();
		questionPanel = new JPanel();
		
		btnPanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BorderLayout());
		questionPanel.setLayout(new GridLayout(7,1));

		questionPanel.add(label[0]);
		questionPanel.add(text[0]);
		questionPanel.add(label[1]);
		questionPanel.add(text[1]);
		questionPanel.add(label[2]);
		questionPanel.add(text[2]);
		
		mainPanel.add(questionPanel, BorderLayout.CENTER);
		mainPanel.add(nullLabel[0], BorderLayout.EAST);
		mainPanel.add(nullLabel[1], BorderLayout.WEST);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(OKbtn);
		btnPanel.add(btn);
		this.add(mainPanel);
		this.setVisible(true);
	}

}
