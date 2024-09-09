import java.io.File;

public class Client {
    public static void main(String[] args) {
        //Read data from the input file
        File inputFile = new File("stock.txt");
        Stock.readFile(inputFile);

        // create a new cart
        Cart cart = new Cart();

        // print welcome message
        System.out.println("\n\n\nWelcome to Lopez Urban Farm's E-commerce Program!\n");
        ValidateInput.promptEnter();

        boolean exit = false;
        while (!exit) {
            //Menu
            System.out.println("\n\n1. Add item to cart");
            System.out.println("2. Remove item from cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            //Validate input
            int choice = ValidateInput.validInt(1, 5);

            //Actions based on the user choice
            switch (choice) {
                case 1:
                    addItemToCart(cart);
                    break;
                case 2:
                    removeItemFromCart(cart);
                    break;
                case 3:
                    cart.printCart();
                    break;
                case 4:
                    checkoutCart(cart);
                    break;
                case 5:
                    exit = true;
                    break;
            }

            if(choice != 5) ValidateInput.promptEnter();
        }

        //Stock data to output file
        File outputFile = new File("stock.txt");
        Stock.writeFile(outputFile);
    }

    private static void addItemToCart(Cart cart) {
        Stock.printStock();
        
        //Get item details from the user
        // System.out.print("Enter item name: ");
        String itemName = ValidateInput.validItemName();
        if(!itemName.toLowerCase().equals("cancel")){
            System.out.print("Enter item count: ");
            int itemCount = ValidateInput.validInt(1, Integer.MAX_VALUE);
            
            // get price for each
            float itemPrice = Stock.getPriceOfItem(itemName);
            if(itemPrice != -1f){
                //Create item and add to cart
                Item newItem = new Item(itemName, itemCount, itemPrice);

                // create item to check amount in stock
                Item existingItem = cart.getItem(itemName);

                if(existingItem != null) {
                    Item testItem = new Item(itemName, existingItem.getCount() + newItem.getCount(), itemPrice);
                    if(Stock.hasItem(testItem)){
                        cart.addItem(newItem);

                        System.out.println("\nExisting item added to cart.");
                    } else {
                        System.out.println("\nNot enough items in stock.");
                    }
                } else {
                    if(Stock.hasItem(newItem)){
                        cart.addItem(newItem);

                        System.out.println("\nNew item added to cart.");
                    } 
                }
                System.out.println();
            } else {
                System.out.println("\nItem not in stock.\n");
            }
        }
        
        
    }

    private static void removeItemFromCart(Cart cart) {
        // print the content of the cart
        cart.printCart();
        
        //Get item name from user
        // System.out.print("Enter item name to remove: ");
        String itemName = ValidateInput.validItemName();

        if(!itemName.toLowerCase().equals("cancel")){
            System.out.print("Enter item count to remove: ");
            int itemCount = ValidateInput.validInt(1, Integer.MAX_VALUE);

            //Create item and remove from stock
            Item itemToRemove = new Item(itemName, itemCount, 0);
            boolean removed = cart.removeItem(itemToRemove);

            if (removed) {
                System.out.println("Item removed from cart.");
            } else {
                System.out.println("Item not found in cart.");
            }
        }
    }

    private static void checkoutCart(Cart cart) {
        //Checkout cart
        Stock.checkoutCart(cart);

        //Prompts user for payment methods
        System.out.println("\n\n=== Payment Information ===");
        System.out.println("Select a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Paypal");
        System.out.print("Enter your choice: ");
        int paymentChoice = ValidateInput.validInt(1, 3);

        switch (paymentChoice) {
            case 1:
                System.out.print("Enter credit card number: ");
                String creditCardNumber = ValidateInput.validCreditCardNumber();
                //Process credit payment
                break;
            case 2:
                System.out.print("Enter debit card number: ");
                String debitCardNumber = ValidateInput.validCreditCardNumber();
                //Process debit payment
                break;
            case 3:
                System.out.print("Enter PayPal email: ");
                String paypalEmail = ValidateInput.validPayPalEmail();
                //Process PayPal
                break;
        }

        //Final confirmation
        //System.out.print("Confirm purchase? (Yes/No): ");
        String confirmPurchase = ValidateInput.validString("Confirm purchase? (Yes/No): ").toLowerCase();

        if (confirmPurchase.equals("yes")) {
            System.out.println("Payment and Checkout was successful. Thank you for shopping with and enjoy your products!");
        } else {
            System.out.println("Purchase was cancelled.");
        }

    }
}
