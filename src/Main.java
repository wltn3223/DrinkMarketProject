

import Service.AdminService;
import cart.CartService;
import user.Customer;
import Service.UserService;


import java.util.Scanner;

public class Main { // login user
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Appconfig appconfig = new Appconfig();
        UserService userService = appconfig.userService();  // User service to use Repository
        AdminService adminService = appconfig.adminService();


        Customer customer = null;  // login user
        boolean adminmode = false; // login admin


        // load user info in txt

        boolean quit = false;
        while (!quit) {

            int menu = userService.choiceMenu();

            if (menu > 4 || menu < 1) {
                System.out.println("1~3까지의 번호를 선택하세요");
                continue;
            }
            switch (menu) {

                case 1:
                    System.out.println("*********************회원가입**********************");
                    userService.join();

                    break;
                case 2:
                    // login
                    System.out.println("*********************로그인**********************");
                    customer = userService.login();
                    if (customer != null) {
                        quit = true;
                    }
                    break;
                case 3:
                    System.out.println("*********************비밀번호찾기**********************");
                    userService.findpassword();
                    break;

                case 4:
                    System.out.println("*********************관리자로그인**********************");
                    adminmode = adminService.login();
                    if (!adminmode) {
                        System.out.println("로그인실패");
                    } else {
                        quit = adminmode;
                    }
                    break;

            }

        }

        if (adminmode) {
            quit = false;
            while (quit) {
                input.nextLine();
                System.out.println("관리자로 로그인하셨습니다");
                System.out.println("1.음료수목록보기 \t2.음료수추가\t3.종료");
                System.out.println("메뉴를 선택하세요");
                int menu = input.nextInt();
                if (menu > 3 || menu < 1) {
                    System.out.println("1~3까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case 1:
                        System.out.println("*********************음료목록**********************");
                        adminService.printDrinkInfo();
                        break;
                    case 2:
                        System.out.println("*********************음료수추가**********************");
                        adminService.addDrink();
                        break;
                    case 3:
                        quit = true;
                }
            }
        } else {
            quit = false;
            // user
            System.out.println("================================");
            System.out.println("메뉴를 선택하세요");
            System.out.println("1. 고객정보확인, 2. 장바구니 비우기, 내 장바구니 확인,장바구니 품목추가, 장바구니 품목 제거, 장바구니 수량 제거");
            while (!quit) {
                System.out.println("메뉴 번호를 선택하세요 1.회원가입 2. 로그인 3. 비밀번호찾기 4. 관리자로그인 ");

                int menu = input.nextInt();

                if (menu > 4 || menu < 1) {
                    System.out.println("1~3까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {

                    case  0:
                        quit = true;
                        break;

                    case 1:
                        // Buffer clear;
                        System.out.println("*********************고객정보 확인**********************");
                        System.out.println(customer);
                        break;
                    case 2:
                        // login
                        System.out.println("*********************내 장바구니 확인**********************");
                        customer = userService.login();
                        if (customer != null) {
                            quit = true;
                        }
                        break;
                    case 3:
                        System.out.println("*********************장바구니 품목추가**********************");
                        userService.findpassword();
                        break;

                    case 4:
                        System.out.println("*********************장바구니 품목 수량추가**********************");

                        break;
                    case 5:
                        System.out.println("*********************장바구니 품목 제거**********************");

                        break;
                    case 6:
                        System.out.println("*********************장바구니 품목 수량제거**********************");

                        break;
                    case 7:
                        System.out.println("*********************장바구니 비우기**********************");

                        break;


                }

            }
        }
        input.close();
        System.out.println("시스템을 종료합니다.");

    }


}

