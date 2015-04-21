

public class main {
	public static void main(String[] args) {
		System.out.println("Test");
		board b = new board();
		// b.move((byte)1, (byte)1);
		b.print();
		for (int[] sub : b.legalMoves()) {
			for (int subsub : sub)
				System.out.print(subsub + " ");
			System.out.println();
		}
	}
}