public interface VendingMachineAPI {
    public void initialize();
    public void buyAnItem();
    public void cancelRequestAndRefund();
    public void getInventoryData();
    public void reset();
}
