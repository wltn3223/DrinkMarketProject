package Repository;

import user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TextUserRepository implements UserRepository {

    private final HashMap<String, Customer> userDic = new HashMap<>();


    @Override
    public void save(ArrayList<String> info) {
        FileWriter fw;
        try {
            if (new File("list.txt").exists()) {
                fw = new FileWriter("list.txt", true);
            } else {
                fw = new FileWriter("list.txt");
            }

            BufferedWriter bw = new BufferedWriter(fw);

            for (String data : info) {
                bw.write(data + "\n");
            }
            bw.close();
            fw.close();

        } catch (IOException e) {

        }
        loadUserList();

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
                userDic.put(info[0], new Customer(info[0], info[1], info[2], info[3]));

            }

            br.close();
            fr.close();

        } catch (IOException e) {
        }

    }

    @Override
    public Boolean selectId(String id) {
        if (userDic.containsKey(id)) {
            return true;
        }
        return false;
    }
    @Override
    public String findpassword(String id) {
        if (userDic.containsKey(id)) {
            return userDic.get(id).getPassword();
        }
        return null;
    }

    @Override
    public Customer findCustomer(Customer customer) {
        if (userDic.containsValue(customer))
            return userDic.get(customer.getId());
        else {
            return null;
        }
    }



}
