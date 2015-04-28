import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

public class Board {

	int[][] board;
	int turn = -1;
	int[] prevMove;

	public Board(int[][] newboard, int turn) {

		this.board = newboard;
		this.turn =  turn;
	}



	public Board() {
		board = new int[8][8];
		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++)
				board[i][j] = 0;
		board[3][3] = 1;
		board[3][4] = -1;
		board[4][3] = -1;
		board[4][4] = 1;
	}

	public void print() {
		for (int[] sub : board) {
			for (int subsub : sub) {
				if (subsub == 1)
					System.out.print("*" + " ");
				else if (subsub == -1)
					System.out.print("/" + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
	}

	public void move(int x, int y) {
		// board[x][y] = turn;
		Update(x, y);
		//player 1
		if (turn == 1)
			turn = -1;
		// player - 1
		else if (turn == -1)
			turn = 1;
		prevMove = new int[] {x, y};
	}

	/*
	 * use the canfilp and dofilp method to
	 * update the board.
	 */
	public void Update(int X, int Y) {
		int i, j;
		int[][] state = this.board;
		if (X < 0) return; /* pass move */
		if (state[X][Y] != 0) {
			// print();
			System.out.println("Illegal move");
		}
		state[X][Y] = turn;
		for (i = -1; i <= 1; i++)
			for (j = -1; j <= 1; j++)
				if ((i != 0 || j != 0) && CanFlip(X, Y, i, j))
					DoFlip(X, Y, i, j);
	}

	public void DoFlip(int X, int Y, int dirX, int dirY) {
		int[][] state = this.board;
		while (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == -turn) {
			X = X + dirX; Y = Y + dirY;
			state[X][Y] = turn;
		}
	}

	public boolean CanFlip(int X, int Y, int dirX, int dirY) {
		int[][] state = this.board;
		boolean capture = false;
		while (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == -turn) {
			X = X + dirX; Y = Y + dirY;
			capture = true;
		}
		if (capture == false) return false;
		if (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == turn)
			return true;
		else return false;
	}

	public ArrayList<int[]> getLegalMoves() {

		ArrayList<int[]> moves = new ArrayList<int[]>();

		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++)
				if (board[i][j] == turn) {
					//Up
					boolean didJump = false;
					int tempI = i;
					int tempJ = j;
					while (tempJ > 1) {
						tempJ --;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Up && Right
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempJ > 1 && tempI < 7) {
						tempJ --;
						tempI ++;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Right
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempI < 7) {
						tempI ++;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Right && Down
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempI < 7 && tempJ < 7) {
						tempI ++;
						tempJ ++;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Down
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempJ < 7) {
						tempJ ++;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Down && Left
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempJ < 7 && tempI > 1) {
						tempI --;
						tempJ ++;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Left
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempI > 1) {
						tempI --;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
					//Left && Up
					didJump = false;
					tempI = i;
					tempJ = j;
					while (tempI > 1 && tempJ > 1) {
						tempJ --;
						tempI --;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new int[] {tempI, tempJ});
						break;
					}
				}

		ArrayList<int[]> tempMoves = new ArrayList<int[]>(moves);
		ArrayList<Integer> dupIndex = new ArrayList<Integer>();
		for (int i = 0; i < tempMoves.size(); i++)
			for (int j = i + 1; j < tempMoves.size(); j++)
				if (!dupIndex.contains(j))
					if (tempMoves.get(i)[0] == tempMoves.get(j)[0])
						if (tempMoves.get(i)[1] == tempMoves.get(j)[1])
							dupIndex.add(j);
		Comparator<Integer> comparator = Collections.reverseOrder();
		Collections.sort(dupIndex, comparator);
		for (int sub : dupIndex)
			tempMoves.remove(sub);
		return tempMoves;
	}
}