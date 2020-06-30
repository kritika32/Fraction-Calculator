import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FracCalc {

	public static void main(String[] args) {
		Fraction f = new Fraction();
		f.Start();
	  }
}

class Fraction {
	private int numer;
	private int deno;
	static Scanner scn = new Scanner(System.in);

	private static class indent {

		static int leftPadding = 3;
		static int topPadding = 2;

		public static void AlignSlowmo(int l, String messg) {
			AlignSlowmo(l, leftPadding, messg);

		}

		public static void AlignSlowmo(int l, int t, String messg) {
			align(l, t, "");
			createDelay(messg);

		}

		public static void eachLine(int temp_sep, String messg) {
			createDelay(messg);
			printChar(temp_sep, " ");

		}

		public static void createDelay(String messg) {
			for (char ch : messg.toCharArray()) {
				System.out.print(ch);
				delay(60);
			}
			delay(70);
		}

		public static void delay(int val) {
			try {
				TimeUnit.MILLISECONDS.sleep(val);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		public static void printChar(int val, String ch) {

			for (int i = 0; i < val; i++) {
				System.out.print(ch);
			}
		}

		public static void align(int t, String messg) {

			align(t, leftPadding, messg);
		}

		public static void separator(int val, int t) {
			align(0, t, "");
			for (int i = 0; i < val; i++) {
				System.out.print("-");
			}
			System.out.println();

		}

		public static void align(int l, int t, String messg) {

			for (int i = 0; i < l; i++) {
				System.out.println();
			}

			for (int i = 0; i < t; i++) {
				System.out.print("\t");
			}

			System.out.print(messg);

		}

		public static void separator(int val) {
			System.out.println();
			separator(val, 0);

		}

		public static void alignPrint(int t, int l, String messg) {
			align(t, l, "");
			System.out.print(messg);
		}

		public static void alignPrint(int t, String messg) {
			align(t, "");
			System.out.print(messg);
		}

		public static void printBox(int sep, int row, String messg) {
			separator(52, 3);
			for (int i = 0; i < row; i++) {
				int temp_sep = sep;
				alignPrint(0, "|");
				if (i == row / 2) {
					printChar((int) Math.ceil(sep - messg.length()) / 2, " ");
					temp_sep = (int) Math.ceil((sep - messg.length()) / 2);
				}
				eachLine(temp_sep, i == row / 2 ? messg : "");

				if (row / 2 == i && messg.length() % 2 == 1)
					System.out.print(" ");

				System.out.println("|");
			}
			separator(52, 3);

		}

		public static char PrintCalc() {
			separator(100);
			printBox(50, 3, "Hey! I am your personal Bot ¯\\(◉‿◉)/¯");
			printBox(50, 9, "I am Trained to work on Fractions!!! ");
			separator(100);

			indent.AlignSlowmo(1, "Wanna give it a Try ᕙ(͡°‿ ͡°)ᕗ (y/n) ??? ");
			char ch = scn.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {
				indent.AlignSlowmo(1, "  Let's onn My Calculator Mode! ");
				separator(100);
				AlignSlowmo(1, "Status: Loading");
				delay(1000);
				createDelay(".");
				delay(1000);
				createDelay(".");
				delay(1000);
				createDelay(".");
				AlignSlowmo(2, "Status: Completed!!!");
				separator(100);
			} else {
				separator(100);
				indent.AlignSlowmo(1, "I Have Failed you My Master ୧(﹒︠ᴗ﹒︡)୨ .... Bye!!!");
				separator(100);
			}
			return ch;

		}

	}

	public Fraction(int n, int d) {
		if (d == 0) {
			indent.AlignSlowmo(2, "InvalidDenominatorException Raised");
		}

		this.numer = n;
		this.deno = d;
	}

	public Fraction(int n) {
		this(n, 1);
	}

	public Fraction() {
		this(0, 1);
	}

	public int getNumerator() {
		return this.numer;
	}

	public int getDenominator() {
		return this.deno;
	}

	@Override
	public String toString() {
		return numer + "/" + this.deno;
	}

	@Override
	public boolean equals(Object other) {

		this.toLowestTerms();
		((Fraction) other).toLowestTerms();
		return this.numer == ((Fraction) other).numer && this.deno == ((Fraction) other).deno;
	}

	public double toDouble() {
		return this.numer / this.deno;
	}

	public Fraction add(Fraction other) {

		if (other == null)
			return new Fraction(Integer.MIN_VALUE, -1);
		int new_denom = this.deno * other.deno;
		this.numer *= other.deno;
		other.numer *= this.deno;

		Fraction nf = new Fraction(this.numer + other.numer, new_denom);
		nf.toLowestTerms();
		return nf;
	}

	public Fraction subtract(Fraction other) {

		if (other == null)
			return new Fraction(Integer.MIN_VALUE, -1);

		int new_denom = this.deno * other.deno;
		this.numer *= other.deno;
		other.numer *= this.deno;

		Fraction nf = new Fraction(this.numer - other.numer, new_denom);
		nf.toLowestTerms();
		return nf;

	}

	public Fraction multiply(Fraction other) {
		if (other == null)
			return new Fraction(Integer.MIN_VALUE, -1);
		Fraction nf = new Fraction(this.numer * other.numer, this.deno * other.deno);
		nf.toLowestTerms();
		return nf;
	}

	public Fraction Divide(Fraction other) {
		if (other == null)
			return new Fraction(Integer.MIN_VALUE, -1);
		other.numer = other.numer ^ other.deno ^ (other.deno = other.numer);
		return this.multiply(other);
	}

	private int gcd(int a, int b) {
		return a == 0 ? b : gcd(b % a, a);
	}

	private void toLowestTerms() {

		int g = gcd(this.numer, this.deno);
		this.numer /= g;
		this.deno /= g;
	}

	public int TakeInput() {
		String[] input = scn.nextLine().split("/");
		try {

			this.numer = Integer.parseInt(input[0]);
			this.deno = Integer.parseInt(input[1]);
			return 1;
		} catch (Exception e) {
			this.numer = -1;
			this.deno = -1;
			indent.AlignSlowmo(2, "Invalid Input!!! Check Carefully Mam... ");
			return -1;
		}

	}

	private Fraction FactoryMethod(int choice) {
		scn.nextLine();
		if (choice == 5) {
			indent.AlignSlowmo(2, "Enter The Fraction to be reduced: ");
			this.TakeInput();
			return null;
		} else {
			indent.AlignSlowmo(2, "Enter The First Fraction (x/y): ");
			int res = this.TakeInput();
			if (res == -1)
				return null;
			Fraction other = new Fraction();
			indent.AlignSlowmo(2, "Enter The Second Fraction (x/y): ");
			res = other.TakeInput();
			if (res == -1)
				return null;

			return other;
		}
	}

	// Interface with one Function is called Functional Interface
	@FunctionalInterface
	private interface function {
		public Fraction calculate(Fraction other);
	}

	private Fraction calculate(Fraction other, function op) {
		return op.calculate(other);
	}

	public void Start() {
		char ch = indent.PrintCalc();
		if (ch == 'y' || ch == 'Y')
			Menu();
	}

	public void Menu() {

		indent.AlignSlowmo(1, "~~~~~MENU~~~~~");
		indent.AlignSlowmo(2, "1.Add Fractions.\n");
		indent.AlignSlowmo(1, "2.Subtract Fractions.\n");
		indent.AlignSlowmo(1, "3.Multiply Fractions.\n");
		indent.AlignSlowmo(1, "4.Divide Fractions.\n");
		indent.AlignSlowmo(1, "5.Convert to Lowest Form.\n");
		indent.AlignSlowmo(1, "6.Exit ");
		Fraction other = null;

		while (true) {
			indent.separator(100);
			indent.align(1, "Enter your choice: ");
			int choice = scn.nextInt();

			if (choice != 6)
				other = FactoryMethod(choice);

			if (choice == 1) {// lamda functions
				calculate(other, (o) -> {
					return this.add(o);
				}).printResult();
			} else if (choice == 2) {
				calculate(other, (o) -> {
					return this.subtract(o);
				}).printResult();
			} else if (choice == 3) {
				calculate(other, (o) -> {
					return this.multiply(o);
				}).printResult();
			} else if (choice == 4) {
				calculate(other, (o) -> {
					return this.Divide(o);
				}).printResult();
			} else if (choice == 5) {
				this.toLowestTerms();
				this.printResult();
			} else {
				indent.AlignSlowmo(1, "Bye Mam!! Have A Nice Day ୧(＾ 〰 ＾)୨");
				break;
			}

		}
		indent.separator(100);
	}

	private void printResult() {
		indent.separator(100);
		if (this.numer == Integer.MIN_VALUE) {
			indent.AlignSlowmo(1, "No Result This Time Mam ᕙ( : ˘ ∧ ˘ : )ᕗ. ");
		} else {
			indent.AlignSlowmo(1, "I Have Calculated it for you Mam ᕙ(＠°▽°＠)ᕗ: ");
			indent.createDelay(this.numer + " / " + this.deno);
		}

		indent.separator(100);
	}

}
