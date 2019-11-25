package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetworkSave extends JFrame{
	// TODO Auto-generated method stub
	String ServerIP;  //서버컴퓨터의 IP
	String filePath;
	JPanel mainPanel, btnPanel, contentPanel;
	JLabel label, Title;
	JButton btn, OKbtn;
	JTextField text;
	public static final int BUFSIZE = 256;  //버퍼사이즈  
	
	public NetworkSave(String path) {
		filePath = path;
		this.setTitle("원격 저장하기");
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
						NetworkSaveStart();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					NetworkSave.this.setVisible(false);
				}
			}
			
		});
		
		btn = new JButton("돌아가기");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				NetworkSave.this.setVisible(false);
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
	public void NetworkSaveStart() throws IOException  {    
		
    String server = ServerIP;                   
    int port = Integer.parseInt("9999");      //포트 9999로
    String filename = filePath;             
    FileInputStream fileIn = new FileInputStream(filename);   
    Socket sock = new Socket(server, port);  
    sendBytes(sock, fileIn);
    System.out.println();        
    sock.close();        
    fileIn.close();       
    }  
	
	private static void sendBytes(Socket sock, InputStream fileIn) throws IOException {    
		OutputStream sockOut = sock.getOutputStream();
		int bytesRead;                          
		byte[] buffer = new byte[BUFSIZE];  
		while ((bytesRead = fileIn.read(buffer)) != -1) {    
			sockOut.write(buffer, 0, bytesRead); 
		}    
		sock.shutdownOutput();     
	}
		
}
