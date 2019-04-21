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
public class Controlador {
    public Cliente[] clienteArray = new Cliente[30];
    public Acceso[] accesoArray = new Acceso[30];
    public Cuenta[] cuentaArray = new Cuenta[30];
    
    Archivo Archivo = new Archivo();
    
    public void crearCliente() throws FileNotFoundException, IOException{
        Archivo.leer(clienteArray); //Lee TXT Cliente
        Archivo.leer(accesoArray);  //Lee TXT Claves
        Archivo.leer(cuentaArray);  //Lee TXT Cuentas
        
            String nombre = Util.stringInput("Ingrese su nombre");
            String apellido = Util.stringInput("Ingrese su apellido");
            String correo = Util.stringInput("Ingrese su correo electronico");
            long cedula = Util.longInput("Ingrese su numero de cedula");
            long numeroCelular = Util.longInput("Ingrese su numero Telefonico");
                Cliente clienteOb = new Cliente(nombre, apellido, correo, cedula, numeroCelular, Util.campo(clienteArray));   //Se instancia Cliente
                int aux = Util.campo(clienteArray);
                clienteArray[aux] = clienteOb; //Se crea el Objeto en el Array
                    if (Util.campo(clienteArray) == 0) {
                       Archivo.guardar(clienteArray);   //Crear TXT  
                    }
                Archivo.añadir(clienteArray, aux);    //Añade informacion al TXT
            
        Archivo.añadirInicia((Util.campo(clienteArray)),Util.campo(accesoArray),Util.campo(cuentaArray)); //se guarda en TXT el ultimo tamaño del arreglo
        Archivo.leer(clienteArray); //Lee TXT
        
        Util.ventanaMensa("Hola");
        for (int i = 0; i < Util.campo(clienteArray); i++) {
            System.out.println(clienteArray[i]); //Recorre el array para mostrar informacion
        }
    }
    
