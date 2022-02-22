//@Author: Javier Pedragosa
#include "Rectangle.h"

using namespace std;

Rectangle::Rectangle(){
    width = 1;
    height = 1;
}

Rectangle::Rectangle(int w, int h){
    width = w;
    height = h;
}

int Rectangle::area(){
    return width * height;
}
