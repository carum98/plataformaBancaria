/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoplataformabancaria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author carum_000
 */
public class Archivo {
    
    int[] inicia = new int[4];
    
    public void leerInicia() throws FileNotFoundException{
        File file = new File("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\inicia.txt"); //se lee archivo con los valores inicales
        Scanner scanner;
            try{
            scanner = new Scanner(file);
            String linea = scanner.nextLine();
                Scanner delimitar = new Scanner(linea);
                String iniciaCliente = delimitar.next(); //se guarda la valiriable inicial de cliente
                String iniciaClave = delimitar.next();   //se guarda la valiriable inicial de la clave
                String iniciaCuenta = delimitar.next();  //se guarda la valiriable inicial de las cuentas
                String iniciaLogs = delimitar.next();
                    inicia[0] = Integer.parseInt(iniciaCliente);    //se guarda en el array y se convierte a Integer
                    inicia[1] = Integer.parseInt(iniciaClave);      //se guarda en el array y se convierte a Integer
                    inicia[2] = Integer.parseInt(iniciaCuenta);     //se guarda en el array y se convierte a Integer
                    inicia[3] = Integer.parseInt(iniciaLogs);
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void añadirInicia(int valorCliente, int valorClave, int valorCuenta, int valorLog){
        FileWriter flwriter = null;
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\inicia.txt", false);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(valorCliente+" ");   //agrega lo valores iniciales al TXT
            bfwriter.write(valorClave+" ");
            bfwriter.write(valorCuenta+" ");
            bfwriter.write(valorLog+"");
            bfwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if (flwriter != null) {
                try {
                        flwriter.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        }
    }
    
    
    
    public static void guardar(Cliente[] clienteArray) throws IOException{
        FileWriter flwriter = null;      
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\datos.txt");            
            BufferedWriter bfwrite = new BufferedWriter(flwriter);
                bfwrite.write("Nombre , Apellido, Correo, Numero de cedula, Numero de telefono, ID"+"\n");
            bfwrite.close();
        }catch (IOException e){
            e.printStackTrace();
	}finally {
        if (flwriter != null){
        try {
             flwriter.close();
        } catch (IOException e){
            e.printStackTrace();
            }
            }
        }
    }
    
    public void leer(Cliente[] clienteArray) throws FileNotFoundException{
        leerInicia();   //se inicializa el metodo para que lea los valores inicales
        File file = new File("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\datos.txt");
        Scanner scanner;
        try{
            scanner = new Scanner(file);
            for (int i = 0; i < inicia[0]; i++) { //Se establece la cantidad de linea que lee
                String linea = scanner.nextLine(); //se lee la linea
                
                    Scanner delimitar = new Scanner(linea); //se crea deliminador para separar los datos
                      delimitar.useDelimiter("\\s*,\\s*");
                Cliente c = new Cliente(delimitar.next(),delimitar.next(),delimitar.next(),Long.parseLong(delimitar.next()),Long.parseLong(delimitar.next()),Integer.parseInt(delimitar.next()));
                clienteArray[i] = c; //se almacena en el array
           }
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void añadir(Cliente[] clienteArray, int i) throws IOException{
        FileWriter flwriter = null;
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\datos.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);

            bfwriter.write(clienteArray[i].getNombre()+","+clienteArray[i].getApellido()+","+clienteArray[i].getCorreo()+","
                        +clienteArray[i].getCedula()+","+clienteArray[i].getCelular()+","+Util.campo(clienteArray)+"\n");   //busca en el array los objetos que seran agregados al archivo
            bfwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if (flwriter != null) {
                try {
                        flwriter.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        }
    }
    
    
    
    public static void guardar() throws IOException{
        FileWriter flwriter = null;      
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Acceso.txt");            
            BufferedWriter bfwrite = new BufferedWriter(flwriter);
                bfwrite.write("Usuario , Clave, ID"+"\n");
            bfwrite.close();
        }catch (IOException e){
            e.printStackTrace();
	}finally {
        if (flwriter != null){
        try {
             flwriter.close();
        } catch (IOException e){
            e.printStackTrace();
            }
            }
        }
    }
    
    public static void añadir(Acceso[] AccesoArray, int i) throws IOException{
        FileWriter flwriter = null;
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Acceso.txt", true);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(AccesoArray[i].getUsuario()+","+AccesoArray[i].getClave()+","+AccesoArray[i].getIDAcceso()+"\n");
            bfwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if (flwriter != null) {
                try {
                        flwriter.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        }
    }
        
    public void leer(Acceso[] AccesoArray) throws FileNotFoundException{
        leerInicia();  //se inicializa el metodo para que lea los valores inicales
        File file = new File("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Acceso.txt");
        Scanner scanner;
        try{
            scanner = new Scanner(file);
            for (int i = 0; i <inicia[1]; i++) { 
                String linea = scanner.nextLine();
                if (linea != null) {
                    Scanner delimitar = new Scanner(linea);
                      delimitar.useDelimiter("\\s*,\\s*");
                Acceso c = new Acceso(delimitar.next(),delimitar.next(),Integer.parseInt(delimitar.next()));
                AccesoArray[i] = c;
                }
            }
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public static void guardar(Cuenta[] CuentaArray) throws IOException{
    FileWriter flwriter = null;      
    try{
        flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Cuentas.txt");            
        BufferedWriter bfwrite = new BufferedWriter(flwriter);
            bfwrite.write("IDCliente , IDCuenta, Monto, Numero de Cuenta, Tipo de cuenta"+"\n");
        bfwrite.close();
    }catch (IOException e){
        e.printStackTrace();
    }finally {
    if (flwriter != null){
    try {
         flwriter.close();
    } catch (IOException e){
        e.printStackTrace();
        }
        }
    }
    }
    
    public static void añadir(Cuenta[] CuentaArray, int i, boolean reescribir) throws IOException{
        FileWriter flwriter = null;
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Cuenta02.txt", reescribir);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(CuentaArray[i].getIDCliente()+","+CuentaArray[i].getIDCuenta()+","+CuentaArray[i].getMonto()+","+CuentaArray[i].getNumeroDeCuenta()+","+CuentaArray[i].getTipo()+"\n");
            bfwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if (flwriter != null) {
                try {
                        flwriter.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        }
    }
    
    public void leer(Cuenta[] CuentaArray) throws FileNotFoundException{
        leerInicia();  //se inicializa el metodo para que lea los valores inicales
        File file = new File("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Cuenta02.txt");
        Scanner scanner;
        try{
            scanner = new Scanner(file);
            for (int i = 0; i < inicia[2]; i++) { 
                String linea = scanner.nextLine();
                if (linea != null) {
                    Scanner delimitar = new Scanner(linea);
                      delimitar.useDelimiter("\\s*,\\s*");
                Cuenta obCuenta = new Cuenta(Integer.parseInt(delimitar.next()),Integer.parseInt(delimitar.next()),Integer.parseInt(delimitar.next()), Long.parseLong(delimitar.next()), (delimitar.next()));
                CuentaArray[i] = obCuenta;
                }
            }
            scanner.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void guardar(LogPagos[] historial) throws IOException{
    FileWriter flwriter = null;      
    try{
        flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Logs.txt");            
        BufferedWriter bfwrite = new BufferedWriter(flwriter);
            bfwrite.write("IDCliente , IDCuenta, Informacion"+"\n");
        bfwrite.close();
    }catch (IOException e){
        e.printStackTrace();
    }finally {
    if (flwriter != null){
    try {
         flwriter.close();
    } catch (IOException e){
        e.printStackTrace();
        }
        }
    }
    }
    
    public static void añadir(LogPagos[] historial, int i, boolean reescribir) throws IOException{
        FileWriter flwriter = null;
        try{
            flwriter = new FileWriter("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Logs.txt", reescribir);
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
            bfwriter.write(historial[i].getIDCliente()+","+historial[i].getIDCuenta()+","+historial[i].getAccion()+"\n");
            bfwriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        if (flwriter != null) {
                try {
                        flwriter.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        }
    }
    
    public void leer(LogPagos[] historial) throws FileNotFoundException{
    leerInicia();  //se inicializa el metodo para que lea los valores inicales
    File file = new File("C:\\Users\\carum_000\\Desktop\\Cursos\\Nueva Carpeta\\ProyectoPlataformaBancaria\\src\\Logs.txt");
    Scanner scanner;
    try{
        scanner = new Scanner(file);
        for (int i = 0; i < inicia[3]; i++) { 
            String linea = scanner.nextLine();
            if (linea != null) {
                Scanner delimitar = new Scanner(linea);
                  delimitar.useDelimiter("\\s*,\\s*");
            LogPagos obCuenta = new LogPagos(Integer.parseInt(delimitar.next()),Integer.parseInt(delimitar.next()),delimitar.next());
            historial[i] = obCuenta;
            }
        }
        scanner.close();
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }

}
