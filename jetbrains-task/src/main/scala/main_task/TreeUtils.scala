package main_task

import scala.collection.mutable

/**
 * Utility object that contains tree manipulation functions.
 */
object TreeUtils {
  /**
   * Replaces all occurrences of a specified subtree in the given tree with a replacement subtree.
   *
   * @param tree          The original tree where we want to perform the replacement.
   * @param searchTree    The subtree we want to search for and replace. [[TreeUtils.findAll]]
   * @param replacement   The subtree that will replace all occurrences of searchTree.
   * @return              A new tree where all occurrences of searchTree have been replaced by replacement.
   */
    def replace(tree: Tree, searchTree: Tree, replacement: Tree): Tree = {
      if(tree.isEqual(searchTree)) {
        replacement
      } else {
        tree match {
          case Identifier(_) => tree

          case Node(children) =>
            val newChildren = children.map(child => replace(child, searchTree, replacement))
            Node(newChildren)
        }
      }
    }

    def matches(tree: Tree, patternTree:Tree) : Boolean = {
      if(tree.isEqual(patternTree)) {
        true
      }else if(patternTree.isInstanceOf[Placeholder]) {
        true
      }else {
        (tree, patternTree) match {
          case (Node(children), Node(otherChildren)) =>
            children.size == otherChildren.size && children.zip(otherChildren).forall {
              case (child1, child2) => matches(child1,child2)
            }
          case _ => false
        }
      }
    }

    def findAll(tree: Tree, patternTree: Tree, result: mutable.ListBuffer[Tree]) : Unit = {
      if(matches(tree,patternTree)) {
        result.addOne(tree)
      } else {
        tree match {
          case Node(children) =>
            children.foreach {
              case child => findAll(child, patternTree, result)
            }
        }
      }
    }
}
