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
        Node<T>* getNext() const;
        void setNext(Node<T>* key);

    private:
        T _element;
        Node<T>* _next;
};


// Constructor per defecte que deixa el _element sense definir i el _next en nullptr,
// utilitzat per crear el sentinella a la CuaEncadenada.
template <class T>
Node<T>::Node() {
    this->_next = nullptr;
}

// Constructor estandard pels nodes que continguin elements.
template <typename T>
Node<T>::Node(const T& key) {
    this->_element = key;
    this->_next = nullptr;
}

// Constructor copia.
template <typename T>
Node<T>::Node(const Node<T>& other) {
    this->_element = other.getElement();
    this->_next = other.getNext();
}

// Destructor.
template <typename T>
Node<T>::~Node() {
    if (this->_next != nullptr)
        delete this->_next;

    delete _element;
}

// Retorna _element.
template <typename T>
const T& Node<T>::getElement() const {
    return this->_element;
}

// Retorna el node que va darrer a aquest en la cua (_next).
template <typename T>
Node<T>* Node<T>::getNext() const {
    return this->_next;
}

// Modifica el punter que apunta al node que va darrer a aquest en la cua (_next).
template <typename T>
void Node<T>::setNext(Node<T>* key) {
    this->_next = key;
}

#endif