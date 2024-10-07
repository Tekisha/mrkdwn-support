package warmup_task

/**
 * Function to calculate f(x) = f(x-1) + f(x-1) using memoization.
 * The base case remains the same: f(0) = 1.
 *
 * This version improves upon the naive recursive implementation by storing
 * previously computed results in a mutable map, reducing the number of
 * function calls and avoiding repeated calculations.
 *
 * Time Complexity: O(x)
 * - Each value from 0 to x is computed at most once, resulting in linear complexity.
 *
 * Space Complexity: O(x)
 * - Space is used for storing previously computed values in the memoization map.
 *
 * This implementation is more efficient than the basic recursive solution,
 * but it still requires additional space for memoization.
 */

def f_memoization(x: Long, memo: scala.collection.mutable.Map[Long, Long] = scala.collection.mutable.Map()): Long = {
  if (x == 0) 1
  else {
    memo.getOrElseUpdate(x, 2 * f_memoization(x - 1, memo))
  }
}
