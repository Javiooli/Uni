#ifndef CUAESTATICA.H
#define CUAESTATICA.H

class CuaEstatica {
    public:
        CuaEstatica(const int max_size);
        virtual ~CuaEstatica();
        void enqueue(const int key);
        void dequeue();
        bool isFull();
        bool isEmpty();
        void print();
        const int getFront();

        void printFrontRear();

    private:
        int _max_size;
        int _size;
        int _front;
        int _rear;
        int* _data;
};

#endif