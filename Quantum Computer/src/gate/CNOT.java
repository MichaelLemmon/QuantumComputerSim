package gate;
import core.Qubit;
import core.State;

/**
 * The C-NOT gate is a 2-qubit gate.
 * It can be represented as a pair of 1 qubit applications
 * @author Connor Imrie
 *
 */
public class CNOT implements TwoQubitGate{

	@Override
	public State[] actOn(Qubit x, Qubit y) {
		Qubit newY;
		if (x.getQubitType() == 1){
			if (y.getQubitType() == 0){
				newY = new Qubit(1);
			}
			else {
				newY = new Qubit(0);
			}
		}
		else {
			newY = y;
		}
		
		State xState = new State(x);
		State yState = new State(newY);
		State[] returnStates = {xState, yState};
		//returns state of 
		return returnStates;
	}
	
	//Test
	public static void main(String[] args){
		Qubit x = new Qubit(1,0);
		Qubit y = new Qubit(0,1);
		CNOT gate = new CNOT();
		
		State[] states = gate.actOn(x, y);
		for(State state: states){
			System.out.println(state.toString());
		}
	}

	@Override
	public State actOn(Qubit q1, Qubit q2, double phase) {
		new Exception("CNOT can only act on two Qubits. Do not use phase");
		return null;
	}
	
}
