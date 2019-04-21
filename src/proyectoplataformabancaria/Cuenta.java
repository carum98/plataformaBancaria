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
public class Cuenta {
    int IDCliente, IDCuenta;
    int monto;
    long numeroDeCuenta;
    String tipo;

    public Cuenta(int IDCliente, int IDCuenta, int monto, long numeroDeCuenta, String tipo) {
        this.IDCliente = IDCliente;
        this.IDCuenta = IDCuenta;
        this.monto = monto;
        this.numeroDeCuenta = numeroDeCuenta;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public long getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(long numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "IDCliente=" + IDCliente + ", IDCuenta=" + IDCuenta + ", monto=" + monto + ", numeroDeCuenta=" + numeroDeCuenta + ", tipo=" + tipo + '}';
    }



}
