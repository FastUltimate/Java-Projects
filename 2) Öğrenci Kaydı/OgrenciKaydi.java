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
        	ogrenciler[i].ogrenciEkle(JOptionPane.showInputDialog(null,"L�tfen �sim girin:"),
        							  JOptionPane.showInputDialog(null,"L�tfen Adres girin:"),
        							  Integer.parseInt(JOptionPane.showInputDialog(null,"Y�l� girin:")),
        							  Integer.parseInt(JOptionPane.showInputDialog(null,"Numaray� girin:")),
        							  JOptionPane.showInputDialog(null,"S�n�f� girin:"));
        }
        
        System.exit(0);
    }
}
