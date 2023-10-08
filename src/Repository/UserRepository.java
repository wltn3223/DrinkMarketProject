package Repository;

import user.Customer;

import java.util.ArrayList;


public interface UserRepository {
    void save(ArrayList<String> info);
    void loadUserList();

    Boolean isId(String id);
    public String findpassword(String id);
    Customer findCustomer(Customer customer);


}
