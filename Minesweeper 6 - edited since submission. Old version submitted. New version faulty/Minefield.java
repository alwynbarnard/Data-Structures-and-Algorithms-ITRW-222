//Alwyn Barnard 28430093

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Random;

public class Minefield extends JFrame
{
	final int ROWS = 10;
	final int COLS = 10;
	final int BOMBS = 10;
		
	private MSCell field[][];
	
	private JButton[][] grid = new JButton[ROWS+2][COLS+2];
	JPanel panel;
	JLabel tempLabel;
	private final GridBagLayout layout;
	private final GridBagConstraints gbc;
	
	public Minefield()
	{
				
		
		super("Tesing Buttons");
		MouseHandler mh = new MouseHandler();
	
		panel = new JPanel();
		layout = new GridBagLayout();
		panel.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int row, col;
		for (row=0; row<ROWS+2; row++)
			for (col = 0; col<COLS+2; col++)
			{
				gbc.gridx = row;
				gbc.gridy = col;
				grid[row][col] = new JButton(row +","+col);
				panel.add(grid[row][col],gbc);
				grid[row][col].addMouseListener (mh);			
			}
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		
		// data
		field= new MSCell[ROWS+2][COLS+2];
		initialiseField();
		plantBombs();
		calculateBombs();
		displayField();
	}

	public void initialiseField()
	{
		for (int i=0; i<ROWS+2;i++)
		{
			for (int j=0; j<COLS+2; j++)
			{
				field[i][j]=new MSCell();
			}
		}
	}
	
	public void plantBombs()
	{
		Random randomNumbers = new Random();
		//plant BOMBS number of bombs randomly in grid
		int bombsPlanted=0;
		int bombRow = 0;
		int bombCol = 0;
		while (bombsPlanted < BOMBS)
		{
			bombRow = randomNumbers.nextInt(ROWS)+1; 
			bombCol = randomNumbers.nextInt(COLS)+1;
			System.out.println( bombRow +" " + bombCol);
			if (!field[bombRow][bombCol].isBomb()) 
			{
				 field[bombRow][bombCol].setBomb();
				 bombsPlanted++;
			}
		}
	}

	
	public void calculateBombs()
	{
		int countBombs= 0;
		for (int i=1; i<=ROWS;i++)
		{
			for (int j=1; j<=COLS; j++)
			{	
	            countBombs=0;
				if (!field[i][j].isBomb())
				{
					for (int a=-1; a<=1; a++)
					{
						for (int b=-1; b<=1; b++)
						{
							if (field[i+a][j+b].isBomb() )
							{
								countBombs++;
							}
						}	
					}	
					field[i][j].setValue(countBombs);
				}
			}
		}
	}
	
	
	public void displayField()
	{
		for (int i=0; i<=ROWS+1;i++)
		{
			for (int j=0; j<=COLS+1; j++)
			{  
				if (i==0 || j==0 || i==ROWS+1 || j==COLS+1)
				{
					field[i][j].setValue(-1);
					grid[i][j].setText(field[i][j].toString(false));
					grid[i][j].setBackground(Color.BLACK);
				}
				grid[i][j].setText(field[i][j].toString(false));
				if (field[i][j].isRevealed())
				{
					grid[i][j].setText(field[i][j].toString(true));
				}
			}
			repaint();
			revalidate();
		}
	}
	
