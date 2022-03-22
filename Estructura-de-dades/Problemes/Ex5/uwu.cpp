template <class T>
class Stack {
    private:
        T* array;
        int _size;
    public:
        //Constructors
        Stack(int maxSize=100);
        Stack(const Stack<T>& other);

        //Destructors
        ~Stack();

        //Consultors
        const T& top() const;
        bool empty() const;
        bool full() const;
        int size() const;
        
        //Modificadors
        void push(const T& elem);
        void pop();
};



void moveTopNPoisitions(int n) {
    if (n <= 0 || stack.first == nullptr) throw;

    SingleNode* node = first;

    while (n > 0 && node != nullptr) {
        node = node->next;
        n--;
    }

    if (node == nullptr) throw;

    SingleNode* tmp = first->next;

    first = node->next;
    node->next = first;
    first = tmp;

}


int findMax(Queue<int>& Q) {
    int max = Q.getFront;
    int temp;

    for (int i = 0; i < Q.size; i++) {
        temp = Q.getFront();
        max = (temp > max ? temp : max);
        Q.dequeue();
        Q.enqueue(temp);
    }

    return max;
}