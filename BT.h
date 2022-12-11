class BT {
private:

    struct Node {
        int value;
        Node* left;
        Node* right;
        Node* parent;
    };

    Node* top;

    Node* initNode(int);
    void toString(Node*);
    int findMin(Node*);
    Node* find(int,Node*);
public:
    BT();
    BT(int);
    void add(int,Node*);
    void remove(int);
    void printNodes(Node*);
};