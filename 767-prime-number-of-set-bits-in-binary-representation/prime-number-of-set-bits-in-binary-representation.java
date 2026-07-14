class Solution {
    public int countPrimeSetBits(int left, int right) {
        boolean[] prime = new boolean[21];
        prime[2] = true;
        prime[3] = true;
        prime[5] = true;
        prime[7] = true;
        prime[11] = true;
        prime[13] = true;
        prime[17] = true;
        prime[19] = true;

        int count = 0;

        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);
            if (prime[bits]) {
                count++;
            }
        }

        return count;
    }
}