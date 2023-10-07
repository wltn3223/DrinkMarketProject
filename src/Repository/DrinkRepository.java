package Repository;

import java.util.ArrayList;

public interface DrinkRepository {
    void loadDrink();
    void saveDrink(String[] info);

   void selectDrink();
}
