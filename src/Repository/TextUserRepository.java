package Repository;

import user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TextUserRepository implements UserRepository {

    private final HashMap<String, Customer> userDic = new HashMap<>();
    // user id는 중복허용 되지 않기 때문에 id와, customer 객체로 맵구성


    @Override
    public void save(ArrayList<String> info,boolean flag) {  // 입력받은 정보List를 txt파일에 저장
        FileWriter fw;
        boolean overwrite = flag;
        
        
        
        try {
            if (!(new File("list.txt").exists()) || overwrite == true || info.size() == 0) {  
            	fw = new FileWriter("list.txt"); // 파일이 없으면 새로 파일을 만들어씀, 아니면 덮어쓰기
            } else {
                fw = new FileWriter("list.txt", true); // txt파일이 존재하면,이어쓰기
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
    public void loadUserList() { // 유저목록이 저장된 txt에서 UserMap으로 유저목록 가져옴
        try {
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
                // 유저목록 가져온후 map에 저장
            }

            br.close();
            fr.close();

        } catch (IOException e) { // 처음에 파일에 아무 내용이 없을 수있기 때문에 catch를 빈칸으로 놔두어 프로그램을 그대로 진행시킴
        }

    }

    @Override
	public void removeUser(Customer customer) {
    	ArrayList<String> info = new ArrayList<String>();
    	userDic.remove(customer.getId());
    	for(Customer data: userDic.values()) {
    		info.add(data.getId());
    		info.add(data.getPassword());
    		info.add(data.getName());
    		info.add(data.getAddress()); 
    	}
    	save(info,true); // 덮어쓴다
    	
    	
		
	}

	@Override
	public void printUserList() {
		userDic.values().forEach(customer -> System.out.println(customer));
	
	}



	@Override
    public Boolean isId(String id) { // UserMap에 id존재여부
        return  userDic.containsKey(id);
    }
    @Override
    public String findpassword(String id) { //Usermap에 id 존재시 id return
        if (userDic.containsKey(id)) {
            return userDic.get(id).getPassword();
        }
        return null;
    }

    @Override
    public Customer findCustomer(Customer customer) { // map에 Cutomer객체 존재시 return
        if (userDic.containsValue(customer))
            return userDic.get(customer.getId());
        else {
            return null;
        }
    }



}
