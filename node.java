import java.util.ArrayList;

public class node{

	
	public int[][] state;
	public int socre;
    public int layer;

	public node parent;
	public ArrayList<node> childList;

	public byte player;
  
    //for connecting its peers
	public node next;
	public node prev;

	public node(byte player,int[][] state){

		this.player=player;	
		this.state=state;
	}
	/*
	 * take current player and state
	 * to create a new child node
	*/
	public node addChild(node child){
		
		child.parent=this;
        child.layer=parent.layer+1;
		this.childList.add(child);
	}

	public void setParent(node parent){
		
		this.parent=parent;
	}
    public void setLayer(int layer){
        
        this.layer=layer;
    }
    
    public ArrayList<int[][]> getLegalMove(){
        
        //return an array list with legal moves from the current state
        
    }
	

}