package burgerqueen.application;

import burgerqueen.dto.Menu;
import burgerqueen.view.MenuService;

public class MenuServiceImpl implements MenuService {

    private MemoryRepository memoryRepository;

    public MenuServiceImpl(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    @Override
    public Menu displayRepository() {
        return memoryRepository.displayMenu();
    }
}
