package persistence;

import model.Cart;
import model.Inventory;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Persistence package modelled off of JsonSerializationDemo:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a JsonWriter to write to the given destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // EFFECTS: opens inventory.json file to save inventory to
    public void openInventory(Inventory inventory) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // EFFECTS: writes inventory to JSONObject with name "Inventory" and contents JSONArray containing
    //          names + prices of all items in inventory
    public void writeInventory(Inventory inventory) {
        JSONObject json = inventory.toJson();
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: opens Cart.json file to save cart to
    public void openCart(Cart cart) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // EFFECTS: writes cart to JSONObject with name "Cart" and contents JSONArray containing
    //          names + prices of all items in cart
    public void writeCart(Cart cart) {
        JSONObject json = cart.toJson();
        saveToFile(json.toString(TAB));
    }

    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // EFFECTS: saves to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
