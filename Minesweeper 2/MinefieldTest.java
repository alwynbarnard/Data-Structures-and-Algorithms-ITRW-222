/*  ALWYN BARNARD 28430093
	ASSIGNMENT MINESWEEPER M2
	ITRW 222
*/
import javax.swing.*;
public class MinefieldTest
{
	public static void main(String[] args)
	{
		Minefield minefield = new Minefield();
		minefield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		minefield.setSize(300,300);
		minefield.setLocationRelativeTo(null);
		minefield.setVisible(true);
	}
}