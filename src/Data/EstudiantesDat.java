/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Entidades.Estudiante;
import Entidades.Materia;
import Entidades.Paralelo;
import Entidades.Termino;
import clasesArch.ArchEstudiantes;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class EstudiantesDat {

    public EstudiantesDat() {
    }
    
    
    public static void adminEstudiantes(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Administrar estudiantes");
        System.out.println("Ingrese Paralelo");
        String paralelo= entrada.nextLine();

        System.out.println("Ingrese el año del Termino Academico");
        String anioTerm= entrada.nextLine();

        System.out.println("Ingrese el numero del Termino Academico");
        String numTerm= entrada.nextLine();

        System.out.println("Ingrese Codigo de Materia");
        String codigoMateria= entrada.nextLine();
        Materia materia = new Materia(codigoMateria);
        
        Termino estermac = new Termino(anioTerm,numTerm);
        Paralelo paralelEst = new Paralelo(paralelo,materia,estermac);
        ArchEstudiantes estPar = new ArchEstudiantes(paralelEst);
        estPar.leerArchivoEstudiantes();
        ArrayList<Estudiante> listEst= paralelEst.getEstudiantes();
        for(Estudiante e: listEst){
            System.out.println(e);
        }
        //GUARDAR INFORMACION CSV

    }
    
    public Estudiante getRandomEstudiantes(ArrayList<Estudiante> est) {
        Random posAle = new Random(System.currentTimeMillis());
        int numAleatorio = posAle.nextInt(est.size());
        
        return est.get(numAleatorio);
    }
    
    public String nombreporMatricula(ArrayList<Estudiante> est){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese su matricula:");
        String matriculaest= entrada.nextLine();
        String nombEst = null;
        for (Estudiante e: est){
            if (e.getMatricula().equals(matriculaest)){
                nombEst = e.getNombre();
            }
        }
        return nombEst;
    }    
    public String nombreporAleatorio(ArrayList<Estudiante> est){
        Estudiante alestudiante = getRandomEstudiantes(est);
        String nomEstudiante= alestudiante.getNombre();
        return nomEstudiante;
        }
    
    public String chooseParticipante (ArrayList<Estudiante> estudiantes){
        Scanner entrada = new Scanner(System.in);
        System.out.println("PARTICIPANTE: \n1.Ingresar matricula.\n2.Elegir Aleatoreamente");
        String opcionParticipante= entrada.nextLine();
        String participante= null;
        switch (opcionParticipante){
            case "1":
                //matricula del participante
                participante = nombreporMatricula(estudiantes);
                break;
            case "2":
                //Eleccion del participante aleatoreamente
               participante = nombreporAleatorio(estudiantes);
                System.out.println("El estudiante elegido es: "+participante);
                break;

            default:
                System.out.println("Ingrese una opcion correcta");
        }//switch participante
        return participante;
    }
}
