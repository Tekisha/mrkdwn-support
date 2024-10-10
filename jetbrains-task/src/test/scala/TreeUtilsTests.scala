import main_task.{Identifier, Node, TreeUtils}
import org.scalatest.funsuite.AnyFunSuite
import main_task._;

class TreeUtilsTests extends AnyFunSuite {

  test("Replace identifier with another identifier") {
    val tree = Identifier("a")
    val searchTree = Identifier("a")
    val replacement = Identifier("b")

    val result = TreeUtils.replace(tree, searchTree, replacement)
    assert(result == Identifier("b"))
  }

  test("Replace identifier in a node with another identifier") {
    val tree = Node(List(Identifier("a"), Identifier("b")))
    val searchTree = Identifier("a")
    val replacement = Identifier("x")

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = Node(List(Identifier("x"), Identifier("b")))

    assert(result == expected)
  }

  test("Replace entire node with another node") {
    val tree = Node(List(Identifier("a"), Identifier("b")))
    val searchTree = Node(List(Identifier("a"), Identifier("b")))
    val replacement = Node(List(Identifier("x"), Identifier("y")))

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = Node(List(Identifier("x"), Identifier("y")))

    assert(result == expected)
  }

  test("Replace subtree in nested nodes") {
    val tree = Node(List(Identifier("a"), Node(List(Identifier("b"), Identifier("c")))))
    val searchTree = Node(List(Identifier("b"), Identifier("c")))
    val replacement = Node(List(Identifier("x"), Identifier("y")))

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = Node(List(Identifier("a"), Node(List(Identifier("x"), Identifier("y")))))

    assert(result == expected)
  }

  test("No replacement when search tree is not found") {
    val tree = Node(List(Identifier("a"), Identifier("b")))
    val searchTree = Identifier("x")
    val replacement = Identifier("y")

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = tree // No change should happen

    assert(result == expected)
  }

  test("Replace multiple occurrences of the same subtree") {
    val tree = Node(List(Identifier("a"), Identifier("a"), Node(List(Identifier("a"), Identifier("b")))))
    val searchTree = Identifier("a")
    val replacement = Identifier("x")

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = Node(List(Identifier("x"), Identifier("x"), Node(List(Identifier("x"), Identifier("b")))))

    assert(result == expected)
  }

  test("Replace entire tree") {
    val tree = Node(List(Identifier("a"), Identifier("b")))
    val searchTree = tree // Search for the entire tree
    val replacement = Node(List(Identifier("x"), Identifier("y")))

    val result = TreeUtils.replace(tree, searchTree, replacement)
    val expected = replacement

    assert(result == expected)
  }
}
