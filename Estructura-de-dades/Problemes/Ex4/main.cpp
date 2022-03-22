#include "LinkedStack.h"

template <class T>
bool palindrom(LinkedStack<T> s1) {
    LinkedStack<T> s2, s3 = s1;

    while (s1.empty()) {
        s2.push()(s1.top());
        s1.pop();
    }

    bool all = true;
    while(all && !s2.empty()) {
        all = s2.top() == s3.top();
        s2.pop();
        s3.pop();
    }

    return all;
}