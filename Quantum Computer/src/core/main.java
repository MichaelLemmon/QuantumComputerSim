package core;

import gate.Hadamard;

public class main {
	public static void main(String[] args){
		Register r = new Register(2);
		r.setQubit(new Qubit(new State(0)), 0);
		r.setQubit(new Qubit(new State(1)), 1);
		
		Hadamard h = new Hadamard();
		r.setQubit(h.actOn(r.getQubit(0)), 0);
		
		System.out.println(r.getMatrix().toString());
		System.out.println(r.getQubit(0).prob1());
	}
}
