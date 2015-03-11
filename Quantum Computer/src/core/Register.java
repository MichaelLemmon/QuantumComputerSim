package core;
/**The Register class represents a quantum register that contains 'n' qubits 
 * @author Connor Imrie
 * 
 * The register is stored as an array of states. 
 * These quantum qubits are a superposition of states and handle their own behaviour 
 * relating to normalisation etc.
 * 
 * To get a meaningful result out of the register, 
 * each element must be taken by the tensor product of the element to its 'right' 
 * in the array (i.e. counting down the array)
 **/
public class Register extends Matrix {
	
	public Register(Matrix m){
		super(m);
	}
	
	public Register(Qubit[] qubits){
		this.setElements(this.getRegisterFromQubits(qubits).getElements());
	}

	public int getLength() {
		return this.getRowLength();
	}
	
	public Matrix getRegisterFromQubits(Qubit[] qubits){
		//go through each state in the array and take the tensor product with the element to its right:
		int length = qubits.length;
		//start with the last two elements:
		Matrix currentTensorMatrix = qubits[0].getTensorProduct(identity(2));
		//Then loop through the remaining elements and take the tensor product with a matrix
		if (qubits.length > 2){
			for (int i = 2; i < length ; i++){
				currentTensorMatrix = qubits[i].getTensorProduct(currentTensorMatrix);
			}
		}
		return currentTensorMatrix;
	}
	
	/* public Qubit getQubit(int index){
		return this.qubits[index];
	}
	
	public State getState(int index){
		int stateIndex = index/2;
		int offset = index%2;
		return this.getQubit(stateIndex).getState(offset);
	}
	
	public void setQubit(Qubit q, int index){
		this.qubits[index] = q;
	}
	
	public void setQubit(State s, int index) {
		this.qubits[index] = new Qubit(s);
		
	}*/
}
