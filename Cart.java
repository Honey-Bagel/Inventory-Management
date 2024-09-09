public class Cart {
    private LList<Item> items;

    public Cart() {
        items = new LList<>();
    } // end default construtor

    public float getTotalPrice(){
        float total = 0f;
        for (int i = 0; i < items.getLength(); i++) {
            total += (items.getEntry(i)).getTotalPrice();
        } // end for loop

        return total;
    }

    public LList<Item> getItems(){
        return items;
    }

    public boolean addItem(Item newItem){
        Item existingItem = getItem(newItem.getName());
        if(existingItem != null){
            return existingItem.add(newItem.getCount());
        } else {
            items.add(newItem);
            return true;
        }
    }

    public Item getItem(String name){
        for(int i = 0; i < items.getLength(); i++){
            if(name.equals(items.getEntry(i).getName())){
                return items.getEntry(i);
            }
        }
        return null;
    }

    public boolean removeItem(Item targetItem){
        Item existingItem = getItem(targetItem.getName());
        boolean result = false;
        if(existingItem != null){
            result = existingItem.remove(targetItem.getCount());
            if(targetItem.getCount() == 0) items.remove(targetItem);
        }

        return result;
    }

    public void clear(){
        items.clear();
    }

    public void printCart(){
        System.out.println("\n\n------------------------- C A R T -------------------------");
        printCartLine("Item", "Amount", "Price", "Total Price");
        for(int i = 0; i < items.getLength(); i++){
            Item temp = items.getEntry(i);
            printCartLine(temp.getName(), temp.getCount(), temp.getPriceForEach(), temp.getTotalPrice());
        }

        System.out.printf("\nCart Total Price: $%.2f\n\n", getTotalPrice());
    }

    private void printCartLine(String s1, String s2, String s3, String s4){
        System.out.printf("%-15s| %-7s| %-7s| %-7s\n", s1, s2, s3, s4);
    }

    private void printCartLine(String name, int count, float price, float totalPrice){
        System.out.printf("%-15s| %-7d| %-7.2f| %-7.2f\n", name, count, price, totalPrice);
    }
}
