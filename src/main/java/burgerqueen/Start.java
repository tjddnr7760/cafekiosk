package burgerqueen;

import burgerqueen.view.Controller;
import burgerqueen.view.InputView;

public class Start {
    public void start() {
        InputView inputView = new InputView();
        AppConfig appConfig = new AppConfig();
        Controller controller = appConfig.getController();

        try {
            while (true) {
                controller.displayScreen();
                String select = inputView.inputString();

                if (select.equals("0")) {
                    controller.viewShoppingCart();
                } else if (select.equals("+")) {
                    controller.printResult();
                    break;
                } else {
                    int intSelect = Integer.parseInt(select) - 1;
                    controller.selectMenu(intSelect);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
