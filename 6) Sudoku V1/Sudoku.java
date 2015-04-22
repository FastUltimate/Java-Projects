/**
 * @(#)Sudoku.java
 *
 *
 * @Mustafa YEMURAL
 * @version 1.00 2015/1/25
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*; 
import javax.swing.border.*;
import javax.swing.text.*;

class JTextFieldLimit extends PlainDocument{
	private int limit;
	JTextFieldLimit(int limit)
	{
		super();
		this.limit = limit;
	}
	
	JTextFieldLimit(int limit, boolean  upper)
	{
		super();
		this.limit = limit;
	}
	
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
		if(str==null)
			return;
		if((getLength() + str.length()) <= limit)
			super.insertString(offset, str, attr);
	}	
}

class GUIDesign extends JFrame implements ActionListener{
	JTextArea[][] boxes = new JTextArea[9][9];
	JPanel sudokuArea;
	JButton newButton, checkButton;
	Container c;
	
	public GUIDesign()
	{
		super("YEMURAL Sudoku V1");
		c = getContentPane();
		c.setLayout(null);
		
		sudokuArea = new JPanel();
		sudokuArea.setLayout(new GridLayout(9, 9));
		
		for(int ROW=0; ROW<boxes.length; ROW++)
		{
			for(int COL=0; COL<boxes[0].length; COL++)
			{
				boxes[ROW][COL] = new JTextArea();
				boxes[ROW][COL].setBorder(new LineBorder(Color.BLACK, 2));
				boxes[ROW][COL].setFont(new Font("Arial", Font.PLAIN, 32));
				boxes[ROW][COL].setForeground(Color.RED);
				boxes[ROW][COL].setDocument(new JTextFieldLimit(1));
				
				if((COL < 3 && ROW != 3 && ROW != 4 && ROW != 5) || (COL > 5 && ROW != 3 && ROW != 4 && ROW != 5) || (COL > 2 && COL < 6 && (ROW == 3 || ROW == 4 || ROW == 5)))
				{
					boxes[ROW][COL].setBackground(Color.darkGray);
				}
				sudokuArea.add(boxes[ROW][COL]);
			}
		}
		c.add(sudokuArea);
		sudokuArea.setBounds(30, 40, 400, 400);
		
		checkButton = new JButton("Check Solution");
		newButton = new JButton("New Game");
		newButton.addActionListener(this);
		checkButton.addActionListener(this);
		c.add(checkButton);
		c.add(newButton);
		
		checkButton.setBounds(60, 460, 150, 50);
	    newButton.setBounds(240, 460, 150, 50);
		
		this.setSize(460, 550);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.show(true);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); // Windows görünümü için...
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e) {}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newButton)
		{
			String[][] traces = {
					{"5", "3", "", "", "7", "", "", "", ""},
					{"6", "", "", "1", "9", "5", "", "", ""},
					{"", "9", "8", "", "", "", "", "6", ""},
					{"8", "", "", "", "6", "", "", "", "3"},
					{"4", "", "", "8", "", "3", "", "", "1"},
					{"7", "", "", "", "2", "", "", "", "6"},
					{"", "6", "", "", "", "", "2", "8", ""},
					{"", "", "", "4", "1", "9", "", "", "5"},
					{"", "", "", "", "8", "", "", "7", "9"}};
					
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++)
				{
					boxes[i][j].setText(traces[i][j]);
					if(!traces[i][j].equals(""))
					{
						boxes[i][j].setEditable(false);
						boxes[i][j].setForeground(Color.MAGENTA);
					}
				}
		}
		
		if(e.getSource() == checkButton)
		{
			int[][] sudoku = new int[9][9];
			
	    	for(int i=0; i<sudoku.length; i++)
	    	{
	    		for(int j=0; j<sudoku[0].length; j++)
	    		{
	    			if(!boxes[i][j].getText().equals(""))
	    			{
	    				sudoku[i][j] = Integer.parseInt(boxes[i][j].getText());	    				
	    			}
					else
					{
						sudoku[i][j] = 0;
					}
	    		}
	    	}
    	
			if(isValid(sudoku))
				JOptionPane.showMessageDialog(null, "That is valid!");
			else
				JOptionPane.showMessageDialog(null, "That is invalid!");				
		}
	}
	
	public boolean isValid(int[][] grid)
    {
    	for(int i=0; i<grid.length; i++)
    	{
    		for(int j=0; j<grid[0].length; j++)
    		{
    			if(grid[i][j]<1 || grid[i][j]>9 || !isValid(i, j, grid))
    				return false;
    		}
    	}
    	return true;
    }
    
    public boolean isValid(int i, int j, int[][] grid)
    {
    	for(int row=0; row<9; row++)
    		if(row != i && grid[row][j] == grid[i][j])
    			return false;
    			
    	for(int column=0; column<9; column++)
    		if(column != j && grid[i][column] == grid[i][j])
    			return false;
    			
    	for(int row=(i/3)*3; row<(i/3)*3+3; row++)
    		for(int col=(j/3)*3; col<(j/3)*3+3; col++)
    			if(row != i && col != j && grid[row][col] == grid[i][j])
    				return false;
    	
    	return true;
    }
}

public class Sudoku {
    public static void main(String[] args) {
        new GUIDesign();
    }
}
