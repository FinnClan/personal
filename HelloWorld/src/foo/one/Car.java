package foo.one;

public  class Car extends Vehicle implements HasWindows {

	static {
		System.out.println("Static initializer for "+Car.class.getSimpleName());
	}
		
	public Car(int nbrWheels) {
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
