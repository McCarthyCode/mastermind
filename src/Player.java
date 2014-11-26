//package mastermind;

import java.util.Scanner;

public abstract class Player {
	protected int[] answer;
	protected int[] matches;

	public Player () {}

	public abstract int[] guess();

	public abstract void receiveMatches(int[] matches);
	
}

class HumanPlayer extends Player
{
	public HumanPlayer() // default constructor
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
	* @param int[] matches 
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

	public int[] guess(){
		return strategy.guess(matches);
	}
	
	public void receiveMatches(int[] matches){
		this.matches = matches;
	}

	

}
