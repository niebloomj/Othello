import java.util.ArrayList;
import java.util.Scanner;

public class main {


	public static final int DEFAULT_DEPTH = 4; //this is the default depth the tree will construct.
	public static int currentDepth = 0;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		Board board = new Board(); //the initial board
        
        String[] command=scan.nextLine().split(" ");
        
        playGame(command,scan);
        
//
//        //first scan the game rule
//
//
//
//        playGame(command,scan);

//		board.print();
//
//		String line;
//		int[] decision;
//		System.out.println("Ready?");
//		line = scan.nextLine();
		//while (true) {

			// System.out.println("Give me your x then your y");
			// int x = scan.nextInt();
			// int y = scan.nextInt();
			// board.move(x, y);
			// board.print();

//			decision = getDecision(board, DEFAULT_DEPTH, 0, 0);
//			board.move(decision[0], decision[1]);
//			board.print();
//			System.out.println("Next?");
//			line = scan.nextLine();

			//Board temp=new Board(board.board,board.turn);

			//everytime the opposite player will give me
			//a new board, and according to the new board we make a root,
			//and bbuild a new tree; then, do the ab pruning and return the
			//move we want to act to handle the opposite player's action.
			// System.out.println("AI Goes");
//
//
//                int[] decision = getDecision(board, DEFAULT_DEPTH, 0, 0);
//                
//
//                decision = getDecision(board, DEFAULT_DEPTH, 0, 0);
//
//                board.move(decision[0], decision[1]);
//                board.print();
//                System.out.println("Next?");
//                line = scan.nextLine();
		




	}


	/*
	 * this method returns an int[], which is the move that the current node contains
	 */
	public static int[] getDecision(Board state, int depthLimit, double timeLimit1, double timeLimit2) {

		node root = new node(state);

        //root.state.turn = 1;
        
		root.layer = currentDepth;
        
        /*    time     |   depthLimit
          -------------------------------
                    1s |   3
                    4s |  if childList num<7, depth=5, else depth=4
               16s     |  if childList num<7, depth=6, if 7<childList num <11, depth=5, else depth=4
            60s        |  if childList num<7, depth=6, if 7<childList num <13, depth=5, else depth=4
            240s       |  if childList num<7, depth=6, if 7<childList num <15, depth=5, else depth=4
         
         //!!!! do more research on 240s
         
         */
        
        build(root, determineDepthByTime(root,timeLimit1));

		currentDepth += 2;

		AlphaBeta(root, root.alpha, root.beta);
		
        System.out.println(root.alpha + ", " + root.beta);

		node back = null;

		//System.out.println(root.childList.size());

		for (node child : root.childList) {

			System.out.println("root.alpha: " + root.alpha + "\nchild.beta: " + child.beta);

			if (child.beta == root.alpha) {
				System.out.println("in");
				back = child;
				break;
			}
		}

        int count=0;
        for (int [] lm : state.getLegalMoves()){
			System.out.println("(" + lm[0] + "," + lm[1] + ")");
            count++;
        }
        System.out.println("got child "+count);

		return back.state.prevMove; //return move: [x,y]

	}

	/*
	 * take a node as root, and build the tree recursively
	 * the depthlimit controls the depth of the tree
	 * this recursive method will construct
	 */

	public static void build(node root, int depthLimit) {

		if ((root.hasLegalMove()) && (root.layer <= depthLimit + currentDepth)) {


			ArrayList<tuple> childrenBoard = root.getChildBoards();

			//System.out.println("the node's player is: "+root.state.turn);

			for (tuple t : childrenBoard) {

				//System.out.println("in main class the tuple's turn is "+t.board.turn);

				node child = new node(t.board); //add new board
				child.state.prevMove[0] = t.x;
				child.state.prevMove[1] = t.y;
				child.layer = root.layer + 1;
				root.addChild(child);

				build(child, depthLimit);
			}

		}

		return;

	}
	/*
	 * The alpha and beta pruning mechanism
	 */
	public static int AlphaBeta(node n, int alpha, int beta) {
		//System.out.println(n.score);
            
        
		if (n.childList.size() == 0) {
			// System.out.println(n.score);
			return n.score;
		}
		System.out.println("current player: " + n.state.turn);

		if (n.state.turn == 1) {
			int childnum = n.childList.size();
			for (int i = 0; i < childnum ; i++) { // for each possible move

				int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);

				if (value > n.alpha) {
					n.alpha = value;
				}
                if (n.beta <= n.alpha){ // pruning
					System.out.println("in");
                    return n.beta;
				//break;
                }
			}
			return n.alpha;

		} else { // for player == -1

			int childnum = n.childList.size();

			for (int i = 0; i < childnum ; i++) {

				int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);
				if (value < n.beta)
					n.beta = value;

                if (n.beta <= n.alpha){ // pruning
					System.out.println("also in");
                    return n.alpha;
				//break;
            }
			}
			return n.beta;
		}


	}

    public static int determineDepthByTime(node root,double timeLimit1){
        
        int depthLimit=DEFAULT_DEPTH;
        
        int childnum=root.getChildNumber();
        
        if(timeLimit1==1){
            
            depthLimit=3;
            
        }else if(timeLimit1==4){
            
            depthLimit=(childnum<=7)?5:4;
            
        }else if(timeLimit1==16){
            
            if(childnum<7)
                depthLimit=6;
            else if(childnum>=7&&childnum<11)
                depthLimit=5;
            else
                depthLimit=4;
            
        }else if(timeLimit1==60){
            
            if(childnum<7)
                depthLimit=6;
            else if(childnum>=7&&childnum<13)
                depthLimit=5;
            else
                depthLimit=4;
            
            
        }else if(timeLimit1==240){
            
            if(childnum<7)
                depthLimit=6;
            else if(childnum>=7&&childnum<15)
                depthLimit=5;
            else
                depthLimit=4;
            
        }
        
        return depthLimit;
        
    }
	public static void playGame(String[] command, Scanner scan) {


		int DEPTHLIMIT;
		double TIMELIMIT1, TIMELIMIT2;

		//ignore the first string, which is GAME, in command
		DEPTHLIMIT = (command[2].equals("0")) ? DEFAULT_DEPTH : Integer.parseInt(command[2]);

		TIMELIMIT1 = Double.parseDouble(command[3]);
		TIMELIMIT2 = Double.parseDouble(command[4]);

		if (command[1].equals("B")) {

			Board board = new Board(); //the initial board
            board.turn = 1;
			//play as player 1

			while (true) {
                
                board.turn=1;
				int[] decision = getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
                board.move(decision[0], decision[1]);
                board.print();
				//the output for informing the opposite player
				//our move
				System.out.println(decision[0]+" "+decision[1] + "\n");
				//parse the input from te oppoiste player

				String input = scan.nextLine();

				String[] move = input.split(" ");
                
                //int[] OppDecision=getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
                
                
                //System.out.println("opposite player gives "+OppDecision[0] + " " + OppDecision[1] + "\n");
                board.turn=-1;
				board.move(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
               //board.move(OppDecision[0],OppDecision[1]);
                board.print();


			}

		} else if (command[1].equals("W")) {

			//play as player -1
			Board board = new Board(); //the initial board
			board.turn = -1;

			while (true) {

				//parse the input from the oppoiste player

				String input = scan.nextLine();

				String[] move = input.split(" ");

                board.turn = -1;
				board.move(Integer.parseInt(move[0]), Integer.parseInt(move[1]));

				//make decision according to the move that
				//opposite player have made

                board.turn = 1;
				int[] decision = getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
				board.move(decision[0], decision[1]);

				//the output for informing the opposite player
				//our move
				System.out.println(decision[0] + " " + decision[1] + "\n");

            }

		}



	}


}