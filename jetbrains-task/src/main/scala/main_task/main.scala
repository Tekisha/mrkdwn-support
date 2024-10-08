package main_task

@main
def main(): Unit = {
  val tree1 = Node(List(Identifier("a"), Node(List(Identifier("b"), Identifier("c"))), Identifier("d")))
  val tree2 = Node(List(Identifier("a"), Node(List(Identifier("b"), Identifier("c"))), Identifier("d")))

  val result = tree1.isEqual(tree2)
  println(result)

  val tree3 = Node(List(Identifier("a"), Node(List(Identifier("b"), Identifier("x"))), Identifier("d")))
  val result2 = tree1.isEqual(tree3)
  println(result2)
}
