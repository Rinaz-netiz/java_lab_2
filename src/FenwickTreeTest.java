public class FenwickTreeTest {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Running Fenwick Tree Tests ===\n");
        
        testBuild();
        testBuildOptimized();
        testPrefixSum();
        testRangeSum();
        testUpdate();
        testSet();
        testSize();
        testToArray();
        testExceptions();
        testBoundary();
        testNegativeNumbers();
        testZeroArray();
        testSingleElement();
        testLargeTree();
        testCorrectness();
        
        System.out.println("\n=== Test Results ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        
        if (testsFailed == 0) {
            System.out.println("\n✓ All tests passed successfully!");
        } else {
            System.out.println("\n✗ Some tests failed");
        }
    }
    
    
    private static void testBuild() {
        System.out.println("Test: tree build");
        int[] arr = {1, 2, 3, 4, 5};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        assertEquals(15, ft.prefixSum(4), "Prefix sum");
        assertEquals(5, ft.size(), "Tree size");
    }
    
    private static void testBuildOptimized() {
        System.out.println("Test: optimized build");
        int[] arr = {1, 2, 3, 4, 5};
        FenwickTree ft1 = new FenwickTree(arr.length);
        FenwickTree ft2 = new FenwickTree(arr.length);
        
        ft1.build(arr);
        ft2.buildOptimized(arr);
        
        for (int i = 0; i < arr.length; i++) {
            assertEquals(ft1.prefixSum(i), ft2.prefixSum(i), 
                "Both methods give same result at index " + i);
        }
    }
    
    
    private static void testPrefixSum() {
        System.out.println("Test: prefix sums");
        int[] arr = {1, 3, 5, 7, 9, 11};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        assertEquals(1, ft.prefixSum(0), "prefixSum(0)");
        assertEquals(4, ft.prefixSum(1), "prefixSum(1)");
        assertEquals(9, ft.prefixSum(2), "prefixSum(2)");
        assertEquals(16, ft.prefixSum(3), "prefixSum(3)");
        assertEquals(25, ft.prefixSum(4), "prefixSum(4)");
        assertEquals(36, ft.prefixSum(5), "prefixSum(5)");
        
        assertEquals(0, ft.prefixSum(-1), "prefixSum(-1) should be 0");
        assertEquals(36, ft.prefixSum(100), "prefixSum(100) should be total sum");
    }
    
    
    private static void testRangeSum() {
        System.out.println("Test: range sums");
        int[] arr = {1, 3, 5, 7, 9, 11};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        assertEquals(1, ft.rangeSum(0, 0), "rangeSum(0, 0)");
        assertEquals(15, ft.rangeSum(1, 3), "rangeSum(1, 3)");
        assertEquals(21, ft.rangeSum(2, 4), "rangeSum(2, 4)");
        assertEquals(36, ft.rangeSum(0, 5), "rangeSum(0, 5)");
        
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], ft.rangeSum(i, i), 
                "rangeSum(" + i + ", " + i + ") should equal arr[" + i + "]");
        }
    }
    
    
    private static void testUpdate() {
        System.out.println("Test: element updates");
        int[] arr = {1, 3, 5, 7, 9, 11};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        ft.update(2, 10);
        assertEquals(19, ft.prefixSum(2), "After update(2, 10)");
        assertEquals(31, ft.rangeSum(2, 4), "rangeSum after update");
        
        ft.update(0, 1);
        ft.update(1, 2);
        assertEquals(2, ft.rangeSum(0, 0), "After update(0, 1)");
        assertEquals(5, ft.rangeSum(1, 1), "After update(1, 2)");
        
        ft.update(2, -5);
        assertEquals(10, ft.rangeSum(2, 2), "After update(2, -5)");
    }
    
    
    private static void testSet() {
        System.out.println("Test: set value");
        int[] arr = {1, 3, 5, 7, 9, 11};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        ft.set(3, 100);
        assertEquals(100, ft.rangeSum(3, 3), "set(3, 100)");
        assertEquals(114, ft.rangeSum(2, 4), "rangeSum after set");
        
        ft.set(2, 0);
        assertEquals(0, ft.rangeSum(2, 2), "set(2, 0)");
    }
    
    
    private static void testSize() {
        System.out.println("Test: tree size");
        FenwickTree ft = new FenwickTree(6);
        ft.build(new int[]{1, 3, 5, 7, 9, 11});
        assertEquals(6, ft.size(), "size()");
    }
    
    private static void testToArray() {
        System.out.println("Test: array conversion");
        int[] arr = {1, 3, 5, 7, 9, 11};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        int[] result = ft.toArray();
        assertArrayEquals(arr, result, "toArray()");
        
        ft.update(0, 1);
        ft.update(2, 5);
        int[] expected = {2, 3, 10, 7, 9, 11};
        assertArrayEquals(expected, ft.toArray(), "toArray() after updates");
    }
    
    
    private static void testExceptions() {
        System.out.println("Test: exception handling");
        FenwickTree ft = new FenwickTree(6);
        ft.build(new int[]{1, 3, 5, 7, 9, 11});
        
        assertThrows(() -> ft.update(10, 5), "update with out of bounds index");
        assertThrows(() -> ft.update(-1, 5), "update with negative index");
        assertThrows(() -> ft.rangeSum(5, 2), "rangeSum with left > right");
        assertThrows(() -> ft.rangeSum(-1, 3), "rangeSum with negative index");
        assertThrows(() -> ft.build(null), "build with null");
        assertThrows(() -> ft.build(new int[]{}), "build with empty array");
    }
    
    
    private static void testBoundary() {
        System.out.println("Test: boundary cases");
        
        FenwickTree ft = new FenwickTree(6);
        ft.build(new int[]{1, 3, 5, 7, 9, 11});
        
        assertEquals(0, ft.prefixSum(-1), "Negative index in prefixSum");
        assertEquals(36, ft.prefixSum(100), "Large index in prefixSum");
    }
    
    private static void testSingleElement() {
        System.out.println("Test: single element tree");
        FenwickTree ft = new FenwickTree(1);
        ft.build(new int[]{42});
        
        assertEquals(42, ft.prefixSum(0), "prefixSum for single element");
        assertEquals(42, ft.rangeSum(0, 0), "rangeSum for single element");
        
        ft.update(0, 8);
        assertEquals(50, ft.prefixSum(0), "After update");
    }
    
    private static void testNegativeNumbers() {
        System.out.println("Test: negative numbers");
        int[] arr = {-5, 10, -3, 7, -2};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        assertEquals(7, ft.prefixSum(4), "Sum with negative numbers");
        assertEquals(14, ft.rangeSum(1, 3), "rangeSum with negatives");
    }
    
    private static void testZeroArray() {
        System.out.println("Test: array of zeros");
        int[] zeros = new int[5];
        FenwickTree ft = new FenwickTree(zeros.length);
        ft.build(zeros);
        
        assertEquals(0, ft.prefixSum(4), "Sum of zeros");
        
        ft.update(2, 10);
        assertEquals(10, ft.prefixSum(4), "After update");
    }
    
    private static void testLargeTree() {
        System.out.println("Test: large tree");
        int n = 10000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        
        FenwickTree ft = new FenwickTree(n);
        ft.build(arr);
        
        long expectedSum = (long) n * (n + 1) / 2;
        assertEquals((int)expectedSum, ft.prefixSum(n - 1), "Sum of large array");
    }
    
    private static void testCorrectness() {
        System.out.println("Test: correctness check");
        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int expected = naiveRangeSum(arr, i, j);
                int actual = ft.rangeSum(i, j);
                assertEquals(expected, actual, 
                    "rangeSum(" + i + ", " + j + ")");
            }
        }
    }
        
    private static int naiveRangeSum(int[] arr, int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += arr[i];
        }
        return sum;
    }
    
    private static void assertEquals(int expected, int actual, String message) {
        if (expected == actual) {
            testsPassed++;
            System.out.println("  ✓ " + message);
        } else {
            testsFailed++;
            System.out.println("  ✗ " + message + 
                " (expected: " + expected + ", got: " + actual + ")");
        }
    }
    
    private static void assertArrayEquals(int[] expected, int[] actual, String message) {
        boolean equals = true;
        if (expected.length != actual.length) {
            equals = false;
        } else {
            for (int i = 0; i < expected.length; i++) {
                if (expected[i] != actual[i]) {
                    equals = false;
                    break;
                }
            }
        }
        
        if (equals) {
            testsPassed++;
            System.out.println("  ✓ " + message);
        } else {
            testsFailed++;
            System.out.print("  ✗ " + message + " (expected: [");
            for (int i = 0; i < expected.length; i++) {
                System.out.print(expected[i]);
                if (i < expected.length - 1) System.out.print(", ");
            }
            System.out.print("], got: [");
            for (int i = 0; i < actual.length; i++) {
                System.out.print(actual[i]);
                if (i < actual.length - 1) System.out.print(", ");
            }
            System.out.println("])");
        }
    }
    
    private static void assertThrows(Runnable runnable, String message) {
        try {
            runnable.run();
            testsFailed++;
            System.out.println("  ✗ " + message + " (exception was not thrown)");
        } catch (Exception e) {
            testsPassed++;
            System.out.println("  ✓ " + message);
        }
    }
    
    @FunctionalInterface
    interface Runnable {
        void run() throws Exception;
    }
}
