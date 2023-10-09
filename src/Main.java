

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
                    System.out.println("===================================================");
                    userService.join();
                    break;
                case 2:
                    // login
                    System.out.println("===================================================");
                    customer = userService.login();
                    if (customer != null) {
                        quit = true;
                    }
                    break;
                case 3:
                    System.out.println("===================================================");
                    userService.findpassword();
                    break;

                case 4:
                    System.out.println("===================================================");
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
            while (!quit) {
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
                        System.out.println("===================================================");
                        adminService.printDrinkList();
                        break;
                    case 2:
                        System.out.println("===================================================");
                        adminService.addDrink();
                        break;
                }
            }
        } else {
            quit = false;
            // user
            while (!quit) {
                int menu = cartService.choiceMenu();

                if (menu > 8 || (menu < -1)) {
                    System.out.println("0~8까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case -1:
                        continue;

                    case 0:
                        quit = true;
                        break;

                    case 1:
                        System.out.println("===================================================");
                        System.out.println("고객정보");
                        System.out.println(customer);
                        break;
                    case 2:
                        System.out.println("===================================================");
                        cartService.printCart();
                        break;
                    case 3:
                        System.out.println("===================================================");
                        cartService.addItem();
                        break;

                    case 4:
                        System.out.println("===================================================");
                        cartService.addQuantity();
                        break;
                    case 5:
                        System.out.println("===================================================");
                        cartService.removeItem();
                        break;
                    case 6:
                        System.out.println("===================================================");
                        cartService.subtractItem();
                        break;
                    case 7:
                        System.out.println("===================================================");
                        cartService.clearCart();
                        break;
                    case 8:
                        System.out.println("===================================================");
                        cartService.order(customer);
                        break;


                }

            }
        }
        System.out.println("시스템을 종료합니다.");

    }


}

