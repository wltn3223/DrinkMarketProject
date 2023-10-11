

import Service.AdminService;
import cart.CartService;
import user.Customer;
import Service.UserService;

public class Main { // login user
    public static void main(String[] args) {
        Appconfig appconfig = new Appconfig(); // 모든 서비스 생성 관리
        // appconfig는 서비스들이 어떤 저장소를 이용할지 결정 (기획자역할)
        UserService userService = appconfig.userService();  // 유저가 이용하는 서비스(회원가입, 로그인, 비밀번호찾기)의 기능을 가진 객체
        AdminService adminService = appconfig.adminService(); // 관리자가 이용하는 서비스(음료 목록 보기, 음료 목록 추가)
        CartService cartService = appconfig.cartService();


        Customer customer = null;  // 현재 로그인한 구매자
        boolean adminmode = false; // 관리자의 로그인 여부 구분


        boolean quit = false;  // 각 서비스 이용 중단 여부
        while (!quit) {

            int menu = userService.choiceMenu(); // 유저가 메뉴 번호를 선택해 UserService 이용

            if (menu > 6 || (menu < 0)) {
                System.out.println("0~4까지의 번호를 선택하세요"); // 없는 메뉴를 누를시 재반복
                continue;
            }
            switch (menu) {
                case 0:
                    System.out.println("프로그램을 종료합니다"); // 0 입력 시 프로그램 종료
                    return;

                case 1:
                    System.out.println("===================================================");
                    userService.join(); // 회원가입 기능
                    break;
                case 2:
                    // login
                    System.out.println("===================================================");
                    customer = userService.login(); // 로그인 기능 로그인 성공시 Customer 인스턴스반환 실패시 null반환
                    if (customer != null) {
                        System.out.println("로그인 성공");
                        quit = true; // 로그인 성공시 반복문 탈출
                    } else {
                        System.out.println("로그인 실패");
                    }
                    break;
                case 3:
                    System.out.println("===================================================");
                    userService.findpassword(); // 비밀번호찾기
                    break;
                case 4:
                    System.out.println("===================================================");
                    userService.removeUser(); // 회원탈퇴
                    break;
                case 5:
                    System.out.println("===================================================");
                    userService.updateUser(); // 정보변경
                    break;

                case 6:
                    System.out.println("===================================================");
                    adminmode = adminService.login(); // 관리자 로그인 관리자 로그인 성공시 adminmode로 전환
                    if (!adminmode) {
                        System.out.println("로그인실패");
                    } else {
                        quit = adminmode;
                    }
                    break;

            }

        }
        quit = false; // 다시 무한루프문 작동시킴

        if (adminmode) {
            while (!quit) {
                System.out.println("관리자로 로그인하셨습니다");
                int menu = adminService.choiceMenu(); // choicemenu에서 메뉴 번호를 입력
                if (menu > 3 || (menu < 0)) { // 잘못입력시
                    System.out.println("0~2까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case 0: // 0 입력시 반복문종료 후 프로그램 종료
                        quit = true;
                        break;
                    case 1:
                        System.out.println("===================================================");
                        adminService.printDrinkList();// 현재 판매하는 음료목룍 출력
                        break;
                    case 2:
                        System.out.println("===================================================");
                        adminService.addDrink(); // 음료목록 추가
                        break;
                    case 3:
                        System.out.println("===================================================");
                        adminService.printUserList(); // 회원 목록확인
                        break;
                }
            }
        } else {

            // user
            while (!quit) {
                int menu = cartService.choiceMenu(); // 메뉴선택

                if (menu > 8 || (menu < 0)) {  // 잘못입력시
                    System.out.println("0~8까지의 번호를 선택하세요");
                    continue;
                }
                switch (menu) {
                    case 0:
                        quit = true;
                        break;

                    case 1: // 고객정보
                        System.out.println("===================================================");
                        System.out.println("고객정보");
                        System.out.println(customer);
                        break;
                    case 2: // 장바구니 출력
                        System.out.println("===================================================");
                        cartService.printCart();
                        break;
                    case 3: // 장바구니 음료 추가
                        System.out.println("===================================================");
                        cartService.addItem();
                        break;

                    case 4: // 장바구니 음료 수량 추가
                        System.out.println("===================================================");
                        cartService.addQuantity();
                        break;
                    case 5: // 장바구니 음료 제거
                        System.out.println("===================================================");
                        cartService.removeItem();
                        break;
                    case 6:// 장바구니 음료 수량 제거
                        System.out.println("===================================================");
                        cartService.subtractItem();
                        break;
                    case 7:// 장바구니 모든항목 제거
                        System.out.println("===================================================");
                        cartService.clearCart();
                        break;
                    case 8:// 주문하기
                        System.out.println("===================================================");
                        cartService.order(customer);
                        break;


                }

            }
        }
        System.out.println("시스템을 종료합니다.");

    }


}

