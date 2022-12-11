// Binary Tree implemntation in C++
// @author: seyhmus inci

#include <iostream>
#include <cstdlib>
#include <string>

#include "BT.h"

using namespace std;

BT::BT() {
    top = NULL;
}

BT::BT(int value) {
    top = initNode(value);
}

BT::Node* BT::initNode(int value) {
    Node* node = new Node;
    node->value = value;
    node->left = NULL;
    node->right = NULL;
    node->parent = NULL;
    return node;
}

void BT::toString(Node* node) {
    cout << "VALUE : " << node->value;
    if (node->left) {
        cout << " LEFT : " << node->left->value;
    } else {
        cout << " LEFT : NONE";
    }
    if (node->right) {
        cout << " RIGHT : " << node->right->value;
    } else {
        cout << " RIGHT : NONE";
    }
    if (node->parent) {
        cout << " PARENT : " << node->parent->value;
    } else {
        cout << " PARENT : NONE";
    }
    cout << endl;
}

void BT::add(int value, Node* node) {
    if (!top) {
        top = initNode(value);
        return;
    }
    if (!node) {
        node = top;
    }
    if (value == node->value) {
        return;
    }
    if (value < node->value) {
        if (node->left) {
            add(value, node->left);
        } else {
            node->left = initNode(value);
            node->left->parent = node;
        }
    } else {
        if (node->right) {
            add(value, node->right);
        } else {
            node->right = initNode(value);
            node->right->parent = node;
        }
    }
}

void BT::remove(int value) {
    Node* node = find(value, top);
    if (!node) {
        return;
    }
    if (node->right) {
        int minVal = findMin(node->right);
        Node* minNode = find(minVal, node->right);
        node->value = minVal;
        if (node->right == minNode) {
            node->right = minNode->right;
        } else {
            minNode->parent->left = minNode->right;
        }
        if (minNode->right) {
            minNode->right->parent = minNode->parent;
        }
    } else if (node->left) {
        if (node->parent) {
            if (node->parent->value > node->value) {
                node->parent->left = node->left;
                node->left->parent = node->parent;
            } else {
                node->parent->right = node->left;
                node->left->parent = node->parent;
            }
        } else {
            top = node->left;
            top->parent = NULL;
        }
    } else {
        if (node->parent) {
            if (node->parent->value > node->value) {
                node->parent->left = NULL;
            } else {
                node->parent->right = NULL;
            }
        } else {
            top = NULL;
        }
    }
}

BT::Node* BT::find(int value, Node* node) {
    if (!top) {
        return NULL;
    }
    if (!node) {
        node = top;
    }
    if (value == node->value) {
        return node;
    } else if (value < node->value) {
        if (node->left) {
            return find(value, node->left);
        } else {
            return NULL;
        }
    } else {
        if (node->right) {
            return find(value, node->right);
        } else {
            return NULL;
        }
    }
}

int BT::findMin(Node* node) {
    if (!node) {
        throw "Node reference to null";
    }
    if (node->left) {
        return findMin(node->left);
    } else {
        return node->value;
    }
}

void BT::printNodes(Node* node) {
    if (!top) {
        return;
    }
    if (!node) {
        node = top;
    }
    toString(node);
    if (node->left) {
        printNodes(node->left);
    }
    if (node->right) {
        printNodes(node->right);
    }

}