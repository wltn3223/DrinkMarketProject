package Service;

import Repository.DrinkRepository;
import user.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdminServiceimple implements AdminService{
    private final DrinkRepository drinkRepository;
    private BufferedReader br;


    public AdminServiceimple(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
        br = new BufferedReader(new InputStreamReader(System.in));

    }
    @Override
    public int choiceMenu() {
        System.out.println("메뉴 선택하세요\n1.음료목록\n 2. 음료추가\n 3. 종료");
        int menu = 0;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요");
        }
        return menu;
    }
    @Override
    public boolean login() {
        Admin admin = new Admin();
        System.out.println("관리자 아이디를 입력하세요");
        String id = "";
        String password = "";
        try {
            id = br.readLine().trim();
        System.out.println("관리자 비밀번호를 입력하세요");
        password = br.readLine().trim();
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요.");
            return false;
        }
        return admin.getId().equals(id) && admin.getPassword().equals(password);
    }



    @Override
    public void addDrink() {
        String[] info = new String[4];

        System.out.println("음료수의 종류를 입력하세요");
        try {
            info[0] = br.readLine().trim().trim();
            System.out.println("음료수의 이름을 입력하세요");
            info[1] = br.readLine().trim().trim();
            System.out.println("음료수의 가격을 입력하세요");
            info[2] = br.readLine().trim().trim();
            System.out.println("음료수의 용량을 입력하세요");
            info[3] = br.readLine().trim().trim();
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요.");
            return;
        }
        drinkRepository.saveDrink(info);

    }
    public void printDrinkInfo(){
        drinkRepository.loadDrink();
        System.out.println("시리얼 번호" +"\t" + "종류" +"\t" + "이름" +"\t" + "가격" +"\t" + "용량");
        drinkRepository.lookDrinkList();
    }


}
