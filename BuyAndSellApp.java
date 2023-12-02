package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Scanner for user input:
// SIX CASES (user stories):
// 1. Inventory
// 2. Cart
// 3. Categories
// 4. Save
// 5. Load
// 6. Checkout
// Each has helpers for smaller tasks
public class BuyAndSellApp {
    private Inventory inventory;
    private Cart cart;
    private Item nextItem;

    private JsonWriter inventoryWriter;
    private JsonWriter cartWriter;
    private JsonReader inventoryReader;
    private JsonReader cartReader;

    private int quantity;
    private Scanner scanOption;

    public BuyAndSellApp() {
        runBuyAndSellApp();
    }

    // MODIFIES: this
    // EFFECTS: scans user input for which of the 4 options (user stories) is chosen
    public void runBuyAndSellApp() {
        inventory = new Inventory();
        cart = new Cart();
        scanOption = new Scanner(System.in);

        while (true) {
            System.out.println("Please select one option: Sell, Buy, Categories, Save, Load, or Checkout.");
            String picked = scanOption.nextLine();

            if (!picked.equals("Checkout")) {
                switchBetweenOptions(picked);
            } else {
                checkout();
                break;
            }
        }
    }

    // EFFECTS: calls method for each input given by user
    public void switchBetweenOptions(String picked) {
        switch (picked) {
            case "Sell":
                scanForInventory();
                break;
            case "Buy":
                scanForCart();
                break;
            case "Categories":
                viewCategories();
                break;
            case "Save":
                saveApplication();
                break;
            case "Load":
                loadApplication();
                break;
        }
    }

    // EFFECTS: scans for item name, price, and quantity of the item user wants and
    //          sets nextItem name and price to given name and price and adds it
    //          to inventory quantity times
    public void scanForInventory() {
        nextItem = new Item();
        Scanner scanItem;
        scanItem = new Scanner(System.in);
        String name;
        double price;

        System.out.println("Please enter a name for your item:");
        name = scanItem.nextLine();
        nextItem.setName(name);

        System.out.println("Please enter a price for your item:");
        price = scanItem.nextDouble();
        nextItem.setPrice(price);

        System.out.println("Please enter a quantity for your item:");
        quantity = scanItem.nextInt();

        scanForCategory();
        inventory.addToInventoryNTimes(nextItem, quantity);
        System.out.println("Your items currently for sale:");
        printInventory();
    }

    // EFFECTS: scans for category to add item to and adds it to that category in inventory
    public void scanForCategory() {
        System.out.println("Please enter a category for your item:");

        printCategories();

        Scanner scanCat = new Scanner(System.in);
        Integer givenCatName = scanCat.nextInt();

        nextItem.setCategory(givenCatName);
    }

    // EFFECTS: prints inventory
    public void printInventory() {
        for (Item item : inventory.getInventory()) {
            System.out.println(item.getName() + " for $" + item.getPrice());
        }
    }

    // EFFECTS: scans for item to remove from inventory and add to cart
    public void scanForCart() {
        nextItem = new Item();
        System.out.println("Pick one of the items in our inventory to buy:");
        printInventory();
        Scanner scanCart = new Scanner(System.in);
        String picked = scanCart.nextLine();

        System.out.println("Choose a quantity:");
        int quantity = scanCart.nextInt();

        nextItem.setName(picked);
        nextItem.setPrice(inventory.removeFromInventoryNTimes(picked, quantity));
        cart.addItemToCartNTimes(nextItem, quantity);
        printCart();
    }

    // EFFECTS: prints cart
    public void printCart() {
        System.out.println("Your cart:");
        for (Item item : cart.getCart()) {
            System.out.println(item.getName() + " for $" + item.getPrice());
        }
    }

    // EFFECTS: prints categories in inventory
    public void printCategories() {
        for (Categories cat : Categories.values()) {
            System.out.println(cat.ordinal() + 1 + "." + cat.toString());
        }
    }

    // EFFECTS: prints out all category options, then all items for category inputted by user
    public void viewCategories() {
        System.out.println("Please choose one category to view:");
        printCategories();

        Scanner scanChosenCat = new Scanner(System.in);
        int chosenCat = scanChosenCat.nextInt();

        for (Item item : inventory.getInventory()) {
            if (chosenCat == item.getCategoryNum()) {
                System.out.println(item.getName() + " for $" + item.getPrice());
            }
        }
    }

    // EFFECTS: saves all items currently in inventory and cart to data files
    private void saveApplication() {
        inventoryWriter = new JsonWriter(inventory.getInventoryDestination());
        cartWriter = new JsonWriter(cart.getDestination());
        try {
            inventoryWriter.openInventory(inventory);
            inventoryWriter.writeInventory(inventory);
            inventoryWriter.close();
            cartWriter.openCart(cart);
            cartWriter.writeCart(cart);
            cartWriter.close();
            System.out.println("Saved.");
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
        }
    }

    // EFFECTS: loads all items from inventory.json and cart.json
    private void loadApplication() {
        inventoryReader = new JsonReader(inventory.getInventoryDestination());
        cartReader = new JsonReader(cart.getDestination());
        try {
            inventory = inventoryReader.readInventory();
            cart = cartReader.readCart();
            System.out.println("Loaded save data.");
        } catch (IOException e) {
            System.out.println("IOException caught.");
        } catch (JSONException j) {
            System.out.println("Object not a string.");
        }
    }

    // REQUIRES: cart is not empty
    // EFFECTS: prints user's cart, total, tax, and total with tax
    //          and prints "Checkout successful" on "Checkout" input, otherwise program is just closed
    public void checkout() {
        printCart();
        System.out.println("Your Total: $" + cart.calculateTotal());
        System.out.println("Tax: $" + cart.calculateTax());
        System.out.println("Grand Total: $" + cart.calculateTotalWithTax());

        System.out.println("Confirm checkout?");

        Scanner scanCheckout = new Scanner(System.in);
        String confirmCheckout = scanCheckout.nextLine();

        if (confirmCheckout.equals("Checkout")) {
            System.out.println("Checkout successful");
        }
        printLog(EventLog.getInstance());
    }

    // EFFECTS: prints event log date + description to console
    public static void printLog(EventLog eventLog) {
        for (Event event : eventLog) {
            System.out.println(event.getDate() + ": " + event.getDescription());
        }
    }
}
