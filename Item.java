package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;

// Item contains name and price
public class Item implements Writable {
    private String name;
    private double price;
    private Map<Integer, Categories> category = new HashMap<>();
    private int categoryNum;

    public Item() {
    }

    // MODIFIES: this
    // EFFECTS: sets name to the given name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: sets price to the given price
    public void setPrice(double price) {
        this.price = price;
    }

    // EFFECTS: returns price
    public double getPrice() {
        return price;
    }

    // REQUIRES: i > 0
    // MODIFIES: this
    // EFFECTS: sets category to integer converted to index in categories
    public void setCategory(Integer i) {
        if (i > 0) {
            category.put(i, Categories.values()[i - 1]);
            categoryNum = i;
        } else {
            category.put(Categories.values().length, Categories.values()[Categories.values().length - 1]);
            categoryNum = Categories.values().length;
        }
    }

    // EFFECTS: returns category
    public Categories getCategory() {
        return category.get(categoryNum);
    }

    // EFFECTS: returns categoryNum
    public int getCategoryNum() {
        return categoryNum;
    }

    // EFFECTS: saves item as JSONObject with name, price, category, and categoryNum
    @Override
    public JSONObject toJson() {
        JSONObject itemToJson = new JSONObject();
        itemToJson.put("name", name);
        itemToJson.put("price", price);
        itemToJson.put("category", category);
        itemToJson.put("categoryNum", categoryNum);
        return itemToJson;
    }
}
