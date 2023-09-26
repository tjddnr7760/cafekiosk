package burgerqueen.dto;

import burgerqueen.Category;

import java.util.List;
import java.util.Objects;

public class Menu {

    private List<Food> foods = List.of(
            new Burger("새우버거", Category.BURGER, 3500, 500, false),
            new Burger("치킨버거", Category.BURGER, 4000, 600, false),
            new Side("감자튀김", Category.SIDE, 1000, 300, 0),
            new Side("어니언링", Category.SIDE, 1000,300, 0),
            new Coke("코카콜라", Category.COKE, 1000, 200, 0),
            new Coke("제로콜라", Category.COKE, 1500, 0, 0)
    );

    public Menu getAllMenu() {
        return this;
    }

    public List<Food> getFoods() {
        return this.foods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(foods, menu.foods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foods);
    }
}
