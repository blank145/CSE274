

import java.util.*;

public class Board  {
	private int[][] board;
	private int[][] key;

	public Board(int[][] n) {
		board = n;
		solution(n.length);
	}

	public int[][] getBoard() {
		return board;
	}

	public int[][] getKey() {
		return key;
	}

	private int[][] solution(int n) {
		key = new int[n][n];
		int count = 1;
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				key[i][j] = count;
				count++;
			}
		}
		key[n - 1][n - 1] = -1;
		return key;
	}

	public boolean equals(Object other) {
		if(other instanceof Board) {
			Board brd = (Board) other;
			for(int i = 0; i < board.length ; i++) {
				if(!Arrays.equals(board[i], brd.board[i])) {
					return  false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < board.length; i++) {
			hash += Arrays.hashCode(board[i]);
		}
		return hash;
	}

	public static int[][] copyBoard(int[][] n) {
		int[][] copy = new int[n.length][n.length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy.length; j++) {
				copy[i][j] = n[i][j];
			}
		}
		return copy;
	}

	public Set<Board> getSuccessors() {
		Set<Board> successors = new HashSet<>();
		int[] empty = findEmpty();
		int length = board.length;
		int x = empty[0];
		int y = empty[1];

		Board up = new Board(copyBoard(board));
		Board down = new Board(copyBoard(board));
		Board left = new Board(copyBoard(board));
		Board right = new Board(copyBoard(board));

		// can move up
		if (x >= 1) {
			moveUp(up, x, y);
			successors.add(up);
		}
		// can move down
		if (x < length - 1 ) {
			moveDown(down, x, y);
			successors.add(down);
		}
		// can move left
		if (y >= 1) {
			moveLeft(left, x, y);
			successors.add(left);
		}
		// can move right
		if (y < length-1) {
			moveRight(right, x, y);
			successors.add(right);
		}
		return successors;
	}

	private void moveUp(Board up, int x, int y) {
		int temp = up.board[x-1][y];
		up.board[x-1][y] = -1;
		up.board[x][y] = temp;

	}

	private void moveDown(Board down, int x, int y) {
		int temp = down.board[x+1][y];
		down.board[x+1][y] = -1;
		down.board[x][y] = temp;
	}

	private void moveLeft(Board left, int x, int y) {
		int temp = left.board[x][y-1];
		left.board[x][y-1] = -1;
		left.board[x][y] = temp;

	}

	private void moveRight(Board right, int x, int y) {
		int temp = right.board[x][y+1];
		right.board[x][y+1] = -1;
		right.board[x][y] = temp;
	}

	private int[] findEmpty() {
		int x = 0;
		int y = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == -1) {
					x = i;
					y = j;
				}
			}
		}
		int[] location = new int[] { x, y };
		return location;
	}
	
//	public String toString() {
//		String result = "";
//		for(int i =0; i < this.board.length; i++) {
//			for(int j = 0; j < this.board[i].length; j++) {
//				result = result + board[i][j] + " ";
//			}
//			result += "\n";
//		}
//		return result;
//	}

}
