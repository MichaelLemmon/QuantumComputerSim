package gate;
import core.*;

public class Hadamard implements OneQubitGate{

	@Override
	public State actOn(Qubit q) {
		//check coefficients to each qubit
		if (q.getQubitType() == 0){
			return (new State(new Complex(1, 0), new Complex(1,0)));
		}
		else if(q.getQubitType() == 1){
			return (new State(new Complex(1,0), new Complex(-1, 0)));
		}
		return null;
	}
	
	/**
	 * This is a test main method to check that hadamard gives appropriate state output.
	 * @param args
	 */
	public static void main(String[] args){
		Hadamard h = new Hadamard();
		State newS = h.actOn(new Qubit(0, 1));
		State S = new State(new Complex(1/newS.getMagnitude(),0),new Complex(1/newS.getMagnitude(),0));
		System.out.println((1/newS.getMagnitude()) + "[ " + newS.get0() + "|0> +  " + newS.get1() + "|1> ]");
		System.out.println(S.prob1());
	}

	@Override
	public State actOn(Qubit q, double parameter) {
		return actOn(q);
	}
}
