package FinalTest;

import java.io.Serializable;

public class Account implements Serializable {
	   int balance, minus; // 잔액 필드
	   String name; // 고객 이름
	   

	   public Account(int money, String y) {
	      balance = money;
	      name = y;
	      minus = 0;
	   } // 식별, 잔액, 이름
	   
	   public Account(int money, String y, int min) {
		      balance = money;
		      name = y;
		      minus = min;		   
	   }
	   public void deposit(int a) {
	      if( a > 0 )
	      balance += a;
	      else
	         throw new IllegalArgumentException();
	   } // 예금하는 메소드
	   
	   public void withdraw(int a) {
	      if( a <= balance + minus)
	      balance -= a;
	      else
	         throw new IllegalArgumentException();
	   } // 인출하는 메소드
	   
	   public int getBalance() {
	      return balance;
	   } // 잔액확인
	   
	   public int real() {
		   return minus;
	   } // 마이너스액만 반환
	   
	   public int canwithdraw() {
		   return balance + minus;
	   } //출금가능 잔액 확인
}