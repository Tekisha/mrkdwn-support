package main_task

/**
 * Utility object that contains tree manipulation functions.
 */
object TreeUtils {
  /**
   * Replaces all occurrences of a specified subtree in the given tree with a replacement subtree.
   *
   * @param tree          The original tree where we want to perform the replacement.
   * @param searchTree    The subtree we want to search for and replace.
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
}
