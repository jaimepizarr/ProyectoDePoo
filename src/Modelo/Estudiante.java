/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author eljosephavila
 */
public class Estudiante {
    private String matricula;
    private String nombre;
    private String email;
    private String paralelo;

    public Estudiante(String matricula, String nombre, String email) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "matricula=" + matricula + ", nombre=" + nombre + ", email=" + email + '}';
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }
    
}
