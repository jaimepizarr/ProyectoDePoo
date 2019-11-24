/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesArch;

import Entidades.Estudiante;
import Entidades.Paralelo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ArchEstudiantes {
    private Paralelo paralelo;
    
    public ArchEstudiantes(Paralelo paralelo) {
        this.paralelo = paralelo;
    }
    
    

    public void leerArchivoEstudiantes() {
        BufferedReader csvReader = null;
        try {
            String codMateria = paralelo.getMateria().getCodigo();
            String anioTerm = paralelo.getTerminoP().getAnio();
            String numTerm = paralelo.getTerminoP().getNumero();
            String ruta = "src/archivos/" + codMateria + "-" + paralelo.getParaleloCod() + "-" + anioTerm + "-" + numTerm + ".csv";

             //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine(); //escapar cabecera de archivo
            while ((fila = csvReader.readLine()) != null) {
                //iterar en el contenido del archivo
                String[] data = fila.split(",");
                paralelo.getEstudiantes().add(new Estudiante(data[0], data[1], data[2])); //crear objeto y agregar a lista
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Paralelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
