package DataStructures;
import java.util.Scanner;
public class PalindromeChecker {
	public static void main (String[]args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter a String:");
		String orginal = scanner.nextLine();
		
		String reversed = new StringBuilder(orginal).reverse().toString();
		
		if 
		(orginal.equalsIgnoreCase(reversed)) {
			System.out.println(orginal + " is a palindrome");
			
		}
		else {
			System.out.println(orginal + " is not a palindrome.");
			
		}
	}

}
