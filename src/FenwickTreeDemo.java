import java.util.Arrays;

public class FenwickTreeDemo {
    public static void main(String[] args) {
        System.out.println("=== Fenwick Tree Demonstration ===\n");
        
        int[] arr = {1, 3, 5, 7, 9, 11};
        System.out.println("Source array: " + Arrays.toString(arr));
        
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);
        System.out.println("Built tree: " + ft);
        
        System.out.println("\n--- Prefix Sums ---");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("prefixSum(%d) = %d\n", i, ft.prefixSum(i));
        }
        
        System.out.println("\n--- Range Sums ---");
        System.out.printf("rangeSum(1, 3) = %d (3+5+7)\n", ft.rangeSum(1, 3));
        System.out.printf("rangeSum(0, 5) = %d (total sum)\n", ft.rangeSum(0, 5));
        System.out.printf("rangeSum(2, 4) = %d (5+7+9)\n", ft.rangeSum(2, 4));
        
        System.out.println("\n--- Updates ---");
        System.out.println("update(2, 10) - add 10 to element at index 2");
        ft.update(2, 10);
        System.out.println("Tree after update: " + ft);
        System.out.printf("rangeSum(2, 4) = %d (was 21, now 31)\n", ft.rangeSum(2, 4));
        
        System.out.println("\nset(3, 100) - set element at index 3 to 100");
        ft.set(3, 100);
        System.out.println("Tree after set: " + ft);
        System.out.printf("rangeSum(3, 3) = %d\n", ft.rangeSum(3, 3));
        
        System.out.println("\n--- Array Restoration ---");
        int[] restored = ft.toArray();
        System.out.println("Restored array: " + Arrays.toString(restored));
        
        System.out.println("\n=== Performance Test ===");
        performanceTest();
        
        System.out.println("\n=== Build Methods Comparison ===");
        compareBuildMethods();
    }
    
    private static void performanceTest() {
        int n = 100000;
        int[] largeArray = new int[n];
        for (int i = 0; i < n; i++) {
            largeArray[i] = i + 1;
        }
        
        long start = System.currentTimeMillis();
        FenwickTree ft = new FenwickTree(n);
        ft.build(largeArray);
        long buildTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ft.rangeSum(i, Math.min(i + 1000, n - 1));
        }
        long queryTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            ft.update(i, 1);
        }
        long updateTime = System.currentTimeMillis() - start;
        
        System.out.printf("Array size: %d\n", n);
        System.out.printf("Build time: %d ms\n", buildTime);
        System.out.printf("10000 queries time: %d ms\n", queryTime);
        System.out.printf("10000 updates time: %d ms\n", updateTime);
    }
    
    private static void compareBuildMethods() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        
        long start = System.currentTimeMillis();
        FenwickTree ft1 = new FenwickTree(n);
        ft1.build(arr);
        long time1 = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        FenwickTree ft2 = new FenwickTree(n);
        ft2.buildOptimized(arr);
        long time2 = System.currentTimeMillis() - start;
        
        System.out.printf("build(): %d ms\n", time1);
        System.out.printf("buildOptimized(): %d ms\n", time2);
        System.out.printf("Speedup: %.2fx\n", (double)time1 / time2);
    }
}
