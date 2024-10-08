package main_task

sealed trait Tree {
  def isEqual(other: Tree): Boolean
}

case class Identifier(value: String) extends Tree {
  override def isEqual(other: Tree): Boolean = other match {
    case Identifier(otherValue) => value == otherValue
    case _ => false
  }
}

case class Node(children: List[Tree]) extends Tree {
  override def isEqual(other: Tree): Boolean = other match {
    case Node(otherChildren) =>
      children.size == otherChildren.size && children.zip(otherChildren).forall {
        case (child1, child2) => child1.isEqual(child2)
      }
    case _ => false
  }
}