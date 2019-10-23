
import javax.swing.JFrame;

public class MSCell
{
	private boolean revealed;
	private boolean bomb;
	private int value;
	private boolean flagged;
	
	public MSCell() //Constructor to initialize the variables
	{
		revealed = false;
		bomb = false;
		value =0;
		flagged = false;
	}
	
	public void setRevealed(boolean rev)
	{
		revealed = rev;
	}
	
	public void setFlagged(boolean flag)
	{
		flagged = flag;
	}
	
	public boolean isRevealed()
	{
		return revealed;
	}
	public boolean isFlagged()
	{
		return flagged;
	}
	
	public void setBomb(boolean settedBomb)
	{
		bomb = settedBomb;
	}
	public boolean isBomb()
	{
		return bomb;
	}
	public void setValue(int v)
	{
		value = v;
	}
	public int getValue()
	{
		return value;
	}
	
	public String toString()
	{
		if (isBomb() == true)
		{
			return "B";
		}
		if (isFlagged() == true)
		{
			return "#";
		}
		else 
		{
			return " ";
		}
		
	}
	public static void main(String[] args)
	{
		Minefield mineFrame = new Minefield();
		mineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mineFrame.setSize(300,200);
		mineFrame.setVisible(true);
	}
}