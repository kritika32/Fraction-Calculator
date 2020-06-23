import java.util.*;

public class prot{


		public static void main(String[] args) throws IllegalAccessException {
		Scanner input = new Scanner(System.in);
		// object variable to store the result of the operation
		fraction_ result;
		// call introduction method
		Introduction();

		while (true) {
			// Get operation from the user
			String operation = getOperation(input);
			// Get two fractions from the user
			fraction_ FirstFraction = getFraction(input);
			fraction_ SecondFraction = getFraction(input);

			switch (operation) {
			case "+":
				System.out.println("-----------------------------------------------------");
				result = FirstFraction.add(SecondFraction);
				System.out.println(FirstFraction + " " + operation + " " + SecondFraction + " " + "is" + " " + result);
				System.out.println("-----------------------------------------------------");
				break;
			case "-":
				System.out.println("-----------------------------------------------------");
				result = FirstFraction.subtract(SecondFraction);
				System.out.println(FirstFraction + " " + operation + " " + SecondFraction + " " + "is" + " " + result);
				System.out.println("-----------------------------------------------------");
				break;
			case "*":
				System.out.println("-----------------------------------------------------");
				result = FirstFraction.multiply(SecondFraction);
				System.out.println(FirstFraction + " " + operation + " " + SecondFraction + " " + "is" + " " + result);
				System.out.println("-----------------------------------------------------");
				break;
			case "/":
				System.out.println("-----------------------------------------------------");
				result = FirstFraction.divide(SecondFraction);
				System.out.println(FirstFraction + " " + operation + " " + SecondFraction + " " + "is" + " " + result);
				System.out.println("-----------------------------------------------------");
				break;
			case "=":
				System.out.println("-----------------------------------------------------");
				boolean res = FirstFraction.equals(SecondFraction);
				System.out.println(FirstFraction + " " + operation + " " + SecondFraction + " " + "is" + " " + res);
				System.out.println("-----------------------------------------------------");
				break;
			}
		}

	}

	// method will describes the calculator program and welcomes user
	public static void Introduction() {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("This program is a fraction calculator");
		System.out.println("It will add,subtract , multiply and divide fractions until you type Q to quit.");
		System.out.println("Please enter your fraction in the form a/b, where a and b are integrs.");
		System.out.println("---------------------------------------------------------------------------------");

	}

	// Ask the user to enter in a valid mathematical operation. if the user enters
	// anything expect "+","-".
	public static String getOperation(Scanner input) {
		System.out.print("Please enter an operation (+,-,/,*,= or Q to quit) : ");
		String operation = input.next();

		while (!operation.equals("+") && !operation.equals("-") && !operation.equals("/") && !operation.equals("*")
				&& !operation.equals("=") && !operation.equals("Q")) {
			if (operation.equals("q"))
				System.exit(1);

			System.out.print("Invalid input(+,-,/,*,= or Q to quit) : ");
			operation = input.next();
		}
		return operation;
	}

	// return true if the parameter is in the form "a/b" where a is any int and b is
	// any positive int
	public static boolean ValidFraction(String input) {
		if (input.substring(1).contains("-")) {
			System.out.println("Please check for - " + input.substring(1));
			return false;
		} else if (input.contains("/")) {
			int i = input.indexOf("/");
			String num = input.substring(0, i);
			String den = input.substring(i + 1);
			return isNumber(num) && isNumber(den) && !den.equals(0);
		} else if (!input.contains("/")) {
			if (input.charAt(0) == '-') {
				String num = input.substring(1);
				System.out.println("num in Valid Fraction method " + num);
				return isNumber(num);
			} else {
				return isNumber(input);
			}
		}
		return false;
	}

	// will check that given string is consist of numbers or not
	private static boolean isNumber(String str) {
		boolean check = false;

		for (int i = 0; i < str.length(); i++) {
			check = Character.isDigit(str.charAt(i));
		}
		return check;
	}
	
	// It prompts the user for a string that is a valid fraction.If they enter
	// anything that is not a valid fraction ,it should re-prompt them until it is
	// valid

