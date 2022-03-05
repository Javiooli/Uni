//@Author: Javier Pedragosa
#include <iostream>

using namespace std;

int main(){
    string nom;
    int option;
    string arr_options[] = {"Sortir", "Benvinguda"};
    int options_size = *(&arr_options + 1) - arr_options;

    cout << "Com et dius? " << endl;
    cin >> nom;

    do {
        cout << "Hola " << nom << ", que vols fer?" << endl;
        for(int i = 0; i < options_size; i++) {
            cout << (i + 1) << ". " << arr_options[i] << endl;
        }
        cin >> option;

        if (option == 2) cout << "Benvingut/da a l'assignatura d'estructura de dades, " << nom << endl;
    }
    while(option != 1);
    cout << "Fins a la propera, " << nom << "!" << endl;
}