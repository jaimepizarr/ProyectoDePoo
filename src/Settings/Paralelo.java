/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

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
    private ArrayList<Estudiante> estudiantes= new ArrayList<>();
    private Materia materia=new Materia();
    private TerminoAcademico termino;

    public Paralelo(String paraleloCod, TerminoAcademico termino, String nombreMateria) {
        this.paraleloCod = paraleloCod;
        this.materia.setNombre(nombreMateria);
        this.termino = termino;
    }

    public Paralelo(String paraleloCod, String codigomateria, TerminoAcademico termino) {
        this.paraleloCod = paraleloCod;
        materia.setCodigo(codigomateria);
        this.termino = termino;
    }

    public Paralelo(String paraleloCod, Materia materia, TerminoAcademico termino) {
        this.paraleloCod = paraleloCod;
        this.materia = materia;
        this.termino = termino;
    }
    

    public String getParaleloCod() {
        return paraleloCod;
    }

    public void setParaleloCod(String paraleloCod) {
        this.paraleloCod = paraleloCod;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public TerminoAcademico getTermino() {
        return termino;
    }

    public void setTermino(TerminoAcademico termino) {
        this.termino = termino;
    }

   
    
    public void leerArchivoEstudiantes(){
        BufferedReader csvReader = null;
        try {
            String codMateria= materia.getCodigo();
            String anioTerm= termino.getAnio();
            String numTerm= termino.getNumero();
            String rute = codMateria+"-"+paraleloCod+"-"+anioTerm+"-"+numTerm+".csv";
            String ruta = "src/archivos/"+rute; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine();//escapar cabecera de archivo
            while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                String[] data = fila.split(",");
                estudiantes.add(new Estudiante(data[0], data[1], data[2])); //crear objeto y agregar a lista

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Paralelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Paralelo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Paralelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
