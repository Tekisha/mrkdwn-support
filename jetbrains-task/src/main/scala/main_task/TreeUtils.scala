package main_task

object TreeUtils {
    def replace(tree: Tree, searchTree: Tree, replacement: Tree): Tree = {
      println("tree:"+tree)
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
