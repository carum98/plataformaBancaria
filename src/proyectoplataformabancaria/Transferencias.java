/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

import java.io.IOException;

/**
 *
 * @author carum_000
 */
public class Transferencias {
    Controlador controlador = new Controlador();
    Pagar pagar = new Pagar();
    
    public void tranferencia(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCliente , int IDCuentaOrigen) throws IOException{
        
        
        int IDClienteOrigen = IDCliente;
        System.out.println(IDClienteOrigen);
        long numeroDeCuenta = Util.longInput("Ingrese el numero de cuenta al cual desea realizar la transferencia");
        
        int IDCuentaDest = 0,IDClienteDest=0;
        System.out.println(Util.campo(cuentaArray));
        for (int i = 0; i < Util.campo(cuentaArray); i++) {
            System.out.println(cuentaArray[i]);
            if (cuentaArray[i].getNumeroDeCuenta() == numeroDeCuenta) {                
                IDCuentaDest = cuentaArray[i].getIDCuenta();
                IDClienteDest = cuentaArray[i].getIDCliente();
                System.out.println("Cuenta "+IDCuentaDest);
                System.out.println("Cliente "+IDClienteDest);
            }
        }
        
        int montoTranfe = Util.intInput("Ingrese el monto que desea transferir");
        
        Util.ventanaMensa("Cuenta Origen \n "+cuentaArray[IDCuentaOrigen]+"\n a Nombre de "+clienteArray[IDClienteOrigen-1].getNombre()+" numero de cedula "+clienteArray[IDClienteOrigen].getCedula()+
        "Cuenta Destino \n"+cuentaArray[IDCuentaDest]+"\n a Nombre de "+clienteArray[IDClienteDest-1].getNombre()+" numero de cedula "+clienteArray[IDClienteDest-1].getCedula());
        
        int confirm = Util.intInput("Confirmar Transferencia \n 1. Si   2. No");
        
        if (confirm == 1) {
            int montoOrigen = cuentaArray[IDCuentaOrigen].getMonto();
            int resta = montoOrigen-montoTranfe;
            cuentaArray[IDCuentaOrigen].setMonto(resta);
            
            int montoDest = cuentaArray[IDCuentaDest].getMonto();
            int suma = montoDest+montoTranfe;
            cuentaArray[IDCuentaDest].setMonto(suma);
            
            String recibo = "Transferenia Numero de cuenta "+cuentaArray[IDCuentaDest].getNumeroDeCuenta()+" a nombre de "+clienteArray[IDClienteDest].getNombre()
                            +" por un monto de "+montoTranfe;
            LogPagos obHisto = new LogPagos(IDClienteOrigen,IDCuentaOrigen,recibo);
            pagar.agregarHistorial(obHisto);
        }
    }
}
