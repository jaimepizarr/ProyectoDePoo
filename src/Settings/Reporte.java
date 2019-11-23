/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eljosephavila
 */
public class Reporte implements Comparable<Reporte> {
    
    private String fechaDeljuego;
    private Estudiante  participante;
    private String niveMaximoAlcanzado;
    private String premio;

    public Reporte(String fechaDeljuego, Estudiante participante, String niveMaximoAlcanzado, String premio) {
        this.fechaDeljuego = fechaDeljuego;
        this.participante = participante;
        this.niveMaximoAlcanzado = niveMaximoAlcanzado;
        this.premio = premio;
    }

    public String getFechaDeljuego() {
        return fechaDeljuego;
    }

    public void setFechaDeljuego(String fechaDeljuego) {
        this.fechaDeljuego = fechaDeljuego;
    }

    public Estudiante getParticipante() {
        return participante;
    }

    public void setParticipante(Estudiante participante) {
        this.participante = participante;
    }

    public String getNiveMaximoAlcanzado() {
        return niveMaximoAlcanzado;
    }

    public void setNiveMaximoAlcanzado(String niveMaximoAlcanzado) {
        this.niveMaximoAlcanzado = niveMaximoAlcanzado;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    @Override
    public int compareTo(Reporte o) {
        return fechaDeljuego.compareTo(o.getFechaDeljuego());
    }

    
    
    
    
}
