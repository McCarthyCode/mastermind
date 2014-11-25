public abstract class Player {
	// Attributes
	private int answer[];
	private int correctGuesses[];
	
	// Operations
	public int[] checkMatches( int arr[] ) {
		int[] ret = new int[4];
		for( int i = 0; i < 4; ++i ) {
			
		}
		return ret;
	}
	public int[] guess() {return null;}
	public void setCode( int code[] ) {
		for( int i = 0; i < 4; ++i )
			answer[i] = code[i];
	}
}

class ComputerPlayer extends Player {
	// Attributes
	private Intellect strategy;
	
	// Operations
	public void setIntellect( Intellect intellect ) {strategy = intellect;}
	public int[] guess() {return strategy.guess();}
}

class HumanPlayer extends Player {
	// Operations
	public int[] getInput() {
		return null;
	}
}
