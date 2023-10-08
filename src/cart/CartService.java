package cart;


import user.Customer;

public interface CartService {
    int choiceMenu();
    void clearCart();
    void printCart();
    void addItem();
    void addQuantity();
    void removeItem();

    void subtractItem();

    void order(Customer customer);


}
