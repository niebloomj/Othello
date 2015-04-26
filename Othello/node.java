import java.util.ArrayList;

public class node {
	
    public Board state;
    public int[] move; //int array with size 2 that decides the next move

	public int alpha = Integer.MIN_VALUE; //default alpha
	public int beta = Integer.MAX_VALUE; //default beta

	public int score;
	public int layer;
    public byte player;

	public node parent;
	public ArrayList<node> childList;
    
	public node(byte player,Board state) {
        this.player=player;
        this.state=state;
        getScore();
        
    }
    public node(Board state,int[] move){
        
        this.state=state;
        getScore();

    }
	/*
	 * take current player and state
	 * to create a new child node
	*/
	public void addChild(node child) {
		
        child.parent = this;
		child.layer = parent.layer + 1;
        child.player=-(this.player);
        this.childList.add(child);
	}

	public void setParent(node parent) {
		this.parent = parent;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

    public ArrayList<tuple> getLegalBoard(){
        
        //the tuple contains the new board
        //and the move, which directs to the new board
        ArrayList<tuple> boardTuple=new ArrayList<tuple>();
    
        for(int[] move:this.state.getLegalMoves()){
            
            
            byte[][] temp=new byte[8][8];
            
            for(int i=0;i<this.state.board.length;i++){
                
                for(int j=0;j<this.state.board[i].length;j++){
                    
                    temp[i][j]=this.state.board[i][j];
                }
            }
            
            Update(temp,this.player,move[0],move[1]);
            
            Board b=new Board(temp);
            
            tuple t=new tuple(b,move);
            
            //do the all flip stuffs, and return the most up-to-date board
            
            boardTuple.add(t);
        }

        return boardTuple;
        
    }
    
    public void Update(byte[][] state, byte player, int X, int Y)
    {
        int i,j;
        
        if (X<0) return; /* pass move */
        if (state[X][Y] != 0) {
            printboard(state, player, turn, X, Y);
            System.out.println("Illegal move");
        }
        state[X][Y] = player;
        for (i=-1; i<=1; i++)
            for (j=-1; j<=1; j++)
                if ((i!=0 || j!=0) && CanFlip(state, player, X, Y, i, j))
                    DoFlip(state, player, X, Y, i, j);
    }
 
   public void DoFlip(byte[][] state, byte player, int X, int Y, int dirX, int dirY)
    {
        while (X+dirX < 8 && X+dirX >= 0 && Y+dirY < 8 && Y+dirY >= 0 && state[X+dirX][Y+dirY]==-player) {
            X = X+dirX; Y = Y+dirY;
            state[X][Y] = player;
        }
    }

    public boolean CanFlip(byte[][] state, byte player, int X, int Y, int dirX, int dirY)
    {
        int capture = false;
        while (X+dirX < 8 && X+dirX >= 0 && Y+dirY < 8 && Y+dirY >= 0 && state[X+dirX][Y+dirY]==-player) {
            X = X+dirX; Y = Y+dirY;
            capture = true;
        }
        if (capture == false) return false;
        if (X+dirX < 8 && X+dirX >= 0 && Y+dirY < 8 && Y+dirY >= 0 && state[X+dirX][Y+dirY]==player)
            return true;
        else return false;
    }
    
    public boolean hasLegalMove(){
        
        return (this.state.getLegalMoves().size()!=0)? true:false;
    }
    
    public int getScore(){
        
        int score=0;
        
        for(int i=0;i<this.state.board.length;i++){
            
            for(int j=0;j<this.state.board[i].length;j++){
                
                if(this.state.board[i][j]==(byte)1)
                    score++;
            }
        }
        
        return score;
    }


}