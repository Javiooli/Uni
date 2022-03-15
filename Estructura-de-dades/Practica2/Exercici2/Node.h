#ifndef Node_H
#define Node_H

template <typename T>
class Node {
    public:
        Node();
        getElement();
        getNext();
        setNext();

    private:
        T element;
        T* next;
};

#endif