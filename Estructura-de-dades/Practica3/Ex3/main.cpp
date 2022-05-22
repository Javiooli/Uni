//@Author: Javier Pedragosa
#include <iostream>
#include "BSTArbre.h"
#include "BSTNode.h"
#include "AVLArbre.h"
#include <list>
using namespace std;

int main(){
    AVLArbre<int, int>* tree1 = new AVLArbre<int, int>();
    int testKeys[] = {2, 0, 8, 45, 76, 5, 3, 40};
    int testValues[] = {5, 5, 1, 88, 99, 12, 9, 11};

    for (int i = 0; i < 8; i++) {
        int key = tree1->insert(testKeys[i], testValues[i])->getKey();
        cout << "Element " << key << " insertat amb valor " << tree1->valuesOf(key).front() << "." << endl;
    }

    cout << endl;

    tree1->printPreorder();
    tree1->printInorder();
    tree1->printPostorder();
    
    cout << endl;

    tree1->printSecondLargestKey();

    cout << endl;

    AVLArbre<int, int>* tree2 = new AVLArbre<int, int>(*tree1);
    tree1->mirrorTree();
    tree1->printInorder();

    cout << endl;

    delete tree1;

    return 0;
}