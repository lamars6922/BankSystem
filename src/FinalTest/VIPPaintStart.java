package FinalTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class VIPPaintStart extends JPanel {
	
	MyDialog errorDialog;	
	String VIPname;
	int biggestMoney, totalMoney;   //자동으로 0으로 초기화  가장 많은 금액과 전체 금액
	double VIPper; //vip의 금액이 전체 중 차지하는 비율
	public VIPPaintStart() {
		if(Bank.accounts.size() != 0) {
			for(int i=0;i<Bank.accounts.size();i++) {
				if(biggestMoney < Bank.accounts.get(i).getBalance()) {
					biggestMoney = Bank.accounts.get(i).getBalance();   //가장 많은 금액을 가진 사람의 금액
					VIPname = Bank.accounts.get(i).name;				//가장 많은 금액을 가진 사람의 이름					
				}				
				totalMoney += Bank.accounts.get(i).getBalance();
			}
			VIPper = (double)(biggestMoney)/(double)(totalMoney)*360;
		}
	}	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(Bank.accounts.size() == 0) {
			g.setFont(new Font("돋움", Font.BOLD,18));
			g.drawString("현재 입금하신 고객이 존재하지 않습니다!", 5,20);
		}
		else {
			g.drawString("VIP고객  잔액", 200,30);
			g.drawString("전체 고객 잔액", 300,30);
			g.setFont(new Font("돋움",Font.BOLD, 12));
			g.drawString("VIP고객  이름", 5,50);
			g.drawString("VIP : "+VIPname, 5,70);
			g.drawString("VIP고객 현재 잔액", 5, 110);
			g.drawString("현재 잔액 : " + biggestMoney, 5, 130);
			g.setFont(new Font("돋움", Font.BOLD,18));
			g.drawString("현재 VIP 고객 정보", 5, 20);
			
				g.setColor(Color.gray);
				g.fillArc(210, 50, 120, 120, (int)VIPper, 360-(int)VIPper);
				g.fillRect(288, 20, 10, 10);
				g.setColor(Color.CYAN);
				g.fillArc(210, 50, 120, 120, 0, (int)VIPper);
				g.fillRect(188, 20, 10, 10);

            	MainView.label[1].setText(" VIP 고객 확인");
            	MainView.label[2].setText("    VIP고객 : " + VIPname);
            	MainView.label[3].setText(" ");
            	MainView.label[4].setText(" ");
		}
	}
}
