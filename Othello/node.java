 import java.util.ArrayList;

public class node {

	public Board state;
	// public int[] move;

	public int alpha = Integer.MIN_VALUE; //default alpha
	public int beta = Integer.MAX_VALUE; //default beta

	public int score;
	public int layer;
	// public int player;

	public node parent;
	public ArrayList<node> childList;

	public node(Board s) {


		childList = new ArrayList<node>();

		//this.player = player;

		this.state = s;
		this.layer = 0;

		this.score = getScore();

	}


//	public node(Board state, int x, int y) {
//
//
//		this.state = state;
//		this.state.move(x, y);
//		this.layer = 0;
//		getScore();
//
//	}

	/*
	 * take current player and state
	 * to create a new child node
	*/
	public void addChild(node child) {

		//System.out.println("this child's layer is "+child.layer);
		child.parent = this;
		//System.out.println("this parent layer is:"+this.parent.layer);
		// child.player = -(this.player);
		this.childList.add(child);
	}

	public void setParent(node parent) {
		this.parent = parent;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public ArrayList<tuple> getChildBoards() {

		//the tuple contains the new board
		//and the move, which directs to the new board
		ArrayList<tuple> boardTuple = new ArrayList<tuple>();

		for (int[] move : this.state.getLegalMoves()) {

			int[][] tempShit = new int[8][8];

			for (int i = 0; i < state.board.length; i++) {
				for (int j = 0; j < state.board[i].length; j++) {

					tempShit[i][j] = state.board[i][j];
				}
			}

			//System.out.println("the parent board's turn is "+this.state.turn);

            //System.out.println("current player: " +this.state.turn);

            //System.out.println("the current board's turn is "+this.state.turn);
			Board tempBoard = new Board(tempShit, (this.state.turn));
			//System.out.println("the child board's turn is "+tempBoard.turn);

			tempBoard.move(move[0], move[1]);
			//System.out.println("move!");
			tuple t = new tuple(tempBoard, move[0], move[1]);
			//System.out.println("the original tuple turn is "+t.board.turn);
			//state.print();
			//do the all flip stuffs, and return the most up-to-date board

			boardTuple.add(t);

		}

		return boardTuple;
	}

	public boolean hasLegalMove() {

		return (this.state.getLegalMoves().size() != 0) ? true : false;
	}
    
    public int getChildNumber(){
        
        return this.state.getLegalMoves().size();
    }

	/*
	 * the score is equivalent to the current number of our player
	 * in the current board.
	 */
	public int getScore() {


		double hscore = 0;
		int minscore = 0;
		int maxscore = 0;

		int badscore = 0;
		int movability = 0;

		for (int i = 0; i < this.state.board.length; i++)

			for ( int j = 0; j < this.state.board[i].length; j++) {

				if (this.state.board[i][j] == 1) {


					if (this.layer < 40) {
						if ((i == 1 && j == 0) ||
						        (i == 0 && j == 1) ||
						        (i == 1 && j == 7) ||
						        (i == 0 && j == 6) ||
						        (i == 6 && j == 0) ||
						        (i == 7 && j == 1) ||
						        (i == 6 && j == 7) ||
						        (i == 7 && j == 6))
							badscore = badscore - 4;

						if ((i == 1 && j == 1) ||
						        (i == 6 && j == 1) ||
						        (i == 1 && j == 6) ||
						        (i == 6 && j == 6))
							badscore -= 10;
					}

					maxscore++;
				}
				if (this.state.board[i][j] == -1)
					minscore++;


			}


		if (this.layer < 40)
			movability = this.state.getLegalMoves().size();




		hscore = 100 * ((double)maxscore - (double)minscore) / ((double)maxscore + (double)minscore)
		         + (double)badscore + (double)movability;

		//this.score=hscore;

		return (int)hscore;


//		int score = 0;
//		for (int i = 0; i < this.state.board.length; i++)
//			for (int j = 0; j < this.state.board[i].length; j++)
//				if (this.state.board[i][j] == (int)1)
//					score++;
//		return score;

	}



	/*
	 * By using the canfilp and dofilp method to
	 * update the board.
	 */
	// public void Update(Board state, int X, int Y) {
	// 	int i, j;

	// 	if (X < 0) return; /* pass move */
	// 	if (state[X][Y] != 0) {
	// 		printboard(state, player, turn, X, Y);
	// 		System.out.println("Illegal move");
	// 	}
	// 	state[X][Y] = player;
	// 	for (i = -1; i <= 1; i++)
	// 		for (j = -1; j <= 1; j++)
	// 			if ((i != 0 || j != 0) && CanFlip(state, player, X, Y, i, j))
	// 				DoFlip(state, player, X, Y, i, j);
	// }

	// public void DoFlip(int[][] state, int player, int X, int Y, int dirX, int dirY) {
	// 	while (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == -player) {
	// 		X = X + dirX; Y = Y + dirY;
	// 		state[X][Y] = player;
	// 	}
	// }

	// public boolean CanFlip(int[][] state, int player, int X, int Y, int dirX, int dirY) {
	// 	int capture = false;
	// 	while (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == -player) {
	// 		X = X + dirX; Y = Y + dirY;
	// 		capture = true;
	// 	}
	// 	if (capture == false) return false;
	// 	if (X + dirX < 8 && X + dirX >= 0 && Y + dirY < 8 && Y + dirY >= 0 && state[X + dirX][Y + dirY] == player)
	// 		return true;
	// 	else return false;
	// }




}