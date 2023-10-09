package Repository;

import item.Drink;

public interface DrinkRepository {
    void loadDrink(); // txt로 된 음료 저장소에서 Drink 목록을 가져와 list에 추가
    void saveDrink(Drink drink); //

   void printDrinkList();

   Drink IsDrink(String serialNum);
}
