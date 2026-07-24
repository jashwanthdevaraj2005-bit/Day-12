/*
Intuition:
-----------
Instead of trying to build all possible subsequences, think of the rolls as
forming "complete sets" of dice faces.

A complete set is formed when we have seen every value from 1 to k at least once.

Example:
rolls = [4,2,1,2,3,3,2,4,1], k = 4

We can split it greedily as:

[4,2,1,2,3] | [3,2,4,1]

Each part contains all values {1,2,3,4}, so we have 2 complete sets.

Key Observation:
----------------
If we have m complete sets, then EVERY sequence of length m can be formed.

Why?
Suppose we want to form:

[a1, a2, a3, ..., am]

- Pick a1 from the 1st complete set.
- Pick a2 from the 2nd complete set.
- ...
- Pick am from the m-th complete set.

Since every complete set contains all numbers from 1 to k,
the required value is always available.

Therefore:
- m complete sets => every sequence of length m exists.
- We cannot guarantee every sequence of length (m + 1),
  because we would need another complete set.

Hence the answer is:

    completeSets + 1

Greedy:
-------
As soon as we have collected all k faces, we immediately count one
complete set and start building the next one.

This is optimal because once a set already contains all k values,
keeping extra rolls inside it does not help. Those rolls are more useful
for forming future complete sets.

Time Complexity:  O(n)
Space Complexity: O(k)
*/

class Solution {
    public int shortestSequence(int[] rolls, int k) {
        Set<Integer> set = new HashSet<>();
        int completeSets = 0;

        for(int r : rolls){
            set.add(r);
            if(set.size() == k){
                completeSets++;
                set.clear();
            }
        }

        return completeSets + 1;
    }
}