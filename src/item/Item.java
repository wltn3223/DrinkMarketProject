package item;

import java.util.Objects;

public class Item {  // 모든 아이템은 ID와 이름 가격 존재
    private String iD;
    private String name;
    private int price;

    public Item(String name, int price) {
        this.iD = "Item" + (int)(Math.random()*1000); // 모든 항목은 생성시 고유 id를 갖게된다.
        this.name = name;                              // 생성자에 넣지 않아도 인스턴스 생성시 알아서 자동생성
        this.price = price;                            // id = "Item" + 숫자로 구성
    }

    public Item(String iD, String name, int price) { // id로 생성시 입력받은 id로 초기화
        this.iD = iD;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(iD, item.iD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD);
    }
}
