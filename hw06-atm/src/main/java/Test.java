
public class Test {
    public static void main(String[] args) {
        Bunch b = new Bunch(Denomination.FIFTY,Denomination.FIVETHOUSAND);
        Bunch b2 = new Bunch(Denomination.TWOTHOUSAND,Denomination.TWOHUNDRED);
        Bunch b3 = new Bunch(Denomination.FIFTY,Denomination.HUNDRED);
        ATM atm = new ATM();
        atm.showBalance();
        atm.putBunch(b);
        atm.putBunch(b2);
        atm.putBunch(b3);
        atm.showBalance();
        ATM.showCells();

        atm.takeMoney(550);
        atm.takeMoney(2400);
        ATM.showCells();

        ATM atm1 = new ATM();
        ATM atm2 = new ATM();

        System.out.println("Before take money:");
        atm1.showBalance();
        atm2.showBalance();

        atm1.takeMoney(10_000);

        System.out.println("After take money from atm1:");
        atm1.showBalance();
        atm2.showBalance(); // из atm2 ничего не снимали, то сумма в нем тоже уменьшилась

    }
}
