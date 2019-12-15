package tictactoe;

import java.util.LinkedList;

public class Board {

	static final int boardLength = 3;
	letterEnum[][] boardState = new letterEnum[boardLength][boardLength];
	public letterEnum[][] getBoardState() {
		return boardState;
	}

	LinkedList<Board> boardTree = new LinkedList<Board>();
	private letterEnum winner;
	private letterEnum nextTurn;
	private static letterEnum firstTurn;
	private int heuristic = -2;

	public LinkedList<Board> getBoardTree() {
		return boardTree;
	}

	public Board(letterEnum[][] startState){
		for(int k=0; k<boardLength; k++){
			for(int j=0; j<boardLength; j++){
				boardState[k][j]=startState[k][j];
			}
		}
	}

	public Board(letterEnum first){
		firstTurn = first;
		boardState = new letterEnum[boardLength][boardLength];
		for(int k=0; k<boardLength; k++){
			for(int j=0; j<boardLength; j++){
				boardState[k][j]=letterEnum.None;
			}
		}
	}

	public void addPiece(letterEnum piece, int row, int column) throws Exception{
		if(boardState[row][column] == letterEnum.None)
			boardState[row][column] = piece;
		else
			throw new Exception("Cannot place a piece there. It is already taken");
	}

	public letterEnum checkWinner(){
		if(winner != null)
			return winner;
		winner = letterEnum.None;
		//check for horizontal winner
		for(int k=0; k<boardLength; k++){
			if(boardState[k][0] != letterEnum.None){
				winner = boardState[k][0];
				for(int j=1; j<boardLength; j++){
					if(boardState[k][j] != winner){
						winner = letterEnum.None;
						break;
					}
				}
				if(winner != letterEnum.None)
					return winner;
			}
		}

		//check for vertical winner
		for(int k=0; k<boardLength; k++){
			if(boardState[0][k] != letterEnum.None){
				winner = boardState[0][k];
				for(int j=1; j<boardLength; j++){
					if(boardState[j][k] != winner){
						winner = letterEnum.None;
						break;
					}
				}
				if(winner != letterEnum.None)
					return winner;
			}
		}

		//check for diagonal winner
		if(boardState[0][0] != letterEnum.None){
			winner = boardState[0][0];
			for(int j=1; j<boardLength; j++){
				if(boardState[j][j] != winner){
					winner = letterEnum.None;
					break;
				}
			}
			if(winner != letterEnum.None)
				return winner;
		}
		if(boardState[0][boardLength-1] != letterEnum.None){
			winner = boardState[0][boardLength-1];
			for(int j=boardLength-1; j>0; j--){
				if(boardState[j][boardLength-j-1] != winner){
					winner = letterEnum.None;
					break;
				}
			}
			if(winner != letterEnum.None)
				return winner;
		}
		winner = letterEnum.None;
		return letterEnum.None;
	}

	//determine the next player
	//return letter of next player
	public letterEnum getNextPlayer(){
		if(nextTurn != null)
			return nextTurn;
		int countX = countFilledPieces();
		if(countX % 2 == 0){
			nextTurn = firstTurn;
		}
		else{
			if(firstTurn == letterEnum.X)
				nextTurn = letterEnum.O;
			else
				nextTurn = letterEnum.X;
		}
		return nextTurn;

	}

	//generate all possibilities starting at this board
	public void generateTree(){
		boardTree = new LinkedList<Board>();
		winner = null;
		heuristic = -2;
		nextTurn = null;
		
		checkWinner();
		if(checkWinner() != letterEnum.None)
			return;
		//first layer
		letterEnum nextLetter = getNextPlayer();
		for(int k=0; k<boardLength; k++){
			for(int j=0; j<boardLength; j++){
				if(boardState[k][j] == letterEnum.None)
				{
					Board newBoard = new Board(boardState);
					try {
						newBoard.addPiece(nextLetter, k, j);
					} catch (Exception e) {
						System.out.println("This shouldn't be read. A piece is already there.");
						System.out.println(e.getMessage());
					}
					newBoard.generateTree();
					boardTree.add(newBoard);
				}
			}
		}
	}

	// count the number of boards in the tree
	// return number of boards
	public int countBoardsInTree(){
		int count = 1;
		for(int k=0; k<boardTree.size(); k++){
			count = count + boardTree.get(k).countBoardsInTree();
		}
		return count;
	}

	// count the number of filled squares in the board
	// return number of pieces
	public int countFilledPieces(){
		int count = 0;
		for(int k=0; k<boardLength; k++){
			for(int j=0; j<boardLength; j++){
				if(boardState[k][j]!=letterEnum.None)
					count++;
			}
		}
		return count;
	}

	// print visual of current board with coordinates
	public void printInfoBoard(){
		String string = " ";
		for(int v=0; v<boardLength; v++){
			string = string + " " + String.valueOf(v);
		}
		string = string + "\n";
		for (int k=0; k<boardLength; k++){
			string = string + String.valueOf(k) + " ";
			for(int j=0; j<boardLength; j++){
				if(boardState[k][j] != letterEnum.None)
					string = string + boardState[k][j].toString();
				else
					string = string + " ";
				if(j<boardLength-1)
					string = string + "|";
			}
			string = string + "\n";
		}
		System.out.println(string);
	}
	
