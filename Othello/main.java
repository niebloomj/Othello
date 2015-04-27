

public class main {
    
    public final int DEFAULT_DEPTH=4;//this is the default depth the tree will construct.
	
    public static void main(String[] args) {
        
        //???do we neeed this to start a new game or we just need the
        //tournament's infrastructure to provide us with a either new board
        //or a new move(x,y) from the oppoiste player?????
        
        Board board=new Board();//the initial board
        
        
        //everytime the opposite player will give me
        //a new board, and according to the new board we make a root,
        //and bbuild a new tree; then, do the ab pruning and return the
        //move we want to act to handle the opposite player's action.
        
        makeDecision(board,DEFAULT_DEPTH,0,0);
        
	}
    
    /*
     * this method returns an int[], which is the move that the current node contains
     */
    public int[] makeDecision(Board state,int depthLimit,int timeLimit1,int timeLimit2){
        
        
        node root = new node((byte)1,state);
        
        build(root);
        AlphaBeta(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
        
        
        node back=null;
        
        for(node child : root.childList ){
            
            if(child.alpha==root.alpha){
                
                back=child;
                break;
            }
            
        }
        
        return child.move; //return move: [x,y]
        
    }
    
    
    /*
     * take a node as root, and build the tree recursively
     * the depthlimit controls the depth of the tree
     * this recursive method will construct
     */
    
    public static void build(node root,int depthLimit){
        
        
        if((root.hasLegalMove())&&!(root.layer>depthLimit)){
            
            
            ArrayList<tuple> childrenBoard=root.getLegalBoard();
            
            for(tuple t:childrenBoard){
                
                node child=new node(t.board); //add new board
                child.move=t.move; //add move (this move directs to this new board)
                root.addChild(child);
                build(child);
            }
            
        }
        
        return;
        
    }
    /*
     * The alpha and beta pruning mechanism
     */
    public static int AlphaBeta(node n, int alpha, int beta){
        
        if(n.children == null)
            return n.score;
        
        if(state.player == 1){
            int childnum = n.childList.size();
            for(int i = 0; i < childnum ; i++){ // for each possible move
                
                int value = AlphaBeta(n.childList.get(i), alpha, beta);
                
                if(value > alpha){
                    alpha = value;}
                if(beta <= alpha) // pruning
                    return beta;
            }
            return alpha;
            
        }
        else{// for player == -1
            int childnum = n.childList.size();
            for(int i = 0; i < childnum ; i++){
                int value = AlphaBeta(n.childList.get(i),alpha,beta);
                if(value < beta)
                    beta = value;
                
                if(beta <= alpha)// pruning
                    return alpha;
                
            }
            return beta;
        }
        
        
    }

}