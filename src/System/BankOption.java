package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BankOption extends JFrame{
	MyDialog errorDialog;
	JButton btn, OKbtn;
	JPanel mainPanel, btnPanel, questionPanel;
	JLabel[] label, nullLabel;
	static JLabel label1, label2;
	JTextField[] text;
	String Title, okbtn, Text1;
	
	public BankOption(int num) {
		switch(num) {
		case 1: //계좌 개설이면
			Title = "일반 계좌 개설하기";
			okbtn = "개설하기";
			Text1 = "최초 예금액을 정해주세요!";
			break;
			
		case 2: //입금이면
			Title = "입금 하기";
			okbtn = "입금하기";
			Text1 = "입금액을 정해주세요!";
			break;

		case 3: //출금이면
			Title = "출금 하기";
			okbtn = "출금하기";
			Text1 = "출금액을 정해주세요!";
			break;
		case 4:
		    Title = "고객 찾기";
		    okbtn = "확인";
		    Text1 = "고객 이름을 입력해주세요!";
		    break;
		}
		this.setTitle(Title);
		this.setSize(400, 250);
		label1 = new JLabel("");
		label2 = new JLabel("");
		btn = new JButton("돌아가기");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				BankOption.this.setVisible(false);
			}
			
		});
		
		OKbtn = new JButton(okbtn); 
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(num != 4) {
					try {
						new Bank(text[0].getText(),Integer.parseInt(text[1].getText()), num);  //고객 이름, 최초입금액  bank클래스로 전달  num이 1이면 개좌 개설, 2면 입금, 3이면 출금을 의미
						BankOption.this.setVisible(false);
						
					} catch(IllegalArgumentException err){
						errorDialog = new MyDialog(BankOption.this, "개좌 개설 오류", "고객 이름과 금액을 다시한번 확인해 주세요!");
						errorDialog.setVisible(true);
					}
				}
				else {
					new Bank(text[0].getText());
				}
			}
			
		});
		
		label = new JLabel[2];
		label[0] = new JLabel("이름을 입력해 주세요!");
		if(num != 4)
			label[1] = new JLabel(Text1);
		
		nullLabel = new JLabel[5];
		for(int i=0;i<5;i++)
			nullLabel[i] = new JLabel("  ");
		
		text = new JTextField[2];
		text[0] = new JTextField("");
		if(num != 4)
			text[1] = new JTextField("");
		
		mainPanel = new JPanel();
		btnPanel = new JPanel();
		questionPanel = new JPanel();

		//	btnPanel.setLayout(new GridLayout(1,2, 50, 20));
		btnPanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BorderLayout());
		questionPanel.setLayout(new GridLayout(5,1));

		questionPanel.add(label[0]);
		questionPanel.add(text[0]);
		if(num != 4) {
			questionPanel.add(label[1]);
			questionPanel.add(text[1]);
		}
		else {
			questionPanel.add(label1);
			questionPanel.add(label2);
		}
		
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
