//@Author: Javier Pedragosa
#include <iostream>
#include <vector>
#include "CuaEstatica.cpp"

using namespace std;

vector<string> menu = {"Inserir element a la cua", "Treure element de la cua", "Consultar el primer element",
                "Imprimir tot el contingut de la CuaEstatica", "Imprimir les posicions del front i el rear", "Sortir"};

void printMenu() {
    for (int i = 0; i < menu.size(); i++) {
        cout << i + 1 << ". " << menu[i] << endl;
    }
}

void selectOption(int& option) {
    if (!(cin >> option) || cin.fail() || option < 0 || option > menu.size()) 
        throw std::invalid_argument("EXCEPTION: Argument no valid.");

}

int selectInt() {
    int input;
    cout << "Entrada: ";
    if (!(cin >> input) || cin.fail())
        throw std::invalid_argument("EXCEPTION: Argument no valid.");
    return input;
}

int main(){
    CuaEstatica cua(3);
    int option = 0;
    while (option != 6) {
        try {
            printMenu();
            selectOption(option);
            switch (option) {
                case 1:
                    cua.enqueue(selectInt());
                    break;
                case 2:
                    cua.dequeue();
                    break;
                case 3:
                    cout << cua.getFront() << endl;
                    break;
                case 4:
                    cua.print();
                    break;
                case 5:
                    cua.printFrontRear();
                    break;
                case 6:
                    cout << "Fins aviat!" << endl;
                    break;
                default:
                    break;

            }
        } catch (const exception& ex) {
            cin.clear();
            cin.ignore(1000, '\n');
            cout << ex.what() << "\n\n";
        }
    }
    return 0;
}