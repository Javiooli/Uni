//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include 
#include "CuaEncadenada.h"
#include "Node.h"
#include "Comanda.h"

using namespace std;

vector<string> MENU = {"Llegir un fitxer amb les entrades de les comandes", "Eliminar una comanda", "Inserir n entrades de comandes des de teclat (0 per finalitzar)",
                "Imprimir per pantalla la cua de comandes", "Sortir"};

void printMenu() {
    for (int i = 0; i < MENU.size(); i++) {
        cout << i + 1 << ". " << MENU[i] << endl;
    }
    
}

// Compara strings caracter a caracter convertint-los a minuscula per fer un equals ignorant majuscules.
bool iequals(const string& a, const string& b) {
    int size = a.size();
    if (b.size() != size)
        return false;
    for (int i = 0; i < size; ++i)
        if (tolower(a[i]) != tolower(b[i]))
            return false;
    return true;
}

// Fa que l'usuari pugui escollir les opcions del menu.
void selectOption(int& option) {
    string ent;
    cin >> ent;
    try {
        option = stoi(ent);
        if (option < 0 || option > MENU.size())
            throw std::invalid_argument("EXCEPTION: Argument no valid.");
    } catch (const exception& ex) {
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
    }
}

// Simplement permet escollir un enter pero comprovant que la entrada sigui valida.
int selectInt() {
    int input;
    cout << "Entrada: ";
    if (!(cin >> input) || cin.fail())
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
    return input;
}

template <class T>
void emplenarCuaDeFitxer(CuaEncadenada<T>& cua) {

}

// Executa la accio pertinent depenent de la opcio que s'hagi escollit.
template <class T>
void executeOption(const int& option, CuaEncadenada<T>& cua) {
    switch (option) {
        case 1:
            cua.enqueue(selectInt());
            break;
        case 2:
            cua.dequeue();
            break;
        case 3:
            cout << "El primer element de la cua es: " << cua.getFront() << "\n\n";
            break;
        case 4:
            cua.print();
            break;
        case 5:
            cout << "Fins aviat!" << endl;
            break;
        default:
            break;
    }
}

// main
int main(){
    int option = 0, exitOption = 5;
    CuaEncadenada<int> cua;

    do {
        try {
            printMenu();                // Imprimir menu
            selectOption(option);       // Escollir opcio
            executeOption(option, cua); // Executar opcio escollida
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    } while (option != exitOption); // exitOption == 5

    return 0;
}