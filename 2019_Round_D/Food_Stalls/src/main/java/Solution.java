import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int k = in.nextInt();
            int n = in.nextInt();
            List<Integer> costs = new ArrayList<>(n);
            List<Integer> distances = new ArrayList<>(n);
            System.out.print("Case #" + i + ":");
            for (int j = 0; j < n; ++j) {
                distances.add(in.nextInt());
            }
            for (int j = 0; j < n; ++j) {
                costs.add(in.nextInt());
            }
            long min = Long.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                int sum = costs.get(j);
                List<Integer> list = new ArrayList<>(n);
                for (int l = 0; l < n; ++l) {
                    int value = Math.abs(distances.get(j) - distances.get(l));
                    value += costs.get(l);
                    if (l != j)
                        list.add(value);
                }
                Collections.sort(list);
                for (int l = 0; l < k; ++l) {
                    sum += list.get(l);
                }
                if (min > sum) {
                    min = sum;
                }
            }
            System.out.println(" " + min);
        }
    }
}
