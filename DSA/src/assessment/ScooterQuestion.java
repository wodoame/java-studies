package assessment;

import java.util.Arrays;

public class ScooterQuestion {
    static void main() {
        System.out.println(solution(23, new int[]{10, 20}));
    }

    public static int solution(int finish, int[] scooters) {
        Arrays.sort(scooters);
        int totalDistance = 0;
        int pos = 0;
        int next = 0;
        while(pos < finish){
            while(next < scooters.length && scooters[next] < pos)next++;
            if(next >= scooters.length)break;
            pos = scooters[next];
            int mov = Math.min(10, finish - pos);
            pos+= mov;
            totalDistance += mov;
        }
        return totalDistance;
    }
}
