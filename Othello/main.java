import java.util.ArrayList;
import java.util.Scanner;

public class main {

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

        
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            
            System.out.println("Give me your x then your y");
            int x = scan.nextInt();
            int y = scan.nextInt();
            board.move(x, y);
            board.print();
            
            //Board temp=new Board(board.board,board.turn);
            
            //everytime the opposite player will give me
            //a new board, and according to the new board we make a root,
            //and bbuild a new tree; then, do the ab pruning and return the
            //move we want to act to handle the opposite player's action.
            System.out.println("AI Goes");
            
            int[] decision = getDecision(board, DEFAULT_DEPTH, 0, 0);
            board.move(decision[0], decision[1]);
            
            board.print();
        }


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
        
        for(int[] lm:state.getLegalMoves())
        System.out.println("("+lm[0]+","+lm[1]+")");
        
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

}