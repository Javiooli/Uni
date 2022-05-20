//@Author: Javier Pedragosa

#ifndef BSTNode_H
#define BSTNode_H

#include <list>

using namespace std;

template <class K, class V>
class BSTNode {
    public:
        BSTNode(const K& key);
        BSTNode(BSTNode<K, V>& orig);
        virtual ~BSTNode();

        //Modificadors
        const K& getKey() const;
        const list<V>& getValues() const;
        BSTNode<K, V>* getLeft() const;
        BSTNode<K, V>* getRight() const;
        BSTNode<K, V>* getParent() const;
        void setLeft(BSTNode<K, V>* node);
        void setRight(BSTNode<K, V>* node);
        void setParent(BSTNode<K, V>* node);
        //TODO: Getters

        //Operacions
        bool isRoot() const;
        bool hasLeft() const;
        bool hasRight() const;
        bool isExternal() const;

        void insertValue(const V& v);
        int depth() const;
        int height() const;
        //bool operator==(const BSTNode<K,V>& node) const;

        private:
            K key;
            list<V, allocator<V>> values;
            BSTNode<K, V>* left;
            BSTNode<K, V>* right;
            BSTNode<K, V>* parent;
            //TODO: Atributs que falten
};//BSTNode

template <class K, class V>
BSTNode<K, V>::BSTNode(const K& key) {
    this->key = key;
    this->left = nullptr;
    this->right = nullptr;
    this->parent = nullptr;
    this->values = {};
}

template <class K, class V>
BSTNode<K, V>::BSTNode(BSTNode<K, V>& orig) {
    this->key = orig.key;
    this->values = orig.values;
    this->left = orig.left;
    this->right = orig.right;
    this->parent = orig.parent;
}

//TODO: Destructor
template <class K, class V>
BSTNode<K, V>::~BSTNode() {

}

template <class K, class V>
const K& BSTNode<K, V>::getKey() const {
    return this->key;
}

template <class K, class V>
const list<V>& BSTNode<K, V>::getValues() const {
    return this->values;
}

template <class K, class V>
BSTNode<K, V>* BSTNode<K, V>::getLeft() const {
    return this->left;
}

template <class K, class V>
BSTNode<K, V>* BSTNode<K, V>::getRight() const {
    return this->right;
}

template <class K, class V>
BSTNode<K, V>* BSTNode<K, V>::getParent() const {
    return this->parent;
}

template <class K, class V>
void BSTNode<K, V>::setLeft(BSTNode<K, V>* node) {
    this->left = node;
}

template <class K, class V>
void BSTNode<K, V>::setRight(BSTNode<K, V>* node) {
    this->right = node;
}

template <class K, class V>
void BSTNode<K, V>::setParent(BSTNode<K, V>* node) {
    this->parent = node;
}

template <class K, class V>
bool BSTNode<K, V>::isRoot() const {
    return (this->parent == nullptr);
}

template <class K, class V>
bool BSTNode<K, V>::hasLeft() const {
    return (this->left != nullptr);
}

template <class K, class V>
bool BSTNode<K, V>::hasRight() const {
    return (this->right != nullptr);
}

//TODO: isExternal()
template <class K, class V>
bool BSTNode<K, V>::isExternal() const {

}

template <class K, class V>
void BSTNode<K, V>::insertValue(const V& v) {
    this->values.push_back(v);
}

//TODO: depth()
template <class K, class V>
int BSTNode<K, V>::depth() const {

}

//TODO: height()
template <class K, class V>
int BSTNode<K, V>::height() const {

}

//TODO: operator==()
//template <class K, class V>
//bool BSTNode<K, V>::operator==(const BSTNode<K, V>& node) const {

//}





#endif