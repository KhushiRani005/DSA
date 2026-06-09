class Solution {
    public double separateSquares(int[][] squares) {
        long totalArea = 0;
        double low = Long.MAX_VALUE;
        double high = 0;

        for (int[] sq : squares) {
            long y = sq[1];
            long l = sq[2];

            totalArea += l * l;
            low = Math.min(low, y);
            high = Math.max(high, y + l);
        }

        double target = totalArea / 2.0;

        for (int iter = 0; iter < 70; iter++) {
            double mid = (low + high) / 2.0;

            double below = 0;

            for (int[] sq : squares) {
                double y = sq[1];
                double l = sq[2];

                if (mid <= y) {
                    continue;
                } else if (mid >= y + l) {
                    below += l * l;
                } else {
                    below += l * (mid - y);
                }
            }

            if (below >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return high;
    }
}
