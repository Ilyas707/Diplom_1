import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType type;
    private String name;
    private float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "barbecue", 75f},
                {IngredientType.FILLING, "ham", 90f},
                {IngredientType.SAUCE, "mayonnaise", 60f}
        });
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testGetName() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(price, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void testGetType() {
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void testMockedIngredient() {
        Ingredient mockedIngredient = mock(Ingredient.class);
        when(mockedIngredient.getPrice()).thenReturn(100.0f);
        assertEquals(100.0f, mockedIngredient.getPrice(), 0.0f);
        verify(mockedIngredient).getPrice();
    }
}
