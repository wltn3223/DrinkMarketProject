package Repository;

import user.Customer;

import java.util.ArrayList;


public interface UserRepository {
    void save(ArrayList<String> info, boolean flag); // txt파일에 덮어쓸지 말지 flag로 결정

    
    void loadUserList();
    void removeUser(Customer customer);
    Boolean isId(String id);
    public String findpassword(String id);
    void updateUser(Customer customer);
    Customer findCustomer(Customer customer);
    void printUserList();


}
