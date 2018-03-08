import java.io.IOException;

public class MainFinn02 {

	public static void main(String args[]) throws IOException {
		if(args.length != 1) {
			System.out.println("Wrong number of arguments provided!");
			System.exit(1);
		}//end if
		
		new KruskalMSTFinn02(args[0]);
		
	}//end main

}//end class