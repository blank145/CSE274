package edu.miamioh.wangy99;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Yichen Wang
 * 
 */
public class NPuzzle {
	
	private Set<Board> states;
	private Map<Board, Set<Board>> adjList;
	
	public NPuzzle(int[][] board) {
		
		states = new HashSet<Board>();
		adjList = new HashMap<Board, Set<Board>>();
		Board origState = new Board(board);
		states.add(origState);
		
		Queue<Board> toProcess = new LinkedList<>();
		toProcess.add(origState);

		while (!toProcess.isEmpty()) {
			//System.out.println(toProcess.size());
			Board brd = toProcess.remove();
			if (!adjList.containsKey(brd)) {
				Set<Board> successors = brd.getSuccessors();
				adjList.put(brd, successors);
				toProcess.addAll(successors);
				states.addAll(successors);
			}
		}
	}

	public Set<Board> getSuccessors(Board brd) {
		return adjList.get(brd);
	}

	public void display() {
		for (Board brd : states) {
			System.out.printf("%s: ", brd);
			for (Board succ : adjList.get(brd)) {
				System.out.printf("%s ", succ);
			}
			System.out.println();
		}
	}
		
	public static boolean isSquare(int[][] board) {
		for(int i = 0; i < board.length-1; i++) {
			if(board[i].length != board.length) {
				return false;
			}
			if(board[i].length != board[i+1].length) {
				return false;
			}
		}	
		return true;
	}
	//store all the int value to a set to check duplicates
	public static boolean isValidPuzzle(int[][] board) {
		boolean result = false;							
		int n = board.length;
		Set<Integer> check = new HashSet<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; i++) {
				if(board[i][j] > 0) {
					check.add(board[i][j]);
				}
			}
		}
		if(check.size() == n*n-1) {
			result = true;
		}
		return result;
	}
	public static boolean solvable(int[][] board) {
		assert(isSquare(board) && isValidPuzzle(board));//check 2d int array board is valid
		boolean flag = false;
		NPuzzle solution = new NPuzzle(board);
		Board temp = new Board(board);
		int[][] key = temp.getKey();
		
		for(Board s: solution.adjList.keySet()) {
			if(solution.adjList.get(s).contains(new Board(key))) {
				flag = true;
			}
		}
		return flag;
	}
}
