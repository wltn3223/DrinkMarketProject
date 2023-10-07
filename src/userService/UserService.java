package userService;

import user.Customer;
import userRepository.TextUserRepository;
import userRepository.UserRepository;

import java.util.ArrayList;

public interface UserService {

    ArrayList<String> join(ArrayList<String> userinfo); // 회원가입
    Customer login();
    String findpassword(String id);

}
