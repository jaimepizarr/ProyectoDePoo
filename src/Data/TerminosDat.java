/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Entidades.Termino;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class TerminosDat {
    ArrayList<Termino> terminos_existentes = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);

    public TerminosDat() {
    }
    
    public void ingresarTermino(){
        System.out.println("Ingresar anio: ");
        String anio= entrada.nextLine();
        //numero de termino
        System.out.println("Ingresar numero de termino");
        String numeroTermino= entrada.nextLine();
        Termino term = new Termino(anio,numeroTermino);
        terminos_existentes.add(term);
    }
    
    public void eliminarTermino(){
        int i=1;
        System.out.println("Los terminos existentes son: ");
        for (Termino t:terminos_existentes){
            System.out.println(i+". "+ t);
            ++i;
        }
        System.out.println("Ingrese el numero del termino a eliminar");
        int termElim = entrada.nextInt();
        entrada.nextLine();
        terminos_existentes.remove(termElim-1);
    }
    
    public Termino configurarTerminoJuego(){
        int j=1;
        for (Termino t:terminos_existentes){
           System.out.println(j+". "+ t);
           ++j;}
        int elegir = entrada.nextInt();
        entrada.nextLine();
        return terminos_existentes.get(elegir-1);
    }
}
