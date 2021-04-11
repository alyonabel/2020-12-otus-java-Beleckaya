import java.util.ArrayList;
import java.util.Arrays;

public class Bunch {
    private static Denomination d;

    private Denomination getD() {
        return d;
    }
    private  ArrayList<Denomination> list;
    public ArrayList<Denomination> getList() {
        return list;
    }
    private int summa;

    Bunch(Denomination... d){
        list = new ArrayList<>(Arrays.asList(d));
    }

       public int countSum(){
        for(int i = 0; i <list.size(); i++)
        {
            summa+=list.get(i).getBanknote();
        }
        return summa;
    }
  }
