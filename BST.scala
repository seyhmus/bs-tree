/**
 * Binary Search Tree Implementation
 * @author seyhmus inci
 */

class Node (val v : Int) {
  var value : Int = v
  var left : Node = null
  var right : Node = null
  var parent : Node = null

  override def toString(): String = {
    var str = "VALUE : " + value;
    if(left == null) {
      str += " LEFT : NONE"
    } else {
      str += " LEFT : " + left.value
    }
    if(right == null) {
      str += " RIGHT : NONE"
    } else {
      str += " RIGHT : " + right.value
    }
    if(parent == null) {
      str += " PARENT : NONE"
    } else {
      str += " PARENT : " + parent.value
    }
    return str
  }
}

class BT {
  var top : Node = null

  def add(value : Int, node : Node): Unit = {
    if(top == null) {
      top = new Node(value)
      return
    }

    if(node == null) {
      return add(value, top)
    }

    if(value == node.value) {
      return
    }
    if(value < node.value) {
      if(node.left == null) {
        node.left = new Node(value)
        node.left.parent = node
      } else {
        add(value, node.left)
      }
    } else {
      if(node.right == null) {
        node.right = new Node(value)
        node.right.parent = node
      } else {
        add(value, node.right)
      }
    }
  }

  def remove(value : Int) : Unit = {
    val node = find(value,null)
    if(node == null) {
      return
    }
    if(node.right != null) {
      val minVal = findMin(node.right)
      val minNode = find(minVal, node.right)
      node.value = minVal
      if(node.right == minNode) {
        node.right = minNode.right
      } else {
        minNode.parent.left = minNode.right
      }
      if(minNode.right != null) {
        minNode.right.parent = minNode.parent
      }
    } else if(node.left != null) {
      if(node.parent != null) {
        if(node.parent.value > node.value) {
          node.parent.left = node.left
        } else {
          node.parent.right = node.left
        }
        node.left.parent = node.parent
      } else {
        top = node.left
        top.parent = null
      }
    } else {
      if(node.parent != null) {
        if(node.parent.value > node.value) {
          node.parent.left = null
        } else {
          node.parent.right = null
        }
      } else{
        top = null
      }
    }
  }

  def printNodes(node: Node): Unit = {
    if(top == null) {
      return
    }

    if(node == null) {
      return printNodes(top)
    }

    println(node)
    if(node.left != null) {
      printNodes(node.left)
    }
    if(node.right != null) {
      printNodes(node.right)
    }
  }

  private def findMin(node : Node) : Int = {
    if(node == null) {
      throw new RuntimeException("node can not be null")
    }
    if(node.left == null) {
      return node.value
    } else {
      return findMin(node.left)
    }
  }

  private def find(value : Int, node : Node): Node = {
    if(node == null) {
      return find(value, top)
    }

    if(value == node.value) {
      return node
    }
    else if(value < node.value) {
      if(node.left == null) {
        return null;
      }
      return find(value, node.left)
    }
    else {
      if(node.right == null) {
        return null;
      }
      return find(value, node.right)
    }
  }

}

object BST {

  def main (args: Array[String]) {
    val values = Array(100, 110, 101, 120, 103, 118, 126, 102, 104, 116, 119, 123, 129, 117, 98)
    val bt = new BT
    for (v <- values) {
      bt.add(v, null)
    }
    bt.printNodes(null)
    println("-----")
    bt.remove(101)
    bt.printNodes(null)
  }

}
