import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class VendingMachineImpl implements VendingMachineAPI {
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
        try {
            vendingMachine.printOperationsInConsole();
            int operation = sc.nextInt();
            boolean exit = false;
            while (operation != 5 && !exit) {
                switch (operation) {
                    case 1:
                        vendingMachine.buyAnItem();
                        break;
                    case 2:
                        vendingMachine.cancelRequestAndRefund();
                        break;
                    case 3:
                        vendingMachine.getInventoryData();
                        break;
                    case 4:
                        vendingMachine.reset();
                        break;
                    case 5:
                        exit = true;
                        sc.close();
                    default:
                        System.out.println("INCORRECT OPERATION SELECTED. SELECT AN APPROPRIATE ACTION");
                }
                vendingMachine.printOperationsInConsole();
                operation = sc.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input " + e);
        }

    }

    public void initialize() {
        System.out.println("======INITIALIZE THE VENDING MACHINE BALANCE NOW======");
        coinInventory.initialize();
        itemInventory.initialize();
    }

    public void buyAnItem() {
        enterItemToBuy();
        insertCoins();
        dispenseItemAndChange();
        getInventoryData();
    }

    public void cancelRequestAndRefund() {
        itemInventory.addBackItem(selectedItemName);

        balanceToBePaid = Double.valueOf(df.format(selectedItemCost));
        coinInventory.dispenseCoins(balanceToBePaid);
        getInventoryData();
    }

    public void getInventoryData() {
        System.out.println("INVENTORY BALANCE: " + coinInventory.inventoryBalance);
        System.out.println("REMAINING ITEMS:");
        itemInventory.printItems();
    }

    public void reset() {
        System.out.println("======RESETTING THE VENDING MACHINE=====");
        coinInventory.reset();
        itemInventory.reset();
        getInventoryData();
    }

    private void enterItemToBuy() {
        System.out.print("======ENTER THE ITEM NAME NOW======");
        selectedItemName = sc.next();
        checkItemAvailability();
    }

    private void insertCoins() {
        System.out.println("======INSERT THE COINS NOW=======");
        insertedAmount = coinInventory.addCoins();
        System.out.println("INSERTED AMOUNT: " + df.format(insertedAmount));
        isPaidEnough();
    }

    private void dispenseItemAndChange() {
        System.out.println("======DISPENSING THE ITEM NOW=======");
        itemInventory.dispenseItem(selectedItemName);

        balanceToBePaid = Double.valueOf(df.format(insertedAmount - selectedItemCost));
        coinInventory.dispenseCoins(balanceToBePaid);
    }

    private void printOperationsInConsole() {
        System.out.println("=====SELECT AN OPERATION TO PERFORM=====");
        System.out.println("ENTER 1 TO BUY AN ITEM");
        System.out.println("ENTER 2 TO CANCEL THE REQUEST");
        System.out.println("ENTER 3 TO GET INVENTORY DATA");
        System.out.println("ENTER 4 TO RESET THE INVENTORY");
        System.out.println("ENTER 5 TO EXIT");
    }

    private void checkItemAvailability() {
        AtomicBoolean itemSelected = new AtomicBoolean(false);
        itemInventory.itemList.forEach(item -> {
            if (item.name.equalsIgnoreCase(selectedItemName)) {
                if (item.count >= 1) {
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
}
