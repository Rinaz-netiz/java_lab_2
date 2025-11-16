/**
 * Fenwick Tree (Binary Indexed Tree) for efficient calculation
 * of prefix sums and element updates.
 * 
 * Time complexity:
 * - build: O(n log n) or O(n)
 * - update: O(log n)
 * - prefixSum: O(log n)
 * - rangeSum: O(log n)
 * 
 * Space complexity: O(n)
 */
public class FenwickTree {
    private int[] tree;
    private int n;
    
    /**
     * Constructor to create an empty Fenwick Tree
     * @param size array size
     */
    public FenwickTree(int size) {
        this.n = size;
        this.tree = new int[n + 1]; // 1-based indexing
    }
    
    /**
     * Build Fenwick Tree from array
     * Time complexity: O(n log n)
     * @param arr source array
     */
    public void build(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        
        this.n = arr.length;
        this.tree = new int[n + 1];
        
        // Fill tree by adding elements one by one
        for (int i = 0; i < n; i++) {
            update(i, arr[i]);
        }
    }
    
    /**
     * Optimized Fenwick Tree construction
     * Time complexity: O(n)
     * @param arr source array
     */
    public void buildOptimized(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        
        this.n = arr.length;
        this.tree = new int[n + 1];
        
        // Copy elements (with index shift by 1)
        for (int i = 0; i < n; i++) {
            tree[i + 1] = arr[i];
        }
        
        // Build tree bottom-up
        for (int i = 1; i <= n; i++) {
            int parent = i + (i & -i);
            if (parent <= n) {
                tree[parent] += tree[i];
            }
        }
    }
    
    /**
     * Update element: add delta to element at index
     * Time complexity: O(log n)
     * @param index element index (0-based)
     * @param delta value to add
     */
    public void update(int index, int delta) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        
        // Convert to 1-based indexing
        index++;
        
        // Update all affected tree elements
        while (index <= n) {
            tree[index] += delta;
            index += index & -index; // add lowest bit
        }
    }
    
    /**
     * Set element value (replace, not add)
     * @param index element index (0-based)
     * @param value new value
     */
    public void set(int index, int value) {
        int currentValue = rangeSum(index, index);
        update(index, value - currentValue);
    }
    
    /**
     * Calculate sum of elements from 0 to index inclusive
     * Time complexity: O(log n)
     * @param index last index (0-based, inclusive)
     * @return sum of elements [0..index]
     */
    public int prefixSum(int index) {
        if (index < 0) {
            return 0;
        }
        if (index >= n) {
            index = n - 1;
        }
        
        // Convert to 1-based indexing
        index++;
        
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index; // remove lowest bit
        }
        
        return sum;
    }
    
    /**
     * Calculate sum on range [left, right]
     * Time complexity: O(log n)
     * @param left left boundary (0-based, inclusive)
     * @param right right boundary (0-based, inclusive)
     * @return sum of elements on range
     */
    public int rangeSum(int left, int right) {
        if (left < 0 || right >= n || left > right) {
            throw new IllegalArgumentException(
                String.format("Invalid range: [%d, %d]", left, right)
            );
        }
        
        if (left == 0) {
            return prefixSum(right);
        }
        
        return prefixSum(right) - prefixSum(left - 1);
    }
    
    /**
     * Get current array size
     * @return array size
     */
    public int size() {
        return n;
    }
    
    /**
     * Restore original array from Fenwick Tree
     * @return restored array
     */
    public int[] toArray() {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = rangeSum(i, i);
        }
        return result;
    }
    
    /**
     * String representation of tree
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FenwickTree[");
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(", ");
            sb.append(rangeSum(i, i));
        }
        sb.append("]");
        return sb.toString();
    }
}
