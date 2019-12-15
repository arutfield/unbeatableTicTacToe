package test;

import java.util.LinkedList;

import junit.framework.TestCase;
import tictactoe.Board;
import tictactoe.letterEnum;

public class BoardTest extends TestCase {

	public void testCreateBoard() throws Exception{
		Board board = new Board(letterEnum.X);
		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.None);
	}

	public void testCreateBoardTopRowX() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 0, 1);
		board.addPiece(letterEnum.X, 0, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.X);
	}

	public void testCreateBoardTopRowO() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 0, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.O);
	}

	public void testCreateBoardMidRowO() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.O, 1, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.O);
	}

	public void testCreateBoardBotRowX() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 2, 1);
		board.addPiece(letterEnum.X, 2, 0);
		board.addPiece(letterEnum.X, 2, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.X);
	}

	public void testCreateBoardLeftColX() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 2, 0);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.X);
	}

	public void testCreateBoardMidColO() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 2, 1);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.O);
	}

	public void testCreateBoardDiagnolRO() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 2, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.O);
	}

	public void testCreateBoardDiagnolLX() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 2, 0);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);

		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.X);
	}

	public void testCreateBoardTie() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 2);
		board.addPiece(letterEnum.O, 0, 2);
		board.addPiece(letterEnum.X, 0, 1);
		board.addPiece(letterEnum.O, 2, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.addPiece(letterEnum.X, 2, 0);
		System.out.println("create board tie:");
		board.printBoard();
		letterEnum winner = board.checkWinner();
		assertEquals(winner, letterEnum.None);
	}

	public void testxStarts() throws Exception{
		Board board = new Board(letterEnum.X);
		letterEnum winner = board.getNextPlayer();
		assertEquals(winner, letterEnum.X);
	}

	public void testxBoard() throws Exception{
		Board board = new Board(letterEnum.X);

		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.X, 0, 1);

		letterEnum winner = board.getNextPlayer();
		assertEquals(winner, letterEnum.X);
	}


	public void testOBoard() throws Exception{
		Board board = new Board(letterEnum.X);

		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.X, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);

		letterEnum winner = board.getNextPlayer();
		assertEquals(winner, letterEnum.O);
	}

	public void testBoardFirstLayer() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();

		assertEquals(board.getBoardTree().size(), 3*3);
	}

	public void testBoardSecondLayer() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();

		assertEquals(board.getBoardTree().get(0).getBoardTree().size(), 3*3-1);
	}

	public void testBoardThirdLayer() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();

		assertEquals(board.getBoardTree().get(0).getBoardTree().get(0).getBoardTree().size(), 3*3-2);
	}

	public void testCountFullBoard() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 2, 1);
		board.addPiece(letterEnum.X, 2, 2);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.addPiece(letterEnum.O, 2, 0);
		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertEquals(count, 1);
	}

	public void testCountTwoLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 2, 2);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countBoardsInTree();
		assertEquals(count, 4);
	}

	public void testCountFullTree() throws Exception{
		Board board = new Board(letterEnum.X);

		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 9*8*7*6*5*4*3*2);
	}

	public void testCountOneLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 8*7*6*5*4*3*2 + 1);
	}

	public void testCountTwoLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 7*6*5*4*3*2 + 1);
	}

	public void testCountThreeLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 2, 0);

		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 6*5*4*3*2 + 1);
	}

	public void testCountFourLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 2, 0);
		board.addPiece(letterEnum.O, 0, 1);

		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 5*4*3*2 + 1);
	}

	public void testCountSixLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 2, 0);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.O, 2, 1);

		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count == 4);
	}

	public void testCountSevenLayerDownTree() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 2, 0);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.O, 2, 1);
		board.addPiece(letterEnum.X, 0, 2);
		board.printBoard();
		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertTrue(count < 2 + 1);
	}

	public void testCountMinDeepTwoLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.minimumDepth();
		assertEquals(count, 2);
	}

	public void testCountThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countBoardsInTree();
		assertEquals(count, 11);
	}

	public void testCountEndThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countBoardsBottomOfTree();
		assertEquals(count, 5);
	}

	public void testCountMinDeepFull() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		int count = board.minimumDepth();
		assertEquals(count, 6);
	}

	public void testCountWinnerXThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countWinners(letterEnum.X);
		assertEquals(count, 2);
	}

	public void testCountWinnerOThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countWinners(letterEnum.O);
		assertEquals(count, 1);
	}

	public void testCountWinnerNThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.countWinners(letterEnum.None);
		assertEquals(count, 2);
	}

	public void testCountWinnerNThreeLayersAgain() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 1);

		board.generateTree();
		int count = board.countWinners(letterEnum.None);
		assertEquals(count, 0);
	}

	public void testCountWinnerXThreeLayersAgain() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 1);
		board.printBoard();

		board.generateTree();
		int count = board.countWinners(letterEnum.X);
		assertEquals(count, 3);
	}


	public void testCountWinnerOThreeLayersAgain() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 1);
		board.printBoard();
		board.generateTree();
		int count = board.countWinners(letterEnum.O);
		assertEquals(count, 2);
	}

	public void testGetHeuristicThreeLayersAgain() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 1);
		board.printBoard();
		board.generateTree();
		int count = board.getHeuristic();
		assertEquals(count, 1);
	} 

	public void testHeuristicThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		int count = board.getHeuristic();
		assertEquals(count, 1);
	}

	public void testHeuristicThreeLayersOTurn() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.addPiece(letterEnum.X, 2, 2);

		board.generateTree();
		int count = board.getHeuristic();
		assertEquals(count, -1);
	}

	public void testGetHeuristicThreeLayersOTurn() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 0);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 2, 1);
		board.addPiece(letterEnum.X, 1, 2);

		board.printBoard();
		board.generateTree();
		int count = board.getHeuristic();
		assertEquals(count, -1);
	} 

	public void testGetHeuristicThreeLayersXCantWin() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 2, 0);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 2, 2);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);

		board.printBoard();
		board.generateTree();
		int count = board.getHeuristic();
		assertEquals(count, 0);
	}

	public void testXWinThreeLayersXCantWin() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 2, 0);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 2, 2);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);

		board.printBoard();
		board.generateTree();
		int count = board.countWinners(letterEnum.X);
		assertEquals(count, 0);
	}

	public void testOWinThreeLayersXCantWin() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 2, 0);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 2, 2);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);

		board.printBoard();
		board.generateTree();
		int count = board.countWinners(letterEnum.O);
		assertEquals(count, 2);
	}

	public void testEndBoardsThreeLayersXCantWin() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 2, 0);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 2, 2);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);

		board.printBoard();
		board.generateTree();
		LinkedList<Board> endBoards = board.getAllEndBoards();
		assertEquals(endBoards.size(), 6);
	}

	public void testEndBoardsThreeLayers() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);

		board.generateTree();
		LinkedList<Board> endBoards = board.getAllEndBoards();
		System.out.println("endBoards:");
		for(int i=0; i<endBoards.size(); i++)
			endBoards.get(i).printBoard();
		System.out.println("end boards done");
		assertEquals(endBoards.size(), 5);
	}

	public void testCreateBoardEndList() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		LinkedList<Board> endBoards = board.getAllEndBoards();
		assertEquals(endBoards.size(), 255168);
	}

	public void testEndBoardsThreeLayersOCantWin() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 0);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.O, 1, 2);
		board.addPiece(letterEnum.X, 1, 1);
		board.addPiece(letterEnum.X, 0, 2);
		System.out.println("start board: ");
		board.printBoard();
		board.generateTree();
		Board bestBoard = board.getBoardWithBestHeuristic();
		System.out.println("best board: ");
		bestBoard.printBoard();
		assertEquals(bestBoard.getHeuristic(), 1);
	}

	public void testCountEndBoardsAtLayer0() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(0, 0);
		assertEquals(0, boardsAtEnd);
	}

	public void testCountEndBoardsAtLayer1() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(1, 0);
		assertEquals(0, boardsAtEnd);
	}

	public void testCountEndBoardsAtLayer5() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(5, 0);
		System.out.println("boards at end: " + boardsAtEnd);
		assertFalse(boardsAtEnd == 0);
	}

	public void testCountEndBoardsCustomBoardAtLayer5() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(5, 0);
		System.out.println("boards at end: " + boardsAtEnd);
		assertEquals(0, boardsAtEnd);
	}

	public void testCountEndBoardsCustomBoardAtLayer1() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(1, 0);
		System.out.println("boards at end: " + boardsAtEnd);
		assertEquals(1, boardsAtEnd);
	}

	public void testCountEndBoardsCustomBoardAtLayer2() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(2, 0);
		System.out.println("boards at end: " + boardsAtEnd);
		assertEquals(1, boardsAtEnd);
	}


	public void testCountEndBoardsCustomBoardAtLayer3() throws Exception{
		Board board = new Board(letterEnum.X);
		board.addPiece(letterEnum.X, 0, 0);
		board.addPiece(letterEnum.X, 1, 0);
		board.addPiece(letterEnum.X, 0, 2);
		board.addPiece(letterEnum.O, 0, 1);
		board.addPiece(letterEnum.O, 1, 1);
		board.addPiece(letterEnum.O, 1, 2);
		board.generateTree();
		int boardsAtEnd = board.countEndsAtLayer(3, 0);
		System.out.println("boards at end: " + boardsAtEnd);
		assertEquals(3, boardsAtEnd);
	}

	public void testCountEndBoardsAtLayerAll() throws Exception{
		Board board = new Board(letterEnum.X);
		board.generateTree();
		int boardsAtEnd = 0;
		for(int i=0; i<10; i++)
			boardsAtEnd = boardsAtEnd + board.countEndsAtLayer(i, 0);
		assertEquals(255168, boardsAtEnd);
	}
}
