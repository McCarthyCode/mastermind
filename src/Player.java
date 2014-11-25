import java.util.Scanner;

public abstract class Player {
	protected int[] answer;
	private int[] matches;

	public Player () {}

	public abstract int[] guess();

	public abstract void receiveMatches(int []);
	
}

class HumanPlayer extends Player
{
	public HumanPlayer() // default constructor
	{
		super();
	}
	
	public int[] guess()
	{
		System.out.println("Enter your guess: ");
		Scanner input = new Scanner(System.in);
		int a,b,c,d;
		a = input.nextInt();
		b = input.nextInt();
		c = input.nextInt();
		d = input.nextInt();
		int[] guess = {a,b,c,d};
		return guess;
	}
	public void recieveMatches(int[] matches)
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
	public static DUMB_INTELLECT = 0;
	public static NORMAL_INTELLECT = 1;

	
	public ComputerPlayer(int intellectLevel)
	{
		super();
		if(intellectLevel == DUMB_INTELLECT)
		{
			strategy = new Dumb();
		}
		else
		{
			strategy = new Normal();
		}
	}

	public int[] guess(int[] matches)
	{
		return strategy.guess(matches);
	}
	
	public void recieveMatches(int[] matches)
	{
		this.matches = matches;
		
	}

}
