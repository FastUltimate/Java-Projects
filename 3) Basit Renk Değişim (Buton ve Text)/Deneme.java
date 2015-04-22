/**
 * @(#)Deneme.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2014/7/23
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Deneme extends JFrame implements ActionListener{
	JPanel alt;
	JButton yellow, red;
	JTextArea text;
	Container c;
	
	public Deneme()
	{	
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		yellow = new JButton("Sari");
		yellow.addActionListener(this);
		red = new JButton("Kirmizi");
		red.addActionListener(this);
		
		yellow.setBackground(Color.BLACK);
		red.setBackground(Color.BLACK);
		yellow.setForeground(Color.WHITE);
		red.setForeground(Color.WHITE);
		
		alt = new JPanel();
		alt.add(yellow);
		alt.add(red);
		
		text = new JTextArea();
		text.setText("Deneme Yazi");
		
		c.add(text,BorderLayout.CENTER);
		c.add(alt,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.show();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == yellow)
		{
			text.setForeground(Color.WHITE);
			text.setBackground(Color.YELLOW);
			yellow.setBackground(Color.YELLOW);
			red.setBackground(Color.BLACK);
		}
		else if(e.getSource() == red)
		{
			text.setForeground(Color.WHITE);
			text.setBackground(Color.RED);
			yellow.setBackground(Color.BLACK);
			red.setBackground(Color.RED);
		}
	}
	
    public static void main(String[] args) {
        new Deneme();
    }
}
