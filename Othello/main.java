import java.util.ArrayList;
import java.util.Scanner;

public class main {


	public static final int DEFAULT_DEPTH = 4; //this is the default depth the tree will construct.
	public static int currentDepth = 0; //records the current depth.
    public static int DEFAULT_CHILDNUM=8;

	public static void main(String[] args) {

		
        Scanner scan = new Scanner(System.in);

        String[] command=scan.nextLine().split(" ");
        
        //new game starts
        playGame(command,scan);
        

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
                     1s|   3
                     4s|  if childList num<7, depth=5, else depth=4
                    16s|  if childList num<7, depth=6, if 7<childList num <11, depth=5, else depth=4
                    60s|  if childList num<7, depth=6, if 7<childList num <13, depth=5, else depth=4
                   240s|  if childList num<7, depth=6, if 7<childList num <15, depth=5, else depth=4
    */
        
        build(root, determineDepthByTime(root,timeLimit1));

		currentDepth += 2;
        
        node back = null;
        
        
        if(isLonelyGrandpa(root)){
           
            back=root.childList.get(0);
        
        }else{
           // System.out.println("root.alpha: " + root.alpha + "root.beta: " + root.beta);
        AlphaBeta(root, root.alpha, root.beta);
        
		for (node child : root.childList) {
            
//			System.out.println("root.alpha: " + root.alpha + "\nchild.beta: " + child.beta+
//                               "\nchild premove" +"(" +child.state.prevMove[0] +","+child.state.prevMove[1]+")");
//            
            if (child.beta == root.alpha) {
				//System.out.println("final alpha: " + root.alpha);
				back = child;
				break;
			}
        }
    }

        if(back==null){
            
            //System.out.println("into the null pointer block");
            return new int[] {-1,-1};
       
        }else{
            
        return back.state.prevMove; //return move: [x,y]
            
        }
		

	}

	/*
	 * take a node as root, and build the tree recursively
	 * the depthlimit controls the depth of the tree
	 * this recursive method will construct
	 */

	public static void build(node root, int depthLimit) {

		if ((root.hasLegalMove()) && (root.layer <= depthLimit + currentDepth)) {

            //copy board layout
            int[][] tempShit = new int[8][8];
            
            for (int i = 0; i < root.state.board.length; i++) {
                
                for (int j = 0; j < root.state.board[i].length; j++) {
                    
                    tempShit[i][j] = root.state.board[i][j];
                }
            }

            int breakcount=0;
            
            for(int[] move: root.state.getLegalMoves()){
               
                breakcount++;
                //if this node has too many children
                //use a condom next time.LOL
                if(breakcount>DEFAULT_CHILDNUM)
                    break;
                
                Board temp=new Board(tempShit,-(root.state.turn));
                temp.move(move[0],move[1]);
                node child = new node(temp);
                child.state.prevMove[0] =move[0];
                child.state.prevMove[1] =move[1];
                child.layer = root.layer + 1;
                root.addChild(child);
                build(child, depthLimit);
                
            }
            
			//ArrayList<tuple> childrenBoard = root.getChildBoards();
            
//            for (tuple t : childrenBoard) {
//
//                node child = new node(t.board); //add new board
//				child.state.prevMove[0] = t.x;
//				child.state.prevMove[1] = t.y;
//				child.layer = root.layer + 1;
//				root.addChild(child);
//				build(child, depthLimit);
//			}

		}

		return;

	}
	/*
	 * The alpha and beta pruning mechanism
	 */
	public static int AlphaBeta(node n, int a , int b) {
		//System.out.println(n.score);
            
        
		if (n.childList.size() == 0) {
			 //System.out.println(n.score);
			return n.score;
		}
		
        int childcount = n.childList.size();
        //first-policy move:
        if(n.parent == null){
            for (int i = 0; i < childcount ; i++){
            
                if((n.childList.get(i).state.prevMove[0]== 0 && n.childList.get(i).state.prevMove[1]== 0)||
                   (n.childList.get(i).state.prevMove[0]== 0 && n.childList.get(i).state.prevMove[1]== 7)||
                   (n.childList.get(i).state.prevMove[0]== 7 && n.childList.get(i).state.prevMove[1]== 7)||
                   (n.childList.get(i).state.prevMove[0]== 7 && n.childList.get(i).state.prevMove[1]== 0)
                   )
                {
                    n.alpha = 10000;
                    n.childList.get(i).beta = 10000;
//                    System.out.println("conner :"+n.childList.get(i).state.prevMove[0]+" "+
//                                       n.childList.get(i).state.prevMove[1]);
                    return 1;
                }
                
                
                if(n.childList.get(i).state.prevMove[0]== 0||n.childList.get(i).state.prevMove[0]== 7||
                   n.childList.get(i).state.prevMove[1]== 0||n.childList.get(i).state.prevMove[1]== 7)
                {
//                    System.out.println("edge:"+n.childList.get(i).state.prevMove[0]+" "+
//                                       n.childList.get(i).state.prevMove[1]);
                    n.alpha = 10000;
                    n.childList.get(i).beta = 10000;
                    return 1;
                }
            
            
            
            }
           
        }
        
        
        
        
       // System.out.println("current player: " + n.state.turn);

		if (n.state.turn == 1) {
			
            int childnum = n.childList.size();
			
            for (int i = 0; i < childnum ; i++) { // for each possible move
                
				int value = AlphaBeta(n.childList.get(i),n.alpha,n.beta);

				if (value > n.alpha) {
					n.alpha = value;
				}
                if (n.beta <= a){ // pruning
					
                    //System.out.println("alpha pruning in");
                    
                    //return n.beta;
				    break;
                }
			}
			return n.alpha;

		} else { // for player == -1

			int childnum = n.childList.size();

			for (int i = 0; i < childnum ; i++) {

				int value = AlphaBeta(n.childList.get(i),n.alpha,n.beta);
				if (value < n.beta)
					n.beta = value;

                if (n.beta <= a){ // pruning
					//System.out.println("beta pruning in");
                    //return n.alpha;
				    break;
            }
			}
			return n.beta;
		}


	}
    /*
     * determine the depth of the tree we built by a given time limit.
     */
    public static int determineDepthByTime(node root,double timeLimit1){
        
        
        int depthLimit=DEFAULT_DEPTH;
        
       
        int childnum=root.getChildNumber();
        
        if(timeLimit1==1){
            
            depthLimit=3;
            
        }else if(timeLimit1==4){
            
            depthLimit=(childnum<=6)?5:4;
            
        }else if(timeLimit1==16){
            
            if(childnum<6)
                depthLimit=6;
            else if(childnum>=6&&childnum<=7)
                depthLimit=5;
            else
                depthLimit=4;
            
        }else if(timeLimit1==60){
            
            if(childnum<=6)
                depthLimit=6;
            else if(childnum>=6&&childnum<7)
                depthLimit=5;
            else
                depthLimit=4;
            
            
        }else if(timeLimit1==240){
            
            if(childnum<=6)
                depthLimit=6;
            else if(childnum>=6&&childnum<=7)
                depthLimit=5;
            else
                depthLimit=4;
            
        }
        
        return depthLimit;
        
    }
    /*
     * The mechanism for connecting the game platform
     */
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
                
