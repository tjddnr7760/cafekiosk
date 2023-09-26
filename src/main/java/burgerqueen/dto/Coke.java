package burgerqueen.dto;

import burgerqueen.Category;

public class Coke extends Food {

    private int straw;

    public Coke(String name, Category category, int price, int kcal, int straw) {
        super(name, category, price, kcal);

        validateConstructorStrawNum(straw);
        this.straw = straw;
    }

    private void validateConstructorStrawNum(int straw) {
        if (straw < 0 || straw > 5) {
            throw new IllegalArgumentException("빨대 갯수가 잘못 입력되었습니다.");
        }
    }
    @Override
    public int isHasStraw() {
        return straw;
    }

    @Override
    public void syncOrderForm(OrderForm orderForm) {
        validateStrawNum(orderForm);
        straw = orderForm.getStrawNum();
    }

    private void validateStrawNum(OrderForm orderForm) {
        if (orderForm.getStrawNum() > 5 || orderForm.getStrawNum() < 0) {
            throw new IllegalArgumentException("빨대 갯수가 잘못 입력되었습니다.");
        }
    }

    @Override
    public String toString() {
        return "Coke{" +
                "straw=" + straw +
                '}' + super.toString();
    }
}
