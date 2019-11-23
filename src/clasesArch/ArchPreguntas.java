/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesArch;


import Modelo.Pregunta;
import Modelo.Pregunta;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Ω
 *
 * @author josephAvila
 */
public class ArchPreguntas {
    
    public ArrayList<Pregunta> preguntas;
    public String materia;

    public ArchPreguntas(String materia) {
        preguntas = new ArrayList<Pregunta>();
        this.materia = materia;
    }
    public ArchPreguntas(){
        preguntas= new ArrayList<Pregunta>();
    }

    /**
     * @param args the command line arguments
     */
   
    /**
     * este metodo lee un archivo y almacena en una lista
     */
    public void leerArchivo() {
        BufferedReader csvReader = null;
        try {
            String ruta = "src/archivos/preguntas"+materia+".csv"; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine();//escapar cabecera de archivo
            while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                String[] data = fila.split(";");
                preguntas.add(new Pregunta(data[0], data[1], data[2],data[3],data[4],data[5])); //crear objeto y agregar a lista

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * almacena una lista en un archivo
     */
    
    public void actualizarArchivo() {
        FileWriter writer = null;
        try {
            String ruta = "src/archivos/preguntas"+materia+".csv"; //ruta del archivo que se va a leer
            writer = new FileWriter(ruta);
            for (Pregunta est : preguntas) {
                writer.write(est.getEnunciado()+ "," + est.getNivel()+ "," + est.getrCorrecta()+","+ est.getrCorrecta()+","+est.getRespInc1()+","+est.getRespInc2()+","+est.getRespInc3()+System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchPreguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

