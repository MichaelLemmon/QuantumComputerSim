package gate;
import core.*;

public class ControlledPhase extends TwoQubitGate {
	
	public ControlledPhase(double phase){
		Matrix m = Matrix.identity(4);
		m.setElement(new Complex(Math.cos(phase), Math.sin(phase)), 3, 3);
		this.matrix = m;
	}
	
	/**
	 * This is a test main method to check that the Controlled Phase Gate gives appropriate state output.
	 * @param args
	 */
	public static void main(String[] args){
		ControlledPhase gate = new ControlledPhase(Math.PI/2);
		
		Qubit[] qubits = {
				new Qubit(new State(1)),
				new Qubit(new State(0))
		};
		Register r = new Register(qubits);
		
		r = gate.actOn(r);
		System.out.println(r);
	}
	

}
