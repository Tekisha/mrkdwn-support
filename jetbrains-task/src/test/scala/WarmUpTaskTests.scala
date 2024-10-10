import org.scalatest.funsuite.AnyFunSuite
import warmup_task.{f_bitshift, f_memoization, f_recursive}

class WarmUpTaskTests extends AnyFunSuite {

  test("f_bitshift should return correct value for small values of x") {
    assert(f_bitshift(0) == 1)
    assert(f_bitshift(1) == 2)
    assert(f_bitshift(2) == 4)
    assert(f_bitshift(3) == 8)
  }

  test("f_bitshift should return correct value for large values of x") {
    assert(f_bitshift(10) == 1024)
    assert(f_bitshift(20) == 1048576)
    assert(f_bitshift(30) == 1073741824L)
  }

  test("f_memoization should return correct value for small values of x") {
    assert(f_memoization(0) == 1)
    assert(f_memoization(1) == 2)
    assert(f_memoization(2) == 4)
    assert(f_memoization(3) == 8)
  }

  test("f_memoization should return correct value for large values of x") {
    assert(f_memoization(10) == 1024)
    assert(f_memoization(20) == 1048576)
    assert(f_memoization(30) == 1073741824L)
  }

  test("f_recursive should return correct value for small values of x") {
    assert(f_recursive(0) == 1)
    assert(f_recursive(1) == 2)
    assert(f_recursive(2) == 4)
    assert(f_recursive(3) == 8)
  }

  test("f_recursive should return correct value for large values of x") {
    assert(f_recursive(10) == 1024)
    assert(f_recursive(20) == 1048576)
    assert(f_recursive(30) == 1073741824L)
  }

  test("All methods should return the same result for various values of x") {
    val valuesToTest = List(0, 1, 2, 5, 10, 15, 20, 30)

    for (x <- valuesToTest) {
      assert(f_bitshift(x) == f_memoization(x))
      assert(f_bitshift(x) == f_recursive(x))
    }
  }
}
