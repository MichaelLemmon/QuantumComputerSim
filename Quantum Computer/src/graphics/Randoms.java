package graphics;

/**
 * Calling a function with no arguments assumes:
 * 		Minimum = 0
 * 		Maximum = 10
 * @author Ross
 *
 */
public class Randoms {
	public static int randomInt(int min, int max) {
		int diff = (max - min);
		return (int) ((Math.random() * diff) + min);
	}
	
	public static int randomInt() {
		return randomInt(0,10);
	}
	
	public static double randomDouble(double min, double max) {
		double diff = (max - min);
		return (Math.random() * diff) + min;
	}
	
	public static double randomDouble() {
		return randomDouble(0,10.0);
	}

	

	public static void assertWithinRange(double min, double max, double value) {
		if (value < min | max > value) {
			System.out.println( ""+ (value < min));
			System.out.println("" +  (value > max));
			new Error("Outwith range");
		}
	}
	
	public static void assertWithinRange(int min, int max, int value) {
		if (value < min | value > max) {
			new Error("Outwith range").printStackTrace();
		}
	}
	
}
