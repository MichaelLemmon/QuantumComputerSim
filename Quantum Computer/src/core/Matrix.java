package core;

/**
 * The matrix class represents a matrix of complex numbers
 */
public class Matrix extends Complex {

	Complex[][] elements;

	/**
	 * Empty constructor for a matrix
	 */
	public Matrix() {

	}

	/**
	 * Constructs a square matrix of the given size
	 * @param a the size of the square matrix
	 */
	public Matrix(int a) {
		this.elements = new Complex[a][a];
	}

	/**
	 * Constructs a matrix of a given size
	 * @param a the number of rows in the matrix
	 * @param b the number of columns in the matrix
	 */
	public Matrix(int a, int b) {
		this.elements = new Complex[a][b];
	}

	/**
	 * Constructs a matrix using another matrix
	 * @param m the input matrix
	 */
	public Matrix(Matrix m) {
		this.elements = m.getElements().clone();
	}

	/**
	 * Constructs a matrix from an array of complex numbers
	 * @param elements the array of complex numbers
	 */
	public Matrix(Complex[][] elements) {
		this.elements = elements;
	}

	/**
	 * Gets the row length of the matrix
	 * @return the length of a row in the matrix
	 */
	public int getRowLength() {
		return this.elements.length;
	}

	/**
	 * Gets the column length of the matrix
	 * @return the length of the column in the matrix
	 */
	public int getColLength() {
		if (this.elements.length > 0) {
			return this.elements[0].length;
		} else {
			return 0;
		}
	}

	/**
	 * Gets a complex number at a specific index in the matrix
	 * @param row the row index
	 * @param col the column index
	 * @return the complex number at the index
	 */
	public Complex getElement(int row, int col) {
		return this.elements[row][col];
	}

	/**
	 * Gets all complex numbers in the matrix
	 * @return the array of complex numbers from the matrix
	 */
	public Complex[][] getElements() {
		return this.elements;
	}

	/**
	 * Sets a complex number inside the matrix
	 * @param val the complex number to be set
	 * @param row the row index
	 * @param col the column index
	 */
	public void setElement(Complex val, int row, int col) {
		this.elements[row][col] = val;
	}

	/**
	 * Sets the elements of the matrix using another array of complex numbers
	 * @param elements the array of complex numbers
	 */
	public void setElements(Complex[][] elements) {
		this.elements = elements;
	}

	/**
	 * Prints the Matrix in row/col format to the console
	 * @param grid the matrix to be printed
	 */
	public static void Print(Matrix grid) {
		for (int r = 0; r < grid.getRowLength(); r++) {
			for (int c = 0; c < grid.getColLength(); c++)
				System.out.print(grid.getElement(r, c) + " ");
			System.out.println();
		}
	}

	/**
	 * Gets the tensor product with another matrix
	 * @param matrix the matrix to take the tensor product with
	 * @return the tensor product of the matrix
	 */
	public Matrix getTensorProduct(Matrix matrix) {
		int rowA = this.getRowLength();
		int colA = this.getColLength();
		int rowB = matrix.getRowLength();
		int colB = matrix.getColLength();
		Matrix out = new Matrix(rowA * rowB, colA * colB);
		for (int i = 0; i < rowA; i++) {
			int rowO = i * rowB;
			for (int j = 0; j < colA; j++) {
				int colO = j * colB;
				Complex aij = this.elements[i][j];
				for (int k = 0; k < rowB; k++) {
					for (int l = 0; l < colB; l++) {
						if (aij != null && matrix.getElement(k, l) != null){
							out.setElement(aij.multiply(matrix.getElement(k, l)),rowO + k, colO + l);
						}
						else {
							out.setElement(null, rowO +k, colO +l);
						}
					}
				}
			}
		}
		return out;

	}

