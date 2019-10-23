/*
	ALWYN BARNARD 28430093
	MINESWEEPER 7 : FINAL
*/

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.Color;
import javax.swing.BorderFactory;  //Border for labels -----------------------------------------
import javax.swing.border.Border;
import javax.swing.Icon; //Icons for buttons -----------------------------------------
import javax.swing.ImageIcon;
import java.awt.Font; // Fonts for buttons -----------------------------------------
import java.lang.System; //For timer -----------------------------------------
import java.lang.*;
import java.time.Duration; 
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; //Exception handling for Bufferedreader and writer
import java.util.Arrays; // To sort array
import javax.swing.JTextArea; //For displaying highscores
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;

public class Minefield extends JFrame
{
	int ROWS = 7;
	int COLS = 7;
	int BOMBS = 4;
	
	String currentLevel ="";
	int cellsRevealed = 0;
	Font sansF = new Font("SansSerif",Font.BOLD+Font.ITALIC,14);
	Font bolderF = new Font("Dialog",Font.BOLD,13);
	
	//sound ----------------------------------------
	
	
	// Data -----------------------------------------
	private MSCell field[][];
	
	// GUI components -----------------------------------------
	private JButton[][] grid = new JButton[720][720];
	JPanel gridPanel;
	JLabel tempLabel;
	JLabel emptylabel;
	int btnsizeW, btnsizeH;

	//Icon for labels
	Icon flag = new ImageIcon("red-icon.png");
	
	private final GridBagLayout layout;
	private final GridLayout layout2;
	private final GridBagConstraints gbc;
	
	JPanel infopanel = new JPanel();
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	Icon resetIcon = new ImageIcon("Reset.png");
	Icon nxtLvl = new ImageIcon("controls_p.png");
	Icon highScoreI = new ImageIcon("trophy.png");
	Icon bmb = new ImageIcon("smallbomb.png");
	JButton resetbtn = new JButton("RESET",resetIcon);
	JButton difLevel = new JButton("DIFFERENT LEVEL",nxtLvl);
	JButton highScorebtn = new JButton("HIGH SCORE",highScoreI);
	JLabel bCount = new JLabel();
	int bCounter = 0;
	JLabel timeLabel = new JLabel();
	
