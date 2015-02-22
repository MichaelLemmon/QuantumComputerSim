/**The State class represents a linear superposition of qubits
 * @author Connor Imrie
 * 
 **/
public class State {
	double a;
	double b;
	
	public State(double a, double b){
		this.a = a;
		this.b = b;
	}
	
	/**
	 * normalise() manipulates the current qubit coefficients and ensures they are normalised.
	 * If the qubits are already normalised then they are not affected by this.
	 */
	public void normalise(){
		double normFactor = Math.sqrt(this.a*this.a + this.b*this.b);
		this.a = this.a/normFactor;
		this.b = this.b/normFactor;
	}

	public Matrix tensorProduct(Matrix matrix) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matrix getMatrix() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
