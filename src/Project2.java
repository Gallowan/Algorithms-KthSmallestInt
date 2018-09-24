/**
 * CS 331.01 - Design and Analysis of Algorithms
 * Professor: SSalloum
 *
 * Programming Assignment #2
 * <kth-smallest integer>
 *
 * Justin Galloway
 *
 * ~Source Code~
 */


import java.util.*;

public class Project2 {
    public static void main(String[] args) {

        Random generator = new Random();
        long timer;
        long alg1;
        long timer2;
        long alg2;
        int[] input = new int[1000]; //Insert whatever number of ints for tests
        int k = 400;
        for (int i = 0; i < input.length-1; i++){
            input[i] = generator.nextInt();
        }
        System.out.println("Array Size: " + input.length);
        System.out.println("K: " + k);

        timer = System.nanoTime();
        System.out.println("Algorithm1: " + kth1(k, input));
        alg1 = System.nanoTime() - timer;
        System.out.println("Time Elapsed: " + alg1);

        timer2 = System.nanoTime();
        System.out.println("Algorithm2 Value: " + kth2(k, input));
        alg2 = System.nanoTime() - timer2;
        System.out.println("Time Elapsed: " + alg2);
    }

    public static int kth1 (int k, int[] set){
        int[] newArr = new int[set.length];
        if (set.length == 1) return set[0]; //Return one thing if only one is found
        else {
            int selected = set[set.length/2];
            int s1 = 0, s2 = 0, s3 = 0;
            // Fill in the newly partitioned array
            for (int a = 0; a < set.length; a++){
                if(set[a] < selected){
                    newArr[s1] = set[a];
                    s1++;
                } else if (set[a] > selected){
                    newArr[(newArr.length-1)-s3] = set[a];
                    s3++;
                } else {
                    s2++;
                }
            }
            int sTemp = s1;
            if (s2 > 0){
                for (int b = 0; b < s2; b++){
                    newArr[sTemp] = selected;
                    sTemp++;
                }
            }
            if (s1 >= k){
                return kth1(k, Arrays.copyOfRange(newArr, 0, s1));
            } else {
                if ((s1 + s2) >= k){
                    return selected;
                } else {
                    return kth1(k - s1 - s2, Arrays.copyOfRange(newArr, s1+s2, newArr.length));
                }
            }
        }
    }
    
    public static int kth2 (int k, int[] set){
        //Use Java Sort if the array is small.
        if (set.length < 50){
            Arrays.sort(set);
            return set[k];
        } else {
            int[] newArr = new int[set.length];
            int[] M = new int[set.length/5];
            for(int i = 0; i < set.length/5; i++){
                int[] temp = Arrays.copyOfRange(set, i*5, (i+1)*5-1);
                Arrays.sort(temp);
                M[i] = temp[temp.length/2];
            }
            Arrays.sort(M);
            // Find the pivot points
            int selected = M[M.length/2];
            int s1 = 0, s2 = 0, s3 = 0;
            for (int a = 0; a < set.length; a++){
                if(set[a] < selected){
                    newArr[s1] = set[a];
                    s1++;
                } else if (set[a] > selected){
                    newArr[(newArr.length-1)-s3] = set[a];
                    s3++;
                } else {
                    s2++;
                }
            }
            int sTemp = s1;
            if (s2 > 0){
                for (int b = 0; b < s2; b++){
                    newArr[sTemp] = selected;
                    sTemp++;
                }
            }
            if (s1 >= k){
                return kth2(k, Arrays.copyOfRange(newArr, 0, s1));
            } else {
                if ((s1 + s2) >= k){
                    return selected;
                } else {
                    return kth2(k - s1 - s2, Arrays.copyOfRange(newArr, s1+s2, newArr.length));
                }
            }
        }
    }
}
