package userService;

import user.Customer;
import userRepository.TextUserRepository;
import userRepository.UserRepository;

import java.util.ArrayList;

public interface UserService {

    void join(); // 회원가입
    Customer login();
    void findpassword();

}
