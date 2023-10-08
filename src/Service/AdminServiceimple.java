package Service;

import Repository.DrinkRepository;
import item.Drink;
import user.Admin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdminServiceimple implements AdminService{
    private final DrinkRepository drinkRepository;
    private BufferedReader br;


    public AdminServiceimple(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
        br = new BufferedReader(new InputStreamReader(System.in));

    }
    @Override
    public int choiceMenu() {
        drinkRepository.loadDrink();
        System.out.println("===================================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("""
                1.음료목록
                2.음료추가
                0.종료""");
        int menu;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요");
            return -1;
        }
        return menu;
    }
    @Override
    public boolean login() {
        Admin admin = new Admin();
        System.out.println("관리자 아이디를 입력하세요");
        String id;
        String password;
        try {
            id = br.readLine().trim();
        System.out.println("관리자 비밀번호를 입력하세요");
        password = br.readLine().trim();
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
            return false;
        }
        return admin.getId().equals(id) && admin.getPassword().equals(password);
    }



    @Override
    public void addDrink() {
        String[] info = new String[4];
        Drink drink;

        System.out.println("음료수의 종류를 입력하세요(예:차, 탄산, 이온)");
        try {
            info[0] = br.readLine().trim().trim();
            System.out.println("음료수의 이름을 입력하세요");
            info[1] = br.readLine().trim().trim();
            System.out.println("음료수의 가격을 입력하세요(숫자만 입력, 단위:원)");
            info[2] = br.readLine().trim().trim();
            System.out.println("음료수의 용량과 단위를 입력하세요(예:250ml, 1.5L)");
            info[3] = br.readLine().trim().trim();
            drink = new Drink(info[0], info[1], Integer.parseInt(info[2]), info[3]);
        } catch (Exception e) {
            System.out.println("형식에 맞게 입력해주세요.");
            System.out.println("다시시도해주세요");
            return;
        }
        drinkRepository.saveDrink(drink);
        System.out.println("음료수가 추가되었습니다.");

    }
    public void printDrinkList(){
        drinkRepository.printDrinkList();
    }


}
