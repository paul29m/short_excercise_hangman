import game.HangmanDetails;
import game.HangmanGame;
import utils.ConsoleCharacterProvider;

public class Main {

    public static void main(String[] args) {
        HangmanDetails hangmanDetails = new HangmanDetails(HangmanGame.getRandomWord(), 10);
        HangmanGame game = new HangmanGame(new ConsoleCharacterProvider(), hangmanDetails);
        game.start();
    }
}
