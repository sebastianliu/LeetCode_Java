/*Given a range [m, n] where 0 <= m <= n <= 2147483647,
        return the bitwise AND of all numbers in this range, inclusive.

        For example, given the range [5, 7], you should return 4.*/

package com.computinglife.leetcode.medium;

/**
 * Created by youngliu on 2/20/16.
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n = n & (n - 1);
        }
        return m & n;
    }
}
