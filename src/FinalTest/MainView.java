package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
public class MainView extends JFrame{
	
	public final int btnNumber = 11; //버튼개수	
	JButton[] btn;
	JPanel mainPanel, btnPanel, labelPanel;
	JLabel TitleLabel;
	static JLabel[] label;
	private String filename;
	
	public MainView() {  
		//추가로 만들것
		//남는공간에 직전에 했던 것 출력하는거 추가하기~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		btn = new JButton[btnNumber];
		label = new JLabel[5];
		btnPanel = new JPanel();
		mainPanel = new JPanel();
		labelPanel = new JPanel();
		TitleLabel = new JLabel(" 다음 내용 중 필요한 기능을 선택하세요!");
		this.setTitle("은행 계좌 관리 프로그램");
		this.setSize(400, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeMenuBar();

		mainPanel.setLayout(new BorderLayout());
		btnPanel.setLayout(new GridLayout(btnNumber, 1, 20, 10));
		
		btn[0] = new JButton("계좌 개설(일반)");
		btn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new BankOption(1);  //1은 계좌개설
			}
		});

		btn[1] = new JButton("계좌 개설(마이너스)");
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mBankOption();
			}
			
		});
		
		btn[2] = new JButton("고객 찾기");
	     btn[2].addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {
	            // TODO Auto-generated method stub
					new BankOption(4);  //4는 고객찾기
	        	 
	            
	         }});
		
		btn[3] = new JButton("VIP 고객 찾기");
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindVIPUser();
			}
			
		});
		
		btn[4] = new JButton("입 금");
		btn[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BankOption(2);  //2는 입금하기
				
			}});
		btn[5] = new JButton("출 금");
		btn[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BankOption(3);  //3은 출금하기
				
			}});
		
		btn[6] = new JButton("저 장");
		btn[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSaver = new JFileChooser();
				switch(fileSaver.showSaveDialog(null)) {
				case JFileChooser.CANCEL_OPTION:
					filename = "unnamed";
					break;
				case JFileChooser.APPROVE_OPTION:
					filename = fileSaver.getSelectedFile().getName();
					new SaveNLoad(fileSaver.getSelectedFile().getPath()).SaveFile();
					label[1].setText(" 저 장"); // 기본적으로 최근활동내역 없음으로 표시
					label[2].setText(" 저장한 파일 이름 : " + filename); // 기본적으로 최근활동내역 표시 안함
					label[3].setText(" "); // 기본적으로 최근활동내역 표시 안함
					label[4].setText(" "); // 기본적으로 최근활동내역 표시 안함
					break;
				case JFileChooser.CUSTOM_DIALOG:
					filename = null;
				}
			}
			
		});
		
		btn[7] = new JButton("불러오기");
		btn[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				switch(fileChooser.showOpenDialog(null)) {
				case JFileChooser.CANCEL_OPTION:
					filename = "unnamed";
					break;
				case JFileChooser.APPROVE_OPTION:
					filename = fileChooser.getSelectedFile().getName();
					new SaveNLoad(fileChooser.getSelectedFile().getPath()).LoadFile();
					label[1].setText(" 불러오기"); // 기본적으로 최근활동내역 없음으로 표시
					label[2].setText(" 불러온 파일 이름 : " + filename); // 기본적으로 최근활동내역 표시 안함
					label[3].setText(" "); // 기본적으로 최근활동내역 표시 안함
					label[4].setText(" "); // 기본적으로 최근활동내역 표시 안함
					break;
				case JFileChooser.CUSTOM_DIALOG:
					filename = null;
				}
			}
			
		});

		btn[8] = new JButton("원격 저장");
		btn[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				switch(fileChooser.showSaveDialog(null)) {
				case JFileChooser.CANCEL_OPTION:
					filename = "unnamed";
					break;
				case JFileChooser.APPROVE_OPTION:
					filename = fileChooser.getSelectedFile().getName();
					new NetworkSave(fileChooser.getSelectedFile().getPath());					
					label[1].setText(" 원격 저장"); // 기본적으로 최근활동내역 없음으로 표시
					label[2].setText(" 원격으로 저장한 파일 이름 : " + filename); // 기본적으로 최근활동내역 표시 안함
					label[3].setText(" "); // 기본적으로 최근활동내역 표시 안함
					label[4].setText(" "); // 기본적으로 최근활동내역 표시 안함
					break;
				case JFileChooser.CUSTOM_DIALOG:
					filename = null;
				}
			}
			
		});

		btn[9] = new JButton("원격 불러오기");
		btn[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				switch(fileChooser.showOpenDialog(null)) {
				case JFileChooser.CANCEL_OPTION:
					filename = "unnamed";
					break;
				case JFileChooser.APPROVE_OPTION:
					filename = fileChooser.getSelectedFile().getName();
					new NetworkLoad(fileChooser.getSelectedFile().getPath());				
					label[1].setText(" 원격 불러오기"); // 기본적으로 최근활동내역 없음으로 표시
					label[2].setText(" 원격으로 불러온 파일 이름 : " + filename); // 기본적으로 최근활동내역 표시 안함
					label[3].setText(" "); // 기본적으로 최근활동내역 표시 안함
					label[4].setText(" "); // 기본적으로 최근활동내역 표시 안함
					break;
				case JFileChooser.CUSTOM_DIALOG:
					filename = null;
				}
			}
			
		});
		btn[10] = new JButton("종료");
		btn[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}			
		});
		
		for(int i=0;i<btnNumber;i++) {
			btnPanel.add(btn[i]);
		}
		
		labelPanel.setLayout(new GridLayout(8,1));
		label[0] = new JLabel(" 최근 활동 내역 ");

		label[1] = new JLabel(" 없음"); // 기본적으로 최근활동내역 없음으로 표시
		label[2] = new JLabel(" "); // 기본적으로 최근활동내역 표시 안함
		label[3] = new JLabel(" "); // 기본적으로 최근활동내역 표시 안함
		label[4] = new JLabel(" "); // 기본적으로 최근활동내역 표시 안함
		
		
		for(int i=0;i<5;i++)
			labelPanel.add(label[i]);
		
		mainPanel.add(TitleLabel, BorderLayout.NORTH);
		mainPanel.add(labelPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.EAST);
		this.add(mainPanel);
		this.setVisible(true);
	}

	public void makeMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu menu1 = new JMenu("파 일");
		JMenu menu2 = new JMenu("기 능");

		JMenuItem mkAccount1 = new JMenuItem("계좌 개설(일반)");
		mkAccount1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[0].doClick();
			}
			
		});
		JMenuItem mkAccount2 = new JMenuItem("계좌 개설(마이너스)");
		mkAccount2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[1].doClick();
			}
			
		});
		
		JMenuItem findVIP = new JMenuItem("VIP 고객 찾기");
		findVIP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[2].doClick();
			}
			
		});
		
		
		JMenuItem deposit = new JMenuItem("입 금");
		deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[3].doClick();
			}
			
		});
		
		JMenuItem withdraw = new JMenuItem("출 금");
		withdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[4].doClick();
			}
			
		});

		JMenuItem save = new JMenuItem("저  장");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[5].doClick();
			}
			
		});
		
		JMenuItem open = new JMenuItem("불러오기");
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn[6].doClick();
			}
			
		});
		
		JMenuItem exit = new JMenuItem("종 료");
		exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0 ) {
						System.exit(0);
				}
		});
		
		
		menu1.add(save);
		menu1.add(open);
		menu1.addSeparator();
		menu1.add(exit);

		menu2.add(mkAccount1);
		menu2.add(mkAccount2);
		menu2.addSeparator();
		menu2.add(findVIP);
		menu2.addSeparator();
		menu2.add(deposit);
		menu2.add(withdraw);

		mb.add(menu1);
		mb.add(menu2);

		this.setJMenuBar(mb);
		
	}

	public static void main(String[] args)
	{
		new MainView();
	}
}
