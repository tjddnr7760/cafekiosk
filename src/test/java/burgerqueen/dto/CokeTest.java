package burgerqueen.dto;

import burgerqueen.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CokeTest {

    @DisplayName("정상동작 : Coke 객체가 정상 생성된다.")
    @ParameterizedTest
    @MethodSource("createCoke_Provider")
    void testCreateCoke(String name, Category category, int price, int kcal, int isSet) {
        //when
        Coke coke = new Coke(name, category, price, kcal, isSet);

        //then
        assertThat(coke)
                .extracting(Coke::getName, Coke::getCategory, Coke::getPrice, Coke::getKcal, Coke::isHasStraw)
                .containsExactly(name, category, price, kcal, isSet);
    }

    @DisplayName("예외처리 : 올바르지 않은 빨대 갯수가 입력된다")
    @ParameterizedTest
    @MethodSource("invalidStrawNum_Provider")
    void invalidConstructorStrawNum_BoundaryConditions(String name, Category category, int price, int kcal, int strawNum) {
        //when, then
        assertThatThrownBy(() -> new Coke(name, category, price, kcal, strawNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빨대 갯수가 잘못 입력되었습니다.");
    }

    private static Stream<Arguments> invalidStrawNum_Provider() {
        return Stream.of(
                //given
                Arguments.of("Acoke", Category.COKE, 4500, 1300, 6),
                Arguments.of("Bcoke", Category.COKE, 3000, 1200, -1)
        );
    }

    @DisplayName("정상동작 : equals")
    @ParameterizedTest
    @MethodSource("createCoke_Provider")
    void equalsTest(String name, Category category, int price, int kcal, int isSet) {
        //when
        Coke coke1 = new Coke(name, category, price, kcal, isSet);
        Coke coke2 = new Coke(name, category, price, kcal, isSet);

        boolean compare = coke1.equals(coke2);

        //then
        assertThat(compare).isTrue();
    }

    @DisplayName("정상동작 : hashCode")
    @ParameterizedTest
    @MethodSource("createCoke_Provider")
    void hashCodeTest(String name, Category category, int price, int kcal, int isSet) {
        //when
        Coke coke1 = new Coke(name, category, price, kcal, isSet);
        Coke coke2 = new Coke(name, category, price, kcal, isSet);

        //then
        assertThat(coke1.hashCode()).isEqualTo(coke2.hashCode());
    }

    private static Stream<Arguments> createCoke_Provider() {
        return Stream.of(
                //given
                Arguments.of("Acoke", Category.COKE, 4500, 1300, 0),
                Arguments.of("Bcoke", Category.COKE, 3000, 1200, 0),
                Arguments.of("Ccoke", Category.COKE, 3500, 1400, 0)
        );
    }

    @DisplayName("정상동작 : 빨대가 정상적으로 셋팅되는가?")
    @Test
    void testSyncOrderForm() {
        //given
        int strawNum = 1;
        OrderForm orderForm = new OrderForm(5);
        orderForm.setStrawNum(strawNum);
        Coke coke = new Coke("Acoke", Category.COKE, 4500, 1300, 0);

        //when
        coke.syncOrderForm(orderForm);

        //then
        assertThat(coke.isHasStraw()).isEqualTo(strawNum);
    }

    /**
     * OrderForm에서 전부 예외처리가 되어 있다.
     */
//    @DisplayName("예외처리 : 빨대 선택이 1(예), 2(아니오)를 초과하면 안된다.")
//    @Test
//    void testSyncOrderForm_BoundaryConditions() {
//        //given
//        int strawNum = 3;
//        OrderForm orderForm = new OrderForm(5);
//        //orderForm.setStrawNum(strawNum);
//        Coke coke = new Coke("Acoke", Category.COKE, 4500, 1300, 0);
//
//        //when, then
//        assertThatThrownBy(() -> coke.syncOrderForm(orderForm))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("빨대 1(예), 2(아니오)를 입력하세요.");
//    }
//
//    @DisplayName("예외처리 : 빨대 갯수가 0개 이하면 안된다.")
//    @ParameterizedTest
//    @CsvSource({
//            "0",
//            "-1"
//    })
//    void testSyncOrderFormMinus_BoundaryConditions(int strawNum) {
//        //given
//        OrderForm orderForm = new OrderForm(5);
//        orderForm.setStrawNum(strawNum);
//        Coke coke = new Coke("Acoke", Category.COKE, 4500, 1300, 0);
//
//        //when, then
//        assertThatThrownBy(() -> coke.syncOrderForm(orderForm))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("빨대 1(예), 2(아니오)를 입력하세요.");
//    }
}
