package burgerqueen.domain;

public class KizDiscount implements Discount {

    @Override
    public int howMuchDiscount(int isCodestaes, int age, int sum) {
        validateKizDiscount(isCodestaes, age);

        if (age < 20) {
            return 500;
        } else {
            return 0;
        }
    }

    private void validateKizDiscount(int isCodestaes, int age) {
        if (isCodestaes < 1 || isCodestaes > 2) {
            throw new IllegalArgumentException("수강정보 혹은 나이가 잘못 입력되었습니다.");
        }

        if (age < 1) {
            throw new IllegalArgumentException("수강정보 혹은 나이가 잘못 입력되었습니다.");
        }
    }
}
