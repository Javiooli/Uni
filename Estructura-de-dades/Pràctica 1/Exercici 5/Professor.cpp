//@Author: Javier Pedragosa
#include "Professor.h"
#include <iostream>

using namespace P;
using namespace std;

Professor::Professor()
{
    this->nom = "Sense nom";
    this->any_naixement = 0;
    this->edat = 0;
}

Professor::Professor(std::string nom, int any_naixement)
{
    this->nom = nom;
    this->any_naixement = any_naixement;
    this->edat = 2022 - any_naixement;
}

void Professor::print()
{
    cout << "\nProfessor(Nom ==> " << this->nom << ", Naixement ==> " <<
    this->any_naixement << ')' << endl;
}


std::string Professor::getNom()
{
    return this->nom;
}


void Professor::setNom(std::string nom)
{
    this->nom = nom;
}


int Professor::getAnyNaixement()
{
    return this->any_naixement;
}


void Professor::setAnyNaixement(int any_naixement)
{
    this->any_naixement = any_naixement;
    this->edat = 2022 - any_naixement;
}


int Professor::getEdat()
{
    return this->edat;
}

