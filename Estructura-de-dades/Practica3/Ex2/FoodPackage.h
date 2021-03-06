#ifndef FoodPackage_H
#define FoodPackage_H

//@Author: Javier Pedragosa

#include <string>

using namespace std;

class FoodPackage {

    public:
        FoodPackage(string time, string id, int am, float pr) {
            this->date_time = time;
            this->product_id = id;
            this->amount = am;
            this->price = pr;
        }

        FoodPackage(const FoodPackage& other) {
            this->date_time = other.date_time;
            this->product_id = other.product_id;
            this->amount = other.amount;
            this->price = other.price;
        }

        string getDate_time() { return this->date_time; }
        void setDate_time(string date_time) { this->date_time = date_time; }
        string getProduct_id() { return this->product_id; }
        void setProduct_id(string product_id) { this->product_id = product_id; }
        int getAmount() { return this->amount; }
        void setAmount(int amount) { this->amount = amount; }
        float getPrice() { return this->price; }
        void setPrice(float price) { this->price = price; }

        void print();
        string toString(string separator = ",");

    private:
        string date_time;
        string product_id;
        int amount;
        float price;
};

#endif