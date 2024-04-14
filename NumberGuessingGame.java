package codsoft_task1;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Number Guessing Game!");

        int round = 1;
        int[] scores = new int[2];

        while (true) {
            System.out.println("Round " + round);
            int maxAttempts = 0;
            int numPlayers = 0;

            System.out.print("Enter the maximum number of attempts: ");
            maxAttempts = scanner.nextInt();

            System.out.print("Enter the number of players (1 for single-player, 2 for multiplayer): ");
            numPlayers = scanner.nextInt();

            if (numPlayers == 1) {
                int score = playSinglePlayerGame(random, maxAttempts);
                System.out.println("Your score for round " + round + ": " + score);
                scores[0] += score;
            } else if (numPlayers == 2) {
                int winner = playMultiplayerGame(random, maxAttempts);
                if (winner == 0) {
                    System.out.println("It's a tie! Both players ran out of attempts.");
                } else {
                    System.out.println("Player " + winner + " wins round " + round + "!");
                    scores[winner - 1]++;
                }
            }

            System.out.println("Player 1 score: " + scores[0]);
            System.out.println("Player 2 score: " + scores[1]);

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            round++;
        }

        scanner.close();
    }

    public static int playSinglePlayerGame(Random random, int maxAttempts) {
        int number = random.nextInt(100) + 1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Guess the number (1-100): ");
        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            int guess = scanner.nextInt();
            if (guess < number) {
                System.out.println("Too low! Try again.");
            } else if (guess > number) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                return maxAttempts - attempts + 1;
            }
        }

        System.out.println("You've run out of attempts. The number was " + number + ".");
        return 0;
    }

    public static int playMultiplayerGame(Random random, int maxAttempts) {
        int[] scores = new int[2];

        for (int player = 1; player <= 2; player++) {
            int number = random.nextInt(100) + 1;

            Scanner scanner = new Scanner(System.in);

            System.out.println("Player " + player + ", it's your turn.");
            for (int attempts = 1; attempts <= maxAttempts; attempts++) {
                System.out.print("Guess the number (1-100): ");
                int guess = scanner.nextInt();
                if (guess < number) {
                    System.out.println("Too low! Try again.");
                } else if (guess > number) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    scores[player - 1] = maxAttempts - attempts + 1;
                    break;
                }
            }

            if (scores[player - 1] == 0) {
                System.out.println("You've run out of attempts. The number was " + number + ".");
            }
        }

        if (scores[0] < scores[1]) {
            return 2; // Player 2 wins
        } else if (scores[0] > scores[1]) {
            return 1; // Player 1 wins
        } else {
            return 0; // Tie
        }
    }
}
