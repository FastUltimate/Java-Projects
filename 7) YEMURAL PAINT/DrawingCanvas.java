/**
 * @(#)DrawingCanvas.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2015/2/2
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener{
	int drawingState = 1;
	double x1[],y1[],x2[],y2[];
	int n;
	Color color[];
	Line2D free[];
	Line2D line[];
	Rectangle2D rect[];
	RoundRectangle2D roundRect[];
	Ellipse2D ellipse[];
	int type[] = new int[2];
	double roundRate = 10;	
	public DrawingCanvas(int drawingState)
	{
		this.drawingState = drawingState;
		free = new Line2D[2];
		line = new Line2D[2];
		rect = new Rectangle2D[2];
		roundRect = new RoundRectangle2D[2];
		ellipse = new Ellipse2D[2];
		x1=new double[2];
		y1=new double[2];
		x2=new double[2];
		y2=new double[2];
		color = new Color[2];
		n=1;
		
		for(int i=0; i<type.length; i++)
			type[i] = 0;
			
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void setDrawingState(int drawingState)
	{
		this.drawingState = drawingState;
	}
	
	public void setDrawingColor(Color c)
	{
		this.color[n-1] = c;
	}
	
	public void setRoundRate(String rate)
	{
		if(rate.equals("") || Double.parseDouble(rate) < 0)
		{
			this.roundRate = Double.parseDouble(rate);
		}
		else
		{
			this.roundRate = Double.parseDouble(rate);
		}
	}
	
	public void setClear()
	{
		for(int i=0; i<n; i++)
		{
			x1[i] = 0;
			x2[i] = 0;
			y1[i] = 0;
			y2[i] = 0;
		}
	}
	
	public void setLine(int x1i,int y1i,int x2i,int y2i)
	{x1[n]=x1i;y1[n]=y1i;x2[n]=x2i;y2[n]=y2i;n++;}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D)g;
		g2.setFont(new Font("Serif",Font.BOLD,24));
		g2.setColor(color[0]);
		g2.setStroke(new BasicStroke(2.0f));
		for(int i=1; i<n; i++)
		{
			if(n >= color.length)
			{
				Color[] temp = new Color[color.length*2];
				System.arraycopy(color, 0, temp, 0, color.length);
				color = temp;
			}
			
			if(n >= type.length)
			{
				int[] temp = new int[type.length*2];
				System.arraycopy(type, 0, temp, 0, type.length);
				type = temp;
			}

			if(n >= free.length)
			{
				Line2D[] temp = new Line2D[free.length*2];
				System.arraycopy(free, 0, temp, 0, free.length);
				free = temp;
			}
			
			if(n >= line.length)
			{
				Line2D[] tempLine = new Line2D[line.length*2];
				System.arraycopy(line, 0, tempLine, 0, line.length);
				line = tempLine;
			}
			
			if(n >= rect.length)
			{
				Rectangle2D[] temp = new Rectangle2D[rect.length*2];
				System.arraycopy(rect, 0, temp, 0, rect.length);
				rect = temp;
			}
			
			if(n >= roundRect.length)
			{
				RoundRectangle2D[] temp = new RoundRectangle2D[roundRect.length*2];
				System.arraycopy(roundRect, 0, temp, 0, roundRect.length);
				roundRect = temp;
			}
			
			if(n >= ellipse.length)
			{
				Ellipse2D[] temp = new Ellipse2D[ellipse.length*2];
				System.arraycopy(ellipse, 0, temp, 0, ellipse.length);
				ellipse = temp;
			}	
						
			if(n >= x1.length)
			{
			    double[] tempX1 = new double[x1.length*2];
	    		System.arraycopy(x1, 0, tempX1, 0, x1.length);
	    		x1 = tempX1;				
			}
			
			if(n >= x2.length)
			{
			    double[] tempX2 = new double[x2.length*2];
	    		System.arraycopy(x2, 0, tempX2, 0, x2.length);
	    		x2 = tempX2;				
			}
			
			if(n >= y1.length)
			{
	    		double[] tempY1 = new double[y1.length*2];
	    		System.arraycopy(y1, 0, tempY1, 0, y1.length);
	    		y1 = tempY1;				
			}
			
			if(n >= y2.length)
			{
				double[] tempY2 = new double[y2.length*2];
	    		System.arraycopy(y2, 0, tempY2, 0, y2.length);
	    		y2 = tempY2;
			}
			
			if(drawingState == 0 && type[i] == 0)
			{
				free[i]=new Line2D.Double(x1[i-1],y1[i-1],x1[i],y1[i]);
				g2.draw(free[i]);
				g2.setColor(color[i]);
				type[i] = -1;
			}
			else if(drawingState == 1 && type[i] == 0)			
			{
				line[i]=new Line2D.Double(x1[i],y1[i],x2[i],y2[i]);
				g2.draw(line[i]);
				g2.setColor(color[i]);
				type[i] = 1;
			}
			else if(drawingState == 2 && type[i] == 0)
			{
				if(x1[i]<x2[i] && y1[i]<y2[i])
				{
					rect[i]=new Rectangle.Double(x1[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else if(x1[i]<x2[i] && y1[i]>y2[i])
				{
					rect[i]=new Rectangle.Double(x1[i], y2[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else if(x1[i]>x2[i] && y1[i]<y2[i])
				{
					rect[i]=new Rectangle.Double(x2[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else
				{
					rect[i]=new Rectangle.Double(x2[i], y2[i], Math.abs(x1[i]-x2[i]), Math.abs(y1[i]-y2[i]));
				}
				g2.draw(rect[i]);
				g2.setColor(color[i]);
				type[i] = 2;	
			}
			else if(drawingState == 3 && type[i] == 0)
			{
				if(x1[i]<x2[i] && y1[i]<y2[i])
				{
					roundRect[i]=new RoundRectangle2D.Double(x1[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]), roundRate, roundRate);
				}
				else if(x1[i]<x2[i] && y1[i]>y2[i])
				{
					roundRect[i]=new RoundRectangle2D.Double(x1[i], y2[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]), roundRate, roundRate);
				}
				else if(x1[i]>x2[i] && y1[i]<y2[i])
				{
					roundRect[i]=new RoundRectangle2D.Double(x2[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]), roundRate, roundRate);
				}
				else
				{
					roundRect[i]=new RoundRectangle2D.Double(x2[i], y2[i], Math.abs(x1[i]-x2[i]), Math.abs(y1[i]-y2[i]), roundRate, roundRate);
				}
				g2.draw(roundRect[i]);
				g2.setColor(color[i]);
				type[i] = 3;				
			}
			else if(drawingState == 4 && type[i] == 0)
			{
				if(x1[i]<x2[i] && y1[i]<y2[i])
				{
					ellipse[i]=new Ellipse2D.Double(x1[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else if(x1[i]<x2[i] && y1[i]>y2[i])
				{
					ellipse[i]=new Ellipse2D.Double(x1[i], y2[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else if(x1[i]>x2[i] && y1[i]<y2[i])
				{
					ellipse[i]=new Ellipse2D.Double(x2[i], y1[i], Math.abs(x2[i]-x1[i]), Math.abs(y2[i]-y1[i]));
				}
				else
				{
					ellipse[i]=new Ellipse2D.Double(x2[i], y2[i], Math.abs(x1[i]-x2[i]), Math.abs(y1[i]-y2[i]));
				}
				g2.draw(ellipse[i]);
				g2.setColor(color[i]);
				type[i] = 4;
			}
			
			if(type[i] == -1)
			{
				g2.draw(free[i]);
				g2.setColor(color[i]);
			}
			else if(type[i] == 1)
			{
				g2.draw(line[i]);
				g2.setColor(color[i]);
			}
			else if(type[i] == 2)
			{
				g2.draw(rect[i]);
				g2.setColor(color[i]);
			}
			else if(type[i] == 3)
			{
				g2.draw(roundRect[i]);
				g2.setColor(color[i]);				
			}
			else if(type[i] == 4)
			{
				g2.draw(ellipse[i]);
				g2.setColor(color[i]);
			}
		}			
	}
	
	//MouseListener
	public void mouseClicked(MouseEvent e)
	{}
	
	public void mousePressed(MouseEvent e)
	{
		if(drawingState == 1 || drawingState == 2 || drawingState == 3 || drawingState == 4)
		{
			x1[n]=e.getX();
			y1[n]=e.getY();			
		}
		else if(drawingState == 0)
		{
			x1[n]=e.getX();
			y1[n]=e.getY();
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		if(drawingState == 1 || drawingState == 2 || drawingState == 3 || drawingState == 4)
		{
			x2[n]=e.getX();
			y2[n]=e.getY();
			n++;
			repaint();			
		}
	}
	public void mouseEntered(MouseEvent e)
	{ }
	public void mouseExited(MouseEvent e)
	{ }
	//MouseMotionListener
	public void mouseDragged(MouseEvent e)
	{
		if(drawingState == 1 || drawingState == 2)
		{
			x2[n]=e.getX();
			y2[n]=e.getY();
			repaint();		
		}
	}
	public void mouseMoved(MouseEvent e)
	{}
}