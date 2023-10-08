package Repository;

import item.Drink;

public interface DrinkRepository {
    void loadDrink();
    void saveDrink(Drink drink);

   void printDrinkList();

   Drink IsDrink(String serialNum);
}
