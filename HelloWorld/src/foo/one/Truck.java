package foo.one;

import java.io.Serializable;

public class Truck extends Vehicle implements HasWindows, Serializable {

	static {
		System.out.println("Static initializer for "+Truck.class.getSimpleName());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 9012219906518491382L;

	public Truck(int nbrWheels) {
		super(nbrWheels);
		System.out.println("Constructor for "+this.getClass().getSimpleName());
	}

	@Override
	public void openWindows() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeWindows() {
		// TODO Auto-generated method stub
		
	}


}
