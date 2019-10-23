/*
	ALWYN BARNARD 28430093
	MINESWEEPER 7 : FINAL
*/

public class MSCell
{
		private boolean revealed;
		private boolean bomb;
		private int value;
		private boolean flagged;
		
		// constructor to initialise variables
		public MSCell ()
		{
			revealed = false;
			flagged = false;
			bomb = false;
			value = -1;
		}
		
		public  void setRevealed ()
		{
			revealed = true;
		}
		public  void setFlagged (boolean b)
		{
			flagged = b;
		}
		public boolean isRevealed()
		{
			return revealed;
		}
		public void setBomb()
		{
			bomb = true;
		}
		public boolean isBomb()
		{
			return bomb;
		}
		public boolean isFlagged()
		{
			return flagged;
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
			String s = "";
			String s2;
			if (bomb)
				s = "";
			if (isRevealed())
			{
			   if (bomb)
				 s = "";
			   else
			   {
				 s = "     " + new Integer(value).toString()+ "     ";
			   }
			}
			if (isFlagged())
				s = " ! ";
			s2 = String.format("%5s",s);
			return s2;
		}
		

}