    public void crearAcceso() throws IOException{
        long cedula = Util.intInput("Ingrese su numero de cedula");
        boolean vali = false;    //Inicializa validacion de usuario
        String usuario="",clave="";
        Archivo.leer(clienteArray); //Lee TXT Cliente
        Archivo.leer(accesoArray);  //Lee TXT Claves
        Archivo.leer(cuentaArray);  //Lee TXT Cuentas
        
        for (int i = 0; i < clienteArray.length; i++) {
            if ((clienteArray[i]!=null) && (cedula == clienteArray[i].getCedula())) { //Se comprueba si el cliente existe
                usuario = Util.stringInput("Crea usuario \n Usuario");
                clave = Util.stringInput("Crea usuario \n Contraseña");
                int IDAcceso = clienteArray[i].getIDcliente();
                Acceso accesoOb = new Acceso(usuario,clave,IDAcceso);   //Se instancia Acceso
                accesoArray[i] = accesoOb; 
                
                Archivo.añadir(accesoArray, i); //Se añade accesos al TXT
                vali = true;
                Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray)); //se guarda en TXT el ultimo tamaño del arreglo
            }
        }
        Archivo.leer(accesoArray);//Lee TXT
        for (int i = 0; i < Util.campo(accesoArray); i++) {
            System.out.println(accesoArray[i]);
        }
        if (vali==false) {
            System.out.println("Usted no es cliente");  //Si el numero de cedula no existe no permite crear Acceso
        }
        }
    
    public void crearCuenta(int IDCliente) throws FileNotFoundException, IOException{
        Archivo.leer(cuentaArray);   //Leer Cuentas
        String[] tipos = {"corriente","ahorros","debito","credito"};
            int selecTipo = Util.intInput("Seleccione un tipo de Cuenta Bancaria \n 1.Corriente 2.Ahorros \n Tarjetas \n 3.Debito 4.Credito ");
            String tipoCuenta = tipos[selecTipo-1];
                int IDCuenta = Util.campo(cuentaArray);
                int monto = (int) (Math.random()*(150000-100000))+100000;
                long numeroDeCuenta = (long) ((Math.random()*(12000-10000))+10000);
                if (tipos[selecTipo-1].equals("credito")) {     //si requiere una tarjeta de credito se establecen los valores de las tarjetas
                        int[] montoCredito = {300000,400000,500000}; 
                        int selec = Util.intInput("Monto tarjeta de la tarjeta de credito \n 1. 300.000  \n 2. 400.000 \n 3. 500.000");
                        monto = montoCredito[selec-1];
                } 
                if(tipos[selecTipo-1].equals("ahorros")){
                        monto = 0;
                }
            Cuenta obCuenta = new Cuenta(IDCliente, IDCuenta, monto, numeroDeCuenta, tipoCuenta);
            cuentaArray[Util.campo(cuentaArray)] = obCuenta;
                System.out.println(Util.campo(cuentaArray));
                Archivo.añadir(cuentaArray, (Util.campo(cuentaArray)-1),true);//Crear Cuenta en TXT
                Archivo.leer(cuentaArray);  //Leer Cuentas
    }
    
    public int mostrarCuenta(int IDCliente) throws FileNotFoundException, IOException{
        Archivo.leer(cuentaArray);  //Leer Cuentas
        int[] IDCuenta = new int[3]; //Array IDCuenta para almacenar los IDCuenta de las diferentess cuentas que puede tener un cliente
        int aux = 0; //auxciliar para para recorrer el arreglo IDCuenta
        int pos[] = new int[3];
        int IDCuentaSelec = 0;
        int selec = 0;
        for (int i = 0; i < Util.campo(cuentaArray); i++) { //Se recorre el array de Cuentas
            if ((cuentaArray[i].getIDCliente()) == IDCliente) {     //IDCliente de la cuenta es igual al IDCliente que se optuvo del usuario y contraseña
                IDCuenta[aux] = cuentaArray[i].getIDCuenta();    //se guarda el IDCuenta
                aux++;
            }
        }
        for (int i = 0; i < Util.campo(clienteArray); i++) {    //Se recorre el array de Clientes
            if (clienteArray[i].getIDcliente() == IDCliente) {
                System.out.println("Bienvenido "+clienteArray[i].getNombre()+" sus cuentas son ");
                for (int j = 0; j < Util.campo(IDCuenta); j++) {
                    if (clienteArray[i].getIDcliente() == IDCliente) {
                        pos[j] = IDCuenta[j];   //se guarda en pos el ID de Cuenta en cada posicion
                        System.out.println("Cuenta "+(j+1)+". "+cuentaArray[ IDCuenta[j] ]);
                    }
                }
                selec = Util.intInput("Seleccione una cuenta");
                System.out.println("Cuenta seleccionada \n"+cuentaArray[ pos[selec-1] ]); //se muestra la cuenta seleccionada
                IDCuentaSelec = cuentaArray[ pos[selec-1] ].getIDCuenta();
//                int modi = Util.intInput("Desea modificar el monto \n 1. Si 2. No");
//                if (modi == 1 && !(cuentaArray[pos[selec-1]].getTipo().equals("credito"))) {
//                    int monto = Util.intInput("Ingrese el nuevo monto");
//                    modificarCuenta(monto, cuentaArray[pos[selec-1]].getIDCuenta()); //parametros monto que se desea ingresar / IDCuenta que se desa modificar
//                }else{
//                    Util.ventanaMensa("Usted no puede modificar el monto");
//                }
            }
        }
        return IDCuentaSelec; //Retorna el ID de la cuenta que se selecciono
    }
    
    public void modificarCuenta(int monto, int IDCuenta) throws FileNotFoundException, IOException{
        Archivo.leer(cuentaArray);
        for (int i = 0; i < Util.campo(cuentaArray); i++) {
            if (cuentaArray[i].IDCuenta == IDCuenta) {
                cuentaArray[i].setMonto(monto);
            }
        }
        boolean aux = false;
        for (int i = 0; i < Util.campo(cuentaArray); i++) {
            Archivo.añadir(cuentaArray, i, aux);
            aux = true;
        }
            Archivo.leer(cuentaArray);
        System.out.println(cuentaArray[IDCuenta]);
    }
    
    public void login() throws FileNotFoundException, IOException{
        Pagar Pagar = new Pagar();
        String usuario, clave;
        boolean accesToken = false;
        Archivo.leer(accesoArray); //lee los accesos del TXT
        Archivo.leer(clienteArray); //Lee TXT Cliente
        do {
            usuario = Util.stringInput("Ingrese su usuario"); //Se solicita usuario
            clave = Util.stringInput("Ingrese su clave");    //Se solicita contraseña
            accesToken = Login.validacion(accesoArray, usuario, clave);  //Ejecuta el metodo loding que regresa un boolean
        } while (accesToken==false);
        int IDClient = 1;
            if ( accesToken == true) {
                System.out.println("Bienvenido a la plataforma web ");
                    int opcion = Util.intInput("Desea crear una cuenta \n 1.Solicitar Cuenta \n 2.Consultar Cuenta \n 3. Transferir \n 4. Pagar");
                    IDClient = Login.informacion(accesoArray, usuario,clave);
                    switch (opcion) {
                    case 1:
                        crearCuenta(IDClient); //crea la cuenta
                        Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray));
                        break;
                    case 2:
                        mostrarCuenta(IDClient); //Muestra las cuentas y solicita seleccionar una
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        int IDCuentaSelec = mostrarCuenta(IDClient); 
                        Pagar.servicio(clienteArray, cuentaArray, IDCuentaSelec);
                                boolean aux = false;
                                for (int i = 0; i < Util.campo(cuentaArray); i++) {
                                Archivo.añadir(cuentaArray, i, aux);
                                aux = true;
        }
                        break;
                    }
            }
        }
    }



