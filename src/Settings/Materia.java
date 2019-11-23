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
public class Materia {
    private  String nombre;
    private String codigo;
    private int cantidadNiveles;
    private boolean activo = true;

    public Materia(String nombre, String codigo, int cantidadNiveles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidadNiveles = cantidadNiveles;
    }

    public Materia(String codigo) {
        this.codigo = codigo;
    }

    public Materia(){}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidadNiveles() {
        return cantidadNiveles;
    }

    public void setCantidadNiveles(int cantidadNiveles) {
        this.cantidadNiveles = cantidadNiveles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Materia{" + "nombre=" + nombre + ", codigo=" + codigo + ", cantidadNiveles=" + cantidadNiveles + ", activo=" + activo + '}';
    }

    
}