	/**
	 * Creates an Identity matrix of a given size
	 * @param size the size of the matrix to be created
	 * @return the identity matrix
	 */
	public static Matrix identity(int size) {
		Matrix identity = new Matrix(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					identity.setElement(new Complex(1.0, 0.0), i, j);
				} 
			}
		}
		return identity;
	}

	/**
	 * Gets the transpose of a matrix
	 * @param A the matrix to take the transpose of
	 * @return the transposed matrix
	 */
	public static Matrix getTranspose(Matrix A) {
		int column = A.getRowLength();
		int row = A.getColLength();
		Matrix B = new Matrix(row, column);
		for (int i = 0; i < column; i++){
			for (int j = 0; j < row; j++){
				B.setElement(A.getElement(i, j), j, i);
			}
		}
		return B;
	}

	/**
	 * Adds another matrix to the current matrix
	 * @param b the matrix to be added
	 * @return the resulting matrix after addition
	 */
	public Matrix add(Matrix b) {
		int row = this.getRowLength();
		int column = this.getColLength();

		if (row != b.getRowLength() || column != b.getColLength()){
			throw new RuntimeException("Wrong dimensions");
		}
		Matrix c = new Matrix(row, column);
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				if (this.getElement(i, j) != null && b.getElement(i, j) != null){
					c.setElement(this.getElement(i, j).add(b.getElement(i, j)), i,j);
				}
				else if(this.getElement(i, j) != null){
					c.setElement(b.getElement(i, j), i, j);
				}
				else if(b.getElement(i, j) != null){
					c.setElement(b.getElement(i, j), i, j);
				}
				else {
					c.setElement(null, i, j);
				}
			}
		}
		return c;
	}

	/**
	 * Subtracts another matrix from the current matrix
	 * @param b the matrix to be subtracted
	 * @return the resulting matrix after subtraction
	 */
	public Matrix subtract(Matrix b) {
		int row = this.getRowLength();
		int column = this.getColLength();
		if (row != b.getRowLength() || column != b.getColLength()){
			throw new RuntimeException("Wrong dimensions");
		}
		Matrix c = new Matrix(row, column);
		for (int i = 0; i < row; i++){
			for (int j = 0; j < column; j++){
				c.setElement(this.getElement(i, j).subtract(b.getElement(i, j)), i,j);
			}
		}
		return c;
	}

	/**
	 * Multiplies the current matrix with another matrix
	 * @param B the matrix to multiply with
	 * @return the resulting matrix after multiplication
	 */
	public Matrix mult(Matrix B) {
		int rowA = this.getRowLength();
		int columnA = this.getColLength();
		int rowB = B.getRowLength();
		int columnB = B.getColLength();
		if (columnA != rowB){
			throw new RuntimeException("Illegal Matrix dimensions.");
		}
		Matrix C = new Matrix(rowA, columnB);
		for (int i = 0; i < rowA; i++) {
			for (int j = 0; j < columnB; j++) {
				Complex partij = new Complex(0);
				for (int k = 0; k < columnA; k++) {
						if (this.getElement(i, k) !=null){
							partij = partij.add(this.getElement(i, k).multiply(B.getElement(k, j)));
						}
				}
				if (partij.getRealPart() == 0){
					partij = null;
				}
				C.setElement(partij, i, j);
			}
		}
		return C;
	}
	
	/**
	 * Converts the matrix to a string representation
	 * @return the string representation of the matrix
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < this.getRowLength(); i++) {
			for (int j = 0; j < this.getColLength(); j++) {
				if (this.getElement(i, j) != null){
					str += this.getElement(i, j).toString();
				}
				else {
					str += '0';
				}
			}
			str += "\n";
		}

		return str;
	}
	
	/**
	 * Gets the probability of a specific index being the correct one
	 * @param index the index to get the probability of
	 * @return the probability
	 */
	public double getProb(int index) {
		
		double prob = 0;

		Complex up = new Complex(this.getElement(index,0));
		prob = up.normSquared();

		return prob;
	}

	/**
	 * Main class to test matrices
	 * @param args
	 */
	public static void main(String[] args) {		
		Matrix A = new Matrix(2);
		A.setElement(new Complex(1), 0, 0);
		A.setElement(new Complex(1), 1, 1);
		Matrix B = new Matrix(A.getElements());
		A.mult(B);
	}
}