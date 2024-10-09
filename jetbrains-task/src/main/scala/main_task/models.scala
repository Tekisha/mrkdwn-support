package main_task

/**
 * Sealed trait 'Tree' represents the basic structure for a tree in TL format.
 * Trees can either be identifiers (leaves) or nodes (which can have children).
 * The trait defines two abstract methods:
 * - `isEqual`: Checks whether the current tree is equal to another tree.
 * - `toString`: Converts the tree into its string representation in TL format.
 */
sealed trait Tree {
  def isEqual(other: Tree): Boolean
  def toString : String
}

/**
 * Case class `Identifier` represents a leaf node in the tree, containing a string value.
 * It implements:
 * - `isEqual`: Compares two `Identifier` trees based on their string value.
 * - `toString`: Returns the string value of the identifier.
 */
case class Identifier(value: String) extends Tree {
  override def isEqual(other: Tree): Boolean = other match {
    case Identifier(otherValue) => value == otherValue
    case _ => false
  }

  override def toString: String = value
}

/**
 * Case class `Node` represents an internal node of the tree, which can have child trees.
 * It contains a list of children that are also of type `Tree`.
 * It implements:
 * - `isEqual`: Compares two `Node` trees based on the size of their children lists and recursively checks if their respective children are equal.
 * - `toString`: Converts the node and its children into their TL representation, where children are separated by spaces and enclosed in parentheses.
 */
case class Node(children: List[Tree]) extends Tree {
  override def isEqual(other: Tree): Boolean = other match {
    case Node(otherChildren) =>
      children.size == otherChildren.size && children.zip(otherChildren).forall {
        case (child1, child2) => child1.isEqual(child2)
      }
    case _ => false
  }

  override def toString: String = {
    val tree_representation = children.map(_.toString).mkString(" ")
    s"($tree_representation)"
  }
}