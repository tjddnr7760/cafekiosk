package burgerqueen.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderFormTest {
    @DisplayName("예외동작 : 메뉴 입력이 0 ~ 5사이가 아닐경우 예외처리한다.")
    @ParameterizedTest
    @CsvSource({
            //given
            "6",
            "-1"
    })
    void orderForm_BoundaryConditions(int selectNum) {
        //when, then
        assertThatThrownBy(() -> new OrderForm(selectNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 메뉴입니다.");
    }

    @DisplayName("정상동작 : 케찹갯수가 정상적으로 저장된다.")
    @Test
    void test_SetKetchapNum() {
        //given
        int ketChapNum = 4;
        OrderForm orderForm = new OrderForm(2);

        //when
        orderForm.setKetchapNum(4);

        //then
        assertThat(orderForm.getKetchapNum()).isEqualTo(ketChapNum);
    }

    @DisplayName("예외동작 : 케찹 갯수가 음수이거나 10개 초과이면 에외처리한다.")
    @ParameterizedTest
    @CsvSource({
            "11",
            "-1"
    })
    void setKetchapNum_BoundaryConditions(int ketchapNum) {
        //given
        OrderForm orderForm = new OrderForm(2);

        //when, then
        assertThatThrownBy(() -> orderForm.setKetchapNum(ketchapNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("켓찹 갯수가 잘못되었습니다.");
    }

    @DisplayName("정상동작 : 빨대갯수가 정상적으로 저장된다.")
    @Test
    void test_SetStrawNum() {
        //given
        int strawNum = 1;
        OrderForm orderForm = new OrderForm(4);

        //when
        orderForm.setStrawNum(1);

        //then
        assertThat(orderForm.getStrawNum()).isEqualTo(strawNum);
    }

    @DisplayName("예외동작 : 빨대갯수가 음수이거나 5개 초과이면 에외처리한다.")
    @ParameterizedTest
    @CsvSource({
            "6",
            "-1"
    })
    void setStrawNum_BoundaryConditions(int strawNum) {
        //given
        OrderForm orderForm = new OrderForm(4);

        //when, then
        assertThatThrownBy(() -> orderForm.setStrawNum(strawNum))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빨대 1(예), 2(아니오)를 입력하세요.");
    }

    @DisplayName("정상동작 : 사이드가 정상적으로 저장된다.")
    @Test
    void test_SetSetSide() {
        //given
        int side = 2;
        OrderForm orderForm = new OrderForm(0);
        orderForm.setSet(true);

        //when
        orderForm.setSetSide(2);

        //then
        assertThat(orderForm.getSetSide()).isEqualTo(side);
    }

    @DisplayName("예외동작 : 사이드를 선택하지 않으면 예외처리한다.")
    @ParameterizedTest
    @CsvSource({
            "1",
            "4"
    })
    void setSetSide_BoundaryConditions(int setSide) {
        //given
        OrderForm orderForm = new OrderForm(0);
        orderForm.setSet(true);

        //when, then
        assertThatThrownBy(() -> orderForm.setSetSide(setSide))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사이드 메뉴가 잘못 입력되었습니다.");
    }

    @DisplayName("정상동작 : 콜라가 정상적으로 저장된다.")
    @Test
    void test_SetSetCoke() {
        //given
        int coke = 4;
        OrderForm orderForm = new OrderForm(0);
        orderForm.setSet(true);

        //when
        orderForm.setSetCoke(4);

        //then
        assertThat(orderForm.getSetCoke()).isEqualTo(coke);
    }

    @DisplayName("예외동작 : 사이드를 선택하지 않으면 예외처리한다.")
    @ParameterizedTest
    @CsvSource({
            "3",
            "6"
    })
    void setSetCoke_BoundaryConditions(int setCoke) {
        //given
        OrderForm orderForm = new OrderForm(0);
        orderForm.setSet(true);

        //when, then
        assertThatThrownBy(() -> orderForm.setSetCoke(setCoke))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("콜라 메뉴가 잘못 입력되었습니다.");
    }
}
