package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Board gameBoard;


	public static void main(String[] args) {
		try {
			printGameInformation();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		boolean validPlayerNum = false;
		while(!validPlayerNum){ //wait for entry to be valid number of players
			System.out.println("Enter number of human players: 0, 1, or 2. Or press 3 for game information: ");
			String playerString = null;
			try {
				playerString = reader.readLine();
				int numberOfPlayers = Integer.parseInt(playerString);
				switch(numberOfPlayers){
				case 0:
					computerPlaysOnly();
					validPlayerNum = true;

					break;
				case 1:
					System.out.println("X is always human in one player game.");
					onePlayerGame();
					validPlayerNum = true;
					break;
				case 2:
					twoPlayerGame();
					validPlayerNum = true;
					break;
				case 3:
					boardInformation();
					break;
				}
			} catch (Exception e) {
				System.out.println("Unusable number of players. Please try again");
			}
		}
		System.out.println("final board: ");
		gameBoard.printBoard();
		System.out.println("Game over. winner: " + gameBoard.checkWinner());
	}

	//one human vs the computer
	private static void onePlayerGame() {
		chooseFirstPlayer();
		while(gameBoard.checkWinner() == letterEnum.None && gameBoard.countFilledPieces() != 9){
			gameBoard.reset();
			if(gameBoard.getNextPlayer() == letterEnum.X){
				//gameBoard.printBoard();
				readInputForBoard();
			}	
			else{
				System.out.println("Next turn is " + gameBoard.getNextPlayer().toString() + ". Please wait for PC...");
				gameBoard.generateTree();
				gameBoard = gameBoard.getBoardWithBestHeuristic();
				gameBoard.printBoard();
			}
		}
	}


	//have computer play itself
	private static void computerPlaysOnly(){
		chooseFirstPlayer();
		while(gameBoard.checkWinner() == letterEnum.None && gameBoard.countFilledPieces() != 9){
			gameBoard.reset();
			System.out.println("Next turn is " + gameBoard.getNextPlayer().toString() + ". Please wait for PC...");
			gameBoard.generateTree();
			gameBoard = gameBoard.getBoardWithBestHeuristic();
			gameBoard.printBoard();
		}
	}

	//view board information and game statistics
	private static void boardInformation(){
		System.out.println("See tic-tac-toe statistics");
		chooseFirstPlayer();
		gameBoard.generateTree();
		
		//find total games possible
		int totalGamesPossible = gameBoard.countBoardsBottomOfTree();
		System.out.println("total boards at bottom: " + totalGamesPossible);
		int counter = 0;
		int[] winCount = new int[3];
		for(letterEnum letter: letterEnum.values()){
			winCount[counter] = gameBoard.countWinners(letter);
			counter++;
		}
		
		//calculate how many times each player wins
		double[] percentPlayerWins = new double[3];
		for(int i=0; i<percentPlayerWins.length; i++){
			percentPlayerWins[i]=Math.round(((double) winCount[i])/((double) totalGamesPossible)*10000.0)/100.0;
			System.out.println("winners for " + letterEnum.values()[i].toString() + ": " + winCount[i] + " (" + percentPlayerWins[i] + "%)");
		}
		
		//percent of games at each level
		for(int i=0; i<10; i++){
			int numberOfGamesForLayer = gameBoard.countEndsAtLayer(i, 0);
			double percentAtLayer = ((double) numberOfGamesForLayer)/((double) totalGamesPossible)*100;
			double roundedPercentAtLayer = Math.round(percentAtLayer*100)/100.0;
			System.out.println("Number of games ending after " + i + " turns: "
			+ numberOfGamesForLayer + " (" + roundedPercentAtLayer + "%)");
		}
	}

	//start a two player game
	private static void twoPlayerGame(){
		//players play
		chooseFirstPlayer();
		while(gameBoard.checkWinner() == letterEnum.None && gameBoard.countFilledPieces() != 9){
			readInputForBoard();
		}

	}

	//read user input for next move
	private static void readInputForBoard(){
		boolean validPlacement = false;
		while(!validPlacement){
			gameBoard.reset();
			letterEnum nextLetter = gameBoard.getNextPlayer();
			System.out.println("Next turn is " + nextLetter.toString() + ". Please give row number then column number:");
			String newTurn = null;
			try {
				newTurn = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(newTurn.equals("s") && !newTurn.equals("S")){
					System.out.println("Getting suggestion:");
					Board testBoard = new Board(gameBoard.getBoardState());
					testBoard.generateTree();
					testBoard.getBoardWithBestHeuristic().printBoard();
				}
				else{
					if(newTurn.equals("h") && !newTurn.equals("H")){
						printGameInformation();
					}
					else{
						gameBoard.addPiece(nextLetter, Character.getNumericValue(newTurn.charAt(0)), 
								Character.getNumericValue(newTurn.charAt(2)));
						validPlacement = true;
					}
				}
				gameBoard.printBoard();

			} catch (Exception e) {
				System.out.println("Unable to put a piece there. Please try again.");

			}
		}

	}

	private static void printGameInformation() throws Exception{
		Board infoBoard = new Board(letterEnum.X);
		infoBoard.printInfoBoard();
		System.out.println("Welcome to tic-tac-toe!");
		System.out.println("You can't beat the machine.");
		System.out.println("Input moves by giving row number, then column number");
		System.out.println("See chart above for numbers reference");
		System.out.println("Example: Typing in '0 2' when X's turn gives you this:");
		infoBoard.addPiece(letterEnum.X, 0, 2);
		infoBoard.printBoard();
		System.out.println("Get ready to play!");
	}

	//choose the first player
	private static void chooseFirstPlayer(){
		System.out.println("Choose first player: X or O.");
		gameBoard = null;

		boolean validInput = false;
		String turnInput;
		while(!validInput){
			try {
				turnInput = reader.readLine();
				switch(turnInput){
				case "X":
				case "x":
					gameBoard = new Board(letterEnum.X);
					break;
				case "O":
				case "o":
					gameBoard = new Board(letterEnum.O);
					break;
				default:
					throw new Exception("bad input for first player");
				}
				validInput = true;
				gameBoard.printBoard();
			} catch (Exception e) {
				System.out.println("Unable to read input. Please choose either 'X' or 'O'");
			}

		}
	}
}
