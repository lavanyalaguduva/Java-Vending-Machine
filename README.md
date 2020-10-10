# Task

### Write a program to design Vending Machine using your 'favourite language' with all possible tests

1. Accept coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.

2. Allow user to select products Coke(25), Pepsi(35), Soda(45)

3. Allow user to take refund by cancelling the request.

4. Return selected product and remaining change if any.

5. Allow reset operation for vending machine supplier.

### Code Structure

#### Main Classes, Interface
1. VendingMachineAPI - an interface which defines the API of Vending Machine

2. VendingMachineImpl - Implements the VendingMachineAPI

3. CashInventory - Class to perform operation related to cash such as 
adding cash to vending machine, calculating balance, 
deducting balance when an item is bought etc.,

4. ItemInventory - Class to perform operations such as 
adding items to the vending machine and 
decrementing the item count when an item is bought

#### High level tests
1.Buy an unavailable item - Should ask for a valid item to enter

2.Buy an item with exact coins inserted - No change to be dispensed, inventory balance should increase

3.Buy an item with more than required coins inserted - change should be dispensed, inventory balance should increase

4.Buy an item with zero inventory balance - User should still be able to buy an item
                       
