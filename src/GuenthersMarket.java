import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GuenthersMarket {
	static Scanner input = new Scanner(System.in);
	private static final Set<String> VALID_YES = new HashSet<>(Arrays.asList("apple", "banana", "cauliflower",
			"dragonfruit", "elderberry", "figs", "grapefruit", "honeydew"));
	private static final Set<String> VALID_NO = new HashSet<>(Arrays.asList("no", "n", "nope"));
	private static Map<String, Double> groceries = new TreeMap<>();
	private static Map<String, Double> cart = new TreeMap<>();
	private static boolean flag = true;
	private static String item = "null";

	public static void main(String[] args) {
		groceries.put("apple", 0.99);
		groceries.put("banana", 0.59);
		groceries.put("cauliflower", 1.59);
		groceries.put("dragonfruit", 2.19);
		groceries.put("Elderberry", 1.79);
		groceries.put("figs", 2.09);
		groceries.put("grapefruit", 1.99);
		groceries.put("honeydew", 3.49);

		System.out.println("Welcome to Guenther's Market!");
		do {
			System.out.println(market());
			contin();
		} while (flag);
		
		input.close();
	}

	public static String market() {
		System.out.print("Item");
		System.out.printf("%17s", "Price");
		System.out.println("\n==============================");
		for (Map.Entry<String, Double> grocerie : groceries.entrySet()) {
			System.out.printf("%-12s %9s", grocerie.getKey(), grocerie.getValue() + "\n");
		}
		if (choice() == true) {
			market();
		}
		return (addToCart(item));
	}

	public static boolean choice() {
		System.out.println("\nWhat item would you like to order?");
		item = input.nextLine();
		if (item.matches("[a-zA-Z]+")) {
			return getYesNo(item);
		} else {
			System.out.println("That's not an option.\n");
			return true;
		}
	}

	public static boolean getYesNo(String prompt) {
		String userInput = (prompt).toLowerCase();
		if (VALID_YES.contains(userInput)) {
			return false;
		} else if (VALID_NO.contains(userInput)) {
			return false;
		} else {
			System.out.println("That's not an option.\n");
			return true;
		}
	}

	public static String addToCart(String item) {
		boolean restart = true;
		while (true) {

			try {

				System.out.println("How many?");
				int amount = input.nextInt();
				double total = amount * groceries.get(item);
				cart.put(item, total);
				restart = false;
				return ("Adding " + amount + " " + item + "s to your cart at $" + groceries.get(item) * amount + "\n");
			} catch (InputMismatchException e) {
				input.nextLine();
			}
		}

	}

	public static void contin() {
		double average = 0;
		input.nextLine();
		System.out.println("Would you like to order anything else?(yes/no)");
		String answer = input.nextLine();
		answer.toLowerCase();
		if (answer.matches("no")) {
			flag = false;
			System.out.println("Thanks for your order!");
			for (Map.Entry<String, Double> checkOut : cart.entrySet()) {
				System.out.printf("%-12s %9s", checkOut.getKey(), checkOut.getValue() + "\n");
			}
			for (Double total : cart.values()) {
				double sum = +total;
				average = (sum / cart.size());
			}
			System.out.println("Average price per item in order was $" + average);
		}
	}
}
