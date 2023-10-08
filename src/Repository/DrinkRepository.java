package Repository;

public interface DrinkRepository {
    void loadDrink();
    void saveDrink(String[] info);

   void lookDrinkList();

   boolean IsDrink(String serialNum);
}
