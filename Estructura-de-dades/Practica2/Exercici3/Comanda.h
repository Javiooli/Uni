#ifndef COMANDA_H
#define COMANDA_H
#include <string>

using namespace std;
class Comanda {
    private:
        string _taula, _plat;
        int _quantitat, _hora, _minut;

    public:
        Comanda();
        Comanda(string taula, string plat, int quantitat, int hora, int minut);

        friend std::ostream& operator<<(std::ostream& os, const Comanda& obj);

        const string toString() const;


};

#endif