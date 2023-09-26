package burgerqueen.application;

import burgerqueen.dto.Food;

import java.util.List;

public interface Order {

    List<Food> saveFoodInShoopingCart(List<Food> foods);
    List<List<Food>> getShoppingCart();
    int calculateDiscount(int isCodestates, int age);
}
