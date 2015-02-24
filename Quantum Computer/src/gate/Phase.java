package gate;
import core.*;

public class Phase implements Gate{

	@Override
	public State actOn(Qubit q) {
		//check coefficients to each qubit
		if (q.get0().getNorm() > 0){
			return (new State(new Complex(0, 0), new Complex(1,0)));
		}
		else if(q.get1().getNorm() > 0){
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
		State newS = h.actOn(new Qubit(new Complex(0, 0), new Complex(1, 0)));
		System.out.println(newS.get0() + "|0> " + newS.get1() + "|1> ");
	}

}
