#ifndef AVLARBRE_HPP
#define AVLARBRE_HPP

#include "BSTArbre.h"

template<class K, class V>
class AVLArbre : public BSTArbre<K,V> {
    public:
        AVLArbre();
        AVLArbre(const AVLArbre<K, V>& orig);
        virtual ~AVLArbre();
        
        BSTNode<K,V>* add(const K& k, const V& value);
        void showKeysPreorder(const BSTNode<K,V>* n = nullptr) const;                                        // O(n) en el pitjor cas, O(log(n)) en casos normals
        void showKeysInorder(const BSTNode<K,V>* n = nullptr) const;                                          // Igual que al showKeysPreorder()
        void showKeysPostorder(const BSTNode<K,V>* n = nullptr) const;
        
    private:
         BSTNode<K,V>* RRrotation(BSTNode<K,V>* n) const; 
         BSTNode<K,V>* LLrotation(BSTNode<K,V>* n) const; 
         BSTNode<K,V>* LRrotation(BSTNode<K,V>* n) const; 
         BSTNode<K,V>* RLrotation(BSTNode<K,V>* n) const; 
};

template<class K, class V>
AVLArbre<K,V>::AVLArbre() {}

template<class K, class V>
AVLArbre<K,V>::AVLArbre(const AVLArbre<K, V>& orig) {}

template<class K, class V>
AVLArbre<K,V>::~AVLArbre() {}

template<class K, class V>
BSTNode<K,V>* AVLArbre<K,V>::add(const K& k, const V& value) {
    BSTNode<K,V>* node = BSTArbre<K,V>::add(k, value);
    if (node->hasLeft() && node->hasRight()) {
        if (node->getBalanceFactor() == 2 && node->getLeft()->getBalanceFactor() == 1) node = LLrotation(node);
        else if (node->getBalanceFactor() == -2 && node->getRight()->getBalanceFactor() == -1) node = RRrotation(node);
        else if (node->getBalanceFactor() == -2 && node->getLeft()->getBalanceFactor() == 1) node = RLrotation(node);
        else if (node->getBalanceFactor() == 2 && node->getLeft()->getBalanceFactor() == -1) node = LRrotation(node);
    }
    node->setHeight();
    cout << "Altura: " << node->getHeight() << endl;
    node->setBalanceFactor();
    cout << "BF: " << node->getBalanceFactor() << endl;
    return node;
}

template<class K, class V>
void AVLArbre<K,V>::showKeysPreorder(const BSTNode<K,V>* n) const {}
template<class K, class V>
void AVLArbre<K,V>::showKeysInorder(const BSTNode<K,V>* n) const {}
template<class K, class V>
void AVLArbre<K,V>::showKeysPostorder(const BSTNode<K,V>* n) const {}


template<class K, class V>
BSTNode<K,V>* AVLArbre<K,V>::LLrotation(BSTNode<K,V>* n) const {
    BSTNode<K,V>* aux;
    BSTNode<K,V>* aux2;
    
    aux = n;
    aux2 = aux->getLeft();
    
    aux->setLeft(aux2->getRight());
    aux2->setRight(n);
    
    return aux2;
}

template<class K, class V>
BSTNode<K,V>* AVLArbre<K,V>::RRrotation(BSTNode<K,V>* n) const {
    BSTNode<K,V>* aux;
    BSTNode<K,V>* aux2;
    
    aux = n;
    aux2 = aux->getRight();
    
    aux->setRight(aux2->getLeft());
    aux2->setLeft(n);
    
    return aux2;
}

template<class K, class V>
BSTNode<K,V>* AVLArbre<K,V>::RLrotation(BSTNode<K,V>* n) const {
    BSTNode<K,V>* aux;
    BSTNode<K,V>* aux2;
    BSTNode<K,V>* aux3;
    
    aux = n;
    aux2 = aux->getRight();
    aux3 = aux->getRight()->getLeft();
    
    aux->setRight(aux3->getLeft());
    aux2->setLeft(aux3->getRight());
    aux3->setLeft(n);
    aux3->setRight(aux2);
    
    return aux3;
}

template<class K, class V>
BSTNode<K,V>* AVLArbre<K,V>::LRrotation(BSTNode<K,V>* n) const {
    BSTNode<K,V>* aux;
    BSTNode<K,V>* aux2;
    BSTNode<K,V>* aux3;
    
    aux = n;
    aux2 = aux->getLeft();
    aux3 = aux->getLeft()->getRight();
    
    aux->setLeft(aux3->getRight());
    aux2->setRight(aux3->getLeft());
    aux3->setRight(n);
    aux3->setLeft(aux2);
    
    return aux3;
}

#endif

