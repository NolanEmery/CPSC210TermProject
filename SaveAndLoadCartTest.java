package persistence;

import model.Cart;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveAndLoadCartTest {
    private Cart c1;
    private Item item1;
    private Item item2;
    private JsonWriter writer;
    private JsonReader reader;

    @BeforeEach
    void runBefore() {
        c1 = new Cart();
        item1 = new Item();
        item2 = new Item();
        item1.setName("Item1");
        item1.setPrice(10);
        item2.setName("Item2");
        item2.setPrice(5);
        writer = new JsonWriter(c1.getDestination());
        reader = new JsonReader(c1.getDestination());
    }

    @Test
    void saveEmptyCartTest() {
        try {
            writer.openCart(c1);
            writer.writeCart(c1);
            writer.close();
            Cart newCart = reader.readCart();
            assertEquals(0, newCart.getCart().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveOneItemNEqualsOneTest() {
        try {
            c1.addItemToCartNTimes(item1, 1);
            writer.openCart(c1);
            writer.writeCart(c1);
            writer.close();
            Cart newCart = reader.readCart();
            assertEquals(1, newCart.getCart().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveOneItemNGreaterThanOneTest() {
        try {
            c1.addItemToCartNTimes(item1, 25);
            writer.openCart(c1);
            writer.writeCart(c1);
            writer.close();
            Cart newCart = reader.readCart();
            assertEquals(25, newCart.getCart().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveMultipleItemsNEqualsOneTest() {
        try {
            c1.addItemToCartNTimes(item1, 1);
            c1.addItemToCartNTimes(item2, 1);
            writer.openCart(c1);
            writer.writeCart(c1);
            writer.close();
            Cart newCart = reader.readCart();
            assertEquals(2, newCart.getCart().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }

    @Test
    void saveMultipleItemsNGreaterThanOneTest() {
        try {
            c1.addItemToCartNTimes(item1, 25);
            c1.addItemToCartNTimes(item2, 25);
            writer.openCart(c1);
            writer.writeCart(c1);
            writer.close();
            Cart newCart = reader.readCart();
            assertEquals(50, newCart.getCart().size());
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }
    }
}
