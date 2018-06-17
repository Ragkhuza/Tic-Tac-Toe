package ticTacToe;

import java.util.Scanner;

public class tictactoe {
	// Content of player
	static final int EMPTY = 0;
	static final int CROSS = 1;
	static final int CIRCLE = 2;

	// Game state
	static final int PLAYING = 0;
	static final int DRAW = 1;
	static final int CROSS_WON = 2;
	static final int CIRCLE_WON = 3;

	static int currentRow;
	static int currentCol;

	static int gameState;
	static int playerTurn;

	static Scanner input = new Scanner(System.in);
	
	//static int ans;

	static final int ROWS = 3;
	static final int COLS = 3;
	static int[][] board = new int[ROWS][COLS];

	public static void main(String[] args) {
		startGame();
		printBoard();
		do {
			// System.out.println(playerTurn + " " + currentRow + " " +
			// currentCol + " " + gameState); // debugging
			playerMove(playerTurn);
			updateState(playerTurn, currentRow, currentCol);
			// System.out.println("Gamestate: " + gameState); // debug
			printBoard();

			if (gameState == CROSS_WON) {
				System.out.println("Player 1 has won!");
				restart();
			} else if (gameState == CIRCLE_WON) {
				System.out.println("Player 2 has won!");
				restart();
			} else if (gameState == DRAW) {
				System.out.println("It's a Draw!");
				restart();
			}

			playerTurn = (playerTurn == CROSS) ? CIRCLE : CROSS;
		} while (gameState == PLAYING);

	}

	private static void restart() {
		System.out.println("Would you like to play again(y/n?)");
		char ans = input.next().charAt(0);
		if (ans == 'y' || ans == 'Y') {
			startGame();
			printBoard();
			gameState = PLAYING;
		}else {
			System.out.println("Okay, bye!");
			System.exit(0);
		}
		
	}

	private static void updateState(int cPlayer, int curRow, int curCol) {
		if (checkWon(cPlayer, curRow, curCol)) {
			gameState = (playerTurn == CROSS) ? CROSS_WON : CIRCLE_WON;
		} else if (isDraw()) {
			gameState = DRAW;
		}

	}

	private static boolean isDraw() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				if (board[row][col] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean checkWon(int pTurn, int currentRow, int currentCol) {
		// check for row win
		return (board[currentRow][0] == pTurn && board[currentRow][1] == pTurn && board[currentRow][2] == pTurn
				// check for col win
				|| board[0][currentCol] == pTurn && board[1][currentCol] == pTurn && board[2][currentCol] == pTurn
				// check for diagonal win
				|| board[0][0] == pTurn && board[1][1] == pTurn && board[2][2] == pTurn
				// check for reverse diagonal win
				|| board[0][2] == pTurn && board[1][1] == pTurn && board[2][0] == pTurn);
	}

	private static void playerMove(int player) {
		boolean validInput = false;
		do {
			System.out.println();
			System.out.println("Player " + player + "'s turn");
			System.out.println("Pick Row(1 - 3): ");
			currentRow = input.nextInt() - 1;
			System.out.println("Pick Column(1 - 3): ");
			currentCol = input.nextInt() - 1;
			System.out.println();

			if (currentRow >= 0 && currentRow < 3 && currentCol >= 0 && currentCol < 3
					&& board[currentRow][currentCol] == EMPTY) {
				board[currentRow][currentCol] = player;
				validInput = true;
			} else {
				System.out.println("Please enter a valid input!");
			}
		} while (validInput != true);

	}

	private static void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				printBlock(board[row][col]);
				if (col != COLS - 1) {
					System.out.print(" | "); // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("- - - - - ");
			}
		}
	}

	private static void printBlock(int i) {
		switch (i) {
		case EMPTY:
			System.out.print(" ");
			break;
		case CROSS:
			System.out.print("X");
			break;
		case CIRCLE:
			System.out.print("O");
			break;
		}

	}

	private static void startGame() {
		System.out.print("Loading");

		try {
			for (int x = 0; x != 50; x++) {
				System.out.print(".");
				Thread.sleep(100);
			}
			System.out.println();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = EMPTY;
			}
		}
		gameState = PLAYING;
		playerTurn = CROSS;

	}
}
