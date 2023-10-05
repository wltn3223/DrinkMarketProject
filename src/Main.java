package user;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Customer customer; // login user
    static HashMap<String, Customer> userMap = new HashMap<String, Customer>(); // Key id, value userinfo
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean adminmode = false; // admin login
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
                    loadinfo();
                    System.out.println("*********************회원가입**********************");
                    signUp();

                    break;
                case 2:
                    // login
                    loadinfo();
                    System.out.println("*********************로그인**********************");
                    quit = login();

                    break;
                case 3:
                    loadinfo();
                    System.out.println("*********************비밀번호찾기**********************");
                    findPassword();
                    break;

                case 4:
                    System.out.println("*********************관리자로그인**********************");
                    adminmode = loginAdmin();
                    quit = adminmode;
                    break;
            }

        }

        if (adminmode) {
            System.out.println("관리자모드입니다.");
            System.out.println("새로운 종류 추가");

        }
        // user
        System.out.println("================================");
        System.out.println("");
        System.out.println("");
        System.out.println("회원정보 출력");
        System.out.println(customer);
        // print userinfo

    }

    // load userlist
    public static void loadinfo() {
        try { // If the file does not exist, process the exception.
            FileReader fr = new FileReader("list.txt");
            BufferedReader br = new BufferedReader(fr);
            String customerId;
            String[] info = new String[5];
            while ((customerId = br.readLine()) != null) {
                info[0] = customerId;
                info[1] = br.readLine();
                info[2] = br.readLine();
                info[3] = br.readLine();
                info[4] = br.readLine();
                userMap.put(info[0], new Customer(info[0], info[1], info[2], info[3], info[4]));

            }

            br.close();
            fr.close();

        } catch (IOException e) {
        }

    }

    // signUp
    public static void signUp() {
        input.nextLine();
        FileWriter fw;
        String id = null;
        ArrayList<String> info = new ArrayList<>();

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            id = input.nextLine().trim();

            if (userMap.containsKey(id)) {
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
        try {
            if (new File("list.txt").exists()) {
                fw = new FileWriter("list.txt", true);
            } else {
                fw = new FileWriter("list.txt");
            }

            BufferedWriter bw = new BufferedWriter(fw);

            for (String data : info) {
                bw.write(data + "\n");
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("회원가입이 정상적으로 완료되었습니다.");

    }

    // if input id is in userList, login
    public static boolean login() {
        boolean logon = false;
        input.nextLine();

        System.out.println("아이디를 입력하세요.");
        String id = input.nextLine().trim();

        if (userMap.containsKey(id)) { // if user id in usermap, get password in map
            String userpassword = userMap.get(id).getPassword();

            System.out.println("비밀번호를 입력하세요.");
            String inputpassword = input.nextLine().trim(); // input password
            if (userpassword.equals(inputpassword)) {
                System.out.println("로그인에 성공하셨습니다.");
                customer = userMap.get(id);
                logon = true;
            } else {
                System.out.println("비밀번호를 잘못입력하셨습니다.");
            }

        } else {
            System.out.println("없는 아이디입니다.");
        }

        return logon;

    }

    public static void findPassword() {
        input.nextLine();
        System.out.println("id를 입력하세요");
        String id = input.nextLine().trim();

        if (userMap.containsKey(id)) {
            System.out.printf("입력하신 id의 비밀번호는 %s입니다.", userMap.get(id).getPassword());
        } else {
            System.out.println("없는아이디입니다.");
        }

    }

    public static boolean loginAdmin() {
        input.nextLine();
        System.out.println("관리자로그인");
        System.out.println("아이디를입력하세요");
        String adminId = input.nextLine().trim();
        System.out.println("비밀번호를입력하세요");
        String adminPassword = input.nextLine().trim();
        Admin admin = new Admin();
        boolean match = adminId.equals(admin.getId()) && adminPassword.equals(admin.getPassword());
        System.out.println(match == true ? "관리자로그인성공" : "관리자로그인실패");
        return match;
    }

}
