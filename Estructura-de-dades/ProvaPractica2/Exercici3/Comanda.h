#ifndef COMANDA_H
#define COMANDA_H
#include <string>

using namespace std;
class Comanda {
    private:
        string _taula, _plat, _hora;
        int _quantitat;

    public:
        Comanda();
        Comanda(string taula, string plat, int quantitat, string hora);

        //friend std::ostream& operator<<(std::ostream& os, const Comanda& obj);

        string toString() const;
        string toStringFormatted() const;


};

#endif