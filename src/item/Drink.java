package item;

import java.util.Objects;

public class Drink extends Item{

    private final String capacity; //용량
    private final String kind; // 종류

    public Drink(String kind, String name, int price,String capacity) {
        super(name, price);
        this.kind = kind;
        this.capacity = capacity;
    }

    public Drink(String iD,String kind, String name, int price,String capacity) {
        super(iD, name, price);
        this.kind = kind;
        this.capacity = capacity ;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object obj) { // Item을 상속한 클래스는 iD를 통해 비교 가능
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
        return this.getId() + "\t\t| " + kind + "\t\t| " + getName() + "\t\t| " + getPrice() + "원\t\t| " + capacity;
    }
}
