
public class tuple {

	Board board;
	int x;
	int y;

	public tuple(Board b, int x, int y) {
        
		this.board = new Board(b.board,b.turn);
        board.prevMove=b.prevMove;
		this.x = x;
		this.y = y;
	}
}