package Repository;

import user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TextUserRepository implements UserRepository {

    private final HashMap<String, Customer> userDic = new HashMap<>();
    // user id는 중복허용 되지 않기 때문에 id와, customer 객체로 맵구성


    @Override
    public void save(ArrayList<String> info,boolean overwrite) {  // 입력받은 정보List를 txt파일에 저장 overwrite 덮어쓰기 여부
        FileWriter fw;
        
        try { // 파일이 존재하지 않거나 flag가 true로 들어오거나 들어온 정보가 아예없다면 txt파일 내용 다지움
            if (!(new File("list.txt").exists()) || overwrite || info.isEmpty()) {
            	fw = new FileWriter("list.txt"); // 
            } else {
                fw = new FileWriter("list.txt", true); // 아니면 이어쓰기
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
    	userDic.remove(customer.getId());
    	updateUser();
    
    	
    	
		
	}

	@Override
	public void updateUser() { // 변경된내용 다시 txt파일에 저장(덮어씀)
		ArrayList<String> info = new ArrayList<>();
		for(Customer data: userDic.values()) {
    		info.add(data.getId());
    		info.add(data.getPassword());
    		info.add(data.getName());
    		info.add(data.getAddress()); 
    	}
    	save(info,true); // 덮어쓴다, 회원이 한명있을때 그한명이 탈퇴하면 info에는 담을 정보가 없음 size는 0, 아무도없다는 뜻이기때문에
    	// save에서 파일내용을 알아서 싹지움
		
	}



	@Override
	public void printUserList() {
		userDic.keySet().stream().forEach(key -> System.out.println(userDic.get(key)));
	
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
