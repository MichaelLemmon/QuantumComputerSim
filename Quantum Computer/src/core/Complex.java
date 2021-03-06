package core;

public class Complex {

	private double realPart;
	private double imagPart;

	/**
	 * Constructor for a complex number
	 */
	public Complex() {
		this.setComplex(Double.NaN, Double.NaN);
	}

	/**
	 * Constructs a complex number using another complex number
	 * @param original the complex number used to create the new complex number
	 */
	public Complex(Complex original) {
		this.setComplex(original.getRealPart(), original.getImagPart());
	}
	
	/**
	 * Constructs a complex number using only a real part. The imaginary part becomes 0.
	 * @param real the value of the real part of the complex number
	 */
	public Complex(double real) {
		this.setComplex(real,  0.0);
	}
	
	/**
	 * Constructs a complex number using real and imaginary parts
	 * @param real the value of the real part of the complex number
	 * @param imag the value of the imaginary part of the complex number
	 */
	public Complex(double real, double imag) {
		this.setComplex(real, imag);
	}

	/**
	 * Sets the real part of the complex number
	 * @param real the real value of the complex number
	 */
	public void setRealPart(double real) {
		this.realPart = real;
	}

	/**
	 * Sets the imaginary part of the complex number
	 * @param imag the imaginary value of the complex number
	 */
	public void setImagPart(double imag) {
		this.imagPart = imag;
	}
	
	/**
	 * Sets the real and imaginary parts of a complex number
	 * @param real the value of the real part of the complex number
	 * @param imag the value of the imaginary part of the complex number
	 */
	public void setComplex(double real, double imag) {
		this.setRealPart(real);
		this.setImagPart(imag);
	}
	
	/**
	 * Gets the real part of the complex number
	 * @return the value of the real part of the complex number
	 */
	public double getRealPart() {
		return this.realPart;
	}

	/**
	 * Gets the imaginary part of the complex number
	 * @return the value of the imaginary part of the complex number
	 */
	public double getImagPart() {
		return this.imagPart;
	}

	/**
	 * Converts the complex number into a string representation
	 * @return the complex number converted into a string
	 */
	public String toString() {

		double real = this.getRealPart();
		double imag = this.getImagPart();
		
		String before = "(";
		String after = ")";
		String realStr = "";
		String imagStr = "";
		String conjuction = "";
		if (real != 0.0 && imag != 0.0){
			if (imag > 0){
				conjuction = " + ";
			}
			else {
				conjuction = " - ";
			}
		}
		
		
		if (real != 0.0){
			realStr += real;
		}
		if (imag != 0.0){
			imagStr += imag + "i";
		}
		if (real == 0.0 && imag == 0.0){
			realStr += real;
		}
		
		return before + realStr + conjuction + imagStr + after;
	}

	/**
	 * Calculates the square modulus
	 * @return the square modulus
	 */
	public double normSquared() {
		return this.getRealPart()*this.getRealPart() + this.getImagPart()*this.getImagPart();
	}

	/**
	 * Calculates the norm of the complex number
	 * @return the norm of the complex number
	 */
	public double getNorm() {
		return Math.sqrt(this.normSquared());
	}

	/**
	 * Gets the complex conjugate of the complex number
	 * @return the complex conjugate
	 */
	public Complex conj() {
		return new Complex(this.getRealPart(), -this.getImagPart());
	}

	/**
	 * Adds a complex number to the current complex number
	 * @param b the complex number to be added
	 * @return the resulting complex number after addition
	 */
	public Complex add(Complex b) {
		return new Complex(this.getRealPart() + b.getRealPart(), this.getImagPart() + b.getImagPart());
	}

	/**
	 * Subtracts a complex number from the current complex number
	 * @param b the complex number to be subtracted
	 * @return the resulting complex number after subtraction
	 */
	public Complex subtract(Complex b) {
		return new Complex(this.getRealPart() - b.getRealPart(), this.getImagPart() - b.getImagPart());
	}

	/**
	 * Multiplies the current complex number by another complex number
	 * @param b the complex number used in the multiplication
	 * @return the resulting complex number after the multiplication
	 */
	public Complex multiply(Complex b) {
		return new Complex(this.getRealPart() * b.getRealPart() - this.getImagPart()
				* b.getImagPart(), this.getRealPart() * b.getImagPart()
				+ this.getImagPart() * b.getRealPart());
	}

	/*
	public static Complex multiply(Complex a, double b) {

		return new Complex(a.getRealPart() * b, a.getImagPart() * b);

	}
	*/

	/**
	 * Divides the current complex number by another complex number
	 * @param b the complex number to be used in the division
	 * @return the resulting complex number after the division
	 */
	public Complex divideBy(double b) {
		return new Complex(this.getRealPart() / b, this.getImagPart() / b);
	}

}
