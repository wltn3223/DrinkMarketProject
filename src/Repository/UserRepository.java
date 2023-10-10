package Repository;

import user.Customer;

import java.util.ArrayList;


public interface UserRepository {
    void save(ArrayList<String> info, boolean flag);

    
    void loadUserList();
    void removeUser(Customer customer);
    Boolean isId(String id);
    public String findpassword(String id);
    Customer findCustomer(Customer customer);
    void printUserList();


}
