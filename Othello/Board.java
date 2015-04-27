import java.util.ArrayList;


public class Board {

	byte[][] board;
	byte turn = 1;
	byte[] prevMove;

	public Board(Board newboard) {
		this.board = newboard.board;
		this.turn = newboard.turn;
	}

	public Board() {
		board = new byte[8][8];
		for (int i = 0; i < 8; i ++)
			for (int j = 0; j < 8; j++)
				board[i][j] = 0;
		board[3][3] = 1;
		board[3][4] = -1;
		board[4][3] = -1;
		board[4][4] = 1;
	}

	public void print() {
		for (byte[] sub : board) {
			for (byte subsub : sub)
				System.out.print(subsub + " ");
			System.out.println();
		}
	}

	public void move(byte x, byte y) {
		// board[x][y] = turn;
		Update(x, y);
		//player 1
		if (turn == 1)
			turn = -1;
		// player - 1
		else if (turn == -1)
			turn = 1;
		prevMove = new byte[] {x, y};
	}

	/*
	 * By using the canfilp and dofilp method to
	 * update the board.
	 */
	public void Update(byte X, byte Y) {
		int i, j;
		byte[][] state = this.board;
		if (X < 0) return; /* pass move */
		if (state[X][Y] != 0) {
			print();
			// printboard(state, turn, turn, X, Y);
			System.out.println("Illegal move");
		}
		state[X][Y] = turn;
		for (i = -1; i <= 1; i++)
			for (j = -1; j <= 1; j++)
				if ((i != 0 || j != 0) && CanFlip(X, Y, i, j))
					DoFlip(X, Y, i, j);
	}

	public void DoFlip(int X, int Y, int dirX, int dirY) {
		byte[][] state = this.board;
		while (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == -turn) {
			X = X + dirX; Y = Y + dirY;
			state[X][Y] = turn;
		}
	}

	public boolean CanFlip(int X, int Y, int dirX, int dirY) {
		byte[][] state = this.board;
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

	public ArrayList<byte[]> getLegalMoves() {
		ArrayList<byte[]> moves = new ArrayList<>();
		for (byte i = 0; i < 8; i ++)
			for (byte j = 0; j < 8; j++)
				if (board[i][j] == turn) {
					//Up
					boolean didJump = false;
					byte tempI = i;
					byte tempJ = j;
					while (tempJ > 1) {
						tempJ --;
						if (board[tempI][tempJ] == turn)
							break;
						if (board[tempI][tempJ] == turn * -1) {
							didJump = true;
							continue;
						}
						if (didJump)
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
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
							moves.add(new byte[] {tempI, tempJ});
						break;
					}
				}
		return moves;
	}
}