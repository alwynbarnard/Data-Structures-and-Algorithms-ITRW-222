//Alwyn Barnard 28430093

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.util.Random;

public class Minefield extends JFrame
{
	final int ROWS = 7;
	final int COLS = 7;
	final int BOMBS = 10;
	
	// Data
	private MSCell field[][];

	// GUI components
	private JButton[][] grid = new JButton[ROWS][COLS];
	JPanel panel;
	JLabel tempLabel;
	private final GridBagLayout layout;
	private final GridBagConstraints gbc;
	
	public Minefield()
	{
				
		// GUI components
		super("Tesing Buttons");
		MouseHandler mh = new MouseHandler();
		// create grid for bottons
		panel = new JPanel();
		layout = new GridBagLayout();
		panel.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int row, col;
		for (row=0; row<=ROWS-1; row++)
			for (col = 0; col<=COLS-1; col++)
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
		field = new MSCell[ROWS][COLS];
		initialiseField();
		plantBombs();
		calculateBombs();
		displayField();
	}

	public void initialiseField()
	{
		for (int i=0; i<ROWS;i++)
		{
			for (int j=0; j<COLS; j++)
			{
				field[i][j] = new MSCell();
			}
		}
	}
	
	
	public void plantBombs()
	{
		Random randomNumbers = new Random();
		int bombsPlanted=0;
		int bombRow = 0;
		int bombCol = 0;
		while (bombsPlanted < BOMBS)
		{
			bombRow = randomNumbers.nextInt(ROWS-2)+1;
			bombCol = randomNumbers.nextInt(COLS-2)+1;
			
			if ((!field[bombRow][bombCol].isBomb()) && (bombRow!=0) && (bombRow !=ROWS-1) && (bombCol !=0) && (bombCol!=COLS-1)) // if not yet bomb
			{
				field[bombRow][bombCol].setBomb();
				bombsPlanted++;
				System.out.println( bombRow +" " + bombCol);
			}
		}
	}
	public void displayField()
	{
		for (int i=0; i<=ROWS-1;i++)
		{
			for (int j=0; j<=COLS-1; j++)
			{  
				if (i==0 || j==0 || i == ROWS-1 || j == COLS-1)
				{
					grid[i][j].setText("0");
				}
				
				else if (field[i][j].toString()!="0")
					{
						grid[i][j].setText(field[i][j].toString());
					}
					else
					{
						grid[i][j].setText(" "+i+":"+j);
					}					
			}
			repaint();
			revalidate();
		}
	}
	
	public void calculateBombs()
	{		
		for (int ro = 1; ro < ROWS - 1; ro++)
		{
			for (int co = 1; co < COLS - 1; co++)
			{
				if (!field[ro][co].isBomb())
				{
					int counter = 0;
					for (int x = ro - 1; x <= ro + 1; x++)
					{
						for (int y = co - 1; y <= co + 1; y++)
						{
							if (field[x][y].isBomb())
								counter++;
						}
					}
					
					field[ro][co].setValue(counter);
				}
			}	
		}		
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
				for (int r=1; r<=ROWS-2; r++)
					for (int c = 1; c<=COLS-2; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							gbc.gridx = r;
							gbc.gridy = c;
							panel.remove(grid[r][c]);
							tempLabel.setText(field[r][c].toString());
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
				tempLabel= new JLabel("@");
				for (int r=1; r<=ROWS-1; r++)
					for (int c = 1; c<=COLS-1; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							gbc.gridx = r;
							gbc.gridy = c;
							grid[r][c].setText("  "+r+"%"+c+" ");
							revalidate();
							repaint();
						}
					
					}
			}	 
		 }
		 public void mousePressed(MouseEvent me) {}
		 public void mouseReleased(MouseEvent me) {}
		 public void mouseEntered(MouseEvent me) {}
		 public void mouseExited(MouseEvent me) {}
	}
	
		
		
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

