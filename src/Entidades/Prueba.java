/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Prueba {
    public static void main (String args[]){
        
        Calendar c1 = Calendar.getInstance();
        System.out.println(Integer.toString(c1.get(Calendar.DATE)));
        Date fecha = new Date();
        System.out.println(fecha.toString());
    }
}
