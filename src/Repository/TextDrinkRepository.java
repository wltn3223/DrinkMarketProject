package Repository;

import item.Drink;

import java.io.*;
import java.util.HashMap;

public class TextDrinkRepository implements DrinkRepository {
    private final HashMap<String, Drink> drinkDic = new HashMap<>();

    @Override
    public void loadDrink() {
        try { // If the file does not exist, process the exception.
            FileReader fr = new FileReader("drinkList.txt");
            BufferedReader br = new BufferedReader(fr);
            String drinkId;
            String[] info = new String[5];
            while ((drinkId = br.readLine()) != null) {
                if (drinkId.contains("Item")) {
                    info[0] = drinkId;
                    info[1] = br.readLine();
                    info[2] = br.readLine();
                    info[3] = br.readLine();
                    info[4] = br.readLine();
                    Drink drink = new Drink(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
                    drinkDic.put(info[0], drink);

                }


            }
            br.close();
            fr.close();

        } catch (IOException e) {
        }

    }

    @Override
    public void saveDrink(Drink drink) {
        FileWriter fw;
        try {
            if (new File("list.txt").exists()) {
                fw = new FileWriter("drinkList.txt", true);
            } else {
                fw = new FileWriter("drinkList.txt");
            }

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(drink.getId() + "\n");
            bw.write(drink.getKind() + "\n");
            bw.write(drink.getName() + "\n");
            bw.write(drink.getPrice() + "\n");
            bw.write(drink.getCapacity() + "\n");

            bw.close();
            fw.close();

        } catch (IOException e) {

        }
        loadDrink();
    }

    @Override
    public void printDrinkList() {
        System.out.println("ID\t\t\t| 종류\t\t\t| 이름\t\t\t\t| 가격\t\t\t| 용량");
        drinkDic.values().stream().forEach(
                drink -> System.out.printf("%-10s  %-10s  %-20s %-10s %-10s\n"
                ,drink.getId(),drink.getKind(),drink.getName(),drink.getPrice(),drink.getCapacity())
        );
    }

    @Override
    public Drink IsDrink(String Id) {
        if (!(drinkDic.containsKey(Id))) {
            return null;
        }
        return drinkDic.get(Id);
    }


}
