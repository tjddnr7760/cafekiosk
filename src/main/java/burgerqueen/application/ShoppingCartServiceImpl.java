package burgerqueen.application;

import burgerqueen.dto.Food;
import burgerqueen.view.ShoppingCartService;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    public ShoppingCartServiceImpl(Order order) {
        this.order = order;
    }

    private Order order;

    @Override
    public List<List<Food>> getShoppingCartService() {
        return order.getShoppingCart();
    }
}
