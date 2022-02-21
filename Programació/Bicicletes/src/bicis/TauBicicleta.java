package bicis;
// Tupla que guarda un conjunt de bicicletes
public class TauBicicleta {
    int       numBicicletes;
    Bicicleta[] taula;
    
    public TauBicicleta(int maxBicicletes) {
	taula = new Bicicleta[maxBicicletes];
	numBicicletes = 0;
    }
    void afegirBicicleta(Bicicleta p) {
	if (numBicicletes < taula.length) {
	    taula[numBicicletes] = p;
	    numBicicletes = numBicicletes + 1;
	} else {
	    System.out.println ("No s'ha afegit la bicicleta. Estas excedint el nombre mà1"
                    + "xim de bicicletes");
	}
    }
    /*
     * Retorna la bicicleta del array de bicis que tingui mínim cost per kilogram.
     * @return bicicleta amb minim cost per kilogram.
    */
    Bicicleta minimPreuPerPes() {
        Bicicleta minimPreu = null;
        for (int i = 0; i < numBicicletes; i++){
            if (minimPreu == null || taula[i].pes/taula[i].preu < minimPreu.pes/minimPreu.preu){
                minimPreu = taula[i];
            }
        }
        return minimPreu;
    }
    /*
     * Retorna la bicicleta de pes inferior a un pes entrat per l’usuari, 
       si és que n’hi ha alguna. Si no hi ha, retorna null
       @param kg llindar del pes
       @return bicicleta o null (si no hi han bicis amb pes menor a kg)
    */
    Bicicleta inferiorPes(double kg) {
        int i = 0;
        boolean trobat = false;
        Bicicleta bicicleta = null;
        while (!trobat && i < numBicicletes){
            bicicleta = taula[i];
            if (bicicleta.pes < kg){
                trobat = true;
            }
            i++;
        }
        if (!trobat){
            bicicleta = null;
        }
        return bicicleta;
    }
    
    void mostrarBicicletes(){
    
        for (int i=0; i < numBicicletes;i++)
            System.out.println(taula[i]);
    
    }
}
