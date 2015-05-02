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

		this.state = s;
		this.layer = 0;

		this.score = getScore();

	}

	/*
	 * take current player and state
	 * to create a new child node
	*/
	public void addChild(node child) {
        
		
		child.parent = this;
        this.childList.add(child);
        
	}

	public void setParent(node parent) {
		this.parent = parent;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

//	public ArrayList<tuple> getChildBoards() {
//
//		//the tuple contains the new board
//		//and the move, which directs to the new board
//		ArrayList<tuple> boardTuple = new ArrayList<tuple>();
//        
//        int breakcount=0;
//
//        for (int[] move : this.state.getLegalMoves()) {
//            
//            breakcount++;
//            
//            if(breakcount>=8){
//                
//                break;
//            }
//            
//			int[][] tempShit = new int[8][8];
//
//			for (int i = 0; i < state.board.length; i++) {
//				
//                for (int j = 0; j < state.board[i].length; j++) {
//
//					tempShit[i][j] = state.board[i][j];
//				}
//			}
//
//			//System.out.println("the parent board's turn is "+this.state.turn);
//
//            //System.out.println("current player: " +this.state.turn);
//
//            //System.out.println("the current board's turn is "+this.state.turn);
//			Board tempBoard = new Board(tempShit, -(this.state.turn));
//			//System.out.println("the child board's turn is "+tempBoard.turn);
//
//			tempBoard.move(move[0], move[1]);
//			//System.out.println("move!");
//			tuple t = new tuple(tempBoard, move[0], move[1]);
//			//System.out.println("the original tuple turn is "+t.board.turn);
//			//state.print();
//			//do the all flip stuffs, and return the most up-to-date board
//
//			boardTuple.add(t);
//
//		}
//
//		return boardTuple;
//	}

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
        
        int nicescore = 0;
		int badscore = 0;
		int movability = 0;

		for (int i = 0; i < this.state.board.length; i++)

			for ( int j = 0; j < this.state.board[i].length; j++) {

				if (this.state.board[i][j] == 1) {
					if (this.layer < 45) {
                        
						if ((i == 1 && j == 0) ||
						        (i == 0 && j == 1) ||
						        (i == 1 && j == 7) ||
						        (i == 0 && j == 6) ||
						        (i == 6 && j == 0) ||
						        (i == 7 && j == 1) ||
						        (i == 6 && j == 7) ||
                            (i == 7 && j == 6)){
							badscore = badscore - 13;
                        }
						if ((i == 1 && j == 1) ||
						        (i == 6 && j == 1) ||
						        (i == 1 && j == 6) ||
                            (i == 6 && j == 6)){
							badscore -= 21;
                        }
                    }
                        if ((i == 0 && j == 0) ||
                            (i == 0 && j == 7) ||
                            (i == 7 && j == 7) ||
                            (i == 7 && j == 0)){
                            nicescore = nicescore + 1097;
                        }
                        
                        if(i == 0 || i == 7 || j == 0 || j ==7)
                            nicescore = nicescore + 27;
                            //System.out.println("nice score edge");

					maxscore = maxscore + 3;
                    
                }
                
                
                //*********************************
                if (this.state.board[i][j] == -1){
					minscore= minscore + 3;
                 
                    if (this.layer < 45) {
                        
                        if ((i == 1 && j == 0) ||
                            (i == 0 && j == 1) ||
                            (i == 1 && j == 7) ||
                            (i == 0 && j == 6) ||
                            (i == 6 && j == 0) ||
                            (i == 7 && j == 1) ||
                            (i == 6 && j == 7) ||
                            (i == 7 && j == 6)){
                            badscore = badscore + 7;
                        }
                        if ((i == 1 && j == 1) ||
                            (i == 6 && j == 1) ||
                            (i == 1 && j == 6) ||
                            (i == 6 && j == 6)){
                            badscore += 11;
                        }
                    }
                    if ((i == 0 && j == 0) ||
                        (i == 0 && j == 7) ||
                        (i == 7 && j == 7) ||
                        (i == 7 && j == 0)){
                        nicescore = nicescore - 197;
                    }
                    
                    if(i == 0 || i == 7 || j == 0 || j ==7)
                        nicescore = nicescore - 27;
                    
                }
                //**********************************
                

			}// end of for loop


		if (this.layer < 40)
			movability = this.state.getLegalMoves().size();




		hscore = 100 * ((double)maxscore - (double)minscore) / ((double)maxscore + (double)minscore)
		         + (double)badscore + (double)movability + (double)nicescore;

		

		return (int)hscore;




	}








}