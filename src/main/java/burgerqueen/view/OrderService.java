package burgerqueen.view;

import burgerqueen.dto.Food;
import burgerqueen.dto.OrderForm;

import java.util.List;

public interface OrderService {

    List<Food> saveShoppingCart(OrderForm order);
    int finalOrder(int isCodeStates, int age);
}
