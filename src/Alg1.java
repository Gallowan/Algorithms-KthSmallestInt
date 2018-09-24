import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Alg1 {

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
        int start = 0;
        int end = set.length - 1;
        while (start < end) {
            // Partitions into S!, S2, and S3
            int pivot = partition(set, start, end);
            //if S1.length > k, then partition the left side
            if (pivot > k) {
                end = pivot - 1;
            } // if S1.length < k, then partition the right side
            else if (pivot < k) {
                start = pivot + 1;
            } else {
                // S1.length = k, so return the number
                return set[pivot];
            }
        }
        return set[start];
    }

    //Partitions the array into two separate arrays
    //Elements that are less than the pivot are on the left
    //Elements that are greatere than the pivot are on the right
    private static int partition(int[] set, int start, int end) {
        int pivot = start;
        int temp;
        while (start <= end) {
            //loops until you find an element larger than pivot
            while (start <= end && set[start] <= set[pivot]) {
                start++;
            }
            //loops until you find an element smalle than pivot
            while (start <= end && set[end] > set[pivot]) {
                end--;
            }
            //stop looping when you;ve checked every element
            if (start > end) {
                break;
            }
            // swap the two elements
            temp = set[start];
            set[start] = set[end];
            set[end] = temp;
        }
        //swap pivot
        temp = set[end];
        set[end] = set[pivot];
        set[pivot] = temp;
        return end;
    }

    private static void swap(int[] set, int first, int second) {
        int temp = set[first];
        set[first] = set[second];
        set[second] = temp;
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