	//Timings -----------------------------------------
	long startTime ;
	long finish=0;
	double timeElapsed = 0;
	
	
	public Minefield()
	{	
		// GUI components -----------------------------------------
		super("Tesing Buttons");
		
		MouseHandler mh = new MouseHandler();
		emptylabel = new JLabel(" ");
		emptylabel.setSize(btnsizeW,btnsizeH);
		
		// create grid for bottons -----------------------------------------
		gridPanel = new JPanel();
		JPanel infopanel = new JPanel();
		layout = new GridBagLayout();
		layout2 = new GridLayout(2,3);
		infopanel.setLayout(layout2);
		gridPanel.setLayout(layout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		int row, col;
		
		for (row=0; row<ROWS+2; row++)
		{
			for (col = 0; col<COLS+2; col++)
			{
				gbc.gridx = row;
				gbc.gridy = col;
				grid[row][col] = new JButton(row +","+col);
				gridPanel.add(grid[row][col],gbc);
				grid[row][col].addMouseListener (mh);			
			}
		}
		
		resetbtn.addMouseListener(mh);
		difLevel.addMouseListener(mh);
		highScorebtn.addMouseListener(mh);
		resetbtn.setFont(sansF);
		difLevel.setFont(sansF);
		highScorebtn.setFont(sansF);
		setLayout(new BorderLayout());
		add(gridPanel,BorderLayout.CENTER);
		
		bCount.setFont(sansF);
		timeLabel.setFont(sansF);
		emptylabel.setBorder(border);
		bCount.setText("Bombs Left: "+BOMBS);
		bCount.setBorder(border);
		timeLabel.setBorder(border);
		
		//info panel -----------------------------------------
		infopanel.add(resetbtn);
		infopanel.add(difLevel);
		infopanel.add(highScorebtn);
		infopanel.add(bCount);
		infopanel.add(timeLabel);
		add(infopanel,BorderLayout.NORTH);
		
		// data -----------------------------------------
		field= new MSCell[720][720];
		initialiseField();
		plantBombs();
		computeNumbers();
		displayField();
		startTime = System.nanoTime();
		timeLabel.setText(" Time: 0 seconds");
		
		switch(ROWS*COLS)
		{
			case 49:
			{
				currentLevel = "7_7HS.txt";
				break;
			}
			case 81:
			{
				currentLevel = "9_9HS.txt";
				break;
			}
			case 256:
			{
				currentLevel = "16_16HS.txt";
				break;
			}
			case 480:
			{
				currentLevel = "16_30HS.txt";
				break;
			}
			case 720:
			{
				currentLevel = "24_30HS.txt";
				break;
			}
		}
	}

	public void Restart()
	{
		playSound("one.wav");
		MouseHandler mh = new MouseHandler();
		gridPanel.removeAll();
		for (int row=0; row<ROWS+2; row++)
		{
			for (int col = 0; col<COLS+2; col++)
			{
				//remove(grid[row][col]);
				gbc.gridx = row;
				gbc.gridy = col;
				grid[row][col] = new JButton(row +","+col);
				gridPanel.add(grid[row][col],gbc);
				grid[row][col].addMouseListener (mh);
			}
		}
		
		bCount.setText("Bombs Left: "+bCounter);
		System.out.println("Restart");
		
		new Minefield();
		initialiseField();
		plantBombs();
		computeNumbers();
		displayField();
		bCount.setText("Bombs Left: "+bCounter);
		startTime = System.nanoTime();
		timeLabel.setText(" Time: 0 seconds");
	} 
	
	
	public void initialiseField()
	{
		for (int i=0; i<ROWS+2;i++)
		{
			for (int j=0; j<COLS+2; j++)
			{
				field[i][j]=new MSCell();
			}
		}
	}
	
	public void plantBombs()
	{
		Random randomNumbers = new Random();
		//plant BOMBS number of bombs randomly in grid -----------------------------------------
		
		int bombsPlanted = 0;
		int bombRow = 0;
		int bombCol = 0;
		while (bombsPlanted < BOMBS)
		{
			bombRow = randomNumbers.nextInt(ROWS)+1; //nextInt(n) produce random number from 0-(n-1) we want a number from 1-(ROWS)
			bombCol = randomNumbers.nextInt(COLS)+1;
			System.out.println( bombRow +" " + bombCol);
			if (!field[bombRow][bombCol].isBomb()) // if not yet bomb
			{
			 field[bombRow][bombCol].setBomb();
			 bombsPlanted++;
			}
		}
		bCounter = BOMBS;
		
	}

	public void computeNumbers()
	{
		int countBombs= 0;
		for (int i=1; i<=ROWS;i++)
		{
			for (int j=1; j<=COLS; j++)
			{	
	            countBombs=0;
				if (!field[i][j].isBomb())
				{
					for (int a=-1; a<=1; a++)
					{
						for (int b=-1; b<=1; b++)
						{
							if (field[i+a][j+b].isBomb() )
							{
								countBombs++;
							}
						}	
					}	
					field[i][j].setValue(countBombs);
				}
			}
		}
	}
	
	
	public void displayField()
	{
		for (int i=0; i<=ROWS+1;i++)
		{
			for (int j=0; j<=COLS+1; j++)
			{  
				grid[i][j].setText(field[i][j].toString());
				grid[i][j].setBackground(Color.LIGHT_GRAY);
			}
			repaint();
			revalidate();
		}
	}
	
	public void Win_Lose(boolean winlose)
	{
		int revealCount = 0;
		int bombCount = 0;
		Icon bombcon = new ImageIcon("bombcon.png");
		Icon wincon = new ImageIcon("winner.png");
		if (winlose == false)
		{
			playSound("two.wav");
			JOptionPane pane = new JOptionPane();
			pane.setIcon(bombcon);
			pane.showMessageDialog(null,"Ahhh you've lost","Lost",JOptionPane.INFORMATION_MESSAGE,bombcon);
			finish = System.nanoTime();
			timeElapsed = finish - startTime;
			timeLabel.setText(" Time: "+Math.round(timeElapsed*0.000000001)+" seconds");
			//System.exit(0);
		}
		else
		{
			for (int i=1; i<=ROWS;i++)
			{
				for (int j=1; j<=COLS; j++)
				{  
					if (field[i][j].isRevealed() == true)
					{
						revealCount++;
					}
					if (field[i][j].isBomb() == true)
					{
						bombCount++;
					}
				}
			
			}
			if ((revealCount+bombCount)==(ROWS*COLS))
			{
				finish = System.nanoTime();
				timeElapsed = finish - startTime;
				timeElapsed = timeElapsed*0.000000001;
				System.out.println("Time: "+Math.round(timeElapsed)+" seconds");
				playSound("two.wav");
				JOptionPane pane = new JOptionPane();
				
				pane.showMessageDialog(null,"You've WON!!!","Win",JOptionPane.INFORMATION_MESSAGE,wincon);
				writeToFile(Math.round(timeElapsed));
				
			}
			
		}
	}
	
	
	public void revealZero(int i,int j)
	{
		System.out.println( i +" " + j);
		for (int a=-1; a<=1; a++)
		{
			for (int b=-1; b<=1; b++)
			{
				if ((field[i+a][j+b].getValue() ==0) && (!field[i+a][j+b].isRevealed()))
				{
					field[i+a][j+b].setRevealed();
					if (field[i+a][j+b].getValue() !=-1) 
					{
						cellsRevealed++;
						
						gridPanel.remove(grid[i+a][j+b]);
						grid[i+a][j+b].setBackground(Color.LIGHT_GRAY);
						gbc.gridx = i+a;
						gbc.gridy = j+b;
						tempLabel = new JLabel(field[i+a][j+b].toString());
						//tempLabel.setBorder(border);
						tempLabel.setSize(btnsizeW,btnsizeH);
						System.out.println(tempLabel.getText());
						gridPanel.add(tempLabel,gbc);
						revalidate();
						repaint();
						revealZero(i+a,j+b); // clear more zeros! -----------------------------------------
					}
				}
				else
				{
					if (!field[i+a][j+b].isRevealed())
					{
						field[i+a][j+b].setRevealed();
						grid[i+a][j+b].setBackground(Color.LIGHT_GRAY);
						if (field[i+a][j+b].getValue() !=-1)
						{
							cellsRevealed++;
							gbc.gridx = i+a;
							gbc.gridy = j+b;
							tempLabel = new JLabel(field[i+a][j+b].toString());
							//tempLabel.setBorder(border);
							tempLabel.setSize(btnsizeW,btnsizeH);
							gridPanel.remove(grid[i+a][j+b]);
							gridPanel.add(tempLabel,gbc);
							//grid[i+a][j+b].setText(field[i+a][j+b].toString());
							revalidate();
							repaint();
						}
						else
						{
							grid[i+a][j+b].setBackground(Color.LIGHT_GRAY);
						}
					}
				}
			}		
		}
	}
	
	// GUI event handlers
	// inner class for MouseListener
	private class MouseHandler implements MouseListener
	{
		String[] lvlOptions = {"9x9","16x16","16x30","24x30"};
		 public void mouseClicked(MouseEvent me) 
		 {
			//left button
			if (me.getButton()== MouseEvent.BUTTON1)
			{
				Object o= me.getSource();
				tempLabel= new JLabel("@");
				//tempLabel.setBorder(border);
				tempLabel.setSize(btnsizeW,btnsizeH);
				long timeNow = System.nanoTime();
				timeElapsed = timeNow - startTime;
				timeLabel.setText(" Time: "+Math.round(timeElapsed*0.000000001)+" seconds");
				if (resetbtn == (JButton) o)
				{
					Restart();
				}
				if (highScorebtn == (JButton) o)
				{
					importTextFile();
					playSound("one.wav");
				}
				if (difLevel == (JButton) o)
				{
					int answer = JOptionPane.showOptionDialog(null,"Please choose a level: \n Beginner: 9*9 with 10 mines and 0.12 difficulty \n Intermediate: 16*16 with 40 mines and 0.16 difficulty \n Expert: 16*30 with 99 mines and 0.21 difficulty \n Master: 24*30 with 199 mines and 0.28 difficulty","Choose difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, lvlOptions, lvlOptions[0]);
					playSound("one.wav");
					switch( answer)
					{

						case 1: 
						{
							ROWS = 16;
							COLS = 16;
							BOMBS = 40;
							currentLevel = "16_16HS.txt";
							Restart();
							break;
						}
						case 2: 
						{
							ROWS = 30;
							COLS = 16;
							BOMBS = 99;
							currentLevel = "16_30HS.txt";
							Restart();
							break;
						}
						case 3: 
						{
							ROWS = 30;
							COLS = 24;
							BOMBS = 199;
							currentLevel = "24_30HS.txt";
							Restart();
							break;
						}
						case 0: 
						{
							ROWS = 9;
							COLS = 9;
							BOMBS = 10;
							currentLevel = "9_9HS.txt";
							Restart();
							break;
						}
						
					}
				}
				else
				{
					for (int r=1; r<=ROWS; r++)
						for (int c = 1; c<=COLS; c++)
						{
							if 	(grid[r][c] == (JButton) o)
							{
								field[r][c].setRevealed();	
								grid[r][c].setBackground(Color.LIGHT_GRAY);							
								if (field[r][c].isBomb())
								{
									Win_Lose(false);
									grid[r][c].setBackground(Color.RED);
									
								}
								
									if(field[r][c].getValue()==0)// set revealed done already -----------------------------------------
									{
										revealZero(r,c);
										gbc.gridx = r;
										gbc.gridy = c;
										gridPanel.remove(grid[r][c]);
										gridPanel.add(tempLabel,gbc);
										cellsRevealed++;
									}
									else
									{
									  cellsRevealed++;
									}
			
								tempLabel.setText(" "+field[r][c].toString()+" ");						
								
								gbc.gridx = r;
								gbc.gridy = c;
								gridPanel.remove(grid[r][c]);
								if (field[r][c].isBomb()) 
									tempLabel.setIcon(bmb);
								gridPanel.add(tempLabel,gbc);
								Win_Lose(true);
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
				//tempLabel.setBorder(border);
				tempLabel.setSize(btnsizeW,btnsizeH);
				long timeNow = System.nanoTime();
				timeElapsed = timeNow - startTime;
				timeLabel.setText(" Time: "+Math.round(timeElapsed*0.000000001)+" seconds");
				for (int r=1; r<=ROWS; r++)
				{
					for (int c = 1; c<=COLS; c++)
					{
						if 	(grid[r][c] == (JButton) o)
						{
							gbc.gridx = r;
							gbc.gridy = c;
							if (field[r][c].isFlagged())
							{
							  field[r][c].setFlagged(false);
							  grid[r][r].setBackground(Color.LIGHT_GRAY);
							  bCounter++;
							}
						    else
							{
								field[r][c].setFlagged(true);
								grid[r][c].setBackground(Color.LIGHT_GRAY);
								grid[r][c].setForeground(Color.RED);
								grid[r][c].setFont(bolderF);
								bCounter--;
							}
							grid[r][c].setText("  "+field[r][c].toString());
							bCount.setText("Bombs Left: "+bCounter);
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

		public String makeEntry(double timeEntry)
		{
			String whoDis = JOptionPane.showInputDialog(null,"Please enter your name for the high scores list","Enter name");
			
			return ("Time: "+ String.valueOf(timeEntry)+", Player: "+whoDis);
		}
		
		public void writeToFile(double timeEntry1)
		{
			//import textfile and populate array
			int countElements = 0;
			try
			{
				Scanner scanner = new Scanner(new File(currentLevel));
				
				while (scanner.hasNextLine()) 
				{
					countElements++;
					scanner.nextLine();
				}
				scanner.close();
			}
			catch (IOException e)
			{
				System.out.println("ERROR:"+e);
			}
			
			String[] records = new String[countElements+1];
			try
			{
				Scanner scanner2 = new Scanner(new File(currentLevel));
				for (int k=0; k<countElements;k++)
				{
					records[k] = scanner2.nextLine();
					System.out.println(records[k]);
				}
				records[countElements++] = makeEntry(timeEntry1);
			}
			catch (IOException e)
			{
				System.out.println("ERROR:"+e);
			}
			int n = records.length;
			String temp;
			for (int i = 0; i < n; i++) 
			{
				for (int j = 1; j < (n - i); j++) 
				{
					if (records[j - 1].compareTo(records[j]) > 0) 
					{
						temp = records[j - 1];
						records[j - 1] = records[j];
						records[j] = temp;
					}
				}
			}
			
			//------------------------------
			try
			{
				FileWriter fileWriter = new FileWriter(currentLevel);
				for (int k=0; k<countElements;k++)
				{
					fileWriter.write(records[k]);
					fileWriter.write(System.getProperty( "line.separator" ));
				}
				fileWriter.close();
			}
			catch(IOException e)
			{
				System.out.println("ERROR: "+ e);
			}
		} 
		
		public void importTextFile() //------------------------------------------------------------------------------------------------
		{
			//import textfile and populate array
			int countElements = 0;
			try
			{
				Scanner scanner = new Scanner(new File(currentLevel));
				
				while (scanner.hasNextLine()) 
				{
					countElements++;
					scanner.nextLine();
				}
				scanner.close();
			}
			catch (IOException e)
			{
				System.out.println("ERROR:"+e);
			}
			
			String[] records = new String[countElements];
			try
			{
				Scanner scanner2 = new Scanner(new File(currentLevel));
				for (int k=0; k<countElements;k++)
				{
					records[k] = scanner2.nextLine();
					System.out.println(records[k]);
				}
				
			}
			catch (IOException e)
			{
				System.out.println("ERROR:"+e);
			}
			 int n = records.length;
			String temp;
			for (int i = 0; i < n; i++) 
			{
				for (int j = 1; j < (n - i); j++) 
				{
					if (records[j - 1].compareTo(records[j]) > 0) 
					{
						temp = records[j - 1];
						records[j - 1] = records[j];
						records[j] = temp;
					}
				}
			}
			JFrame highscoreframe = new JFrame();
			JTextArea textarea = new JTextArea();
			textarea.setFont(sansF);
			textarea.append("High Scores : \n");
			textarea.append("---------------------------------------------- \n");
			for (int h = 0; h < n; h++)
			{
				textarea.append(h+1 + " - " + records[h]+"\n");
			}
			highscoreframe.add(textarea);
			highscoreframe.setVisible(true);
			highscoreframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			highscoreframe.setSize(300,300);
			highscoreframe.setLocationRelativeTo(null);
			
			
			
		}
		
		public void playSound(String soundFile) 
		{
			try
			{
				
			File f = new File(soundFile);
			AudioInputStream audioInn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
			Clip clipp = AudioSystem.getClip();
			clipp.open(audioInn);
			clipp.start();
			}
			catch(Exception e)
			{
				System.out.println("ERROR: "+e);
			}
		}

	
}


