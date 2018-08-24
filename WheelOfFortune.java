/*
Jason Wang
May 28, 2016
Ms. Dyke
This program is the Wheel of Fortune game which is a two player game in which the two players race to solve the puzzle. Each player takes turns spinning the wheel to get an amount of money which will be added under their names if they solve the puzzle.
If they solve the puzzle, they will win the amount of money listed under their name. A list of available letters is listed and the winner of the game may be listed in the high scores list if they qualify.

*/
import java.awt.*;
import java.io.*;
import hsa.*;

public class WheelOfFortune
{
    /*
	      Name:               Type:               Description:
	      c                   reference           This variable accesses the Console class.
	      choice              String              This variable allows the user to select an option from the main menu.
	      firstName           String              This variable stores the value of the first player's name.
	      secondName          String              This variable stores the value of the second player's name.
	      letter              char                This variable stores the value of the user's guess for a letter if they choose to spin the wheel.
	      finalAnswer         String              This variable stores the value of the final answer determined by the random integer.
	      TOTAL               static final int    This variable is used to store the value of the array used to select an answer from Words.jason.
	      answer              String              This variable is used to store the value of the phrase answer.
	      topic               String              This variable is used to store the value of the topic of the phrase.
	      ORDER               static final int    This variable is used to store the value of the array used for the high scores.
	      highScoreName       String              This variable is used to store the value of the user's name in high scores.
	      highScoreAmount     String              This variable is used to store the value of the user's amount in high scores.
	      line                int                 This variable is used in high scores to increase the value of the line for reading the high score names/amounts
	      header              String              This variable is used to check for the correct file.
	      LETTER              final int           This variable is used for an array in picking a random string.
	      letterRepeat        char                This variable stores the letters inputted to check for any repeating letters.
	      randomPhraseInt     int                 This variable is used for selecting a random phrase as the answer to the puzzle.
	      lengthAnswer        int                 This variable is used to store the value of the length of the answer.
	      displayLetter       boolean             This variable holds true or false to indicate whether a letter should be displayed.
	      spin                int                 This variable stores the value of the amount of money that can be won from the wheel spin.
	      currentTurn         String              This variable stores the value of the name of the person who's turn it currently is.
	      done                boolean             This variable determines whether to exit the puzzle section of the program or not.
	      amount1             int                 This variable holds the value of the amount of money that can be won by player 1.
	      amount2             int                 This variable holds the value of the amount of money that can be won by player 2.
	      displayed           boolean             This variable determines whether to display the change of turn.
	      input               reference           This variable accesses the BufferedReader class and is used for getting values from the files for high scores and for the random phrase.
	      output              reference           This variable accesses the PrintWriter class and is used to store values into high scores.
	      winningAmount       int                 This variable stores the amount of money won by the winner of the game.
    */

    Console c;
    String choice;
    String firstName;
    String secondName;
    char letter;
    String finalAnswer;
    static final int TOTAL = 100;
    String[] answer = new String [TOTAL];
    String[] topic = new String [TOTAL];
    static final int ORDER = 10;
    String[] highScoreName = new String [ORDER];
    String[] highScoreAmount = new String [ORDER];
    int line;
    String header;
    final int LETTER = 26;
    char[] letterRepeat = new char [LETTER];
    int randomPhraseInt;
    int lengthAnswer = 0;
    boolean displayLetter;
    int spin = 0;
    String currentTurn;
    boolean[] done;
    int amount1 = 10000;
    int amount2 = 10000;
    boolean displayed;
    BufferedReader input;
    PrintWriter output;
    int winningAmount;


    //This method clears the screen and displays the title centered on the screen.
    private void title ()
    {
	c.clear ();
	c.setTextBackgroundColor (Color.yellow);
	c.print (' ', 28);
	c.println ("Wheel of Fortune Game");
	c.println ();
    }


    //This method pauses the program and asks for the user to press on a key before it continues.
    private void pauseProgram ()
    {
	c.println ();
	c.println ("Press any key to continue...");
	c.println ();
	c.getChar ();

    }


