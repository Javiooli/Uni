/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javis
 */

import java.util.*;

public class Conjunts {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        int n;
        int i = 0;
        int elem = 1;
        int sufixCount = 0;
        String[] sufix = {"r","n","r","t","è"};
        boolean finalitzat = false;
        Scanner ent = new Scanner(System.in);

        System.out.println("Introdueix la mida màxima de les llistes");
        n = ent.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];

        while(i < n && !finalitzat){
            System.out.println("Introdueix el "+ elem + sufix[sufixCount]+" element de la primera llista.");
            a[i] = ent.nextInt();
            elem++;
            if(elem <= 5)
                sufixCount++;
            if (a[i] == 0)
                finalitzat = true;
            i++;
        }
        i = 0;
        elem = 1;
        sufixCount = 0;
        finalitzat = false;

        while(i < n && !finalitzat){
            System.out.println("Introdueix el "+ elem + sufix[sufixCount]+" element de la segona llista.");
            b[i] = ent.nextInt();
            elem++;
            if(elem <= 5)
                sufixCount++;
            if (b[i] == 0)
                finalitzat = true;
            i++;
        }
        if (conjuntsIguals(a, b, n))
            System.out.println("Si");
        else
            System.out.println("No");
        
        ent.close();

    }

    static boolean conjuntsIguals(int[] a, int[] b, int n){
        boolean iguals = true;
        boolean trobat = false;
        int i = 0;
        int j = 0;

        while(i < n && iguals){

            while(j < n && !trobat){

                if(a[i] == b[j])
                    trobat = true;
                else
                    j++;
            }

            if(!trobat)
                iguals = false;
            else{
                trobat = false;
                j = 0;
                i++;
            }
            
        }
        i = 0;
        j = 0;

        while(i < n && iguals){

            while(j < n && !trobat){

                if(b[i] == a[j])
                    trobat = true;
                else
                    j++;
            }
            
            if(!trobat)
                iguals = false;
            else{
                trobat = false;
                j = 0;
                i++;
            }
            
        }

        return iguals;
                    
            
        
    }
}
