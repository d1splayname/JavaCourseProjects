import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test
{
    public static void main(String[] args)
    {
        ArrayList<Integer> coll = new ArrayList<>(Arrays.asList(-4,-4,-4,-4,-4,-4,-4));
        int k = 2;
        System.out.println(coll);
//        coll2.sort(Integer::compare);
//        Collections.reverse(coll2);
        System.out.println(Selector.kmin(coll, k, Integer::compare));

        System.out.println(coll);
    }

}
