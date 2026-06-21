class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        max_cost = max(costs)

        # Frequency array for counting sort
        freq = [0] * (max_cost + 1)

        for cost in costs:
            freq[cost] += 1

        count = 0

        # Buy ice creams from cheapest to most expensive
        for cost in range(1, max_cost + 1):
            if freq[cost] == 0:
                continue

            can_buy = min(freq[cost], coins // cost)

            count += can_buy
            coins -= can_buy * cost

            if coins < cost:
                break

        return count