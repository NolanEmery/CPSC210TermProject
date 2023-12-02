package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

// Graphical User Interface for user input
// FIVE CASES (user stories):
// 1. Inventory
// 2. Cart
// 3. Categories
// 4. Save
// 5. Load
public class BuyAndSellAppGUI extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final Font FONT24 = new Font("Arial", Font.PLAIN, 24);
    private static final Font FONT18 = new Font("Arial", Font.PLAIN, 18);
    private static final String imageFile = "./data/pexels-emirkhan-bal-953864.jpg";

    //private JFrame setCategoryFrame;

    private String nameToAdd;
    private int quantity;

    private DefaultListModel inventoryInGUI; // declare GUI lists and Inventory and Cart up here
    private DefaultListModel cartInGUI; // so ActionListeners have access to them
    private DefaultListModel categoriesInGUI;

    private Inventory inventoryInModel;
    private Cart cartInModel;
    private Item nextItem;

    private JsonWriter inventoryWriter;
    private JsonWriter cartWriter;
    private JsonReader inventoryReader;
    private JsonReader cartReader;

    private Button addToInventoryButton;

    private JTextField mainJTextField; // declare JTextFields up here so their text
    private JTextField getPriceJTextField; // can be accessed from their ActionListeners
    private JTextField getQuantityJTextField;
    private JTextField getCategoryJTextField;
    private JTextField viewCategoriesJTextField;

    // Constructor sets up JScrollPane for inventory and cart contents display and JPanel for buttons
    // and JTextField to input names of items
    public BuyAndSellAppGUI() {
        super("BuyAndSellApp");
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inventoryInModel = new Inventory();
        cartInModel = new Cart();

        JScrollPane myInventoryAndCartView = new JScrollPane();
        add(myInventoryAndCartView);
        makeInventoryAndCartView(myInventoryAndCartView);

        JPanel myUserInputPanel = new JPanel();
        add(myUserInputPanel, BorderLayout.SOUTH);
        makeUserInputPanel(myUserInputPanel);

        DisplayEventLog displayEventLog = new DisplayEventLog();
        addWindowListener(displayEventLog);

        this.setVisible(true);
    }

    // WindowListener to call printLog in BuyAndSellApp when window is closed
    private class DisplayEventLog implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            BuyAndSellApp.printLog(EventLog.getInstance());
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

    // MODIFIES: this, jpanel
    // EFFECTS: takes a newly constructed JPanel and adds buttons and JTextField and JLabel to it
    public JPanel makeUserInputPanel(JPanel jpanel) {
        jpanel.setLayout(new BorderLayout(BuyAndSellAppGUI.WIDTH / 100, BuyAndSellAppGUI.HEIGHT / 100));
        jpanel.setBackground(Color.GRAY);
        JLabel labelForPanel = new JLabel("Options");
        labelForPanel.setFont(FONT24);
        jpanel.add(labelForPanel, BorderLayout.PAGE_START);

        JPanel myButtons = new JPanel();
        jpanel.add(myButtons);
        makeButtons(myButtons);

        mainJTextField = new JTextField();
        mainJTextField.setFont(FONT24);
        mainJTextField.setToolTipText("Enter a name for your item");

        SetNameEnterKeyPressedEvent setNameEnterKeyPressedEvent = new SetNameEnterKeyPressedEvent();
        mainJTextField.addActionListener(setNameEnterKeyPressedEvent);

//        SetNameEvent setNameEvent = new SetNameEvent();
//        mainJTextField.addActionListener(setNameEvent);

        jpanel.add(mainJTextField, BorderLayout.PAGE_END);
        jpanel.setVisible(true);
        return jpanel;
    }

    // Second required event for user story of "Add item to inventory"
    // Event type is user presses enter key
    // JTextField.getText() is sent to inventoryInGUI and inventoryInModel just like in
    // AddToInventoryEvent; however event type is different (enter key vs button pressed)
    private class SetNameEnterKeyPressedEvent implements ActionListener {
        private SetNameEnterKeyPressedEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nextItem = new Item();
            nameToAdd = mainJTextField.getText();

            getPriceJTextField = new JTextField();
            JFrame jframe = new JFrame("Please enter a price for your item");
            jframe.setVisible(true);
            jframe.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 10);
            jframe.add(getPriceJTextField);

            nextItem.setName(nameToAdd);

            SetPriceEvent setPriceEvent = new SetPriceEvent();
            getPriceJTextField.addActionListener(setPriceEvent);
        }
    }

    // MODIFIES: this, jpanel
    // EFFECTS: adds buttons with functionality linked to model to given JPanel
    public void makeButtons(JPanel jpanel) {
        jpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jpanel.setBackground(Color.gray);

        addToInventoryButton = new Button("Add To Inventory");
        SetNameEvent setNameEvent = new SetNameEvent();
        addToInventoryButton.addActionListener(setNameEvent);

        Button addToCartButton = new Button("Add To Cart");
        AddToCartEvent addToCartEvent = new AddToCartEvent();
        addToCartButton.addActionListener(addToCartEvent);

        Button viewCategoriesButton = new Button("View Categories");
        ViewCategoriesEvent viewCategoriesEvent = new ViewCategoriesEvent();
        viewCategoriesButton.addActionListener(viewCategoriesEvent);

        Button saveButton = new Button("Save");
        SaveEvent saveEvent = new SaveEvent();
        saveButton.addActionListener(saveEvent);

        Button loadButton = new Button("Load");
        LoadEvent loadEvent = new LoadEvent();
        loadButton.addActionListener(loadEvent);

        jpanel.add(addToInventoryButton);
        jpanel.add(addToCartButton);
        jpanel.add(viewCategoriesButton);
        jpanel.add(saveButton);
        jpanel.add(loadButton);
    }

    // sets name of item to string typed into JTextField
    private class SetNameEvent implements ActionListener {
        private SetNameEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nextItem = new Item();
            nameToAdd = mainJTextField.getText();

            getPriceJTextField = new JTextField();
            JFrame jframe = new JFrame("Please enter a price for your item");
            jframe.setVisible(true);
            jframe.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 10);
            jframe.add(getPriceJTextField);

            nextItem.setName(nameToAdd);

            SetPriceEvent setPriceEvent = new SetPriceEvent();
            getPriceJTextField.addActionListener(setPriceEvent);
        }
    }

    // Sets price of item to double entered into JTextField
    private class SetPriceEvent implements ActionListener {
        private SetPriceEvent() {
            super();
        }

        // REQUIRES: text entered into the JTextField is a double
        @Override
        public void actionPerformed(ActionEvent e) {
            String stringToParse = getPriceJTextField.getText();
            double priceToAdd = Double.parseDouble(stringToParse);
            nextItem.setPrice(priceToAdd);

            getQuantityJTextField = new JTextField();
            JFrame jframe = new JFrame("Please enter a quantity for your item");
            jframe.setVisible(true);
            jframe.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 10);
            jframe.add(getQuantityJTextField);

            SetQuantityEvent setQuantityEvent = new SetQuantityEvent();
            getQuantityJTextField.addActionListener(setQuantityEvent);
        }
    }

    // Sets quantity of item to int typed into JTextField
    private class SetQuantityEvent implements ActionListener {
        private SetQuantityEvent() {
            super();
        }

        // REQUIRES: text entered into the JTextField is an integer
        @Override
        public void actionPerformed(ActionEvent e) {
            String stringToParse = getQuantityJTextField.getText();
            quantity = Integer.parseInt(stringToParse);

            getCategoryJTextField = new JTextField();
            JFrame setCategoryFrame = new JFrame("Please enter a category for your item");
            setCategoryFrame.setLayout(new BorderLayout());
            setCategoryFrame.setVisible(true);
            setCategoryFrame.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            setCategoryFrame.add(getCategoryJTextField, BorderLayout.PAGE_END);
            PrintCategories printCategories = new PrintCategories();
            setCategoryFrame.add(printCategories, BorderLayout.PAGE_START);

            SetCategoryEvent setCategoryEvent = new SetCategoryEvent();
            getCategoryJTextField.addActionListener(setCategoryEvent);
        }
    }

    // Sets category of item to int typed into JTextField
    private class SetCategoryEvent implements ActionListener {
        private SetCategoryEvent() {
            super();
        }

        // REQUIRES: text entered into the JTextField is an integer
        @Override
        public void actionPerformed(ActionEvent e) {
            String stringToParse = getCategoryJTextField.getText();
            int givenCatName = Integer.parseInt(stringToParse);

            nextItem.setCategory(givenCatName);

            for (int i = 0; i < quantity; i++) {
                inventoryInGUI.addElement(printAllInfoOnItem(nextItem));
            }
            inventoryInModel.addToInventoryNTimes(nextItem, quantity);
            //setCategoryFrame.dispose();
        }
    }

    // JPanel with a JList of all categories
    public class PrintCategories extends JPanel {
        public PrintCategories() {
            super();
            BuyAndSellAppGUI.this.categoriesInGUI = new DefaultListModel();
            JList jcategories = new JList(BuyAndSellAppGUI.this.categoriesInGUI);
            for (Categories cat : Categories.values()) {
                categoriesInGUI.addElement(cat.ordinal() + 1 + "." + cat);
            }
            jcategories.setFont(FONT18);
            add(jcategories);

            setVisible(true);
        }
    }

    // EFFECTS: prints name and price of item
    public String printAllInfoOnItem(Item item) {
        return item.getName() + " for $" + item.getPrice();
    }

    // REQUIRES: string inputted by user before pressing button is an item in inventory
    private class AddToCartEvent implements ActionListener {
        private AddToCartEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            nextItem = new Item();
            nameToAdd = mainJTextField.getText();

            nextItem.setName(nameToAdd);
            nextItem.setPrice(inventoryInModel.removeFromInventoryNTimes(nameToAdd, 1));
            inventoryInGUI.removeElement(printAllInfoOnItem(nextItem));

            cartInGUI.addElement(printAllInfoOnItem(nextItem));
            cartInModel.addItemToCartNTimes(nextItem, 1);
            mainJTextField.setText("");
            displayGraphic();
        }
    }

    // Creates a JFrame with a JPanel in it and a JLabel containing an ImageIcon containing a
    // BufferedImage
    // Stock Image found from user emirkhan bal on Pexels.com:
    // https://www.pexels.com/photo/gray-steel-shopping-cart-953864/
    public void displayGraphic() {
        JFrame graphicFrame = new JFrame("Graphic");
        graphicFrame.setVisible(true);
        graphicFrame.setBounds(0, 0, WIDTH, HEIGHT);

        JPanel graphicPanel = new JPanel();
        graphicPanel.setVisible(true);
        graphicPanel.setBounds(0, 0, WIDTH, HEIGHT);
        graphicFrame.add(graphicPanel);

        Image img;
        try {
            img = ImageIO.read(new File(imageFile));
            Image imgScaled = img.getScaledInstance(WIDTH, HEIGHT, 1);
            ImageIcon imgIcon = new ImageIcon(imgScaled);
            JLabel imgLabel = new JLabel(imgIcon);
            graphicPanel.add(imgLabel);
            imgLabel.setBounds(0, 0, WIDTH, HEIGHT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Makes new JFrame with all categories printed out and a JTextField under with an
    // ActionListener for when user inputs int matching a category number
    private class ViewCategoriesEvent implements ActionListener {
        private ViewCategoriesEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame viewCategoriesFrame = new JFrame("Please choose one category to view");
            viewCategoriesFrame.setLayout(new BorderLayout());
            viewCategoriesFrame.setVisible(true);
            viewCategoriesFrame.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

            PrintCategories printCategories = new PrintCategories();
            viewCategoriesFrame.add(printCategories, BorderLayout.PAGE_START);

            viewCategoriesJTextField = new JTextField();
            viewCategoriesFrame.add(viewCategoriesJTextField, BorderLayout.PAGE_END);

            PrintItemsInChosenCategory printItemsInChosenCategory = new PrintItemsInChosenCategory();
            viewCategoriesJTextField.addActionListener(printItemsInChosenCategory);
        }
    }

    // Prints all items in chosen category in a new JFrame
    private class PrintItemsInChosenCategory implements ActionListener {
        public PrintItemsInChosenCategory() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String stringToParse = viewCategoriesJTextField.getText();
            int chosenCat = Integer.parseInt(stringToParse);

            JFrame viewItemsInCategoryJFrame = new JFrame("Items in " + Categories.values()[chosenCat - 1]);
            viewItemsInCategoryJFrame.setBounds(WIDTH / 4, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            viewItemsInCategoryJFrame.setVisible(true);

            DefaultListModel itemsInCategory = new DefaultListModel();
            JList itemsInCategoryJList = new JList(itemsInCategory);
            itemsInCategoryJList.setFont(FONT24);
            viewItemsInCategoryJFrame.add(itemsInCategoryJList);

            for (Item item : inventoryInModel.getInventory()) {
                if (chosenCat == item.getCategoryNum()) {
                    itemsInCategory.addElement(printAllInfoOnItem(item));
                }
            }
        }
    }

    // Saves all items in inventory and cart
    private class SaveEvent implements ActionListener {
        private SaveEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inventoryWriter = new JsonWriter(inventoryInModel.getInventoryDestination());
            cartWriter = new JsonWriter(cartInModel.getDestination());
            try {
                inventoryWriter.openInventory(inventoryInModel);
                inventoryWriter.writeInventory(inventoryInModel);
                inventoryWriter.close();
                cartWriter.openCart(cartInModel);
                cartWriter.writeCart(cartInModel);
                cartWriter.close();
                mainJTextField.setText("Saved.");
            } catch (FileNotFoundException f) {
                mainJTextField.setText("File doesn't exist");
            }
        }
    }

    // Loads all items to inventory and cart
    private class LoadEvent implements ActionListener {
        private LoadEvent() {
            super();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inventoryReader = new JsonReader(inventoryInModel.getInventoryDestination());
            cartReader = new JsonReader(cartInModel.getDestination());
            try {
                inventoryInModel = inventoryReader.readInventory();
                cartInModel = cartReader.readCart();
                for (Item item : inventoryInModel.getInventory()) {
                    inventoryInGUI.addElement(printAllInfoOnItem(item));
                }
                for (Item item : cartInModel.getCart()) {
                    cartInGUI.addElement(printAllInfoOnItem(item));
                }
                mainJTextField.setText("Loaded save data.");
            } catch (IOException i) {
                mainJTextField.setText("IOException caught.");
            } catch (JSONException j) {
                mainJTextField.setText("Object not a string");
            }
        }
    }

    // MODIFIES: this, jscrollpane
    // EFFECTS: adds JScrollBar and JViewPort with JPanel with 2 side-by-side JPanels containing
    // items in inventory and cart to given JScrollPane
    public void makeInventoryAndCartView(JScrollPane jscrollPane) {
        jscrollPane.setBounds(0, 0, BuyAndSellAppGUI.WIDTH, BuyAndSellAppGUI.HEIGHT);

        JViewport inventoryAndCartViewPort = new JViewport();
        jscrollPane.setViewport(inventoryAndCartViewPort);
        makeInventoryAndCartViewPort(inventoryAndCartViewPort);

        jscrollPane.setVerticalScrollBar(new JScrollBar());
        jscrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        setVisible(true);
    }

    // MODIFIES: this, jviewport
    // EFFECTS: makes a JViewPort to contain one JPanel with a FlowLayout to manage one JPanel
    //          for each of Inventory and Cart lists
    public void makeInventoryAndCartViewPort(JViewport jviewport) {
        jviewport.setBounds(0, 0, BuyAndSellAppGUI.WIDTH, BuyAndSellAppGUI.HEIGHT);

        JPanel inventoryAndCartPanels = new JPanel();
        jviewport.add(inventoryAndCartPanels);
        inventoryAndCartPanels.setLayout(new FlowLayout());

        InventoryPanel inventoryPanel = new InventoryPanel();
        inventoryAndCartPanels.add(inventoryPanel);

        CartPanel cartPanel = new CartPanel();
        inventoryAndCartPanels.add(cartPanel);

        inventoryAndCartPanels.setVisible(true);
        jviewport.setVisible(true);
    }

    // JPanel for inventory
    private class InventoryPanel extends JPanel {
        private InventoryPanel() {
            super();
            BuyAndSellAppGUI.this.inventoryInGUI = new DefaultListModel();
            inventoryInGUI.addElement("Inventory");

            JList jinventory = new JList(BuyAndSellAppGUI.this.inventoryInGUI);
            jinventory.setFont(FONT18);
            //jinventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //jinventory.setSelectedIndex(0);
            add(jinventory);

            setVisible(true);
        }
    }

    // JPanel for cart
    private class CartPanel extends JPanel {
        private CartPanel() {
            super();
            BuyAndSellAppGUI.this.cartInGUI = new DefaultListModel();
            cartInGUI.addElement("Cart");

            JList jcart = new JList(BuyAndSellAppGUI.this.cartInGUI);
            jcart.setFont(FONT18);
            //jcart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //jcart.setSelectedIndex(0);
            add(jcart);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BuyAndSellAppGUI::new);
    }
}