	public void Win_Lose(boolean winlose)
	{
		int revealCount=0;
		int bombCount=0;
		
		if (winlose == false)
		{
			JOptionPane pane = new JOptionPane();
			pane.showMessageDialog(null,"Ahhh you've lost");
			System.exit(0);
		}
		else
		{
			for (int i=1; i<=ROWS;i++)
			{
				for (int j=1; j<=COLS; j++)
				{  
					if (field[i][j].isRevealed() == true)
					{
						revealCount++;
					}
					if (field[i][j].isBomb() == true)
					{
						bombCount++;
					}
				}
			
			}
			if ((revealCount+bombCount)==(ROWS*COLS))
			{
				System.out.println("You've won");
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null,"You've WON!!!");
			}
			
		}
	}
	public void recursion(int rowsValue, int columnValue)
	{
		//tempLabel= new JLabel("@");
		
		if ((field[rowsValue][columnValue].getValue() == 0) && (!field[rowsValue][columnValue].isBomb()))
		{
			for (int a=-1; a<=1; a++)
			{
				for (int b=-1; b<=1; b++)
				{
					if (grid[rowsValue+a][columnValue+b].getBackground() != Color.BLACK)
					{
						
							gbc.gridx = rowsValue+a;
							gbc.gridy = columnValue+b;
							panel.remove(grid[rowsValue+a][columnValue+b]);
							//field[rowsValue+a][columnValue+b].setValue(0);
							grid[rowsValue+a][columnValue+b].setText(" "+field[rowsValue+a][columnValue+b].toString(true)+" ");
							
							field[rowsValue+a][columnValue+b].setFlagged(false);
							field[rowsValue+a][columnValue+b].setRevealed();
							tempLabel = new JLabel();
							tempLabel.setText(field[rowsValue+a][columnValue+b].toString(true));
							panel.add(tempLabel,gbc);
							
						/* if (field[rowsValue+a][columnValue+b].getValue() == 0)
						{
							recursion(rowsValue+a,columnValue+b);
						} */ //It doesn't work :'(
					}
					
				}
				
			}	
		}
		revalidate();
		repaint();
	}
	
	
	// GUI event handlers
	// inner class for MouseListener
	private class MouseHandler implements MouseListener
	{
		@Override 
		 
		 public void mouseClicked(MouseEvent me) 
		 {
			//left button
			if (me.getButton()== MouseEvent.BUTTON1)
			{
				Object o= me.getSource();
				tempLabel= new JLabel("@");
				for (int r=1; r<=ROWS; r++)
					for (int c = 1; c<=COLS; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							gbc.gridx = r;
							gbc.gridy = c;
							panel.remove(grid[r][c]);
							tempLabel.setText(" "+field[r][c].toString(true));
							field[r][c].setFlagged(false);
							field[r][c].setRevealed();
							if (field[r][c].isBomb())
							{
								Win_Lose(false);							
							}
							else if(field[r][c].getValue() == 0)
							{
								recursion2(r,c);
								
							}
							else 
							{
								Win_Lose(true);
							}
							panel.add(tempLabel,gbc);
							revalidate();
							repaint();
						}
					}
			}
			// right button
			if (me.getButton()== MouseEvent.BUTTON3)
			{
				Object o= me.getSource();
				tempLabel= new JLabel("!");
				for (int r=1; r<=ROWS; r++)
				{
					for (int c = 1; c<=COLS; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							gbc.gridx = r;
							gbc.gridy = c;
							grid[r][c].setText("!");
							grid[r][c].setBackground(Color.RED);
							field[r][c].setFlagged(true);
							revalidate();
							repaint();
						}
					}
				}
			}	 
		}
		 public void mousePressed(MouseEvent me) {}
		 public void mouseReleased(MouseEvent me) {}
		 public void mouseEntered(MouseEvent me) {}
		 public void mouseExited(MouseEvent me) {}

}

public void recursion2(int rwc, int clc)
{
	//base case
	for (int b=-1; b <2; b++)
	{
		for (int a=-1; a<2; a++)
		{
			if (grid[rwc+b][clc+a].getBackground() != Color.BLACK)
			{
				if(field[rwc+b][clc+a].getValue()==0)
				{
					recursion2(rwc+b,clc+a);
				}
				else
				{
					field[rwc+b][clc+a].setValue(0);
					field[rwc+b][clc+a].setRevealed();
					grid[rwc+b][clc+a].setText("0");
					//panel.add(tempLabel,gbc);
				}
			}
			a++;
		}
	}
	revalidate();
	repaint();
}
	
	/*
		_______________________________
		||0:0|0:1|0:2|0:3|0:4|0:5|0:6||
		||1:0|1:1|1:2|1:3|1:4|1:5|1:6||
		||2:0|2:1|2:2|2:3|2:4|2:5|2:6||
		||3:0|3:1|3:2|3:3|3:4|3:5|3:6||
		||4:0|4:1|4:2|4:3|4:4|4:5|4:6||
		||5:0|5:1|5:2|5:3|5:4|5:5|5:6||
		-------------------------------
		
*/	
		
}


