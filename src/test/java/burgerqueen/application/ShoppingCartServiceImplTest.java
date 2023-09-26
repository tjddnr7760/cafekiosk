package burgerqueen.application;

import burgerqueen.AppConfig;
import burgerqueen.Category;
import burgerqueen.dto.Burger;
import burgerqueen.dto.Coke;
import burgerqueen.dto.Food;
import burgerqueen.dto.Side;
import burgerqueen.view.ShoppingCartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartServiceImplTest {
    @DisplayName("정상동작 : getShoppingCartService() 장바구니에 저장된 List<Llist<Food>가 리턴된다.")
    @ParameterizedTest
    @MethodSource("getShoppingCartService_Provider")
    void testGetShoppingCartService(List<List<Food>> foods) {
        //given
        AppConfig appConfig = new AppConfig();
        ShoppingCartService shoppingCartService = appConfig.getShoppingCartServiceImpl();
        Order order = appConfig.getCozOrderImpl();

        //when
        for (List<Food> eachFoods : foods) {
            order.saveFoodInShoopingCart(eachFoods);
        }
        List<List<Food>> shoppingList = shoppingCartService.getShoppingCartService();

        //then
        assertThat(shoppingList).isEqualTo(foods);
    }

    private static Stream<Arguments> getShoppingCartService_Provider() {
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
}
