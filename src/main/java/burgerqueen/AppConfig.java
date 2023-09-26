package burgerqueen;

import burgerqueen.application.*;
import burgerqueen.domain.*;
import burgerqueen.infrastructure.MemoryRepositoryImpl;
import burgerqueen.view.Controller;
import burgerqueen.view.MenuService;
import burgerqueen.view.OrderService;
import burgerqueen.view.ShoppingCartService;

public class AppConfig {

    private MemoryRepository memoryRepository = new MemoryRepositoryImpl();
    private MenuService menuService = new MenuServiceImpl(getMemoryRepositoryImpl());

    private Discount cozDisCount = new CozDiscount();
    private Discount kizDisCount = new KizDiscount();

    private Order cozOrder = new OrderImpl(cozDisCount);
    private Order kizOrder = new OrderImpl(kizDisCount);

    private OrderService orderService = new OrderServiceImpl(getMemoryRepositoryImpl(), getCozOrderImpl());
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl(getCozOrderImpl());

    public MemoryRepository getMemoryRepositoryImpl() {
        return this.memoryRepository;
    }

    public MenuService getMenuServiceImpl() {
        return this.menuService;
    }

    public Order getCozOrderImpl() {
        return this.cozOrder;
    }

    public Order getKizOrderImpl() {
        return this.kizOrder;
    }

    public OrderService getOrderServiceImpl() {
        return this.orderService;
    }

    public ShoppingCartService getShoppingCartServiceImpl() {
        return this.shoppingCartService;
    }

    public Controller getController() {
        return new Controller(getMenuServiceImpl(), getOrderServiceImpl(), getShoppingCartServiceImpl());
    }
}