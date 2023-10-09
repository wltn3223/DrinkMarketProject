package cart;

import Repository.DrinkRepository;
import item.Drink;
import user.Customer;

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
        System.out.println("""
                1.고객정보확인
                2.내 장바구니 확인
                3.장바구니 품목추가
                4.장바구니 품목 수량추가
                5.장바구니 품목 제거
                6.장바구니 품목 수량제거
                7.장바구니비우기
                8.주문하기
                0.종료""");
        int menu;
        try {
            menu = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
            return -1;
        }
        return menu;
    }

    public void clearCart() {
        cartList.clear();
        System.out.println("장바구니를 비웠습니다. 확인해주세요.");

    }

    @Override
    public void printCart() {
        cartList.forEach(System.out::println);
        System.out.println("===================================================");

    }

    @Override
    public void addItem() {
        String id;
        Drink drink;
        drinkRepository.printDrinkList();
        System.out.println("추가할 음료의 ID를 입력해주세요");


        try {
            id = br.readLine();
            drink = drinkRepository.IsDrink(id);
            if (drink == null){
                System.out.println("Id를 잘못입력하셨습니다. 다시시도해주세요.");
                return;
            }
            if (!(isCartList(id).isPresent())) {
                cartList.add(new CartItem(drink));
                System.out.println("추가되었습니다.");
            } else {
                System.out.println("해당 음료는 이미 장바구니에 존재합니다. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    public void addQuantity() {
        printCart();
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
                System.out.println("없는 id입니다. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void subtractItem() {
        printCart();
        String id;
        int num;
        System.out.println("수량을 감소시킬 음료의 ID를 입력해주세요");
        try {
            id = br.readLine();
            if (!(isCartList(id).isPresent())) {
                System.out.println("없는 id입니다. 다시 시도해주세요.");
                return;
            }
            CartItem cartitem = isCartList(id).get();
            System.out.println("감소시킬 수량(개수)을 입력해주세요");
            num = Integer.parseInt(br.readLine());
            if(cartitem.getQuantity()>num) {
                cartitem.subtractQuantity(num);
                System.out.println("수량이 감소되었습니다.");
            }
            else {
                System.out.println("보유수량보다 큰 수량을 입력하셨습니다. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void removeItem() {
        printCart();
        String id;
        System.out.println("감소시킬 음료의 수량을 입력해주세요");
        try {
            id = br.readLine();
            if (!(isCartList(id).isPresent())) {
                System.out.println("없는 id입니다.");
            } else {
                CartItem cartitem = isCartList(id).get();
                cartList.remove(cartitem);
                System.out.println("해당음료가 장바구니에서 제거되었습니다.");
            }
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void order(Customer customer) {
        int totalPrice = 0;
        if (cartList.isEmpty()){
            System.out.println("장바구니에 항목이 없습니다. 항목을 추가한 후 시도해주세요.");
            return;
        }
        System.out.println("====================고객정보=========================");
        System.out.println(customer);
        System.out.println("====================주문내역=========================");
        printCart();
        for (CartItem cartItem:cartList){
            totalPrice += cartItem.getTotalprince();
        }
        System.out.println("총가격:" + totalPrice);
        System.out.println("====================주문완료=========================");

    }

    public Optional<CartItem> isCartList(String id) {
        return cartList.stream()
                .filter(item -> item.getDrink().getId().equals(id))
                .findFirst();

    }

}
