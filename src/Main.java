

import user.Customer;
import userService.UserService;


import java.util.Scanner;

public class Main { // login user
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Appconfig appconfig = new Appconfig();
        UserService userService =  appconfig.userService();  // User service to use Repository

        Customer customer;  // login user


        ; // load user info in txt

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
                    // Buffer clear;
                    System.out.println("*********************회원가입**********************");
                    userService.join();

                    break;
                case 2:
                    // login
                    System.out.println("*********************로그인**********************");
                    customer = userService.login();
                    if(customer != null){
                        quit = true;
                    }
                    break;
                case 3:
                    System.out.println("*********************비밀번호찾기**********************");
                    userService.findpassword();
                    break;

                case 4:
                    System.out.println("*********************관리자로그인**********************");

                    break;
            }

        }
        //admin
        System.out.println("관리자로 로그인하셨습니다");
        System.out.println("메뉴를 선택하세요");
        System.out.println("1. 품목추가 ");


        // user
        System.out.println("================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("1. 고객정보, 2. 장바구니 비우기, 내 장바구니 확인,장바구니 품목추가, 장바구니 품목 제거, 장바구니 수량 제거");
        // print userinfo

    }




}
