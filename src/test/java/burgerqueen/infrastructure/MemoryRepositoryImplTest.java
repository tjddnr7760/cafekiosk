package burgerqueen.infrastructure;

import burgerqueen.Category;
import burgerqueen.application.MemoryRepository;
import burgerqueen.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryRepositoryImplTest {

    /**
     * 1. 정상동작 테스트
     * - 레포지토리에서 실행하는 메서드가 올바른 값을 리턴하는지 검증한다.
     * <p>
     * 2. 예외처리 테스트
     * - 레포지토리에 접근하는 모든 경우가 컴파일 에러로 예외처리 된다.
     * <p>
     * 3. 경계조건 테스트
     * - 예외처리가 모두 컴파일 에러로 처리됨으로 경계조건 테스트는 없다.
     */

    @DisplayName("정상동작 : displayMenu() 레포지토리에서 호출시 생성되는 Menu객체가 Menu객체와 동일한지 검증한다.")
    @Test
    void test_DisplayMenu() {
        //given
        MemoryRepositoryImpl memoryRepositoryImpl = new MemoryRepositoryImpl();
        Menu menu = new Menu();

        //when
        Menu menu1 = memoryRepositoryImpl.displayMenu();
        Menu menu2 = menu.getAllMenu();

        //then
        assertThat(menu1).isEqualTo(menu2);
    }

    @DisplayName("정상동작 : getFood() OrderFrom에 저장된 Food객체 정상 리턴되는지 검증한다.")
    @ParameterizedTest
    @MethodSource("getFood_Provider")
    void test_GetFood(Food food, int index) {
        //given
        OrderForm orderForm = new OrderForm(index);
        MemoryRepository memoryRepository = new MemoryRepositoryImpl();

        //when
        Food getFood = memoryRepository.getFood(orderForm);

        //then
        assertThat(getFood).isEqualTo(food);
    }

    @DisplayName("정상동작 : getFoodByIndex() 인덱스로 올바른 Food를 리턴한다.")
    @ParameterizedTest
    @MethodSource("getFood_Provider")
    void test_GetFoodByIndex(Food food, int index) {
        //given
        MemoryRepository memoryRepository = new MemoryRepositoryImpl();

        //when
        Food getFood = memoryRepository.getFoodByIndex(index);

        //then
        assertThat(getFood).isEqualTo(food);
    }

    private static Stream<Arguments> getFood_Provider() {
        return Stream.of(
                Arguments.of(new Burger("새우버거", Category.BURGER, 3500, 500, false), 0),
                Arguments.of(new Burger("치킨버거", Category.BURGER, 4000, 600, false), 1),
                Arguments.of(new Side("감자튀김", Category.SIDE, 1000, 300, 0), 2),
                Arguments.of(new Side("어니언링", Category.SIDE, 1000, 300, 7), 3),
                Arguments.of(new Coke("코카콜라", Category.COKE, 1000, 200, 0), 4),
                Arguments.of(new Coke("제로콜라", Category.COKE, 1500, 0, 0), 5)
        );
    }

    @DisplayName("정상동작 : getFoods() 리스트를 정상적으로 반환한다.")
    @ParameterizedTest
    @MethodSource("getFoods_Provider")
    void test_GetFoods(List<Food> foods) {
        //given
        MemoryRepository memoryRepository = new MemoryRepositoryImpl();

        //when
        List<Food> getFoods = memoryRepository.getFoods();

        //then
        assertThat(getFoods).isEqualTo(foods);
    }

    private static Stream<Arguments> getFoods_Provider() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Burger("새우버거", Category.BURGER, 3500, 500, false),
                                new Burger("치킨버거", Category.BURGER, 4000, 600, false),
                                new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                                new Side("어니언링", Category.SIDE, 1000, 300, 7),
                                new Coke("코카콜라", Category.COKE, 1000, 200, 0),
                                new Coke("제로콜라", Category.COKE, 1500, 0, 0)
                        )
                )
        );
    }
}
