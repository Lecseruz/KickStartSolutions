import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        try (Scanner sc = new Scanner(System.in))
        {
            long[] two = new long[55];
            long store = 1;
            int mx = 50;
            for (int i = 0; i < 55; ++i)
            {
                two[i] = store;
                store *= 2;
            }
            int tests = sc.nextInt();
            for (int cs = 1; cs <= tests; ++cs)
            {
                int n = sc.nextInt();
                long m = sc.nextLong();
                long[] one = new long[55];
                long[] zero = new long[55];
                long[] suf = new long[55];
                Arrays.fill(one, 0);
                Arrays.fill(zero, 0);
                Arrays.fill(suf, 0);
                for (int i = 0; i < n; ++i)
                {
                    long r1 = sc.nextLong();
                    for (int j = 0; j < mx; ++j)
                    {
                        if ((r1 & two[j]) != 0)
                        {
                            one[j] += two[j];
                        }
                        else
                            zero[j] += two[j];
                    }
                }
                for (int i = 0; i < mx; ++i)
                {
                    suf[i] = Math.min(one[i], zero[i]);
                }
                for (int i = 0; i < mx; ++i)
                {
                    suf[i + 1] += suf[i];
                }
                // DB(suf[50]);
                if (suf[50] > m)
                {
                    System.out.println("Case #" + cs + ": -1");
                    continue;
                }
                long sum = 0, ans = 0;
                for (int i = mx - 1; i >= 0; --i)
                {
                    long infront = 0;
                    if (i >= 1)
                        infront = suf[i - 1];
                    if (zero[i] + sum + infront <= m)
                    {
                        // DB(i);
                        // DB(sum);
                        // DB(zero[i]);
                        // DB(infront);
                        ans += two[i];
                        sum += zero[i];
                    }
                    else
                    {
                        sum += one[i];
                    }
                }
                System.out.println("Case #" + cs + ": " + ans);
            }
        }
        //        try (Scanner sc = new Scanner(System.in))
        //        {
        //            int t = sc.nextInt();
        //            for (int cs = 1; cs <= t; ++cs)
        //            {
        //                int n = sc.nextInt();
        //                long m = sc.nextLong();
        //                List<Long> list = new ArrayList<>();
        //                for (int i = 0; i < n; ++i)
        //                {
        //                    list.add(sc.nextLong());
        //                }
        //                int max = -1;
        //                long maxValue = -1;
        //                for (Long val : list){
        //                    maxValue = Math.max(maxValue, val);
        //                }
        //                maxValue = Math.max(maxValue, m);
        //                for (int i = 0; i <= maxValue * 2; ++i)
        //                {
        //                    long value = 0;
        //                    for (int j = 0; j < n; ++j)
        //                    {
        //                        value += list.get(j) ^ i;
        //                    }
        //                    if (value <= m)
        //                        max = Math.max(max, i);
        //                }
        //                list.clear();
        //                System.out.println("Case #" + cs + ": " + max);
        //            }
        //        }
    }
}
