//package mastermind;

import java.util.Scanner;

public class Game {

	private void displayResults() {
		if (winner == 1) {
			System.out.println("YOU WIN!!!");
		} else if (winner == 2) {
			System.out.println("YOU LOSE!!!");
		} else {
			System.out.println("DRAW!!!");
			System.out.println("Both players guessed the combination in equal number of turns.");
		}
	}

	private int[] getGuess(Player p) {
		return p.guess();
	}
	
	public Game() {
		
	}

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
			for (int j = 0; j < 4; i++) {
				if (!humanDone && (humanGuess[i] == computerCode[j])) {
					numHumanMatches++;
					if (i == j) // matching digits and matching slots
						humanMatches[i] = computerCode[j];
					else
						humanMatches[i] = -1;
				}

				if (!compDone && (computerGuess[i] == humanGuess[j])) {
					numComputerMatches++;
					if (i == j) // matching digits and matching slots
						computerMatches[i] = humanCode[j];
					else
						computerMatches[i] = -1;
				}
			}
		}

		if (numHumanMatches == 4)
			winner = 1;
		if (numComputerMatches == 4)
			winner = 2;

		if (numHumanMatches == 4 && numComputerMatches == 4)
			winner = 3;
	}

	private void notifyPlayer(Player p, int[] matches) {

	}

	private void playAgain() {
		System.out.println("Would you like to play again?");
		System.out.println("1 - Yes");
		System.out.println("2 - No");
		replay = scan.nextInt();
	}

	private void reset() {
		winner = 0;
		replay = 1;

		computerGuess = new int[4];
		computerMatches = new int[4];
		humanGuess = new int[4];
		humanMatches = new int[4];
	}

	private void getSettings() {

		System.out.println("Choose level difficulty:");
		System.out.println("1 - Easy");
		System.out.println("2 - Normal");
		System.out.println("3 - Hard");
		levelChoice = scan.nextInt();

		System.out.println("Select opponent intellect:");
		System.out.println("1 - Dumb");
		System.out.println("2 - Normal");
		System.out.println("3 - Smart");
		opponentChoice = scan.nextInt();

	}

	Player humanPlayer;
	Player computerPlayer;
	
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

	public void runGame() {
		scan = new Scanner(System.in);
		while (replay > 1) {
			
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
			
			while (replay == 1) {
				//getCode is causing errors
				//humanCode = getCode(humanPlayer);
				
				while (winner == 0) {
					humanGuess = getGuess(humanPlayer);

					computerGuess = getGuess(computerPlayer);

					notifyPlayer(computerPlayer, computerMatches);
					notifyPlayer(humanPlayer, humanMatches);

					checkResults();

					if (winner > 0) {
						displayResults();
						reset();
						playAgain();
					}
				}

			}
		}
	}
}
