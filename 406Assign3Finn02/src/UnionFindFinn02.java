public class UnionFindFinn02 {

	private int[] parentNode;
	private int[] depths;

	public UnionFindFinn02(int[] depths, int numOfNodes) {
		parentNode = new int[numOfNodes + 1];
		this.depths = depths;
	}// end constructor

	public void makeSet(int vertex) {
		parentNode[vertex] = vertex;
	}// end makeSet

	public int find(int num) {

		while (num != parentNode[num]) {
            parentNode[num] = parentNode[parentNode[num]];
            num = parentNode[num];
        }
        return num;
	}// end find

	public void union(int i, int j) {

		int root1 = find(i);
		int root2 = find(j);

		if (root2 == root1)
			return;

		if (depths[root1] > depths[root2]) {
			parentNode[root2] = root1;
		} // end if
		else if (depths[root2] > depths[root1]) {
			parentNode[root1] = root2;
		} // end else if
		else {
			parentNode[root2] = root1;
			depths[root1]++;
		} // end else
	}// end union

}// end class