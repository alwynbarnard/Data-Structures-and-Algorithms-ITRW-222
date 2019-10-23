//Alwyn Barnard 28430093

public class MSCell
{
		private boolean revealed;
		private boolean bomb;
		private int value;
		private boolean flagged;
		
		// constructor to initialise variables
		public MSCell ()
		{
			revealed=false;
			flagged = false;
			bomb = false;
			value=0;
		}
		// accessor and mutators
		public void setRevealed ()
		{
			revealed = true;
		}
		public void setFlagged (boolean b)
		{
			flagged = b;
		}
		public boolean isRevealed()
		{
			return revealed;
		}
		public void setBomb()
		{
			bomb=true;
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
			value=v;
		}
		public int getValue()
		{
			return value;
		}
		
		public String toString(boolean reveal) 
		{
			String s=" ";
			if (bomb)
			{
				s = " ";
			}
			if (reveal==true)
			{
				if (bomb)
				{
					s="B";
				}
				else
				{
					s += value;
				}
			}
			return s;
		}
		

}