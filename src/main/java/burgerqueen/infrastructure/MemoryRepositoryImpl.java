package burgerqueen.infrastructure;

import burgerqueen.application.MemoryRepository;
import burgerqueen.dto.Food;
import burgerqueen.dto.Menu;
import burgerqueen.dto.OrderForm;

import java.util.List;

public class MemoryRepositoryImpl implements MemoryRepository {

    private Menu menu = new Menu();

    @Override
    public Menu displayMenu() {
        return menu.getAllMenu();
    }

    @Override
    public Food getFood(OrderForm order) {
        return menu.getFoods().get(order.getSelectNum());
    }

    @Override
    public List<Food> getFoods() {
        return menu.getFoods();
    }

    @Override
    public Food getFoodByIndex(int index) {
        return getFoods().get(index);
    }
}
