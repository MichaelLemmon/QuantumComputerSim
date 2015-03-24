package core;

import java.util.Scanner;
import algorithm.Grover;

/**
 * Class to operate Grover's algorithm with console input
 */
public class main {
	
	/**
	 * Takes console input for number of qubits and solution/search index, and then performs Grover's algorithm using these
	 * @param args
	 */
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		int numberOfQubits = 0;
		int searchIndex = 0;
		
		Boolean qubitsSet = false;
		while (!qubitsSet){
			System.out.println("Please input the number of qubits in the register:");
			int in = scanner.nextInt();
			if (in > 0){
				numberOfQubits = in;
				qubitsSet = true;
			} else {
				System.out.println("You have entered an invalid number of qubits");
			}
		}
		
		Qubit[] qubits = new Qubit[numberOfQubits];
		for(int i = 0; i < numberOfQubits; i++){
			qubits[i] = new Qubit(new State(0));
		}
		
		boolean searchIndexSet = false;
		while (!searchIndexSet){
			System.out.println("Please input the search index:");
			int in = scanner.nextInt();
			if (in >= 0 && in < Math.pow(2, numberOfQubits)){
				searchIndex = in;
				searchIndexSet = true;
			} else {
				System.out.println("You have entered an invalid search index");
			}
		}
		scanner.close();
		Register testR = new Register(qubits);
		Grover g = new Grover(testR, searchIndex);
		testR = g.act();
		int mostProbableIndex = 0;
		double probability = 0;
		for (int i = 0; i < Math.pow(2, testR.getLength()); i++){
			double tempProb = testR.getProb(i);
			if(tempProb > probability){
				probability = tempProb;
				mostProbableIndex = i;
			}
		}
		System.out.println("Most probable index found: " + mostProbableIndex + " with probability of: " + probability);
	}
}
