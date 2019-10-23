/*  ALWYN BARNARD 28430093
	ASSIGNMENT MINESWEEPER M2
	ITRW 222
*/
import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
public class Minefield extends JFrame
{
	int ROWS = 5;
	int COLS = 5;
	JButton[][] grid = new JButton[ROWS][COLS];
	JPanel panel = new JPanel();
	JFrame frame = this;
	
	public Minefield()
	{
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();

		frame.setTitle("Minesweeper");
		panel.setLayout(gridBag);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		MouseHandler mouseHandler = new MouseHandler();
		
		//Add to panel
		constraints.weightx = 0.5;
		for (int rr=0; rr<ROWS; rr++)
		{
			for (int cc=0; cc<COLS; cc++)
			{
				grid[rr][cc] = new JButton(""+rr+","+cc);
				constraints.gridx = rr;
				constraints.gridy = cc;
				panel.add(grid[rr][cc],constraints);
				grid[rr][cc].addMouseListener(mouseHandler);
			}
		}
		frame.add(panel);
	}
	public static void main(String[] args)
	{
		new Minefield();
	}
	
	public class MouseHandler implements MouseListener
	{
		public void mousePressed(MouseEvent me) {}
		public void mouseReleased(MouseEvent me) { }
		public void mouseEntered(MouseEvent me) { }
		public void mouseExited(MouseEvent me) { }
		public void mouseClicked(MouseEvent me) 
		{ 
		
		JButton butt = (JButton) me.getSource();
		GridBagConstraints constraints = new GridBagConstraints();
		
		for (int r=0; r<ROWS; r++)
		{
			for (int c=0; c<COLS; c++)
			{
				if (butt == grid[c][r])
				{
					constraints.gridx = c;
					constraints.gridy = r;
					if (me.getButton() == MouseEvent.BUTTON1)
					{
						if (!grid[c][r].getText().contains("%"))
						{
							panel.remove(grid[c][r]);
							panel.add(new JLabel(grid[c][r].getText()),constraints);
						}
					}
					else if (me.getButton() == MouseEvent.BUTTON3)
					{
						if (grid[c][r].getText().contains("&"))
						{
							grid[c][r].setText("&"+r+c);
						}
						else
						{
							grid[c][r].setText(grid[c][r].getText() + "&");
						}
					}
					panel.revalidate();
					panel.repaint();
				}
			}
		}
		
			
		}
	}
}