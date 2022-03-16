#ifndef LINKEDSTACK_H
#define LINKEDSTACK_H

#include "Node.h"
#include <stdexcept>

template <class T>
class LinkedStack {
    private:
        Node<T>* _top;
        int _size;
    public:
        LinkedStack() {
            _top = nullptr;
            _size = 0;
        }
        LinkedStack(const LinkedStack<T>& other) {
            Node<T>* node = other._top, *prev = nullptr;
            _top = nullptr;

            while (node != nullptr) {
                Node <T>* newNode = new Node<T>(*node);

                if (_top == nullptr)
                    _top = newNode;
                else
                    prev->setNext(newNode);

                prev = newNode;
                node = node->next();
            }
            _size = other._size;
        }
        ~LinkedStack() {
            Node<T>* node = _top;

            while (node != nullptr) {
                Node<T>* next = node->next();
                delete node;
                node = next;
            }
        }

        int size() const { return _size; }
        bool empty() const { return _size == 0 }

        void push(const T& element) {
            Node<T>* node = new Node<T>(element);
            node->setNext(_top);
            _top = node;
            _size++;
        }
        void pop() {
            if (empty()) {
                throw std::out_of_range("Empty stack.");
            } else {
                Node <T>* next = _top->next()
                delete _top;
                _top = next;
                size--;
            }
        }
        const T& top() const;

        void print() const;


};

#endif