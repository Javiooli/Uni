#ifndef CuaEncadenada_H
#define CuaEncadenada_H

#include "Node.h"

using namespace std;

template <class T>
class CuaEncadenada {
    public:
        CuaEncadenada();
        CuaEncadenada(CuaEncadenada<T>& other);
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

// Constructor per defecte que crea un sentinella que es quedara sempre al _front.
template <class T>
CuaEncadenada<T>::CuaEncadenada() {
    Node<T>* centinella = new Node<T>();
    this->_front = centinella;
    this->_rear = centinella;
}

// Constructor copia.
template <class T>
CuaEncadenada<T>::CuaEncadenada(CuaEncadenada<T>& other) {
    this->_front = new Node<T>();
    Node<T>* temp = other.getFront();
    this->_front->setNext(temp);
    this->_rear = temp;
    while (temp != nullptr) {
        temp = temp->getNext();
        this->_rear->setNext(temp);
        this->_rear = temp;
    }
}

template <class T>
CuaEncadenada<T>::~CuaEncadenada() {

}

// Retorna si la cua esta buida mirant si despres del sentinella no hi ha cap element.
template <class T>
bool CuaEncadenada<T>::isEmpty() const {
    return (this->_front->getNext() == nullptr);
}

// Imprimeix el contingut de la cua.
template <class T>
void CuaEncadenada<T>::print() const {
    cout << '[';
    Node<T>* temp = this->_front->getNext();
    while (temp != nullptr) {
        cout << temp->getElement() << (temp->getNext() == nullptr ? "" : ", ");
        temp = temp->getNext();
    }
    cout << "]\n\n";
}

// Afegeix un element al final de la cua modificant el punter _next del que era l'ultim node de la cua i el _rear.
template <class T>
void CuaEncadenada<T>::enqueue(const T key) {
    Node<T>* elem = new Node(key);
    this->_rear->setNext(elem);
    this->_rear = elem;
    cout << "Element " << key << " agregat.\n\n";
}

// Elimina el primer element de la cua modificant el _next del sentinella.
template <class T>
void CuaEncadenada<T>::dequeue() {
    if (isEmpty()) throw out_of_range("EXCEPTION: La cua esta buida.");

    Node<T>* realFront = this->_front->getNext();

    cout << "Element " << realFront->getElement() << " eliminat.\n\n";

    this->_front->setNext(realFront->getNext());

    if (this->_front->getNext() == nullptr) {
        this->_rear = _front;
    }
}

// Retorna el primer element de la cua sense comptar el sentinella.
template <class T>
const T CuaEncadenada<T>::getFront() const {
    if (isEmpty()) throw out_of_range("EXCEPTION: La cua esta buida.");

    return this->_front->getNext()->getElement();
}


#endif