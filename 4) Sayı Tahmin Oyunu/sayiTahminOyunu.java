/**
 * @(#)sayiTahminOyunu.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2014/7/26
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class sayiTahminOyunu extends JFrame implements ActionListener{
	JTextField tahmin, bilgi, puan;
	JButton t, ob;
	JRadioButton on, yuz, bin;
	JPanel alt, orta, ust;
	Container c;
	JLabel lSayi, lAralik, lPuan, lBilgi;
	ButtonGroup grp;
	short durum=1;
	String info;
	int point=10;
	int temp_sayi = 0;
	int sayac = 1;
	
	public sayiTahminOyunu()
	{
		super("YEMURAL - Say� Tahmin Oyunu v1.0");
		c = this.getContentPane();
		
		ust = new JPanel();
		orta = new JPanel();
		alt = new JPanel();
		
		c.add(ust,BorderLayout.NORTH);
		c.add(orta,BorderLayout.CENTER);
		c.add(alt,BorderLayout.SOUTH);
		
		lSayi = new JLabel("L�tfen tahmininizi girin: ");
		ust.add(lSayi,new FlowLayout(FlowLayout.LEFT));
		tahmin = new JTextField(6);
		tahmin.setEditable(false);
		ust.add(tahmin,new FlowLayout(FlowLayout.LEFT));
		t = new JButton("TAHM�N ET");
		t.setEnabled(false);
		ust.add(t,new FlowLayout(FlowLayout.LEFT));
		
		lAralik = new JLabel("L�tfen aral��� se�in: ");
		on = new JRadioButton("0 - 10",true);
		yuz = new JRadioButton("0 - 100",false);
		bin = new JRadioButton("0 - 1000",false);
		grp = new ButtonGroup();
		grp.add(on);
		grp.add(yuz);
		grp.add(bin);
		orta.add(lAralik, BorderLayout.WEST);
		orta.add(on, new FlowLayout(FlowLayout.LEADING));
		orta.add(yuz, new FlowLayout(FlowLayout.LEADING));
		orta.add(bin, new FlowLayout(FlowLayout.LEADING));
		
		lBilgi = new JLabel("B�LG�: ");
		lPuan = new JLabel("PUAN: ");
		bilgi = new JTextField(26);
		puan = new JTextField(26);
		ob = new JButton("OYUNU BA�LAT");
		bilgi.setEditable(false);
		puan.setEditable(false);
		orta.add(lBilgi,new FlowLayout(FlowLayout.LEFT));
		orta.add(bilgi,new FlowLayout(FlowLayout.LEFT));
		orta.add(lPuan,new FlowLayout(FlowLayout.RIGHT));
		orta.add(puan,new FlowLayout(FlowLayout.RIGHT));
		alt.add(ob,BorderLayout.SOUTH);
		
		t.addActionListener(this);
		ob.addActionListener(this);
		on.addActionListener(this);
		yuz.addActionListener(this);
		bin.addActionListener(this);
		
		this.setSize(300,180);
		this.setLocation(400,300);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.show();
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows g�r�n�m� i�in...
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e) {}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == on)
		{
			durum=1;
			point = 10;
			
		}
		else if(e.getSource() == yuz)
		{
			durum=2;
			point = 100;
		}
		else if(e.getSource() == bin)
		{
			durum=3;
			point = 1000;
		}
		
		if(e.getSource() == t)
		{
			try{
				if(tahmin.getText() != null && tahmin.getText() != "" && !tahmin.getText().matches("[^0-9]+"))
				{
					if(Integer.parseInt(tahmin.getText())==temp_sayi)
					{
						JOptionPane.showMessageDialog(null,"TEBR�KLER SAYIYI "+sayac+" TAHM�NDE BULDUNUZ!!!","KAZANDINIZ!",JOptionPane.PLAIN_MESSAGE);
						tahmin.setEditable(false);
						on.setEnabled(true);
						yuz.setEnabled(true);
						bin.setEnabled(true);
						t.setEnabled(false);
						puan.setText("");
						point=10;
						sayac=1;
						bilgi.setText("OYUN B�TT�!");
					}
					else if(Integer.parseInt(tahmin.getText())>temp_sayi)
					{
						bilgi.setText("B�RAZ A�A�I �N");
						switch(durum){
							case 1:
								point -= 1;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=10;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");
								}
								break;
							case 2:
								point -= 5;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=100;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");									
								}	
								break;
							case 3:
								point -= 20;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=1000;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");									
								}
								break;
						}
						sayac += 1;	
						tahmin.setText("");
					}
					else
					{
						bilgi.setText("B�RAZ YUKARI �IK");
						switch(durum){
							case 1:
								point -= 1;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=10;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");									
								}
								break;
							case 2:
								point -= 5;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=100;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");									
								}	
								break;
							case 3:
								point -= 20;
								puan.setText(Integer.toString(point));
								if(point==0)
								{
									JOptionPane.showMessageDialog(null,"Puan�n�z bitti�i i�in kald�n�z. Deneme say�n�z: "+sayac,"OYUN B�TT�!",JOptionPane.ERROR_MESSAGE);
									point=1000;
									bilgi.setText("OYUN B�TT�!");
									tahmin.setEditable(false);
									on.setEnabled(true);
									yuz.setEnabled(true);
									bin.setEnabled(true);
									t.setEnabled(false);
									puan.setText("");									
								}
								break;
						}
						sayac += 1;
						tahmin.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"L�tfen ge�erli bir de�er giriniz!","GE�ERS�Z DE�ER!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(NumberFormatException a)
			{
				JOptionPane.showMessageDialog(null,"L�tfen ge�erli bir de�er giriniz!","GE�ERS�Z DE�ER!",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == ob)
		{
			temp_sayi = this.hesapla(durum);
			JOptionPane.showMessageDialog(null,temp_sayi);
			tahmin.setEditable(true);
			on.setEnabled(false);
			yuz.setEnabled(false);
			bin.setEnabled(false);
			t.setEnabled(true);
			puan.setText(Integer.toString(point));
			tahmin.setText("");
			bilgi.setText("OYUN BA�LADI!");
		}
	}
	
	public int hesapla(short durum)
	{
		int katsayi=10;
		switch(durum){
			case 1:
				katsayi = 10;
				break;
			case 2:
				katsayi = 100;
				break;
			case 3:
				katsayi = 1000;
				break;
		}
		int sayi = (int)(Math.random()*katsayi);
		return sayi;
	}
	
    public static void main(String[] args) {
    	new sayiTahminOyunu();
    }
}
