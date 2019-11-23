/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesArch;

import Modelo.Paralelo;
import Modelo.Reporte;
import Modelo.Termino;
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
    private ArrayList<Reporte> reportes =new ArrayList<>();


    public ArchReporte() {
    }

    public void generarCsvReporte(Termino termino,Paralelo paralelo, String materia) throws IOException{
        String archivoCSV = "src/archivos/reportes"+materia+"-"+paralelo.getParaleloCod()+ "-"+termino.getAnio()+"-"+termino.getNumero()+"-R"+".csv";
        Writer writer = Files.newBufferedWriter(Paths.get(archivoCSV));
    }
    
    public void actualizarArchivoReporte(String materia,String paralelo,String anio ,String numero) {//// OJO HACER ESTO
        FileWriter writer = null;
        try {
            String ruta = "src/archivos/reportes/"+materia+"-"+paralelo+ "-"+anio+"-"+numero+"-R"+".csv"; //ruta del archivo que se va a leer
            writer = new FileWriter(ruta);
            for (Reporte est :reportes ) {
                writer.write(est.getFechaDeljuego()+ ";" + est.getParticipante().getNombre()+ ";" + est.getNiveMaximoAlcanzado()+";"+ est.getPremio()+System.lineSeparator());
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
    
    public void leerArchivo(Termino termino,Paralelo paralelo, String materia) {
        BufferedReader csvReader = null;
        try {
            String ruta = "src/archivos/reportes/"+materia+"-"+paralelo.getParaleloCod()+ "-"+termino.getAnio()+"-"+termino.getNumero()+"-R"+".csv";; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine();//escapar cabecera de archivo
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
