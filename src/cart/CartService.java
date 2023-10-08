package cart;



public interface CartService {
    int choiceMenu();
    void clearCart();
    void printCart();
    void addItem();
    public void addQuantity();
    void removeItem();

    void subtractItem();


}
