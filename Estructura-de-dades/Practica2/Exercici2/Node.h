#ifndef Node_H
#define Node_H

template <class T>
class Node {
    public:
        Node();
        Node(const T& key);
        Node(const Node<T>& other);
        ~Node();
        const T& getElement() const;
        const Node<T>* getNext() const;
        void setNext(const Node<T>* key);

    private:
        T _element;
        Node<T>* _next;
};


template <class T>
Node<T>::Node() {
    this->_next = nullptr;
}

template <typename T>
Node<T>::Node(const T& key) {
    this->_element = key;
    this->_next = nullptr;
}

template <typename T>
Node<T>::Node(const Node<T>& other) {
    this->_element = other.getElement();
    this->_next = other.getNext();
}

template <typename T>
Node<T>::~Node() {

}

template <typename T>
const T& Node<T>::getElement() const {
    return this->_element;
}

template <typename T>
const Node<T>* Node<T>::getNext() const {
    return this->_next;
}

template <typename T>
void Node<T>::setNext(const Node<T>* key) {
    this->_next = key;
}

#endif