//@Author: Javier Pedragosa
#include <string>

using namespace std;

namespace E
{
    class Estudiant
    {
        public:
            void print();
            int getEdat();

        private:
            int any_naixement;
            int edat;
            std::string nom;
            int assignatures;
    };
}