//@Author: Javier Pedragosa
#include <iostream>
#include <Vector>
#include <stdexcept>
#include "Estudiant.cpp"
#include "Professor.cpp"

using namespace std;

vector<string> arr_options = {"Sortir", "Afegir persona", "Resum persones"};

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

void charComprovar(char& entrada, string options){
    netejarCin();
    for (int i = 0; i < options.length(); i++) {
        if (entrada == options[i]) {
            return;
        }
    }
    throw std::invalid_argument("Entrada no valida.");
}

void intSeleccio(int& entrada) {
    try {
        imprimirMenu(arr_options);
        intComprovar(entrada, 1, arr_options.size());
    } catch (const std::invalid_argument& ex) {
        cout << endl << ex.what() <<  " Escull un nombre d'entre les opcions." << endl;
        intSeleccio(entrada);
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

void seleccionarEoP(char& seleccio) {
    cout << "Prem 'E' per crear un estudiant o 'P' per crear un professor." << endl;
    cin >> seleccio;
    seleccio = tolower(seleccio);
    try {
        charComprovar(seleccio, "ep");
    } catch (const std::invalid_argument& ex) {
        cout << endl << ex.what() << endl;
        seleccionarEoP(seleccio);
    }
}

void afegirEstudiant(int& comptador_estudiants) {
    string nom;
    int any_naixement;
    int assignatures;
    comptador_estudiants++;
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

void afegirProfessor(int& comptador_professors) {
    string nom;
    int any_naixement;
    comptador_professors++;
    cout << "Professor: " << comptador_professors << "\nNom? ";
    cin >> nom;
    cout << "Any naixement? ";
    introduirNum(any_naixement);
    Professor professor(nom, any_naixement);
    professor.print();
    cout << "Edat del nou professor: " << professor.getEdat() << "\n\n";
}

void afegirPersona(int& comptador_estudiants, int& comptador_professors) {
    char seleccio;
    seleccionarEoP(seleccio);
    if (seleccio == 'e') {
        afegirEstudiant(comptador_estudiants);
    }
    else if (seleccio == 'p') {
        afegirProfessor(comptador_professors);
    }
}

int main(){
    int comptador_estudiants = 0;
    int comptador_professors = 0;
    int option;

    do {
        cout << "Hola, que vols fer?" << endl;
        
        intSeleccio(option);


        switch(option){
            case 1:
                cout << "Fins a la propera." << endl;
                break;
            case 2: 
                afegirPersona(comptador_estudiants, comptador_professors);
                break;
            case 3:
                cout << "Estudiants creats: " << comptador_estudiants << ", professors creats: " << comptador_professors << ".\n\n";
                break;
            default:
                break;
        }

    } while (option != 1);
}

