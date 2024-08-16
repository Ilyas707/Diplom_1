import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private final Burger burger = new Burger();

    @Mock
    Ingredient ingredientFirst;
    @Mock
    Ingredient ingredientSecond;
    @Mock
    Database database;

    private final List<Bun> buns = Arrays.asList(new Bun("test bun", 60.5f));
    private Bun defaultBun;

    @Before
    public void setDefaultBun() {
        Mockito.when(database.availableBuns()).thenReturn(buns);
        defaultBun = database.availableBuns().get(0);
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(defaultBun);
        assertEquals("Некорректное наименование булки", "test bun", burger.bun.getName());
    }

    @Test
    public void checkGetPrice() {
        Mockito.when(ingredientFirst.getPrice()).thenReturn(125.5F);
        Mockito.when(ingredientSecond.getPrice()).thenReturn(250F);
        burger.setBuns(defaultBun);
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        float expectedBurgerPrice = 496.5F;
        assertEquals("Некорректная цена бургера", expectedBurgerPrice, burger.getPrice(), 0);
    }

    @Test
    public void checkGetReceipt() {
        Mockito.when(ingredientFirst.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientFirst.getName()).thenReturn("hot sauce");
        Mockito.when(ingredientFirst.getPrice()).thenReturn(100F);
        Mockito.when(ingredientSecond.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientSecond.getName()).thenReturn("sausage");
        Mockito.when(ingredientSecond.getPrice()).thenReturn(300F);
        burger.setBuns(defaultBun);
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        String expected = String.format(
                "(==== %s ====)%n= sauce %s =%n= filling %s =%n(==== %s ====)%n%nPrice: %.6f%n",
                "test bun", "hot sauce", "sausage", "test bun", burger.getPrice()
        );
        assertEquals(expected, burger.getReceipt());
    }

    @Test
    public void checkRemoveIngredient() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientSecond, burger.ingredients.get(0));
    }

    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientFirst, burger.ingredients.get(1));
        assertEquals(ingredientSecond, burger.ingredients.get(0));
    }
}
