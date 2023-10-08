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
            String serialNum;
            String[] info = new String[5];
            while ((serialNum = br.readLine()) != null) {
                if (serialNum.contains("Item")) {
                    info[0] = serialNum;
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
    public void saveDrink(String[] info) {
        Drink drink = new Drink(info[0], info[1], Integer.parseInt(info[2]), info[3]);
        FileWriter fw;
        try {
            if (new File("list.txt").exists()) {
                fw = new FileWriter("drinkList.txt", true);
            } else {
                fw = new FileWriter("drinkList.txt");
            }

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(drink.getId() + "\n");
            for (String data : info) {
                bw.write(data + "\n");
            }
            bw.close();
            fw.close();

        } catch (IOException e) {

        }
        loadDrink();
    }

    @Override
    public void printDrinkList() {
        System.out.println("ID\t종류\t이름\t가격\t용량");
        drinkDic.values().stream().forEach(System.out::println);
    }

    @Override
    public Drink IsDrink(String Id) {
        if (!(drinkDic.containsKey(Id))) {
            return null;
        }
        return drinkDic.get(Id);
    }


}
