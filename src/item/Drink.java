package item;

import java.util.Objects;

public class Drink extends Item{

    private String  capacity;
    private String kind;

    public Drink(String kind, String name, int price,String capacity) {
        super(name, price);
        this.kind = kind;
        this.capacity = capacity;
    }

    public Drink(String iD,String kind, String name, int price,String capacity) {
        super(iD, name, price);
        this.kind = kind;
        this.capacity = capacity;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Drink)){
            return false;
        }
        return  ((Drink)obj).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return this.getId() + "\t" + kind + "\t" + getName() + "\t" + getPrice() + "원\t" + capacity +"\n";
    }
}
