package burgerqueen.application;

import burgerqueen.AppConfig;
import burgerqueen.Category;
import burgerqueen.dto.*;
import burgerqueen.view.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {
    @DisplayName("정상동작 : OrderForm형식대로 food를 동기화하고 저장한 food리스스를 리턴한다.")
    @ParameterizedTest
    @MethodSource("saveShoppingCart_Provider")
    void testSaveShoppingCart(OrderForm orderForm, List<Food> foods) {
        //given
        AppConfig appconfig = new AppConfig();
        OrderService orderService = appconfig.getOrderServiceImpl();

        //when
        orderForm = addOptions(orderForm);
        List<Food> getFoods = orderService.saveShoppingCart(orderForm);

        //then
        assertThat(getFoods).isEqualTo(foods);
    }

    private static OrderForm addOptions(OrderForm orderForm) {
        if (orderForm.getSelectNum() == 0) {
            orderForm.setSet(true);
            orderForm.setSetSide(3);
            orderForm.setKetchapNum(5);
            orderForm.setSetCoke(5);
            orderForm.setStrawNum(1);

            return orderForm;
        }

        if (orderForm.getSelectNum() == 2) {
            orderForm.setKetchapNum(3);
            return orderForm;
        }

        return orderForm;
    }

    private static Stream<Arguments> saveShoppingCart_Provider() {
        return Stream.of(
                //given
                Arguments.of(
                        new OrderForm(0),
                        List.of(
                                new Burger("새우버거", Category.BURGER, 3500, 500, true),
                                new Side("어니언링", Category.SIDE, 1000,300, 5),
                                new Coke("제로콜라", Category.COKE, 1500, 0, 1)
                        )
                ),
                Arguments.of(
                        new OrderForm(2),
                        List.of(
                                new Side("감자튀김", Category.SIDE, 1000, 300, 3)
                        )
                )
        );
    }

    @DisplayName("정상동작 : 최종 주문 할인금액이 정상 출력된다.")
    @ParameterizedTest
    @MethodSource("testFinalOrder_Provider")
    void testFinalOrder(List<List<Food>> foods, int sum, int isCodestates, int age) {
        //given
        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.getOrderServiceImpl();
        Order order = appConfig.getCozOrderImpl();

        //when
        for (List<Food> eachFoods : foods) {
            order.saveFoodInShoopingCart(eachFoods);
        }

        int discount = orderService.finalOrder(isCodestates, age);

        //then
        assertThat(discount).isEqualTo(sum);
    }

    private static Stream<Arguments> testFinalOrder_Provider() {
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
}
