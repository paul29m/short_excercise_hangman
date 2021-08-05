package utils;

import java.util.Scanner;

public class ConsoleCharacterProvider implements ICharacterProvider{

    private Scanner consoleInput = new Scanner(System.in);

    public ConsoleCharacterProvider() {
        consoleInput = new Scanner(System.in);
    }

    @Override
    public String getProposedCharacter() {
        return consoleInput.nextLine();
    }
}
