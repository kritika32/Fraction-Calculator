import java.util.*;
import java.util.concurrent.TimeUnit;
public class FractionCalculator {
	public static void main(String[] args) {
		Fraction f = new Fraction();
		f.Start();
	}

}
class Fraction {
	private int numer;
	private int denom;
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
				delay(70);
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

		public static void alignPrint(int t, String messg) {

			align(0, leftPadding, messg);
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

				if (row/2 == i && messg.length() % 2 == 1)
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
				indent.AlignSlowmo(1, "    But Never Mind Let's onn My Calculator Mode! ");
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

	}
}