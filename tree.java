
	public class Tree{

		private node root;
		//public int depth;

		public Tree(node root){
			
			this.root=root;
		}
		
		public void buildWholeTree(){

			buildTreeWithDepth(Integer.MAX_VALUE);
		}
		
		public void buildTreeWithDepth(int depth){

			/*                      layer
 					root              0
 				|     |    |          1
 			   |||	 |||  |||		  2

			*/

			//create the layer 1 manually
			produceChildren(root);
			//create the layer 2 manually
			for(node n:root.childList){
				produceChildren(n);
			}
			
			//node pointer points to the first node
			//in the layer of 2
			node temp=root.childList.get(0).childList.get(0);
			int count=0;

			while(count <= depth-2){

				
				if(temp.layer%2==0){//even layer, traverse from left to right

					buildEntireLayerFromLeft(temp);
					//after building this layer, the pointer
					//landed on the last node of the current layer
					temp=temp.childList.get(temp.childList.size);

				}else{ //odd layer, traverse from right to left

					buildEntireLayerFromRight(temp);
					//after building this layer, the pointer
					//landed on the first node of the current layer
					temp=temp.childList.get(0);

				}
				
				count++;
			}


		}
		
		public void buildEntireLayerFromLeft(node nodePtr){
			
			while(nodePtr.parent.prev!=null){

				nodePtr=nodePtr.parent.prev.childList.get(0);
				buildPartialLayer(nodePtr);
			}
		}
		
		public void buildPartialLayerFromRight(node nodePtr){


			while(nodePtr!=null){
				
				produceChildren(nodePtr);

				//keep the pointer not to point to null
				if(nodePtr.prev==null){
					break;
				}
				else{
					nodePtr=nodePtr.prev;
				}
			}
			// //back to parent layer
			// while(nodePtr.parent.next!=null){
			// 	nodePtr=nodePtr.parent.next;
			// }

		}
				public void buildEntireLayerFromRight(node nodePtr){
			
			while(nodePtr.parent.next!=null){

				nodePtr=nodePtr.parent.next.childList.get(0);
				buildPartialLayer(nodePtr);
			}
		}
		
		public void buildPartialLayerFromLeft(node nodePtr){


			while(nodePtr!=null){
				
				produceChildren(nodePtr);

				//keep the pointer not to point to null
				if(nodePtr.next==null){
					break;
				}
				else{
					nodePtr=nodePtr.next;
				}
			}
			// //back to parent layer
			// while(nodePtr.parent.next!=null){
			// 	nodePtr=nodePtr.parent.next;
			// }

		}
		public void produceChildren(node currentNode){

		ArrayList<int[][]> legalState=currentNode.getLegalMove();
		ArrayList<node> children=new ArrayList<node>();
		byte player=(currentNode.player==1)? 0:1;


		for(int[][] ls: legalState){

			node child=new node(player,ls);
			//set the layer of the child
			child.setLayer(currentNode.layer+1);
            child.setParent(currentNode);
			currentNode.addChild(child);
			children.add(child);
			
			}

		//connecting peers
		for(int i=0;i<children.size()-1;i++){
			
			children.get(i).next=children.get(i+1);
			children.get(i+1).prev=children.get(i);
		}

		}

		// //build entire tree
		// public void build(Node currentNode){

		// 		//stop recursion when no legal move 
		// 		//has been found. or node's layer exceeds the depth 
		// 		if(currentNode.state.getLegalMove().size()==0||currentNode.getLayer()>depth){
		// 			return;
		// 		}

		// 		for(int[][] state:currentNode.state.getLegalMove()){
					
		// 			byte player=(currentNode.player==1)? 0:1;
		// 			Node child=new Node(state);
		// 			child.setLayer();
		// 			child.setParent(currentNode);
		// 			currentNode.addChild(child);
					
		// 			build(child);
		// 		}
		// }




	}

		