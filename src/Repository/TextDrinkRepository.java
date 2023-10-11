package Repository;

import item.Drink;

import java.io.*;
import java.util.HashMap;

public class TextDrinkRepository implements DrinkRepository { // 출시된 음료 저장소
    private final static HashMap<String, Drink> drinkDic = new HashMap<>();// Key = drink의 id, value = drink 인스턴스

    @Override
    public void loadDrink() {
        try { // txt로된 drink저장소에서 drink 정보를 가져와 map에 저장
            FileReader fr = new FileReader("drinkList.txt");
            BufferedReader br = new BufferedReader(fr);
            String drinkId;
            String[] info = new String[5];
            while ((drinkId = br.readLine()) != null) {  // drink의 id = "Item" + 숫자로 구성
                if (drinkId.contains("Item")) { //txt에서 한줄을 읽어 Item이라는 글자 포함여부 확인후 읽어서 저장
                    info[0] = drinkId;
                    info[1] = br.readLine();
                    info[2] = br.readLine();
                    info[3] = br.readLine();
                    info[4] = br.readLine();
                    Drink drink = new Drink(info[0], info[1], info[2], Integer.parseInt(info[3]), info[4]);
                    drinkDic.put(info[0], drink);
                    // 읽은후 객체 생성후 Map에 저장

                }


            }
            br.close();
            fr.close();

        } catch (IOException e) { // 파일 첫실행시 읽을 내용이 없기때문에 예외처리 프로그램 진행을 위함
        }

    }

    @Override
    public void saveDrink(Drink drink) {
        FileWriter fw;
        try {
            if (new File("list.txt").exists()) {  // txt파일이 존재시 그파일에 내용을 이어서씀
                                                            // 파일이 없을시 만들어씀 (처음사용시)
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

        } catch (IOException e) { // 예외처리

        }
        loadDrink();
    }

    @Override
    public void printDrinkList() { // drink목록을 출력
        System.out.println("ID\t\t\t| 종류\t\t\t| 이름\t\t\t\t| 가격\t\t\t| 용량");
        drinkDic.values().stream().forEach( // drink 객체를 스트림을 통해 하나씩 빼서 print
                drink -> System.out.printf("%-10s  %-10s  %-20s %-10s %-10s\n"
                ,drink.getId(),drink.getKind(),drink.getName(),drink.getPrice(),drink.getCapacity())
        );
    }

    @Override
    public Drink IsDrink(String Id) { // id를 받아 key에 id가 존재시 drink반환 없으면 null 반환
        if (!(drinkDic.containsKey(Id))) {
            return null;
        }
        return drinkDic.get(Id);
    }


}
