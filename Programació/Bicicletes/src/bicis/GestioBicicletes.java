package bicis;
/*
 * Es vol gestionar la informació de com a molt 25 bicicletes. Cada bicicleta té 
 * un conjunt de dades: model, pes, si te suspensió i preu. Es vol programar una 
 * solucio que permeti: 1. Afegir bicicletes 2. Obtenir el model de la bicicleta 
 * que tingui mínim cost per kilogram. 3. Obtenir la bicicleta de pes inferior a un
 * pes entrat per l’usuari, si és que n’hi ha alguna. 4. Sortir.
 * La classe Bicicleta  emmagatzema informació d’una bicicleta (Bicicleta.java), 
 * la classe TauBicicleta guarda informació del conjunt de bicicletas 
 * (TauBicicleta.java), i el fitxer que visualitza el menú que es mostra a l’usuari 
 * i conté la funció principal main() es GestioBicicletes.java.  
*/
import java.util.Scanner;
public class GestioBicicletes {
    public static int menuOpcions(Scanner sc) {
	System.out.println("\n(1) Inserir/afegir una bicicleta");
	System.out.println("(2) Bicicleta de cost preu per kg minim");
	System.out.println("(3) Cerca de la bicicleta inferior a un cert pes");
        System.out.println("(4) Mostrar bicicletes");
        	System.out.println("(5) Sortir");
	System.out.println("Escull una de les opcions:");
	return sc.nextInt();
    }

    public static void main(String [] args) {
	final int NUM_BICIS = 50;
        Bicicleta     bici;
	TauBicicleta  tauBicis;
	int opcio;
	Scanner sc;
	sc = new Scanner(System.in);
        String model; double pes, preu; boolean teSuspensio; 

	tauBicis = new TauBicicleta(NUM_BICIS);
        /*
        Primer:   opcio = menuOpcions(sc)
        Seguent():opcio = menuOpcions(sc)
        FinSeq(): opcio == 4
        Esquema: recorregut
        */
	opcio = menuOpcions(sc);
	while (opcio!=5) {
	    // tractar element
	    switch (opcio) {
	    case 1: // Afegir bicicleta
		System.out.println("Model?");;
	        model = sc.next();
	        System.out.println("Pes?");;
	        pes = sc.nextDouble();
	        System.out.println("Te suspensió (s/n)?");
	        teSuspensio = sc.next().equals("s");
	        System.out.println("Preu?");;
	        preu = sc.nextDouble();             
                bici = new Bicicleta(model,pes,teSuspensio,preu);
		tauBicis.afegirBicicleta(bici);
		break;
            
	    case 2: // minim_preu_per_pes
		bici = tauBicis.minimPreuPerPes();
		System.out.println("Bicicleta de minim preu per kg " 
				   + bici);
		
		break;

	    case 3: // N'hi ha alguna de pes inferior a 1kg?
		System.out.println("Llindar de pes?");
		bici = tauBicis.inferiorPes(sc.nextInt());
		if ( bici != null ) {
		    System.out.println("Bicicleta trobada\n " + bici);
		} else {
		    System.out.println("Bicicleta no trobada ");
		}
		break;
            case 4:
		tauBicis.mostrarBicicletes();
		break;        
	    default:
		System.out.println("opcio erronia");
		break;
	    }

	    // obtencio seguent element
	    opcio = menuOpcions(sc);
	}
	System.out.println("Programa acabat correctament!");
    } 
}
