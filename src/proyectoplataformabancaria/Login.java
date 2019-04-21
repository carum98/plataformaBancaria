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

    public static boolean validacion(Acceso[] accesoArray, String usuario, String clave){
    boolean accesToken = false; // se inicaliza validacion
            for (int i = 0; i < Util.campo(accesoArray); i++) { //se recorreco el array de Accesos
                if ((usuario.equals(accesoArray[i].getUsuario())) && (clave.equals(accesoArray[i].getClave()))) {   //se comprueba que usuario y contraseña sean validos
                    accesToken = true; 
            }
            }
            if (accesToken == false) {
                Util.ventanaMensa("Usuario o clave incorrecta, intente nuevamente");
            }
        return accesToken;
    }
    
    public static int informacion(Acceso[] accesoArray, String usuario, String clave){
        int IDCliente = 0;
        for (int i = 0; i < Util.campo(accesoArray); i++) { //se recorreco el array de Accesos
                if ((usuario.equals(accesoArray[i].getUsuario())) && (clave.equals(accesoArray[i].getClave()))) {   //se comprueba que usuario y contraseña sean validos
                    IDCliente = accesoArray[i].getIDAcceso(); 
            }
           }
        return IDCliente;
    }
}
