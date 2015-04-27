import java.util.ArrayList;


public class Board {

	byte[][] board;
	byte turn = 1;

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
		board[x][y] = turn;
		if (turn == 1) {
			turn = -1;
		} else if (turn == -1) {
			turn = 1;
		}
	}

	public ArrayList<byte[]> getLegalMoves() {
		ArrayList<byte[]> moves = new ArrayList<>();
		for (byte i = 0; i < 8; i ++)
			for (byte j = 0; j < 8; j++)
				if (board[i][j] == turn) {
					//Up
					boolean didJump = false;
					byte tempI = (byte) i;
					byte tempJ = (byte) j;
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