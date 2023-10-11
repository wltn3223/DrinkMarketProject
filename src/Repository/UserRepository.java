package Repository;

import user.Customer;

import java.util.ArrayList;


public interface UserRepository {
    void save(ArrayList<String> info, boolean flag); // txt파일에 덮어쓸지 말지 flag로 결정

    
    void loadUserList(); // user목록 불러오기
    void removeUser(Customer customer);  // 회원탈퇴 txt파일에서 제거
    Boolean isId(String id);  // id존재확인
    String findpassword(String id); // id입력후 패스워드찾기
    void updateUser(); // 회원정보 변경후 갱신
    Customer findCustomer(Customer customer); // customer객체찾기
    void printUserList(); // 유저목록프린트


}
