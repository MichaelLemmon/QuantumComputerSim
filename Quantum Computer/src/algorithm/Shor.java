package algorithm;
import core.*;
import gate.Hadamard;

/**
 * Shor's Algorithm implementation. Still a work in progress and so comments are left in as a guide to thought-process
 *
 */
public class Shor {
	
	/**
	 * Test method for Shor's algorithm, for chosen 'random' value to be 3 and N = 15
	 * @param args
	 */
	public static void main(String[] args) {
		int m = 3;
		int N = 15;
		int sizeOfRegister = 0;
		for(int i = N*N; i < 2*N*N; i++) {
			for(int j = 0; j < 10; j++) {
				if(Math.pow(2, j) == i){ sizeOfRegister = j; }
			}
		}
		int reg2Size = (int)Math.ceil(Math.log(N) / Math.log(2));
		Register r1 = new Register("0", true, 8);
		Register r2 = new Register("0", true, reg2Size );
		//System.out.println(r2.getLength());
		Hadamard h = new Hadamard();
		r1 = h.actOn(r1);
		
		Matrix finalMatrix = new Matrix(r2.getRowLength(),1);
		for(int i = 0; i < r2.getRowLength(); i++) {
			int s = (int)(Math.pow(m, i)%N);
			String string = "" + s;
			Register newReg = new Register(string,true, r2.getLength());
			//	System.out.println(newReg);
			finalMatrix = finalMatrix.add(newReg);

		}
		//	System.out.println(finalMatrix);


		Register out = new Register(r1.getTensorProduct(finalMatrix));
	//	System.out.println(out.getRowLength());
		out = new Register(actOnRegister(out, N));

			Matrix newR = projection(out, N);
			System.out.println(newR);

	//	System.out.println(out);
	}
	
	/**
	 * Creates a matrix that is the tensor of two registers, and which will take a random orthonormal projection of the first register
	 * leaving the second unaffected.
	 * @param r is the resultant matrix of two registers tensored together
	 * @param N is the number being factorised
	 * @return
	 */
	public static Matrix projection(Register r, int N) {

		int noOfQubits = r.getLength()-(int)Math.ceil(Math.log(N) / Math.log(2));
		int noOfStates = r.getRowLength();
		int random = (int)Math.round(Math.sqrt(noOfStates)*Math.random());
		String string = "" + random;
		Register R = new Register(string, true, noOfQubits);
		Register RT = new Register(Matrix.getTranspose(R));
		Matrix projection = R.mult(RT);
		Matrix I = Matrix.identity((int)Math.pow(2,(int)Math.ceil(Math.log(N) / Math.log(2))));
		//	System.out.println(projection.getRowLength()*I.getRowLength());
		Matrix out = projection.getTensorProduct(I);
		//return out;
		return out.mult(r);

	}
	
	/**
	 * Calculates the hadamard transform of every qubit in the first register, leaving the second register unaffected.
	 * @param r is the resultant matrix of two registers tensored together
	 * @param N is the number being factorised
	 * @return
	 */
	public static Matrix actOnRegister(Register r, int N) {
		// Create a hadamard matrix of (size register length)/2 and and identity of same length
		/*int sizeOfOneRegister = (int)Math.sqrt(r.getLength());

		Hadamard h = new Hadamard();

		Matrix hadamard = h.getMatrix();
		Matrix finalHadamard = new Matrix(hadamard);
		for(int i = 0; i < sizeOfOneRegister; i++) {
		finalHadamard = finalHadamard.getTensorProduct(hadamard);
		}

		Matrix iden = Matrix.identity((int)Math.sqrt(r.getRowLength()));
		Matrix out = finalHadamard.getTensorProduct(iden);
		//System.out.println(finalHadamard);
		System.out.println("HERE");
		return out.mult(r);*/
		
		//create hadamard that is sqrt(superimposed register) in size
		int sizeH = r.getLength()-(int)Math.ceil(Math.log(N) / Math.log(2));
		Hadamard h = new Hadamard();
		Matrix H = h.getMatrix();
		Matrix tempBigH = new Matrix(1);
		tempBigH.setElement(new Complex(1), 0, 0);
		for (int i = 0; i < sizeH; i++){
			tempBigH = tempBigH.getTensorProduct(H);
		}
		H = null;
		Matrix I = Matrix.identity((int)Math.pow(2,(int)Math.ceil(Math.log(N) / Math.log(2))));
		//System.out.println(I);
		tempBigH = tempBigH.getTensorProduct(I);
		I = null;
		/*System.out.println(r.getRowLength() + "      " + r.getColLength());
		System.out.println(tempBigH.getRowLength() + "    " + tempBigH.getColLength());
		System.out.println(I.getRowLength() + "      " + I.getColLength());*/
		return tempBigH.mult(r);
	}
}
