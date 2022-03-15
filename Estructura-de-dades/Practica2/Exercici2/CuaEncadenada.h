#ifndef CuaEncadenada_H
#define CuaEncadenada_H

#include "Node.h"

template <typename T>
class CuaEncadenada {
    public:
        CuaEncadenada();
        CuaEncadenada(const CuaEncadenada<T>& q);
        ~CuaEncadenada();
        bool isEmpty();
        void print();
        void enqueue(const T key);
        void dequeue();
        const T getFront();

    private:
        Node<T>* _front;
        Node<T>* _rear;
}; //CuaEncadenada

#endif