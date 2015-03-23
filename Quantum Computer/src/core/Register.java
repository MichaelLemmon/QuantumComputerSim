package core;

/**
 * The Register class represents a quantum register that contains 'n' qubits
 * 
 * @author Connor Imrie
 * 
 *         The register is stored as an array of states. These quantum qubits
 *         are a superposition of states and handle their own behaviour relating
 *         to normalisation etc.
 * 
 *         To get a meaningful result out of the register, each element must be
 *         taken by the tensor product of the element to its 'right' in the
 *         array (i.e. counting down the array)
 **/
public class Register extends Matrix {

	/**
	 * Constructor for a register using another matrix
	 * 
	 * @param m
	 *            the matrix used to construct the register
	 */
	public Register(Matrix m) {
		super(m);
	}

	/**
	 * Constructor for a register using an array of Qubits
	 * 
	 * @param qubits
	 *            the array of Qubits used to construct the register
	 */
	public Register(Qubit[] qubits) {
		int length = qubits.length;
		Matrix currentTensorMatrix = new Matrix(1);
		currentTensorMatrix.setElement(new Complex(1), 0, 0);
		for (int i = 0; i < length; i++) {
			currentTensorMatrix = currentTensorMatrix
					.getTensorProduct(qubits[i]);
		}
		this.elements = currentTensorMatrix.getElements();
	}

	public Register(String reg, boolean decimal, int size) {
		String binary = "";
		if(decimal){
			int intReg = Integer.parseInt(reg);
			binary = Integer.toBinaryString(intReg);
			int loopSize = size-binary.length();
			for (int i = 0; i < loopSize; i++){
				binary = "0" + binary;
			}
		}
		else {
			binary = reg;
		}
		
		Qubit[] qubits = new Qubit[binary.length()];
		for (int i = 0; i < binary.length(); i++){
			qubits[i] = new Qubit(new State(Character.getNumericValue(binary.charAt(i))));
		}
		this.elements = new Register(qubits).getElements();
	}

	/**
	 * Gets the length of a register
	 * 
	 * @return the length of a register
	 */
	public int getLength() {
		return (int) (Math.log(this.getRowLength()) / Math.log(2));
	}
	
	public static void main(String[] args){
		Register r = new Register("0100", false, 4);
		System.out.println(r.toString());
	}

}
