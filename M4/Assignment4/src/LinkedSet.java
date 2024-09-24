import java.util.ArrayList;
import java.util.Iterator;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Joshua Chen (jzc0289@auburn.edu)
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T>
{

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /**
     * References to the first and last node of the list.
     */
    Node front;
    Node rear;

    /**
     * c
     * The number of nodes in the list.
     */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet()
    {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////

    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this)
        {
            result.append(element).append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////

    /**
     * Returns the current size of this collection.
     *
     * @return the number of elements in this collection.
     */
    public int size()
    {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////

    /**
     * Tests to see if this collection is empty.
     *
     * @return true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param element The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element)
    {
        if (element == null || this.contains(element))
        {
            return false;
        }

        Node current = this.front;
        Node newNode = new Node(element);

        if (current == null)
        {
            this.front = this.rear = newNode;
            this.size++;
            return true;
        }
        else if (this.front.element.compareTo(element) > 0)
        {
            link(newNode, this.front, null);
            this.front = newNode;
            this.size++;
            return true;
        }
        else if (this.rear.element.compareTo(element) < 0)
        {
            link(this.rear, newNode, null);
            this.rear = newNode;
            this.size++;
            return true;
        }

        while (current.next != null)
        {
            if (current.element.compareTo(element) > 0)
            {
//                current = current.prev;
                break;
            }

            current = current.next;
        }

        if (current.element.compareTo(element) > 0)
        {
            current = current.prev;
        }

        link(current, newNode, current.next);
        this.size++;
        return true;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param element The element to be removed.
     * @return true if collection is changed, false otherwise.
     */
    public boolean remove(T element)
    {
        if (element == null || !this.contains(element) || isEmpty())
        {
            return false;
        }
        else if (this.front.element.equals(element))
        {
            if (this.size == 1)
            {
                this.front = this.rear = null;
            }
            else
            {
                this.front = this.front.next;
                this.front.prev = null;
            }

            size--;
            return true;
        }
        else if (this.rear.element.equals(element))
        {
            if (this.size == 1)
            {
                this.front = this.rear = null;
            }
            else
            {
                this.rear = this.rear.prev;
                this.rear.next = null;
            }

            size--;
            return true;
        }

        Node current = this.front;

        while (current.next != null)
        {
            if (current.element.equals(element))
            {
                link(current.prev, current.next, null);
                this.size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param element The element whose presence in this collection is to be tested.
     * @return true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element)
    {
        if (isEmpty())
        {
            return false;
        }

        for (T t : this)
        {
            if (t.equals(element))
            {
                return true;
            }
        }

        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return true if this set contains exactly the same elements as
     * the parameter set, false otherwise
     */
    public boolean equals(Set<T> s)
    {
        for (T element : this)
        {
            if (!s.contains(element))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return true if this set contains exactly the same elements as
     * the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s)
    {
        return equals((Set<T>) s);
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s)
    {
        LinkedSet<T> unionSet = new LinkedSet<>();

        for (T element : this)
        {
            unionSet.add(element);
        }
        for (T element : s)
        {
            unionSet.add(element);
        }

        return unionSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s)
    {
        return union((Set<T>) s);
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s)
    {
        LinkedSet<T> intersectionSet = new LinkedSet<>();

        for (T element : this)
        {
            if (s.contains(element))
            {
                intersectionSet.add(element);
            }
        }

        return intersectionSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return a set that contains elements that are in both
     * this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s)
    {
        return intersection((Set<T>) s);
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s)
    {
        LinkedSet<T> complementSet = new LinkedSet<>();

        for (T element : this)
        {
            if (!s.contains(element))
            {
                complementSet.add(element);
            }
        }

        return complementSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return a set that contains elements that are in this
     * set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s)
    {
        return complement((Set<T>) s);
    }

    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator()
    {
        return new linkedListIterator();
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator()
    {
        return new descendingLinkedListIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return an iterator over members of the power set
     */

    public Iterator<Set<T>> powerSetIterator()
    {
        // compute power set
        Iterator<T> tItr = this.iterator();
        ArrayList<T> elements = new ArrayList<>();

        while (tItr.hasNext())
        {
            elements.add(tItr.next());
        }

        ArrayList<Set<T>> resultPowerSet = powerSet(elements);

        return resultPowerSet.iterator();
    }


    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    private void link(Node n1, Node n2, Node n3)
    {
        if (n2 == null)
        {
            n1.next = null;
        }
        else
        {
            n1.next = n2;
            n2.prev = n1;

            if (n3 != null)
            {
                n2.next = n3;
                n3.prev = n2;
            }
        }
    }

    private ArrayList<Set<T>> powerSet(ArrayList<T> elements)
    {
        ArrayList<Set<T>> returnArray = new ArrayList<>();
        returnArray.add(new LinkedSet<>());

        ArrayList<Set<T>> tmpArray = new ArrayList<>();

        for (T element : elements)
        {
            for (Set<T> subArray : returnArray)
            {
                Set<T> tmpSubArray = new LinkedSet<>();
                for (T element2 : subArray)
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

    ////////////////////
    // Nested classes //
    ////////////////////

    private class linkedListIterator implements Iterator<T>
    {
        private Node current = null;

        @Override
        public boolean hasNext()
        {
            if (isEmpty())
            {
                return false;
            }
            else if (current == null)
            {
                return true;
            }
            else
            {
                return current.next != null;
            }
        }

        @Override
        public T next()
        {
            if (current == null)
            {
                current = front;
            }
            else
            {
                current = current.next;
            }

            return current.element;

        }
    }

    private class descendingLinkedListIterator implements Iterator<T>
    {
        private Node current = null;

        @Override
        public boolean hasNext()
        {
            if (isEmpty())
            {
                return false;
            }
            else if (current == null)
            {
                return true;
            }
            else
            {
                return current.prev != null;
            }
        }

        @Override
        public T next()
        {
            if (current == null)
            {
                current = rear;
            }
            else
            {
                current = current.prev;
            }

            return current.element;

        }
    }

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node
    {
        /**
         * the value stored in this node.
         */
        T element;
        /**
         * a reference to the node after this node.
         */
        Node next;
        /**
         * a reference to the node before this node.
         */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node()
        {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that contains element
         * and with no node before or after it.
         */
        public Node(T e)
        {
            element = e;
            next = null;
            prev = null;
        }
    }

}
