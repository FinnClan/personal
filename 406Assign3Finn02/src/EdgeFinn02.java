
public class EdgeFinn02 implements Comparable<EdgeFinn02> {

	int weight, v1, v2;
	UnionFindFinn02 uf;
	int[] edge = new int[3];

	public EdgeFinn02(int v1, int v2, int weight, UnionFindFinn02 uf) {

		edge[0] = v1;
		edge[1] = v2;
		edge[2] = weight;
		
		this.uf = uf;
		
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;

	}// end constructor

	public int compareTo(EdgeFinn02 anEdge) {
		if (this.getWeight() < anEdge.getWeight()) {
			return -1;
		} // end if
		else if (this.getWeight() == anEdge.getWeight()) {
			return 0;
		} // end else if
		else {
			return 1;
		} // end else

	}// end compareTo
	
	public boolean areConnected(int i, int j) {
		return uf.find(i) == uf.find(j);
	}//end areConnected

	public int getV1() {
		return v1;
	}//end getV1
	
	public int getV2() {
		return v2;
	}//end getV1
	
	public int getWeight() {
		return weight;
	}// end getWeight
	
	public String toString() {
		return getV1() + " " + getV2() + " " + getWeight();
	}//end toString

}//end class
