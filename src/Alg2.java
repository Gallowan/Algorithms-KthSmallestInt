import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Alg2 {
    public static void main(String[] args) {
        int[] test = generateArr(1000000);
        long startTime = System.currentTimeMillis();
        System.out.println(select(50, test));
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken: " + totalTime + " milliseconds");
        Arrays.sort(test);
        System.out.println(test[50]);
    }

    private static int select(int k, int[] set) {
        if(set.length < 50) {
            Arrays.sort(set);
            return set[k];
        }
        return 0;
    }

    //Generates the array used for testing
    private static int[] generateArr(int len) {
        Set<Integer> random = new HashSet<Integer>();
        for (int i = 0; i < len; i++) {
            int number = (int) (Math.random() * (len * 5));
            if (!random.contains(number)) {
                random.add(number);
            } else {
                i--;
            }
        }
        int[] res = new int[random.size()];
        int counter = 0;
        for (Integer i : random) {
            res[counter] = i;
            counter++;
        }
        return res;
    }
}
