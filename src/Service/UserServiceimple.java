package Service;

import user.Customer;

import Repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserServiceimple implements  UserService{
    Scanner input = new Scanner(System.in);
   private final UserRepository userRepository;


    public UserServiceimple(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void join( ) {
        userRepository.loadUserList();
        String id = null;
        ArrayList<String> info = new ArrayList<>();

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            id = input.nextLine().trim();

            if (userRepository.selectId(id)) {
                System.out.println("중복되는 아이디입니다.\n 다시입력해주세요.");
                continue;
            }
            break;

        }
        info.add(id);
        System.out.println("비밀번호를 입력하세요");
        info.add(input.nextLine().trim());
        System.out.println("이름을 입력하세요");
        info.add(input.nextLine().trim());
        System.out.println("주소를 입력하세요");
        info.add(input.nextLine().trim());

        userRepository.save(info);
        System.out.println("회원가입이 정상적으로 완료되었습니다.");




    }

    @Override

    public Customer login() {
        userRepository.loadUserList();
        Customer customer = null;
        System.out.println("아이디를 입력하세요.");
        String id = input.nextLine().trim();
        if(userRepository.selectId(id)){
            System.out.println("비밀 번호를 입력하세요");
            String password = input.nextLine().trim();
            customer = userRepository.findCustomer(new Customer(id,password));
        }
        else{
            System.out.println("없는 id입니다.");

        }
        if (customer == null) {
            System.out.println("로그인 실패");
            return null;
        }
        else {
            System.out.println("로그인 성공");
            return customer;

        }
    }


    @Override
    public void findpassword() {
        userRepository.loadUserList();
        System.out.println("id를 입력하세요");
        String id = input.nextLine();
        if(userRepository.findpassword(id) == null){
            System.out.println("없는 아이디입니다.");

        }else {
            System.out.println("회원님의 아이디는 = " + userRepository.findpassword(id) + "입니다.");
        }


    }
}
