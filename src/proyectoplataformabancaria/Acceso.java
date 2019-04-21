/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

/**
 *
 * @author carum_000
 */
public class Acceso {
    private String usuario, clave;
    private int IDAcceso;

    public Acceso(String usuario, String clave, int IDAcceso) {
        this.usuario = usuario;
        this.clave = clave;
        this.IDAcceso = IDAcceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIDAcceso() {
        return IDAcceso;
    }

    public void setIDAcceso(int IDAcceso) {
        this.IDAcceso = IDAcceso;
    }

    @Override
    public String toString() {
        return "Acceso{" + "usuario=" + usuario + ", clave=" + clave + ", IDAcceso=" + IDAcceso + '}';
    }
    
    
    
}
