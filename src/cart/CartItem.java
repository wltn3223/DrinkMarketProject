package cart;

import item.Drink;

import java.util.Objects;

public class CartItem {
    private Drink drink;
    private int quantity;

    private int totalprince;

    public CartItem(Drink drink) {
        this.drink = drink;
        this.quantity = 1;
    }

    public Drink getDrink() {
        return drink;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void  addQuantity(int quantity){
        this.quantity += quantity;
    }
    public void  subtractQuantity(int quantity){
        this.quantity -= quantity;
    }

    public int getTotalprince() {
        return this.quantity * this.getDrink().getPrice();
    }


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
        return "CartItem{" +
                "이름=" + drink +
                ", quantity=" + quantity +
                ", totalprince=" + totalprince +
                '}';
    }
}
