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
public class Premio {
    private int puntaje;
    private String  recompensa;
    private String nivelAlcanzado;

    public Premio(int puntaje, String recompensa, String nivelAlcanzado) {
        this.puntaje = puntaje;
        this.recompensa = recompensa;
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(String recompensa) {
        this.recompensa = recompensa;
    }

    public String getNivelAlcanzado() {
        return nivelAlcanzado;
    }

    public void setNivelAlcanzado(String nivelAlcanzado) {
        this.nivelAlcanzado = nivelAlcanzado;
    }
    
    
   
    
}
