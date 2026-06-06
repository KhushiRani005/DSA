class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int product = nums1[i] * nums2[j];

                dp[i][j] = Math.max(
                    product,
                    product + Math.max(0, dp[i + 1][j + 1])
                );

                dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
            }
        }

        return dp[0][0];
    }
}