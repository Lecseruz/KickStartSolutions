package xorwhat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            System.out.print("Case #" + i + ":");
            int n = in.nextInt();
            int q = in.nextInt();
            int[] array = new int[n];
            for (int j = 0; j < n; ++j) {
                int value = in.nextInt();
                array[j] = value;
                int count = countOnes(value);
                if (count % 2 == 1)
                    treeSet.add(j);
            }
            for (int j = 0; j < q; ++j) {
                int index = in.nextInt();
                int value = in.nextInt();
                int count = countOnes(value);
                if (count % 2 == 1) {
                    treeSet.add(index);
                } else {
                    treeSet.remove(index);
                }
                array[index] = value;
                int max;
                if (treeSet.size() % 2 == 0) {
                    max = array.length;
                } else {
                    int first = treeSet.first();
                    int last = treeSet.last();
                    max = Math.max(last, n - first - 1);
                }
                System.out.print(" " + max);
            }
            treeSet.clear();
            System.out.println();
        }
    }

    private static int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}

