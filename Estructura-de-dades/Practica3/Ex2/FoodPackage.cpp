//@Author: Javier Pedragosa
#include <iostream>
#include <string>
#include "FoodPackage.h"

using namespace std;

FoodPackage::FoodPackage(string time, string id, int am, float pr) {
    this->date_time = time;
    this->product_id = id;
    this->amount = am;
    this->price = pr;
}

FoodPackage::FoodPackage(const FoodPackage& other) {
    this->date_time = other.date_time;
    this->product_id = other.product_id;
    this->amount = other.amount;
    this->price = other.price;
}

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