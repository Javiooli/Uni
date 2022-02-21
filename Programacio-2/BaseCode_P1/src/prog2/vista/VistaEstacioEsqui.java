/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.vista;

import java.util.Scanner;
//import prog2.model.EstacioEsqui;

/**
 *
 * @author ricardo.marques
 */
public class VistaEstacioEsqui {

    /* Atributs */
    // Add the attributes here

    /* Constructor de la Vista*/
    public VistaEstacioEsqui(String nomEstacio, int velocitatVentNord, String visibilitatNord,
            int velocitatVentSud, String visibilitatSud) {

    }

    /* Métodes */      
    public void gestioEstacio() {

        // Creación de un objeto para leer el input desde el teclado
        Scanner sc = new Scanner(System.in);

        // Llamar a la funcion que gestiona el menu
        gestioMenu(sc);
    }

    /* ******************************************** */
 /* Gestion, Opciones y Descripcion del Menu (M) */
 /* ******************************************** */
    private static enum OpcionesMenu {
        M_Opcion_1_ListarPistas,
        M_Opcion_2_ListarPistasAbiertas,
        M_Opcion_3_ListarPistasCerradas,
        M_Opcion_4_ListarRemontadores,
        M_Opcion_5_ListarRemontadoresEnServicio,
        M_Opcion_6_ListarRemontadoresFueraServicio,
        M_Opcion_7_CalcularTotalKms,
        M_Opcion_8_Modificar_Vent,
        M_Opcion_9_Modificar_Visibilitat,
        M_Opcion_10_Report_Meteo,
        M_Opcion_11_Salir
    };

    // Descripcio de les opcions del menu principal
    private static final String[] descMenu = {
        "Llistar la informació de totes les pistes", // Opcio 1
        "Llistar la informació de les pistes obertes", // Opcio 2
        "Llistar la informació de les pistes tancades", // Opcio 3
        "Llistar la informació de tots els remuntadors", // Opcio 4
        "Llistar la informació dels remuntadors en servei", // Opcio 5
        "Llistar la informació dels remuntadors fora de servei", // Opcio 6
        "Calcular el total de kilometres de pistes i de pistes obertes", // Opcio 7
        "Modificar la velocitat del vent", // Opcio 8
        "Modificar la visibilitat", // Opcio 9
        "Mostrar les condicions meteorologiques actuals", // Opcio 10
        "Sortir del gestor de l'estació" // Opcion 12
    };

    // Funcio que gestiona el menu principal de la aplicacio
    // Hi ha 3 passos principals:
    //   1. Crear l'objete que representa (conté) el menu
    //   2. Asignar les descripcions del menu
    //   3. Bucle n el cual es tracta la opcio seleccionada per l'usuari
    public void gestioMenu(Scanner sc) {
        // Creación del objeto que representa el menu. El primer argumento del contructor es el nombre del menu
        Menu<OpcionesMenu> menuVall2000 = new Menu<>("Menu NO IMPLEMENTAT ---> estacio.getNomEstacio()", OpcionesMenu.values());

        // Assignar una descripción a cada una de las opciones
        //  - OPCIONAL - Por defecto enseña el nombre de la opción
        menuVall2000.setDescripcions(descMenu);

        // Variable (de tipo enumerado igual a las opciones del menu) que contiene la opcion escogida
        OpcionesMenu opcionMenu;

        // Lançar el bucle principal de la aplicación
        do {
            menuVall2000.mostrarMenu();
            opcionMenu = menuVall2000.getOpcio(sc);

            switch (opcionMenu) {
                case M_Opcion_1_ListarPistas:
                    // Put your code here
                    break;
                case M_Opcion_2_ListarPistasAbiertas:
                    // Put your code here
                    break;
                case M_Opcion_3_ListarPistasCerradas:
                    // Put your code here
                    break;
                case M_Opcion_4_ListarRemontadores:
                    // Put your code here
                    break;
                case M_Opcion_5_ListarRemontadoresEnServicio:
                    // Put your code here
                    break;
                case M_Opcion_6_ListarRemontadoresFueraServicio:
                    // Put your code here
                    break;
                case M_Opcion_7_CalcularTotalKms:
                    // Put your code here
                    break;
                case M_Opcion_8_Modificar_Vent:
                    // Put your code here
                    break;
                case M_Opcion_9_Modificar_Visibilitat:
                    // Put your code here
                    break;
                case M_Opcion_10_Report_Meteo:
                    // Put your code here
                    break;
            }

        } while (opcionMenu != OpcionesMenu.M_Opcion_11_Salir);
    }

    
}
