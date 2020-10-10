public interface VendingMachineAPI {
    public void initialize();
    public void enterItemToBuy();
    public void insertCoins();
    public void dispenseItemAndChange();
    public void reset();
}
