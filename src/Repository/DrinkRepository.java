package Repository;

import item.Drink;

public interface DrinkRepository {
    void loadDrink(); // txt로 된 음료 저장소에서 Drink 목록을 가져와 map에 추가
    void saveDrink(Drink drink); // 음료 저장 txt에

   void printDrinkList(); // 음료 목록출력

   Drink IsDrink(String id); // id를 통해 음료 존재 여부 확인
}
