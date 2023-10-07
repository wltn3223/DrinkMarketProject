import Repository.TextUserRepository;
import Service.UserService;
import Service.UserServiceimple;

public class Appconfig {
    public UserService userService(){
        return new UserServiceimple(userRepository());
    }
    public TextUserRepository userRepository(){
        return new TextUserRepository();
    }

}
