package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FindVIPUser extends JFrame{
	JButton btn;
	JPanel mainPanel, btnPanel;
	JFrame frame;
	JLabel label;
	public FindVIPUser() {
		frame = new JFrame();
		frame.setTitle("VIP 회원");
		frame.setSize(400, 250);
		btn = new JButton();
		btn.setText("돌아가기");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frame.setVisible(false);
			}
			
		});
		
		//label = new JLabel(" 현재 VIP고객 정보");
		mainPanel = new JPanel();
		btnPanel = new JPanel();
		
		btnPanel.setLayout(new FlowLayout());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new VIPPaintStart(), BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(btn);
		frame.add(mainPanel);		
		frame.setVisible(true);
		
	}
	

}
