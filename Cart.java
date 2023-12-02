package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Cart has List<Item> to store all items in user's cart
public class Cart implements Writable {
    private static final String cartFile = "./data/cart.json";
    private List<Item> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    // EFFECTS: returns cart
    public List<Item> getCart() {
        return cart;
    }

    // MODIFIES: this
    // EFFECTS: adds item to cart n times
    public void addItemToCartNTimes(Item item, int n) {
        for (int i = 0; i < n; i++) {
            cart.add(item);
        }
    }

    // EFFECTS: adds up prices of all items in cart
    public double calculateTotal() {
        double total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        return total;
    }

    // EFFECTS: returns 12% tax on total
    public double calculateTax() {
        return calculateTotal() * 0.12;
    }

    // EFFECTS: returns total with 12% tax applied
    public double calculateTotalWithTax() {
        return calculateTotal() + calculateTax();
    }

    // EFFECTS: saves cart as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonCart = new JSONObject();
        jsonCart.put("name", "Cart");
        jsonCart.put("items", saveToJson());
        return jsonCart;
    }

    // EFFECTS: adds every item in cart to JSONArray
    public JSONArray saveToJson() {
        JSONArray jsonItemsInCart = new JSONArray();
        for (Item item : cart) {
            jsonItemsInCart.put(item.toJson());
        }
        return jsonItemsInCart;
    }

    // EFFECTS: returns cartFile
    public String getDestination() {
        return cartFile;
    }

//    ** Maybe implement later (not needed for user stories) **
//    // REQUIRES: cart has at least n copies of the item with name matching given name
//    public void removeItemFromCartNTimes(String s, int n) {
//
//    }
}
