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

public class FoodTest {

    /**
     * 기능 테스트
     * *
     * * 정상 동작 테스트
     * * 1. 원하는 함수가 정상적으로 동작하는지 테스트한다.
     * *
     * * 예외 테스트
     * * 1. 비즈니스 로직상에서 런타임 에러시 발생해야 하는 에러를 명확히 정하고 테스트한다.
     * * 2. 컴파일 에러는 테스트하지 않는다.
     * *
     * * 경계조건 테스트
     * * 1. 정상 동작 테스트와 예외 테스트 사이에서 경계조건 값과 에러 값을 테스트하여 경계조건을 테스트한다.
     * *
     * * 모의객체 사용법을 익힌다.
     */

    /**
     * 1. 정상 동작 테스트
     * - 객체 생성 잘 되는지
     * - 게터 잘 동작 하는지
     * - 오버라이딩한 equals함수 잘 동작 하는지
     * - 오버라이딩한 hashCode함수 잘 동작 하는지
     */

    @DisplayName("정상동작 : 객체생성, 게터")
    @Test
    void createFood2() {
        //given
        String name = "Burgers";
        Category category = Category.BURGER;
        int price = 4500;
        int kcal = 1200;

        //when
        Food food = new Food(name, category, price, kcal);

        //then
        assertThat(food).isNotNull();
        assertThat(food.getName()).isEqualTo(name);
        assertThat(food.getCategory()).isEqualTo(category);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getKcal()).isEqualTo(kcal);
    }

    @DisplayName("정상동작 : 객체생성, 게터")
    @Test
    void createFood_Extracting() {
        //given
        String name = "GBurger";
        Category category = Category.BURGER;
        int price = 5500;
        int kcal = 1400;

        //when
        Food food = new Food(name, category, price, kcal);

        //then
        assertThat(food)
                .extracting(Food::getName, Food::getCategory, Food::getPrice, Food::getKcal)
                .containsExactly(name, category, price, kcal);
    }

    @DisplayName("정상동작 : 객체생성, 게터")
    @ParameterizedTest
    @CsvSource({
            //given
            //CsvSource 열거형 사용시에는 열거형 이름만 적어주면 된다.
            "Aside, SIDE, 1000, 500",
            "Bside, SIDE, 1000, 500",
            "Cside, SIDE, 1000, 600",
            "Dside, SIDE, 1000, 700"
    })
    void createFood_Csv(String name, Category category, int price, int kcal) {
        //when
        Food food = new Food(name, category, price, kcal);

        //then
        assertThat(food)
                .extracting(Food::getName, Food::getCategory, Food::getPrice, Food::getKcal)
                .containsExactly(name, category, price, kcal);
    }


    @DisplayName("정상동작 : 객체생성, 게터사용")
    @ParameterizedTest
    @MethodSource("provideMethod")
    void createFood_Method(String name, Category category, int price, int kcal) {
        //when
        Food food = new Food(name, category, price, kcal);

        //then
        assertThat(food)
                .extracting(Food::getName, Food::getCategory, Food::getPrice, Food::getKcal)
                .containsExactly(name, category, price, kcal);
    }

    private static Stream<Arguments> provideMethod() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.SIDE, 2500, 1300),
                Arguments.of("Cburger", Category.SIDE, 3500, 1300)
        );
    }

    @DisplayName("정상동작 : equals()")
    @ParameterizedTest
    @MethodSource("provideEquals")
    void equalsTest(String name, Category category, int price, int kcal) {
        //when
        Food food = new Food(name, category, price, kcal);
        Food food2 = new Food(name, category, price, kcal);

        boolean compare = food.equals(food2);

        //then
        assertThat(compare).isTrue();
    }

    private static Stream<Arguments> provideEquals() {
        return Stream.of(
                //given
                Arguments.of("ABurger", Category.BURGER, 5000, 1300),
                Arguments.of("BSide", Category.SIDE, 2000, 150)
        );
    }

    @DisplayName("정상동작 : hashCode()")
    @ParameterizedTest
    @MethodSource("provideHash")
    void hashCodeTest(String name, Category category, int price, int kcal) {
        //when
        Food food = new Food(name, category, price, kcal);
        Food food2 = new Food(name, category, price, kcal);

        //then
        assertThat(food.hashCode()).isEqualTo(food2.hashCode());
    }

    private static Stream<Arguments> provideHash() {
        return Stream.of(
                //given
                Arguments.of("Dburger", Category.BURGER, 4500, 1300),
                Arguments.of("Jburger", Category.BURGER, 5000, 1300)
        );
    }

    /**
     * 예외 테스트
     * Food 객체 생성지 매개변수에 대한 예외처리
     * 1. name 이 null 혹은 빈 값일때
     * 2. category가 null 일때
     * 3. price < 0 일때
     * 4. kcal < 0 일때
     */

    @DisplayName("비즈니스로직 예외 : 올바르지 않은 생성자 매개변수로 Food객체 생성시")
    @ParameterizedTest
    @MethodSource("provideConstructorParms")
    void exception_Constructor_Parms(String name, Category category, int price, int kcal) {
        //when, then
        assertThatThrownBy(() -> new Food(name, category, price, kcal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food 객체 생성 오류");
    }

    private static Stream<Arguments> provideConstructorParms() {
        return Stream.of(
                //given
                //예외처리 테스트를 작성하면서 매개변수 넘겨줄때, 컴파일 에러는 이미 걸리지는
                //예외이기 때문에 별도로 처리해주지 않고 런타임 동작에 있어 비즈니스 로직에 영향을 주는
                //예외 상황을 테스트 한다.
                Arguments.of(null, Category.BURGER, 4500, 1200), // null name
                Arguments.of("", Category.BURGER, 4500, 1200),

                //Food객체의 매개변수가 Category열거형 이기 때문에 문자열은 컴파일 에러로 처리된다.
                Arguments.of("Bonion", null, 1200, 200),
                //new Object[]{"Bonion", "", 1200, 200}, // empty category
                //new Object[]{"Bonion", "what", 1200, 200}, // String category

                //int와 integer는 null과 빈값과 String을 받을 수 없기 때문에 컴파일 에러로 처리된다.
                //new Object[]{"Aburger", Category.BURGER, null, 1300}, // null price
                //new Object[]{"Aburger", Category.BURGER, "", 1300}, // empty price
                Arguments.of("Aburger", Category.BURGER, -2000, 1300),
                //new Object[]{"Aburger", Category.BURGER, "what", 1300}, //String price

                //int와 integer는 null과 빈값과 String을 받을 수 없기 때문에 컴파일 에러로 처리된다.
                //new Object[]{"Ccoke", Category.COKE, 1000, null}, // null kcal
                //new Object[]{"Ccoke", Category.COKE, 1000, ""}, // empty kcal
                Arguments.of("Ccoke", Category.COKE, 1000, -50)
                //new Object[]{"Ccoke", Category.COKE, 1000, "what"} //String kcal
        );
    }

    /**
     * 경계조건 테스트
     * 정상 동작 값과 경계값 사이의 테스트를 진행한다.
     * 1. price < 0 이면 안됨으로 0과 -1일때 정상 동작하는지 확인한다.
     * 2. price 최댓값을 100_000_000으로 정하고 1억인 경우와 1억 1인 경우를 테스트 한다.
     * 3. kcal <0 이면 안됨으로 0과 -1일때 정상 동작하는지 확인한다.
     * 4. kcal 최댓값을 5000으로 정하고 5000인 경우와 5001인 경우를 테스트 한다.
     */

    @DisplayName("경계조건 : price가 0인 경우 정상 동작한다.")
    @ParameterizedTest
    @MethodSource("priceProvider")
    void testPirceZero_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when,
        Food food = new Food(name, category, price, kcal);

        // then
        assertThat(food).isNotNull();
    }

    private static Stream<Arguments> priceProvider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 0, 1300),
                Arguments.of("Bburger", Category.BURGER, 0, 1300),
                Arguments.of("Cburger", Category.BURGER, 0, 1300)
        );
    }

    @DisplayName("경계조건 : price가 -1인 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @MethodSource("priceMinusOne_Provider")
    void testPriceMinusOne_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when, then
        assertThatThrownBy(() -> {
            new Food(name, category, price, kcal);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food 객체 생성 오류");

    }

    private static Stream<Arguments> priceMinusOne_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, -1, 1300),
                Arguments.of("Bburger", Category.BURGER, -1, 1500),
                Arguments.of("Cburger", Category.BURGER, -1, 1600)
        );
    }

    @DisplayName("경계조건 : price의 최댓값 100_000_000으로 객체 생성시 정상 동작한다.")
    @ParameterizedTest
    @CsvSource({
            //given
            "\"Aburger\", BURGER, 100_000_000, 1300",
            "\"Bburger\", BURGER, 100_000_000, 1500"
    })
    void testPriceMax_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when
        Food food = new Food(name, category, price, kcal);

        //then
        assertThat(food).isNotNull();
    }

    @DisplayName("경계조건 : price 최댓값을 넘어선 100_000_001로 객체 생성시 예외처리한다.")
    @ParameterizedTest
    @MethodSource("priceMaxPlusOne_Provider")
    void testPriceMaxPlusOne_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when, then
        assertThatThrownBy(() -> {
            new Food(name, category, price, kcal);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food 객체 생성 오류");
    }

    private static Stream<Arguments> priceMaxPlusOne_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 100_000_001, 1300),
                Arguments.of("Bburger", Category.BURGER, 100_000_001, 1300),
                Arguments.of("Cburger", Category.BURGER, 100_000_001, 1300)
        );
    }

    @DisplayName("경계조건 : kcal가 0일때 정상동작한다.")
    @ParameterizedTest
    @MethodSource("kcalZero_Provider")
    void testKcalZero_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when,
        Food food = new Food(name, category, price, kcal);

        // then
        assertThat(food).isNotNull();
    }

    private static Stream<Arguments> kcalZero_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 3000, 0),
                Arguments.of("Bburger", Category.BURGER, 4000, 0),
                Arguments.of("Cburger", Category.BURGER, 5000, 0)
        );
    }

    @DisplayName("경게조건 : kcal가 -1인경우 예외처리한다.")
    @ParameterizedTest
    @MethodSource("kcalMinusOne_Provider")
    void testKcalMinusOne_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when, then
        assertThatThrownBy(() -> {
            new Food(name, category, price, kcal);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food 객체 생성 오류");
    }

    private static Stream<Arguments> kcalMinusOne_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 3000, -1),
                Arguments.of("Aburger", Category.BURGER, 3000, -1),
                Arguments.of("Aburger", Category.BURGER, 3000, -1)
        );
    }

    @DisplayName("경게조건 : kcal가 5000인경우 정상동작 한다.")
    @ParameterizedTest
    @MethodSource("kcalMax_Provider")
    void testKcalMax_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when,
        Food food = new Food(name, category, price, kcal);

        // then
        assertThat(food).isNotNull();
    }

    private static Stream<Arguments> kcalMax_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 3000, 5000),
                Arguments.of("Aburger", Category.BURGER, 3000, 5000),
                Arguments.of("Aburger", Category.BURGER, 3000, 5000)
        );
    }

    @DisplayName("경게조건 : kcal가 5001인경우 예외처리 한다.")
    @ParameterizedTest
    @MethodSource("kcalMaxPlusOne_Provider")
    void testKcalMaxPlusOne_BoundaryConditions(String name, Category category, int price, int kcal) {
        //when, then
        assertThatThrownBy(() -> {
            new Food(name, category, price, kcal);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food 객체 생성 오류");
    }

    private static Stream<Arguments> kcalMaxPlusOne_Provider() {
        return Stream.of(
                //given
                Arguments.of("Aburger", Category.BURGER, 3000, 5001),
                Arguments.of("Aburger", Category.BURGER, 3000, 5001),
                Arguments.of("Aburger", Category.BURGER, 3000, 5001)
        );
    }
}