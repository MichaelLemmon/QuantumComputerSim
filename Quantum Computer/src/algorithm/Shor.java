package algorithm;
import core.*;
import gate.Hadamard;

public class Shor {
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
		Register r = new Register(r1.getTensorProduct(r2));

		System.out.println(r.getRowLength());
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
			Matrix newR = projection(out);
			System.out.println(newR);

	}

	public static Matrix projection(Register r) {

		int noOfQubits = r.getLength();
		int noOfStates = r.getRowLength();
		int random = (int)Math.round(Math.sqrt(noOfStates)*Math.random());
		String string = "" + random;
		Register R = new Register(string, true, noOfQubits/2);
		Register RT = new Register(Matrix.getTranspose(R));
		Matrix projection = R.mult(RT);
		Matrix iden = Matrix.identity((int)Math.sqrt(noOfStates));
		//	System.out.println(iden);
		Matrix out = projection.getTensorProduct(iden);

		return out;
	}

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
