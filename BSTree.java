/**
 * Binary Search Tree Implementation
 * @author seyhmus
 */

public class BSTree {
    
    public static void main(String[] args) {
        int val[] = {100, 110, 101, 120, 103, 118, 126, 102, 104, 116, 119, 123, 129, 117, 98};
        BSTree bt = new BSTree();
        for(int v : val) {
            bt.add(v, null);
        }
        bt.printNodes(null);
        bt.remove(101);
        System.out.println("-----");
        bt.printNodes(null);
    }


    public static class Node {

        int value;
        Node left, right, parent;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            String out = "VALUE : " + value;
            if (left != null) {
                out += " LEFT : " + left.value;
            } else {
                out += " LEFT : NONE";
            }
            if (right != null) {
                out += " RIGHT : " + right.value;
            } else {
                out += " RIGHT : NONE";
            }
            if (parent != null) {
                out += " PARENT : " + parent.value;
            } else {
                out += " PARENT : NONE";
            }
            return out;
        }
    }

    Node top;
    
    public BSTree() {
        top = null;
    }
    
    public BSTree(int value) {
        top = new Node(value);
    }

    public void add(int value, Node node) {
        if (top == null) {
            top = new Node(value);
            return;
        }
        if(node == null) {
            node = top;
        }
        if (value == node.value) {
            return;
        }
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left.parent = node;
            } else {
                add(value, node.left);
            }
        }
        if (value > node.value) {
            if (node.right == null) {
                node.right = new Node(value);
                node.right.parent = node;
            } else {
                add(value, node.right);
            }
        }
    }

    public void remove(int value) {
        Node node = find(value, null);
        if (node == null) {
            return;
        }
        if (node.right != null) {
            int minVal = findMin(node.right);
            Node minNode = find(minVal, node.right);
            node.value = minVal;
            if (node.right == minNode) {
                node.right = minNode.right;
            } else {
                minNode.parent.left = minNode.right;
            }
            if (minNode.right != null) {
                minNode.right.parent = minNode.parent;
            }
        } else if (node.left != null) {
            if (node.parent != null) {
                if (node.parent.value > node.value) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
                node.left.parent = node.parent;
            } else {
                top = node.left;
                top.parent = null;
            }
        } else {
            if (node.parent != null) {
                if (node.parent.value > node.value) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            } else {
                top = null;
            }
        }
    }

    private int findMin(Node node) {
        if (top == null) {
            throw new RuntimeException("Tree is empty");
        }
        if (node == null) {
            throw new RuntimeException("Null reference to node");
        }
        if (node.left != null) {
            return findMin(node.left);
        }
        return node.value;
    }

    private Node find(int value, Node node) {
        if (top == null) {
            return null;
        }
        if (node == null) {
            node = top;
        }
        if (value == node.value) {
            return node;
        } else if (value < node.value) {
            if (node.left == null) {
                return null;
            } else {
                return find(value, node.left);
            }
        } else {
            if (node.right == null) {
                return null;
            } else {
                return find(value, node.right);
            }
        }
    }

    public void printNodes(Node node) {
        if(top == null) {
            return;
        }
        if(node == null) {
            node = top;
        }
        System.out.println(node);
        if(node.left != null) {
            printNodes(node.left);
        }
        if(node.right != null) {
            printNodes(node.right);
        }
    }
}
