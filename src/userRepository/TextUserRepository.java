package userRepository;

import user.Customer;
import user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TextUserRepository implements UserRepository {

    private final HashMap<String, Customer> store = new HashMap<>();


    @Override
    public void save(Customer customer) {
        store.put(customer.getId(), customer);
    }

    @Override
    public void loadUserList() {
        try { // If the file does not exist, process the exception.
            FileReader fr = new FileReader("list.txt");
            BufferedReader br = new BufferedReader(fr);
            String customerId;
            String[] info = new String[4];
            while ((customerId = br.readLine()) != null) {
                info[0] = customerId;
                info[1] = br.readLine();
                info[2] = br.readLine();
                info[3] = br.readLine();
                store.put(info[0], new Customer(info[0], info[1], info[2], info[3]));

            }

            br.close();
            fr.close();

        } catch (IOException e) {
        }
    }

    @Override
    public Boolean findId(String id) {
        if (store.containsKey(id)) {
            return true;
        }
        return false;
    }
    @Override
    public String findpassword(String id) {
        if (store.containsKey(id)) {
            return store.get(id).getPassword();
        }
        return null;
    }

    @Override
    public Customer findCustomer(Customer customer) {
        if (store.containsValue(customer))
            return store.get(customer.getId());
        else {
            return null;
        }
    }



}
