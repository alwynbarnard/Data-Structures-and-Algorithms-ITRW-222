/*
	ALWYN BARNARD 28430093
	MINESWEEPER 7 : FINAL
*/

import javax.swing.JFrame;
import java.awt.Color;
public class MinefieldTest
{  
  public static void main (String[] args)
  { 
	Minefield mineFrame = new Minefield();
    mineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mineFrame.setSize(700,700);
	mineFrame.setLocationRelativeTo(null);
	mineFrame.setVisible(true);
	
	
  }
}