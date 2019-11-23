/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eljosephavila
 */
public class Reporte implements Comparable<Reporte> {
    
    private Date fechaDeljuego;
    private Estudiante  participante;
    private String niveMaximoAlcanzado;
    private String premio;
    private String nombreParticipante;

    public Reporte(Date fechaDeljuego, Estudiante participante, String niveMaximoAlcanzado, String premio) {
        this.fechaDeljuego = fechaDeljuego;
        this.participante = participante;
        this.niveMaximoAlcanzado = niveMaximoAlcanzado;
        this.premio = premio;
    }

    public Reporte(Date fechaDeljuego, String niveMaximoAlcanzado, String premio, String nombreParticipante) {
        this.fechaDeljuego = fechaDeljuego;
        this.niveMaximoAlcanzado = niveMaximoAlcanzado;
        this.premio = premio;
        this.nombreParticipante = nombreParticipante;
    }

    public String getNombreParticipante() {
        return nombreParticipante;
    }

    public void setNombreParticipante(String nombreParticipante) {
        this.nombreParticipante = nombreParticipante;
    }
    

    public Date getFechaDeljuego() {
        return fechaDeljuego;
    }

    public void setFechaDeljuego(Date fechaDeljuego) {
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
