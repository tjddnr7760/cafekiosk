package burgerqueen.domain;

import burgerqueen.AppConfig;
import burgerqueen.Category;
import burgerqueen.application.Order;
import burgerqueen.dto.Burger;
import burgerqueen.dto.Coke;
import burgerqueen.dto.Food;
import burgerqueen.dto.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderImplTest {
    @DisplayName("정상동작 : saveFoodInShoopingCart() foods 리스트가 저장된다.")
    @ParameterizedTest
    @MethodSource("shoppingCartTest_Provider")
    void testsaveFoodInShoopingCart(List<Food> foods) {
        //given
        Discount cozDiscount = new CozDiscount();
        Order order = new OrderImpl(cozDiscount);

        //when
        List<Food> getFoods = order.saveFoodInShoopingCart(foods);

        //then
        assertThat(getFoods).isEqualTo(foods);
    }

    private static Stream<Arguments> shoppingCartTest_Provider() {
        return Stream.of(
                //given
                Arguments.of(
                        List.of(
                                new Burger("새우버거", Category.BURGER, 3500, 500, false),
                                new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                new Coke("코카콜라", Category.COKE, 1000, 200, 0)
                        ),
                        List.of(
                                new Burger("치킨버거", Category.BURGER, 4000, 600, false)
                        )
                )
        );
    }

    @DisplayName("정상동작 : getShoppingCart() 가 올바른 List<List<Food>>를 반환한다.")
    @ParameterizedTest
    @MethodSource("getShoppingCart_Provider")
    void testGetShoppingCart(List<List<Food>> foods) {
        //given
        Discount cozDiscount = new CozDiscount();
        Order order = new OrderImpl(cozDiscount);
        for (List<Food> eachFoods : foods) {
            order.saveFoodInShoopingCart(eachFoods);
        }
        //when
        List<List<Food>> listFoods = order.getShoppingCart();

        //then
        assertThat(listFoods).isEqualTo(foods);
    }

    private static Stream<Arguments> getShoppingCart_Provider() {
        return Stream.of(
                //given
                Arguments.of(
                        List.of(
                                List.of(
                                        new Burger("새우버거", Category.BURGER, 3500, 500, false),
                                        new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                        new Coke("코카콜라", Category.COKE, 1000, 200, 0)
                                ),
                                List.of(
                                        new Burger("치킨버거", Category.BURGER, 4000, 600, false)
                                )
                        )
                )
        );
    }

    @DisplayName("정상동작 : CozDiscount 10프로 할인이 정상적으로 계산된다.")
    @ParameterizedTest
    @MethodSource("CozHowMuchDiscount_Provider")
    void test_CozHowMuchDiscount(List<List<Food>> foods, int sum, int isCodeStates, int age) {
        //when
        AppConfig app = new AppConfig();
        Order order = app.getCozOrderImpl();

        for (List<Food> food : foods) {
            order.saveFoodInShoopingCart(food);
        }

        int discountValue = order.calculateDiscount(isCodeStates, age);

        //then
        assertThat(discountValue).isEqualTo(sum);
    }

    private static Stream<Arguments> CozHowMuchDiscount_Provider() {
        return Stream.of(
                //given
                Arguments.of(
                        List.of(
                                List.of(
                                        new Burger("새우버거", Category.BURGER, 3500, 500, false),
                                        new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                        new Coke("코카콜라", Category.COKE, 1000, 200, 0)
                                ),
                                List.of(
                                        new Burger("치킨버거", Category.BURGER, 4000, 600, false)
                                )
                        ), 950, 1, 25
                )
        );
    }

    @DisplayName("정상동작 : KizDiscount 500원 할인이 정상적으로 계산된다.")
    @ParameterizedTest
    @MethodSource("KizHowMuchDiscount_Provider")
    void test_KizHowMuchDiscount(List<List<Food>> foods, int sum, int isCodeStates, int age) {
        //when
        AppConfig app = new AppConfig();
        Order order = app.getKizOrderImpl();

        for (List<Food> food : foods) {
            order.saveFoodInShoopingCart(food);
        }

        int discountValue = order.calculateDiscount(isCodeStates, age);

        //then
        assertThat(discountValue).isEqualTo(sum);
    }

    private static Stream<Arguments> KizHowMuchDiscount_Provider() {
        return Stream.of(
                //given
                Arguments.of(
                        List.of(
                                List.of(
                                        new Burger("새우버거", Category.BURGER, 1500, 500, false),
                                        new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                        new Coke("코카콜라", Category.COKE, 1000, 200, 0)
                                ),
                                List.of(
                                        new Burger("치킨버거", Category.BURGER, 2000, 600, false)
                                )
                        ), 500, 1, 17
                )
        );
    }
}
