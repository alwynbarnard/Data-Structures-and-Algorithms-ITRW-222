/*  ALWYN BARNARD 28430093
	ASSIGNMENT M1
	ITRW 222
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Minefield extends JFrame
{
	int ROWS = 3;
	int COLS = 3;
	JButton[][] grid = new JButton[ROWS][COLS];
	JPanel panel = new JPanel();
	JFrame frame = this;

	public Minefield()
	{
		panel.setLayout(new GridLayout(ROWS,COLS));

		for (int r=0; r<ROWS; r++)
		{
			for (int c=0; c<COLS; c++)
			{
				grid[r][c] = new JButton(""+r+" "+c);
			}
		}
		//Add to panel
		for (int r=0; r<ROWS; r++)
		{
			for (int c=0; c<COLS; c++)
			{
				panel.add(grid[r][c]);
			}
		}
		ButtonHandler buttonHandler = new ButtonHandler();
		for (int r=0; r<ROWS; r++)
		{
			for (int c=0; c<COLS; c++)
			{
				grid[r][c].addActionListener(buttonHandler);
			}
		}
		frame.add(panel);
	}
	public static void main(String[] args)
	{
		new Minefield();
	}
	
	public class ButtonHandler implements ActionListener
	{
		JOptionPane paneOutput = new JOptionPane();
		public void actionPerformed(ActionEvent e)
		{
			String message = ((JButton)e.getSource()).getText();
			paneOutput.showMessageDialog(null,message);
		}
	}
}