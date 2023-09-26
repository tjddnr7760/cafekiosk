package burgerqueen.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KizDiscountTest {
    @DisplayName("정상동작 : 500원 할인이 정상적으로 계산된다.")
    @ParameterizedTest
    @CsvSource({
            //given
            "1, 22, 5000, 0",
            "1, 17, 5500, 500",
            "2, 20, 4500, 0",
            "2, 18, 6000, 500"
    })
    void test_kizHowMuchDiscount(int isCodeStates, int age, int sum, int answer) {
        //when
        Discount discount = new KizDiscount();
        int discountValue = discount.howMuchDiscount(isCodeStates, age, sum);

        //then
        assertThat(discountValue).isEqualTo(answer);
    }

    @DisplayName("예외동작 : 나이가 0살 이하이거나 코드 스테이츠가 1 혹은 2가 아니다.")
    @ParameterizedTest
    @CsvSource({
            //given
            "0, 22, 5000, 500",
            "3, 26, 5500, 550",
            "1, 0, 4500, 0",
            "1, -1, 6000, 0"
    })
    void kizHowMuchDiscount_BoundaryConditions(int isCodeStates, int age, int sum, int answer) {
        //when
        Discount discount = new KizDiscount();

        //then
        assertThatThrownBy(() -> {
            int discountValue = discount.howMuchDiscount(isCodeStates, age, sum);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강정보 혹은 나이가 잘못 입력되었습니다.");
    }
}
