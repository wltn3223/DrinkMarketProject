package item;

import java.util.Objects;

public class Item {
    private String iD;
    private String name;
    private int price;

    public Item(String name, int price) {
        this.iD = "Item" + (int)(Math.random()*1000);
        this.name = name;
        this.price = price;
    }

    public Item(String serialNum, String name, int price) {
        this.iD = serialNum;
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
