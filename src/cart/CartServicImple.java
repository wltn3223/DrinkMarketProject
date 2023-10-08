package cart;

import Exceptions.AllException;
import Repository.DrinkRepository;
import item.Drink;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class CartServicImple implements  CartService{

    ArrayList<CartItem> cartList = new ArrayList<>();
    DrinkRepository drinkRepository;
    private BufferedReader br;

    public CartServicImple(DrinkRepository drinkRepository) {

        this.drinkRepository = drinkRepository;
        br =  new BufferedReader(new InputStreamReader(System.in));
    }
    public void clearCart() {
        cartList.clear();

    }

    @Override
    public void printCart() {
        cartList.forEach(System.out::println);

    }

    @Override
    public void addItem(String serialNum) {
        drinkRepository.loadDrink();
        
        if(cartList.contains(new CartItem(drink))){
            System.out.println("해당음료는 이미 장바구니에 있습니다.");
        }
        else {
            System.out.println("해당음료를 장바구니에 추가합니다.");
            cartList.add(new CartItem(drink));
        }

    }
    public void addQuantity(Drink drink, int quantity)  {
        CartItem cartItem = isdrinkInCart(drink);
        cartItem.addQuantity(quantity);
    }

    @Override
    public void removeItem(String serialNum) {
        cartList.remove(isdrinkInCart(drink));
        System.out.println("해당음료가 장바구니에서 제거되었습니다.");

    }

    @Override
    public void subtractItem(String serialNum, int quantity)  {
        CartItem cartItem = isdrinkInCart(drink);
        cartItem.subtractQuantity(quantity);
        System.out.println("해당음료의 수량이 감소 되었습니다.");


    }
    public CartItem isdrinkInCart(Drink drink) {
        return cartList.stream().filter(cartItem -> cartItem.getDrink().equals(drink))
                .findFirst()
                .orElseThrow(() -> new AllException("해당 음료는 장바구니에 없습니다."));

    }
}
