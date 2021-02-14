ReadMe.txt
 Write a program to design Vending Machine using your 'favourite language' with all possible tests

1. Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
2. Allow user to select products Coke(25), Pepsi(35), Soda(45)
3. Allow user to take refund by cancelling the request.
4. Return selected product and remaining change if any.
5. Allow reset operation for vending machine supplier.

Project built with Gradle built automation tool.
In the the build.gradle file, I used Junit, assertj and lombok as dependencies.
Project structured with
    - model; Coin and Product created as Enum.
    - exceptions; defined by ErrorType and VendingMachineException.
    - repo; VendingMachineInventory class. I started to coinInventory but not finished yet.
Than created MyVending class for vending machine activities such as
1- acceptCoin
2- chooseProduct
3- refundOverPayment
4- sumOfRefund
5- cancelProcess
6- reset

At the end I created MyVendingMachineTest class to test methods and exceptions.
I have tested following 6 scenarios
1- Accept 1,5,10,25 Cents
2- Choosing product with insufficient balance will cause an exception
3- Verify out of stock exception message with assertj library
4- Out of Stock Product will throw exception
5- Select product and Check Inventory
6- Select unavailable product and get exception
7- Select Product and Check refunded Coins
8- Insert coins then cancel process and get coins back
9- Reset after inserting coins then total should be 0
