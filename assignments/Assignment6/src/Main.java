/* Prof. Santos
 * IT 2660 Fall 2020
 * Robert Pratt
 * Assignment 6
 */

import java.util.Random;

public class Main {

    public static int[] numGenerator(int[] n) {
        Random rand = new Random();
        n = rand.ints(n.length, 1, 100 + 1).toArray();
        return n;
    }

    public static void print(int[] a, int[] b) {
        System.out.printf("Unsorted | Sorted%n");
        for(int i = 0; i < a.length; i++)
            System.out.printf("%4d%6s%5d%n", a[i], "|", b[i]);
    }

    public static int[] mergeSort(int[] a) {
        //base case where the array is in its smallest possible size
        if(a.length <= 1)
            return a;
        //if not already in its base case, we need to get it there
        else {
            //divide the array into two halves
            int[] l = new int[a.length / 2];
            int[] r = new int[a.length - l.length];

            for(int i = 0; i < l.length; i++)
                l[i] = a[i];

            for(int i = 0; i < r.length; i++)
                r[i] = a[l.length + i];

            l = mergeSort(l);
            r = mergeSort(r);

            //create a resulting array that contains the two halves sorted
            int[] result = merge(l, r);
            return result;
        }
    }

    public static int[] merge(int[] l, int[] r){
        int[] s = new int[l.length + r.length];
        int left, right, sorted;
        left = right = sorted = 0;

        //combine the arrays and place their values in numerical order
        while(left < l.length || right < r.length) {
            if(left < l.length && right < r.length) {
                if(l[left] < r[right])
                    s[sorted++] = l[left++];

                else
                    s[sorted++] = r[right++];
            }

            else if(left < l.length)
                s[sorted++] = l[left++];

            else if(right < r.length)
                s[sorted++] = r[right++];
        }

        return s;
    }

    public static void main(String args[]) {
        int[] nums = new int[100];
        nums = numGenerator(nums);
        int[] sortedNums = mergeSort(nums);
        print(nums, sortedNums);
    }
}
