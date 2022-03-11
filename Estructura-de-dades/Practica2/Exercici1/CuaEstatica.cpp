#include "CuaEstatica.h"
#include <iostream>
#include <vector>
#include <stdexcept>

using namespace std;

CuaEstatica::CuaEstatica(const int max_size) {
    _data = new int[max_size];
    _max_size = max_size;
    _size = 0;
    _front = 0;
    _rear = 0;
    cout << "Estructura creada" << "\n\n";
}

CuaEstatica::~CuaEstatica() {
    delete [] _data;
}

void CuaEstatica::enqueue(const int key) {
    if (isFull()) throw std::out_of_range("EXCEPTION: L'estructura esta plena.");
    if (!isEmpty()) _front = ++_front % _max_size;
    _data[_front] = key;
    _size++;
    cout << "Element " << _data[_front] << " agregat" << "\n\n";
}

void CuaEstatica::dequeue() {
    if (isEmpty()) throw std::out_of_range("EXCEPTION: L'estructura esta buida");
    cout << "Element " << _data[_rear] << " eliminat" << "\n\n";
    _rear = ++_rear % _max_size;
    _size--;
}

bool CuaEstatica::isFull() {
    if (_size < _max_size) return false;
    else return true;
}

bool CuaEstatica::isEmpty() {
    if (_size != 0) return false;
    else return true;
}

void CuaEstatica::print() {
    if (isEmpty()) throw std::out_of_range("EXCEPTION: L'estructura esta buida");
    cout << '[';
    for (int i = 0; i < _size; i++) {
        cout << _data[(i + _rear) % _max_size] << (i != _size - 1 ? ", " : "]\n\n");
    }
}

int CuaEstatica::getFront() {
    if (isEmpty()) throw std::out_of_range("EXCEPTION: L'estructura esta buida");
    return _data[_front];
}

void CuaEstatica::printFrontRear() {
    if (isEmpty()) throw std::out_of_range("EXCEPTION: L'estructura esta buida");
    cout << "Front: " << _front << ", Rear: " << _rear << "\n\n";
}
