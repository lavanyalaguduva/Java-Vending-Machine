import java.util.ArrayList;
import java.util.List;

public class ItemInventory {
    List<Item> itemList = new ArrayList<>();

    public void addItems() {
        itemList.add(new Item("COKE", 25, 1.25));
        itemList.add(new Item("PEPSI", 35, 1.25));
        itemList.add(new Item("SODA", 45, 0.75));
        itemList.add(new Item("FANTA", 0, 0.75));
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
