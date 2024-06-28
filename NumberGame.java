import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;
        
        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 0;
            boolean hasGuessedCorrectly = false;
            int maxAttempts = 10; // You can change this to limit the number of attempts

            System.out.println("Guess the number between 1 and 100");

            while (!hasGuessedCorrectly && numberOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;

                if (userGuess == numberToGuess) {
                    hasGuessedCorrectly = true;
                    totalScore += maxAttempts - numberOfAttempts + 1; // Higher score for fewer attempts
                    System.out.println("Congratulations! You've guessed the correct number.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was: " + numberToGuess);
            }

            roundsPlayed++;
            
            System.out.print("Do you want to play again? (yes/no): ");
            scanner.nextLine(); // Consume the newline
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("You've played " + roundsPlayed + " rounds.");
        System.out.println("Your total score is: " + totalScore);
        
        scanner.close();
    }
}