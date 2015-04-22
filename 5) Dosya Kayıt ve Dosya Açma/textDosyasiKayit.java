/**
 * @(#)textDosyasiKayit.java
 *
 *
 * @Text Dosyasý Kaydetme
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
		super("YEMURAL - Yazý Yöneticisi v1.0");
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		yazi = new JTextArea("Buraya yazýnýzý yazýn.");
		yazi.setLineWrap(true);
		kayit = new JButton("KAYDET");
		kayit.setSize(40,10);
		ac = new JButton("DOSYA AÇ");
		ac.setSize(40,10);
		kayitPnl = new JPanel();
		trh = new JButton("SADECE TARÝHÝ KAYDET");
		
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
			String dosya_ismi = JOptionPane.showInputDialog(this,"Lütfen kaydetmek istediðiniz dosya ismini girin: ");
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter("Texts/"+dosya_ismi+".txt"));
				out.write(yazi.getText());
				out.close();
				JOptionPane.showMessageDialog(null,"Metin dosyasý baþarýyla kaydedildi!");
			}catch(IOException a){
			}
		}
		
		if(e.getSource() == ac)
		{
			short durum = Short.parseShort(JOptionPane.showInputDialog(this,"Lütfen seçiminizi yapýn: \n[1]Tarih Dosyasý Aç\n[2]Normal Yazý Dosyasý Aç"));
			if(durum==1)
			{
				String dosya_ismi = JOptionPane.showInputDialog(this,"Lütfen açmak istediðiniz dosya ismini girin: ");
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
				String dosya_ismi = JOptionPane.showInputDialog(this,"Lütfen açmak istediðiniz dosya ismini girin: ");
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
			String dosya_ismi = JOptionPane.showInputDialog(this,"Lütfen tarih dosyasýnýn ismini girin: ");
			Date tarih = new Date();
			tarih.setTime(System.currentTimeMillis());
			String trhstr = tarih.toString();
			try{
				BufferedWriter out = new BufferedWriter(new FileWriter("Dates/"+dosya_ismi+".tarih"));
				out.write(trhstr);
				out.close();
				JOptionPane.showMessageDialog(null,"Tarih dosyasý baþarýyla kaydedildi!");
			}catch(IOException a){
			}
		}
	}
	
    public static void main(String[] args) 
    {
        new textDosyasiKayit();
    }
}
