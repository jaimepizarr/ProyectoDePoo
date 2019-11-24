/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesArch;

import Entidades.Paralelo;
import Entidades.Reporte;
import Entidades.Termino;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ArchReporte {
    public ArrayList<Reporte> reportes =new ArrayList<>();


    public ArchReporte() {
    }

    public void generarCsvReporte(String ruta) throws IOException{
        String archivoCSV = ruta;
        Writer writer = Files.newBufferedWriter(Paths.get(archivoCSV));
    }
    
    public void actualizarArchivoReporte(String rute) {//// OJO HACER ESTO
        FileWriter writer = null;
        try {
            String ruta = rute; //ruta del archivo que se va a leer
            writer = new FileWriter(ruta);
            for (Reporte est :reportes ) {
                writer.write(est.getFechaDeljuego()+ ";" + est.getNombreParticipante()+ ";" + est.getNiveMaximoAlcanzado()+";"+ est.getPremio()+System.lineSeparator());
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
    
    public void leerArchivo(String rute) {
        BufferedReader csvReader = null;
        try {
            String ruta = rute; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = null;
            while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                String[] data = fila.split(";");
                reportes.add(new Reporte(data[0], data[1], data[2],data[3])); //crear objeto y agregar a lista

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArchReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArchReporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(ArchReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
