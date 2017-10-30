package edu.miamioh.wangy99;

public class PuzzleDemo {
	public static void main(String[] args) {
		int[][] t1 = new int[][] { { -1, 1, 2 }, { 5, 6, 3 }, { 4, 7, 8 } };
		int[][] v2 = { { -1, 2, 1 }, { 5, 6, 3 }, { 4, 7, 8 } };

		Board v1 = new Board(t1);
		Board v3 = new Board(t1);
		System.out.println("Equals and hashCode: ");
		System.out.println(v1.equals(v3));
		System.out.println(v1.hashCode() + " " + v3.hashCode());
		
		 System.out.println(NPuzzle.solvable(t1));//
		 System.out.println(NPuzzle.solvable(v2));//

	}
}
