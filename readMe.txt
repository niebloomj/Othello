Project Othello 

ReadMe

Team member:
Xuefeng Peng 		course#:114			Major: CS & Applid Math
Yuntao  Zhou  		course#:113			Major: CS & Math
Jacob   Niebloom	course#:			Major: CS & Business

Abstract:
In this project, we have built an AI System to play Othello with others. The AI takes a current board layout as input and predicts the next certain number of steps ahead to build up a tree structure for later usage;and finally, the AI returns the best move we should take as our next move by using Alpha-Beta pruning algorithm. Noteworthily, the Alpha-Beta pruning process consists of a heuristic function, which decides the score for each node in the tree.

Files:
1. Board.java 
2. Node.java
3. tuple.java
4. main.java

Board.java
Board is the infrastructure of our system. The board records the current board layout by using int[][], the turn, and the move the player takes to reach the current board layout. 
The move function takes the responsibility of checking canFlip, making doFile as well as update.
Moreover, the getLegalMoves method returns an array list of moves (x,y)that we can take. 

Node.java
Node is the fundamental element of the tree structure. Each node contains a board, a layer number, a score, a parent and a child list. The primary functions inside this file is adding child and getting score. The get score function is actually our heuristic function.

Tuple.java
This calss is here for passing data from node class to main class. The data it passes are board and move. 

Main.java
This class is where we handle the standard input and output related problem. Besides, the depth limit and time limit control are effectuated in this class. The playGame acts as an encapsulation function who will detect which player is the first player and behave accordingly.The getDecision function is where the alpha-beta pruning process search in the tree that we built with required depth. In the end, this function returns a move (X,Y) for system print out. 
The build function is for building up the tree; and this tree will ultimately passed into alpha-beta pruning function.
There also is a function for determining the tree's depth limit by the given time limit as well as the root's childlist size. If the size of the child list is too large and the cut-off depth is too deep, then the algorithm will be extremely time-cosuming. Beside, out of memory heap error also may happen. 

More about the AI, heuristic function and Alpha-Beta pruning:









