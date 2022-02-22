//@Author: Javier Pedragosa
#include <iostream>
#include <string>

using namespace std;

class Person
{
    private:
        String name;
        int age;

    public:
        Person(String n, int a);
        string getName()const;
        int getAge()const;
        void incrementAge();
        void print()const;


}