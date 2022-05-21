template <class T>
class Inventari : protected T {
    public:
        Inventari(float iva);
        Inventari(float iva, string file_path);
        Inventari(const Inventari<T>& orig);
        virtual ~Inventari();
        void loadFromFile(string file_path);
        void printAll() const;
        void printAllReverse() const;
        float priceInTotal() const;
        float priceInTimeInterval(pair<string, string> ival) const;
        float priceInTimeIntervalByProduct(pair<string, string> ival, string pid) const;
        int size() const;
        int height() const;
    private:
        float iva;
        /* Metodes auxiliars, definiu-los aquí sota */
}; 