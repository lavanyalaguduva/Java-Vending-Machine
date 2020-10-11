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
1. Buy an unavailable item - 
    - Should prompt the user to enter a valid item

2. Buy an item with exact coins inserted  
    - No change to be dispensed, 
    - Inventory balance should increase
    - Item count should decrease

3. Buy an item with more than required coins inserted 
    - Exact change should be dispensed 
    - Inventory balance should increase
    - item count should decrease

4. Buy an item with zero inventory balance 
    - User should still be able to buy an item

#### Command to run all the operations
VendingMachineImpl.java has a main function. This will prompt the user to select one of the following operations

1.Buy an item

2.Get the inventory data

3.Resetting the vending machine

4.Exit from the application

This can be run from IDE or from the terminal

**To run from the terminal, use the below instruction**

``` Run java VendingMachineImpl from src/main/java```

#### Improvements that can be done
1. If the cash inventory doesn't have enough change to dispense, application goes in an infinite loop. 
This needs to be fixed.

2. Throwing exceptions and using debugger logs to print error and logs instead of console logs

3. Try running the vending machine implementation on a server and connecting the client remotely
                       
