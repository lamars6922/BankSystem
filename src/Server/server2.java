
package Server;

import java.net.*;  
 import java.io.*;   
 public class server2 {  //클라이언트 > 서버 전송전용 서버
	 
    private static final int BUFSIZE = 256;   
    public static void main(String[] argv) throws IOException { 
    	
      int servPort = Integer.parseInt("9999"); 

       ServerSocket servSock = new ServerSocket(servPort); 
       FileOutputStream fos = new FileOutputStream("C://Users//P-hj//Desktop//BackUp.txt");
       int recvMsgSize;   

       byte[] byteBuffer = new byte[BUFSIZE]; 


          Socket clntSock = servSock.accept();     

          System.out.println("Handling client at " + 

          clntSock.getInetAddress().getHostAddress() + " on port " + 

          clntSock.getPort()); 

          InputStream in = clntSock.getInputStream(); 

          ByteArrayOutputStream out = new ByteArrayOutputStream(); 
          

          while ((recvMsgSize = in.read(byteBuffer)) != -1) { 

             out.write(byteBuffer, 0, recvMsgSize); 

          } 

          System.out.println("Received data : " + new String(out.toByteArray())); 
          fos.write(out.toByteArray());
          fos.close();
          clntSock.close(); 
    } 
 } 

