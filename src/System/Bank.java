package FinalTest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class Bank extends JFrame {
	JLabel label;
	JButton btn;
	boolean sameName;
   static Vector<Account> accounts = new Vector<Account>();
   int service = 1000;
   String customerName;
   int cash;
   MyDialog errorDialog;
   public Bank(String InName) {
	   customerName = InName;
	   check();
   }
   public Bank(String InName, int InMoney, int num) {   //num에 따라 개좌 개설, 입금, 출금이 달라짐
	   	customerName = InName;
	   	cash = InMoney;
	   	sameName = false;
	   	switch(num) {
	   		case 1:  //일반 계좌 개설
	   			make();
	   			break;	   			
	   		case 2: //입금
	   			plus();
	   			break;	   			
	   		case 3: //출금
	   			minus();
	   			break;	   
	   	}
   }
   
   public Bank(String InName, int InMoney, int InMinus, int num) {  //마이너스 계좌 생성
	    customerName = InName;
	   	cash = InMoney;
	   	sameName = false;
	   	
	      if(cash <= 0) {
	         cash = 0;
	      } // 0이하의 숫자입력시 설정하지 않는다고 받아드립니다.
	      for(int i=0;i<accounts.size();i++) {
	    	  if(accounts.get(i).name.equals(customerName)) {
	    		  	sameName = true;  //이미 같은이름으로 계좌를 개설한 것이 존재하면
	    	       	errorDialog = new MyDialog(this, "계좌 개설 오류", "이미 같은 이름으로 계좌를 개설하였습니다!");
	    	       	errorDialog.setVisible(true);    		  
	    	  }
	      }
	      if(!sameName) {
	    	  accounts.add(new Account(cash, customerName, InMinus));
	    	  MainView.label[1].setText(" 계좌 개설");
	    	  MainView.label[2].setText("   고객 이름 : " + InName);
	    	  MainView.label[3].setText(" 최초 입금액");
	    	  MainView.label[4].setText("   금액 : " + InMoney);
	      }
	      sameName = false; //sameName을 다시 false로 바꾸기(그래야 다음부터 게좌개설이 정상으로 실행)
	      
   }
   
   public void make() {
      if(cash <= service) {
         cash = service;
      } // 0이하의 숫자입력시 설정하지 않는다고 받아드립니다.
      for(int i=0;i<accounts.size();i++) {
    	  if(accounts.get(i).name.equals(customerName)) {
    		  	sameName = true;  //이미 같은이름으로 계좌를 개설한 것이 존재하면
    	       	errorDialog = new MyDialog(this, "계좌 개설 오류", "이미 같은 이름으로 계좌를 개설하였습니다!");
    	       	errorDialog.setVisible(true);    		  
    	  }
      }
      if(!sameName) {
    	  accounts.add(new Account(cash, customerName));
    	  MainView.label[1].setText(" 계좌 개설");
    	  MainView.label[2].setText("   고객 이름 : " + customerName);
    	  MainView.label[3].setText(" 최초 입금액");
    	  MainView.label[4].setText("   금액 : " + cash);
      }
      sameName = false; //sameName을 다시 false로 바꾸기(그래야 다음부터 게좌개설이 정상으로 실행)
   } // 계좌 생성
   
   public void plus() {
	   if(accounts.size() == 0) {
       	errorDialog = new MyDialog(this, "개좌 개설 고객 없음", "계좌를 개설한 고객이 존재하지 않습니다.");
       	errorDialog.setVisible(true);
	   }
      for(int i=0;i<accounts.size();i++) {
    	  if(accounts.get(i).name.equals(customerName)) {
            try {
            	accounts.get(i).deposit(cash);
            	MainView.label[1].setText(" 입금");
            	MainView.label[2].setText("   고객 이름 : " + customerName);
            	MainView.label[3].setText(" 입금액");
            	MainView.label[4].setText("   금액 : " + cash);
            break;
            }
            catch(IllegalArgumentException e) {
            	errorDialog = new MyDialog(this, "입금액 오류", "입금액이 음수입니다. 입금액을 다시 확인해 주세요!");
            	errorDialog.setVisible(true);
            	break;
            }
         }
    	 if(i == accounts.size()-1) {
    		 errorDialog = new MyDialog(this, "고객정보 없음", "입력하신 이름을 가진 고객 정보가 존재하지 않습니다!");
    		 errorDialog.setVisible(true);    	
    	 }
      }
   } // 계좌 입금
   
   public void minus() {
	   if(accounts.size() == 0) {
       	errorDialog = new MyDialog(this, "개좌 개설 고객 없음", "계좌를 개설한 고객이 존재하지 않습니다.");
       	errorDialog.setVisible(true);
	   }
      for(int i=0;i<accounts.size();i++) {
         if(accounts.get(i).name.equals(customerName)) {
            try {
            	accounts.get(i).withdraw(cash);
            	MainView.label[1].setText(" 출금");
            	MainView.label[2].setText("   고객 이름 : " + customerName);
            	MainView.label[3].setText(" 출금액");
            	MainView.label[4].setText("   금액 : " + cash);
            break;
            }
            catch(IllegalArgumentException e) {
            	errorDialog = new MyDialog(this, "한도 초과", "출금액이 예금액보다 많습니다.(한도초과)");
            	errorDialog.setVisible(true);
            	break;
            }
         }
    	 if(i == accounts.size()-1) {
    		 errorDialog = new MyDialog(this, "고객정보 없음", "입력하신 이름을 가진 고객 정보가 존재하지 않습니다!");
    		 errorDialog.setVisible(true);  
    	 }
      } 
   } // 계좌 출금
      public void check() {
       if(accounts.size() == 0) {
           errorDialog = new MyDialog(this, "개좌 개설 고객 없음", "계좌를 개설한 고객이 존재하지 않습니다.");
           errorDialog.setVisible(true);
       }
       for(int i=0;i<accounts.size();i++) {
          if(accounts.get(i).name.equals(customerName)) {
        	  BankOption.label1.setText("   고객 이름 :  " + customerName);
        	  BankOption.label2.setText("   금액 :  " + accounts.get(i).getBalance());
             break;
          }
         if(i == accounts.size()-1) {
            errorDialog = new MyDialog(this, "고객정보 없음", "입력하신 이름을 가진 고객 정보가 존재하지 않습니다!");
            errorDialog.setVisible(true);       
         }
       }
    }   
}