    //This method is for the main menu which allows the user to choose to go to instructions, program, high scores or exit.
    public void mainMenu ()
    {
	title ();
	c.println ();
	c.println ("1. Instructions");
	c.println ("2. Program");
	c.println ("3. High Score");
	c.println ("4. Exit");
	c.println ("Please enter a number: ");

	while (true)
	{
	    choice = c.readLine ();
	    if (choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4"))
		break;
	    else
	    {
		new Message ("Please enter 1, 2, 3, or 4.", "ERROR");
		c.setCursor (9, 1);
		c.println ();
		c.setCursor (9, 1);
	    }
	}
    }


    /*
    This method displays the splash screen animation at the beginning of the program.
    Name:           Type:           Purpose:
    t               reference       This variable accesses the class which contains the code for the splash screen.
    e               reference       This variable accesses the InterruptedException class.
    */


    public void splashScreen ()
    {
	c.setTextBackgroundColor (Color.yellow);
	SplashScreen t = new SplashScreen (c);
	t.start ();
	try
	{
	    t.join ();
	    Thread.sleep (1500);
	}
	catch (InterruptedException e)
	{
	}
    }


    //This method is to display the instructions to the Wheel of Fortune game.
    public void instructions ()
    {
	title ();
	c.println ();
	c.println ("Welcome to the Wheel of Fortune Game! This game is a two player game in which");
	c.println ("each player will race to complete the puzzle. The two players will take turns");
	c.println ("choosing to spin the wheel or to solve the puzzle. If the player chooses to spin the wheel, a certain amount of money will be won if you guess a correct letter in the puzzle. The first player to complete the puzzle wins the amount of money listed under their name. If a player chooses a wrong letter or enters the wrong answer to the puzzle, the turn will switch over to the other player.");
	c.println ("Good luck and have fun!");
	c.println ();
	pauseProgram ();
    }


    //This method gets the names of both of the players playing the game.
    public void getNames ()
    {
	title ();
	while (true)
	{
	    while (true)
	    {
		c.setCursor (5, 1);
		c.println ("Please enter your names:");
		c.print ("Player 1: ");
		firstName = c.readLine ();
		if (firstName.length () < 21)
		{
		    break;
		}
		new Message ("Your name must be less than or equal to 20 characters!", "ERROR");
		c.setCursor (6, 11);
		c.println ();
		c.setCursor (6, 11);
	    }
	    currentTurn = firstName;
	    while (true)
	    {
		c.setCursor (7, 1);
		c.print ("Player 2: ");
		secondName = c.readLine ();
		if (secondName.length () < 21)
		{
		    break;
		}
		new Message ("Your name must be less than or equal to 20 characters!", "ERROR");
		c.setCursor (7, 11);
		c.println ();
		c.setCursor (7, 11);
	    }
	    break;
	}
    }


    /*
    This method stores the high scores of the top 10 scores achieved by players into a file. The name of the file is High Scores.jason.
    private void storeScore ()

    Name:           Type:           Purpose:
    e               reference       This variable accesses the IOException class)
    */

    private void storeScore ()
    {
	line = 0;
	try
	{
	    input = new BufferedReader (new FileReader ("High Scores.jason"));
	    header = input.readLine ();
	    do
	    {
		highScoreName [line] = input.readLine ();
		highScoreAmount [line] = input.readLine ();
		line++;
	    }
	    while (line < ORDER);
	}
	catch (IOException e)
	{
	}
    }


    /*
    This method outputs the high scores in the file High Scores.jason onto the screen.

    Name:           Type:           Purpose:
    e               reference       This variable accesses the Exception class.
    */
    public void highScore ()
    {
	int line = 0;
	title ();
	storeScore ();
	c.setFont (new Font ("Century Gothic", 1, 20));
	c.drawString ("HIGH SCORES", 250, 80);

	c.setCursor (6, 1);
	try
	{
	    do
	    {
		if (highScoreName [line] == null)
		    highScoreName [line] = "";
		if (highScoreAmount [line] == null)
		    highScoreAmount [line] = "";
		if (highScoreName [line + 5] == null)
		    highScoreName [line + 5] = "";
		if (highScoreAmount [line + 5] == null)
		    highScoreAmount [line + 5] = "";
		c.print (' ', 17);
		c.print (line + 1 + ". Name: " + highScoreName [line], 35);
		c.println (line + 6 + ". Name: " + highScoreName [line + 5]);
		c.print (' ', 20);
		c.print ("Score: " + highScoreAmount [line], 35);
		c.println ("Score: " + highScoreAmount [line + 5]);
		c.println ();
		line++;
	    }
	    while (line < ORDER / 2);
	}
	catch (Exception e)
	{
	}
	for (int x = 0 ; x < 6 ; x++)
	{
	    c.println ();
	}
	pauseProgram ();
    }


    /*
    This method generates a random integer from 0-11. Depending on the number, the wheel will land on a different sector of the wheel. An amount of money is then assigned.
    Name:           Type:           Purpose:
    red             Color           This variable stores the colour red, determined by RGB colours
    orange          Color           This variable stores the colour orange, determined by RGB colours
    lightRed        Color           This variable stores the colour light red, determined by RGB colours
    purple          Color           This variable stores the colour purple, determined by RGB colours
    blue            Color           This variable stores the colour blue, determined by RGB colours
    yellow          Color           This variable stores the colour yellow, determined by RGB colours
    green           Color           This variable stores the colour green, determined by RGB colours
    lightBlue       Color           This variable stores the colour light blue, determined by RGB colours
    beige           Color           This variable stores the colour beige, determined by RGB colours
    pink            Color           This variable stores the colour pink, determined by RGB colours
    darkGreen       Color           This variable stores the colour dark green, determined by RGB colours
    darkOrange      Color           This variable stores the colour dark orange, determined by RGB colours
    degrees         int             This variable store the value of the number of degrees the wheel will spin by.
    randomInt       int             This variable stores the value of a random number with a range of 12 (0-11)
    */

    public void wheelSpin ()
    {
	Color red = new Color (255, 0, 0);
	Color orange = new Color (255, 165, 0);
	Color lightRed = new Color (255, 69, 0);
	Color purple = new Color (186, 85, 211);
	Color blue = new Color (0, 0, 205);
	Color yellow = new Color (255, 255, 0);
	Color green = new Color (127, 255, 0);
	Color lightBlue = new Color (0, 191, 255);
	Color beige = new Color (238, 232, 170);
	Color pink = new Color (240, 128, 128);
	Color darkGreen = new Color (50, 205, 50);
	Color darkOrange = new Color (255, 140, 0);

	int degrees;
	int randomInt = (int) (Math.random () * 12);
	c.setColor (Color.black);
	c.drawLine (130, 190, 130, 210);
	c.drawLine (130, 210, 125, 200);
	c.drawLine (130, 210, 135, 200);
	if (randomInt == 0)
	{
	    degrees = 15 + 360 * 2;
	    spin = 200;
	}
	else if (randomInt == 1)
	{
	    degrees = 15 * 3 + 360 * 2;
	    spin = 100;
	}
	else if (randomInt == 2)
	{
	    degrees = 15 * 5 + 360 * 2;
	    spin = 1000;
	}
	else if (randomInt == 3)
	{
	    degrees = 15 * 7 + 360 * 2;
	    spin = 200;
	}
	else if (randomInt == 4)
	{
	    degrees = 15 * 9 + 360 * 2;
	    spin = 100;
	}
	else if (randomInt == 5)
	{
	    degrees = 15 * 11 + 360 * 2;
	    spin = 300;
	}
	else if (randomInt == 6)
	{
	    degrees = 15 * 13 + 360 * 2;
	    spin = 800;
	}
	else if (randomInt == 7)
	{
	    degrees = 15 * 15 + 360 * 2;
	    spin = 100;
	}
	else if (randomInt == 8)
	{
	    degrees = 15 * 17 + 360 * 2;
	    spin = 600;
	}
	else if (randomInt == 9)
	{
	    degrees = 15 * 19 + 360 * 2;
	    spin = 200;
	}
	else if (randomInt == 10)
	{
	    degrees = 15 * 21 + 360 * 2;
	    spin = 500;
	}
	else
	{
	    degrees = 15 * 23 + 360 * 2;
	    spin = 100;
	}

	for (int x = 0 ; x < degrees ; x++)
	{
	    c.setColor (pink);
	    c.fillArc (30, 210, 200, 200, 0 + x, 30);
	    c.setColor (red);
	    c.fillArc (30, 210, 200, 200, 30 + x, 30);
	    c.setColor (yellow);
	    c.fillArc (30, 210, 200, 200, 60 + x, 30);
	    c.setColor (green);
	    c.fillArc (30, 210, 200, 200, 90 + x, 30);
	    c.setColor (lightBlue);
	    c.fillArc (30, 210, 200, 200, 120 + x, 30);
	    c.setColor (darkOrange);
	    c.fillArc (30, 210, 200, 200, 150 + x, 30);
	    c.setColor (purple);
	    c.fillArc (30, 210, 200, 200, 180 + x, 30);
	    c.setColor (lightRed);
	    c.fillArc (30, 210, 200, 200, 210 + x, 30);
	    c.setColor (beige);
	    c.fillArc (30, 210, 200, 200, 240 + x, 30);
	    c.setColor (darkGreen);
	    c.fillArc (30, 210, 200, 200, 270 + x, 30);
	    c.setColor (blue);
	    c.fillArc (30, 210, 200, 200, 300 + x, 30);
	    c.setColor (orange);
	    c.fillArc (30, 210, 200, 200, 330 + x, 30);
	}
	try
	{

	    Thread.sleep (2);
	}
	catch (Exception e)
	{
	}

	c.setColor (blue);
	c.setFont (new Font ("Britannic Bold", 1, 30));
	c.drawString ("$" + spin, 250, 100);
    }


    /*This method generates a random integer with a range of TOTAL which is 100 to select a random answer from the file Words.jason to use as the answer to the puzzle in the game.
      Name:         Type:           Purpose:
      wordCheck     char            This variable is used in checking if the puzzle is completed.
    */
    private void pickString ()
    {
	char[] wordCheck;
	randomPhraseInt = (int) (Math.random () * TOTAL);
	do
	{
	    letterRepeat [line] = ' ';
	    line++;
	}
	while (line < LETTER);
	line = 0;
	try
	{
	    input = new BufferedReader (new FileReader ("Words.jason"));
	    header = input.readLine ();
	    do
	    {
		answer [line] = input.readLine ();
		topic [line] = input.readLine ();
		line++;
	    }
	    while (line < TOTAL);
	}
	catch (IOException e)
	{
	}
	finalAnswer = answer [randomPhraseInt];
	lengthAnswer = finalAnswer.length ();
	done = new boolean [lengthAnswer];
	for (int x = 0 ; x < lengthAnswer ; x++)
	{
	    if (finalAnswer.charAt (x) == ' ')
		done [x] = true;
	    else
		done [x] = false;
	}

	wordCheck = new char [lengthAnswer];
	line = 0;
	do
	{
	    wordCheck [line] = ' ';
	    line++;
	}
	while (line < lengthAnswer);

    }


    //This method switches the turn of the players and is used if a player enters a wrong letter or answer.
    public void switchTurn ()
    {
	if (currentTurn.equals (firstName))
	{
	    currentTurn = secondName;
	}
	else
	{
	    currentTurn = firstName;
	}

    }


    //This method will display which player's turn it is on the screen.
    private void displayTurn ()
    {
	switchTurn ();
	c.setCursor (3, 50);
	c.println ();
	c.setCursor (3, 50);
	c.print (currentTurn + "'s turn");
    }


    /*This method contains the entire portion of the game for the users to play. this includes the options to spin/ solve, the displaying of the scores and the displaying of the letters and the puzzle.
    Name:           Type:           Purpose:
    option          String          This variable allows the user to choose whether they would like to spin or solve.
    userAnswer      String          This variable stores the answer the user has input if they choose to answer the puzzle (option B)
    count           int             This variable is used to draw the blank spaces for the number of letters in the puzzle.
    */
    public void display ()
    {
	String option;
	String userAnswer;
	int count = 0;
	displayed = false;

	title ();
	pickString ();
	c.println ();
	c.println ("Please spin or solve!");
	c.setCursor (5, 11);
	c.print (firstName);
	c.setCursor (5, 64);
	c.println (secondName);
	c.print (' ', 10);
	c.print ("$" + amount1);
	c.print (' ', 47);
	c.println ("$" + amount2);
	c.setColor (Color.white);
	c.fillRect (240, 70, 140, 40);
	c.setColor (Color.black);
	c.drawRect (240, 70, 140, 40);
	c.setFont (new Font ("Britannic Bold", Font.PLAIN, 30));
	c.print ("Press A to spin and B to solve: ");
	c.setCursor (3, 50);
	c.print (currentTurn + "'s turn");
	c.setColor (Color.black);
	c.setFont (new Font ("Century Gothic", 1, 30));
	c.drawString (" A   B   C   D   E   F   G   H   I   J   K   L   M", 60, 460);
	c.drawString ("N   O   P   Q   R   S   T   U   V   W   X   Y   Z", 60, 500);
	c.setFont (new Font ("Century Gothic", Font.BOLD, 20));
	c.drawString ("Topic: " + topic [randomPhraseInt], 450, 400);


	for (int x = 0 ; x < lengthAnswer ; x++)
	{
	    count = x * 30;

	    if (finalAnswer.charAt (x) == 32)
	    {
		c.setColor (Color.yellow);
		c.fillRect (20 + count, 600, 20, 5);
	    }

	    else
	    {
		c.setColor (Color.red);
		c.fillRect (20 + count, 600, 20, 5);
	    }
	}

	while (true)
	{
	    c.setColor (Color.white);
	    c.fillRect (241, 71, 139, 39);
	    c.setCursor (7, 33);
	    c.println ();
	    c.println ();
	    c.setCursor (7, 33);

	    option = c.readLine ();
	    if (option.equals ("A") || option.equals ("a"))
	    {
		wheelSpin ();

		c.setCursor (8, 1);
		c.print ("Guess a letter:");

		while (true)
		{
		    displayLetter = true;
		    letter = c.getChar ();
		    for (int y = 0 ; y < 26 ; y++)
		    {
			if (letter + 32 == letterRepeat [y] || letter - 32 == letterRepeat [y] || letter == letterRepeat [y])
			{
			    displayLetter = false;
			    new Message ("You have already entered this letter.", "ERROR");
			    break;
			}
		    }
		    for (int x = 0 ; x < 26 ; x++)
		    {
			if (letterRepeat [x] == ' ')
			{
			    letterRepeat [x] = letter;
			    break;
			}
		    }
		    if ((letter >= 65 && letter <= 90) || (letter >= 97 && letter <= 122))
		    {
			break;
		    }
		    new Message ("This is not a letter.", "ERROR");
		}
		boolean displayed = false;

		if (displayLetter == true)
		{
		    for (int x = 0 ; x < finalAnswer.length () ; x++)
		    {

			if ((letter + 32 == finalAnswer.charAt (x)) || (letter - 32 == finalAnswer.charAt (x)) || (letter == finalAnswer.charAt (x)))
			{
			    c.setColor (Color.black);
			    c.setFont (new Font ("Britannic Bold", 1, 30));
			    c.drawString (finalAnswer.substring (x, x + 1), 20 + (30 * x), 600);
			    done [x] = true;

			    if (currentTurn.equals (firstName))
			    {
				amount1 = amount1 + spin;
				c.setCursor (6, 11);
				c.print ("$" + amount1);
			    }
			    else
			    {
				amount2 = amount2 + spin;
				c.setCursor (6, 64);
				c.print ("$" + amount2);
			    }

			    displayed = true;
			}

		    }
		    if (!displayed)
		    {
			displayTurn ();
		    }
		}
		c.setColor (Color.yellow);
		if (letter == 'a' || letter == 'A')
		{
		    c.fillRect (58, 435, 30, 30);
		}
		else if (letter == 'b' || letter == 'B')
		{
		    c.fillRect (108, 435, 30, 30);
		}
		else if (letter == 'c' || letter == 'C')
		{
		    c.fillRect (155, 435, 30, 30);
		}
		else if (letter == 'd' || letter == 'D')
		{
		    c.fillRect (203, 435, 30, 30);
		}
		else if (letter == 'e' || letter == 'E')
		{
		    c.fillRect (240, 435, 30, 30);
		}
		else if (letter == 'f' || letter == 'F')
		{
		    c.fillRect (280, 435, 30, 30);
		}
		else if (letter == 'g' || letter == 'G')
		{
		    c.fillRect (325, 435, 30, 30);
		}
		else if (letter == 'h' || letter == 'H')
		{
		    c.fillRect (365, 435, 30, 30);
		}
		else if (letter == 'i' || letter == 'I')
		{
		    c.fillRect (408, 435, 30, 30);
		}
		else if (letter == 'j' || letter == 'J')
		{
		    c.fillRect (445, 435, 30, 30);
		}
		else if (letter == 'k' || letter == 'K')
		{
		    c.fillRect (488, 435, 30, 30);
		}
		else if (letter == 'l' || letter == 'L')
		{
		    c.fillRect (528, 435, 30, 30);
		}
		else if (letter == 'm' || letter == 'M')
		{
		    c.fillRect (568, 435, 30, 30);
		}
		else if (letter == 'n' || letter == 'N')
		{
		    c.fillRect (58, 475, 30, 30);
		}
		else if (letter == 'o' || letter == 'O')
		{
		    c.fillRect (105, 475, 30, 30);
		}
		else if (letter == 'p' || letter == 'P')
		{
		    c.fillRect (148, 475, 30, 30);
		}
		else if (letter == 'q' || letter == 'Q')
		{
		    c.fillRect (195, 475, 30, 30);
		}
		else if (letter == 'r' || letter == 'R')
		{
		    c.fillRect (238, 475, 30, 30);
		}
		else if (letter == 's' || letter == 'S')
		{
		    c.fillRect (278, 475, 30, 30);
		}
		else if (letter == 't' || letter == 'T')
		{
		    c.fillRect (318, 475, 30, 30);
		}
		else if (letter == 'u' || letter == 'U')
		{
		    c.fillRect (358, 475, 30, 30);
		}
		else if (letter == 'v' || letter == 'V')
		{
		    c.fillRect (398, 475, 30, 30);
		}
		else if (letter == 'w' || letter == 'W')
		{
		    c.fillRect (448, 475, 30, 30);
		}
		else if (letter == 'x' || letter == 'X')
		{
		    c.fillRect (493, 475, 30, 30);
		}
		else if (letter == 'y' || letter == 'Y')
		{
		    c.fillRect (538, 475, 30, 30);
		}
		else
		{
		    c.fillRect (578, 475, 30, 30);
		}

		boolean exit = true;
		for (int x = 0 ; x < done.length ; x++)
		{
		    if (!done [x])
		    {
			exit = false;
			break;
		    }
		}
		if (exit)
		{
		    winner ();
		    break;
		}

	    }
	    else if (option.equals ("B") || option.equals ("b"))
	    {
		c.setColor (Color.blue);
		c.drawLine (350, 322, 600, 322);
		c.setCursor (14, 45);
		c.print ("What is the word/phrase?");
		c.setCursor (15, 45);
		c.print ("(Include spaces and capital letters)");
		c.setCursor (16, 45);
		userAnswer = c.readLine ();

		if (userAnswer.equals (finalAnswer))
		{
		    winner ();
		    break;
		}
		else
		{
		    c.setCursor (16, 45);
		    c.println ();
		    displayTurn ();
		}
	    }
	    else
	    {
		new Message ("Please enter A or B.", "ERROR");
		c.setCursor (7, 33);
		c.println ();
		c.setCursor (7, 33);
	    }
	}
    }


    /*
    This method is used to update the high scores.
    Name:                   Type:                   Purpose:
    scoreInString           String                  This variable holds the value of the user's winning amount as a string value.
    highScoreAmountNum      int                     This variable holds the value of the array highScoreAmount [x] as an integer form.
    name                    String                  This variable holds the value for the user's name as a temporary value.
    score                   String                  This variable holds the value for the user's score as a temporary value.
    CURRENT                 final int               This variable holds the value used in the name and score variables.
    */
    private void storeNewScore ()
    {
	final int CURRENT = 11;
	String[] name = new String [CURRENT];
	String[] score = new String [CURRENT];
	String scoreInString = String.valueOf (winningAmount);
	int highScoreAmountNum = 0;
	try
	{
	    output = new PrintWriter (new FileWriter ("High Scores.jason"));
	    try
	    {
		for (int x = 0 ; x < ORDER ; x++)
		{
		    highScoreAmountNum = Integer.parseInt (highScoreAmount [x]);
		    scoreInString = String.valueOf (winningAmount);
		    if (winningAmount >= highScoreAmountNum)
		    {
			for (int y = ORDER ; y > x ; y--)
			{
			    name [y] = highScoreName [y - 1];
			    score [y] = highScoreAmount [y - 1];
			}
			name [x] = currentTurn;
			score [x] = scoreInString;
			break;
		    }
		    else
		    {
			name [x] = highScoreName [x];
			score [x] = highScoreAmount [x];
		    }
		}
		output.println ();
		for (int x = 0 ; x < ORDER ; x++)
		{
		    output.println (name [x]);
		    output.println (score [x]);
		}
		output.close ();
	    }
	    catch (Exception e)
	    {
	    }
	}
	catch (IOException e)
	{
	}
    }


    //This method determines the winner, the winning amount and displays a congratulations message.
    public void winner ()
    {
	winningAmount = 0;

	if (currentTurn.equals (firstName))
	{
	    winningAmount = amount1;
	}
	else
	{
	    winningAmount = amount2;
	}
	title ();
	for (int x = 0 ; x < 10 ; x++)
	{
	    c.println ();
	}
	c.print (' ', 26);
	c.println ("Congratulations " + currentTurn + "!");
	c.print (' ', 28);
	c.print ("You have won $" + winningAmount + "!");
	for (int x = 0 ; x < 15 ; x++)
	{
	    c.println ();
	}

	count = 0;
	displayed = false;
	amount1 = 10000;
	amount2 = 10000;
	letterRepeat = new char [LETTER];
	storeScore ();
	storeNewScore ();
	pauseProgram ();
    }


    //This method clears the screen and displays a goodbye message.
    public void goodBye ()
    {
	title ();
	c.println ();
	c.println ("Thank you for playing the Wheel of Fortune Game made by Jason Wang!");
	for (int x = 0 ; x < 20 ; x++)
	{
	    c.println ();
	}
	pauseProgram ();
	c.close ();
    }


    /*This is the class constructor used to create a console window.
    Name:          Type:            Purpose:
    c              reference        This variable accesses the Console class.
    */
    public WheelOfFortune ()
    {
	c = new Console (31, 80, "Wheel of Fortune");
    }


    //This is the main method which runs all the other methods in the program.
    /*
	Name            Type            Description
	t               Reference       This variable points to the WordReplacer class.
    */
    public static void main (String[] args)
    {
	WheelOfFortune t = new WheelOfFortune ();

	t.splashScreen ();
	while (true)
	{
	    t.mainMenu ();
	    if (t.choice.equals ("1"))
	    {
		t.instructions ();
	    }
	    else if (t.choice.equals ("2"))
	    {
		t.getNames ();
		t.display ();
	    }
	    else if (t.choice.equals ("3"))
	    {
		t.highScore ();
	    }
	    else
	    {
		break;
	    }
	}
	t.goodBye ();
    }
}
