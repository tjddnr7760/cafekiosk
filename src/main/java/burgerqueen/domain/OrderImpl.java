package burgerqueen.domain;

import burgerqueen.application.Order;
import burgerqueen.dto.Food;

import java.util.List;

public class OrderImpl implements Order {

    public OrderImpl(Discount discount) {
        this.discount = discount;
    }

    private Discount discount;
    private ShoppingCart shoppingCart = new ShoppingCart();

    @Override
    public List<Food> saveFoodInShoopingCart(List<Food> foods) {
        return saveFood(foods);
    }

    private List<Food> saveFood(List<Food> foods) {
        return shoppingCart.saveFood(foods);
    }

    @Override
    public List<List<Food>> getShoppingCart() {
        return shoppingCart.show();
    }


// |이 코드는 할인 금액을 계산하는 메소드입니다.
// |
// |좋은 점:
// |- 코드의 가독성이 높습니다. 메소드명과 변수명이 명확하게 지어져 있어 코드를 이해하기 쉽습니다.
// |- 메소드가 한 가지 일만 하도록 구현되어 있습니다. 쇼핑카트의 총 금액을 계산하고 할인 금액을 계산하는 두 가지 기능이 분리되어
// 있습니다.
// |- 인터페이스를 활용하여 유연성을 높였습니다. 할인 금액을 계산하는 방식이 변경되더라도 코드 수정이 쉬울 것입니다.
// |
// |나쁜 점:
// |- 메소드의 파라미터가 많습니다. isCodestates와 age 외에도 shoppingCart.getSum()을 호출하여 총 금액을
// 가져와야 합니다. 이러한 경우 메소드를 호출할 때 매번 많은 파라미터를 전달해야 하므로 불편할 수 있습니다.
    @Override
    public int calculateDiscount(int isCodestates, int age) {
        int sum = shoppingCart.getSum();
        return discount.howMuchDiscount(isCodestates, age, sum);
    }
}
