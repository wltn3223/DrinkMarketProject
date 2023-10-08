package cart;


import Exceptions.AllException;

public interface CartService {

    void clearCart();
    void printCart();
    void addItem(String serialNum);
    void removeItem(String serialNum) throws AllException;

    void subtractItem(String serialNum, int quantity) throws AllException;


}
