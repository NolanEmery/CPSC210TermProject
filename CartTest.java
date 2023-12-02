package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {
    Cart cart1;
    Item item1;
    Item item2;

    @BeforeEach
    void setUp() {
        cart1 = new Cart();
        item1 = new Item();
        item2 = new Item();
        item1.setPrice(10);
        item2.setPrice(25);
    }

    @Test
    void cartTest() {
        assertEquals(0, cart1.getCart().size());
    }

    @Test
    void addItemToCartNTimesNEqualsOneSingleItemTest() {
        cart1.addItemToCartNTimes(item1, 1);
        assertTrue(cart1.getCart().contains(item1));
        assertEquals(1, cart1.getCart().size());
    }

    @Test
    void addItemToCartNTimesNGreaterThanOneSingleItemTest() {
        cart1.addItemToCartNTimes(item1, 25);
        assertTrue(cart1.getCart().contains(item1));
        assertEquals(25, cart1.getCart().size());
    }

    @Test
    void addItemToCartNTimesNEqualsOneMultipleItemsTest() {
        cart1.addItemToCartNTimes(item1, 1);
        assertTrue(cart1.getCart().contains(item1));
        assertEquals(1, cart1.getCart().size());
        cart1.addItemToCartNTimes(item2, 1);
        assertTrue(cart1.getCart().contains(item2));
        assertEquals(2, cart1.getCart().size());
    }

    @Test
    void addItemToCartNTimesNGreaterThanOneMultipleItemsTest() {
        cart1.addItemToCartNTimes(item1, 25);
        assertTrue(cart1.getCart().contains(item1));
        assertEquals(25, cart1.getCart().size());
        cart1.addItemToCartNTimes(item2, 25);
        assertTrue(cart1.getCart().contains(item2));
        assertEquals(50, cart1.getCart().size());
    }

    @Test
    void calculateTotalEmptyCartTest() {
        assertEquals(0, cart1.calculateTotal());
    }

    @Test
    void calculateTotalSingleItemNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        assertEquals(10, cart1.calculateTotal());
    }

    @Test
    void calculateTotalMultipleItemNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        cart1.addItemToCartNTimes(item2, 1);
        assertEquals(35, cart1.calculateTotal());
    }

    @Test
    void calculateTotalSingleItemNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        assertEquals(250, cart1.calculateTotal());
    }

    @Test
    void calculateTotalMultipleItemsNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        cart1.addItemToCartNTimes(item2, 25);
        assertEquals(875, cart1.calculateTotal());
    }

    @Test
    void calculateTaxEmptyCartTest() {
        assertEquals(0, cart1.calculateTax());
    }

    @Test
    void calculateTaxSingleItemNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        assertEquals(1.2, cart1.calculateTax());
    }

    @Test
    void calculateTaxMultipleItemsNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        cart1.addItemToCartNTimes(item2, 1);
        assertEquals(4.2, cart1.calculateTax());
    }

    @Test
    void calculateTaxSingleItemNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        assertEquals(30, cart1.calculateTax());
    }

    @Test
    void calculateTaxMultipleItemsNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        cart1.addItemToCartNTimes(item2, 25);
        assertEquals(105, cart1.calculateTax());
    }

    @Test
    void calculateTotalWithTaxEmptyCartTest() {
        assertEquals(0, cart1.calculateTotalWithTax());
    }

    @Test
    void calculateTotalWithTaxSingleItemNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        assertEquals(11.2, cart1.calculateTotalWithTax());
    }

    @Test
    void calculateTotalWithTaxMultipleItemsNEqualsOneTest() {
        cart1.addItemToCartNTimes(item1, 1);
        cart1.addItemToCartNTimes(item2, 1);
        assertEquals(39.2, cart1.calculateTotalWithTax());
    }

    @Test
    void calculateTotalWithTaxSingleItemNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        assertEquals(280, cart1.calculateTotalWithTax());
    }

    @Test
    void calculateTotalWithTaxMultipleItemsNGreaterThanOneTest() {
        cart1.addItemToCartNTimes(item1, 25);
        cart1.addItemToCartNTimes(item2, 25);
        assertEquals(980, cart1.calculateTotalWithTax());
    }
}
