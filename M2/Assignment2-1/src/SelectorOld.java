import java.util.Arrays;

/**
 * Defines a library of selection methods
 * on arrays of ints.
 *
 * @author Joshua Chen (jzc0289@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 01/06/2024
 */
public final class SelectorOld
{
    /**
     * Can't instantiate this class.
     * <p>
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private SelectorOld()
    {
    }


    /**
     * Selects the minimum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int min(int[] a)
    {
        if (a == null || a.length == 0)
        {
            throw new IllegalArgumentException();
        }

        return kmin(a, 1);
    }


    /**
     * Selects the maximum value from the array a. This method
     * throws IllegalArgumentException if a is null or has zero
     * length. The array a is not changed by this method.
     */
    public static int max(int[] a)
    {
        if (a == null || a.length == 0)
        {
            throw new IllegalArgumentException();
        }

        return kmax(a, 1);
    }


    /**
     * Selects the kth minimum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth minimum value. Note that there is no kth
     * minimum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmin(int[] a, int k)
    {
        // exceptions
        if (a == null || a.length == 0 || k < 1 || k > a.length)
        {
            throw new IllegalArgumentException();
        }

        // copy and sort new array copy
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Arrays.sort(b);

        int kNum = b[0];
        k--;

        // loop through sorted array to find k-th unique number
        for (int i = 1; i < b.length; i++)
        {
            if (k == 0)
            {
                return kNum;
            }

            if (b[i] > kNum)
            {
                kNum = b[i];
                k--;
            }
        }

        if (k == 0)
        {
            return kNum;
        }

        throw new IllegalArgumentException("not enough diversity. SMH.");
    }


    /**
     * Selects the kth maximum value from the array a. This method
     * throws IllegalArgumentException if a is null, has zero length,
     * or if there is no kth maximum value. Note that there is no kth
     * maximum value if k < 1, k > a.length, or if k is larger than
     * the number of distinct values in the array. The array a is not
     * changed by this method.
     */
    public static int kmax(int[] a, int k)
    {
        // exceptions
        if (a == null || a.length == 0 || k < 1 || k > a.length)
        {
            throw new IllegalArgumentException();
        }

        // copy and sort new array copy
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        Arrays.sort(b);

        int kNum = b[b.length - 1];
        k--;

        // loop through sorted array to find k-th unique number
        for (int i = b.length - 2; i >= 0; i--)
        {
            if (k == 0)
            {
                return kNum;
            }

            if (b[i] < kNum)
            {
                kNum = b[i];
                k--;
            }
        }

        if (k == 0)
        {
            return kNum;
        }

        throw new IllegalArgumentException("not enough diversity. SMH.");
    }


    /**
     * Returns an array containing all the values in a in the
     * range [low..high]; that is, all the values that are greater
     * than or equal to low and less than or equal to high,
     * including duplicate values. The length of the returned array
     * is the same as the number of values in the range [low..high].
     * If there are no qualifying values, this method returns a
     * zero-length array. Note that low and high do not have
     * to be actual values in a. This method throws an
     * IllegalArgumentException if a is null or has zero length.
     * The array a is not changed by this method.
     */
    public static int[] range(int[] a, int low, int high)
    {
        // exceptions
        if (a == null || a.length == 0)
        {
            throw new IllegalArgumentException();
        }

        // find count of numbers in the range
        int size = 0;
        int[] tmp = new int[a.length];
        for (int i : a)
        {
            if (low <= i && i <= high)
            {
                tmp[size] = i;
                size++;
            }
        }

        int[] returned = new int[size];

        System.arraycopy(tmp, 0, returned, 0, size);

        return returned;
    }


    /**
     * Returns the smallest value in a that is greater than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int ceiling(int[] a, int key)
    {
        if (a == null || a.length == 0)
        {
            throw new IllegalArgumentException();
        }

        boolean hasValid = false;

        for (int j : a)
        {
            if (j >= key)
            {
                hasValid = true;
                break;
            }
        }

        if (!hasValid)
        {
            throw new IllegalArgumentException();
        }

        int num = a[0];
        for (int j : a)
        {
            if (j == key)
            {
                return j;
            }
            else if (j > key)
            {
                num = j;
                for (int k : a)
                {
                    if (num > k && k > key)
                    {
                        num = k;
                    }
                }
            }
        }

        return num;
    }


    /**
     * Returns the largest value in a that is less than or equal to
     * the given key. This method throws an IllegalArgumentException if
     * a is null or has zero length, or if there is no qualifying
     * value. Note that key does not have to be an actual value in a.
     * The array a is not changed by this method.
     */
    public static int floor(int[] a, int key)
    {
        if (a == null || a.length == 0)
        {
            throw new IllegalArgumentException();
        }

        boolean hasValid = false;

        for (int j : a)
        {
            if (j <= key)
            {
                hasValid = true;
                break;
            }
        }

        if (!hasValid)
        {
            throw new IllegalArgumentException();
        }

        int num = a[0];
        for (int j : a)
        {
            if (j == key)
            {
                return j;
            }
            else if (j < key)
            {
                num = j;
                for (int k : a)
                {
                    if (num < k && k < key)
                    {
                        num = k;
                    }
                }
            }
        }

        return num;
    }

}
