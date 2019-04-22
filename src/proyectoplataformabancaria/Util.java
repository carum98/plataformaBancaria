/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

import javax.swing.JOptionPane;

/**
 *
 * @author carum_000
 */
public class Util {
    
        public static String stringInput(String mensaje){ //devuelve un mensaje qiue retorna un String
            return JOptionPane.showInputDialog(mensaje);
        }
        
        public static int intInput(String mensaje){ //devuelve un mensaje que retorna un int
            return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        }
        
        public static long longInput(String mensaje){   //devuelve un mensaje que retorna long
            return Long.parseLong(JOptionPane.showInputDialog(mensaje));
        }
        public static void ventanaMensa(String mensaje){
            JOptionPane.showMessageDialog(null, mensaje);
            return;
        }

        public static int campo(Cliente[] Array){ //busca la cantidad de campos ocupados en el array
        int contador = 0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=null) {
                contador++;}}
        return contador;}
        
        public static int campo(Acceso[] Array2){
        int contador = 0;
        for (int i = 0; i < Array2.length; i++) {
            if (Array2[i]!=null) {
                contador++;}}
        return contador;}
        
        public static int campo(Cuenta[] Array){
        int contador = 0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=null) {
                contador++;}}
        return contador;}
        
        public static int campo(int[] Array){
        int contador = 0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=0) {
                contador++;}}
        return contador;}
        
        public static int campo(LogPagos[] Array){
        int contador = 0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=null) {
                contador++;}}
        return contador;}
        
        public static String listado(String[] Array){
            String mensaje = "";
            for (int i = 0; i < Array.length; i++) {
                mensaje += i+1+". "+Array[i]+"\n";
            }
            return mensaje;
        }
        
}
