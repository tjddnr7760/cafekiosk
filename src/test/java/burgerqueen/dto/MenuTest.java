package burgerqueen.dto;

import burgerqueen.Category;
import burgerqueen.infrastructure.MemoryRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    /**
     * 1. 정상 동작 테스트
     *  - 메뉴가 정상적으로 들어있는지 테스트한다.
     *
     * 2. 에외 테스트
     *  - Menu 클래스는 조작할수이는 셋메서드가 없음으로 컴파일 에러로 모두 처리할 수 있다.
     *
     * 3. 경계조건 테스트
     *  - 예외가 전부 컴파일 에러임으로 경계조건 테스트는 없다.
     */

    @DisplayName("정상동작 : getFoods() 올바른 리스트 메뉴를 반환하는지 검증한다.")
    @ParameterizedTest
    @MethodSource("getFoods_Provider")
    void testMenu_getFoods(List<Food> streamFoods) {
        //when
        Menu menu = new Menu();
        List<Food> foods = menu.getFoods();

        //then
        assertThat(foods.size()).isEqualTo(streamFoods.size());
        assertThat(foods).isEqualTo(streamFoods);
    }

    private static Stream<Arguments> getFoods_Provider() {
        //given
        List<Food> streamFoods = List.of(
                new Burger("새우버거", Category.BURGER, 3500, 500, false),
                new Burger("치킨버거", Category.BURGER, 4000, 600, false),
                new Side("감자튀김", Category.SIDE, 1000, 300, 0),
                //new Side("감자튀김", Category.SIDE, 1000, 200, 10),
                new Side("어니언링", Category.SIDE, 1000, 300, 7),
                new Coke("코카콜라", Category.COKE, 1000, 200, 0),
                new Coke("제로콜라", Category.COKE, 1500, 0, 0)
        );

        return Stream.of(
                Arguments.of(streamFoods)
        );
    }

    @DisplayName("정상동작 : getAllMenu()가 자신의 객체를 리턴하는지 검증한다.")
    @Test
    void testMenu_getAllMenu() {
        //given
        MemoryRepositoryImpl memoryRepositoryImpl = new MemoryRepositoryImpl();
        Menu menu = new Menu();

        //when
        Menu repoMenu = memoryRepositoryImpl.displayMenu();
        Menu originalMenu = menu.getAllMenu();

        //then
        assertThat(repoMenu).isEqualTo(originalMenu);
    }

    @DisplayName("정상동작 : equals()")
    @Test
    void test_Equals() {
        //given
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();

        //when
        boolean compare = menu1.equals(menu2);

        //then
        assertThat(compare).isTrue();
    }

    @DisplayName("정상동작 : hashCode()")
    @Test
    void test_HashCode() {
        //given
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();

        //when, then
        assertThat(menu1.hashCode()).isEqualTo(menu2.hashCode());
    }
}
