/**
 * @(#)OgrenciKaydi.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2014/7/7
 */
 import javax.swing.*;
 
class GenelKisi {
	String isim;
	String adres;
	int yas;
	
	public void GenelKisi(String ad,String adr,int year)
	{
		this.isim = ad;
		this.adres = adr;
		this.yas = year;
	}
	
	public String toString()
	{
		String msg = "Isim: "+this.isim+"\nAdres: "+this.adres+"\nYas: "+this.yas;
		
		return msg;
	}
}

class Ogrenci extends GenelKisi {
	int ogr_no;
	char sinif;
	
	public void ogrenciEkle(String ad,String adr,int year,int no,char sinif_c)
	{
		super.GenelKisi(ad,adr,year);
		this.ogr_no = no;
		this.sinif = sinif_c; 
	}
}

public class OgrenciKaydi 
{
    public static void main(String[] args) 
    {
        int kayit_sayisi = Integer.parseInt(JOptionPane.showInputDialog(null,"Lutfen kac ogrenci kaydi gireceginizi girin: "));
        Object ogrenciler = new Ogrenci[kayit_sayisi];
        
        for(int i=0;i<kayit_sayisi;i++)
        {
        	ogrenciler[i].ogrenciEkle(JOptionPane.showInputDialog(null,"Lütfen Ýsim girin:"),
        							  JOptionPane.showInputDialog(null,"Lütfen Adres girin:"),
        							  Integer.parseInt(JOptionPane.showInputDialog(null,"Yýlý girin:")),
        							  Integer.parseInt(JOptionPane.showInputDialog(null,"Numarayý girin:")),
        							  JOptionPane.showInputDialog(null,"Sýnýfý girin:"));
        }
        
        System.exit(0);
    }
}
