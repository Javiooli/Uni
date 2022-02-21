package bicis;
import java.util.Scanner;
public class Bicicleta {
    String  model;
    double  pes;
    boolean teSuspensio;
    double  preu;
    
    public Bicicleta(String model, double pes, boolean teSuspensio, double preu){
        this.model = model;
	this.pes = pes;
	this.teSuspensio = teSuspensio;
	this.preu = preu;
    
    }
     
    public String toString() {
	return ("Bicicleta: \n\t Model: "+model+"\n\t Pes: "+pes
		+"\n\t Suspensio:" +teSuspensio+"\n\t Preu: " +preu);
    }
}

