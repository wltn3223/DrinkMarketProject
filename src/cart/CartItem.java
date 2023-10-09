package cart;

import item.Drink;

import java.util.Objects;

public class CartItem {  // 장바구니
    private Drink drink;  // 장바구니에 담을 drink 객체
    private int quantity; // drink 객체의 수량

    private int totalprince; // 총가격

    public CartItem(Drink drink) {  // (생성자)drink 음료 추가시 갯수 1
        this.drink = drink;
        this.quantity = 1;
        updateTotalprince();
    }

    public Drink getDrink() {
        return drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void  addQuantity(int quantity){  // 음료 수량 추가
        this.quantity += quantity;
        updateTotalprince();  // 총가격 업데이트
    }
    public void  subtractQuantity(int quantity){ // 음료 수량 감소
            this.quantity -= quantity;
            updateTotalprince(); // 총가격 업데이터

    }

    public int getTotalprince() {
        return this.quantity * this.getDrink().getPrice();
    } // 현재 가격 return
    public void updateTotalprince() {
        this.totalprince = this.quantity * this.getDrink().getPrice();
    }
    // 현재 가격 update


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CartItem)){
            return false;
        }
        return ((CartItem) obj).getDrink().equals(this.getDrink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(drink);
    }

    @Override
    public String toString() {
        return drink.getId() +
                "\t\t\t| " + drink.getName() +
                "\t\t| " + quantity +
                "\t\t\t\t| "+ totalprince;
    }
}
