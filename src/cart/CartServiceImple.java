package cart;

import Repository.DrinkRepository;
import item.Drink;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;


public class CartServiceImple implements CartService {

    ArrayList<CartItem> cartList = new ArrayList<>();
    DrinkRepository drinkRepository;
    private BufferedReader br;

    public CartServiceImple(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int choiceMenu() {
        drinkRepository.loadDrink();
        System.out.println("===================================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("1.고객정보확인\n2.내 장바구니 확인\n3.장바구니 품목추가\n4.장바구니 품목 수량추가\n5.장바구니 품목 제거\n6.장바구니 품목 수량제거\n0.종료");
        int menu;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("똑바로 입력해주세요");
            return -1;
        }
        return menu;
    }

    public void clearCart() {
        cartList.clear();

    }

    @Override
    public void printCart() {
        cartList.forEach(System.out::println);

    }

    @Override
    public void addItem() {
        String id;
        Drink drink;
        drinkRepository.printDrinkList();
        System.out.println("추가할 음료의 ID를 입력해주세요");
        try {
            id = br.readLine();
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
            return;
        }
        drink = drinkRepository.IsDrink(id);
        if (drink != null) {
            cartList.add(new CartItem(drink));
        } else {
            System.out.println("잘못된ID를 입력하셨습니다.");
        }


    }

    public void addQuantity() {
        String id;
        int num;
        System.out.println("수량을 추가할 음료의 ID를 입력해주세요");
        try {
            id = br.readLine();
            if (isCartList(id).isPresent()) {
                CartItem cartitem = isCartList(id).get();
                System.out.println("추가할 수량(개수)을 입력해주세요");
                num = Integer.parseInt(br.readLine());
                cartitem.addQuantity(num);
                System.out.println("수량이 추가되었습니다.");
            } else {
                System.out.println("없는 id입니다.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void removeItem() {
        String id;
        int num;
        System.out.println("수량을 감소시킬 음료의 ID를 입력해주세요");
        try {
            id = br.readLine();
            if (isCartList(id).isPresent()) {
                CartItem cartitem = isCartList(id).get();
                System.out.println("감소시킬 수량(개수)을 입력해주세요");
                num = Integer.parseInt(br.readLine());
                cartitem.subtractQuantity(num);
                System.out.println("수량이 감소되었습니다.");
            } else {
                System.out.println("없는 id입니다.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void subtractItem() {
        String id;
        System.out.println("감소시킬 음료의 수량을 입력해주세요");
        try {
            id = br.readLine();
            if (isCartList(id).isPresent()) {
                CartItem cartitem = isCartList(id).get();
                cartList.remove(cartitem);
                System.out.println("해당음료가 장바구니에서 제거되었습니다.");
            } else {
                System.out.println("없는 id입니다.");
            }
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    public Optional<CartItem> isCartList(String id) {
        return cartList.stream()
                .filter(item -> item.getDrink().getId().equals(id))
                .findFirst();

    }

}
