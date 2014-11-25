import java.util.Scanner;

public abstract class Player {
	protected int[] answer;
	private int[] correctGuesses;

	public Player () {}
	public Player(int[] answer)	{this.answer = answer;}

	public abstract int[] guess();

	public abstract void setCode(int[] code);
}

class HumanPlayer extends Player
{
	public HumanPlayer() // default constructor
	{
		super();
	}
	public HumanPlayer(int[] answer)
	{
		super(answer);
	}
	public int[] guess()
	{
		Scanner input = new Scanner(System.in);
		int a,b,c,d;
		a = input.nextInt();
		b = input.nextInt();
		c = input.nextInt();
		d = input.nextInt();
		int[] guess = {a,b,c,d};
		return guess;
	}

	public void setCode(int[] code)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 4 digits for initial code: ");

		this.answer[0] = input.nextInt();
		this.answer[1] = input.nextInt();
		this.answer[2] = input.nextInt();
		this.answer[3] = input.nextInt();

	}
}

class ComputerPlayer extends Player
{

	private Intellect strategy;

	public ComputerPlayer()
	{
		super();
	}
	public ComputerPlayer(int[] answer, Intellect strategy)
	{
		super(answer);
		this.strategy = strategy;
	}

	public void setIntellect(Intellect strategy)
	{
		this.strategy = strategy;
	}

	public int[] guess()
	{
		return null;
	}
	
	public void setCode(int[] code) {}
}
