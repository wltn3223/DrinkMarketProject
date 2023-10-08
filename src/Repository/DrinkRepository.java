package Repository;

import item.Drink;

public interface DrinkRepository {
    void loadDrink();
    void saveDrink(String[] info);

   void printDrinkList();

   Drink IsDrink(String serialNum);
}
