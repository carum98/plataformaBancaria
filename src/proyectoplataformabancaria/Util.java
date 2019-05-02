/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author carum_000
 */
public class Util {
    
        public static String stringInput(String mensaje){                       //Metodo para mostrar una Ventana con input y retorna un String
            return JOptionPane.showInputDialog(mensaje);                        //Ventana con la clase JOptionPane y el metodo showInputDialog
        }
        
        public static int intInput(String mensaje){                             //Metodo para mostrar una Ventana con input y retorna un Integer
            return Integer.parseInt(JOptionPane.showInputDialog(mensaje));      //Ventana con la clase JOptionPane y el metono showInputDiaglos | convierte el resultado de String a Integer
        }
        
        public static long longInput(String mensaje){                           //Metodo para mostrar una Ventana con input y retorna un Long
            return Long.parseLong(JOptionPane.showInputDialog(mensaje));        //Ventana con la clase JOptionPane y el metono showInputDiaglos | convierte el resultado de String a Longer
        }
        public static void ventanaMensa(String mensaje){                        //Metodo pora mostrar una ventana de dialogo
            JOptionPane.showMessageDialog(null, mensaje);                       //Ventana con la clase JOptionPane y el metodo showMessageDialog
            return;
        }

        public static int campo(Cliente[] Array){                               //Metodo que busca la cantidad de campos ocupados en el array de Cliente
        int contador = 0;                                                       //Se inicializa contador
        for (int i = 0; i < Array.length; i++) {                                //Recorre el Array
            if (Array[i]!=null) {                                               //Si el array es diferente a null
                contador++;}}                                                   //Aumenta el contador
        return contador;}                                                       //Retorna contador
        
        public static int campo(Acceso[] Array){                                //Metodo que busca la cantidad de campos ocupados en el array de Accesos
        int contador = 0;                                                       //Se inicializa contador
        for (int i = 0; i < Array.length; i++) {                                //Recorre el Array
            if (Array[i]!=null) {                                               //Si el array es diferente a null
                contador++;}}                                                   //Aumenta el contador
        return contador;}                                                       //Retorna contador
        
        public static int campo(Cuenta[] Array){                                //Metodo que busca la cantidad de campos ocupados en el array de Cuentas
        int contador = 0;                                                       //Se inicializa contador
        for (int i = 0; i < Array.length; i++) {                                //Recorre el Array
            if (Array[i]!=null) {                                               //Si el array es diferente a null
                contador++;}}                                                   //Aumenta el contador
        return contador;}                                                       //Retorna contador
        
        public static int campo(int[] Array){                                   //Metodo que busca la cantidad de campos ocupados en el array con Integers
        int contador = 0;                                                       //Se inicializa contador
        for (int i = 0; i < Array.length; i++) {                                //Recorre el Array
            if (Array[i]!=0) {                                                  //Si el array es diferente a null
                contador++;}}                                                   //Aumenta el contador
        return contador;}                                                       //Retorna contador
        
        public static int campo(LogPagos[] Array){                              //Metodo que busca la cantidad de campos ocupados en el array de Historal de Pagos
        int contador = 0;                                                       //Se inicializa contador
        for (int i = 0; i < Array.length; i++) {                                //Recorre el Array
            if (Array[i]!=null) {                                               //Si el array es diferente a null
                contador++;}}                                                   //Aumenta el contador
        return contador;}                                                       //Retorna contador
        
        public static String listado(String[] Array){                           //Medoto para retornar un String con el formato de Listado | recibe un array con formato String
            String mensaje = "";                                                //Se inicializa la varible mensaje
            for (int i = 0; i < Array.length; i++) {                            //Recorre el array
                mensaje += i+1+". "+Array[i]+"\n";                              //Almacena y concatena cada dato del string con un numero para formar el listado
            }
            return mensaje;                                                     //Retorna un mensaje
        }
        
        public static String listado(int[] Array){                              //Medoto para retornar un String con el formato de Listado | recibe un array con formato de Integer
            String mensaje = "";                                                //Se inicializa la varible mensaje
            for (int i = 0; i < Array.length; i++) {                            //Recorre el array
                mensaje += i+1+". "+Array[i]+"\n";                              //Almacena y concatena cada dato del string con un numero para formar el listado
            }
            return mensaje;                                                     //Retorna un mensaje
        }
        
        public static boolean confirmacion(String mensaje){                     //Metodo para formar un mensaje de confirmacion
            boolean confirm = false;                                            //Se inicializa confirm en false
            int selec;                                                          //Se inicializa selec
                selec = JOptionPane.showConfirmDialog(null, mensaje,"Confirmacion" ,JOptionPane.YES_NO_OPTION); //Se forma ventana showConfirmDialog con el formato YES or NO | retorna 0=Si o 1=No
                    if (selec == 0) {                                           //Si selec es igual a 0  |  SI                                      
                        confirm = true;                                         //Alamcenar true en confirm
                    }else if(selec == 1){                                       //Si selec es igual a 1  |  No
                        confirm = false;                                        //Alamacenar false en confirm
                    }   
            return confirm;                                                     //Retorna confirm
        }
        
        public static void actualizarTXT(Cuenta[] cuentaArray) throws IOException{//Metodo para actualizar archivos TXT 
            boolean auxi = false;                                               //Se inicializa auxi con false para que la primera vez que ingrese al metodo añadir borre todo
            for (int i = 0; i < Util.campo(cuentaArray); i++) {                 //Recorre el array
                Archivo.añadir(cuentaArray, i, auxi);                           //Llama al metodo añadir y le pasa como parametros el array de cuentas, la poscion en el array y el auxiliar
            auxi = true;}                                                       //Cambia el auxiliar para que sobreescriba y no borre las lineas existentes
        }
        
}
