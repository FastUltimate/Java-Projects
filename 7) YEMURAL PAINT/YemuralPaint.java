/**
 * @(#)YemuralPaint.java
 *
 *
 * @Mustafa YEMURAL 
 * @version 1.00 2015/1/29
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

class MainGUI extends JFrame implements ActionListener{
	private JMenuBar jMenuBar1 = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu editMenu = new JMenu("Edit");
	private JMenu drawMenu = new JMenu("Draw");
	private JMenu helpMenu = new JMenu("Help");
	private JMenuItem openItem = new JMenuItem("Open");
	private JMenuItem saveItem = new JMenuItem("Save");
	private JMenuItem exitItem = new JMenuItem("Exit");
	private JMenuItem colorItem = new JMenuItem("Line Color");
	private JMenuItem backColorItem = new JMenuItem("Background Color");
	private JMenuItem deleteItem = new JMenuItem("Clear All");
	private JRadioButtonMenuItem freeItem = new JRadioButtonMenuItem("Free Draw", false);
	private JRadioButtonMenuItem lineItem = new JRadioButtonMenuItem("Line", true);
	private JRadioButtonMenuItem rectItem = new JRadioButtonMenuItem("Rectangle", false);
	private JRadioButtonMenuItem roundedRectItem = new JRadioButtonMenuItem("Rounded Rectangle", false);
	private JRadioButtonMenuItem elipsItem = new JRadioButtonMenuItem("Ellipse", false);
	private JRadioButtonMenuItem textItem = new JRadioButtonMenuItem("Text", false);
	private ButtonGroup drawGroup1 = new ButtonGroup();
	
	private JToolBar toolBar1 = new JToolBar("Drawing");
	private JButton freeTool = new JButton(new ImageIcon("images/freeImg.gif"));
	private JButton lineTool = new JButton(new ImageIcon("images/lineImg.gif"));
	private JButton rectTool = new JButton(new ImageIcon("images/rectImg.gif"));
	private JButton roundRectTool = new JButton(new ImageIcon("images/roundedRectImg.gif"));
	private JButton elipsTool = new JButton(new ImageIcon("images/elipsImg.gif"));
	private JButton textTool = new JButton(new ImageIcon("images/textImg.gif"));
	
	int drawingState = 1;
	private DrawingCanvas drawingPnl = new DrawingCanvas(drawingState);
	Container c;
	Color color;
	Color backColor;
	
	public MainGUI()
	{
		super("YEMURAL Paint v1");
		
		c = this.getContentPane();
		c.setLayout(null);
		
		c.add(drawingPnl);
		drawingPnl.setBackground(Color.WHITE);
		drawingPnl.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		drawingPnl.setBounds(80, 0, 400, 400);
		drawingPnl.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, false));
		
		c.add(toolBar1);
		toolBar1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		toolBar1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		freeTool.setSize(30,30);
		toolBar1.add(freeTool);
		freeTool.addActionListener(this);
			
		lineTool.setSize(30,30);
		toolBar1.add(lineTool);
		rectTool.setSize(30,30);
		toolBar1.add(rectTool);
		roundRectTool.setSize(30,30);
		toolBar1.add(roundRectTool);
		elipsTool.setSize(30,30);
		toolBar1.add(elipsTool);
		textTool.setSize(30,30);
		toolBar1.add(textTool);
		toolBar1.setFloatable(false);
		toolBar1.setBounds(0,0,80,250);
		
		// Menu System:
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		editMenu.add(colorItem);
		colorItem.addActionListener(this);
		editMenu.add(backColorItem);
		backColorItem.addActionListener(this);
		editMenu.addSeparator();
		editMenu.add(deleteItem);
		deleteItem.addActionListener(this);
		drawMenu.add(freeItem);
		freeItem.addActionListener(this);
		drawMenu.add(lineItem);
		lineItem.addActionListener(this);
		drawMenu.add(rectItem);
		rectItem.addActionListener(this);
		drawMenu.add(roundedRectItem);
		roundedRectItem.addActionListener(this);
		drawMenu.add(elipsItem);
		elipsItem.addActionListener(this);
		drawMenu.add(textItem);
		textItem.addActionListener(this);
		
		drawGroup1.add(freeItem);
		drawGroup1.add(lineItem);
		drawGroup1.add(rectItem);
		drawGroup1.add(roundedRectItem);
		drawGroup1.add(elipsItem);
		drawGroup1.add(textItem);
				
		jMenuBar1.add(fileMenu);
		jMenuBar1.add(editMenu);
		jMenuBar1.add(drawMenu);
		jMenuBar1.add(helpMenu);
		
		setJMenuBar(jMenuBar1);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows görünümü için...
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e) {}
		
		c.setBackground(SystemColor.activeCaption);
	}

	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == freeItem)
		{
			drawingPnl.setDrawingState(0);
		}
		
		if(e.getSource() == lineItem)
		{
			drawingPnl.setDrawingState(1);
		}
		
		if(e.getSource() == rectItem)
		{
			drawingPnl.setDrawingState(2);
		}
		
		if(e.getSource() == roundedRectItem)
		{
			drawingPnl.setRoundRate(JOptionPane.showInputDialog(this, "Please insert round rate(Default: 10):", "Round Rate", JOptionPane.PLAIN_MESSAGE));
			drawingPnl.setDrawingState(3);
		}
		
		if(e.getSource() == elipsItem)
		{
			drawingPnl.setDrawingState(4);
		}
		
		if(e.getSource() == textItem)
		{
			drawingPnl.setDrawingState(5);
		}
		
		if(e.getSource() == colorItem)
		{
			color=JColorChooser.showDialog(null,"Choose Color",color);
			drawingPnl.setDrawingColor(color);
		}
		
		if(e.getSource() == backColorItem)
		{
			backColor=JColorChooser.showDialog(null,"Choose Color",backColor);
			drawingPnl.setBackground(backColor);
		}
		
		if(e.getSource() == freeTool)
		{
			drawingPnl.setDrawingState(0);
		}
		
		if(e.getSource() == deleteItem)
		{
			drawingPnl.setClear();
		}
	}
}

public class YemuralPaint {
    public static void main(String[] args) {
        MainGUI GUI = new MainGUI();
        GUI.setSize(500, 500);
		GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUI.setResizable(true);
		GUI.setLocation(400, 200);
		GUI.show(true);
    }
}