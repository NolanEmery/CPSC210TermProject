package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryTest {
    Inventory i1;
    Item item1;
    Item item2;

    @BeforeEach
    void setUp() {
        i1 = new Inventory();
        item1 = new Item();
        item2 = new Item();
        item1.setName("Item1");
        item2.setName("Item2");
    }

//    @Test
//    void inventoryTest() {
//        assertEquals(11, i1.getCategories().size());
//    }

    @Test
    void addToInventoryNTimesNEqualsOneTest() {
        i1.addToInventoryNTimes(item1, 1);
        assertTrue(i1.getInventory().contains(item1));
        assertEquals(1, i1.getInventory().size());
    }

    @Test
    void addToInventoryNTimesNGreaterThenOneTest() {
        i1.addToInventoryNTimes(item1, 25);
        assertTrue(i1.getInventory().contains(item1));
        assertEquals(25, i1.getInventory().size());
    }

    @Test
    void addToInventoryNTimesNEqualsOneMultipleItemsTest() {
        i1.addToInventoryNTimes(item1, 1);
        assertTrue(i1.getInventory().contains(item1));
        assertEquals(1, i1.getInventory().size());
        i1.addToInventoryNTimes(item2, 1);
        assertTrue(i1.getInventory().contains(item2));
        assertEquals(2, i1.getInventory().size());
    }

    @Test
    void addToInventoryNTimesNGreaterThanOneMultipleItemsTest() {
        i1.addToInventoryNTimes(item1, 25);
        assertTrue(i1.getInventory().contains(item1));
        assertEquals(25, i1.getInventory().size());
        i1.addToInventoryNTimes(item2, 25);
        assertTrue(i1.getInventory().contains(item2));
        assertEquals(50, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesNEqualsSizeNEqualsOneTest() {
        i1.addToInventoryNTimes(item1, 1);
        i1.removeFromInventoryNTimes("Item1", 1);
        assertEquals(0, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesNEqualsSizeNGreaterThenOneTest() {
        i1.addToInventoryNTimes(item1, 25);
        i1.removeFromInventoryNTimes("Item1", 25);
        assertEquals(0, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesNLessThanSizeNEqualsOneTest() {
        i1.addToInventoryNTimes(item1, 25);
        i1.removeFromInventoryNTimes("Item1", 1);
        assertEquals(24, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesNLessThanSizeNGreaterThanOneTest() {
        i1.addToInventoryNTimes(item1, 25);
        i1.removeFromInventoryNTimes("Item1", 10);
        assertEquals(15, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesStringNotEqualTest() {
        i1.addToInventoryNTimes(item1, 25);
        i1.removeFromInventoryNTimes("Item2", 25);
        assertEquals(25, i1.getInventory().size());
    }

    @Test
    void removeFromInventoryNTimesStringNotFirstItemTest() {
        i1.addToInventoryNTimes(item1, 25);
        i1.addToInventoryNTimes(item2, 25);
        i1.removeFromInventoryNTimes("Item2", 25);
        assertEquals(25, i1.getInventory().size());
    }

    @Test
    void getInventoryDestinationTest() {
        assertEquals("./data/inventory.json", i1.getInventoryDestination());
    }

//    @Test
//    void findCategoryToRemoveFromCategoriesContainsStringTest() {
//        i1.getCategories().get(0).addToCategoryNTimes(item1, 1);
//        i1.findCategoryToRemoveFrom("Item1", 1);
//        assertEquals(0, i1.getCategories().get(0).getItemsInCategory().size());
//    }
//
//    @Test
//    void findCategoryToRemoveFromCategoriesDoesNotContainStringTest() {
//        i1.getCategories().get(0).addToCategoryNTimes(item1, 1);
//        i1.findCategoryToRemoveFrom("Item2", 1);
//        assertEquals(1, i1.getCategories().get(0).getItemsInCategory().size());
//    }
}