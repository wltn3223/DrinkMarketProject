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

public class Appconfig { // 모든 서비스 생성 관리 // 서비스들이 어떤 저장소를 이용할지 결정
    public UserService userService(){ // 유저서비스는 유저저장소 이용
        return new UserServiceimple(userRepository());
    }
    public AdminService adminService(){ // 관리자서비스는 유저저장소와, 음료저장소이용
        return  new AdminServiceimple(drinkRepository(),userRepository());
    }
    public CartService cartService(){ // 카트서비스는 음료저장소이용
        return  new CartServiceImple(drinkRepository());
    }

    private DrinkRepository drinkRepository() {
        return new TextDrinkRepository();
    }

    public UserRepository userRepository(){
        return new TextUserRepository();
    }

}
