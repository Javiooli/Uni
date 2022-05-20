//@Author: Javier Pedragosa
#include <iostream>
#include "BSTNode.h"
#include <list>

#ifndef BSTArbre_H
#define BSTArbre_H

template <class K, class V>
class BSTArbre {
    public:
        BSTArbre();
        BSTArbre(const BSTArbre<K, V>& orig);
        virtual ~BSTArbre();
        bool empty() const;
        int size() const;
        int height() const;
        BSTNode<K, V>* insert(const K& k, const V& v);
        const list<V> valuesOf(const K& key) const;
        void printPreorder() const;
        void printInorder() const;
        void printPostorder() const;
        const list<BSTNode<K, V>> getLeafNodes() const;
        const list<BSTNode<K, V>> getLeafNodesAux(const BSTNode<K, V>* node, list<BSTNode<K, V>>& llista) const;
        void printSecondLargestKey() const;
        const BSTArbre<K, V> mirrorTree() const;
    protected:
        BSTNode<K, V>* root;
        BSTNode<K, V>* search(const K& key) const;
    private:
        int _size;
};//BSTArbre

template <class K, class V>
BSTArbre<K, V>::BSTArbre() {
    this->root = nullptr;
    this->_size = 0;
}

template <class K, class V>
BSTArbre<K, V>::BSTArbre(const BSTArbre<K, V>& orig) {
    this->root = orig.root;
    this->_size = orig._size;
}

template <class K, class V>
BSTArbre<K, V>::~BSTArbre() {

}

template <class K, class V>
bool BSTArbre<K, V>::empty() const {
    return (this->root == nullptr);
}

template <class K, class V>
int BSTArbre<K, V>::size() const {
    return this->_size;
}

//TODO: height()
template <class K, class V>
int BSTArbre<K, V>::height() const {

}

template <class K, class V>
BSTNode<K, V>* BSTArbre<K, V>::insert(const K& k, const V& v) {
    bool nodeSet = false;
    if (empty()) {
        this->root = new BSTNode<K, V>(k);
        this->root->insertValue(v);
    } else {
        BSTNode<K, V>* ptr = root;
        BSTNode<K, V>* node = new BSTNode<K, V>(k);
        node->insertValue(v);
        while (!nodeSet) {
            //Si ptr te dos fills, escollir esquerre si la seva key es major que la del parametre, dreta si no
            if (ptr->hasLeft() && ptr->hasRight()) {
                if (ptr->getLeft()->getKey() > node->getKey()) ptr = ptr->getLeft();
                else ptr = ptr->getRight();

            // Si nomes en te un
            } else {
                //Si te esquerre i la seva key es major que la del parametre, ptr passa a ser l'esquerre
                if (ptr->hasLeft() && ptr->getLeft()->getKey() > node->getKey()) {
                    ptr = ptr->getLeft();
                
                //Si te esquerre pero es menor o igual: si te dret anem a la dreta i si no posem el del parametre com a fill dret
                } else if (ptr->hasLeft() && ptr->getLeft()->getKey() <= node->getKey()) {
                    if (ptr->hasRight()) ptr = ptr->getRight();
                    else {
                        node->setParent(ptr);
                        ptr->setRight(node);
                        nodeSet = true;
                    }
                //Aqui sabem que no te esquerre, nomes dret o no en te fills.
                //Si la key de ptr es menor que la del node del parametre ens centrem en la branca dreta de ptr
                } else if (ptr->getKey() <= node->getKey()) {
                    //Si ptr te fill dret, ptr apunta a aquest fill, si no, el node del parametre es converteix en el fill dret
                    if (ptr->hasRight()) ptr = ptr->getRight();
                    else {
                        node->setParent(ptr);
                        ptr->setRight(node);
                        nodeSet = true;
                    }
                //Ultimament, si res de l'anterior es compleix, podem fer el node del parametre el fill esquerre de ptr
                } else {
                    node->setParent(ptr);
                    ptr->setLeft(node);
                    nodeSet = true;
                }
            }
        }
    }
}

template <class K, class V>
const list<V> BSTArbre<K, V>::valuesOf(const K& key) const {
    return search(key)->getValues();
}

//TODO: printPreorder()
template <class K, class V>
void BSTArbre<K, V>::printPreorder() const {

}

//TODO: printInorder()
template <class K, class V>
void BSTArbre<K, V>::printInorder() const {

}

//TODO: printPostorder()
template <class K, class V>
void BSTArbre<K, V>::printPostorder() const {

}

template <class K, class V>
const list<BSTNode<K, V>> BSTArbre<K, V>::getLeafNodes() const {
    list<BSTNode<K, V>> llista = new list<BSTNode<K, V>>;
    return getLeafNodesAux(root, llista);
}

template <class K, class V>
const list<BSTNode<K, V>> BSTArbre<K, V>::getLeafNodesAux(const BSTNode<K, V>* node, list<BSTNode<K, V>>& llista) const {
    if (node == nullptr) return llista;
     
    if (!node->hasLeft() && !node->hasRight()) {
        llista.insert(node);
        return llista;
    }
 
    if (node->hasLeft()) getLeafNodesAux(node->getLeft(), llista);
         
    if (node->hasRight()) getLeafNodesAux(node->getRight(), llista);
}


template <class K, class V>
void BSTArbre<K, V>::printSecondLargestKey() const {

}

template <class K, class V>
const BSTArbre<K, V> BSTArbre<K, V>::mirrorTree() const {

}

template <class K, class V>
BSTNode<K, V>* BSTArbre<K, V>::search(const K& key) const {
    BSTNode<K, V>* ptr = root;
    while (ptr != nullptr) {
        if (ptr->key < key) ptr = ptr->getRight();
        else if (ptr->key > key) ptr = ptr->getLeft();
        else return ptr;
    }
    return nullptr;

}

#endif