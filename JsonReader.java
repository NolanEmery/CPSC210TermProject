package persistence;

import model.Cart;
import model.Inventory;
import model.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Persistence package modelled off of JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JsonReader to read from the given source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads inventory
    public Inventory readInventory() throws IOException {
        String inventoryInJson = readFile(source);
        JSONObject jsonInventory = new JSONObject(inventoryInJson);
        return parseInventory(jsonInventory);
    }

    // EFFECTS: reads cart
    public Cart readCart() throws IOException {
        String cartInJson = readFile(source);
        JSONObject jsonCart = new JSONObject(cartInJson);
        return parseCart(jsonCart);
    }

    // EFFECTS: returns source file as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: reconstructs a new inventory from items in json file
    private Inventory parseInventory(JSONObject jsonObject) {
        jsonObject.getString("name");
        Inventory inventory = new Inventory();
        addItemsToInventory(inventory, jsonObject);
        return inventory;
    }

    // EFFECTS: reconstructs a new cart from items in json file
    private Cart parseCart(JSONObject jsonObject) {
        jsonObject.getString("name");
        Cart cart = new Cart();
        addItemsToCart(cart, jsonObject);
        return cart;
    }

    // MODIFIES: inventory
    // EFFECTS: adds all items from json file to inventory
    private void addItemsToInventory(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItemToInventory(inventory, nextItem);
        }
    }

    // MODIFIES: inventory
    // EFFECTS: adds one item from json file to inventory
    public void addItemToInventory(Inventory inventory, JSONObject jsonObject) {
        Item nextItem = new Item();
        String name = jsonObject.getString("name");
        nextItem.setName(name);
        nextItem.setPrice(jsonObject.getDouble("price"));
        int categoryNum = jsonObject.getInt("categoryNum");
        nextItem.setCategory(categoryNum);
        inventory.addToInventoryNTimes(nextItem, 1);
    }

    // MODIFIES: cart
    // EFFECTS: adds all items from json file to cart
    private void addItemsToCart(Cart cart, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItemToCart(cart, nextItem);
        }
    }

    // MODIFIES: cart
    // EFFECTS: adds one item from json file to cart
    public void addItemToCart(Cart cart, JSONObject jsonObject) {
        Item nextItem = new Item();
        String name = jsonObject.getString("name");
        nextItem.setName(name);
        nextItem.setPrice(jsonObject.getDouble("price"));
        cart.addItemToCartNTimes(nextItem, 1);
    }
}