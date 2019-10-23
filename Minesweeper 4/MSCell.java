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
			bomb = false;
			value=0;
		}
		// accessor and mutators
		public  void setRevealed ()
		{
			revealed = true;
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
		public void setFlagged()
		{
			flagged=true;
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
		
		public String toString() // mark BOMB!
		{
			String s="";
			if (isBomb())
				s = "B";
			else
				s = s + getValue();
			return s;
		}
		

}