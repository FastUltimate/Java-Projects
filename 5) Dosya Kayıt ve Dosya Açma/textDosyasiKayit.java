/**
 * @(#)textDosyasiKayit.java
 *
 *
 * @Text Dosyas� Kaydetme
 * @version 1.00 2014/7/28
 */
 import javax.swing.*;
 import javax.swing.event.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.Date;
 import java.io.*;

public class textDosyasiKayit extends JFrame implements ActionListener
{
	JTextArea yazi;
	JButton kayit,ac,trh;
	JPanel kayitPnl;
	Container c;
	
	public textDosyasiKayit()
	{
		super("YEMURAL - Yaz� Y�neticisi v1.0");
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		yazi = new JTextArea("Buraya yaz�n�z� yaz�n.");
		yazi.setLineWrap(true);
		kayit = new JButton("KAYDET");
		kayit.setSize(40,10);
		ac = new JButton("DOSYA A�");
		ac.setSize(40,10);
		kayitPnl = new JPanel();
		trh = new JButton("SADECE TAR�H� KAYDET");
		
		kayitPnl.add(kayit,BorderLayout.CENTER);
		kayitPnl.add(ac,BorderLayout.CENTER);
		kayitPnl.add(trh,BorderLayout.CENTER);
		c.add(yazi,BorderLayout.NORTH);
		c.add(kayitPnl,BorderLayout.SOUTH);
		
		kayit.addActionListener(this);
		ac.addActionListener(this);
		trh.addActionListener(this);
		
		this.setSize(400,300);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.show();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == kayit)
		{
			String dosya_ismi = JOptionPane.showInputDialog(this,"L�tfen kaydetmek istedi�iniz dosya ismini girin: ");
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter("Texts/"+dosya_ismi+".txt"));
				out.write(yazi.getText());
				out.close();
				JOptionPane.showMessageDialog(null,"Metin dosyas� ba�ar�yla kaydedildi!");
			}catch(IOException a){
			}
		}
		
		if(e.getSource() == ac)
		{
			short durum = Short.parseShort(JOptionPane.showInputDialog(this,"L�tfen se�iminizi yap�n: \n[1]Tarih Dosyas� A�\n[2]Normal Yaz� Dosyas� A�"));
			if(durum==1)
			{
				String dosya_ismi = JOptionPane.showInputDialog(this,"L�tfen a�mak istedi�iniz dosya ismini girin: ");
				String str;
				try{
					BufferedReader in = new BufferedReader(new FileReader("Dates/"+dosya_ismi+".tarih"));
					while((str = in.readLine()) != null)
					{
						yazi.setText(str);
					}
					in.close();
				}catch(IOException a){
				}					
			}
			else if(durum==2)
			{
				String dosya_ismi = JOptionPane.showInputDialog(this,"L�tfen a�mak istedi�iniz dosya ismini girin: ");
				String str;
				try{
					BufferedReader in = new BufferedReader(new FileReader("Texts/"+dosya_ismi+".txt"));
					while((str = in.readLine()) != null)
					{
						yazi.setText(str);
					}
					in.close();
				}catch(IOException a){
				}				
			}
		
		}
		if(e.getSource() == trh)
		{
			String dosya_ismi = JOptionPane.showInputDialog(this,"L�tfen tarih dosyas�n�n ismini girin: ");
			Date tarih = new Date();
			tarih.setTime(System.currentTimeMillis());
			String trhstr = tarih.toString();
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter("Dates/"+dosya_ismi+".tarih"));
				out.write(trhstr);
				out.close();
				JOptionPane.showMessageDialog(null,"Tarih dosyas� ba�ar�yla kaydedildi!");
			}catch(IOException a){
			}
		}
	}
	
    public static void main(String[] args) 
    {
        new textDosyasiKayit();
    }
}
