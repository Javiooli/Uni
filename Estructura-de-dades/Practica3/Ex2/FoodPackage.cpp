//@Author: Javier Pedragosa
#include <iostream>
#include <string>
#include "FoodPackage.h"

using namespace std;

void FoodPackage::print() {
    cout << "(" << toString(", ") << ")" << endl;
}

string FoodPackage::toString(string separator = ",") {
    string str = string();
    str.append(this->date_time);
    str.append(separator);
    str.append(this->product_id);
    str.append(separator);
    str.append(to_string(this->amount));
    str.append(separator);
    str.append(to_string(this->price));

    return str;
    
}