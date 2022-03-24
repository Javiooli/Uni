#ifndef CuaEncadenada_H
#define CuaEncadenada_H

#include "Node.h"

template <class T>
class CuaEncadenada {
    public:
        CuaEncadenada();
        CuaEncadenada(const CuaEncadenada<T>& other);
        ~CuaEncadenada();
        bool isEmpty() const;
        void print() const;
        void enqueue(const T key);
        void dequeue();
        const T getFront() const;

    private:
        Node<T>* _front;
        Node<T>* _rear;
}; //CuaEncadenada

template <class T>
CuaEncadenada<T>::CuaEncadenada() {
    Node<T> centinella = new Node<T>();
    this->_front = *centinella;
    this->_rear = *centinella;
}

template <class T>
CuaEncadenada<T>::CuaEncadenada(const CuaEncadenada<T>& other) {
    this->_front = other.getFront();
    while (!other.isEmpty()) {
        this->_rear->setNext(other.getFront());
        this->_rear = other.getFront();
        other.dequeue();
    }
}

template <class T>
CuaEncadenada<T>::~CuaEncadenada() {

}

template <class T>
bool CuaEncadenada<T>::isEmpty() const {

}



#endif