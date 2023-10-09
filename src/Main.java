

import Service.AdminService;
import cart.CartService;
import user.Customer;
import Service.UserService;

public class Main { // login user
    public static void main(String[] args) {
        Appconfig appconfig = new Appconfig();
        UserService userService = appconfig.userService();  // User service to use Repository
        AdminService adminService = appconfig.adminService();
        CartService cartService = appconfig.cartService();


        Customer customer = null;  // login user
        boolean adminmode = false; // login admin


        // load user info in txt

        boolean quit = false;
        while (!quit) {

            int menu = userService.choiceMenu();

            if (menu > 4 || (menu < -1)) {
                System.out.println("0~4까지의 번호를 선택하세요");
                continue;
            }
            switch (menu) {
                case -1:
                    continue;
                case 0:
                    quit = true;
                    break;

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
                System.out.println("관리자로 로그인하셨습니다");
                int menu = adminService.choiceMenu();
                if (menu > 2 || (menu < -1)) {
                    System.out.println("0~2까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case -1:
                        continue;
                    case 0:
                        quit = true;
                    case 1:
                        System.out.println("*********************음료목록**********************");
                        adminService.printDrinkList();
                        break;
                    case 2:
                        System.out.println("*********************음료수추가**********************");
                        adminService.addDrink();
                        break;
                }
            }
        } else {
            quit = false;
            // user
            while (!quit) {
                int menu = cartService.choiceMenu();

                if (menu > 7 || (menu < -1)) {
                    System.out.println("0~7까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case -1:
                        continue;

                    case 0:
                        quit = true;
                        break;

                    case 1:
                        System.out.println("*********************고객정보 확인**********************");
                        System.out.println(customer);
                        break;
                    case 2:
                        System.out.println("*********************내 장바구니 확인**********************");
                        cartService.printCart();
                        break;
                    case 3:
                        System.out.println("*********************장바구니 품목추가**********************");
                        cartService.addItem();
                        break;

                    case 4:
                        System.out.println("*********************장바구니 품목 수량추가**********************");
                        cartService.addQuantity();
                        break;
                    case 5:
                        System.out.println("*********************장바구니 품목 제거**********************");
                        cartService.removeItem();
                        break;
                    case 6:
                        System.out.println("*********************장바구니 품목 수량제거**********************");
                        cartService.subtractItem();
                        break;
                    case 7:
                        System.out.println("*********************장바구니 비우기**********************");
                        cartService.clearCart();
                        break;
                    case 8:
                        System.out.println("*********************장바구니 비우기**********************");

                        break;


                }

            }
        }
        System.out.println("시스템을 종료합니다.");

    }


}

