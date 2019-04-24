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
    
    public void tranferencia(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCliente) throws IOException{
        
        int IDCuentaOrigen = controlador.mostrarCuenta(IDCliente, true, "Seleccione la cuenta Origen");
        int IDClienteOrigen = clienteArray[IDCuentaOrigen].getIDcliente();
        
        long numeroDeCuenta = Util.longInput("Ingrese el numero de cuenta al cual desea realizar la transferencia");
        
        int IDCuentaDest = 0,IDClienteDest=0;
        for (int i = 0; i < cuentaArray.length; i++) {
            if (cuentaArray[i].getNumeroDeCuenta()==numeroDeCuenta) {
                IDCuentaDest = cuentaArray[i].getIDCuenta();
                IDClienteDest = cuentaArray[i].getIDCliente();
            }
        }
        
        int montoTranfe = Util.intInput("Ingrese el monto que desea transferir");
        
        Util.ventanaMensa("Cuenta Origen \n "+cuentaArray[IDCuentaOrigen]+"\n a Nombre de "+clienteArray[IDClienteOrigen].getNombre()+" numero de cedula "+clienteArray[IDClienteOrigen].getCedula()+
        "Cuenta Destino \n"+cuentaArray[IDCuentaDest]+"\n a Nombre de "+clienteArray[IDClienteDest].getNombre()+" numero de cedula "+clienteArray[IDClienteDest].getCedula());
        
        int confirm = Util.intInput("Confirmar Transferencia \n 1. Si   2. No");
        
        if (confirm == 1) {
            int montoOrigen = cuentaArray[IDCuentaOrigen].getMonto();
            int resta = montoOrigen-montoTranfe;
            cuentaArray[IDCuentaOrigen].setMonto(resta);
            
            int montoDest = cuentaArray[IDCuentaDest].getMonto();
            int suma = montoDest+montoTranfe;
            cuentaArray[IDCuentaDest].setMonto(suma);
        }
    }
}
