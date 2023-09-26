package burgerqueen.dto;

import burgerqueen.Category;

import java.util.Objects;

public class Food {

    private static final String ERROR_MESSAGE = "Food 객체 생성 오류";
    private static final int MIN_PRICE = 0;
    private static final int MAX_PRICE = 100_000_000;
    private static final int MIN_KCAL = 0;
    private static final int MAX_KCAL = 5000;

    private String name;
    private Category category;
    private int price;
    private int kcal;

    public Food(String name, Category category, int price, int kcal) {
        // 항상 null먼저 비교
        validateName(name);
        validateCategory(category);
        validatePrice(price);
        validateKcal(kcal);

        this.name = name;
        this.category = category;
        this.price = price;
        this.kcal = kcal;
    }

    private void validateKcal(int kcal) {
        if (kcal < MIN_KCAL || kcal > MAX_KCAL) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private void validatePrice(int price) {
        if (price < MIN_PRICE || price > MAX_PRICE) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private void validateCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private void validateName(String name) {
        // 항상 null을 먼저 비교
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getKcal() {
        return kcal;
    }

    public void syncOrderForm(OrderForm orderForm) {
        //overriding
    }

    public boolean isSet() {
        return false;
    }

    public int getKetchup() {
        return 0;
    }

    public int isHasStraw() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return price == food.price &&
                kcal == food.kcal &&
                Objects.equals(name, food.name) &&
                category == food.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, price, kcal);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", kcal=" + kcal +
                '}';
    }
}
