package burgerqueen.dto;

public class OrderForm {

    private int selectNum = 0;
    private int ketchapNum = 0;
    private int strawNum = 0;

    private boolean isSet = false;
    private int setSide = 0;
    private int setCoke = 0;

    public OrderForm(int selectNum) {
        validateSelectNum(selectNum);
        this.selectNum = selectNum;
    }

    private void validateSelectNum(int selectNum) {
        if (selectNum < 0 || selectNum > 5) {
            throw new IllegalArgumentException("올바르지 않은 메뉴입니다.");
        }
    }

    public int getSelectNum() {
        return selectNum;
    }

    public int getKetchapNum() {
        return ketchapNum;
    }

    public void setKetchapNum(int ketchapNum) {
        validateKetchapNum(ketchapNum);
        this.ketchapNum = ketchapNum;
    }

    private void validateKetchapNum(int ketchapNum) {
        if (ketchapNum < 0 || ketchapNum > 10) {
            throw new IllegalArgumentException("켓찹 갯수가 잘못되었습니다.");
        }
    }

    public int getStrawNum() {
        return strawNum;
    }

    public void setStrawNum(int strawNum) {
        validateStrawNum(strawNum);
        this.strawNum = strawNum;
    }

    private void validateStrawNum(int strawNum) {
        if (strawNum < 1 || strawNum > 2) {
            throw new IllegalArgumentException("빨대 1(예), 2(아니오)를 입력하세요.");
        }
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public int getSetSide() {
        return setSide;
    }

    public void setSetSide(int setSide) {
        validateSetSide(setSide);
        this.setSide = setSide;
    }

    private void validateSetSide(int setSide) {
        if (setSide < 2 || setSide > 3) {
            throw new IllegalArgumentException("사이드 메뉴가 잘못 입력되었습니다.");
        }
    }

    public int getSetCoke() {
        return setCoke;
    }

    public void setSetCoke(int setCoke) {
        validateSetCoke(setCoke);
        this.setCoke = setCoke;
    }

    private void validateSetCoke(int setCoke) {
        if (setCoke < 4 || setCoke > 5) {
            throw new IllegalArgumentException("콜라 메뉴가 잘못 입력되었습니다.");
        }
    }
}
