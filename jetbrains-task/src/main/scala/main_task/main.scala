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
///////////////////////////////////////////
  val id = Identifier("abc")
  println(id.toString)

  val tree = Node(List(Identifier("abc"), Node(List(Identifier("def"), Identifier("ghi"))), Identifier("jkl")))
  println(tree.toString)

  val treeResult = Parser.parseString("(a (b c) d)")

  treeResult match {
    case Right(tree) => println(s"Parsed tree: $tree")
    case Left(error) => println(s"Failed to parse: $error")
  }
/////////////////////////////////////////////
  val searchTree = Node(List(Identifier("b"), Identifier("c")))
  val replacement = Identifier("x")

  val newTree = TreeUtils.replace(tree1, searchTree, replacement)

  println(newTree)
}
