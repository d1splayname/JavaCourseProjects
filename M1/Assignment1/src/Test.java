import java.util.Arrays;

public class Test
{
    public static void main(String[] args)
    {
        int[] a = {2, 8, 7, 3, 4};

        int[] b = new int[a.length];
        int[][] c = {{1, 2, 3}};
        a = c[0];

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

}
