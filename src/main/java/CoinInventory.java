import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoinInventory {
    private final Map<Coin, Integer> inventory = new HashMap<>();
    double inventoryBalance;
    DecimalFormat df = new DecimalFormat("#.##");

    public double addCoins() {
        double insertedAmount = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of 1 penny coins: ");
        insertedAmount += add(Coin.PENNY, sc.nextInt());

        System.out.print("Enter the number of 5 pence coins: ");
        insertedAmount += add(Coin.NICKLE, sc.nextInt());

        System.out.print("Enter the number of 10 pence coins: ");
        insertedAmount += add(Coin.DIME, sc.nextInt());

        System.out.print("Enter the number of 25 pence coins: ");
        insertedAmount += add(Coin.QUARTER, sc.nextInt());

        inventoryBalance = Double.valueOf(df.format(inventoryBalance));
        System.out.println("INVENTORY BALANCE: " + inventoryBalance);

        return insertedAmount;

    }

    public double add(Coin item, int noOfCoins) {
        if (inventory.containsKey(item)) {
            int count = inventory.get(item);
            inventory.put(item, count + noOfCoins);
        } else {
            inventory.put(item, noOfCoins);
        }

        double insertedAmount = (item.getDenomination() * noOfCoins) / 100.0;
        inventoryBalance += insertedAmount;

        return insertedAmount;
    }

    public double dispenseCoins(double balanceToBePaid) {
        System.out.println("TOTAL CHANGE TO DISPENSE: " + Double.valueOf(df.format(balanceToBePaid)));

        if (balanceToBePaid > 0) {
            while (balanceToBePaid > 0) {
                if (balanceToBePaid >= (Double.valueOf(Coin.QUARTER.getDenomination()) / 100.0) && inventory.get(Coin.QUARTER) >= 1) {
                    balanceToBePaid = deduct(balanceToBePaid, Coin.QUARTER);
                } else if (balanceToBePaid >= (Double.valueOf(Coin.DIME.getDenomination()) / 100.0) && inventory.get(Coin.DIME) >= 1) {
                    balanceToBePaid = deduct(balanceToBePaid, Coin.DIME);
                } else if (balanceToBePaid >= (Double.valueOf(Coin.NICKLE.getDenomination()) / 100.0) && inventory.get(Coin.NICKLE) >= 1) {
                    balanceToBePaid = deduct(balanceToBePaid, Coin.NICKLE);
                } else if (balanceToBePaid >= (Double.valueOf(Coin.PENNY.getDenomination()) / 100.0) && inventory.get(Coin.PENNY) >= 1) {
                    balanceToBePaid = deduct(balanceToBePaid, Coin.PENNY);
                }
            }
        }
        System.out.println("BALANCE TO BE PAID: " + balanceToBePaid);
        System.out.println("INVENTORY BALANCE: " + df.format(this.inventoryBalance));
        return balanceToBePaid;
    }

    private double deduct(double balance, Coin coin) {
        double valueToDeduct = Double.valueOf(coin.getDenomination()) / 100;
        balance = Double.valueOf(df.format(balance - valueToDeduct));
        System.out.println("COIN DISPENSED: " + coin.getDenomination()+"P");

        this.inventory.put(coin, this.inventory.get(coin) - 1);
        this.inventoryBalance = Double.valueOf(df.format(this.inventoryBalance - valueToDeduct));

        return balance;
    }

    public void reset() {
        inventory.clear();
        inventoryBalance=0.00;
    }
}
enum Coin {
    PENNY(1), NICKLE(5), DIME(10), QUARTER(25);

    private final int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }
}