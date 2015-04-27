import java.util.ArrayList;
//methods for using in main.class

public class tree {
	int depthLimit;

	public Board makeDecision(Board state, int timeLimit1, int timeLimit2) {
		node root = new node(new Board());
		build(root);
		AlphaBeta(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
		node back = null;
		for (node child : root.childList )
			if (child.alpha == root.alpha) {
				back = child;
				break;
			}
		return back.state;
	}

	public void build(node root) {
		if ((root.hasLegalMove()) && !(root.layer > depthLimit)) {
			ArrayList<tuple> childrenBoard = root.getChildBoards();
			for (tuple t : childrenBoard) {
				node child = new node(t.board); //add new board
				child.state.move(t.x, t.y);//add move (this move directs to this new board)
				root.addChild(child);
				build(child);
			}
		}
		return;
	}


	public int AlphaBeta(node n, int alpha, int beta) {
		if (!n.hasLegalMove())
			return n.score;
		if (n.state.turn == 1) {
			int childnum = n.childList.size();
			for (int i = 0; i < childnum ; i++) { // for each possible move
				int value = AlphaBeta(n.childList.get(i), alpha, beta);
				if (value > alpha)
					alpha = value;
				if (beta <= alpha) // pruning
					return beta;
			}
			return alpha;
		} else { // for player == -1
			int childnum = n.childList.size();
			for (int i = 0; i < childnum ; i++) {
				int value = AlphaBeta(n.childList.get(i), alpha, beta);
				if (value < beta)
					beta = value;
				if (beta <= alpha) // pruning
					return alpha;

			}
			return beta;
		}
	}
}


//		public void buildWholeTree(){
//
//			buildTreeWithDepth(Integer.MAX_VALUE);
//		}
//
//        public node continueBuildTree(node ptr，int depth){
//
//            //if the depth limit has been reached
//            //the ptr will either landed on the first node of the even layer
//            //or the last node of the odd layer
//
//            //check if the first 3 layers 0,1,2 has been constructed
//            if(ptr.layer<=2){
//                System.out.println("please construct the first three layers by using buildTreeWithDepth(depth)");
//            }
//
//            while(ptr.layer < depth){
//
//            if(ptr.layer%2==0)  //build from even layer
//                buildEntireLayerFromLeft(ptr);
//
//
//            if(ptr.layer%2!=0) //build from odd layer
//                buildEntireLayerFromRight(ptr);
//
//
//
//            }
//            return ptr;
//
//        }
//		public node buildTreeWithDepth(int depth){
//
//			/*                      layer
// 					root              0
// 				|     |    |          1
// 			   |||	 |||  |||		  2
//
//			*/
//
//			//create the layer 1 manually
//			produceChildren(root);
//			//create the layer 2 manually
//			for(node n:root.childList){
//				produceChildren(n);
//			}
//
//			//node pointer points to the first node
//			//in the layer of 2
//			node temp=root.childList.get(0).childList.get(0);
//
//
//			while(temp.layer < depth){
//
//
//				if(temp.layer%2==0){//even layer, traverse from left to right
//
//					buildEntireLayerFromLeft(temp);
//					//after building this layer, the pointer
//					//landed on the last node of the current layer
//					temp=temp.childList.get(temp.childList.size());
//
//				}else{ //odd layer, traverse from right to left
//
//					buildEntireLayerFromRight(temp);
//					//after building this layer, the pointer
//					//landed on the first node of the current layer
//					temp=temp.childList.get(0);
//
//				}
//
//
//			}
//
//            return temp;
//
//		}
//
//        // ->->->-> traverse from left to right
//		public node buildEntireLayerFromLeft(node nodePtr){
//
//			while(nodePtr.parent.next!=null){
//
//				nodePtr=nodePtr.parent.next.childList.get(0);
//				buildPartialLayer(nodePtr);
//			}
//            //return the current node pointer for the purpose of
//            //continuing building the tree with depth limit.
//            return nodePtr;
//		}
//
//        //<-<-<-<- traverse from right to left
//        public node buildEntireLayerFromRight(node nodePtr){
//
//            while(nodePtr.parent.prev!=null){
//
//                nodePtr=nodePtr.parent.prev.childList.get(0);
//                buildPartialLayer(nodePtr);
//            }
//            //return the current node pointer for the purpose of
//            //continuing building the tree with depth limit.
//            return nodePtr;
//        }
//
//		public void buildPartialLayerFromRight(node nodePtr){
//
//
//			while(nodePtr!=null){
//
//				produceChildren(nodePtr);
//
//				//keep the pointer not to point to null
//				if(nodePtr.prev==null){
//					break;
//				}
//				else{
//					nodePtr=nodePtr.prev;
//				}
//			}
//
//		}
//
//		public void buildPartialLayerFromLeft(node nodePtr){
//
//
//			while(nodePtr!=null){
//
//				produceChildren(nodePtr);
//
//				//keep the pointer not to point to null
//				if(nodePtr.next==null){
//					break;
//				}
//				else{
//					nodePtr=nodePtr.next;
//				}
//			}
//
//		}
//		public void produceChildren(node currentNode){
//
//		ArrayList<int[][]> legalState=currentNode.getLegalMove();
//		ArrayList<node> children=new ArrayList<node>();
//        byte player=(currentNode.player==1)? 0:1;
//
//		for(int[][] ls: legalState){
//
//			node child=new node(player,ls);
//			currentNode.addChild(child);
//			children.add(child);
//
//			}
//
//		//connecting peers
//		for(int i=0;i<children.size()-1;i++){
//
//			children.get(i).next=children.get(i+1);
//			children.get(i+1).prev=children.get(i);
//		}
//
//		}