package item;

import java.util.Objects;

public class Drink {

    private  static int drinkNum = 0;
    private String num;
    private String name;
    private String kind;

    private int price;
    private int capacity;

    public Drink(String name, String kind, int capacity, int price) {
        this.num = "drink"+ drinkNum++;
        this.name = name;
        this.kind = kind;
        this.capacity = capacity;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
        return  ((Drink)obj).getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
