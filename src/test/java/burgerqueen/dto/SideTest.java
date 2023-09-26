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

public class SideTest {

    /**
     * 1. 정상 동작
     * - 객체 정상 생성
     * - equals함수 정상 동작
     * - hashCode 함수 정상 동작
     * <p>
     * 2. 예외 처리
     * - 케찹수가 0보다 작을시 예외
     * - 케찹수가 10보타 클시 예외
     * <p>
     * 3. 경계조건
     * - 케찹이 0인경우 통과
     * - 케찹이 -1인경우 실패
     * - 케찹이 10인경우 통과
     * - 케찹이 11인경우 실패
     */

    @DisplayName("정상동작 : Side 객체가 정상 생성된다.")
    @ParameterizedTest
    @MethodSource("createSide_Provider")
    void testCreateSide(String name, Category category, int price, int kcal, int ketchap) {
        //when
        Side side = new Side(name, category, price, kcal, ketchap);

        //then
        assertThat(side)
                .extracting(Side::getName, Side::getCategory, Side::getPrice, Side::getKcal, Side::getKetchup)
                .containsExactly(name, category, price, kcal, ketchap);
    }

    @DisplayName("정상동작 : equals")
    @ParameterizedTest
    @MethodSource("createSide_Provider")
    void equalsTest(String name, Category category, int price, int kcal, int ketchap) {
        //when
        Side side1 = new Side(name, category, price, kcal, ketchap);
        Side side2 = new Side(name, category, price, kcal, ketchap);

        boolean compare = side1.equals(side2);

        //then
        assertThat(compare).isTrue();
    }

    @DisplayName("정상동작 : hashCode")
    @ParameterizedTest
    @MethodSource("createSide_Provider")
    void hashCodeTest(String name, Category category, int price, int kcal, int ketchap) {
        //when
        Side side1 = new Side(name, category, price, kcal, ketchap);
        Side side2 = new Side(name, category, price, kcal, ketchap);

        //then
        assertThat(side1.hashCode()).isEqualTo(side2.hashCode());
    }

    private static Stream<Arguments> createSide_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aside", Category.SIDE, 4500, 1300, 10),
                Arguments.of("Bside", Category.SIDE, 3000, 1200, 1),
                Arguments.of("Cside", Category.SIDE, 3500, 1400, 3)
        );
    }

    @DisplayName("비즈니스로직 예외 : 케찹의 갯수가 음수인 경우")
    @ParameterizedTest
    @MethodSource("ketchapMinus_Provider")
    void testKetchapMinus(String name, Category category, int price, int kcal, int ketchap) {
        //when, then
        assertThatThrownBy(() -> new Side(name, category, price, kcal, ketchap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("케찹 갯수가 잘못 입력되엇습니다.");
    }

    private static Stream<Arguments> ketchapMinus_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aside", Category.SIDE, 2500, 700, -1),
                Arguments.of("Bside", Category.SIDE, 2000, 300, -3),
                Arguments.of("Cside", Category.SIDE, 2200, 500, -25)
        );
    }

    @DisplayName("비즈니스로직 예외 : 케찹의 갯수가 10개 초과인 경우")
    @ParameterizedTest
    @MethodSource("ketchapMaxPlusOne_Provider")
    void testKetchapMaxPlusOne(String name, Category category, int price, int kcal, int ketchap) {
        //when, then
        assertThatThrownBy(() -> new Side(name, category, price, kcal, ketchap))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("케찹 갯수가 잘못 입력되엇습니다.");
    }

    private static Stream<Arguments> ketchapMaxPlusOne_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aside", Category.SIDE, 2500, 700, 11),
                Arguments.of("Bside", Category.SIDE, 2000, 300, 15),
                Arguments.of("Cside", Category.SIDE, 2200, 500, 50)
        );
    }

    @DisplayName("경계조건 : 케찹의 갯수가 0인경우 통과")
    @ParameterizedTest
    @MethodSource("ketChapZero_Provider")
    void testKetchapZero(String name, Category category, int price, int kcal, int ketchap) {
        //when
        Side side = new Side(name, category, price, kcal, ketchap);

        //then
        assertThat(side)
                .extracting(Side::getName, Side::getCategory, Side::getPrice, Side::getKcal, Side::getKetchup)
                .containsExactly(name, category, price, kcal, ketchap);
    }

    private static Stream<Arguments> ketChapZero_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aside", Category.SIDE, 2500, 700, 0),
                Arguments.of("Bside", Category.SIDE, 2000, 300, 0),
                Arguments.of("Cside", Category.SIDE, 2200, 500, 0)
        );
    }

    @DisplayName("경계조건 : 케찹의 갯수가 10인경우 통과")
    @ParameterizedTest
    @MethodSource("ketChapMax_Provider")
    void testKetchapMax(String name, Category category, int price, int kcal, int ketchap) {
        //when
        Side side = new Side(name, category, price, kcal, ketchap);

        //then
        assertThat(side)
                .extracting(Side::getName, Side::getCategory, Side::getPrice, Side::getKcal, Side::getKetchup)
                .containsExactly(name, category, price, kcal, ketchap);
    }

    private static Stream<Arguments> ketChapMax_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aside", Category.SIDE, 2500, 700, 10),
                Arguments.of("Bside", Category.SIDE, 2000, 300, 10),
                Arguments.of("Cside", Category.SIDE, 2200, 500, 10)
        );
    }

    @DisplayName("정상동작 : 케찹 동기화가 정상적으로 셋팅된다.")
    @Test
    void syncOrderFormKetchap() {
        //given
        int ketChap = 4;
        OrderForm orderForm = new OrderForm(3);
        orderForm.setKetchapNum(ketChap);
        Side side = new Side("Aside", Category.SIDE, 2500, 700, 0);

        //when
        side.syncOrderForm(orderForm);

        //then
        assertThat(side.getKetchup()).isEqualTo(ketChap);
    }

//    @DisplayName("예외처리 : 케찹갯수가 잘못입력되어 동기화가 처리되지 않는다.")
//    @ParameterizedTest
//    @CsvSource({
//            "11",
//            "-1"
//    })
//    void syncOrderFormKetchap_BoundaryConditions(int ketChap) {
//        //given
//        OrderForm orderForm = new OrderForm(3);
//        orderForm.setKetchapNum(ketChap); // 케찹의 수 설정
//        Side side = new Side("Aside", Category.SIDE, 2500, 700, 0);
//
//        //when, then
//        assertThatThrownBy(() -> side.syncOrderForm(orderForm))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("케찹 갯수가 잘못 입력되엇습니다.");
//    }
}
