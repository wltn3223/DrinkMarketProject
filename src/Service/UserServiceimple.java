package Service;

import user.Customer;

import Repository.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UserServiceimple implements UserService { // Userservice 구현


    private BufferedReader br; // 글을 입력받을 reader
    private final UserRepository userRepository; // UserService가 이용할 User데이터저장소


    public UserServiceimple(UserRepository userRepository) {  // User데이터저장소의 구현을 다른곳에서 생성자를통해 주입받음(// 의존성주입)
        this.userRepository = userRepository;
        br = new BufferedReader(new InputStreamReader(System.in));

    }


    @Override
    public int choiceMenu() {  // 메뉴선택
        userRepository.loadUserList(); // 로그인 기능, 비밀번호 찾기를 고를수있기때문에 User데이터저장소에 있는 user맵에 회원목록을 불러옴
        System.out.println("===================================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("""
                1.회원가입
                2.로그인
                3.비밀번호찾기
                4.회원탈퇴
                5.정보수정하기
                6.관리자로그인
         
                0.종료""");
        int menu;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요"); // 예외처리
            return -1; // 예외발생시 -1리턴후 재반복
        }
        return menu;
    }

    @Override
    public void join() {
        String id;  // User의 id가 중복여부 구분
        ArrayList<String> info = new ArrayList<>(); // 정보를 입력받을 list

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            try {
                id = br.readLine().trim();
                if (userRepository.isId(id)) {
                    System.out.println("중복되는 아이디입니다.\n다시입력해주세요."); // 중복시 재입력
                    continue;
                }
                info.add(id);
                System.out.println("비밀번호를 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("이름을 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("주소를 입력하세요");
                info.add(br.readLine().trim());
                info.add("0"); // 처음 잔고 0원
            } catch (Exception e) {
                System.out.println("올바른 형식으로 입력해주세요");
                return;  // 예외발생시 메서드 종료
            }
            break;

        }

        userRepository.save(info, false);  // 정보를 제대로 입력받을시  정보 List를 저장소에 전달, 이후 저장소가 저장 기본적으로 이어쓰기,  덮어쓰기(flag = true;)
        System.out.println("회원가입이 정상적으로 완료되었습니다.");


    }

    @Override

    public Customer login() {  // 로그인 성공시 customer 인스턴스 반환 실패시  null 반환
        Customer customer = null;
        String id;
        String password;
        try {
            System.out.println("아이디를 입력하세요.");
            id = br.readLine().trim();
            if (!(userRepository.isId(id))) {  // 
            	System.out.println("없는 id입니다."); // id없을시 null return
            	return null;
            } 
            System.out.println("비밀 번호를 입력하세요");
            password = br.readLine().trim();
            customer = userRepository.findCustomer(new Customer(id, password)); //id가 저장소에 있을시 비밀번호 입력
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요");  // 예외처리
        }
        return customer;
      
    }


    @Override
    public void findpassword() {
        System.out.println("id를 입력하세요");
        String id = null;
        try {
            id = br.readLine();
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요"); // 예외처리
        }
        if (userRepository.findpassword(id) == null) { // 입력한 아이디가 저장소에 있으면 비밀번호 출력
            System.out.println("없는 아이디입니다.");
            return;
        } 
         System.out.println("회원님의 비밀번호는 = " + userRepository.findpassword(id) + "입니다.");
      


    }
    public void updateUser() {
    	Customer customer = login();
    	if(customer == null) {
    		System.out.println("비밀번호가틀렸습니다.");
    		return;
    	}
    	System.out.println("회원정보수정하기 1. 비밀번호 변경, 2.주소 변경");
        int choice;
        try {
            choice = Integer.parseInt(br.readLine()) ;
        if(choice ==1){
            System.out.println("변경할 비밀번호입력");
            String password = br.readLine();
            customer.setPassword(password);
        }else {
            System.out.println("변경할 주소입력");
            String address = br.readLine();
            customer.setAddress(address);
        }
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요"); // 예외처리
        }
        userRepository.updateUser();
        System.out.println("변경완료");
    }



    @Override
	public void removeUser() { // 회원 탈퇴 로그인 성공시 저장소에서 삭제
		Customer customer = login();
		if(customer == null) {
			System.out.println("비밀번호가틀렸습니다.");
			return;
			
		}
		userRepository.removeUser(customer); // 저장소로 넘기면 알아서 삭제
		 System.out.println("회원탈퇴가 정상적으로 완료되었습니다.");
		
		
	}

}
