//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include <fstream>
#include <chrono>
#include <thread>
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
    if (!(cin >> input) || cin.fail()) {
        cin.clear();
        cin.ignore(1000, '\n');
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
    }
    return input;
}

template <class T>
void inserirEntrades(CuaEncadenada<T>& cua) {
    string entrada, taula, plat, hora;
    bool correcte = false;
    int exit = -1, quant, iter = 0;
    while (exit != 0) {
        iter++;
        while (!correcte) {
            cout << "Entra numero de taula per inserir una nova entrada o 0 per sortir: ";
            cin >> entrada;
            if (entrada[0] == '0') {
                exit = 0;
                break;
            }
            if (entrada[0] != 'i' && entrada[1] != 'd') {
                try {
                    exit = stoi(entrada);
                    taula = "id" + entrada;
                    correcte = true;
                } catch (const exception& e) {
                    cout << "EXCEPTION: Entrada invalida.\n\n";
                }
            } else {
                taula = entrada;
                correcte = true;
            }
        }

        if (exit == 0) {
            cout << "\n\n";
            break;
        }

        correcte = false;
        while (!correcte) {
            cout << "Entra nom del plat: ";
            std::cin >> std::ws;
            try {
                std::getline(std::cin, entrada);
                plat = entrada;
                correcte = true;
            } catch (const exception& ex) {
                cout << "EXCEPTION: Entrada invalida.\n\n";
            }
        }

        correcte = false;
        while (!correcte) {
            cout << "Entra quantitat: ";
            try {
                quant = selectInt();
                correcte = true;
            } catch (const exception& ex) {
                cout << ex.what() << "\n\n";
            }
        }

        std::time_t t = std::time(0);
        std::tm* now = std::localtime(&t);
        string h = (now->tm_hour < 10 ? ('0' + to_string(now->tm_hour)) : to_string(now->tm_hour));
        string m = (now->tm_min < 10 ? ('0' + to_string(now->tm_min)) : to_string(now->tm_min));
        hora = h + ':' + m;

        Comanda com(taula, plat, quant, hora);
        cua.enqueue(com);
        correcte = false;
    }

    cout << iter << " comandes afegides.\n\n";
}

template <class T>
void emplenarCuaDeFitxer(CuaEncadenada<T>& cua) {
    string nomFitxer, linia, taula, plat, quantitat, hora, sbstr;
    int numAtribut = 0, iter = 0;
    cout << "Introdueix el nom del fitxer: ";
    cin >> nomFitxer;
    if (nomFitxer[nomFitxer.length() - 4] != '.' || nomFitxer[nomFitxer.length() - 3] != 't' ||
        nomFitxer[nomFitxer.length() - 2] != 'x' || nomFitxer[nomFitxer.length() - 1] != 't')

        nomFitxer.append(".txt");
    
    ifstream fitxer(nomFitxer);
    if (fitxer.is_open()) {
        cout << endl;
        while (getline(fitxer, linia)) {
            iter++;
            for (char c : linia) {
                if (c == ',') {
                    switch (numAtribut) {
                        case 0:
                            taula = sbstr;
                            break;
                        case 1:
                            plat = sbstr;
                            break;
                        case 2:
                            quantitat = sbstr;
                            break;
                    }
                    sbstr = "";
                    numAtribut++;

                } else {
                    
                    if (numAtribut == 3)
                        hora += c;
                    else
                        sbstr += c;

                }

using namespace std;
            }
            Comanda com(taula, plat, stoi(quantitat), hora);
            numAtribut = 0;
            sbstr = "";
            hora = "";
            cua.enqueue(com);
        }
        cout << iter << " comandes afegides.\n\n";
    } else {
        cout << "EXCEPTION: Fitxer no trobat.\n\n";
    }
}

// Executa la accio pertinent depenent de la opcio que s'hagi escollit.
template <class T>
void executeOption(const int& option, CuaEncadenada<T>& cua) {
    switch (option) {
        case 1:
            emplenarCuaDeFitxer(cua);
            break;
        case 2:
            cua.dequeue();
            break;
        case 3:
            inserirEntrades(cua);
            break;
        case 4:
            cua.print();
            break;
    }
}

// main
int main(){
    int option = 0, exitOption = 5;
    CuaEncadenada<Comanda> cua;

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
        std::this_thread::sleep_for(std::chrono::milliseconds(500));
    } while (option != exitOption); // exitOption == 5

    return 0;
}