import org.junit.Test;
import static org.junit.Assert.*;
import praktikum.IngredientType;


public class IngredientTypeTest {
    @Test
    public void selectSauceFromEnumIsCorrect() {
        assertEquals(IngredientType.valueOf("SAUCE"), IngredientType.SAUCE);
    }

    @Test
    public void selectFillingFromEnumIsCorrect() {
        assertEquals(IngredientType.valueOf("FILLING"), IngredientType.FILLING);
    }

    @Test
    public void valuesMethodReturnsAllEnumValues() {
        IngredientType[] expectedValues = {IngredientType.SAUCE, IngredientType.FILLING};
        assertArrayEquals(expectedValues, IngredientType.values());
    }

    @Test
    public void unknownEnumValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            IngredientType.valueOf("UNKNOWN");
        });
    }
}
