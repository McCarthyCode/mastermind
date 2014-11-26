package Group1.Mastermind;

import java.util.Scanner;

public abstract class Player {
	protected int[] matches;

	public Player () {}

	public abstract int[] guess();

	public abstract void receiveMatches(int[] matches);
	
}

class HumanPlayer extends Player
{
	public HumanPlayer() // default constructor for HumanPlayer
	{
		super();
	}
	/*
	* Human Player guesses by taking input from the stream. 
	* The guess is stored in an int array. 
	*/
	public int[] guess()
	{
		System.out.println("Enter your guess (4 nonnegative digits, one per line):");
		Scanner input = new Scanner(System.in);
		int a,b,c,d;
		a = input.nextInt();
		b = input.nextInt();
		c = input.nextInt();
		d = input.nextInt();
		int[] guess = {a,b,c,d};
		return guess;
	}
	/*
	*
	* receiveMatches() counts how many numbers were guessed in the correct position and incorrect 
	* and displays that information. 
	* @param int[] matches : An array that indicates how a guess matched up with a Player's code.
	*/
	public void receiveMatches(int[] matches)
	{
		this.matches = matches;
		int correctPosition = 0;
		int incorrectPosition = 0;
		for(int i = 0; i < 4; i++)
		{
			if(matches[i] == -1) incorrectPosition++;
			if(matches[i] > -1) correctPosition++;
		}
		System.out.println("Number of correct guess in correct position: " + correctPosition);
		System.out.println("Number of correct guess in incorrect position: " + incorrectPosition);
	}

}

class ComputerPlayer extends Player
{
	private Intellect strategy;
	public static int DUMB_INTELLECT = 0;
	public static int NORMAL_INTELLECT = 1;

	/*
	* When this constructor is called, intellectLevel determines how the
	* ComputerPlayer should behave. 
	*/
	public ComputerPlayer(int intellectLevel)
	{
		super();
		if(intellectLevel == DUMB_INTELLECT)	{
			strategy = new Dumb();
		}
		else {
			strategy = new Normal();
		}
	}
	
	/*
	* @return Based on this Computer's strategy, a proper guess is returned for the Computer by calling
	* guess of the Intellect class. matches[] is passed into the guess so the Computer can determine a smart 
	* guess. 
	*/
	public int[] guess(){
		return strategy.guess(matches);
	}
	
	/*
	* Sets this object's matches[] to the matches[] passed in
	* @param int[] matches : An array that indicates how a guess matched up with a Player's code.
	*/
	public void receiveMatches(int[] matches){
		this.matches = matches;
	}

	

}
