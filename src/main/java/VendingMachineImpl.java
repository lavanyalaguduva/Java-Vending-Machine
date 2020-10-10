
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class VendingMachineImpl implements  VendingMachineAPI{
    static Scanner sc = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("#.##");

    String selectedItemName;
    double selectedItemCost;
    double insertedAmount;
    double balanceToBePaid;

    CoinInventory coinInventory = new CoinInventory();
    ItemInventory itemInventory = new ItemInventory();

    public static void main(String[] args) {
        VendingMachineImpl vendingMachine = new VendingMachineImpl();
        vendingMachine.initialize();
        vendingMachine.enterItemToBuy();
        vendingMachine.insertCoins();
        vendingMachine.dispenseItemAndChange();
        vendingMachine.reset();
        sc.close();
    }

    public void initialize() {
        System.out.println("======INITIALIZE THE VENDING MACHINE BALANCE NOW======");
        coinInventory.addCoins();
        itemInventory.addItems();
    }

    public void enterItemToBuy() {
        System.out.print("======ENTER THE ITEM NAME NOW======");
        selectedItemName = sc.next();
        checkItemAvailability();
    }

    public void insertCoins() {
        System.out.println("======INSERT THE COINS NOW=======");
        insertedAmount = coinInventory.addCoins();
        System.out.println("INSERTED AMOUNT: " + df.format(insertedAmount));
        isPaidEnough();
    }

    public void dispenseItemAndChange() {
        System.out.println("======DISPENSING THE ITEM NOW=======");
        itemInventory.dispenseItem(selectedItemName);

        balanceToBePaid = Double.valueOf(df.format(insertedAmount - selectedItemCost));
        coinInventory.dispenseCoins(balanceToBePaid);
    }

    public void reset(){
        System.out.println("======RESETTING THE VENDING MACHINE=====");
        coinInventory.reset();
        itemInventory.reset();
        printInventoryData();
    }
    private void checkItemAvailability() {
        AtomicBoolean itemSelected = new AtomicBoolean(false);
        itemInventory.itemList.forEach(item -> {
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                if (item.count > 1) {
                    System.out.println("ITEM AVAILABLE");
                    System.out.println("ITEM PRICE: " + item.cost);
                    selectedItemCost = item.cost;
                    itemSelected.set(true);
                }
            }
        });
        if (!itemSelected.get()) {
            System.out.println("ITEM NOT AVAILABLE. PLEASE SELECT AN AVAILABLE ITEM");
            enterItemToBuy();
        }
    }

    private void isPaidEnough() {
        if (insertedAmount < selectedItemCost) {
            System.out.println("ITEM PRICE: " + selectedItemCost);
            System.out.println("INSERT THE CORRECT AMOUNT OF MONEY");
            insertCoins();
        }
    }

    private void printInventoryData(){
        System.out.println("INVENTORY BALANCE: "+coinInventory.inventoryBalance);
        System.out.println("REMAINING ITEMS:");
        itemInventory.printItems();
    }



}
