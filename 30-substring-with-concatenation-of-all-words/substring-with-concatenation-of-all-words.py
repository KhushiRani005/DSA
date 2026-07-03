from collections import Counter, defaultdict

class Solution:
    def findSubstring(self, s: str, words: list[str]) -> list[int]:
        if not s or not words:
            return []

        word_len = len(words[0])
        word_count = len(words)
        total_len = word_len * word_count
        n = len(s)

        target = Counter(words)
        ans = []

        # Try every possible offset
        for offset in range(word_len):
            left = offset
            right = offset
            window = defaultdict(int)
            count = 0

            while right + word_len <= n:
                word = s[right:right + word_len]
                right += word_len

                if word in target:
                    window[word] += 1
                    count += 1

                    while window[word] > target[word]:
                        left_word = s[left:left + word_len]
                        window[left_word] -= 1
                        left += word_len
                        count -= 1

                    if count == word_count:
                        ans.append(left)

                        left_word = s[left:left + word_len]
                        window[left_word] -= 1
                        left += word_len
                        count -= 1

                else:
                    window.clear()
                    count = 0
                    left = right

        return ans