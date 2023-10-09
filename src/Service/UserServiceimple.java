package Service;

import user.Customer;

import Repository.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UserServiceimple implements UserService { // Userservice 구현


    private BufferedReader br; // 글을 입력받을 reader
    private final UserRepository userRepository; // UserService가 이용할 User데이터저장소


    public UserServiceimple(UserRepository userRepository) {  // User데이터저장소의 구현을 다른곳에서 생성자를통해 주입받음(// 의존성주입)
        this.userRepository = userRepository;
        br = new BufferedReader(new InputStreamReader(System.in));

    }


    @Override
    public int choiceMenu() {  // 메뉴선택
        userRepository.loadUserList(); // 로그인 기능, 비밀번호 찾기를 고를수있기때문에 User데이터저장소에 있는 user맵에 회원목록을 불러옴
        System.out.println("===================================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("""
                1.회원가입
                2.로그인
                3.비밀번호찾기
                0.종료""");
        int menu;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요"); // 예외처리
            return -1; // 예외발생시 -1리턴후 재반복
        }
        return menu;
    }

    @Override
    public void join() {
        String id;  // User의 id가 중복여부 구분
        ArrayList<String> info = new ArrayList<>(); // 정보를 입력받을 list

        while (true) {
            System.out.println("가입하실 아이디를 입력하세요");
            try {
                id = br.readLine().trim();
                if (userRepository.isId(id)) {
                    System.out.println("중복되는 아이디입니다.\n 다시입력해주세요."); // 중복시 재입력
                    continue;
                }
                info.add(id);
                System.out.println("비밀번호를 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("이름을 입력하세요");
                info.add(br.readLine().trim());
                System.out.println("주소를 입력하세요");
                info.add(br.readLine().trim());
            } catch (Exception e) {
                System.out.println("올바른 형식으로 입력해주세요");
                return;  // 예외발생시 메서드 종료
            }
            break;

        }

        userRepository.save(info);  // 정보를 제대로 입력받을시  정보List를 저장소에 전달, 이후 저장소가 저장
        System.out.println("회원가입이 정상적으로 완료되었습니다.");


    }

    @Override

    public Customer login() {  // 로그인 성공시 customer 인스턴스 반환 실패시  null 반환
        Customer customer = null;
        String id;
        String password;
        try {
            System.out.println("아이디를 입력하세요.");
            id = br.readLine().trim();
            if (userRepository.isId(id)) {  // id가 저장소에 있을시 비밀번호 입력
                System.out.println("비밀 번호를 입력하세요");
                password = br.readLine().trim();
                customer = userRepository.findCustomer(new Customer(id, password));
            } else { // id없을시 null return
                System.out.println("없는 id입니다.");
                return null;

            }
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요");  // 예외처리
        }
        if (customer == null) {
            System.out.println("비밀번호가 틀렸습니다.");
            return null;
        } else {
            System.out.println("로그인 성공");
            return customer;

        }
    }


    @Override
    public void findpassword() {
        System.out.println("id를 입력하세요");
        String id = null;
        try {
            id = br.readLine();
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요"); // 예외처리
        }
        if (userRepository.findpassword(id) == null) { // 입력한 아이디가 저장소에 있으면 비밀번호 출력
            System.out.println("없는 아이디입니다.");

        } else {
            System.out.println("회원님의 비밀번호는 = " + userRepository.findpassword(id) + "입니다.");
        }


    }

}
