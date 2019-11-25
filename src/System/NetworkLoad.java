package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetworkLoad extends JFrame{
	// TODO Auto-generated method stub
	String ServerIP;  //서버컴퓨터의 IP
	String filePath;
	JPanel mainPanel, btnPanel, contentPanel;
	JLabel label, Title;
	JButton btn, OKbtn;
	JTextField text;
	public NetworkLoad(String path) {
		filePath = path;
		this.setTitle("원격 불러오기");
		this.setSize(250, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(2,1));
		label = new JLabel("서버 컴퓨터의 IP주소를 입력해 주세요!");

		text = new JTextField();
		
		OKbtn = new JButton("완료");
		OKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerIP = text.getText();  
				if(ServerIP.equals("")) {
					System.out.println("빈칸");
				}
				else {
					try {
						NetworkLoadStart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					NetworkLoad.this.setVisible(false);
				}
			}
			
		});
		
		btn = new JButton("돌아가기");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				NetworkLoad.this.setVisible(false);
			}
			
		});
		contentPanel.add(label);
		contentPanel.add(text);
		btnPanel.add(OKbtn);
		btnPanel.add(btn);
		mainPanel.add(contentPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setVisible(true);
	}
	public void NetworkLoadStart() throws IOException {

		ObjectInputStream ois;
	       Socket socket = new Socket(ServerIP, 9999);
	        //데이터를 통신을 위해서 소켓의 스트림 얻기.
	        InputStream in = socket.getInputStream();
	        DataInputStream dis = new DataInputStream(in);
	        OutputStream out = socket.getOutputStream();
	        DataOutputStream dos = new DataOutputStream(out);
	         String fileNameMsg = "BackUp.txt";   //받아올파일의 이름은 BackUp.txt 로 고정
	        dos.writeUTF(fileNameMsg);
		     FileOutputStream fos = new FileOutputStream(filePath);
	        while(true){
	            int data=dis.read(/*buffer*/);
	            if(data == -1) break;
	            fos.write(data);
	        }

			try {
				ois = new ObjectInputStream(new FileInputStream(filePath));
				try {
					Object obj = ois.readObject();
					Bank.accounts = (Vector<Account>)obj;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	        dos.close();
	        dis.close();
	        out.close();
	        in.close();
	        socket.close();
	 }
	
}
