import java.util.ArrayList;

public class node {
	public Board state;

	public int alpha = Integer.MIN_VALUE; //default alpha
	public int beta = Integer.MAX_VALUE; //default beta

	public int score;
	public int layer;

	public node parent;
	public ArrayList<node> childList;

	//for connecting its peers
	// public node next;
	// public node prev;

	public node(byte player, Board state) {
		this.state = state;
	}
	/*
	 * take current player and state
	 * to create a new child node
	*/
	public void addChild(node child) {
		child.parent = this;
		child.layer = parent.layer + 1;
		child.state = state;
		////////////////////////////////////
		//child.state.move() we need to move the state to the new position
		////////////////////////////////////
		this.childList.add(child);
	}

	public void setParent(node parent) {
		this.parent = parent;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public ArrayList<int[]> getLegalMove() {
		return state.getLegalMoves();
	}


}