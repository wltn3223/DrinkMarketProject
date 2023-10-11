package Service;



public interface AdminService {
    int choiceMenu(); // 메뉴선택
    void addDrink(); // 음료추가
    void printDrinkList();  // 음료목록보기
    boolean login(); // 관리자로그인
    void printUserList(); // 유저목록보기

}
