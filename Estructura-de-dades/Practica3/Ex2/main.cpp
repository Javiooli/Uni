//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include <fstream>
#include <chrono>
#include <thread>

#include "FoodPackage.h"
#include "BSTArbre.h"
#include "Inventari.h"

using namespace std;

vector<string> MENU = {"Carregar arxiu", "Mostrar transaccions", "Mostrar transaccions (invers)",
                "Mostrar total inventari", "Mostrar total entre dues dates", "Mostrar total entre dues dates per producte",
                "Guardar transaccions totals entre dues dates", "Sortir"};

void printMenu() {
    for (int i = 0; i < MENU.size(); i++) {
        cout << i + 1 << ". " << MENU[i] << endl;
    }
    
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

template <class T>
void emplenarInvDeFitxer(Inventari<T>& inv) {
    
}

// Executa la accio pertinent depenent de la opcio que s'hagi escollit.
template <class T>
void executeOption(const int& option, Inventari<T>& inv) {
    switch (option) {
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        default:
            break;
    }
}

// main
int main(){
    int option = 0, exitOption = 8;
    Inventari<BSTArbre<string, FoodPackage>> inventari(21);

    do {
        try {
            printMenu();                // Imprimir menu
            selectOption(option);       // Escollir opcio
            executeOption(option, inventari); // Executar opcio escollida
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    } while (option != exitOption); // exitOption == 8

    return 0;
}