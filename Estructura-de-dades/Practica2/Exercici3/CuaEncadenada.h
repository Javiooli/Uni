#ifndef CuaEncadenada_H
#define CuaEncadenada_H

#include "Node.h"
#include "Comanda.h"
#include <iomanip>

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

// Imprimeix el contingut de la cua en forma de taula.
template <class T>
void CuaEncadenada<T>::print() const {
    char separator = ' ';
    cout << endl;
    string line, sbstr, taula, plat, quantitat, hora;
    int numAtribut = 0;
    Node<T>* temp = this->_front->getNext();
    cout << left << setw(10) << setfill(separator) << "Taula";
    cout << left << setw(30) << setfill(separator) << "Plat";
    cout << left << setw(15) << setfill(separator) << "Quant.";
    cout << left << setw(5)  << setfill(separator) << "Hora";
    cout << endl;
    cout << left << setw(65) << setfill('-') << "";
    cout << endl;

    while (temp != nullptr) {

        line = temp->getElement().toString();

        for (char c : line) {
            if (c == ',') {
                switch (numAtribut) {
                    case 0:
                        taula = sbstr.substr(2);
                        break;
                    case 1:
                        plat = sbstr;
                        break;
                    case 2:
                        quantitat = sbstr;
                        break;
                }
                sbstr = "";
                numAtribut++;

            } else {
                
                if (numAtribut != 3)
                    sbstr += c;
                else
                    hora += c;

            }
        }

        cout << left << setw(5)  << setfill(separator) << taula;
        cout << left << setw(5)  << setfill(separator) << "|";
        cout << left << setw(25) << setfill(separator) << plat;
        cout << left << setw(5)  << setfill(separator) << "|";
        cout << left << setw(10) << setfill(separator) << quantitat;
        cout << left << setw(5)  << setfill(separator) << "|";
        cout << left << setw(5)  << setfill(separator) << hora;
        cout << endl;

        hora = "";
        sbstr = "";
        numAtribut = 0;
        temp = temp->getNext();

    }
    cout << "\n\n";
}

// Afegeix un element al final de la cua modificant el punter _next del que era l'ultim node de la cua i el _rear.
template <class T>
void CuaEncadenada<T>::enqueue(const T key) {
    Node<T>* elem = new Node(key);
    this->_rear->setNext(elem);
    this->_rear = elem;
    cout << "Comanda afegida.\n\n";
}

// Elimina el primer element de la cua modificant el _next del sentinella.
template <class T>
void CuaEncadenada<T>::dequeue() {
    if (isEmpty()) throw out_of_range("EXCEPTION: La cua esta buida.");

    Node<T>* realFront = this->_front->getNext();

    cout << "Comanda eliminada.\n\n";

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