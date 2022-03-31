#include "Comanda.h"

using namespace std;
Comanda::Comanda() {
    this->_taula = "null";
    this->_plat = "null";
    this->_quantitat = 0;
    this->_hora = "null";
}

Comanda::Comanda(string taula, string plat, int quantitat, string hora) {
    if (taula[0] == 'i' && taula[1] == 'd') this->_taula = taula;
    else this ->_taula = "id" + taula;

    this->_plat = plat;
    this->_quantitat = quantitat;
    
    this->_hora = hora;
}

std::string Comanda::toString() const {
    string comanda;
    comanda = _taula + ',' + _plat + ',' + std::to_string(_quantitat) + ',' + _hora;
    return comanda;
}