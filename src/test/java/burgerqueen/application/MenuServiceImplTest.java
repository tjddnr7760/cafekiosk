package burgerqueen.application;

import burgerqueen.AppConfig;
import burgerqueen.dto.Menu;
import burgerqueen.view.MenuService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuServiceImplTest {

    /**
     * 1. 정상동작 테스트
     *  - 메서드가 정상적인 메뉴를 반환하는지 검증한다.
     *
     * 2. 예외처리 테스트
     *  - 호출하는 메서드만 존재함으로 모든 예외처리는 컴파일 에러로 처리 가능하다.
     *
     * 3. 경계조건 테스트
     *  - 예외처리 테스트가 컴파일 에러로 처리가능함으로 경계조건 테스트는 존재하지 않는다.
     */

    @DisplayName("정상동작 : displayRepository() 메서드 호출시 동일한 Menu를 반환한다.")
    @Test
    void test_DisplayRepository() {
        //given
        AppConfig appConfig = new AppConfig();
        MenuService menuServiceImpl = appConfig.getMenuServiceImpl();

        //when
        Menu menu1 = menuServiceImpl.displayRepository();
        Menu menu2 = new Menu();

        //then
        assertThat(menu1).isEqualTo(menu2);
    }
}
