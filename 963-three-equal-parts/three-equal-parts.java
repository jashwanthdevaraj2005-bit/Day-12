class Solution {
    public int[] threeEqualParts(int[] arr) {
        int ones = 0;
        for (int i : arr) if (i == 1) ones++;

		/**
		 * The first base case is a hardcoded case when there are only zeros in the array. 
		 * Since the array size is at least 3 in the case of only zeros there are still available to cut the array and get three equal binary numbers. 
		 * e.g. [0, 0, 0] we can devide into three parts |0, 0, 0| and those numbers are correct binary numbers. 
		 * e.g. [0, 0, 0, 0, 0, ...] we can devide into three parts |0, 0, 0...| and those numbers are correct binary numbers. 
		
		 * arr = [0, 0, 0, 0, 0, ...]
		 * ind = [0, 1, 2, 3, 4]
         * i = 0 and j = 2 because first part is 0 - 0(0, i) indexes, second part is 1 - 1(i + 1, j - 1) indexes and third part is 2 - 2(j, n - 1) indexes
		
		 * So taking into consideration above answer [0, 2] is the first correct answer that we can get in the case of only zeros. 
		*/
        if (ones == 0) return new int[]{0, 2};
		
		/**
		 * If there are ones in an array they should be dividable on 3. 
		 * This is because we need to divide the array into three parts 
		 * and each part should have the same amount of ones 
		 * in the case of binary numbers to be equal
		*/
        if (ones % 3 != 0) return new int[]{-1, -1};

		/**
		* This is the most trickier part of this solution.
		*  Basically what we are doing here is we are looking for the first occurrence of '1' for each chunk. 
		*  Let's take an example: arr = [0, 1, 1, 0, | 0, 0, 1, 1, 0, | 1, 1, 0]

		* Since the count of ones is 6 and 6 / 3 = 2 it means that after every +2 index we are standing on the next chunk. 0 +, 2 +, 4 + ... 
		* Therefore:  
		* First occurrence of '1' for the first chunk is on the index of 1
		* First occurrence of '1' for the second chunk is on the index of 6
		* First occurrence of '1' for the third chunk is on the index of 9
		*/
        int point1 = 0, point2 = 0, point3 = 0;
        int unit = ones / 3;
        int oneCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (oneCounter == 0) point1 = i;
                else if (oneCounter == unit) point2 = i;
                else if (oneCounter == 2 * unit) point3 = i;

                oneCounter++;
            }
        }

		/**
		* The last part is pretty straightforward. 
		* We just need to start all pointers from the first occurrences of '1' and move three of them simultaneously to the right. 
		* As we know binary numbers are equals if all of their bits are equal. Otherwise we return {-1, -1}
		*/
        while (point3 < arr.length) {
            if (arr[point1] != arr[point2] || arr[point2] != arr[point3]) return new int[]{-1, -1};

            point1++;
            point2++;
            point3++;
        }

        return new int[]{point1 - 1, point2};
    }
}