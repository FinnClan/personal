package foo.one;

public class Bicycle extends Vehicle {

	static {
		System.out.println("Static initializer for "+Bicycle.class.getSimpleName());
	}

	
	public Bicycle(int nbrWheels) {
		super(nbrWheels);
		System.out.println("Constructor for "+this.getClass().getSimpleName());
		// TODO Auto-generated constructor stub
	}

}
