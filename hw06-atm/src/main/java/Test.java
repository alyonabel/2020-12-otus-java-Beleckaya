
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

    }
}
