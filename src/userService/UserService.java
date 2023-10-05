package userService;

import user.Customer;
import userRepository.TextUserRepository;
import userRepository.UserRepository;

public interface UserService {

    void join(); // 회원가입
    Customer login();
    String findpassword(String id);

}
