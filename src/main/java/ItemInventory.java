import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemInventory {
    List<Item> itemList = new ArrayList<>();

    public void initialize() {
        itemList.add(new Item("COKE", 25, 1.25));
        itemList.add(new Item("PEPSI", 35, 1.25));
        itemList.add(new Item("SODA", 45, 0.75));
        itemList.add(new Item("FANTA", 1, 0.75));
        printItems();
    }

    public void printItems(){
        System.out.println("ITEM_NAME" + "\t|\t" + "ITEM_COST" + "\t|\t" + "ITEM_COUNT");
        for (Item item : itemList) {
            System.out.println(item.name + "\t\t|\t" + item.cost + "\t\t|\t" + item.count);
        }
    }
    public void dispenseItem(String selectedItemName) {
        this.itemList.forEach(item -> {
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                item.count--;
                System.out.println("COLLECT YOUR ITEM NOW");
                return;
            }
        });
    }
    public void addBackItem(String selectedItemName){
        for(Item item: itemList){
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                item.count++;
                System.out.println("REQUEST CANCELLED. ITEM CANNOT BE COLLECTED NOW");
                return;
            }
        }
    }

    public void reset() {
        itemList.clear();
    }


    public class Item {
        String name;
        int count;
        double cost;

        public Item(String name, int count, double cost) {
            this.name = name;
            this.count = count;
            this.cost = cost;
        }
    }
}
