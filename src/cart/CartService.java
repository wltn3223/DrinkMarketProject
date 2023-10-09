package cart;


import user.Customer;

public interface CartService {
    int choiceMenu();  // 메뉴선택
    void clearCart(); // 장바구니 비우기
    void printCart();  // 장바구니 목록 출력
    void addItem(); // 장바구니 음료 추가
    void addQuantity(); // 장바구니 음료 수량 추가
    void removeItem(); // 장바구니 음료 제거

    void subtractItem(); // 장바구니 음료 수량 감소시키는 메서드

    void order(Customer customer); // 장바구니에 담은 항목 주문하기


}
