package Service;

import user.Customer;


public interface UserService {

    int choiceMenu();

    void join(); // 회원가입
    Customer login();
    void findpassword();

}
