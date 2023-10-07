package item;

import java.util.Objects;

public class Drink {
    private String name;
    private String kind;
    private int capacity;
    private String brand;

    public Drink(String name, String kind, int capacity, String brand) {
        this.name = name;
        this.kind = kind;
        this.capacity = capacity;
        this.brand = brand;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
