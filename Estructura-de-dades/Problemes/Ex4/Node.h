#ifndef NODE_H
#define NODE_H

template <class T>
class Node {
    private:
        T _element;
        Node<T>* _next;
    public:
        Node(const T& element) {
            _element = element;
            _next = nullptr;
        }
        Node(const Node<T>& other) {
            _element = other._element;
            _next = nullptr;
        }
        ~Node() {}

        const T& element() const { return _element; }
        Node<T>* next() const { return _next; }
        void setNext(Node<T>* next);
};

#endif