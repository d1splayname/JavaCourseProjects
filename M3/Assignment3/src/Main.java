import java.util.Comparator;

public class Main
{
    public static void main(String[] args)
    {
        // test all matches
        Term[] testTerms = new Term[]{new Term("abcd", 2), new Term("ab", 8), new Term("a", 4), new Term("abc", 10), new Term("abcde", 6)};
        String prefix = "a";

        Autocomplete ac = new Autocomplete(testTerms);
        Term[] inRange = ac.allMatches(prefix);

        for (Term inRangeTerm : inRange)
        {
            System.out.println(inRangeTerm);
        }

//        Term[] testTerms = new Term[]{new Term("az", 1), new Term("abcde", 1)};
        int length = 3;

        // compare by weight
//        long weight1 = 9223372036854775807L;
//        Term[] testTerms = new Term[]{new Term("query", 9223372036854775807L), new Term("query", 1)};
//        Comparator<Term> weightComparator = Term.byDescendingWeightOrder();
//        System.out.println(weightComparator.compare(testTerms[0], testTerms[1]) < 0);

//        Autocomplete ac = new Autocomplete(testTerms);
//        Term[] inRange = ac.allMatches(prefix);

//        System.out.println(hello);

//        System.out.println("-------------");
//        for (Term inRangeTerm : inRange)
//        {
//            System.out.println(inRangeTerm);
//        }

//        String b = "auburn";
//        String c = "aua";
//        String a = "aub";
//        System.out.println(a.compareTo(c));
//        System.out.println(a.compareTo(b));
//        int len = 3;
//        System.out.println(a.substring(0, len));
    }

    static int first(int arr[], int x, int n)
    {
        int low = 0, high = n - 1, res = -1;
        while (low <= high)
        {
            // Normal Binary Search Logic
            int mid = (low + high) / 2;
            if (arr[mid] > x)
                high = mid - 1;
            else if (arr[mid] < x)
                low = mid + 1;

                // If arr[mid] is same as
                // x, we update res and
                // move to the left half.
            else
            {
                res = mid;
                high = mid - 1;
            }
        }
        return res;
    }

    // If x is present in arr[] then returns
    // the index of LAST occurrence of x in
    // arr[0..n-1], otherwise returns -1
    static int last(int arr[], int x, int n)
    {
        int low = 0, high = n - 1, res = -1;
        while (low <= high)
        {
            // Normal Binary Search Logic
            int mid = (low + high) / 2;
            if (arr[mid] > x)
                high = mid - 1;
            else if (arr[mid] < x)
                low = mid + 1;

                // If arr[mid] is same as x,
                // we update res and move to
                // the right half.
            else
            {
                res = mid;
                low = mid + 1;
            }
        }
        return res;
    }
}