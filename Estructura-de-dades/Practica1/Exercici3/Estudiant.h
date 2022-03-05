//@Author: Javier Pedragosa
#ifndef ESTUDIANT_H
#define ESTUDIANT_H
#include <string>

using namespace std;

namespace E
{
    class Estudiant
    {
        public:
            Estudiant(std::string, int, int);
            void print();
            string getNom();
            void setNom(std::string);
            int getAnyNaixement();
            void setAnyNaixement(int);
            int getAssignatures();
            void setAssignatures(int);
            int getEdat();

        private:
            std::string nom;
            int any_naixement;
            int edat;
            int assignatures;
    };
}
#endif