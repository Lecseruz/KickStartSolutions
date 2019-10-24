import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int cs = 1; cs <= t; ++cs)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int q = sc.nextInt();
            int[] pages = new int[n + 1];
            Arrays.fill(pages, 1);
            int r1;
            for (int i = 0; i < m; ++i)
            {
                r1 = sc.nextInt();
                pages[r1] = 0;
            }
            Map<Integer, Long> map = new HashMap<>();
            for (int i = 0; i < q; ++i)
            {
                r1 = sc.nextInt();
                map.computeIfPresent(r1, (k, v) -> ++v);
                map.putIfAbsent(r1, (long)1);
            }
            long ans = 0;
            for (Map.Entry it : map.entrySet())
            {
                Long r2 = (Long)it.getValue();
                r1 = (Integer)it.getKey();
                long cnt = 0;
                if (r2 != 0)
                {
                    for (int i = r1; i <= n; i += r1)
                    {
                        if (pages[i] != 0)
                            ++cnt;
                    }
                    ans += (cnt * r2);
                }
            }
            System.out.println("Case #" + cs + ": " + ans);
        }
    }
}
