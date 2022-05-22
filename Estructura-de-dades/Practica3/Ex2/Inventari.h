//@Author: Javier Pedragosa

#include <string>
#include "FoodPackage.h"
#include "BSTNode.h"

using namespace std;

template <class T>
class Inventari : protected T {
    public:
        Inventari(float iva);
        Inventari(float iva, string file_path);
        Inventari(const Inventari<T>& orig);
        virtual ~Inventari();
        void loadFromFile(string file_path);
        void printAll() const;
        void printAllReverse();
        float priceInTotal() const;
        float priceInTimeInterval(pair<string, string> ival) const;
        float priceInTimeIntervalByProduct(pair<string, string> ival, string pid) const;
        int size() const;
        int height() const;
    private:
        float iva;
        void auxPrintAll(const BSTNode<string, FoodPackage>* node) const;
        float auxPriceInTotal(const BSTNode<string, FoodPackage>* node, float& total) const;
        float auxPriceInTimeInterval(const BSTNode<string, FoodPackage>* node, float& total, pair<string, string> ival) const;
        float auxPriceInTimeIntervalByProduct(const BSTNode<string, FoodPackage>* node, float& total, const pair<string, string> ival, const string pid) const;
        /* Metodes auxiliars, definiu-los aquí sota */
};

template <class T>
Inventari<T>::Inventari(float iva) {
    this->iva = iva;
}

template <class T>
Inventari<T>::Inventari(float iva, string file_path) {
    
}

template <class T>
Inventari<T>::Inventari(const Inventari<T>& orig) {
    
}

template <class T>
Inventari<T>::~Inventari() {

}

template <class T>
void Inventari<T>::loadFromFile(string file_path) {
    string linia, sbstr, date_time, product_id, amount, price;
    int numAtribut = 0, iter = 0;
    
    ifstream fitxer(file_path);
    if (fitxer.is_open()) {
        cout << endl;
        while (getline(fitxer, linia)) {
            iter++;
            for (char c : linia) {
                if (c == ',') {
                    switch (numAtribut) {
                        case 0:
                            date_time = sbstr;
                            break;
                        case 1:
                            product_id = sbstr;
                            break;
                        case 2:
                            amount = sbstr;
                            break;
                        default:
                            break;
                    }
                    sbstr = "";
                    numAtribut++;

                } else {
                    
                    if (numAtribut == 3)
                        price += c;
                    else
                        sbstr += c;

                }
            }
            FoodPackage FP(date_time, product_id, stoi(amount), stof(price));
            numAtribut = 0;
            sbstr = "";
            price = "";
            T::insert(date_time, FP);
        }
        cout << iter << " transaccions afegides.\n\n";
    } else {
        cout << "EXCEPTION: Fitxer no trobat.\n\n";
    }
}

template <class T>
void Inventari<T>::printAll() const {
    if (T::empty()) cout << "L'inventari esta buit." << endl;
    else {
        auxPrintAll(this->root);
        cout << "\n\n";
    }
}

template <class T>
void Inventari<T>::auxPrintAll(const BSTNode<string, FoodPackage>* node) const {
    if (node != nullptr) {
        auxPrintAll(node->getLeft());
        cout << "Dia: " << node->getKey() << ", Nombre de comandes: " << node->getValues().size() << "\n";
        auxPrintAll(node->getRight());
    }
}

template <class T>
void Inventari<T>::printAllReverse() {
    T::mirrorTree();
    T::printInorder();
    T::mirrorTree();
}

template <class T>
float Inventari<T>::priceInTotal() const {
    float total = 0;
    if (T::empty()) return 0;
    else {
        total = auxPriceInTotal(this->root, total);
        return total + (total * this->iva);
    }
}

template <class T>
float Inventari<T>::auxPriceInTotal(const BSTNode<string, FoodPackage>* node, float& total) const {
    if (node != nullptr) {
        auxPriceInTotal(node->getLeft(), total);
        list<FoodPackage, allocator<FoodPackage>> valors = node->getValues();
        for (FoodPackage fp : node->getValues()) {
            total += fp.getPrice() * fp.getAmount();
        }
        auxPriceInTotal(node->getRight(), total);
        return total;
    }
}

template <class T>
float Inventari<T>::priceInTimeInterval(pair<string, string> ival) const {
    float total = 0;
    if (T::empty()) return 0;
    else {
        total = auxPriceInTimeInterval(this->root, total, ival);
        return total + (total * this->iva);
    }
}

template <class T>
float Inventari<T>::auxPriceInTimeInterval(const BSTNode<string, FoodPackage>* node, float& total, pair<string, string> ival) const {
    if (node != nullptr) {
        auxPriceInTimeInterval(node->getLeft(), total, ival);
        if (node->getKey() >= ival.first && node->getKey() <= ival.second) {
            for (FoodPackage fp : node->getValues()) {
                total += fp.getPrice() * fp.getAmount();
            }
        }
        auxPriceInTimeInterval(node->getRight(), total, ival);
        return total;
    }
}

template <class T>
float Inventari<T>::priceInTimeIntervalByProduct(pair<string, string> ival, string pid) const {
    float total = 0;
    if (T::empty()) return 0;
    else {
        total = auxPriceInTimeIntervalByProduct(this->root, total, ival, pid);
        return total + (total * this->iva);
    }
}
template <class T>
float Inventari<T>::auxPriceInTimeIntervalByProduct(const BSTNode<string, FoodPackage>* node, float& total, const pair<string, string> ival, const string pid) const {
    if (node != nullptr) {
        auxPriceInTimeIntervalByProduct(node->getLeft(), total, ival, pid);
        if (node->getKey() >= ival.first && node->getKey() <= ival.second) {
            for (FoodPackage fp : node->getValues()) {
                if (fp.getProduct_id() == pid)
                    total += fp.getPrice() * fp.getAmount();
            }
        }
        auxPriceInTimeIntervalByProduct(node->getRight(), total, ival, pid);
        return total;
    }
}
template <class T>
int Inventari<T>::size() const {

}

template <class T>
int Inventari<T>::height() const {

}