package warmup_task

/**
 * Iterative function to calculate f(x) = f(x-1) + f(x-1) using bit shifting.
 * The base case remains the same: f(0) = 1.
 *
 * This implementation takes advantage of the relationship between f(x)
 * and powers of 2, allowing us to compute 2^x directly through bit shifting.
 *
 * Time Complexity: O(1)
 * - The calculation is done in constant time due to the use of bit shifting.
 *
 * Space Complexity: O(1)
 * - No additional space is used that scales with the input size.
 *
 * This is the most efficient approach for computing f(x) given the
 * mathematical definition and provides optimal time and space complexity.
 */

def f_bitshift(x: Long) = 1L << x //2^x
