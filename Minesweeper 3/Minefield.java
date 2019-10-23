
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
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
	int ROWS = 5;
	int COLS = 4;
	private JButton[][] grid = new JButton[ROWS][COLS];
	JPanel panel;
	JLabel tempLabel;
	private final GridBagLayout layout;
	private final GridBagConstraints constraints;
	
	private MSCell field[][] = new MSCell[ROWS][COLS];
	private final int BOMBS=7;
	
	public Minefield()
	{
		super("Tesing Buttons");

		MouseHandler mh = new MouseHandler();
		// create grid for bottons
		panel = new JPanel();
		layout = new GridBagLayout();
		panel.setLayout(layout);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		int row, col;
		
		for (row=0; row<=ROWS-1; row++)
		{
			for (col = 0; col<=COLS-1; col++)
			{
				constraints.gridx = row;
				constraints.gridy = col;
				grid[row][col] = new JButton(row +","+col);
				panel.add(grid[row][col],constraints);
				grid[row][col].addMouseListener (mh);
			}
		}
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		
		initialiseField();
		plantBombs();
		displayField();
		
	}

	// inner class for MouseListener
	private class MouseHandler implements MouseListener
	{
		public void mouseClicked(MouseEvent me) 
		{
		//left button
			if (me.getButton()== MouseEvent.BUTTON1)
			{
				Object o= me.getSource();
				tempLabel= new JLabel("@");
				for (int r=0; r<=ROWS-1; r++)
				{
					for (int c = 0; c<=COLS-1; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							constraints.gridx = r;
							constraints.gridy = c;
							panel.remove(grid[r][c]);
							field[r][c].setRevealed(true);
							tempLabel.setText("  "+r+"*"+c+" ");
							panel.add(tempLabel,constraints);
							revalidate();
							repaint();
						}
					}	
				}
			}
			// right button
			if (me.getButton()== MouseEvent.BUTTON3)
			{
				Object o= me.getSource();
				tempLabel= new JLabel("@");
				for (int r=0; r<=ROWS-1; r++)
				{
					for (int c = 0; c<=COLS-1; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							constraints.gridx = r;
							constraints.gridy = c;
							field[r][c].setRevealed(false);
							field[r][c].setFlagged(true);
							grid[r][c].setText("  "+r+"%"+c+" ");
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
	
	
	public void initialiseField()
	{
		for (int p=0; p<ROWS;p++)
		{
			for (int q=0;q<COLS; q++)
			{
				field[p][q] = new MSCell();
			}
		}				
	}
		
	public void plantBombs()
	{
		Random randomNumbers = new Random();
		for (int bo =0; bo <= BOMBS; bo++)
		{
			int bombRow = randomNumbers.nextInt(ROWS);
			int bombCol = randomNumbers.nextInt(COLS);
			if( field[bombRow][bombCol].isBomb() == true) //If it already has a bomb
			{
				bo--; //then redo the random pic
			}
			else 
			{
				field[bombRow][bombCol].setBomb(true);
				System.out.println(bombRow+" "+bombCol);
			}	
		}
	}
		
	public void displayField()
	{
		for (int a=0; a<=ROWS-1;a++)
		{
			for (int b=0; b<=COLS-1; b++)
			{
				constraints.gridx = a;
				constraints.gridy = b;
				if( field[a][b].isBomb() == true)
				{
					
					grid[a][b].setText("B");
					panel.remove(grid[a][b]);
					panel.add(grid[a][b], constraints);
					revalidate();
					repaint();
				}
				/* else 
				{
					grid[a][b].setText(" "+a+" "+b);
				} */
				
				
			}
		}
	}
	public static void main(String[] args)
	{
		
	}
		
		
}