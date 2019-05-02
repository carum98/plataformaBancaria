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
    
    public boolean tranferencia(Cliente[] clienteArray, Cuenta[] cuentaArray, int IDCliente , int IDCuentaOrigen) throws IOException{  //Metodo para realizar transferencia
        int IDClienteOrigen = IDCliente;                                        //Se almacena el ID Cliente en la variable Cliente Origen para poder visualizarlo de mejor manera
        long numeroDeCuenta = Util.longInput("Ingrese el numero de cuenta al cual desea realizar la transferencia");//Solicita el numero de cuenta a la cual se desea realizar la transferencia
        int IDCuentaDest = 0,IDClienteDest=0, montoTranfe =0;                   //Se inicializa las variables ID Cuenta Destino y ID Cliente Destino
        boolean vali;                                                           //Se inicializa vali
        int montoOrigen = cuentaArray[IDCuentaOrigen].getMonto();               //Se almacena en montoOrigen el monto actual de la cuenta que va a realizar la transferencia

        for (int i = 0; i < Util.campo(cuentaArray); i++) {                     //Recorre el array de cuentas
            if (cuentaArray[i].getNumeroDeCuenta() == numeroDeCuenta) {         //Si el numero de cuenta es igual al numero de cuenta que ingreso el usuario            
                IDCuentaDest = cuentaArray[i].getIDCuenta();                    //Almacena el ID de la cuenta a la cual van a ir dirigidos los fondos
                IDClienteDest = cuentaArray[i].getIDCliente();}                 //Almacena el ID del cliente al cual van a ir dirigios los fondos
        }
        do{
            montoTranfe =Util.intInput("Ingrese el monto que desea transferir");    //Solicita el monto que desea transferir a la cuenta
            vali = false;                                                           //Cambia la variable vali a false
            if (montoTranfe>montoOrigen) {                                          //Si monto a transferir es mayor al monto original de la cuenta
                Util.ventanaMensa("El monto ingresado, supera al monto total en la cuenta, por favor ingrese nuevamente");//Muesta un mensaje de error
                vali = true;}                                                       //Cambia la variable vali para que se repita el loop
        }while(vali);
        
        Util.ventanaMensa("Cuenta Origen \n "+cuentaArray[IDCuentaOrigen]+"\n a Nombre de "+clienteArray[IDClienteOrigen-1].getNombre()+" numero de cedula "+clienteArray[IDClienteOrigen].getCedula()+"\n"+
        "Cuenta Destino \n"+cuentaArray[IDCuentaDest]+"\n a Nombre de "+clienteArray[IDClienteDest-1].getNombre()+" numero de cedula "+clienteArray[IDClienteDest-1].getCedula());//Muestra informacion con respecto a la transferencia
        boolean confirm = Util.confirmacion("Confirmar Transferencia");         //Solicita confirmacion para realizar la transferencia
        if (confirm) {                                                          //Si confirmacion es true
            int resta = montoOrigen-montoTranfe;                                //Se utiliza el operador resta, el monto de la cuenta menos el monto que se desea tranferir
            cuentaArray[IDCuentaOrigen].setMonto(resta);                        //Guarda el resultado con la deduccion del monto a transferir en la cuenta origen (cuenta que realizo la tranferencia) 
            int montoDest = cuentaArray[IDCuentaDest].getMonto();               //Almacena en  montoDest el monto actual de la cuenta a la cual se va a realizar la transferencia
            int suma = montoDest+montoTranfe;                                   //Se utiliza el operador suma, para sumar el monto que se desea tranferir al monto de la cuenta a la cual se desea transferir
            cuentaArray[IDCuentaDest].setMonto(suma);                           //Guarda el resultado con la cantidad que se transfirio en la cuenta a la cual se desea realizar la transferencia
            String recibo = "Transferenia Numero de cuenta "+cuentaArray[IDCuentaDest].getNumeroDeCuenta()+" a nombre de "+clienteArray[IDClienteDest-1].getNombre()
                            +" por un monto de "+montoTranfe;                   //Genera el recibo para guardar en el log de pagos
            LogPagos obHisto = new LogPagos(IDClienteOrigen,IDCuentaOrigen,recibo);//Crea obHisto y le envia por medio del constructor el ID del cliente desde el cual se realizar al transferencia y el ID de la cuenta y tambien el recibo generado con aterioridad
            pagar.agregarHistorial(obHisto);                                    //Llama al metodo agregarHistorial para agrear al TXT de Logs
            
        }else{
            Util.ventanaMensa("Tranferencia cancelada");                        //Si en la validacion selecciono No, muestra el mensaje y cancela la tranferencia
        }
        return confirm;
    }
}
