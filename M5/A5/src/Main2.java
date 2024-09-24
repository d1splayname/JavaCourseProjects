public class Main2
{
    public static void main(String[] args)
    {
        for (int num : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
        {
            if (num == 1)
            {
                break;
            }
            System.out.println(num);
        }
    }
}
