import java.util.*;

class Solution {
    private static int par[], ans;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            par = new int[n];
            ans = 0;
            for (int j = 0; j < n; j++) {
                par[j] = j;
            }
            for (int j = 0; j < m; j++) {
                union(sc.nextInt() - 1, sc.nextInt() - 1);
            }
            HashSet<Integer> hm = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (!hm.contains(find(j))) {
                    hm.add(find(j));
                }

            }
            ans += (hm.size() - 1) * 2;
            System.out.println("Case #" + (i + 1) + ": " + ans);
        }
    }

    public static int find(int v) {
        return (par[v] == v ? v : (par[v] = find(par[v])));
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        ans++;
        par[v] = u;

    }
}