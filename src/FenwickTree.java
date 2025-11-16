public class FenwickTree {
    private int[] tree;
    private int n;

    public FenwickTree(int size) {
        this.n = size;
        this.tree = new int[n + 1];
    }
    
    public void build(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        
        this.n = arr.length;
        this.tree = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            update(i, arr[i]);
        }
    }
    
    public void buildOptimized(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        
        this.n = arr.length;
        this.tree = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            tree[i + 1] = arr[i];
        }
        
        for (int i = 1; i <= n; i++) {
            int parent = i + (i & -i);
            if (parent <= n) {
                tree[parent] += tree[i];
            }
        }
    }

    public void update(int index, int delta) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        
        index++;
        
        while (index <= n) {
            tree[index] += delta;
            index += index & -index; 
        }
    }
    
    public void set(int index, int value) {
        int currentValue = rangeSum(index, index);
        update(index, value - currentValue);
    }
    
    public int prefixSum(int index) {
        if (index < 0) {
            return 0;
        }
        if (index >= n) {
            index = n - 1;
        }
        
        index++;
        
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index; 
        }
        
        return sum;
    }
    
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
    
    public int size() {
        return n;
    }
    
    public int[] toArray() {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = rangeSum(i, i);
        }
        return result;
    }
    
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
