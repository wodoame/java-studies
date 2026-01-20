package sorting;

public class CountingInversions {
    public static int[] countInversions(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        mergeSort(nums, indices, counts, 0, n - 1);
        return counts;
    }

    private static void mergeSort(int[] nums, int[] indices, int[] counts, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, indices, counts, left, mid);
        mergeSort(nums, indices, counts, mid + 1, right);
        merge(nums, indices, counts, left, mid, right);
    }

    private static void merge(int[] nums, int[] indices, int[] counts, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                temp[k] = indices[j];
                rightCount++;
                j++;
            } else {
                temp[k] = indices[i];
                counts[indices[i]] += rightCount;
                i++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = indices[i];
            counts[indices[i]] += rightCount;
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = indices[j];
            j++;
            k++;
        }

        System.arraycopy(temp, 0, indices, left, temp.length);
    }
}
