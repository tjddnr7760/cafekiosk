package burgerqueen.dto;

import burgerqueen.Category;

public class Burger extends Food {

    private boolean isSet;

    public Burger(String name, Category category, int price, int kcal, boolean isSet) {
        super(name, category, price, kcal);
        this.isSet = isSet;
    }

    @Override
    public boolean isSet() {
        return isSet;
    }

    @Override
    public void syncOrderForm(OrderForm orderForm) {
        isSet = orderForm.isSet();
    }

    @Override
    public String toString() {
        return "Burger{" +
                "isSet=" + isSet +
                '}' + super.toString();
    }
}
