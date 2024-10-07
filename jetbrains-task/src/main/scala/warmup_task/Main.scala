package warmup_task

@main
def main(): Unit = {
  println(f_recursive(60))

  println(f_memoization(60))

  println(f_iteration(60))
}
