/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        totalSum = getTotalSum(root);
        calculateSubtreeSum(root);

        return (int)(maxProduct % MOD);
    }

    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;

        return node.val
                + getTotalSum(node.left)
                + getTotalSum(node.right);
    }

    private long calculateSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = calculateSubtreeSum(node.left);
        long right = calculateSubtreeSum(node.right);

        long currentSum = node.val + left + right;

        maxProduct = Math.max(
            maxProduct,
            currentSum * (totalSum - currentSum)
        );

        return currentSum;
    }
}