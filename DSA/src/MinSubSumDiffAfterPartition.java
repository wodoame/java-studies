public class MinSubSumDiffAfterPartition {
    static void main() {
        int[] nums = {8, 3, 5, 1, 2};
        System.out.println(quickSelect(nums, 0, nums.length -1, 3));
    }

    public static int partition(int[] arr, int low, int high){
        int i = low;
        int pivot = arr[high];
        for (int j = low; j < high; j++) {
           if(arr[j] < pivot){
              swap(arr, i++, j);
           }
        }
        swap(arr, i, high);
        return i;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int quickSelect(int[] arr, int low, int high, int k){
        if (low == high) {
            return arr[low];
        }

        int pi = partition(arr, low, high);
        if(pi == k - 1){
            return arr[pi];
        }
        if(k - 1 < pi){
            return quickSelect(arr, low, pi - 1, k);
        }
        return quickSelect(arr, pi + 1, high, k);
    }

}
