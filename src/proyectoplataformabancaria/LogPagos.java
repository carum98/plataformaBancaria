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
public class LogPagos {
    int IDCliente;
    int IDCuenta;
    String accion;

    public LogPagos(int IDCliente, int IDCuenta, String accion) {
        this.IDCliente = IDCliente;
        this.IDCuenta = IDCuenta;
        this.accion = accion;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public int getIDCuenta() {
        return IDCuenta;
    }

    public void setIDCuenta(int IDCuenta) {
        this.IDCuenta = IDCuenta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "LogPagos{" + "IDCliente=" + IDCliente + ", IDCuenta=" + IDCuenta + ", accion=" + accion + '}';
    }
    
    
}
