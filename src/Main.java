

import user.Customer;
import userRepository.TextUserRepository;
import userRepository.UserRepository;
import userService.UserService;
import userService.UserServiceimple;


import java.util.Scanner;

public class Main { // login user
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Customer customer;  // login user
        UserRepository userRepository = new TextUserRepository(); // User repository
        UserService userService = new UserServiceimple();   // User service

        userRepository.loadUserList();

        boolean quit = false;
        while (!quit) {
            System.out.println("메뉴 번호를 선택하세요 1.회원가입 2. 로그인 3. 비밀번호찾기 4. 관리자로그인");

            int menu = input.nextInt();

            if (menu > 4 || menu < 1) {
                System.out.println("1~3까지의 번호를 선택하세요");
                continue;
            }
            switch (menu) {

                case 1:
                    input.nextLine();  // Buffer clear;
                    System.out.println("*********************회원가입**********************");


                    userService.join();
                    userRepository.loadUserList();
                    break;
                case 2:
                    input.nextLine();
                    // login
                    System.out.println("*********************로그인**********************");
                    customer = userService.login();
                    if(customer != null){
                        quit = true;
                    }
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("*********************비밀번호찾기**********************");
                    break;

                case 4:
                    input.nextLine();
                    System.out.println("*********************관리자로그인**********************");

                    break;
            }

        }


        // user
        System.out.println("================================");

        System.out.println("회원정보 출력");
        // print userinfo

    }





//    public static boolean loginAdmin() {
//        input.nextLine();
//        System.out.println("관리자로그인");
//        System.out.println("아이디를입력하세요");
//        String adminId = input.nextLine().trim();
//        System.out.println("비밀번호를입력하세요");
//        String adminPassword = input.nextLine().trim();
//        Admin admin = new Admin();
//        boolean match = adminId.equals(admin.getId()) && adminPassword.equals(admin.getPassword());
//        System.out.println(match == true ? "관리자로그인성공" : "관리자로그인실패");
//        return match;
//    }

}
