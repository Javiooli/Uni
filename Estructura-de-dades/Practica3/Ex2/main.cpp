//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include <fstream>
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
    string nomFitxer;
    cout << "Introdueix el nom del fitxer: ";
    cin >> nomFitxer;
    if (nomFitxer[nomFitxer.length() - 4] != '.' || nomFitxer[nomFitxer.length() - 3] != 't' ||
        nomFitxer[nomFitxer.length() - 2] != 'x' || nomFitxer[nomFitxer.length() - 1] != 't')

        nomFitxer.append(".txt");
    
    inv.loadFromFile(nomFitxer);
}

// Executa la accio pertinent depenent de la opcio que s'hagi escollit.
template <class T>
void executeOption(const int& option, Inventari<T>& inv, pair<string, string> dates, string id) {
    switch (option) {
        case 1:
            emplenarInvDeFitxer(inv);
            break;
        case 2:
            inv.printAll();
            break;
        case 3:
            inv.printAllReverse();
            break;
        case 4:
            cout << "Despesa total en inventari: " << inv.priceInTotal() << " euros." << endl << endl;
            break;
        case 5:
            cout << "Introdueix la primera data (AAAA-MM-DD): "; cin >> dates.first;
            cout << "Introdueix la segona data (AAAA-MM-DD): "; cin >> dates.second;
            cout << "Despesa total en inventari entre " << dates.first << " i " << dates.second << ": " << inv.priceInTimeInterval(dates) << " euros." << endl << endl;
            break;
        case 6:
            cout << "Introdueix la id del producte: "; cin >> id;
            cout << "Introdueix la primera data (AAAA-MM-DD): "; cin >> dates.first;
            cout << "Introdueix la segona data (AAAA-MM-DD): "; cin >> dates.second;
            cout << "Despesa total en inventari del producte " << id << " entre " << dates.first << " i " << dates.second << ": " << inv.priceInTimeIntervalByProduct(dates, id) << " euros." << endl << endl;
            break;
        case 7:
            cout << "WIP" << endl;
    }
}

// main
int main(){
    int option = 0, exitOption = 8;
    Inventari<BSTArbre<string, FoodPackage>> inventari(0.21);
    pair<string, string> dates;
    string id;

    do {
        try {
            printMenu();                // Imprimir menu
            selectOption(option);       // Escollir opcio
            executeOption(option, inventari, dates, id); // Executar opcio escollida
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    } while (option != exitOption); // exitOption == 8

    return 0;
}