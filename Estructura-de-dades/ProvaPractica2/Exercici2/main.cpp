//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include "CuaEncadenada.h"
#include "Node.h"

// En executar-lo, entrar "debug" a la consola per desbloquejar els tres casos de prova.

using namespace std;

vector<string> MENU = {"Inserir element a la cua", "Treure element de la cua", "Consultar el primer element",
                "Imprimir tot el contingut de la cua", "Sortir"};

vector<string> DEBUG_MENU = {"Inserir element a la cua", "Treure element de la cua", "Consultar el primer element", // Per accedir al menu de debug amb els dos casos de prova,
                "Imprimir tot el contingut de la cua", "Cas de prova 1", "Cas de prova 2", "Cas de prova 3", "Sortir"};               // entrar 'debug' en comptes d'un numero al menu.

// Imprimeix el menu MENU si el mode debug no esta activat, i el menu DEBUG_MENU si el mode debug esta activat.
// A mes, recorda sovint com activar o desactivar el mode debug.
void printMenu(const bool& debug, int& turns) {
    turns = ++turns % 5;
    if (!debug) {
        if (turns == 3) cout << "(Entra debug per entrar al mode debug.)\n\n";
        for (int i = 0; i < MENU.size(); i++) {
            cout << i + 1 << ". " << MENU[i] << endl;
        }
    } else {
        if (turns == 3) cout << "(Entra out per sortir del mode debug.)\n\n";
        cout << "=========================\n          DEBUG          \n=========================" << endl;
        for (int i = 0; i < DEBUG_MENU.size(); i++) {
            cout << i + 1 << ". " << DEBUG_MENU[i] << endl;
        }
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

// Fa que l'usuari pugui escollir les opcions del menu a mes d'activar i desactivar el mode debug.
void selectOption(int& option, bool& debug) {
    string ent;
    cin >> ent;
    try {
        option = stoi(ent);
        if (option < 0 || option > (debug ? DEBUG_MENU.size() : MENU.size()))
            throw std::invalid_argument("EXCEPTION: Argument no valid.");
        
    } catch (const exception& ex) {
        option = 0;
        if (iequals(ent, "debug")) debug = true;
        else if (iequals(ent, "out")) debug = false;
        else throw std::invalid_argument("EXCEPTION: Argument no valid.");
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
void casDeProva_1(CuaEncadenada<T>& cua) {
    while (!cua.isEmpty()) {cua.dequeue();}
    cua.enqueue(10);
    cua.enqueue(20);
    cua.enqueue(30);
    cua.enqueue(40);
    cua.print();
    cua.dequeue();
    cua.enqueue(50);
    cua.print();
}

template <class T>
void casDeProva_2(CuaEncadenada<T>& cua) {
    while (!cua.isEmpty()) {cua.dequeue();}
    cua.enqueue(10);
    cout << "El primer element de la cua es: " << cua.getFront() << "\n\n";
    cua.enqueue(20);
    cua.enqueue(30);
    cua.print();
    cua.dequeue();
    cout << "El primer element de la cua es: " << cua.getFront() << "\n\n";
    cua.dequeue();
    cua.dequeue();
    try {
        cua.dequeue();
    } catch (const exception& e) {
        cout << e.what() << "\n\n";
        cua.print();
    }
}

void casDeProva_3() {
    cout << "CAS PROVA IGUALS" << endl;
    CuaEncadenada<int> q; q.enqueue(1); q.enqueue(2); q.enqueue(3);

    CuaEncadenada<int> q2; q2.enqueue(4); q2.enqueue(5);
    cout << "No son iguals " << (q.iguals(q2) ? "True" : "False") << endl;
    cout << "No son iguals " << (q2.iguals(q) ? "True" : "False") << endl;
    cout << "No son iguals " << (q.iguals(q) ? "True" : "False") << endl;

    CuaEncadenada<int> q3; q3.enqueue(4); q3.enqueue(5);
    cout<<"No son iguals "<<(q3.iguals(q2) ? "True": "False") <<endl;
    cout<<"No son iguals "<<(q2.iguals(q3) ? "True": "False") <<endl;
    cout<<"Fi cas de Prova IGUALS\n\n";
}

// Executa la accio pertinent depenent de la opcio que s'hagi escollit i si el mode debug esta activat.
template <class T>
void executeOption(const int& option, const bool& debug, CuaEncadenada<T>& cua) {
    if (!debug) {
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
    } else {
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
                casDeProva_1(cua);
                break;
            case 6:
                casDeProva_2(cua);
                break;
            case 7:
                casDeProva_3();
                break;
            case 8:
                cout << "Fins aviat!" << endl;
                break;
            default:
                break;
        }
    }
}

// main
int main(){
    bool debug = false;
    int option = 0, exitOption = 5, turns = 0; // Turns mesura la quantitat de vegades que l'usuari passa pel menu per imprimir el missatge sobre el debug.
    CuaEncadenada<int> cua;

    do {
        try {
            printMenu(debug, turns);                // Imprimir menu
            selectOption(option, debug);        // Escollir opcio
            exitOption = (debug ? 8 : 5);       // Canviar numero opcio sortir segons si debug esta activat o no
            executeOption(option, debug, cua);  // Executar opcio escollida
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    } while (option != exitOption); // exitOption = 5 normalment, 7 si mode debug

    return 0;
}

//g++ *.cpp -o main.exe -std=c++11