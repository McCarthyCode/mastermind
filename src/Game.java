//package mastermind;

import java.util.Scanner;

public class Game {

	public Game() {
		
	}

	Player humanPlayer;
	Player computerPlayer;
	
	int numTurns;
	int levelChoice;
	int[] humanCode;
	int[] computerCode;
	
	int[] computerGuess;
	int[] computerMatches;
	int[] humanGuess;
	int[] humanMatches;

	int winner = 0;
	int replay = 1;
	int opponentChoice;

	Scanner scan;
	LevelFactory factory;

	// Contains main game loops which run the game
	public void runGame() {
		showWelcomeScreen();
		scan = new Scanner(System.in);
		
		while (replay > 0) {
			
			getSettings();
			// 1 == easy, 3 == hard, 2 and any other input == normal
			switch (levelChoice) {
			case 1:
				factory = new EasyLevelFactory();
				break;
			case 3:
				factory = new HardLevelFactory();
				break;
			default:
				factory = new MediumLevelFactory();
				break;
			}
			
			//while (replay == 1) {
				resetGame();
				humanPlayer = new HumanPlayer();
				computerPlayer = new ComputerPlayer(opponentChoice);
				computerCode = factory.createLevel();
				humanCode = promptUserForCode();
				
				while (winner == 0) {
					numTurns++;
					
					humanGuess = getGuess(humanPlayer);
					computerGuess = getGuess(computerPlayer);

					checkResults();

					if (winner > 0) {
						displayResults();
						playAgain();
					} else {
						notifyPlayer(computerPlayer, computerMatches);
						notifyPlayer(humanPlayer, humanMatches);
					}
				}

			//}
		}
		System.out.println("Thank you for playing Mastermind. We're hoping to see you again soon!");
	}
	
	// Mediator function. Asks a user for their guess.
	private int[] getGuess(Player p) {
		return p.guess();
	}
	
	// Gets user's 4 digits that the computer opponent will try to guess
	private int[] promptUserForCode(){
		System.out.println("Please enter your sequence of 4 nonnegative digits that your opponent will have to guess:");
		System.out.println("Enter one number per line:");
		int a,b,c,d;
		a = scan.nextInt();
		b = scan.nextInt();
		c = scan.nextInt();
		d = scan.nextInt();
		return new int[] {a,b,c,d};
		
	}
	
	// Contains game logic. Iterates over guesses and correct answers, sets the user flag if one is found.
	private void checkResults() {

		// n == -2 means number does not exist within opponent's combination
		// n == -1 means digit exist within opponent's combination, but in
		// different slot
		// n >= 0 means that user guessed the correct digit in the correct slot
		// in that case, we set n to the actual digit
		for (int i = 0; i < 4; i++) {
			humanMatches[i] = -2;
			computerMatches[i] = -2;
		}

		// contains number of digits a player guessed correctly
		int numHumanMatches = 0;
		int numComputerMatches = 0;

		boolean compDone = false;
		boolean humanDone = false;

		// evaluate matches and number of matches for each player
		// the done flags are set for when a matching number is found
		// to limit number of unnecessary checks
		// and combine checks for both players in one loop
		for (int i = 0; i < 4; i++) {
			compDone = false;
			humanDone = false;
			for (int j = 0; j < 4; j++) {
				if (!humanDone && (humanGuess[i] == computerCode[j])) {
					
					if (i == j){ // matching digits and matching slots
						humanMatches[i] = computerCode[j];
						numHumanMatches++;
					}
					else
						humanMatches[i] = -1;
					humanDone = true;
				}

				if (!compDone && (computerGuess[i] == humanCode[j])) {
					
					if (i == j){ // matching digits and matching slots
						numComputerMatches++;
						computerMatches[i] = humanCode[j];
					} else
						computerMatches[i] = -1;
					compDone = true;
				}
			}
		}
		// set winner flag: 1 == user, 2 == computerPlayer, 3 == draw
		if (numHumanMatches == 4)
			winner = 1;
		if (numComputerMatches == 4)
			winner = 2;
		if (numHumanMatches == 4 && numComputerMatches == 4)
			winner = 3;
	}

	// Mediator function. Passes the results of a player's guess back to the player
	private void notifyPlayer(Player p, int[] matches) {
		p.receiveMatches(matches);
	}

	// sets the replay variable based on whether the user wants to play again
	private void playAgain() {
		System.out.println("Would you like to play again?");
		System.out.println("1 - Yes");
		System.out.println("0 - No");
		replay = scan.nextInt();
		
	}

	// resets main game bookkeeping variables
	private void resetGame() {
		winner = 0;
		replay = 1;
		numTurns = 0;
		computerGuess = new int[4];
		computerMatches = new int[4];
		humanGuess = new int[4];
		humanMatches = new int[4];
	}

	// asks the user to choose difficulty of the game
	private void getSettings() {

		System.out.println("Choose level difficulty:");
		System.out.println("1 - Easy");
		System.out.println("2 - Normal");
		System.out.println("3 - Hard");
		levelChoice = scan.nextInt();

		System.out.println("Select opponent intellect:");
		System.out.println("1 - Dumb");
		System.out.println("2 - Normal");
		opponentChoice = scan.nextInt();

	}
	
	// communicates the game results to the user, including the number of turns and correct answer
	private void displayResults() {
		System.out.println("====================================================================================================");
		System.out.println("====================================================================================================");
		System.out.println("");
		
		if (winner == 1) {
			System.out.println("	     YOU WIN!!! Congratulations, you managed to beat your opponent. You are the Mastermind!");
		} else if (winner == 2) {
			System.out.println("                                 YOU LOSE!!!");
			System.out.println("              Your opponent was able to correctly guess your 4 digits.");
		} else {
			System.out.println("                                   DRAW!!!");
			System.out.println("               Both players guessed the combination in equal number of turns.");
		}
		System.out.println("Your opponent's digits were: " + computerCode[0] + ", " + computerCode[1] + ", " + computerCode[2] + ", " + computerCode[3]);
		System.out.println("The game has ended after " + numTurns + " turns.");
		System.out.println("");
		System.out.println("====================================================================================================");
		System.out.println("====================================================================================================");
		System.out.println("");
	}
	
	public void showWelcomeScreen(){
		System.out.println(" ======================= WELCOME TO MASTERMIND =======================");
		System.out.println("The rules are simple: ");
		System.out.println(" - Come up with a code of 4 digits, 0 through 9");
		System.out.println(" - Try to guess your opponent's 4 digits");
		System.out.println(" - Computer will in turn try to guess your digits");
		System.out.println(" - First one to guess correctly wins the game");
		System.out.println(" - After making a guess, you will be notified about its accuracy ");
		System.out.println("");
		System.out.println("   Let's play!");
		System.out.println("");
	}
	
	public static void main( String args[] ) {
		Game g = new Game();
		g.runGame();
	}
	
}
