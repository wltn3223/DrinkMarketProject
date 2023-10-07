package Service;

import Repository.DrinkRepository;
import user.Admin;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminServiceimple implements AdminService{
    private final DrinkRepository drinkRepository;
    private static  Scanner scanner = new Scanner(System.in);


    public AdminServiceimple(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }
    @Override
    public boolean login() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("관리자 아이디를 입력하세요");
        String id = scanner.nextLine().trim();
        System.out.println("관리자 비밀번호를 입력하세요");
        String password = scanner.nextLine().trim();
        return admin.getId().equals(id) && admin.getPassword().equals(password);
    }



    @Override
    public void addDrink() {
        Scanner scanner = new Scanner(System.in);
        String[] info = new String[4];
        System.out.println("음료수의 종류를 입력하세요");
        info[0] = scanner.nextLine().trim();
        System.out.println("음료수의 이름을 입력하세요");
        info[1] = scanner.nextLine().trim();
        System.out.println("음료수의 가격을 입력하세요");
        info[2] = scanner.nextLine().trim();
        System.out.println("음료수의 용량을 입력하세요");
        info[3] = scanner.nextLine().trim();
        drinkRepository.saveDrink(info);

    }
    public void printDrinkInfo(){
        drinkRepository.loadDrink();
        System.out.println("시리얼 번호" +"\t" + "종류" +"\t" + "이름" +"\t" + "가격" +"\t" + "용량");
        drinkRepository.selectDrink();
    }


}
