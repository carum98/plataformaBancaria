/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author carum_000
 */
public class Pagar {
    LogPagos[] historial = new LogPagos[30];
    Archivo Archivo = new Archivo();
    
    Controlador controlador = new Controlador();
    String[] categorias = {"Agua", "Luz", "TV Cable", "Internet", "Telefonia fija", "Recargas"}, 
            Agua = {"AYA","ESPH"},
            Luz =  {"ICE", "CNFL", "JASEC","ESPH"},
            cableInterFija = {"Kolbi", "Cabletica", "Telecable", "Claro", "Tigo", "Movistar"};
    
    public void tarjeta(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCuentaSelec, int IDCuentaPagar) throws FileNotFoundException, IOException{
        int salPendi = 400000-cuentaArray[IDCuentaSelec].getMonto();
        int selec = Util.intInput("Tarjeta a Pagar ->\n"+cuentaArray[IDCuentaSelec]+"\n Por medio de la cuenta ->"+cuentaArray[IDCuentaPagar]+"\n Saldo Pendiente: "+salPendi+" \nDesea proceder con el pago 1. Si  2. No");
        if (selec == 1) {
            int residuo = pago(salPendi, cuentaArray[IDCuentaPagar].getMonto());
            cuentaArray[IDCuentaPagar].setMonto(residuo);
            cuentaArray[IDCuentaSelec].setMonto(400000);
            
            String recibo = "Pago de tarjeta de credito tarjeta ID "+IDCuentaSelec+ " por un total de "+salPendi;
            LogPagos obHisto = new LogPagos(clienteArray[cuentaArray[IDCuentaSelec].getIDCliente()-1].getIDcliente(), IDCuentaPagar, recibo);
            
            agregarHistorial(obHisto);
//            Archivo.leer(historial);
//            
//            int aux = Util.campo(historial);    //se busca un campo libre en el array
//            historial[aux] = obHisto;   //se almacena el objeto en el array
//            Archivo.añadir(historial,aux, true);
        }
    }
    
    public void servicio(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCuentaSelec) throws FileNotFoundException, IOException {
        int montoPagar = 0, cate, selec;
        String prov;
        Util.ventanaMensa("Pagar con la cuenta \n"+cuentaArray[IDCuentaSelec].toString());
            cate = Util.intInput(Util.listado(categorias));
            cate = cate-1;
                Cliente cliente = clienteArray[(cuentaArray[IDCuentaSelec].getIDCliente()-1)];
        if (cate<5) {
            if (cate == 0) {
                selec = Util.intInput(Util.listado(Agua));
                prov = Agua[selec-1];
                montoPagar = 15000;
            }else if(cate == 1){
                selec = Util.intInput(Util.listado(Luz));
                prov = Luz[selec-1];
                montoPagar = 20000;
            }else{
                selec = Util.intInput(Util.listado(cableInterFija));
                prov = cableInterFija[selec-1];
                if (cate==2) {
                    montoPagar = 8000;}
                else if(cate==3){
                    montoPagar = 12000;}
                else{
                    montoPagar = 6000;}
            }
            
            int contrato = Util.intInput("Ingrese el numero de contrato");
                Util.ventanaMensa(prov+"\n Contrato a nombre de "+cliente.getNombre()+"\n cedula "+cliente.getCedula()+"\n Pago pendiente "+montoPagar);
                int opci = Util.intInput("Proceder con el pago \n 1. Si 2. No");
                int residuo = 0;
            if (opci == 1) {
                residuo = pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());
            }
            cuentaArray[IDCuentaSelec].setMonto(residuo);
            Util.ventanaMensa("Pago realizado \n"+cuentaArray[IDCuentaSelec].toString()); //se confirma pago
            String recibo = "Pago de servicio Proveedor "+prov+" Numero de contrato "+contrato+" Saldo Pagado "+montoPagar; //se crea el recibo para el log
                LogPagos obHisto = new LogPagos(cliente.getIDcliente(),IDCuentaSelec,recibo);   //se inicializa y crea el objeto
                
                agregarHistorial(obHisto);
//            Archivo.leer(historial);
//            int aux = Util.campo(historial);    //se busca un campo libre en el array
//            historial[aux] = obHisto;   //se almacena el objeto en el array
//            Archivo.añadir(historial,aux, true);

        }else if(cate==5){
            Util.intInput("Ingrese el numero telefonico al cual desea recargar");
                montoPagar = Util.intInput("Ingrese el monto que desea recargar");
                    int residuo = pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());
                    cuentaArray[IDCuentaSelec].setMonto(residuo);
            Util.ventanaMensa("Recarga realizada \n"+cuentaArray[IDCuentaSelec].toString());
        }
    }
    
    public int pago(int montoPagar, int montoCuenta){
        if (montoPagar>montoCuenta) {
            Util.ventanaMensa("Saldo insuficinete en la cuenta");
        }
        int residuo = montoCuenta-montoPagar;
        return residuo;
    }
    
    public void agregarHistorial(LogPagos obHisto) throws FileNotFoundException, IOException{
            Archivo.leer(historial);
            
            int aux = Util.campo(historial);    //se busca un campo libre en el array
            historial[aux] = obHisto;   //se almacena el objeto en el array
            Archivo.añadir(historial,aux, true);
    }
    
    public void mostrarHistorial(int IDCliente, int IDCuentaSelec) throws FileNotFoundException{
            Archivo.leer(historial);
            for (int i = 0; i < Util.campo(historial); i++) {
                if ((historial[i].getIDCuenta() == IDCuentaSelec) && (historial[i].getIDCliente()==IDCliente)) {
                    System.out.println(historial[i]);
                }
            }
    }
    
    public int tamano() throws FileNotFoundException{   //Regrese el tamaño de array Historial
        Archivo.leer(historial);
        int tamano = Util.campo(historial)+1;
        return tamano;
    }
}