	// print visual of current board
	public void printBoard(){
		String string = "";
		for (int k=0; k<boardLength; k++){
			for(int j=0; j<boardLength; j++){
				if(boardState[k][j] != letterEnum.None)
					string = string + boardState[k][j].toString();
				else
					string = string + " ";
				if(j<boardLength-1)
					string = string + "|";
			}
			string = string + "\n";
		}
		System.out.println(string);
	}

	// count number of boards at the bottom of the tree
	// return number of boards
	public int countBoardsBottomOfTree(){
		int count = 0;
		if (boardTree.size()==0)
			return 1;
		else
		{
			for(int k=0; k<boardTree.size(); k++){
				count = count + boardTree.get(k).countBoardsBottomOfTree();
			}
		}
		return count;
	}

	//find the minimum possible depth of the tree
	// return minimum depth
	public int minimumDepth(){
		if(boardTree.size() == 0)
			return 1;
		else{
			int minimum = boardTree.get(0).minimumDepth();
			for (Board subBoard : boardTree){
				int value = subBoard.minimumDepth();
				if (minimum > value){
					minimum = value;
				}
			}
			return minimum + 1;
		}
	}


	// count number of times given player has won in the tree
	// input letter of player
	// return number of wins
	public int countWinners(letterEnum winnerLetter){
		int count = 0;

		if(boardTree.size()==0){
			if(this.winner == winnerLetter){
				count = 1;
			}
		}
		else
		{
			for(Board subBoard : boardTree){
				count = count + subBoard.countWinners(winnerLetter);
			}
		}
		return count;

	}

	// get heuristic for board
	// return heuristic
	public int getHeuristic(){
		if(heuristic != -2)
			return heuristic;
		int bestHeuristic = -2;
		if(boardTree.size() == 0){
			switch(checkWinner()){
			case None:
				return 0;
			case O:
				return -1;
			case X:
				return 1;
			}
		}
		else{
			if(getNextPlayer()==letterEnum.X){
				bestHeuristic = boardTree.get(0).getHeuristic();
				for(int i=1; i<boardTree.size(); i++){
					int value = boardTree.get(i).getHeuristic();
					if(value > bestHeuristic){
						bestHeuristic = value;
					}
				}
			}
			else{
				bestHeuristic = boardTree.get(0).getHeuristic();
				for(int i=1; i<boardTree.size(); i++){
					int value = boardTree.get(i).getHeuristic();
					if(value < bestHeuristic){
						bestHeuristic = value;
					}
				}
			}
		}
		heuristic = bestHeuristic;
		return bestHeuristic;
	}

	//get board in tree with best heuristic
	//return board
	public Board getBoardWithBestHeuristic(){
		LinkedList<Board> goodBoards = new LinkedList<Board>();
		heuristic = getHeuristic();
		for(int i=0; i<boardTree.size(); i++){
			if(boardTree.get(i).getHeuristic() == heuristic)
				goodBoards.add(boardTree.get(i));
		}
		//find board most likely to win
		double bestScore = -2;

		if(nextTurn==letterEnum.O)
			bestScore = 2;
		Board bestBoard = null;
		for(int i=0; i<goodBoards.size(); i++){
			double score = averageScore(goodBoards.get(i).getAllEndBoards());
			//System.out.println("score for below board: " + score);
			//goodBoards.get(i).printBoard();
			if(nextTurn == letterEnum.X){
				if(score > bestScore){
					bestScore = score;
					bestBoard = goodBoards.get(i);
				}
			}
			else{
				if(score < bestScore){
					bestScore = score;
					bestBoard = goodBoards.get(i);
				}
			}

		}
		return bestBoard;
	}

	//get all boards at the bottom of the tree
	// return boards
	public LinkedList<Board> getAllEndBoards(){
		LinkedList<Board> endBoards = new LinkedList<Board>();
		if(boardTree.size()==0){
			endBoards.add(this);
		}
		else{
			for(int i=0; i<boardTree.size(); i++){
				endBoards.addAll(boardTree.get(i).getAllEndBoards());				
			}
		}
		return endBoards;
	}

	private double averageScore(LinkedList<Board> boards){
		int sum = 0;
		for(int i=0; i<boards.size(); i++){
			sum = sum + boards.get(i).getHeuristic();
		}
		double value = ((double) sum)/((double) boards.size());
		return value;
	}
	
	//reset Board object
	public void reset(){
		boardTree = new LinkedList<Board>();
		winner = null;
		heuristic = -2;
		nextTurn = null;

	}
	
	//count how many times game ends at a given layer
	// input layer to check, current layer being looked at
	//return times game ends at desired layer
	public int countEndsAtLayer(int layerToCheck, int currentLayer){
		int sum = 0;
		if(layerToCheck == currentLayer){
			if (boardTree.size() == 0)
				sum = 1;			
		}
		else
		{
			for (int i=0; i<boardTree.size(); i++)
				sum = sum + boardTree.get(i).countEndsAtLayer(layerToCheck, currentLayer + 1);
		}
		return sum;
	}
}



