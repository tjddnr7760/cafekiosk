package burgerqueen.dto;

import burgerqueen.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BurgerTest {

    /**
     * 1. 정상동작 테스트
     *  - Burger객체 정상생성 된다.
     *  - equals 정상 동작한다.
     *  - hashCode 정상 동작한다.
     *
     * 2. 예외처리 테스트
     *  - 접근함수가 하나로, boolean타입만을 생성자 매개변수로 유일하게 가짐으로
     *    컴파일 에러로 전부 예외처리 가능하다.
     *
     * 3. 경계조건 테스트
     *  - boolean타입이기 때문에 경계조건이 없다.
     */

    @DisplayName("정상동작 : Burger 객체가 정상 생성된다.")
    @ParameterizedTest
    @MethodSource("createBurger_Provider")
    void testCreateBurger(String name, Category category, int price, int kcal, boolean isSet) {
        //when
        Burger burger = new Burger(name, category, price, kcal, isSet);

        //then
        assertThat(burger)
                .extracting(Burger::getName, Burger::getCategory, Burger::getPrice, Burger::getKcal, Burger::isSet)
                .containsExactly(name, category, price, kcal, isSet);
    }

    @DisplayName("정상동작 : equals")
    @ParameterizedTest
    @MethodSource("createBurger_Provider")
    void equalsTest(String name, Category category, int price, int kcal, boolean isSet) {
        //when
        Burger burger1 = new Burger(name, category, price, kcal, isSet);
        Burger burger2 = new Burger(name, category, price, kcal, isSet);

        boolean compare = burger1.equals(burger2);

        //then
        assertThat(compare).isTrue();
    }

    @DisplayName("정상동작 : hashCode")
    @ParameterizedTest
    @MethodSource("createBurger_Provider")
    void hashCodeTest(String name, Category category, int price, int kcal, boolean isSet) {
        //when
        Burger burger1 = new Burger(name, category, price, kcal, isSet);
        Burger burger2 = new Burger(name, category, price, kcal, isSet);

        //then
        assertThat(burger1.hashCode()).isEqualTo(burger2.hashCode());
    }

    private static Stream<Arguments> createBurger_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 4500, 1300, false),
                Arguments.of("Aburger", Category.BURGER, 3000, 1200, true),
                Arguments.of("Aburger", Category.BURGER, 3500, 1400, false)
        );
    }

    @DisplayName("정상동작 : 버거의 세트설정이 동작하는지 검증한다.")
    @Test
    void testSyncOrderFrom() {
        //given
        OrderForm orderForm = new OrderForm(1);
        orderForm.setSet(true);
        Burger burger1 = new Burger("Aburger", Category.BURGER, 4500, 1300, false);

        //when
        burger1.syncOrderForm(orderForm);

        //then
        assertThat(burger1.isSet()).isTrue();
    }
}
