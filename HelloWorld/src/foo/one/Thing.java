package foo.one;

public abstract class Thing {

	private String color;
	private int length;
	private int height;
	private int weight;
	private int depth;
	private int cost;

	static {
		System.out.println("Static initializer for "+Thing.class.getSimpleName());
	}
	
	public Thing() {
		super();
		System.out.println("Constructor for "+this.getClass().getSimpleName());
	}
	
	

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
