package Repository;

import item.Drink;
import user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TextDrinkRepository implements  DrinkRepository{
    private final HashMap<String, Drink> drinkDic = new HashMap<>();
    @Override
    public void loadDrink() {
        try { // If the file does not exist, process the exception.
            FileReader fr = new FileReader("drinklist.txt");
            BufferedReader br = new BufferedReader(fr);
            String bookNum;
            String[] info = new String[4];
            while ((bookNum = br.readLine()) != null) {
                info[0] = br.readLine().trim();
                info[1] = br.readLine().trim();
                info[2] = br.readLine().trim();
                info[3] = br.readLine().trim();
                drinkDic.put(bookNum, new Drink(info[0], info[1],Integer.parseInt(info[2]) , Integer.parseInt(info[3])));

            }

            br.close();
            fr.close();

        } catch (IOException e) {
        }

    }

    @Override
    public void addDrink(ArrayList<String> info) {
        FileWriter fw;
        try {
            if (new File("list.txt").exists()) {
                fw = new FileWriter("drinklist.txt.txt", true);
            } else {
                fw = new FileWriter("drinklist.txt.txt");
            }

            BufferedWriter bw = new BufferedWriter(fw);

            for (String data : info) {
                bw.write(data + "\n");
            }
            bw.close();
            fw.close();

        } catch (IOException e) {

        }

    }


}
