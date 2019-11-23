/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

/**
 *
 * @author eljosephavila
 */
public class Pregunta implements Comparable<Pregunta>{
    private String enunciado;
    private String nivel;
    private String repuesta_correcta;
    private String posible_respuesta1;
    private String posible_respuesta2;
    private String posible_respuesta4;

    public Pregunta(String enunciado, String nivel, String repuesta_correcta, String posible_respuesta1, String posible_respuesta2, String posible_respuesta4) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.repuesta_correcta = repuesta_correcta;
        this.posible_respuesta1 = posible_respuesta1;
        this.posible_respuesta2 = posible_respuesta2;
        this.posible_respuesta4 = posible_respuesta4;
    }

    @Override
    public String toString() {
        return "Pregunta   " + "enunciado=" + enunciado + ", nivel=" + nivel + ", repuesta_correcta=" + repuesta_correcta + ", posible_respuesta1=" + posible_respuesta1 + ", posible_respuesta2=" + posible_respuesta2 + ", posible_respuesta4=" + posible_respuesta4 ;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRepuesta_correcta() {
        return repuesta_correcta;
    }

    public void setRepuesta_correcta(String repuesta_correcta) {
        this.repuesta_correcta = repuesta_correcta;
    }

    public String getPosible_respuesta1() {
        return posible_respuesta1;
    }

    public void setPosible_respuesta1(String posible_respuesta1) {
        this.posible_respuesta1 = posible_respuesta1;
    }

    public String getPosible_respuesta2() {
        return posible_respuesta2;
    }

    public void setPosible_respuesta2(String posible_respuesta2) {
        this.posible_respuesta2 = posible_respuesta2;
    }

    public String getPosible_respuesta4() {
        return posible_respuesta4;
    }

    public void setPosible_respuesta4(String posible_respuesta4) {
        this.posible_respuesta4 = posible_respuesta4;
    }

    @Override
    public int compareTo(Pregunta o) {
        return nivel.compareTo(o.getNivel());
    }

    
        
        
    }
    
    

