//@Author: Javier Pedragosa
#include <iostream>

using namespace std;

template <class T>
class Prova {
    public:
        Prova() {};
        bool equal(T a, T b) { return a == b; }
};

int main(){
    Prova<string> a;
    cout << (a.equal("hola", "hola") ? "true" : "false");
}