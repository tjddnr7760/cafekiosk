package burgerqueen.domain;

import burgerqueen.Category;
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

public class ShoppingCartTest {
    @DisplayName("정상동작 : saveFood() foods리스트가 저장된다.")
    @ParameterizedTest
    @MethodSource("shoppingCartTest_Provider")
    void testSaveFood(List<Food> foods) {
        //given
        ShoppingCart shoppingCart = new ShoppingCart();

        //when
        List<Food> getFoods = shoppingCart.saveFood(foods);

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

    @DisplayName("정상동작 : show() 를 호출하면 List<List<Food>>를 올바르게 리턴한다.")
    @ParameterizedTest
    @MethodSource("showTest_Provider")
    void testShow(List<List<Food>> foods) {
        //when
        ShoppingCart shoppingCart = new ShoppingCart();
        for (List<Food> eachFoods : foods) {
            shoppingCart.saveFood(eachFoods);
        }
        List<List<Food>> shoppingList = shoppingCart.show();

        //then
        assertThat(shoppingList).isEqualTo(foods);
    }

    private static Stream<Arguments> showTest_Provider() {
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

    @DisplayName("정상동작 : 저장된 음식들의 총합을 정상적으로 계산한다.")
    @ParameterizedTest
    @MethodSource("testGetSum_Provider")
    void test_GetSum(List<List<Food>> foods, int sum) {
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        for (List<Food> eachFoods : foods) {
            shoppingCart.saveFood(eachFoods);
        }

        //when
        int result = shoppingCart.getSum();

        //then
        assertThat(result).isEqualTo(sum);
    }

    private static Stream<Arguments> testGetSum_Provider() {
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
                        ), 9500
                ),
                Arguments.of(
                        List.of(
                                List.of(
                                        new Burger("새우버거", Category.BURGER, 2500, 500, false),
                                        new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                        new Coke("코카콜라", Category.COKE, 3000, 200, 0)
                                ),
                                List.of(
                                        new Burger("치킨버거", Category.BURGER, 5300, 600, false)
                                )
                        ), 11800
                )
        );
    }
}
