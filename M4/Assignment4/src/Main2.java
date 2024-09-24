import java.util.Arrays;

public class Main2
{
    public static void main(String[] args)
    {
        LinkedSet<Integer> test0 = new LinkedSet<>();
        test0.add(1);
        test0.add(2);
        test0.add(3);
        LinkedSet<Integer> test1 = new LinkedSet<>();

        System.out.println(test1.intersection(test0));
    }
}
