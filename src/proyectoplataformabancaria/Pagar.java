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
public class Pagar {
    Controlador controlador = new Controlador();
    
    String[] categorias = {"Agua", "Luz", "TV Cable", "Internet", "Telefonia fija", "Recargas",};
    public void tarjeta(){
        
    }
    
    public void servicio(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCuentaSelec) {
        Util.ventanaMensa("Pagar con la cuenta \n"+cuentaArray[IDCuentaSelec].toString());
        int selec = Util.intInput(Util.listado(categorias));
        int montoPagar = 0;
        Cliente cliente = clienteArray[(cuentaArray[IDCuentaSelec].getIDCliente()-1)];
        
        if ((selec-1)<5) {
            Util.intInput("Ingrese el numero de contrato");
            Util.ventanaMensa("Contrato a nombre de "+cliente.getNombre()+"\n cedula "+cliente.getCedula());
            int opci = Util.intInput("Proceder con el pago \n 1. Si 2. No");
            if (opci == 1) {
//                pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());
            }
        }else if((selec-1)==5){
            Util.intInput("Ingrese el numero telefonico al cual desea recargar");
            montoPagar = Util.intInput("Ingrese el monto que desea recargar");
            int residuo = pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());
            cuentaArray[IDCuentaSelec].setMonto(residuo);
            Util.ventanaMensa("Recarga realizada \n"+cuentaArray[IDCuentaSelec].toString());
        }
    }
    
    public int pago(int montoPagar, int montoCuenta){
        int residuo = montoCuenta-montoPagar;
        return residuo;
    }
}
