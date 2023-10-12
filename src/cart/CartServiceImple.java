package cart;

import Repository.DrinkRepository;
import Repository.UserRepository;
import item.Drink;
import user.Customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Optional;


public class CartServiceImple implements CartService {

    ArrayList<CartItem> cartList = new ArrayList<>(); // 장바구니 항목 배열
    DrinkRepository drinkRepository; // 장바구니 서비스는 음료 저장소를 이용
    UserRepository userRepository;
    private BufferedReader br; // 키보드로 입력받음

    public CartServiceImple(DrinkRepository drinkRepository,UserRepository userRepository) { // 생성자를통해 음료저장소 주입 (의존성 주입)
        this.drinkRepository = drinkRepository;
        this.userRepository = userRepository;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int choiceMenu() { // 메뉴선택
        drinkRepository.loadDrink(); // 메뉴 선택 이후를 위해 음료목록 불러옴
        System.out.println("===================================================");
        System.out.println("메뉴를 선택하세요");
        System.out.println("""
                1.고객정보확인
                2.현금충전
                3.내 장바구니 확인
                4.장바구니 품목추가
                5.장바구니 품목 수량추가
                6.장바구니 품목 제거
                7.장바구니 품목 수량제거
                8.장바구니비우기
                9.주문하기
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

    public void clearCart() { // 장바구니 비우기
    	if(cartList.size() != 0) {
    		System.out.println("이미 장바구니가 비어있습니다.");
    		return;
    	}
        cartList.clear();
        System.out.println("장바구니를 비웠습니다. 확인해주세요.");

    }

    @Override
    public void printCart() { // 장바구니의 항목 출력
        cartList.forEach(System.out::println);
        System.out.println("===================================================");

    }

    @Override
    public void addItem() { // 장바구니에 음료추가 이미 있으면 추가하지않음
        String id;
        Drink drink;
        drinkRepository.printDrinkList();
        System.out.println("추가할 음료의 ID를 입력해주세요");


        try {
            id = br.readLine();  // 욤료 저장소에 입력받은 id를 통해 drink객체 있는지 확인 없으면 메서드 종료
            drink = drinkRepository.IsDrink(id);
            if (drink == null){
                System.out.println("Id를 잘못입력하셨습니다. 다시시도해주세요.");
                return;
            }
            if (!(isCartList(id).isPresent())) { //
                cartList.add(new CartItem(drink));
                System.out.println("추가되었습니다.");
            } else { // 이미 해당 id를 가진 drink가 장바구니에 있을시 메시지 출력후 종료
                System.out.println("해당 음료는 이미 장바구니에 존재합니다. 다시 시도해주세요.");
            }

        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    public void addQuantity() { //ID를 통해 장바구니에 음료 존재여부 확인후 수량 추가
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
    public void subtractItem() { // ID를 통해 장바구니에 음료 존재여부 확인후 수량 감소
        printCart();                // 장바구니에 담은 수량보다 같거나 많이 입력시 다시 시도
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
    public void removeItem() { // ID를 통해 장바구니에 음료 존재여부 확인후 제거
        printCart();
        String id;
        System.out.println("제거할 음료의 id를 입력해주세요");
        try {
            id = br.readLine();
            if (!(isCartList(id).isPresent())) { // 음료가 없을때
                System.out.println("없는 id입니다.");
            } else {
                CartItem cartitem = isCartList(id).get();  // 음료가 있을때
                cartList.remove(cartitem);
                System.out.println("해당음료가 장바구니에서 제거되었습니다.");
            }
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요.");
        }


    }

    @Override
    public void order(Customer customer) { // 주문하기 customer객체를 받아 customer정보와 장바구니 내역 내 보유 현금, 장바구니 총가격, 주문후 보유잔액 출력후 저장.
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
        System.out.println("보유 잔액" + customer.getMoney());
        System.out.println("총가격:" + totalPrice);
        if(customer.getMoney()<totalPrice){
            System.out.println("잔액이부족합니다");
            return;
        }

        System.out.println("결제후 잔액:" + (customer.getMoney() - totalPrice));
        customer.setMoney(customer.getMoney() - totalPrice);
        cartList.clear();
        userRepository.updateUser(customer);
        System.out.println("====================주문완료=========================");


    }

    @Override // 현금 충전 입력한 금액 충전
    public void depositMoney(Customer customer) {
        System.out.println("충전 하실 금액을 입력하세요(단위:원)");
        try {
        	Customer depositCustomer = customer;
            String money = br.readLine();
            System.out.println("충전 전 금액:"  + depositCustomer.getMoney());
            depositCustomer.depositMoney(Integer.parseInt(money));
            System.out.println("충전 후 금액:"  + depositCustomer.getMoney());
            userRepository.updateUser(customer);
        } catch (Exception e) {
            System.out.println("올바른 형식으로 입력해주세요");
        }

    }

    public Optional<CartItem> isCartList(String id) { // 음료객체가 있으면 drink객체 반환 없으면 null반환
        return cartList.stream()                        // optional로 감싸서 반환 받음(null 예방)
                .filter(item -> item.getDrink().getId().equals(id))
                .findFirst();

    }

}
