class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rowPrefix = new int[m + 1][n + 1];
        int[][] colPrefix = new int[m + 1][n + 1];

        // Row prefix sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
            }
        }

        // Column prefix sums
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                colPrefix[i + 1][j] = colPrefix[i][j] + grid[i][j];
            }
        }

        int maxSize = Math.min(m, n);

        for (int size = maxSize; size >= 2; size--) {
            for (int r = 0; r + size <= m; r++) {
                for (int c = 0; c + size <= n; c++) {

                    int target = 0;

                    // Main diagonal
                    for (int i = 0; i < size; i++) {
                        target += grid[r + i][c + i];
                    }

                    // Secondary diagonal
                    int diag2 = 0;
                    for (int i = 0; i < size; i++) {
                        diag2 += grid[r + i][c + size - 1 - i];
                    }

                    if (diag2 != target) continue;

                    boolean magic = true;

                    // Check rows
                    for (int i = r; i < r + size; i++) {
                        int rowSum = rowPrefix[i][c + size] - rowPrefix[i][c];
                        if (rowSum != target) {
                            magic = false;
                            break;
                        }
                    }

                    if (!magic) continue;

                    // Check columns
                    for (int j = c; j < c + size; j++) {
                        int colSum = colPrefix[r + size][j] - colPrefix[r][j];
                        if (colSum != target) {
                            magic = false;
                            break;
                        }
                    }

                    if (magic) return size;
                }
            }
        }

        return 1;
    }
}