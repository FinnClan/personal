import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KruskalMSTFinn02 {

	PriorityQueue<EdgeFinn02> Q;
	UnionFindFinn02 uf;
	EdgeFinn02 edge;
	private LinkedList<EdgeFinn02> T = null;

	private int[] depth;

	private int numOfNodes;

	public KruskalMSTFinn02(String inFile) throws FileNotFoundException {
		initializeHeap(new File(inFile));
	}// end constructor

	private void initializeHeap(File dataFile) throws FileNotFoundException {

		Q = new PriorityQueue<EdgeFinn02>();

		Scanner sc = new Scanner(dataFile);

		Pattern p = Pattern.compile("c");
		while (sc.findInLine(p) != null) {
			sc.nextLine();
		} // end while

		numOfNodes = sc.nextInt();
		depth = new int[numOfNodes + 1];

		uf = new UnionFindFinn02(depth, numOfNodes);

		for (int v = 1; v <= numOfNodes; v++) {
			uf.makeSet(v);
		} // end for

		while (sc.hasNextLine()) {
			sc.nextLine();
			Q.add(new EdgeFinn02(sc.nextInt(), sc.nextInt(), sc.nextInt(), uf));
		} // end while

//		while (!Q.isEmpty()) {
//			System.out.print(Q.poll() + ", ");
//		} // end while

		sc.close();
		findMST(dataFile);
		
	}// end initializeHeap

	private void findMST(File dataFile) {

		// Step3: Set T to null
		// Step4: While T has fewer than (n-1) edges do
		// Step 4.1: Remove the minimum weight edge, say (u,v), from Q.
		// Step 4.2: If (find(u) != find(v) )
		// step 4.2.1: add (u,v) to T.
		// Step 4.2.2: union(u, v)
		// EndWhile
		// output T.

		T = new LinkedList<EdgeFinn02>();

		while (T.size() < (numOfNodes - 1)) {
			edge = Q.poll();
			if (!edge.areConnected(edge.getV1(), edge.getV2())) {
				T.add(edge);
				uf.union(edge.getV1(), edge.getV2());
			} // end if
		} // end while
		
		System.out.println(T.toString());

	}// end findMST

}// end class
