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
    LogPagos[] historial = new LogPagos[30];    //Se crea el array historial
    Archivo Archivo = new Archivo();            //Se inicializa el Archivo
    Controlador controlador = new Controlador();//Se inicialzaa controlador
    
    String[] categorias = {"Agua", "Luz", "TV Cable", "Internet", "Telefonia fija", "Recargas"}, //Se inicializa array con las categorias de pago
            Agua = {"AYA","ESPH"},                                                               //Se inicializa array con las proveedores de Agua
            Luz =  {"ICE", "CNFL", "JASEC","ESPH"},                                              //Se inicializa array con las proveedores de Luz
            cableInterFija = {"Kolbi", "Cabletica", "Telecable", "Claro", "Tigo", "Movistar"};   //Se inicializa array con las proveedores de cable, internet y telefonia fija
    
    public boolean tarjeta(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCuentaSelec, int IDCuentaPagar) throws FileNotFoundException, IOException{  //Metodo para pagar las tarjetas de credito | recibe el array de clientes, array de la tajeta que se va a pagar y el ID de la cuenta con la cual se va a pagar
        int[] montoCredito = {300000,400000,500000};                                                    //Se inicliaza el array con los precios de las tarjetas
        int Tar = Util.intInput("Seleccione el monto de la tarjeta \n"+Util.listado(montoCredito));     //Se solicita al usuario el monto de la tarjeta que se va a pagar
        int salPendi = montoCredito[Tar-1]-cuentaArray[IDCuentaSelec].getMonto();                       //Se optiene el saldo pendiente restando el monto de tarjeta de credito menos el monto actual de la cuenta (monto tarjeta - monto actual)                
        Util.ventanaMensa("Tarjeta a Pagar ->"+cuentaArray[IDCuentaSelec]+"\n Por medio de la cuenta ->"+cuentaArray[IDCuentaPagar]+"\n Saldo Pendiente: "+salPendi);  //Se muestra informacion al usuario con respecto a la cuenta con la cual se va a pagar, la tarjeta que va a pagar y el saldo pendiente 
        boolean confirm = Util.confirmacion("Desea proceder con el pago");                                //Se solicita confirmacion para proceder con el pago
        if (confirm) {                                                                                    //Si selec es true
            int residuo = pago(salPendi, cuentaArray[IDCuentaPagar].getMonto());                        //Llama al metodo pagar | le pasa el saldo pendiente y el monto actual de la tarjeta y almacena el retrun en la varible reciduo
            cuentaArray[IDCuentaPagar].setMonto(residuo);                                               //El monto obtenido del metodo pagar (reciduo) llama al setter de la cuenta con la cual se pago la tarjeta y almacena el monto obtenido
            cuentaArray[IDCuentaSelec].setMonto(montoCredito[Tar-1]);                                   //Almacena por medio del metodo setter el monto original ya de la tarjeta
                String recibo = "Pago de tarjeta de credito tarjeta ID "+IDCuentaSelec+ " por un total de "+salPendi;                               //Se crea el recibo para almacenar en el historial de pagos
                LogPagos obHisto = new LogPagos(clienteArray[cuentaArray[IDCuentaSelec].getIDCliente()-1].getIDcliente(), IDCuentaPagar, recibo);   //Se crea objeto obHisto y se envia por medio del constructor el ID del cliente, ID de la cuenta que se pago y el recibo con la informacion
                agregarHistorial(obHisto);                                                               //Llama al metodo agregarHistorial para almacenar en el TXT del historial
        }
        return confirm;
    }
    
    public boolean servicio(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCuentaSelec) throws FileNotFoundException, IOException {   //Metodo para pago de servicios
        int montoPagar = 0, cate, selec;                                        //Se inicializa monto a pgar, cate y selec
        String prov;                                                            //Se inicializa prov
        boolean confirm = true;
        Util.ventanaMensa("Pagar con la cuenta \n"+cuentaArray[IDCuentaSelec].toString());      //Muestra la cuenta con la cual se realizara el pago
            cate = Util.intInput(Util.listado(categorias));                                     //Muestra las categorias de pago, es decir los servicios que puede realizar un pago por ejemplo Luz, Agua, etc..
            cate = cate-1;                                                                      //Auxiliar para almacenar en cate el numero correpondiente del array y no realizar la resta en reiteradas ocaciones
                Cliente cliente = clienteArray[(cuentaArray[IDCuentaSelec].getIDCliente()-1)];  //Se obtiene el ID del cliente que realiza el pago
        if (cate<=4) {                   //Si categoria es menor o igual a 4 (0=Agua, 1=Luz, 2=TV, 3=Internet, 4=Telefonia) muestra la informacion de los proveedores y asigna los montos correspondientes
            if (cate == 0) {             //Agua
                selec = Util.intInput(Util.listado(Agua));                      //Muestra listados de los proveedores de Agua
                prov = Agua[selec-1];                                           //Almacena el nombre del proveedore en la variable prov
                montoPagar = 15000;                                             //Asigna a la varible monto a pagar el monto correpondiente
            }else if(cate == 1){         //Luz
                selec = Util.intInput(Util.listado(Luz));                       //Muestra listado de los proveedores de Luz
                prov = Luz[selec-1];                                            //Almacena el nombre del proveedore en la variable prov
                montoPagar = 20000;                                             //Asigna a la varible monto a pagar el monto correpondiente
            }else{                                                              
                selec = Util.intInput(Util.listado(cableInterFija));            //Muestra los proveedores de Cable, Internet y Telefonia fija
                prov = cableInterFija[selec-1];                                 //Almacena el nombre del proveedore en la variable prov
                if (cate==2) {           //TV Cable
                    montoPagar = 8000;}                                         //Asigna a la varible monto a pagar el monto correpondiente
                else if(cate==3){        //Internet
                    montoPagar = 12000;}                                        //Asigna a la varible monto a pagar el monto correpondiente
                else{                    //Telefonia
                    montoPagar = 6000;}                                         //Asigna a la varible monto a pagar el monto correpondiente
            }
            long contrato = Util.longInput("Ingrese el numero de contrato");    //Solicita el numero de contrato
                Util.ventanaMensa(prov+"\n Contrato a nombre de "+cliente.getNombre()+"\n cedula "+cliente.getCedula()+"\n Pago pendiente "+montoPagar);    //Muestra informacion del pago del servicio
                confirm = Util.confirmacion("Proceder con el pago");    //Solicita confirmacion para proceder con el pago 
                int residuo = 0;                                                //Inicializa residuo
            if (confirm) {                                                      //Si confirm es true
                residuo = pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());                                          //Llama al metodo pagar | le pasa el monto pendiente y el monto actual de la tarjeta y almacena el return en la variable reciduo
                    cuentaArray[IDCuentaSelec].setMonto(residuo);                                                           //Asigna el monto con el rebajo aplicado a la cuenta con la cual se realizo el pago
                    Util.ventanaMensa("Pago realizado \n"+cuentaArray[IDCuentaSelec].toString());                           //Muestra un mensaje con la confirmacion del pago
                    String recibo = "Pago de servicio Proveedor "+prov+" Numero de contrato "+contrato+" Saldo Pagado "+montoPagar; //se crea el recibo para el historial de pagos
                    LogPagos obHisto = new LogPagos(cliente.getIDcliente(),IDCuentaSelec,recibo);                           //Se inicializa y crea el objeto, se pasa por el medio del constructor el ID del cliente, ID de la cuenta seleccionada y el recibo
                agregarHistorial(obHisto);                                                                                  //Se agrega al TXT del historial
            }
        }else if(cate==5){              //Si categoria es igual a 5(5=Recargas)
            Util.intInput("Ingrese el numero telefonico al cual desea recargar");           //Solicita el numero al cual se desea realizar la recarga
                montoPagar = Util.intInput("Ingrese el monto que desea recargar");          //Solicita el monto que desea recargar
                    int residuo = pago(montoPagar, cuentaArray[IDCuentaSelec].getMonto());  //Llama al metodo pagar | le pasa el monto pendiente y el monto actual de la tarjeta y almacena el return en la variable reciduo
                    cuentaArray[IDCuentaSelec].setMonto(residuo);                           //Asigna el monto con el rebajo aplicado a la cuenta con la cual se realizo el pago
            Util.ventanaMensa("Recarga realizada \n"+cuentaArray[IDCuentaSelec].toString());//Muestra mensaje de confirmacion
        }
        return confirm;
    }
    
    public int pago(int montoPagar, int montoCuenta){//Metodo para realizar pagos | recibe el monto a pagar y el monto de la cuenta                                       
        int residuo = 0;
        if (montoCuenta>montoPagar) {                               //Si el monto de la cuenta es mayor al monto de la cuenta
            residuo = montoCuenta-montoPagar;                       //Realiza una resta, monto de la cuenta menos el monto a pagar y lo almacena en la variable reciduo para retornar el resutado como el monto restante que quedo en la cuenta
        }else{                                                      //Sino es decir el monto a pagar es mayor que el monto en la cuenta
           Util.ventanaMensa("Saldo insuficinete en la cuenta");    //Muestra un mensaje que indique que el monto es insuficiente 
           residuo = montoCuenta; }                                 //Almacena en la varable residuo el monto de la cuenta sin ningun rebajo
        return residuo;                                             //Retorna varibla residuo
    }
    
    public void agregarHistorial(LogPagos obHisto) throws FileNotFoundException, IOException{//Metodo para agregar objetos al array al al TXT de Logs
            Archivo.leer(historial);                                //Lee TXT Logs y lo almacena en el array historial
                int aux = Util.campo(historial);                    //se busca un campo libre en el array
                historial[aux] = obHisto;                           //se almacena el objeto en el array
                Archivo.añadir(historial,aux, true);                //Se añade el array historial al TXT
    }
    
    public String mostrarHistorial(int IDCliente, int IDCuentaSelec) throws FileNotFoundException{//Metodo para mostrar el Histrial
        String mensje = "";                                                                                     //Inicializa mensjae para almacenar todo el historial de pagos
            Archivo.leer(historial);                                                                            //Lee TXT Logs y lo almacena en el array historial
            for (int i = 0; i < Util.campo(historial); i++) {                                                   //Recorre el array historial
                if ((historial[i].getIDCuenta() == IDCuentaSelec) && (historial[i].getIDCliente()==IDCliente)) {//Si el ID de la cuenta en el historial es igual al ID de la cuenta seleccionada y el ID del cliente en el historial es igual al ID del cliente que se logeo
                    mensje += historial[i]+"\n";}                                                               //Concatena cada registro que encuentre del historial en la variable mensaje
            }
        return mensje;                                                                                          //Retorna la varible mensaje
    }
    
    public int tamano() throws FileNotFoundException{   //Retorna el tamaño de array Historial
        Archivo.leer(historial);                        //Lee TXT de Logs
        int tamano = Util.campo(historial);             //Llama la metodo campo que retorna la posicion libre en el array historial
        return tamano;                                  //Retorna la variable tamaño con el campo libre en el array historial
    }
}
