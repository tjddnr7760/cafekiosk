package burgerqueen.application;

import burgerqueen.dto.Food;
import burgerqueen.dto.OrderForm;
import burgerqueen.view.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private MemoryRepository memoryRepository;
    private Order order;
    private List<Food> orderList;

    public OrderServiceImpl(MemoryRepository memoryRepository, Order order) {
        this.memoryRepository = memoryRepository;
        this.order = order;
    }

    @Override
    public List<Food> saveShoppingCart(OrderForm orderForm) {
        orderList = new ArrayList<>();

        if (orderForm.isSet()) {
            Food Burger = memoryRepository.getFoodByIndex(orderForm.getSelectNum());
            Food Side = memoryRepository.getFoodByIndex(orderForm.getSetSide());
            Food Coke = memoryRepository.getFoodByIndex(orderForm.getSetCoke());

            //동기화
            Burger.syncOrderForm(orderForm);
            Side.syncOrderForm(orderForm);
            Coke.syncOrderForm(orderForm);

            orderList.add(Burger);
            orderList.add(Side);
            orderList.add(Coke);
        }

        if (!orderForm.isSet()) {
            Food food = getFood(orderForm);
            food.syncOrderForm(orderForm);
            orderList.add(food);
        }

        return saveFoodInShoppingCart(orderList);
    }

    private Food getFood(OrderForm orderForm) {
        return memoryRepository.getFood(orderForm);
    }

    private List<Food> getFoods() {
        return memoryRepository.getFoods();
    }

    private List<Food> saveFoodInShoppingCart(List<Food> foods) {
        return order.saveFoodInShoopingCart(foods);
    }

    @Override
    public int finalOrder(int isCodeStates, int age) {
        return order.calculateDiscount(isCodeStates, age);
    }
}
