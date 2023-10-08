package Service;

import user.Customer;

import Repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UserServiceimple implements UserService {


    private BufferedReader br;
    private final UserRepository userRepository;


    public UserServiceimple(UserRepository userRepository) {
        this.userRepository = userRepository;
        br = new BufferedReader(new InputStreamReader(System.in));

    }


    @Override
    public int choiceMenu() {
        System.out.println("메뉴 선택하세요\n1.회원가입\n 2. 로그인\n 3. 비밀번호찾기\n 4. 관리자로그인");
        int menu = 0;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요");
        }
        return menu;
    }

    @Override
    public void join() {
        userRepository.loadUserList();
        String id = null;
        ArrayList<String> info = new ArrayList<>();

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            try {
                id = br.readLine().trim();
                if (userRepository.isId(id)) {
                    System.out.println("중복되는 아이디입니다.\n 다시입력해주세요.");
                    continue;
                }
                info.add(id);
                System.out.println("비밀번호를 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("이름을 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("주소를 입력하세요");
                info.add(br.readLine().trim());
            } catch (Exception e) {
                System.out.println("똑바로 입력해주세요");
                return;
            }
            break;

        }

        userRepository.save(info);
        System.out.println("회원가입이 정상적으로 완료되었습니다.");


    }

    @Override

    public Customer login() {
        userRepository.loadUserList();
        Customer customer = null;
        String id = "";
        String password = "";
        try {
            System.out.println("아이디를 입력하세요.");
            id = br.readLine().trim();
            if (userRepository.isId(id)) {
                System.out.println("비밀 번호를 입력하세요");
                password = br.readLine().trim();
                customer = userRepository.findCustomer(new Customer(id, password));
            } else {
                System.out.println("없는 id입니다.");

            }
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요");
        }
        if (customer == null) {
            System.out.println("로그인 실패");
            return null;
        } else {
            System.out.println("로그인 성공");
            return customer;

        }
    }


    @Override
    public void findpassword() {
        userRepository.loadUserList();
        System.out.println("id를 입력하세요");
        String id = null;
        try {
            id = br.readLine();
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요");
        }
        if (userRepository.findpassword(id) == null) {
            System.out.println("없는 아이디입니다.");

        } else {
            System.out.println("회원님의 아이디는 = " + userRepository.findpassword(id) + "입니다.");
        }


    }
}
