class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";

        // Generate sequential numbers of length 2 to 9
        for (int len = 2; len <= 9; len++) {
            for (int start = 0; start + len <= 9; start++) {
                int num = 0;

                for (int i = start; i < start + len; i++) {
                    num = num * 10 + (digits.charAt(i) - '0');
                }

                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}