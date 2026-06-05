class Solution {

    static class Pair {
        long count;   // number of valid numbers
        long waviness; // total waviness

        Pair(long c, long w) {
            count = c;
            waviness = w;
        }
    }

    private String digits;
    private Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;

        digits = String.valueOf(n);

        memo = new Pair[17][11][11][3][2];

        return dfs(0, 10, 10, 0, true).waviness;
    }

    private Pair dfs(int pos,
                     int prev2,
                     int prev1,
                     int lenState,
                     boolean tight) {

        if (pos == digits.length()) {
            return new Pair(1, 0);
        }

        if (!tight && memo[pos][prev2][prev1][lenState][0] != null) {
            return memo[pos][prev2][prev1][lenState][0];
        }

        int limit = tight ? digits.charAt(pos) - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (lenState == 0 && d == 0) {
                Pair nxt = dfs(pos + 1, 10, 10, 0, nextTight);

                totalCount += nxt.count;
                totalWave += nxt.waviness;
            } else if (lenState == 0) {
                Pair nxt = dfs(pos + 1, 10, d, 1, nextTight);

                totalCount += nxt.count;
                totalWave += nxt.waviness;
            } else if (lenState == 1) {
                Pair nxt = dfs(pos + 1, prev1, d, 2, nextTight);

                totalCount += nxt.count;
                totalWave += nxt.waviness;
            } else {

                int add = 0;

                if ((prev1 > prev2 && prev1 > d) ||
                    (prev1 < prev2 && prev1 < d)) {
                    add = 1;
                }

                Pair nxt = dfs(pos + 1, prev1, d, 2, nextTight);

                totalCount += nxt.count;
                totalWave += nxt.waviness + add * nxt.count;
            }
        }

        Pair ans = new Pair(totalCount, totalWave);

        if (!tight) {
            memo[pos][prev2][prev1][lenState][0] = ans;
        }

        return ans;
    }
}