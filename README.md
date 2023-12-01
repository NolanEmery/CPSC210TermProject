# Buy and Sell App

## Outline
**What Will the Application Do?**

For my personal project, I will create a 
buy-and-sell app with basic features that allow
the user to list their items for sale. The 
user can then navigate the inventory of the items
they have listed and select items to add to their
cart. This will simulate a real buy-and-sell,
such as ebay, where one user would add an item
and another would buy the item. They should also
be able to sort the store's inventory by 
category. Once the user has a full cart with 
everything they desire to purchase, the user 
should be prompted by the application to check
out. This will lead the user to a checkout page
that displays the total of the items they just
selected as well as their total with tax applied.
From there, they should be presented with a
checkout button to check out. This will end the
application.

**Who Will Use It?**

This application could be of use to people who
are interested in seeing all items they want 
to sell displayed at once. They can also test
that the correct prices are assigned to the 
correct items and that the checkout process 
works properly by the app simulating the buyer's
end of the transactions.

**Why Is This Project of Interest to You?**

This project is of interest to me because 
buy-and-sell applications are common in the 
world, and it seemed like an appropriate way 
to implement this project's requirement of 
user interaction since any buy-and-sell has
to be able to handle specific items the user
is selling.

## User Stories

*Stories*:
- *Story One*: As a user, I want to be able to
  add an item (with a name and a price) to my
  inventory.
- *Story Two*: As a user, I want to be able to
  add items from inventory to my cart.
- *Story Three*: As a user, I want to be able
  to sort by category to see every item in that
  category.
- *Story Four*: As a user, I want to be able to
  check out with all the items in my cart.
- *Story Five*: As a user, I want to be able
  to save my inventory and cart to file.
- *Story Six*: As a user, I want to be able
  to load my inventory and cart from a 
  previous state.

# Instructions For Grader

- You can generate the first required event by
  typing an item name into the text field at 
  the bottom and pressing enter. This brings up
  a new JFrame with a new JTextField. Type a
  price (a double) and press enter. This brings 
  up a third JFrame with a third JTextField. 
  Type a quantity and press enter. This
  brings up a fourth JFrame with all categories 
  printed and a fourth JTextField. Enter a 
  category number of those displayed. Item name
  and price will be printed to JPanel on 
  left quantity times.
- You can generate the second required event by
  typing an item name into the text field at
  the bottom and clicking the "Add To Inventory"
  button. Follow the same steps for price, 
  quantity, and category as in first required 
  event.
- You can locate my visual component by clicking
  the "Add To Cart" button after inputting the 
  name of an item in inventory. In addition to 
  adding it to cart, this will open 
  a new JFrame with a stock image of a cart.
- You can save the state of my application by
  clicking the "Save" button.
- You can reload the state of my application
  by clicking the "Load" button.

# Phase 4: Task 2

- Wed Aug 10 11:03:47 PDT 2022: Book for $10.0 added to inventory
- Wed Aug 10 11:03:47 PDT 2022: Book for $10.0 added to inventory
- Wed Aug 10 11:03:47 PDT 2022: Book for $10.0 added to inventory
- Wed Aug 10 11:03:47 PDT 2022: Book for $10.0 added to inventory
- Wed Aug 10 11:03:47 PDT 2022: Book for $10.0 added to inventory
- Wed Aug 10 11:03:54 PDT 2022: Book for $10.0 removed from inventory
- Wed Aug 10 11:04:16 PDT 2022: Item1 for $75.0 added to inventory
- Wed Aug 10 11:04:20 PDT 2022: Item1 for $75.0 removed from inventory

# Phase 4: Task 3

- If I had more to time refactor my code, I 
  would likely want to write an abstract class
  that inventory and cart would extend. This 
  class would be called something like ItemCollection
  and would have a field for a List of Item and
  addNTimes and removeNTimes methods and a getter
  for List of Item. This would reduce duplication
  in the code since Inventory and Cart both 
  currently have most of this functionality.
