package Service;

import user.Customer;


public interface UserService {

    int choiceMenu(); // 메뉴선택

    void join(); // 회원가입
    Customer login(); // 로그인
    void findpassword(); // 비밀번호찾기
    void removeUser();
    void updateUser();


}
