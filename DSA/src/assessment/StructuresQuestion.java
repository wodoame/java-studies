package assessment;

public class StructuresQuestion {
    static void main() {
        System.out.println(minCost(new int[]{5, 7, 9, 4, 11}));
    }
    static int minCost(int[] structures){
        // For ascending
        // x, x + 1, x + 2, ..., x + i
        // x + i >= structures[i] (for every i)
        // x >= structures[i] - i (for every i)
        // therefore x = max(structures[i] - i)

        // For descending
        // y, y -1, y - 2, ... , y - i
        // y - i >= structures[i] (for every i)
        // y >= structures[i] + i (for every i)
        // therefore y = max(structures[i] + i)
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        for (int i = 0; i < structures.length; i++) x = Math.max(x, structures[i] - i);
        for (int i = 0; i < structures.length; i++) y = Math.max(y, structures[i] + i);
        // TODO: COMPLETE THIS
        return 0;
    }
}
