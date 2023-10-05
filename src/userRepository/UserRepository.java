package userRepository;

import user.Customer;


public interface UserRepository {
    void save(Customer customer);
    void loadUserList();

    Boolean findId(String id);
    public String findpassword(String id);
    Customer findCustomer(Customer customer);


}
