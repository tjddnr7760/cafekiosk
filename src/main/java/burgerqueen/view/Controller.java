package burgerqueen.view;

import burgerqueen.dto.Food;
import burgerqueen.dto.Menu;
import burgerqueen.dto.OrderForm;

import java.util.List;

public class Controller {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    private MenuService menuService;
    private OrderService orderService;
    private ShoppingCartService shoppingCartService;

    public Controller(MenuService menuService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    public void displayScreen() {
        outputView.outputScreen(displayRepository());
    }

    public void selectMenu(int intSelect) {
        outputView.isSaveShoppingCart(saveShoppingCart(intSelect));
    }

    public void viewShoppingCart() {
        outputView.printShoppingCart(getShoppingCart());
    }

    public void printResult() {
        List<List<Food>> foods = getShoppingCart();
        int discount = finalOrder();
        outputView.printResult(foods, discount);
    }

    private int finalOrder() {
        outputView.isCodeStates();
        int isCode = inputView.inputMenu();
        outputView.isAge();
        int age = inputView.inputMenu();
        return orderService.finalOrder(isCode, age);
    }

    private List<List<Food>> getShoppingCart() {
        return shoppingCartService.getShoppingCartService();
    }

    private Menu displayRepository() {
        return menuService.displayRepository();
    }

    private List<Food> saveShoppingCart(int intSelect) {
        return orderService.saveShoppingCart(options(intSelect));
    }

    private int inputMenuNum() {
        return inputView.inputMenuNum();
    }

    private int insertMenu() {
        return inputView.inputMenu();
    }

    private OrderForm options(int selectNum) {
        OrderForm orderForm = new OrderForm(selectNum);

        OrderForm orderForm1 = getSetOrderForm(selectNum, orderForm);
        if (orderForm1 != null) return orderForm1;

        OrderForm orderForm2 = getSideOrderForm(selectNum, orderForm);
        if (orderForm2 != null) return orderForm2;

        OrderForm orderForm3 = getCokeOrderForm(selectNum, orderForm);
        if (orderForm3 != null) return orderForm3;

        return orderForm;
    }

    private OrderForm getCokeOrderForm(int selectNum, OrderForm orderForm) {
        if (selectNum == 4 || selectNum == 5) {
            return getCokeOrderForm(orderForm);
        }
        return null;
    }

    private OrderForm getSideOrderForm(int selectNum, OrderForm orderForm) {
        if (selectNum == 2 || selectNum == 3) {
            return getSideOrderForm(orderForm);
        }
        return null;
    }

    private OrderForm getSetOrderForm(int selectNum, OrderForm orderForm) {
        if (selectNum == 0 || selectNum == 1) {
            outputView.burgerIsSet();
            int decision = insertMenu();

            if (isSetDecision(orderForm, decision)) {
                return orderForm;
            }

        }
        return null;
    }

    private boolean isSetDecision(OrderForm orderForm, int decision) {
        if (decision == 1) {
            return true;
        }

        if (decision == 2) {
            orderForm.setSet(true);
            inputSideOrderForm(orderForm);
            inputCokeOrderForm(orderForm);
            return true;
        }
        return false;
    }

    private OrderForm getCokeOrderForm(OrderForm orderForm) {
        outputView.isStraw();
        orderForm.setStrawNum(insertMenu());
        return orderForm;
    }

    private OrderForm getSideOrderForm(OrderForm orderForm) {
        outputView.isKetchap();
        orderForm.setKetchapNum(insertMenu());
        return orderForm;
    }

    private void inputCokeOrderForm(OrderForm orderForm) {
        outputView.printCoke(displayRepository());
        orderForm.setSetCoke(inputMenuNum());
        outputView.isStraw();
        orderForm.setStrawNum(insertMenu());
    }

    private void inputSideOrderForm(OrderForm orderForm) {
        outputView.printSide(displayRepository());
        orderForm.setSetSide(inputMenuNum());
        outputView.isKetchap();
        orderForm.setKetchapNum(insertMenu());
    }

}
