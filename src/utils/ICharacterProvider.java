package utils;

public interface ICharacterProvider {
    /*
    * Method provides new characters for the HangmanGame
    *
    * */
    String getProposedCharacter();

    /*
    * Interrupts the feed of new characters
    *
    * */
    void endProcess();
}
