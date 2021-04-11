public class Cells{

    private static int countFifty; //Count of the banknote with Denomination "50"
    private static int countHundred;
    private static int countTwohundred ;
    private static int countFivehundred;
    private static int countThousand;
    private static int countTwothousand;
    private static int countFivethousand;

    public void putToCellsByUser(Bunch bunch){
        for (int i=0; i<bunch.getList().size();i++) {
            if (bunch.getList().get(i).equals(Denomination.FIFTY)) countFifty++;
            else  if (bunch.getList().get(i).equals(Denomination.HUNDRED)) countHundred++;
            else  if (bunch.getList().get(i).equals(Denomination.TWOHUNDRED)) countTwohundred++;
            else  if (bunch.getList().get(i).equals(Denomination.FIVEHUNDRED)) countFivehundred++;
            else  if (bunch.getList().get(i).equals(Denomination.THOUSAND)) countThousand++;
            else  if (bunch.getList().get(i).equals(Denomination.TWOTHOUSAND)) countTwothousand++;
            else  if (bunch.getList().get(i).equals(Denomination.FIVETHOUSAND)) countFivethousand++;
        }
    }
    public int getCountFifty() {
        return countFifty;
    }

    public int getCountHundred() {
        return countHundred;
    }

    public int getCountTwohundred() {
        return countTwohundred;
    }

    public int getCountFivehundred() {
        return countFivehundred;
    }

    public int getCountThousand() {
        return countThousand;
    }

    public int getCountTwothousand() {
        return countTwothousand;
    }

    public int getCountFivethousand() {
        return countFivethousand;
    }

    public int fillCells(Denomination d, int i){
        if(d.equals(Denomination.FIFTY)) return countFifty+=i;
        else if (d.equals(Denomination.HUNDRED)) return countHundred+=i;
        else if (d.equals(Denomination.TWOHUNDRED)) return countTwohundred+=i;
        else if (d.equals(Denomination.FIVEHUNDRED)) return countFivehundred+=i;
        else if (d.equals(Denomination.THOUSAND)) return countThousand+=i;
        else if(d.equals(Denomination.TWOTHOUSAND)) return countTwothousand+=i;
        else if(d.equals(Denomination.FIVETHOUSAND)) return countFivethousand+=i;
        else return 0;
    }

    public int getBalance (){
        int i= Denomination.FIFTY.getBanknote()*countFifty;
        int i2 = Denomination.HUNDRED.getBanknote()*countHundred;
        int i3 = Denomination.TWOHUNDRED.getBanknote()*countTwohundred;
        int i4 = Denomination.FIVEHUNDRED.getBanknote() *countFivehundred;
        int i5 = Denomination.THOUSAND.getBanknote()*countThousand;
        int i6 = Denomination.TWOTHOUSAND.getBanknote()*countTwothousand;
        int i7 = Denomination.FIVETHOUSAND.getBanknote()*countFivethousand;
        return i+i2+i3+i4+i5+i6+i7;
    }

    public int deleteBanknoteFromCells (Denomination d, int i){
        if(d.equals(Denomination.FIFTY)) return countFifty-=i;
        else if (d.equals(Denomination.HUNDRED)) return countHundred-=i;
        else if (d.equals(Denomination.TWOHUNDRED)) return countTwohundred-=i;
        else if (d.equals(Denomination.FIVEHUNDRED)) return countFivehundred-=i;
        else if (d.equals(Denomination.THOUSAND)) return countThousand-=i;
        else if(d.equals(Denomination.TWOTHOUSAND)) return countTwothousand-=i;
        else if(d.equals(Denomination.FIVETHOUSAND)) return countFivethousand-=i;
        else return 0;
    }

}



