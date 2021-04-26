import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bunch {
    private static Denomination d;
    private  List<Denomination> list;
    private int sum;

    Bunch(Denomination... d){
        list = new ArrayList<>(Arrays.asList(d));
    }

    public List<Denomination> getList() {
        return list;
    }

    public int countSum(){
        for(int i = 0; i <list.size(); i++)
        {
            sum+=list.get(i).getBanknote();
        }
        return sum;
    }
  }
