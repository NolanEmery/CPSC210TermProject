package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Categories.SPORTING_GOODS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    Item i1;
    Item i2;

    @BeforeEach
    void setUp() {
        i1 = new Item();
        i2 = new Item();
        i1.setName("Item1");
        i2.setName("Item2");
        i1.setPrice(10);
        i2.setPrice(25);
        i1.setCategory(1);
        i2.setCategory(0);
    }

    @Test
    void itemTest() {
        assertEquals("Item1", i1.getName());
        assertEquals(10, i1.getPrice());
        assertEquals(SPORTING_GOODS, i1.getCategory());
        assertEquals(1, i1.getCategoryNum());
    }

    @Test
    void itemCategoryZeroTest() {
        assertEquals("Item2", i2.getName());
        assertEquals(25, i2.getPrice());
        assertEquals(Categories.OTHER, i2.getCategory());
        assertEquals(11, i2.getCategoryNum());
    }
}
