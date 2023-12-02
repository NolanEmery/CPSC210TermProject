package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriesTest {

    @Test
    void testCategories() {
        assertEquals(11, Categories.values().length);
    }
}
