package burgerqueen.application;

import burgerqueen.dto.Food;
import burgerqueen.dto.Menu;
import burgerqueen.dto.OrderForm;

import java.util.List;

public interface MemoryRepository {

    Menu displayMenu();
    Food getFood(OrderForm order);
    Food getFoodByIndex(int index);
    List<Food> getFoods();
}
