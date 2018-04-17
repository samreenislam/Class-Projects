import java.net.URL;
import java.util.Random;
import java.util.Scanner;

// Samreen F. Islam (sfi6zy)
// Used code learned in class and piazza

public class Hangman {

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String yourword = input.nextLine();
		String empty = "";
		if ( yourword.equals(empty)){	//If word not entered, enter URL with comma-separated words
			System.out.print("Enter a URL: ");
			String url = input.nextLine();
			URL web = new URL(url);
			Scanner Reader = new Scanner(web.openStream());
			String line = Reader.nextLine();
			String[] word = line.split(",");
			int number = word.length;
			Random rand = new Random();
			int theone = rand.nextInt(number+1);
			String theword = word[theone];
			System.out.println("Selecting a random word.");
			String TheWord = theword.toUpperCase();
			
			int lengthw = TheWord.length();
			
			String bracket1 = "";
			for ( int i = 0; i < lengthw ; i ++){
				bracket1 += "-";
			}
			System.out.print("[" + bracket1 + "]" + " You have 6 left, enter a letter: ");
			int wonder = 0;
			int turns = 6;
			String entered = input.next();
			
			while (turns >= 1 && wonder < lengthw) {
				
			String Entered = entered.toUpperCase();
			char zeone = 0;
			char letter = 0;
			
			
			for ( int i = 0; i < lengthw ; i ++){
				letter = TheWord.charAt(i);
				if (Entered.charAt(0) == letter) {
					zeone = letter;
					break;
				} 
					}
			
			String bracket2 = "";
			for ( int j = 0; j < lengthw ; j ++){
				if (TheWord.charAt(j) == zeone ) {
					bracket2 += zeone;
					if (zeone != bracket1.charAt(j)){
					wonder += 1;}
				} else {
				bracket2 += bracket1.charAt(j); 
				}
			}
			
			if (zeone == letter) {
				if (wonder < lengthw) {
				System.out.println("Correct!"); }
			} else {
				turns -= 1;
				if (turns > 0) {
					System.out.println("Incorrect!");
				}
			}
			
			
			
			bracket1 = bracket2;
			if (wonder < lengthw && turns >= 1) {
			System.out.print("[" + bracket2 + "]" + " You have " + turns + " left, enter a letter: ");
			entered = input.next();
			} else if (wonder == lengthw) {
				System.out.print("You win! The word was \"" + TheWord + "\"");
			} else if ( turns == 0){
				System.out.print("You lose! The word was \"" + TheWord + "\"");
			}
			}
			
			
			
			
		} else {
			String YourWord = yourword.toUpperCase();
			int lengthw = YourWord.length();
			String bracket1 = "";
			for ( int i = 0; i < lengthw ; i ++){
				bracket1 += "-";
			}
			System.out.print("[" + bracket1 + "]" + " You have 6 left, enter a letter: ");
			int wonder = 0;
			int turns = 6;
			String entered = input.next();
			
			while (turns >= 1 && wonder < lengthw) {
				
			String Entered = entered.toUpperCase();
			char zeone = 0;
			char letter = 0;
			
			
			for ( int i = 0; i < lengthw ; i ++){
				letter = YourWord.charAt(i);
				if (Entered.charAt(0) == letter) {
					zeone = letter;
					break;
				} 
					}
			
			String bracket2 = "";
			for ( int j = 0; j < lengthw ; j ++){
				if (YourWord.charAt(j) == zeone ) {
					bracket2 += zeone;
					if (zeone != bracket1.charAt(j)){
					wonder += 1;}
				} else {
				bracket2 += bracket1.charAt(j); 
				}
			}
			
			if (zeone == letter) {
				if (wonder < lengthw) {
				System.out.println("Correct!"); }
			} else {
				turns -= 1;
				if (turns > 0) {
					System.out.println("Incorrect!");
				}
			}
			
			
			
			bracket1 = bracket2;
			if (wonder < lengthw && turns >= 1) {
			System.out.print("[" + bracket2 + "]" + " You have " + turns + " left, enter a letter: ");
			entered = input.next();
			} else if (wonder == lengthw) {
				System.out.print("You win! The word was \"" + YourWord + "\"");
			} else if ( turns == 0){
				System.out.print("You lose! The word was \"" + YourWord + "\"");
			}
			}
			
		}

	}

}
