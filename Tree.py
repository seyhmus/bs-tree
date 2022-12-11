# Binary Tree Implementation in Python
# @athor: seyhmus inci

class Node:
    def __init__(self, value, left=None, right=None, parent=None):
        self.value = value
        self.left = left
        self.right = right
        self.parent = parent

    def __str__(self):
        out = "VALUE : " + str(self.value)
        if self.left != None:
            out += " LEFT : " + str(self.left.value)
        else:
            out += " LEFT : NONE"
        if self.right != None:
            out += " RIGHT : " + str(self.right.value)
        else:
            out += " RIGHT : NONE"
        if self.parent != None:
            out += " PARENT : " + str(self.parent.value)
        else:
            out += " PARENT : NONE"
        return out

class BTree:
    def __init__(self,value=None):
        if value != None:
            node = Node(value)
            self.top = node
        else:
            self.top = None

    def add(self, value, node=None):
        if self.top == None:
            self.top = Node(value)
            return
        if node == None:
            node = self.top
        if node.value == value:
            return
        if value < node.value:
            if node.left == None:
                node.left = Node(value)
                node.left.parent = node
            else:
                self.add(value,node.left)
        else:
            if node.right == None:
                node.right = Node(value)
                node.right.parent = node
            else:
                self.add(value,node.right)

    def remove(self, value):
        node = self.find(value)
        if node == None:
            return
        if node.right != None:
            minVal = self.findMin(node.right)
            minNode = self.find(minVal, node.right)
            node.value = minVal
            if node.right == minNode:
                node.right = minNode.right
            else:
                minNode.parent.left = minNode.right
            if minNode.right != None:
                minNode.right.parent = minNode.parent
        elif node.left != None:
            if node.parent != null:
                if node.parent.value > node.value:
                    node.parent.left = node.left
                else:
                    node.parent.right = node.left
                node.left.parent = node.parent
            else:
                self.top = node.left
                self.top.parent = None
        else:
            if node.parent != None:
                if node.parent.value > node.value:
                    node.parent.left = None
                else:
                    node.parent.right = None
            else:
                self.top = None

    def find(self, value, node=None):
        if self.top == None:
            return None
        if node == None:
            node = self.top
        if value == node.value:
            return node
        elif value < node.value:
            if node.left == None:
                return None;
            else:
                return self.find(value, node.left)
        else:
            if node.left == None:
                return None;
            else:
                return self.find(value, node.right)

    def findMin(self, node = None):
        if self.top == None:
            raise "Null reference"
        if node.left == None:
            return node.value
        else:
            return self.findMin(node.left)

    def lowToHigh(self, node = None):
        if self.top == None:
            return
        if node == None:
            node = self.top
        if node.left != None:
            self.lowToHigh(node.left)
        print node
        if node.right != None:
            self.lowToHigh(node.right)

    def highToLow(self, node = None):
        if self.top == None:
            return
        if node == None:
            node = self.top
        if node.right != None:
            self.highToLow(node.right)
        print node
        if node.left != None:
            self.highToLow(node.left)

    def printNodes(self, node=None):
        if self.top == None:
            return
        if node == None:
            node = self.top		
        print node
        if node.left != None:
            self.printNodes(node.left)
        if node.right != None:
            self.printNodes(node.right)

t = BTree(100)
squares = [(-2)**x for x in range(10)]
for i in squares:
	t.add(100+i)
	
v = [100, 110, 101, 120, 103, 118, 126, 102, 104, 116, 119, 123, 129, 117, 98]
bt = BTree()
for i in v:
	bt.add(i)

bt.printNodes()
bt.remove(101)
print "-----"
bt.printNodes()

