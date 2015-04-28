import java.util.ArrayList;
import java.util.Scanner;

public class main {

<<<<<<< HEAD
    public static final int DEFAULT_DEPTH = 4; //this is the default depth the tree will construct.
    public static int currentDepth = 0;

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        //first scan the game rule
        
        String[] command=scan.nextLine().split(" ");
        
        playGame(command,scan);
        
        
   
//        //???do we neeed this to start a new game or we just need the
//        //tournament's infrastructure to provide us with a either new board
//        //or a new move(x,y) from the oppoiste player?????
//
//        Board board = new Board(); //the initial board
//        
//        //everytime the opposite player will give me
//        //a new board, and according to the new board we make a root,
//        //and bbuild a new tree; then, do the ab pruning and return the
//        //move we want to act to handle the opposite player's action.
//
//        // getDecision(board, DEFAULT_DEPTH, 0, 0);
//        
//        board.board[3][2]=1; board.board[3][3]=1; board.board[3][4]=1;
//        board.board[4][2]=-1;board.board[4][3]=-1;board.board[4][4]=-1; board.board[4][5]=-1;
//        board.board[5][4]=1; board.board[6][1]=1;
//        
//        board.print();
//        
//        for(int [] lm: board.getLegalMoves())
//            System.out.println("("+lm[0]+","+lm[1]+")");
//
//        
=======
	public static final int DEFAULT_DEPTH = 4; //this is the default depth the tree will construct.
	public static int currentDepth = 0;

	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		//???do we neeed this to start a new game or we just need the
		//tournament's infrastructure to provide us with a either new board
		//or a new move(x,y) from the oppoiste player?????

		Board board = new Board(); //the initial board

		//everytime the opposite player will give me
		//a new board, and according to the new board we make a root,
		//and bbuild a new tree; then, do the ab pruning and return the
		//move we want to act to handle the opposite player's action.

		// getDecision(board, DEFAULT_DEPTH, 0, 0);

		board.board[3][2] = 1; board.board[3][3] = 1; board.board[3][4] = 1;
		board.board[4][2] = -1; board.board[4][3] = -1; board.board[4][4] = -1; board.board[4][5] = -1;
		board.board[5][4] = 1; board.board[6][1] = 1;

		board.print();

