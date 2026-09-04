import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    private static final String[] moves = {"Rock", "Paper", "Scissors"};
    private static Random rand = new Random();

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) return "Draw";

        if (playerMove.equalsIgnoreCase("Rock")) {
            return (computerMove.equalsIgnoreCase("Scissors")) ? "Player Wins" : "Computer Wins";
        } else if (playerMove.equalsIgnoreCase("Paper")) {
            return (computerMove.equalsIgnoreCase("Rock")) ? "Player Wins" : "Computer Wins";
        } else if (playerMove.equalsIgnoreCase("Scissors")) {
            return (computerMove.equalsIgnoreCase("Paper")) ? "Player Wins" : "Computer Wins";
        } else {
            return "Invalid Move";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rounds = 5;

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < rounds; i++) {
            System.out.print("Round " + (i+1) + " — Enter Rock, Paper, or Scissors: ");
            String playerMove = sc.nextLine().trim();
            String computerMove = moves[rand.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            System.out.println("Player: " + playerMove + ", Computer: " + computerMove + " → " + result);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else if (result.equals("Draw")) draws++;
        }
        double winPercent = (wins * 100.0) / rounds;
        System.out.printf("\nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n",
                wins, losses, draws, winPercent);
    }
}