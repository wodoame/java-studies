package leetcode.arrays;

// leetcode 74
public class SearchA2DArray {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Search for the target row linearly and run binary search on it
        for(int[] row: matrix){ // Alternatively, you could search for the target row using binary search (which is more efficient)
            if(target <= row[row.length - 1])return binSearch(row, target);
        }
        return false;
    }

    public static boolean binSearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int current = arr[mid];
            if(current == target)return true;
            if(current < target)left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }

    // Saw this interesting solution which considers the whole matrix as a flattened array
    public boolean searchMatrixAlternateSolution(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        int m = matrix.length;
        int n = matrix[0].length;

        int low =0;
        int high = m*n-1;

        while(low<=high) {
            int mid = low +(high-low)/2;
            int r = mid/n;
            int c = mid%n;
            if(matrix[r][c]==target) return true;
            else if(matrix[r][c]>target) high = mid-1;
            else low = mid+1;
        }

        return false;
    }
}
