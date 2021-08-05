package game;

import utils.ICharacterProvider;

import java.util.Arrays;
import java.util.List;

public class HangmanGame {

    private static List<String> words = Arrays.asList("mercedes", "science", "computer", "dog", "rainbow", "gadgets");

    private ICharacterProvider characterProvider;

    private HangmanDetails hangmanDetails;

    private int score;

    public HangmanGame(ICharacterProvider characterProvider, HangmanDetails hangmanDetails) {
        this.characterProvider = characterProvider;
        this.hangmanDetails = hangmanDetails;
        score = 0;
    }

    public void start() {
        printHelloMessage();
        while (hangmanDetails.getNumberOfChances() != 0 && !checkWin()) {
            printNextIteration();
            String newGuess = getNewCharacter();
            if (newGuess instanceof String) {
                updateGameStatus(newGuess);
            }
        }

        if (checkWin()) {
            printCongratsMessage();
            score += 10;
        } else {
            printSorryMessage();
        }

        printGoodbyeOrTryAgainMessage();
        checkIfTyrAgain();
    }

    private boolean checkWin() {
        return hangmanDetails.getCurrentGuess().equals(hangmanDetails.getCorrectWord());
    }

    private void updateGameStatus(String newGuess) {
        if (hangmanDetails.getCorrectWord().contains(newGuess) && !hangmanDetails.getCurrentGuess().contains(newGuess)) {
            computeCurrentGuess(newGuess);
            printCorrectInputMessage(newGuess);
        }

        if (!hangmanDetails.getCorrectWord().contains(newGuess) && !hangmanDetails.getCurrentGuess().contains(newGuess)) {
            hangmanDetails.oneChanceGone();
        }

        if(!hangmanDetails.getCorrectWord().contains(newGuess)) {
            hangmanDetails.addToWrongAnswers(newGuess);
        }
    }

    public void computeCurrentGuess(String newGuess) {
        StringBuilder newCurrentGuess = new StringBuilder();
        for (int i = 0; i < hangmanDetails.getCorrectWord().length(); i++) {
            if (hangmanDetails.getCorrectWord().charAt(i) == newGuess.charAt(0)) {
                newCurrentGuess.append(newGuess.charAt(0));
            } else if (hangmanDetails.getCurrentGuess().charAt(i) != HangmanDetails.FACET) {
                newCurrentGuess.append(hangmanDetails.getCorrectWord().charAt(i));
            } else {
                newCurrentGuess.append(HangmanDetails.FACET);
            }
        }

        hangmanDetails.setCurrentGuess(newCurrentGuess.toString());
    }

    public void checkIfTyrAgain() {
        String option = getNewCharacter();
        if (option instanceof String && option.toUpperCase().equals("R")) {
            hangmanDetails = new HangmanDetails(getRandomWord(), 10);
            start();
        }
        characterProvider.endProcess();
    }

    private String getNewCharacter() {
        String newCharacter = characterProvider.getProposedCharacter();
        if (validateEnteredCharacter(newCharacter)) {
            return newCharacter;
        }
        System.out.println("Sorry! Your input is not valid: " + newCharacter);
        return null;
    }

    public boolean validateEnteredCharacter(String newCharacter) {
        return newCharacter.length() == 1;
    }

    public static String getRandomWord() {
        return words.get((int) (Math.random() * words.size()));
    }

    private void printHelloMessage() {
        System.out.println("Hi gamer! You have " + hangmanDetails.getNumberOfChances() + " chances to find the correct answer. Good Luck!");
    }

    private void printCorrectInputMessage(String newGuess) {
        System.out.println("Your input <" + newGuess + "> was correct!");
    }

    private void printNextIteration() {
        System.out.println("Current guess: " + hangmanDetails.getCurrentGuess());
        System.out.println("Chances remaining: " + hangmanDetails.getNumberOfChances());
        if (!hangmanDetails.getCharactersNotInList().isEmpty()) {
            System.out.println("Wrong answers: " + hangmanDetails.getCharactersNotInList());
        }
        System.out.println("Provide your character: ");
    }

    private void printCongratsMessage() {
        System.out.println("Congrats, you have won! The word was: " + hangmanDetails.getCorrectWord());
    }

    private void printSorryMessage() {
        System.out.println("Sorry! The word was: " + hangmanDetails.getCorrectWord());
    }

    private void printGoodbyeOrTryAgainMessage() {
        System.out.println("Your game ended! Current score <" + score + ">");
        System.out.println("Type r/R to play again! Or press any button to exit");
    }

}
