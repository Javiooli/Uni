//@Author: Javier Pedragosa
#include <iostream>
#include "BSTArbre.h"
#include "BSTNode.h"
#include <list>
using namespace std;

int main(){
    BSTArbre<int, int> tree1 = BSTArbre<int, int>();
    int testKeys[] = {2, 0, 8, 45, 76, 5, 3};
    int testValues[] = {5, 5, 1, 88, 99, 12, 9, 11};
    for (int i = 0; i < 7; i++) {
        cout << tree1.insert(testKeys[i], testValues[i])->getKey();
    }
    return 0;
}