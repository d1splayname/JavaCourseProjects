import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch
{
    /**
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
    {
        if (a == null || key == null || comparator == null)
        {
            throw new NullPointerException();
        }

        // binary search
        int middle;
        int left = 0;
        int right = a.length - 1;
        int result = -1;

        while (left <= right)
        {
            middle = (left + right) / 2;

            if (comparator.compare(key, a[middle]) == 0)
            {
                // found a match, but need to find first match in this chunk
                result = middle;
                // search left of this result
                right = middle - 1;
            }
            else if (comparator.compare(key, a[middle]) > 0)
            {
                left = middle + 1;
            }
            else
            {
                right = middle - 1;
            }
        }

        return result;
    }

    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
    {
        if (a == null || key == null || comparator == null)
        {
            throw new NullPointerException();
        }

        // binary search
        int middle;
        int left = 0;
        int right = a.length - 1;
        int result = -1;

        while (left <= right)
        {
            middle = (left + right) / 2;

            if (comparator.compare(key, a[middle]) == 0)
            {
                // found a match, but need to find first match in this chunk
                result = middle;
                // search right of this result
                left = middle + 1;
            }
            else if (comparator.compare(key, a[middle]) > 0)
            {
                left = middle + 1;
            }
            else
            {
                right = middle - 1;
            }
        }

        return result;
    }

}
