package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Inventory contains all 11 categories (which can not be changed by the user)
// - Contains ArrayList<Item> for items in inventory and ArrayList<Category> for list of 11 categories
public class Inventory implements Writable {
    private static final String inventoryFile = "./data/inventory.json";
    private List<Item> inventory = new ArrayList<>();

    public Inventory() {
    }

    // EFFECTS: returns inventory
    public List<Item> getInventory() {
        return inventory;
    }

    // MODIFIES: this
    // EFFECTS: adds item to inventory n times
    public void addToInventoryNTimes(Item item, int n) {
        for (int i = 0; i < n; i++) {
            inventory.add(item);
            EventLog.getInstance().logEvent(new Event(item.getName() + " for $" + item.getPrice()
                    + " added to inventory"));
        }
    }

    // REQUIRES: inventory has at least n copies of item with name matching given string
    // MODIFIES: this
    // EFFECTS: removes any items in inventory that have names matching given string n times
    // ITERATOR documentation found from w3 schools java tutorial (condition under which item is
    //                                                            removed and int i are original)
    // https://www.w3schools.com/java/java_iterator.asp
    public double removeFromInventoryNTimes(String s, int n) {
        Iterator<Item> it = inventory.iterator();
        int i = 0;
        double boughtItemPrice = 0;
        while (it.hasNext() && i < n) {
            Item item = it.next();
            if (item.getName().equals(s)) {
                it.remove();
                EventLog.getInstance().logEvent(new Event(item.getName() + " for $" + item.getPrice()
                        + " removed from inventory"));
                i++;
                boughtItemPrice = item.getPrice();
            }
        }
        return boughtItemPrice;
    }

    // EFFECTS: saves inventory as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonInventory = new JSONObject();
        jsonInventory.put("name", "Inventory");
        jsonInventory.put("items", saveToJson());
        return jsonInventory;
    }

    // EFFECTS: adds every item in inventory to JSONArray
    public JSONArray saveToJson() {
        JSONArray jsonItemsInInventory = new JSONArray();
        for (Item item : inventory) {
            jsonItemsInInventory.put(item.toJson());
        }
        return jsonItemsInInventory;
    }

    // EFFECTS: returns inventoryFile
    public String getInventoryDestination() {
        return inventoryFile;
    }
}
