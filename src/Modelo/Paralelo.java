/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eljosephavila
 */
public class Paralelo {
    private String paraleloCod;
    private Materia materia=new Materia();
    private Termino terminoP;
    private ArrayList<Estudiante> estudiantes= new ArrayList<>();

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }    

    public Paralelo(String paraleloCod, Termino termino, String nombreMateria) {
        this.paraleloCod = paraleloCod;
        this.materia.setNombre(nombreMateria);
        this.terminoP = termino;
    }

    public Paralelo(String paraleloCod, String codigomateria, Termino termino) {
        this.paraleloCod = paraleloCod;
        materia.setCodigo(codigomateria);
        this.terminoP = termino;
    }

    public Paralelo(String paraleloCod, Materia materia, Termino termino) {
        this.paraleloCod = paraleloCod;
        this.materia = materia;
        this.terminoP = termino;
    }
    

    public String getParaleloCod() {
        return paraleloCod;
    }

    public void setParaleloCod(String paraleloCod) {
        this.paraleloCod = paraleloCod;
    }



    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Termino getTerminoP() {
        return terminoP;
    }

    public void setTerminoP(Termino termino) {
        this.terminoP = termino;
    }

   
    
    
}
