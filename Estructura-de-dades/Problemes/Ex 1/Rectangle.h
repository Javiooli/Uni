//@Author: Javier Pedragosa
#ifndef RECTANGLE_H
#define RECTANGLE_H
#include <iostream>

using namespace std;

class Rectangle {
    private:
        int width;
        int height;

    public:
        Rectangle();
        Rectangle(int h, int w);

        int area();
};

#endif