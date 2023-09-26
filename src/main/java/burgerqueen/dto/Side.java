package burgerqueen.dto;

import burgerqueen.Category;

public class Side extends Food {

    private int ketchup;

    public Side(String name, Category category, int price, int kcal, int ketchup) {
        super(name, category, price, kcal);

        validateKetchap(ketchup);
        this.ketchup = ketchup;
    }

    private void validateKetchap(int ketchup) {
        if (ketchup < 0 || ketchup > 10) {
            throw new IllegalArgumentException("케찹 갯수가 잘못 입력되엇습니다.");
        }
    }
    @Override
    public int getKetchup() {
        return ketchup;
    }

    @Override
    public void syncOrderForm(OrderForm orderForm) {
        validateKetchapNum(orderForm);
        ketchup = orderForm.getKetchapNum();
    }

    private void validateKetchapNum(OrderForm orderForm) {
        if (orderForm.getKetchapNum() < 0 || orderForm.getKetchapNum() > 10) {
            throw new IllegalArgumentException("케찹 갯수가 잘못 입력되엇습니다.");
        }
    }

    @Override
    public String toString() {
        return "Side{" +
                "ketchup=" + ketchup +
                '}' + super.toString();
    }
}
