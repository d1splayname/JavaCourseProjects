////import org.junit.jupiter.api.Assertions;
////import org.junit.jupiter.api.Test;
//
//public class SelectorTest
//{
//    public static void main(String[] args)
//    {
//        int actual;
//        int expected;
//        int[] a;
//
//        // Selector.min()
//
//        a = new int[]{2, 8, 7, 3, 4};
//        expected = 2;
//        actual = Selector.min(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{5, 9, 1, 7, 3};
//        expected = 1;
//        actual = Selector.min(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 7, 6, 5, 4};
//        expected = 4;
//        actual = Selector.min(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 2, 8, 7, 3, 3, 4};
//        expected = 2;
//        actual = Selector.min(a);
//        Assertions.assertEquals(expected, actual);
//
//
//        // Selector.max()
//        a = new int[]{2, 8, 7, 3, 4};
//        expected = 8;
//        actual = Selector.max(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{5, 9, 1, 7, 3};
//        expected = 9;
//        actual = Selector.max(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 7, 6, 5, 4};
//        expected = 8;
//        actual = Selector.max(a);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 2, 8, 7, 3, 3, 4};
//        expected = 8;
//        actual = Selector.max(a);
//        Assertions.assertEquals(expected, actual);
//
//
//        // kmin
//        int k;
//
//        a = new int[]{2, 8, 7, 3, 4};
//        k = 1;
//        expected = 2;
//        actual = Selector.kmin(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{5, 9, 1, 7, 3};
//        k = 3;
//        expected = 5;
//        actual = Selector.kmin(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 7, 6, 5, 4};
//        k = 5;
//        expected = 8;
//        actual = Selector.kmin(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 2, 8, 7, 3, 3, 4};
//        k = 3;
//        expected = 4;
//        actual = Selector.kmin(a, k);
//        Assertions.assertEquals(expected, actual);
//
//
//        // kmax
//        a = new int[]{2, 8, 7, 3, 4};
//        k = 1;
//        expected = 8;
//        actual = Selector.kmax(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{5, 9, 1, 7, 3};
//        k = 3;
//        expected = 5;
//        actual = Selector.kmax(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 7, 6, 5, 4};
//        k = 5;
//        expected = 4;
//        actual = Selector.kmax(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        a = new int[]{8, 2, 8, 7, 3, 3, 4};
//        k = 3;
//        expected = 4;
//        actual = Selector.kmax(a, k);
//        Assertions.assertEquals(expected, actual);
//
//        // range
//        int[][] a1 = {
//                {2, 8, 7, 3, 4},
//                {5, 9, 1, 7, 3},
//                {8, 7, 6, 5, 4},
//                {8, 2, 8, 7, 3, 3, 4}
//        };
//        int[] low1 = {1, 3, 4, 3};
//        int[] high1 = {5, 5, 8, 3};
//        int[][] expected1 = {
//                {2, 3, 4},
//                {5, 3},
//                {8, 7, 6, 5, 4},
//                {7, 3, 3, 4}
//        };
//
//        int low;
//        int high;
//        int[] expectedList;
//        int[] actualList;
//
//        for (int i = 0; i < 4; i++)
//        {
//            a = a1[i];
//            low = low1[i];
//            high = high1[i];
//            expectedList = expected1[i];
//            actualList = Selector.range(a, low, high);
//            Assertions.assertEquals(expected, actual);
//
//        }
//
//
//        // floor
//        int[] k1;
//        int[] expected2;
//
//        a1 = new int[][]{
//                {2, 8, 7, 3, 4},
//                {5, 9, 1, 7, 3},
//                {8, 7, 6, 5, 4},
//                {8, 2, 8, 7, 3, 3, 4}
//        };
//        k1 = new int[]{6, 1, 9, 5};
//        expected2 = new int[]{4, 1, 8, 4};
//
//        for (int i = 0; i < 4; i++)
//        {
////            System.out.println(i);
//
//            a = a1[i];
//            k = k1[i];
//            expected = expected2[i];
//            actual = Selector.floor(a, k);
//            Assertions.assertEquals(expected, actual);
//        }
//
//
//        // ceiling
//        a1 = new int[][]{
//                {2, 8, 7, 3, 4},
//                {5, 9, 1, 7, 3},
//                {8, 7, 6, 5, 4},
//                {8, 2, 8, 7, 3, 3, 4}
//        };
//        k1 = new int[]{1, 7, 0, 5};
//        expected2 = new int[]{2, 7, 4, 7};
//
//        for (int i = 0; i < 4; i++)
//        {
////            System.out.println(i);
//
//            a = a1[i];
//            k = k1[i];
//            expected = expected2[i];
//            actual = Selector.ceiling(a, k);
//            Assertions.assertEquals(expected, actual);
//        }
//    }
//}
