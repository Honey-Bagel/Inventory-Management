import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Stock {
    private static LList<Item> items;
    private static File ioFile;
    private static Scanner scanner;
    private static PrintWriter writer;
    private static boolean initialized = false;

    public static void readFile(File inputFile){
        ioFile = inputFile;
        checkInitialize();
        try {
            scanner = new Scanner(ioFile);
            String line;
            int badLineCount = 0;

            while(scanner.hasNext()){
                line = scanner.nextLine();
                
                if(checkCommaCount(line)){
                    String[] splitLine = line.split(",");

                    try{
                        String itemName = splitLine[0];
                        int itemCount = Integer.parseInt(splitLine[1]);
                        float itemPrice = Float.parseFloat(splitLine[2]);

                        if(!addItem(new Item(itemName, itemCount, itemPrice))){
                            System.out.println("addItem failed.");
                            continue;
                        }
                    } catch (NumberFormatException e){
                        badLineCount++;
                        continue;
                    }
                } else {
                    badLineCount++;
                    continue;
                }
            }

            scanner.close();
            System.out.println("Finished reading " + ioFile.getName() + " with " + badLineCount + " bad line(s).");
        } catch (FileNotFoundException e){
            System.out.println("File not found for readFile.");
        }
    }

    public static void writeFile(File outputFile){
        ioFile = outputFile;
        checkInitialize();
        try {
            writer = new PrintWriter(ioFile);
            Item temp;
            for(int i = 0; i < items.getLength(); i++){
                temp = items.getEntry(i);
                writer.printf("%s,%d,%.2f\n", temp.getName(), temp.getCount(), temp.getPriceForEach());
            }
            
            writer.close();
            System.out.println("Finished writing " + ioFile.getName());
        } catch (FileNotFoundException e){
            System.out.println("File not found for writeFile");
        }
    }

    private static boolean addItem(Item newItem){
        checkInitialize();

        if(newItem == null) return false;

        if(hasItem(newItem)){
            Item temp = getReference(newItem.getName());
            temp.add(newItem.getCount());
        } else {
            items.add(newItem);
        }

        return true;
    }

    private static boolean removeItem(Item targetItem){
        checkInitialize();

        if(hasItem(targetItem)){
            Item temp = getReference(targetItem.getName());

            temp.remove(targetItem.getCount());

            if(temp.getCount() == 0) items.remove(temp);
            return true;
        }

        return false;
    }

    public static boolean hasItem(Item targetItem){
        checkInitialize();

        if(targetItem == null) return false;

        Item temp = getReference(targetItem.getName());

        // compare target amount to amount in stock
        return temp != null && temp.getCount() >= targetItem.getCount();
    }

    public static void checkoutCart(Cart cart){
        checkInitialize();

        LList<Item> cartItems = cart.getItems();
        
        for(int i = 0; i < cartItems.getLength(); i++){
            removeItem(cartItems.getEntry(i));
        }

        System.out.printf("Thank you for your purchase! Your total was: $%.2f\s", cart.getTotalPrice());
        cart.clear();
    }

    public static void printStock(){
        System.out.println("\n\n------------------------- S T O C K -------------------------");
        printStockLine("Item", "Amount", "Price");
        for(int i = 0; i < items.getLength(); i++){
            Item temp = items.getEntry(i);
            printStockLine(temp.getName(), ""+temp.getCount(), ""+temp.getPriceForEach());
        }
    }

    private static void printStockLine(String s1, String s2, String s3){
        System.out.printf("%-15s| %-7s| %-7s\n", s1, s2, s3);
    }

    public static float getPriceOfItem(String itemName){
        Item targetItem = getReference(itemName);
        if(targetItem != null) return targetItem.getPriceForEach();

        return -1f;
    }

    public static void checkInitialize(){
        if(!initialized){
            initialize();
        }
    }

    private static void initialize(){
        items = new LList<Item>();
        initialized = true;
    }

    private static boolean checkCommaCount(String line){
        int count = 0;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == ',') count++;
            if(count > 2) return false;
        }

        return count == 2;
    }

    private static Item getReference(String targetName){
        // find item with the same name
        Item temp = null;
        boolean found = false;
        for(int i = 0; i < items.getLength(); i++){
            temp = items.getEntry(i);

            if(temp.getName().toLowerCase().equals(targetName.toLowerCase())){
                found = true;
                break;
            }
        }

        if(found) return temp;
        else return null;
    }
}
