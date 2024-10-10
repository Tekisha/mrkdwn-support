import org.scalatest.funsuite.AnyFunSuite
import main_task.{Identifier, Node}

class TreeModelTests extends AnyFunSuite {

  test("Identifier should be equal to another Identifier with the same value") {
    val identifier1 = Identifier("test")
    val identifier2 = Identifier("test")

    assert(identifier1.isEqual(identifier2))
  }

  test("Identifier should not be equal to another Identifier with a different value") {
    val identifier1 = Identifier("test")
    val identifier2 = Identifier("different")

    assert(!identifier1.isEqual(identifier2))
  }

  test("Identifier should not be equal to a Node") {
    val identifier = Identifier("test")
    val node = Node(List(Identifier("test")))

    assert(!identifier.isEqual(node))
  }

  test("Node with the same structure should be equal") {
    val node1 = Node(List(Identifier("a"), Identifier("b")))
    val node2 = Node(List(Identifier("a"), Identifier("b")))

    assert(node1.isEqual(node2))
  }

  test("Node with different children should not be equal") {
    val node1 = Node(List(Identifier("a"), Identifier("b")))
    val node2 = Node(List(Identifier("a"), Identifier("c")))

    assert(!node1.isEqual(node2))
  }

  test("Node with different number of children should not be equal") {
    val node1 = Node(List(Identifier("a"), Identifier("b")))
    val node2 = Node(List(Identifier("a"), Identifier("b"), Identifier("c")))

    assert(!node1.isEqual(node2))
  }

  test("Node should not be equal to an Identifier") {
    val node = Node(List(Identifier("a"), Identifier("b")))
    val identifier = Identifier("a")

    assert(!node.isEqual(identifier))
  }

  test("toString for Identifier should return the value") {
    val identifier = Identifier("test")
    assert(identifier.toString == "test")
  }

  test("toString for Node with children should return TL format") {
    val node = Node(List(Identifier("a"), Identifier("b")))
    assert(node.toString == "(a b)")
  }

  test("toString for nested Node should return TL format with nested structure") {
    val node = Node(List(Identifier("a"), Node(List(Identifier("b"), Identifier("c")))))
    assert(node.toString == "(a (b c))")
  }
}
