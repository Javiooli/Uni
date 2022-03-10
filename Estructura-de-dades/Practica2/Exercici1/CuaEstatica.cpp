#include "CuaEstatica.h"
#include <iostream>
#include <vector>

using namespace std;

/*CuaEstatica(const int max_size);
            virtual ~CuaEstatica();
            void enqueue(const int key);
            void dequeue();
            bool isFull();
            bool isEmpty();
            void print();
            const int getFront();

            void printFrontRear();*/

CuaEstatica::CuaEstatica(const int max_size) {
    _data = new int[max_size];
    _max_size = max_size;
    _size = 0;
    _front = 0;
    _rear = 0;
}

CuaEstatica::~CuaEstatica() {
    delete [] _data;
}

void CuaEstatica::enqueue(const int key) {
    if (isFull()) return;
    _rear = _rear++ % _max_size;
    _data[_rear] = key;
}