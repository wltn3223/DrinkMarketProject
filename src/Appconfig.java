import Repository.TextUserRepository;
import userService.UserService;
import userService.UserServiceimple;

public class Appconfig {
    public UserService userService(){
        return new UserServiceimple(userRepository());
    }
    public TextUserRepository userRepository(){
        return new TextUserRepository();
    }

}
