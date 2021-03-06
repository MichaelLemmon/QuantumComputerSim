package core;

/**
 * The state class represents an individual state in the form of a matrix
 */
public class State extends Matrix{

	/**
	 * Constructs a state using a matrix
	 * @param m the matrix used to construct the state
	 */
	public State(Matrix m) {
		if (m.getRowLength() == 2 && m.getColLength() == 1){
			this.setElements(m.getElements());
		}
		new Exception("THATS NOT A QUBIT");
	}
	
	/**
	 * Creates a |0> or |1> state
	 * @param type a number 0 or 1 representing their respective states
	 */
	public State(int type){
		this.elements = new Complex[2][1];
		if (type == 0){
			this.elements[0][0] = new Complex(1);
			this.elements[1][0] = new Complex(0);
		}
		else if (type == 1){
			this.elements[0][0] = new Complex(0);
			this.elements[1][0] = new Complex(1);
		}
	}

	/**
	 * Gets the first complex number of a state
	 * @return the first complex number of a state
	 */
	public Complex get0() {
		return this.elements[0][0];
	}
	
	/**
	 * Gets the second complex number of a state
	 * @return the second complex number of a state
	 */
	public Complex get1() {
		return this.elements[1][0];
	}
	/**
	 * Returns the integer value corresponding to the state, this is either 0 or 1
	 */
	public int getStateType(){
		if (this.getElement(0,0) == new Complex(1)){
			return 0;
		}
		else if (this.getElement(1,0) == new Complex(1)){
			return 1;
		}
		else {
			new Exception("Qubit is not properly formed. Can only have 1 integer component. Q = " + this.toString());
		}
		return 0;
	}
	
	/**
	 * Gets the matrix which represents a state
	 * @return the matrix of the state
	 */
	public Matrix getMatrix() {
		Matrix m = new Matrix(2,1);
		m.setElements(this.getElements());
		return m;
	}
	
	/**
	 * Main method for testing states
	 * @param args
	 */
	public static void main(String[] args){
		State q = new State(0);
		System.out.println(q);
	
	}
	
}
