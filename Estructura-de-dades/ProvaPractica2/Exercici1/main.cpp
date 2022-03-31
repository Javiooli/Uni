//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include "CuaEstatica.h"

using namespace std;

vector<string> menu = {"Inserir element a la cua", "Treure element de la cua", "Consultar el primer element",
                "Imprimir tot el contingut de la CuaEstatica", "Imprimir les posicions del front i el rear", "Sortir"};

vector<string> debugMenu = {"Inserir element a la cua", "Treure element de la cua", "Consultar el primer element",
                "Imprimir tot el contingut de la CuaEstat¶ica", "Imprimir les posicions del front i el rear",
                "Cas de prova 1", "Cas de prova 2", "Cas de prova 3", "Sortir"};

void printMenu(const bool& debug) {
    if (!debug) {
        for (int i = 0; i < menu.size(); i++) {
            cout << i + 1 << ". " << menu[i] << endl;
        }
    } else {
        cout << "=========================\n          DEBUG          \n=========================" << endl;
        for (int i = 0; i < debugMenu.size(); i++) {
            cout << i + 1 << ". " << debugMenu[i] << endl;
        }
    }
}

void selectOption(int& option, const bool& debug) {
    if (!(cin >> option) || cin.fail() || option < 0 || option > (debug ? debugMenu.size() : menu.size()))
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
}

int selectInt() {
    int input;
    cout << "Entrada: ";
    if (!(cin >> input) || cin.fail())
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
    return input;
}

bool iequals(const string& a, const string& b) {
    int size = a.size();
    if (b.size() != size)
        return false;
    for (int i = 0; i < size; ++i)
        if (tolower(a[i]) != tolower(b[i]))
            return false;
    return true;
}

CuaEstatica crearCua(bool& debug) {
    bool correcte = false;
    int input;
    string _debug;
    while (!correcte) {
        cout << "Introdueix el tamany desitjat per la cua o 'debug' per entrar al mode debug: ";
        cin >> _debug;
        try {
            input = stoi(_debug);
            if (input <= 0) throw std::invalid_argument("");
            correcte = true;
        } catch (const exception& ex) {
            if (iequals(_debug, "debug")) {
                debug = true;
                correcte = true;
            } else {
                cout << endl << "EXCEPTION: Entrada no valida. Introdueix un nombre positiu o 'debug'\n\n";
            }
        }
    }
    if (debug)
        input = 3;

    CuaEstatica cua(input);
    return cua;
}

void casDeProva_1() {
    CuaEstatica cua(3);
    cua.enqueue(10);
    cua.enqueue(20);
    cua.printFrontRear();
    cua.enqueue(30);
    try {
        cua.enqueue(40);
    } catch (const exception& ex) {
        cout << ex.what() << "\n\n";
        cua.print();
        cua.printFrontRear();
        cua.dequeue();
        cua.enqueue(50);
        cua.print();
        cua.printFrontRear();
    }
}

void casDeProva_2() {
    CuaEstatica cua(3);
    cua.enqueue(10);
    cout << cua.getFront() << "\n\n";
    cua.enqueue(20);
    cua.enqueue(30);
    cua.print();
    cua.printFrontRear();
    cua.dequeue();
    cout << cua.getFront() << "\n\n";
    cua.dequeue();
    cua.printFrontRear();
    cua.dequeue();
    try {
        cua.dequeue();
    } catch (const exception& ex) {
        cout << ex.what() << "\n\n";
        cua.print();
        cua.printFrontRear();
    }
}

void casDeProva_3() {
    cout<<" CAS PROVA DUPLICA"<<endl;

    CuaEstatica q(5);
    q.enqueue(1);
    q.enqueue(2);
    q.print();

    q.duplica();
    q.print();

    CuaEstatica q3(2);
    q3.enqueue(6);
    q3.enqueue(7);
    q3.print();

    try {
        q.duplica();
    } catch(const exception& e) {
        cout<<e.what()<<endl;
    }

    cout<<"Fi cas de Prova DUPLICA\n"; 
}

void executeOption(const int& option, const bool& debug, CuaEstatica& cua) {
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
                cua.printFrontRear();
                break;
            case 6:
                cout << "Fins aviat!" << endl;
                cua.~CuaEstatica();
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
                cua.printFrontRear();
                break;
            case 6:
                casDeProva_1();
                break;
            case 7:
                casDeProva_2();
                break;
            case 8:
                casDeProva_3();
                break;
            case 9:
                cout << "Fins aviat!" << endl;
                cua.~CuaEstatica();
                break;
            default:
                break;
        }
    }
}

int main(){
    bool debug = false;
    int option = 0;
    int exitOption = 0;

    CuaEstatica cua = crearCua(debug);
    exitOption = (debug ? 9 : 6);

    while (option != exitOption) {
        try {
            printMenu(debug);
            selectOption(option, debug);
            executeOption(option, debug, cua);
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    }
    return 0;
}