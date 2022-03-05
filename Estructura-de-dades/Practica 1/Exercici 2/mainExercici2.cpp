//@Author: Javier Pedragosa
//@Date: 20/02/22
#include <iostream>
#include <Vector>

using namespace std;

void redefinir_nom(string& nom){
    cout << "Hola, com et dius? " << endl;
    cin >> nom;
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

int main(){
    string nom;
    int option;
    vector<string> arr_options = {"Sortir", "Benvinguda", "Redefinir el nom"};

    redefinir_nom(nom);

    do {
        cout << "Hola " << nom << ", que vols fer?" << endl;
        
        seleccio(arr_options, option);


        switch(option){
            case 1:
                cout << "Fins a la propera, " << nom << "!" << endl;
                break;
            case 2: 
                cout << "Benvingut/da a l'assignatura d'estructura de dades, " << nom << endl;
                break;
            case 3:
                redefinir_nom(nom);
                break;
            default:
                break;
        }
    }
    while(option != 1);

    return 0;
}