	public static fraction_ getFraction(Scanner input) throws IllegalAccessException {
		int num;
		int den;
		System.out.print("Enter fraction (a/b) or an integer (a) : ");
		String fraction = input.next();
		while (!ValidFraction(fraction)) {
			System.out.println(
					"Invalid fraction!!! Enter fraction in the form of a/b in which a and b are integers and b is not zero");
			fraction = input.next();
		}
		if (!fraction.contains("/")) {
			if (fraction.substring(0, 1).equals("-")) {
				num = Integer.parseInt(fraction.substring(1)) * -1;
			} else {
				num = Integer.parseInt(fraction);
			}
			den = 1;
		} else {
			int i = fraction.indexOf('/');
			if (fraction.substring(0, 1).equals("-")) {
				num = Integer.parseInt(fraction.substring(1, i)) * -1;
			} else {
				num = Integer.parseInt(fraction.substring(0, i));
			}
			den = Integer.parseInt(fraction.substring(i + 1));
		}

		return new fraction_(num, den);
	}

}

//fraction is an object that holds the information about a fraction (numerator and denominator)

class fraction_ {
	private int numerator;
	private int denominator;

//Constructors

//a two parameter constructor that initializes the numerator and denominator as ints
	public fraction_(int num, int deno) {
		if (deno == 0)
			throw new IllegalArgumentException("0 is not allowed as denominator");
		else if (deno < 0) {// if the user enters a negative denominator bump the negative sign to the
							// numerator
			this.numerator = num * -1;
			this.denominator = deno * -1;
		} else {
			this.numerator = num;
			this.denominator = deno;
		}
	}

	// One parameter constructor that initializes the object equal in value to the
	// integer parameter
	public fraction_(int num) {
		this(num, 1);
	}

	// zero parameter constructor that initializes the object to 0
	public fraction_() throws IllegalAccessException {
		this(0);
	}

	// Methods

	// exposes the value of numerator field to the user
	public int getNumerator() {
		return numerator;
	}

	// exposes the value of denominator field to the user
	public int getDenominator() {
		return denominator;
	}

	// numerator/denominator a string representation of the fraction
	public String toString() {
		return numerator + "/" + denominator;
	}

	// the result of numerator/denominator
	public double toDouble() {
		return (double) numerator / (double) denominator;
	}

	// return a new fraction that is the sum of other and this fractions
	public fraction_ add(fraction_ other) throws IllegalAccessException {
		fraction_ frac = new fraction_(((this.numerator * other.denominator) + (this.denominator * other.numerator)),
				this.denominator * other.denominator);

		frac.tolowestTerms();
		return frac;
	}

	// returns a new fraction that is the difference of the other and this fraction
	public fraction_ subtract(fraction_ other) {
		fraction_ frac = new fraction_(((this.numerator * other.denominator) - (this.denominator * other.numerator)),
				this.denominator * other.denominator);
		frac.tolowestTerms();
		return frac;
	}

	// returns a new fraction that is the product of the other and this fraction
	public fraction_ multiply(fraction_ other) {
		fraction_ frac = new fraction_(this.numerator * other.numerator, this.denominator * other.denominator);
		frac.tolowestTerms();
		return frac;
	}

	// returns a new fraction that is the divide of the other and this fraction
	public fraction_ divide(fraction_ other) {
		if (other.numerator == 0)
			throw new IllegalArgumentException();
		else {
			fraction_ frac = new fraction_(other.denominator*this.numerator, this.denominator *other.numerator);
            frac.tolowestTerms();
			return frac;
		}
	}

	// must take in an "Object" to properly override the object class equals
	// method,but should ultimately check if two fractions are equal
	public boolean equals(Object other) {
		if (other instanceof fraction_) {
			fraction_ frac = (fraction_) other;
			this.tolowestTerms();
			frac.tolowestTerms();

			if ((Math.abs(this.numerator) == Math.abs(frac.numerator))
					&& (Math.abs(this.denominator) == Math.abs(frac.denominator))) {
				return true;
			}
		}
		return false;
	}

	// converts the current fraction to the lowest terms
	public void tolowestTerms() {
		int gcd = gcd(this.numerator, this.denominator);
		this.numerator = this.numerator / gcd;
		this.denominator = this.denominator / gcd;
	}

	// takes in two ints and determine the greatest common divisor of the two ints
	// ,should be a static method
	public static int gcd(int num, int denom) {
		while (num != 0 && denom != 0) {
			int rem = num % denom;
			num = denom;
			denom = rem;
		}
		return num;
	}

}
