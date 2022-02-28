//@Author: Javier Pedragosa
#include <iostream>
#include <Vector>
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

void seleccio(vector<string> arr_options, int& option){
    while (imprimirMenu(arr_options) && (!(cin >> option) || option < 1 || option > arr_options.size())) {
        cin.clear();
        cin.ignore(1000, '\n');
        std::cout << "Opcio no valida, escull un nombre d'entre les opcions." << endl;
    }
}

void introduirNum(int& option){
    netejarCin();
    while (!(cin >> option) || option < 0) {
        netejarCin();
        std::cout << "Entrada no valida, introdueix un nombre positiu." << endl;
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
    vector<Estudiant> estudiants = {};

    do {
        cout << "Hola, que vols fer?" << endl;
        
        seleccio(arr_options, option);


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

