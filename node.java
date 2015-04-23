
public class node{

	
	short[][] state;
	int socre;

	node parent;
	List<node> childList;

	byte player; 

	//for connecting its peers
	node next;
	node prev;

	public node(byte player,short[][] state){

		this.player=player;	
		this.state=state;
	}
	/*
	 * take current player and state
	 * to create a new child node
	*/
	public node insertChild(byte player,int[][] state){
		
		node newChild=new node(player,state);
		newChild.parent=this;
		this.childList.add(newChild);
	}

	public void setParent(node parent){
		
		this.parent=parent;
	}
	

}