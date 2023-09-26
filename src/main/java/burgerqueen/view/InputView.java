package burgerqueen.view;

import java.util.Scanner;

public class InputView {

    Scanner scanner = new Scanner(System.in);

    public int inputMenuNum() {
        return scanner.nextInt() - 1;
    }

    public int inputMenu() {
        return scanner.nextInt();
    }

    public String inputString() {
        return scanner.nextLine();
    }
}
