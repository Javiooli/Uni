//@Author: Javier Pedragosa
#include <iostream>
#include <Vector>
#include <stdexcept>
#include "Estudiant.cpp"

using namespace std;

vector<string> arr_options = {"Sortir", "Informar estudiant"};

void netejarCin() {
    cin.clear();
    cin.ignore(1000, '\n');
}

bool imprimirMenu(vector<string> arr_options){
    for(int i = 0; i < arr_options.size(); i++) {
            cout << (i + 1) << ". " << arr_options[i] << endl;
        }
    return true;
}

void intComprovar(int& entrada, int min, int max) {
    if (!(cin >> entrada) || cin.fail() || entrada < min || entrada > max) {
        netejarCin();
        throw std::invalid_argument("Entrada no valida.");
    }
}

void seleccio(int& entrada) {
    try {
        imprimirMenu(arr_options);
        intComprovar(entrada, 1, arr_options.size());
    } catch (const std::invalid_argument& ex) {
        cout << endl << ex.what() <<  " Escull un nombre d'entre les opcions." << endl;
        seleccio(entrada);
    }
}

void introduirNum(int& option){
    try {
        intComprovar(option, 1, 2022);
    } catch (const std::invalid_argument& ex) {
        cout << endl << ex.what() << " Introdueix un nombre positiu." << endl;
        introduirNum(option);
    }
}

void afegirEstudiant(int comptador_estudiants) {
    string nom;
    int any_naixement;
    int assignatures;
    netejarCin();
    cout << "Estudiant: " << comptador_estudiants << "\nNom? ";
    cin >> nom;
    cout << "Any naixement? ";
    introduirNum(any_naixement);
    cout << "Assignatures? ";
    introduirNum(assignatures);
    Estudiant estudiant(nom, any_naixement, assignatures);
    estudiant.print();
    cout << "Edat del nou estudiant: " << estudiant.getEdat() << "\n\n";
}

int main(){
    int comptador_estudiants = 0;
    int option;

    do {
        cout << "Hola, que vols fer?" << endl;
        
        seleccio(option);


        switch(option){
            case 1:
                cout << "Fins a la propera." << endl;
                break;
            case 2: 
                comptador_estudiants++;
                afegirEstudiant(comptador_estudiants);
                break;
            default:
                break;
        }

    } while (option != 1);
}

