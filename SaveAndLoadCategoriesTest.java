//package persistence;
//
//import model.Cart;
//import model.Category;
//import model.Inventory;
//import model.Item;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class SaveAndLoadCategoriesTest {
//    private Category cat1;
//    private Inventory i1;
//    private Item item1;
//    private Item item2;
//    private JsonWriter writer;
//    private JsonReader reader;
//
//    @BeforeEach
//    void runBefore() {
//        cat1 = new Category();
//        i1 = new Inventory();
//        item1 = new Item();
//        item2 = new Item();
//        cat1.setName("Cat1");
//        cat1.setNumber(1);
//        item1.setName("Item1");
//        item1.setPrice(10);
//        item2.setName("Item2");
//        item2.setPrice(5);
//        writer = new JsonWriter(i1.getCategoriesDestination());
//        reader = new JsonReader(i1.getCategoriesDestination());
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals("Cat1", cat1.getName());
//        assertEquals(1, cat1.getNumber());
//    }
//
//    @Test
//    void saveEmptyCategoryTest() {
//        try {
//            writer.openCategories();
//            writer.writeCategories();
//            writer.close();
//            Category newCategory = reader.readCategories();
//            assertEquals(0, newCategory.getItemsInCategory().size());
//        } catch (Exception e) {
//            System.out.println("File doesn't exist");
//        }
//    }
//
//    @Test
//    void saveOneItemNEqualsOneTest() {
//        try {
//            cat1.addToCategoryNTimes(item1, 1);
//            writer.openCategories();
//            writer.writeCategories();
//            writer.close();
//            Category newCategory = reader.readCategories();
//            assertEquals(1, newCategory.getItemsInCategory().size());
//        } catch (Exception e) {
//            System.out.println("File doesn't exist");
//        }
//    }
//
//    @Test
//    void saveOneItemNGreaterThanOneTest() {
//        try {
//            cat1.addToCategoryNTimes(item1, 25);
//            writer.openCategories();
//            writer.writeCategories();
//            writer.close();
//            Category newCategory = reader.readCategories();
//            assertEquals(25, newCategory.getItemsInCategory().size());
//        } catch (Exception e) {
//            System.out.println("File doesn't exist");
//        }
//    }
//
//    @Test
//    void saveMultipleItemsNEqualsOneTest() {
//        try {
//            cat1.addToCategoryNTimes(item1, 1);
//            cat1.addToCategoryNTimes(item2, 1);
//            writer.openCategories();
//            writer.writeCategories();
//            writer.close();
//            Category newCategory = reader.readCategories();
//            assertEquals(2, newCategory.getItemsInCategory().size());
//        } catch (Exception e) {
//            System.out.println("File doesn't exist");
//        }
//    }
//
//    @Test
//    void saveMultipleItemsNGreaterThanOneTest() {
//        try {
//            cat1.addToCategoryNTimes(item1, 25);
//            cat1.addToCategoryNTimes(item2, 25);
//            writer.openCategories();
//            writer.writeCategories();
//            writer.close();
//            Category newCategory = reader.readCategories();
//            assertEquals(50, newCategory.getItemsInCategory().size());
//        } catch (Exception e) {
//            System.out.println("File doesn't exist");
//        }
//    }
//}
