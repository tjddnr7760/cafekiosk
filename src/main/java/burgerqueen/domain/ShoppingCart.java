package burgerqueen.domain;

import burgerqueen.dto.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {

    private List<List<Food>> shoppingList = new ArrayList<>();

    public List<Food> saveFood(List<Food> foods) {
        return add(foods);
    }

    private List<Food> add(List<Food> foods) {
        shoppingList.add(foods);
        return foods;
    }

    public List<List<Food>> show() {
        return this.shoppingList;
    }

    public int getSum() {
        return shoppingList.stream()
                .flatMap(List::stream)
                .collect(Collectors.summingInt(Food::getPrice));
    }
}
