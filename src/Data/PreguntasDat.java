/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Entidades.Materia;
import Entidades.Pregunta;
import clasesArch.ArchPreguntas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class PreguntasDat {
    ArrayList<Pregunta> preguntas = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);
    MateriaParaleloDat matDat = new MateriaParaleloDat();

    public PreguntasDat() {
    }

    
    public void visualizarPreguntas(ArrayList<Materia> materiasJuego,String nombre){
        
            ArchPreguntas ej = new ArchPreguntas(nombre);
            ej.leerArchivo();
            Collections.sort(ej.preguntas);
            int indexP=1;
            for(Pregunta mostrar:ej.preguntas){
                System.out.println(indexP + " " +mostrar);
                indexP+=1;
            }
         
    }
    
    public void agregPregunta(){
        System.out.println("Ingrese Codigo Materia");
        String materiaAgregar= entrada.nextLine();
        String nombreMat = matDat.obtNombreConCod(materiaAgregar);
        if(matDat.materiasAct(matDat.materiasJuego,nombreMat)){
            ArchPreguntas archp = new ArchPreguntas(nombreMat);
            archp.leerArchivo();

            System.out.println("Ingrese Enunciado de la pregunta");
            String enunciado= entrada.nextLine();

            System.out.println("Ingrese Nivel");
            String nivel= entrada.nextLine();

            System.out.println("Ingrese la  respuesta CORRECTA");
            String respuestaCorrecta= entrada.nextLine();

            System.out.println("Ingrese Posible Respuesta 1");
            String respuestaInc1= entrada.nextLine();

            System.out.println("Ingrese Posible Respuesta 2");
            String respuestaInc2= entrada.nextLine();

            System.out.println("Ingrese Posible Respuesta 3");
            String respuestaInc3= entrada.nextLine();

            Pregunta preguntaAgg= new Pregunta(enunciado, nivel , respuestaCorrecta, respuestaInc1, respuestaInc2, respuestaInc3);
            archp.preguntas.add(preguntaAgg);
            //ALMACENAR PREGUNTAS EN CSV NOMBRE CODIGO DE LA MATERIA
            archp.actualizarArchivo();
            }
    }
    
    public void eliminarPreg(){
        System.out.println("Ingrese el nombre de la Materia");
        String subject = entrada.nextLine();
        ArchPreguntas ap = new ArchPreguntas(subject);
        ap.leerArchivo();
        System.out.println("Que pregunta desea eliminar");
        int k=1;
        for(Pregunta p:ap.preguntas){
            System.out.println(k+".- "+p);
            k++;
        }
        int  eliminar= entrada.nextInt();
        ap.preguntas.remove((eliminar-1));
        ap.actualizarArchivo();
    }

    
}

