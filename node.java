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

	public node(byte player,short[][] state){

		this.player=player;	
		this.state=state;
	}
	/*
	 * take current player and state
	 * to create a new child node
	*/
	public node addChild(byte player,int[][] state){
		
		node newChild=new node(player,state);
		newChild.parent=this;
		this.childList.add(newChild);
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