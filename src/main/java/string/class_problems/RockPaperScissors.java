package string.class_problems;

import java.util.Scanner;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if (playerMove.equals("Rock") && computerMove.equals("Scissors")) {
            return "Player Wins";
        }

        if (playerMove.equals("Paper") && computerMove.equals("Rock")) {
            return "Player Wins";
        }

        if (playerMove.equals("Scissors") && computerMove.equals("Paper")) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] moves = {"Rock", "Paper", "Scissors"};

        int wins = 0;
        int losses = 0;
        int draws = 0;

        int rounds = 5;

        for (int i = 1; i <= rounds; i++) {

            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            String playerMove = sc.nextLine();

            int randomIndex = (int) (Math.random() * 3);
            String computerMove = moves[randomIndex];

            String result = playRound(playerMove, computerMove);

            System.out.println("Round " + i);
            System.out.println("Player: " + playerMove);
            System.out.println("Computer: " + computerMove);
            System.out.println("Result: " + result);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println();
        }

        double winPercentage = (wins * 100.0) / rounds;

        System.out.println("Final Summary");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win Percentage: " + winPercentage + "%");

        sc.close();
    }
}