package Service;

import user.Customer;

public interface UserService {

    void join(); // 회원가입
    Customer login();
    void findpassword();

}
