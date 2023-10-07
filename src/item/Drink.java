package item;

import java.util.Objects;

public class Drink extends Item{

    private String name;
    private String kind;

    private int price;
    private int capacity;

    public Drink(String kind, String name, int price,int capacity) {
        super(name, price);
        this.kind = kind;
        this.capacity = capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return getSerialNum();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Drink)){
            return false;
        }
        return  ((Drink)obj).getNum().equals(this.getNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNum());
    }
}
