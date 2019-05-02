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
public class ProyectoPlataformaBancaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {     
      Controlador controllador = new Controlador();
      
      int opcion = Util.intInput("1. Crear Cliente \n 2.Crear Cuenta \n 3. Login \n 4. Salir");
        
      switch(opcion){
          case 1: 
              controllador.crearCliente();
              controllador.crearAcceso();
              controllador.login();
              break;
          case 2:
              controllador.crearAcceso();
              controllador.login();
              break;
          case 3:
              controllador.login();
              break;
      }
    }

}
