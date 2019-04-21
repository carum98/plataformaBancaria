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
public class Cliente {
    private String nombre;
    private String apellido;
    private String correo;
    private long cedula, celular;
    private int IDcliente;

    public Cliente(String nombre, String apellido, String correo, long cedula, long celular, int IDcliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.celular = celular;
        this.IDcliente = IDcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public int getIDcliente() {
        return IDcliente;
    }

    public void setIDcliente(int IDcliente) {
        this.IDcliente = IDcliente;
    }

    @Override
    public String toString() {
        return "cliente{" + "nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", cedula=" + cedula + ", celular=" + celular + ", IDcliente=" + IDcliente + '}';
    }
    
    
}
