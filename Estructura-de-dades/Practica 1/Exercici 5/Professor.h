//@Author: Javier Pedragosa
#ifndef PROFESSOR_H
#define PROFESSOR_H
#include <string>

using namespace std;

namespace P
{
    class Professor
    {
        public:
            Professor();
            Professor(std::string, int);
            void print();
            string getNom();
            void setNom(std::string);
            int getAnyNaixement();
            void setAnyNaixement(int);
            int getEdat();

        private:
            std::string nom;
            int any_naixement;
            int edat;
    };
}
#endif