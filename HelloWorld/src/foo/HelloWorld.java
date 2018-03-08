package foo;

import foo.one.Bicycle;
import foo.one.Car;
import foo.one.Truck;
import foo.one.Vehicle;

public class HelloWorld extends MainApp {

	public static void main(String[] args) {
		System.out.println("Hi "+args[0]);

		System.out.println("Instantiating vehicle");
		new Vehicle(2);
		System.out.println("Instantiating bicycle");
		new Bicycle(2);
		System.out.println("Instantiating car");
		new Car(2);
		System.out.println("Instantiating truck");
		new Truck(2);
	}

}
