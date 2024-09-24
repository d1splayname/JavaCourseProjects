import java.util.Arrays;
import java.util.Comparator;


/**
 * Autocomplete.
 */
public class Autocomplete
{

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms)
    {
        if (terms == null)
        {
            throw new NullPointerException();
        }

        this.terms = terms;

        // sort terms - bubble sort
        Term tmp;

        for (int i = 0; i < terms.length - 1; i++)
        {
            for (int j = i; j < terms.length - 1; j++)
            {
                if (terms[j].compareTo(terms[j + 1]) > 0)
                {
                    tmp = this.terms[j];
                    this.terms[j] = this.terms[j + 1];
                    this.terms[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix)
    {
        if (prefix == null)
        {
            throw new NullPointerException();
        }

        int first = BinarySearch.firstIndexOf(this.terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
        int last = BinarySearch.lastIndexOf(this.terms, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
//        System.out.println("first: " + first + "\tlast: " + last);

        Term[] inRangeTerms = Arrays.copyOfRange(this.terms, first, last + 1);

        // sort numbers in weight order - bubble sort
        Comparator<Term> weightComparator = Term.byDescendingWeightOrder();
        Term tmp;

//        for (Term inRangeTerm : inRangeTerms)
//        {
//            System.out.println(inRangeTerm);
//        }
//        System.out.println("-----");
        for (int i = inRangeTerms.length - 1; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
            {
                if (weightComparator.compare(inRangeTerms[j], inRangeTerms[j + 1]) > 0)
                {
//                    System.out.println(inRangeTerms[j] + "<->" + inRangeTerms[j + 1]);
                    tmp = inRangeTerms[j];
                    inRangeTerms[j] = inRangeTerms[j + 1];
                    inRangeTerms[j + 1] = tmp;
                }
//                System.out.print("");
            }
        }

        return inRangeTerms;

        /*
        Term tmp;

        // sort by prefix order
        for (int i = 0; i < terms.length - 1; i++)
        {
            for (int j = i; j < terms.length - 1; j++)
            {
                if (Term.byPrefixOrder(prefix.length()).compare(terms[j], terms[j + 1]) > 0)
                {
                    tmp = this.terms[j];
                    this.terms[j] = this.terms[j + 1];
                    this.terms[j + 1] = tmp;
                }
            }
        }

        tmp = new Term(prefix, 0);

        int first = -1;
        int last = -1;

        for (int i = 0; i < this.terms.length; i++)
        {
            if (first == -1 && tmp.compareTo(this.terms[i]) > 0)
            {
                first = i;
            }
            else if (last == -1 && tmp.compareTo(this.terms[i]) < 0)
            {
                last = i;
            }
        }

//        int first = BinarySearch.firstIndexOf();
//        int last = BinarySearch.lastIndexOf(BinarySearch.);

        Term[] inRange = Arrays.copyOfRange(this.terms, first, last);

        // sort in descending order of weight - bubble sort
//        var compare = Term.byDescendingWeightOrder();
        for (int i = 0; i < terms.length - 1; i++)
        {
            for (int j = i; j < terms.length - 1; j++)
            {
                if (Term.byDescendingWeightOrder().compare(terms[j], terms[j + 1]) > 0)
                {
                    tmp = this.terms[j];
                    this.terms[j] = this.terms[j + 1];
                    this.terms[j + 1] = tmp;
                }
            }
        }

        return inRange;
        */
    }
}