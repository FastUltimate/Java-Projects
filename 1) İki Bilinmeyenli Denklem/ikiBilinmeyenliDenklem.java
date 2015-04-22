/**
 * @(#)ikiBilinmeyenliDenklem.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2014/7/6
 */
import javax.swing.*;

class Denklem {
	int a,b,c;
	
	public void Denklem()
	{
		this.a=0;
		this.b=0;
		this.c=0;
	}
	
	public void katsayiAl(int k,int l,int m)
	{
		this.a=k;
		this.b=l;
		this.c=m;
	}
	
	private double deltaHesapla()
	{
		int d = (this.b)*(this.b)-4*this.a*this.c;
		
		return d;
	}
	
	public String toString()
	{
		double delta = this.deltaHesapla();
		String msg;
		double x,y;
		
		if(delta>0)
		{
			x=((-this.b)-Math.sqrt(delta))/2*this.a;
			y=((-this.b)+Math.sqrt(delta))/2*this.a;
			msg = "Denklem iki koke sahiptir. \nIlk Kok: "+x+"\nIkinci Kok: "+y;
		}
		else if(delta==0)
		{
			x=(-this.b)/2*this.a;
			msg = "Denklem tek koke sahip ve bu kok: "+x;
		}
		else
		{
			msg = "Denklemin reel koku yok.";
		}
		
		return msg;
	}
}

public class ikiBilinmeyenliDenklem {
	
    public static void main(String[] args) {
    	Denklem kokler = new Denklem();
    	int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Lutfen ilk katsayiyi girin: "));
    	int b = Integer.parseInt(JOptionPane.showInputDialog(null, "Lutfen ikinci katsayiyi girin: "));
    	int c = Integer.parseInt(JOptionPane.showInputDialog(null, "Lutfen ucuncu katsayiyi girin: "));
    	
    	kokler.katsayiAl(a,b,c);
    	JOptionPane.showMessageDialog(null,kokler);
    	
    	System.exit(0);
    }
}
