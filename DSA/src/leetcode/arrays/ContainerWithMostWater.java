package leetcode.arrays;

public class ContainerWithMostWater {
    static void main() {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right){
            int leftHeight = height[left];
            int rightHeight = height[right];
            maxArea = Math.max(maxArea, Math.min(leftHeight, rightHeight) * (right - left));
            if(height[left] <= height[right]) left++;
            else right--;
        }
     return maxArea;
    }
}
