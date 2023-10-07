package item;

import java.util.Objects;

public class Item {
    private String serialNum;
    private String name;
    private int price;

    public Item(String name, int price) {
        this.serialNum = "Item" + (int)(Math.random()*1000);
        this.name = name;
        this.price = price;
    }

    public Item(String serialNum, String name, int price) {
        this.serialNum = serialNum;
        this.name = name;
        this.price = price;
    }

    public String getSerialNum() {
        return serialNum;
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
        return Objects.equals(serialNum, item.serialNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNum);
    }
}
