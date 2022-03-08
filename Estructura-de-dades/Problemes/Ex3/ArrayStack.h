//@Author: Javier Pedragosa
#ifndef ARRAYSTACK_H
#define ARRAYSTACK_H
#include <stdexcept>
#include <iostream>

using namespace std;

template <class T>
class ArrayStack {
    private:
        T* array;
        int _top;
        int _maxSize;
    
    public:
        ArrayStack(int maxSize = 100);
        ArrayStack(const ArrayStack& other);
        ~ArrayStack();

        int maxSize() const { return _maxSize; };
        int size() const { return _top + 1; };
        bool empty() const { return _top == -1; };
        bool full() const  { return size() == _maxSize; };

        const T& top() const;
        void push(const T& element);
        void pop();
        
        void print() const;
};

template <class T>
ArrayStack<T>::ArrayStack(int maxSize) {
    array = new T[maxSize];
    _maxSize = maxSize;
    _top = -1;
}

template<class T>
ArrayStack<T>::ArrayStack(const ArrayStack& other) {
    array = new T[other._maxSize];

    for (int i = 0; i < other.size(); i++) {
        array[i] = other.array[i];
    }

    _maxSize = other._maxSize;
    _top = other._top;
}

template<class T>
ArrayStack<T>::~ArrayStack() {
    delete [] array;
}

template<class T>
const T& ArrayStack<T>::top() const {
    if (empty())
        throw out_of_range("Empty stack.");

    return array[_top];
}

template<class T>
void ArrayStack<T>::push(const T& element) {
    if (full())
        throw out_of_range("Full stack.");

    array[++_top] = element;
}

template<class T>
void ArrayStack<T>::pop() {
    if (empty())
        throw out_of_range("Empty Stack.");

    --_top;
}

template<class T>
void ArrayStack<T>::print() const {
    cout << "[";
    for (int i = 0; i < size();  i++) {
        cout << array[i] << (i < _top ? ", " : "");
    }
    cout << "]" << endl;
}


#endif