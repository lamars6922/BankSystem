package FinalTest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyDialog extends JDialog {
	JLabel error;
	JPanel panel, errorPanel, btnPanel;
	JButton btn;
   
   public MyDialog(JFrame frame, String Title, String content) {
      super(frame);
      setTitle(Title);
      error = new JLabel(content);

      panel = new JPanel(new BorderLayout());
      errorPanel = new JPanel(new FlowLayout());
      btnPanel = new JPanel(new FlowLayout());
      
      btn = new JButton("돌아가기");
      btn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			MyDialog.this.setVisible(false);
		}
    	  
      });
      errorPanel.add(error);
      btnPanel.add(btn);
      panel.add(errorPanel, BorderLayout.CENTER);
      panel.add(btnPanel, BorderLayout.SOUTH);
      add(panel);
      setSize(350, 150);
   }
}