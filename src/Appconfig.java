import Repository.DrinkRepository;
import Repository.TextDrinkRepository;
import Repository.TextUserRepository;
import Repository.UserRepository;
import Service.AdminService;
import Service.AdminServiceimple;
import Service.UserService;
import Service.UserServiceimple;
import cart.CartServiceImple;
import cart.CartService;

public class Appconfig {
    public UserService userService(){
        return new UserServiceimple(userRepository());
    }
    public AdminService adminService(){
        return  new AdminServiceimple(drinkRepository());
    }
    public CartService cartService(){
        return  new CartServiceImple(drinkRepository());
    }

    private DrinkRepository drinkRepository() {
        return new TextDrinkRepository();
    }

    public UserRepository userRepository(){
        return new TextUserRepository();
    }

}
