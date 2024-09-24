import java.util.*;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author YOUR NAME HERE (joshuazc@auburn.edu)
 */
public final class Selector
{

    /**
     * Can't instantiate this class.
     * <p>
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     */
    private Selector()
    {
    }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the minimum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp)
    {
        if (coll == null || comp == null)
        {
            throw new IllegalArgumentException();
        }
        else if (coll.isEmpty())
        {
            throw new NoSuchElementException();
        }

        return kmin(coll, 1, comp);
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll the Collection from which the maximum is selected
     * @param comp the Comparator that defines the total order on T
     * @return the maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp)
    {
        if (coll == null || comp == null)
        {
            throw new IllegalArgumentException();
        }
        else if (coll.isEmpty())
        {
            throw new NoSuchElementException();

        }

        return kmax(coll, 1, comp);
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth minimum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth minimum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp)
    {
        // exceptions
        if (coll == null || comp == null)
        {
            throw new IllegalArgumentException();
        }
        else if (coll.isEmpty() || k < 1)
        {
            throw new NoSuchElementException();
        }
        // copy and sort copied array
        ArrayList<T> coll2 = new ArrayList<>(coll);
        coll2.sort(comp);
//        Collections.reverse(coll2);
//        coll2.reversed();

        T kNum = coll2.get(0);
        k--;

        // loop through sorted array to find k-th unique number
        for (T value : coll2)
        {
            if (k == 0)
            {
                return kNum;
            }

            if (comp.compare(value, kNum) > 0)
            {
                kNum = value;
                k--;
            }
        }

        if (k == 0)
        {
            return kNum;
        }
        else
        {
            // no kth value
            throw new NoSuchElementException();
        }

    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll the Collection from which the kth maximum is selected
     * @param k    the k-selection value
     * @param comp the Comparator that defines the total order on T
     * @return the kth maximum value in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp)
    {
        // exceptions
        if (coll == null || comp == null)
        {
            throw new IllegalArgumentException();
        }
        else if (coll.isEmpty() || k < 1)
        {
            throw new NoSuchElementException();
        }

        // copy and sort copied array
        ArrayList<T> coll2 = new ArrayList<>(coll);
        coll2.sort(comp);
        Collections.reverse(coll2);
//        coll2.reversed();
//        ArrayList

//        kmin()

        T kNum = coll2.get(0);
        k--;

        // loop through sorted array to find k-th unique number
        for (T value : coll2)
        {
            if (k == 0)
            {
                return kNum;
            }

            if (comp.compare(value, kNum) < 0)
            {
                kNum = value;
                k--;
            }
        }

        if (k == 0)
        {
            return kNum;
        }
        else
        {
            // no k-th value
            throw new NoSuchElementException();
        }
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the range values are selected
     * @param low  the lower bound of the range
     * @param high the upper bound of the range
     * @param comp the Comparator that defines the total order on T
     * @return a Collection of values between low and high
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */

    public static <T> Collection<T> range(Collection<T> coll, T low, T high, Comparator<T> comp)
    {
        // exceptions
        if (coll == null || comp == null)
        {
            throw new IllegalArgumentException();
        }
        else if (coll.isEmpty())
        {
            throw new NoSuchElementException();
        }

        Collection<T> inRangeList = new ArrayList<>();

        for (T value : coll)
        {
            if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0)
            {
                inRangeList.add(value);
            }
        }

        if (inRangeList.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else
        {
            return inRangeList;
        }
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the ceiling value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the ceiling value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp)
    {
        T max = Selector.max(coll, comp);
        Collection<T> possibleValues = Selector.range(coll, key, max, comp);
        return Selector.min(possibleValues, comp);
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll the Collection from which the floor value is selected
     * @param key  the reference value
     * @param comp the Comparator that defines the total order on T
     * @return the floor value of key in coll
     * @throws IllegalArgumentException as per above
     * @throws NoSuchElementException   as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp)
    {
        T min = Selector.min(coll, comp);
        Collection<T> possibleValues = Selector.range(coll, min, key, comp);
        return Selector.max(possibleValues, comp);
    }
}