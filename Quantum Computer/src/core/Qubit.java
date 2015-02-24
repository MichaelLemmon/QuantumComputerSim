package core;

// @author Ben Crabbe
public class Qubit{

	private int a, b;

	public Qubit(int a, int b){
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		int a = this.get0();
		int b = this.get1();

		return "(" + a + ")| 0 > + (" + b + ")| 1 >";
	}
	
	public int get0(){
		return this.a;
	}
	
	public int get1(){
		return this.b;
	}

	
}
