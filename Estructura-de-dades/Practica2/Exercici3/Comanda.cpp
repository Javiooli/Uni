#include "Comanda.h"


using namespace std;
Comanda::Comanda() {

}

Comanda::Comanda(string taula, string plat, int quantitat, int hora, int minut) {
    if (taula[0] == 'i' && taula[1] == 'd') this->_taula = taula;
    else this ->_taula = "id" + taula;

    this->_plat = plat;
    this->_quantitat = quantitat;
    
    if (hora < 24 && hora >= 0) this->_hora = hora;
    else this->_hora = 0;

    if (minut <= 60 && minut >= 0) this->_minut = minut;
    else this->_minut = 0;
}

const string Comanda::toString() const {
    string comanda;
    comanda = _taula + ',' + _plat + ',' + std::to_string(_quantitat) + ',' + std::to_string(_hora) + ':' + std::to_string(_minut) + '\n';
    return comanda;
}

std::ostream& operator<<(std::ostream& os, const Comanda& obj){
    os << obj.toString();
    return os;
}

