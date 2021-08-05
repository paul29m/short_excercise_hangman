package game;

import java.util.HashSet;
import java.util.Set;

public class HangmanDetails {
    public static final char FACET = '-';

    private String correctWord;

    private String currentGuess;

    private int numberOfChances;

    private Set<Character> charactersNotInList = new HashSet<>();

    public HangmanDetails(String correctWord, int numberOfChances) {
        this.correctWord = correctWord;
        this.numberOfChances = numberOfChances;
        this.currentGuess = generateWordFacet(correctWord);
        this.charactersNotInList = new HashSet<>();
    }

    private String generateWordFacet(String correctWord) {
        StringBuilder currentGuessBuilder = new StringBuilder();
        for (int i = 0; i < correctWord.length(); i++) {
            currentGuessBuilder.append(FACET);
        }
        return currentGuessBuilder.toString();
    }

    /*
    * Getter and setter for the HangmanGame correct answer
    *
    * */
    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    /*
    * Getter and setter for the HangmanGame current guess
    * Used to update and provide the progress of the game
    *
    * */
    public String getCurrentGuess() {
        return currentGuess;
    }

    public void setCurrentGuess(String currentGuess) {
        this.currentGuess = currentGuess;
    }

    /*
    * Getter and setter for the HangmanGame number of chances
    * Used to update and provide the chances remaining until the end of the game
    *
    * */
    public int getNumberOfChances() {
        return numberOfChances;
    }

    public void setNumberOfChances(int numberOfChances) {
        this.numberOfChances = numberOfChances;
    }

    public void oneChanceGone() {
        this.numberOfChances -=1;
    }

    public Set<Character> getCharactersNotInList() {
        return charactersNotInList;
    }

    public void setCharactersNotInList(Set<Character> charactersNotInList) {
        this.charactersNotInList = charactersNotInList;
    }

    public void addToWrongAnswers(String newGuess) {
        this.charactersNotInList.add(newGuess.charAt(0));
    }
}