                if(board.getLegalMoves().size()!=0){
                    
                    int[] decision = getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
                    
                    if(decision[0]==-1&&decision[1]==-1){
                        
                        System.out.println("pass");
                    
                    }else{
                        
                        board.move(decision[0], decision[1]);
                        //the output for informing the opposite player
                        //our move
                        System.out.println(decision[0]+" "+decision[1] + "\n");
                        //board.print();
                    }
                    
                    
                }else{
                    
                    System.out.println("pass");
                }

				//parse the input from te oppoiste player

				String input = scan.nextLine();

				String[] move = input.split(" ");
                
                //int[] OppDecision=getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
                
                
                //System.out.println("opposite player gives "+OppDecision[0] + " " + OppDecision[1] + "\n");
                board.turn=-1;
                if(move[0].equals("pass")){
                    
                    //board remains unchanged
                    
                }else{
                    
                    board.move(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
                    
                }

                //board.move(OppDecision[0],OppDecision[1]);
               // board.print();


			}

		} else if(command[1].equals("W")){

			//play as player -1
			Board board = new Board(); //the initial board
			board.turn = -1;

			while (true) {

				//parse the input from the oppoiste player

				String input = scan.nextLine();

				String[] move = input.split(" ");

                board.turn = -1;
                //opposite player enters pass
               
                if(move[0].equals("pass")){
                   
                   //board remains unchanged
                
                }else{
                    
				board.move(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
                    
                }

				//make decision according to the move that
				//opposite player have made

                board.turn = 1;
                
                if(board.getLegalMoves().size()!=0){
                    
                    int[] decision = getDecision(board, DEPTHLIMIT, TIMELIMIT1, TIMELIMIT2);
                    
                    if(decision[0]==-1&&decision[1]==-1){
                        
                        System.out.println("pass");
                        
                    }else{
                        
                        board.move(decision[0], decision[1]);
                        //the output for informing the opposite player
                        //our move
                        System.out.println(decision[0]+" "+decision[1] + "\n");
                        
                    }
                    
                    
                }else{
                    
                    System.out.println("pass");
                }

            }
        }
    }
    
    /*
     * detect whether the current has grand child or not
     */
    
    public static boolean isLonelyGrandpa(node root){
        
        if(root.childList.size()==1&&root.childList.get(0).childList.size()==0)
            return true;
        else
            return false;
        
        
    }

}