package userService;

import user.Customer;
import userRepository.TextUserRepository;
import userRepository.UserRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserServiceimple implements  UserService{
    static Scanner input = new Scanner(System.in);
   final UserRepository userRepository;

    public UserServiceimple(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<String> join( ArrayList<String> userinfo) {
        String id = null;
        ArrayList<String> info = new ArrayList<>();

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            id = input.nextLine().trim();

            if (userRepository.findId(id)) {
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
        System.out.println("스마트폰 번호를 입력하세요");
        info.add(input.nextLine().trim());
        System.out.println("회원가입이 정상적으로 완료되었습니다.");

        return info;


    }

    @Override

    public Customer login() {
        Customer customer = null;
        System.out.println("아이디를 입력하세요.");
        String id = input.nextLine().trim();
        if(userRepository.findId(id)){
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
    public String findpassword(String id) {
        return userRepository.findpassword(id);
    }
}
