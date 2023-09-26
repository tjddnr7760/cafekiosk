package burgerqueen.view;

import burgerqueen.Category;
import burgerqueen.dto.Menu;
import burgerqueen.dto.Food;

import java.util.List;
import java.util.Scanner;

public class OutputView {

// |이 코드는 메뉴를 출력하는 메소드입니다.
// |
// |좋은 점:
// |- 메뉴 객체를 파라미터로 받아서 확장성이 좋습니다.
// |- 메뉴의 카테고리, 이름, 칼로리, 가격을 깔끔하게 출력합니다.
// |- 선택지를 명확하게 표시하여 사용자가 선택하기 쉽습니다.
// |
// |나쁜 점:
// |- 메뉴의 개수가 고정되어 있어서, 메뉴가 추가되거나 삭제될 경우 코드를 수정해야 합니다.
// |- 선택지가 하드코딩되어 있어서, 선택지가 변경될 경우 코드를 수정해야 합니다.
// |- 출력하는 내용이 많아서 가독성이 떨어질 수 있습니다.
// |
// |개선할 점:
// |- 메뉴의 개수가 유동적으로 변할 수 있도록 코드를 수정합니다.
// |- 선택지를 배열이나 리스트로 관리하여 유연하게 대처할 수 있도록 합니다.
// |- 출력 내용을 줄이거나, 출력 형식을 변경하여 가독성을 높입니다.
    public void outputScreen(Menu menu) {
        List<Food> foods = menu.getFoods();

        System.out.println("BurgerQueen Order Service");
        System.out.printf("(5) %s %dkcal %d원\n", foods.get(4).getName(), foods.get(4).getKcal(), foods.get(4).getPrice());
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(60));

        System.out.printf("🍔 %s\n", foods.get(0).getCategory());
        System.out.printf("(1) %s %dkcal %d원\n", foods.get(0).getName(), foods.get(0).getKcal(), foods.get(0).getPrice());
        System.out.printf("(2) %s %dkcal %d원\n", foods.get(1).getName(), foods.get(1).getKcal(), foods.get(1).getPrice());
        System.out.println();

        System.out.printf("🍟 %s\n", foods.get(2).getCategory());
        System.out.printf("(3) %s %dkcal %d원\n", foods.get(2).getName(), foods.get(2).getKcal(), foods.get(2).getPrice());
        System.out.printf("(4) %s %dkcal %d원\n", foods.get(3).getName(), foods.get(3).getKcal(), foods.get(3).getPrice());
        System.out.println();

        System.out.printf("🥤 %s\n", foods.get(4).getCategory());
        System.out.printf("(6) %s %dkcal %d원\n", foods.get(5).getName(), foods.get(5).getKcal(), foods.get(5).getPrice());
        System.out.println();

        System.out.println("🧺 (0) 장바구니");
        System.out.println("📦 (+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.print("[📣] 메뉴를 선택해주세요 : ");
    }

    public void isSaveShoppingCart(List<Food> foods) {
        for (Food food : foods) {
            System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.", food.getName());
            System.out.println();
        }
        System.out.println();
    }

    public void burgerIsSet() {
        System.out.println("단품으로 주문하시겠어요? (1)_단품(3500원) (2)_세트(4500원)");
    }

    public void printSide(Menu menu) {
        List<Food> foods = menu.getFoods();

        System.out.println("사이드를 골라주세요");
        System.out.printf("🍟 %s\n", foods.get(2).getCategory());
        System.out.printf("(3) %s %dkcal %d원\n", foods.get(2).getName(), foods.get(2).getKcal(), foods.get(2).getPrice());
        System.out.printf("(4) %s %dkcal %d원\n", foods.get(3).getName(), foods.get(3).getKcal(), foods.get(3).getPrice());
    }

    public void printCoke(Menu menu) {
        List<Food> foods = menu.getFoods();

        System.out.println("음료를 골라주세요");
        System.out.printf("🥤 %s\n", foods.get(4).getCategory());
        System.out.printf("(5) %s %dkcal %d원\n", foods.get(4).getName(), foods.get(4).getKcal(), foods.get(4).getPrice());
        System.out.printf("(6) %s %dkcal %d원\n", foods.get(5).getName(), foods.get(5).getKcal(), foods.get(5).getPrice());
    }

    public void isKetchap() {
        System.out.println("케찹은 몇개가 필요하신가요?");
    }

                public void isStraw() {
                    System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
                }

                public void printShoppingCart(List<List<Food>> listFoods) {
                    System.out.println("🧺 장바구니");
                    System.out.println("-".repeat(60));
                    int sum = 0;

                    sum = printOrderForm(listFoods, sum);
                    System.out.printf("합계 : %d원\n", sum);
                    System.out.println("이전으로 돌아가려면 엔터를 누르세요");

                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                }

                private int printOrderForm(List<List<Food>> listFoods, int sum) {
                    for (List<Food> foods : listFoods) {
                        if (foods.get(0).getCategory() == Category.BURGER && foods.get(0).isSet()) {
                            sum += foods.get(0).getPrice() + foods.get(1).getPrice() + foods.get(2).getPrice();
                            System.out.printf(
                                    "  %s세트  %d원 (%s(케첩 %d개), %s(%s))\n", foods.get(0).getName(),
                                    foods.get(0).getPrice() + foods.get(1).getPrice() + foods.get(2).getPrice(),
                        foods.get(1).getName(), foods.get(1).getKetchup(), foods.get(2).getName()
                        , foods.get(2).isHasStraw() == 1 ? "빨대 있음" : "빨대 없음"
                        );
                continue;
            }

            for (Food food : foods) {
                if (food.getCategory() == Category.BURGER) {
                    System.out.printf("  %s     %d원 (단품)\n", food.getName(), food.getPrice());
                    sum += food.getPrice();
                } else if (food.getCategory() == Category.SIDE) {
                    System.out.printf("  %s     %d원 (케첩 %d개)\n", food.getName(), food.getPrice(), food.getKetchup());
                    sum += food.getPrice();
                } else if (food.getCategory() == Category.COKE) {
                    System.out.printf("  %s     %d원 (%s)\n", food.getName(), food.getPrice(),
                            food.isHasStraw() == 1 ? "빨대 있음" : "빨대 없음");
                    sum += food.getPrice();
                }
            }
        }

        System.out.println("-".repeat(60));
        return sum;
    }

    public void isCodeStates() {
        System.out.println("코드스테이츠 수강생이십니까? (1)_예 (2)_아니오");
    }

    public void isAge() {
        System.out.println("나이가 어떻게 되십니까?");
    }

    public void printResult(List<List<Food>> foods, int discount) {
        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        int sum = printOrderForm(foods, 0);
        System.out.printf("합계 : %d원\n", sum);
        System.out.printf("할인 적용 금액 : %d원\n", sum - discount);
        System.out.println("[📣] 이용해주셔서 감사합니다. ");
    }
}
