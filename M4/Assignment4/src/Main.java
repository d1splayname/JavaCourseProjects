import java.util.ArrayList;
import java.util.Iterator;

public class Main
{
    public static void main(String[] args)
    {
//        LinkedSet<Integer> ls0 = new LinkedSet<>();
//        ls0.add(4);
//
//        System.out.println(ls0.contains(4));
//
//        ls0.add(1);
//        ls0.add(5);
//        ls0.add(3);
//        ls0.add(1);
//        ls0.add(2);
//
//        LinkedSet<Integer> ls1 = new LinkedSet<>();
//        ls1.add(1);
//        ls1.add(2);
//        ls1.add(3);
//
//        LinkedSet<Integer> ls2 = new LinkedSet<>();
//        ls2.add(2);
//        ls2.add(3);
//        ls2.add(1);
//
//        System.out.println(ls1.intersection(ls2));

        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        set.add(1);
        set.add(2);
        ArrayList<Set<Integer>> powerSet = powerSet(set);

        System.out.println(powerSet);
    }

    private static ArrayList<Set<Integer>> powerSet(ArrayList<Integer> elements)
    {
        ArrayList<Set<Integer>> returnArray = new ArrayList<>();
        returnArray.add(new LinkedSet<>());

        ArrayList<Set<Integer>> tmpArray = new ArrayList<>();

        for (Integer element : elements)
        {
            for (Set<Integer> subArray : returnArray)
            {
                Set<Integer> tmpSubArray = new LinkedSet<>();
                for (Integer element2 : subArray)
                {
                    tmpSubArray.add(element2);
                }
                tmpSubArray.add(element);

                tmpArray.add(tmpSubArray);
            }

            returnArray.addAll(tmpArray);
            tmpArray.clear();
        }

        return returnArray;
    }

//    private static ArrayList<Set<Integer>> powerSet(ArrayList<Integer> elements)
//    {
//        ArrayList<Set<Integer>> returnArray = new ArrayList<>();
//        returnArray.add(new LinkedSet<>());
//
//        ArrayList<Set<Integer>> tmpArray = new ArrayList<>();
//
//
//        for (Integer element : elements)
//        {
//            for (Set<Integer> subArray : returnArray)
//            {
//                Set<Integer> tmpSubArray = subArray;
//                tmpSubArray.add(element);
//
//                tmpArray.add(tmpSubArray);
//            }
//
//            returnArray.addAll(tmpArray);
//            tmpArray.clear();
//        }
//
//        return returnArray;
//    }
}