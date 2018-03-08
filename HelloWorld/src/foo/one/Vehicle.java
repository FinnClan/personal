package foo.one;

public class Vehicle extends Thing {
	private int nbrWheels;
	
	static {
		System.out.println("Static initializer for "+Vehicle.class.getSimpleName());
	}
	
	public Vehicle(int nbrWheels) {
		super();
		this.nbrWheels = nbrWheels;
		System.out.println("Constructor for "+this.getClass().getSimpleName());
	}

	public int getNbrWheels() {
		return nbrWheels;
	}

	public void setNbrWheels(int nbrWheels) {
		this.nbrWheels = nbrWheels;
	}
	
}
