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

    public Drink(String serialNum,String kind, String name, int price,String capacity) {
        super(serialNum, name, price);
        this.kind = kind;
        this.capacity = capacity;
    }



    public String getNum() {
        return getSerialNum();
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

    @Override
    public String toString() {
        return this.getSerialNum() + "\t" + kind + "\t" + getName() + "\t" + getPrice() + "원\t" + capacity +"\n";
    }
}