		for (int [] lm : board.getLegalMoves())
			System.out.println("(" + lm[0] + "," + lm[1] + ")");


>>>>>>> origin/master
//        Scanner scan = new Scanner(System.in);
//
//
//
//        while (true) {
//
//            System.out.println("Give me your x then your y");
//            int x = scan.nextInt();
//            int y = scan.nextInt();
//            board.move(x, y);
//            board.print();
//
//            //Board temp=new Board(board.board,board.turn);
//
//            //everytime the opposite player will give me
//            //a new board, and according to the new board we make a root,
//            //and bbuild a new tree; then, do the ab pruning and return the
//            //move we want to act to handle the opposite player's action.
//            System.out.println("AI Goes");
//
//            int[] decision = getDecision(board, DEFAULT_DEPTH, 0, 0);
//            board.move(decision[0], decision[1]);
//
//            board.print();
//        }


<<<<<<< HEAD
    }

    /*
     * this method returns an int[], which is the move that the current node contains
     */
    public static int[] getDecision(Board state, int depthLimit, int timeLimit1, int timeLimit2) {

        node root = new node(state);
        
        root.layer = currentDepth;
        
        build(root, depthLimit);
        
        currentDepth += 2;
       
        AlphaBeta(root, root.alpha, root.beta);
        
        node back = null;
        
        //System.out.println(root.childList.size());
       
        for (node child : root.childList) {
            
            
            if (child.alpha == root.alpha) {
                
                back = child;
                break;
            }
        }
        
        
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
           
            //System.out.println("the node layer is: "+root.layer);
            
            for (tuple t : childrenBoard) {
                
                node child = new node(t.board); //add new board
                child.state.prevMove[0]=t.x;
                child.state.prevMove[1]=t.y;
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

        if (!n.hasLegalMove())
            return n.score;
        
        if (n.state.turn == 1) {
            int childnum = n.childList.size();
            for (int i = 0; i < childnum ; i++) { // for each possible move

                int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);

                if (value > n.alpha) {
                    n.alpha = value;
                }
                if (beta <= n.alpha) // pruning
                    return n.beta;
            }
            return n.alpha;
        } else { // for player == -1
            
            int childnum = n.childList.size();
            
            for (int i = 0; i < childnum ; i++) {
               
                int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);
                if (value < n.beta)
                    n.beta = value;

                if (beta <= n.alpha) // pruning
                    return n.alpha;

            }
            return n.beta;
        }


    }
    public void playGame(String[] command, Scanner scan){
        
        
        int DEPTHLIMIT;
        double TIMELIMIT1,TIMELIMIT2;
        
        //ignore the first string, which is GAME, in command
        (command[2].equals("0"))? DEPTHLIMIT=DEFAULT_DEPTH:DEPTHLIMIT=Integer.parseInt(command[2]);
        
        TIMELIMIT1=Double.parseDouble(command[3]);
        TIMELIMIT2=Double.parseDouble(command[4]);
        
        if(command[1].equals("B")){
            
            Board board = new Board(); //the initial board
            
            //play as player 1
            
            while(true){
                
             int[] decision = getDecision(board, DEPTHLIMIT,TIMELIMIT1,TIMELIMIT2);
             
             board.move(decision[0], decision[1]);
                
             //the output for informing the opposite player
             //our move
             System.out.println(decision[0]+" "+decision[1]+"\n");
            //parse the input from te oppoiste player
            
             String input=scan.nextLine();
             
             int[] move=input.split(" ");
             
             board.move(move[0],move[1]);
                
            }
       
        }
        else if(command[1].equals("W")){
            
            //play as player -1
            Board board = new Board(); //the initial board
            
            while(true){
                
                //parse the input from the oppoiste player
                
                String input=scan.nextLine();
                
                int[] move=input.split(" ");
                
                board.move(move[0],move[1]);
                
                //make decision according to the move that
                //opposite player have made

                int[] decision = getDecision(board, DEPTHLIMIT,TIMELIMIT1,TIMELIMIT2);
                
                board.move(decision[0], decision[1]);
                
                //the output for informing the opposite player
                //our move
                System.out.println(decision[0]+" "+decision[1]+"\n");
                
                
            }

            
        }
        
        
        
    }
=======
	}

	/*
	 * this method returns an int[], which is the move that the current node contains
	 */
	public static int[] getDecision(Board state, int depthLimit, int timeLimit1, int timeLimit2) {

		node root = new node(state);

		root.layer = currentDepth;

		build(root, depthLimit);

		currentDepth += 2;
		//System.out.println(" before root: "+ root.alpha + " child: " + child.alpha);

		AlphaBeta(root, root.alpha, root.beta);

		node back = null;

		System.out.println(root.childList.size());

		for (node child : root.childList) {

			// System.out.println(" after root: "+ root.alpha + " child: " + child.beta);

			if (child.alpha == root.alpha) {
				//System.out.println("work");
				back = child;
				break;
			}
		}


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

			//System.out.println("the node layer is: "+root.layer);

			for (tuple t : childrenBoard) {

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

		if (!n.hasLegalMove())
			return n.score;

		if (n.state.turn == 1) {
			int childnum = n.childList.size();
			for (int i = 0; i < childnum ; i++) { // for each possible move

				int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);

				if (value > n.alpha) {
					n.alpha = value;
				}
				if (beta <= n.alpha) // pruning
					return n.beta;
			}
			return n.alpha;
		} else { // for player == -1

			int childnum = n.childList.size();

			for (int i = 0; i < childnum ; i++) {

				int value = AlphaBeta(n.childList.get(i), n.alpha, n.beta);
				if (value < n.beta)
					n.beta = value;

				if (beta <= n.alpha) // pruning
					return n.alpha;

			}
			return n.beta;
		}


	}
>>>>>>> origin/master

}