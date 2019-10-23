//Alwyn Barnard 28430093


import javax.swing.JFrame;
public class MinefieldTest
{  
  public static void main (String[] args)
  { 
	Minefield mineFrame = new Minefield();
    mineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mineFrame.setSize(500,500);
	mineFrame.setLocationRelativeTo(null);
	mineFrame.setVisible(true);
  }
}