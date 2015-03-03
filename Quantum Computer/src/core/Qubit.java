package core;
/**The State class represents a linear superposition of qubits
 * @author Connor Imrie
 * 
 **/
public class Qubit {
	double magnitude;
	Complex a;
	Complex b;
	
	public Qubit(Complex a, Complex b){
		this.a = a;
		this.b = b;
		this.normalise();
	}
	
	public Qubit(State q){
		this.a = new Complex(q.get0(), 0);
		this.b = new Complex(q.get1(), 0);
		this.normalise();
	}
	
	/**
	 * normalise() manipulates the current qubit coefficients and ensures they are normalised.
	 * If the qubits are already normalised then they are not affected by this.
	 */
	public void normalise(){
		this.magnitude = Math.sqrt(a.getNorm()*a.getNorm() + b.getNorm()*b.getNorm());
		this.a = this.a.divideBy(this.magnitude);
		this.b = this.b.divideBy(this.magnitude);
	}
	
	/**
	 * tensorProduct takes the tensor product between this state and another state
	 * i.e. (this) (x) (matrix), where (x) is the tensor product operator
	 * @param matrix
	 * @return
	 */
	public Matrix tensorProduct(Matrix matrix) {
		Matrix me = this.getMatrix();
		return me.getTensorProduct(matrix);
	}

	public Matrix tensorProduct(Qubit qubit) {
		Matrix me = this.getMatrix();
		Matrix stateMatrix = qubit.getMatrix();
		return me.getTensorProduct(stateMatrix);
	}
	
	public Matrix getMatrix() {
		Matrix m = new Matrix(2, 1);
		m.SetElement(this.get0(), 0, 0);
		m.SetElement(this.get1(), 1, 0);
		return m;
	}
	
	public Complex get0(){
		return this.a;
	}
	
	public Complex get1(){
		return this.b;
	}
	
	public double getMagnitude(){
		return this.magnitude;
	}
	
	public String toString() { 
		
		Complex a = this.get0();
		Complex b = this.get1();
		
	return a + "|0> + " + b + "|1>";
		
	}
	
	public State getState(int offset){
		if (offset == 0){
			return new State(0);
		}
		else {
			return new State(1);
		}
	}
		
	
	// Method to calculate the probability of a qubit being in either up or down
	public double prob0() {
	
		double prob = 0;

		Complex up = new Complex(this.get0());
		prob = up.normSquared();

		return prob;
	}
	
	public double prob1() {
		
		double prob = 0;
		
		Complex down = new Complex(this.get1());
		prob = down.normSquared();
		
		return prob;
		
	}
	
}
