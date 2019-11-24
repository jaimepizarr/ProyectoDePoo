/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Entidades.Materia;
import Entidades.Paralelo;
import Entidades.Termino;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class MateriaParaleloDat {
    ArrayList<Materia> materiasJuego = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);
    ArrayList<Paralelo> paralelosJuego = new ArrayList<>();

    public MateriaParaleloDat() {
    }

    public ArrayList<Materia> getMateriasJuego() {
        return materiasJuego;
    }

    public void setMateriasJuego(ArrayList<Materia> materiasJuego) {
        this.materiasJuego = materiasJuego;
    }

    public Scanner getEntrada() {
        return entrada;
    }

    public void setEntrada(Scanner entrada) {
        this.entrada = entrada;
    }

    

    public ArrayList<Paralelo> getParalelosJuego() {
        return paralelosJuego;
    }

    public void setParalelosJuego(ArrayList<Paralelo> paralelosJuego) {
        this.paralelosJuego = paralelosJuego;
    }


    
    public void ingresarMateria(){
        System.out.println("Ingrese Nombre Materia");
        String nombreMat= entrada.nextLine();

        System.out.println("Ingrese Codigo Materia");
        String codigoMateria= entrada.nextLine();

        System.out.println("Cantidad de niveles");
        int cantidadNiveles= entrada.nextInt();
        entrada.nextLine();

        Materia materia = new Materia(nombreMat,codigoMateria,cantidadNiveles);
        materiasJuego.add(materia);
    }
    
    public void editarMateria(){
        System.out.println("Ingrese Codigo Materia");
        String codMateria= entrada.nextLine();
        int pos = 0;
        for (int i =0; i<materiasJuego.size();++i){
            if (materiasJuego.get(i).getCodigo().equals(codMateria)){
                pos=i;
            }
        }
        String cambio = "S";
        System.out.println("¿Desea cambiar el nombre de la materia? \nS/N");
        while (cambio == "S"){
            System.out.println("Ingrese el nuevo nombre");
            String nombre_nuevo = entrada.nextLine();
            materiasJuego.get(pos).setNombre(nombre_nuevo);
        }
        String cambio2="Y";
        System.out.println("¿Desea cambiar la cantidad de niveles de la materia? \n S/N");
        while (cambio2 == "S"){
            System.out.println("Ingrese la nueva cantidad de niveles");
            int niveles = entrada.nextInt();
            entrada.nextLine();
            materiasJuego.get(pos).setCantidadNiveles(niveles);
        }
    }
    
    public void desactivarMateria(){
        System.out.println("Ingrese Codigo Materia");
        String codMatDesac= entrada.nextLine();
        System.out.println("¿Está seguro de desactivar esta materia?\n  S/N");
        String confirmacion= entrada.nextLine();
        switch (confirmacion){
            case "S":
                for (Materia mat : materiasJuego){
                    if (mat.getCodigo().equals(codMatDesac)){
                        mat.setActivo(false);
                    }
                }
                break;
            case "N":
                break; 
        }
    }
    
    public void agregarParalelo(){
        System.out.println("Ingrese Materia");
        String nombMat= entrada.nextLine();
        Materia matForPar = getMateriaConNombre(nombMat);
        if (materiasAct(materiasJuego,nombMat)){
            System.out.println("Ingrese el año del Termino Academico");
            String anioTerm= entrada.nextLine();

            System.out.println("Ingrese el numero del Termino Academico");
            String numTerm= entrada.nextLine();


            System.out.println("Ingrese Numero de paralelo");
            String paralelo= entrada.nextLine();

            Paralelo parJuego = new Paralelo(paralelo,matForPar,new Termino(anioTerm,numTerm));
            paralelosJuego.add(parJuego);
        }
    }
    public void eliminarParalelo(){
        ArrayList<Paralelo> parElim = new ArrayList<>();
        for (Paralelo p: paralelosJuego){
            int i=1;
            if(materiasAct(materiasJuego,p.getMateria().getNombre())){
                System.out.println(i+".- "+p);
                i++;
                parElim.add(p);
            }
        }
        System.out.println("Seleccionar paralelo a eliminar");
        int seleccion=entrada.nextInt();
        entrada.nextLine();
        paralelosJuego.remove(parElim.get(seleccion - 1));
                
    }
    public Materia getMateriaConNombre(String nombre){
        Materia obMat = null;
        for (Materia mtr : materiasJuego){
            if (mtr.getNombre().equals(nombre)){
                obMat = mtr;
            }
        }
        return obMat;
    }
    
    public boolean materiasAct(ArrayList<Materia> mat, String nombreMat){
        ArrayList<Materia> materiatAct= new ArrayList<>();
        for(Materia mtr: mat){
            if (mtr.isActivo()){
                materiatAct.add(mtr);
            }
        }
        boolean bool = true;
        for(Materia m1:materiatAct){
            if (!(m1.getNombre().equals(nombreMat))){
                bool = false;
            }
        }
        return bool;

    }
    
    public String obtNombreConCod ( String codigo){
        String nombre = null;
        for(Materia m:materiasJuego){
            if (m.getCodigo().equals(codigo)){
                nombre= m.getNombre();
            }
        }
        return nombre;
    }
}
