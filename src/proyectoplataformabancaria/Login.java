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
public class Login {
    public static int login(Acceso[] accesoArray){//Metodo realizar el proceso de Logueo a la plataforma | retorna el ID del cliente 
        String usuario, clave;                                                  //Se inicializa usuario y clave
        boolean accesToken = false;                                             //Se inicializa accesToken en false
        int IDClient = 1;                                                       //Se inicializa IDCliente
        do {                        
            usuario = Util.stringInput("Ingrese su usuario");                   //Se solicita el usuario
            clave = Util.stringInput("Ingrese su clave");                       //Se solicita la contraseña
            accesToken = validacion(accesoArray, usuario, clave);               //Ejecuta el metodo validacion | se pasa como parametros el array de accesos, el usuario ingresado y la contraseña ingresada | el metodo retorna un boolean
        } while (accesToken==false);                                            //Mientras accesToken sea false, para que salga del loop hasta que ingrese un usuario y contraseña validos
        IDClient = Login.IDCliente(accesoArray, usuario,clave);                 //Llama al metodo IDCliente para encontrar el ID del cliente que corespondientes a las credenciales
        return IDClient;                                                        //Retorna el ID del cliente
    }

    public static boolean validacion(Acceso[] accesoArray, String usuario, String clave){//Metodo para la validacion de credencieles de acceso
    boolean accesToken = false;                                                 // se inicaliza validacion en false
            for (int i = 0; i < Util.campo(accesoArray); i++) {                 //se recorreco el array de Accesos
                if ((usuario.equals(accesoArray[i].getUsuario())) && (clave.equals(accesoArray[i].getClave()))) {   //se comprueba que usuario y contraseña sean validos
                    accesToken = true; }                                        //Si son validos almacena True en la variable accesToken
            }
            if (accesToken == false) {                                          //Si accesToken es false
                Util.ventanaMensa("Usuario o clave incorrecta, intente nuevamente");//Indica al cliente que las credenciales son incorectas
            }
        return accesToken;                                                      //Retorna accesToken
    }
    
    public static int IDCliente(Acceso[] accesoArray, String usuario, String clave){
        int IDCliente = 0;                                                      //Se inicializa el ID del cliente
        for (int i = 0; i < Util.campo(accesoArray); i++) {                     //se recorreco el array de Accesos
                if ((usuario.equals(accesoArray[i].getUsuario())) && (clave.equals(accesoArray[i].getClave()))) {   //se comprueba que usuario y contraseña sean validos
                    IDCliente = accesoArray[i].getIDAcceso();}                  //Almacena el IDAcceso (el cual es el mismo que el ID del cliente) en la variable IDCliente
           }
        return IDCliente;                                                       //Retorna IDCliente
    }   
}
