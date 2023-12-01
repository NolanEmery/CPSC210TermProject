package persistence;

import model.Inventory;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Categories.BOOKS;
import static model.Categories.TOYS_AND_GAMES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadInventoryTest {
    private Inventory i1;
    private Item item1;
    private Item item2;
    private JsonWriter writer;
    private JsonReader reader;

    @BeforeEach
    void runBefore() {
        i1 = new Inventory();
        item1 = new Item();
        item2 = new Item();
        item1.setName("Item1");
        item1.setPrice(10);
        item1.setCategory(2);
        item2.setName("Item2");
        item2.setPrice(5);
        item2.setCategory(4);
        writer = new JsonWriter(i1.getInventoryDestination());
        reader = new JsonReader(i1.getInventoryDestination());
    }

    @Test
    void saveEmptyInventoryTest() {
        try {
            assertEquals(BOOKS, item1.getCategory());
            assertEquals(TOYS_AND_GAMES, item2.getCategory());
            writer.openInventory(i1);
            writer.writeInventory(i1);
            writer.close();
            Inventory newInventory = reader.readInventory();
            assertEquals(0, newInventory.getInventory().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveOneItemNEqualsOneTest() {
        try {
            i1.addToInventoryNTimes(item1, 1);
            writer.openInventory(i1);
            writer.writeInventory(i1);
            writer.close();
            Inventory newInventory = reader.readInventory();
            assertEquals(1, newInventory.getInventory().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveOneItemNGreaterThanOneTest() {
        try {
            i1.addToInventoryNTimes(item1, 25);
            writer.openInventory(i1);
            writer.writeInventory(i1);
            writer.close();
            Inventory newInventory = reader.readInventory();
            assertEquals(25, newInventory.getInventory().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveMultipleItemsNEqualsOneTest() {
        try {
            i1.addToInventoryNTimes(item1, 1);
            i1.addToInventoryNTimes(item2, 1);
            writer.openInventory(i1);
            writer.writeInventory(i1);
            writer.close();
            Inventory newInventory = reader.readInventory();
            assertEquals(2, newInventory.getInventory().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveMultipleItemsNGreaterThanOneTest() {
        try {
            i1.addToInventoryNTimes(item1, 25);
            i1.addToInventoryNTimes(item2, 25);
            writer.openInventory(i1);
            writer.writeInventory(i1);
            writer.close();
            Inventory newInventory = reader.readInventory();
            assertEquals(50, newInventory.getInventory().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }
}
