package warmup_task

/**
 * Recursive function to calculate f(x) = f(x-1) + f(x-1)
 * with the base case f(0) = 1.
 *
 * This function represents the mathematical definition where:
 * - f(x) is defined as the sum of two calls to f(x-1)
 * - f(0) is defined as 1
 *
 * The result of the function represents the value of 2 raised to the power of x,
 * since the recursive calls double the value at each level.
 *
 * Time Complexity: O(2^x)
 * - There are repeated calculations which is not optimal solution
 *
 * This implementation may lead to stack overflow for large values of x due to deep recursion.
 */
def f_recursive(x: Long): Long = {
  if (x == 0) 1
  else 2*f_recursive(x-1)
}
