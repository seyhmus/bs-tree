#include <iostream>
#include <cstdlib>

#include "BT.cpp"

using namespace std;

int main() {
    
    int val[] = { 100, 110, 101, 120, 103, 118, 126, 102, 104, 116, 119, 123, 129, 117, 98 };
    int i;
    BT* bt = new BT;
    for(i=0;i<sizeof(val)/sizeof(int);i++) {
        bt->add(val[i], NULL);
    }
    
    bt->printNodes(NULL);
    
    bt->remove(101);
 
    cout << "-----" << endl;
    bt->printNodes(NULL);
    
    return 0;
}