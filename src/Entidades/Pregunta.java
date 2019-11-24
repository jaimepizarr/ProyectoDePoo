/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author eljosephavila
 */
public class Pregunta implements Comparable<Pregunta>{
    private String enunciado;
    private String nivel;
    private String rCorrecta;
    private String respInc1;
    private String respInc2;
    private String respInc3;

    public Pregunta(String enunciado, String nivel, String rCorrecta, String respInc1, String respInc2, String respInc3) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.rCorrecta = rCorrecta;
        this.respInc1 = respInc1;
        this.respInc2 = respInc2;
        this.respInc3 = respInc3;
    }
    
    @Override
    public String toString() {
        return "Pregunta   " + "enunciado=" + enunciado + ", nivel=" + nivel + ", repuesta_correcta=" + rCorrecta + ", posible_respuesta1=" + respInc1 + ", posible_respuesta2=" + respInc2 + ", posible_respuesta4=" + respInc3 ;
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

    public String getrCorrecta() {
        return rCorrecta;
    }

    public void setrCorrecta(String rCorrecta) {
        this.rCorrecta = rCorrecta;
    }

    public String getRespInc1() {
        return respInc1;
    }

    public void setRespInc1(String respInc1) {
        this.respInc1 = respInc1;
    }

    public String getRespInc2() {
        return respInc2;
    }

    public void setRespInc2(String respInc2) {
        this.respInc2 = respInc2;
    }

    public String getRespInc3() {
        return respInc3;
    }

    public void setRespInc3(String respInc3) {
        this.respInc3 = respInc3;
    }

    @Override
    public int compareTo(Pregunta o) {
        return nivel.compareTo(o.getNivel());
    }

    
        
        
    }
    
    

