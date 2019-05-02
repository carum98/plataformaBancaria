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
    public Cliente[] clienteArray = new Cliente[30];    //Se crea Array de clientes
    public Acceso[] accesoArray = new Acceso[30];       //Se crea Array de Accesos (Contaseña)
    public Cuenta[] cuentaArray = new Cuenta[30];       //Se crea Array de Cuentas
    Archivo Archivo = new Archivo();    //Se instancia la clase Archivo
    public void crearCliente() throws FileNotFoundException, IOException{ //Metodo de creacion de Clientes
        Pagar Pagar = new Pagar();      //Se instancia la clase Pagar
        Archivo.leer(clienteArray);     //Lee TXT Cliente y los carga en clienteArray
        Archivo.leer(accesoArray);      //Lee TXT Claves y los carga en accesoArray
        Archivo.leer(cuentaArray);      //Lee TXT Cuentas y los carga en cuentaArray
            String nombre = Util.stringInput("Ingrese su nombre");              //Solicita el nombre del cliente | almacena en como variable tipo String en nombre
            String apellido = Util.stringInput("Ingrese su apellido");          //Solicita el apellido del cliente | almacena en como variable tipo String en apellido
            String correo = Util.stringInput("Ingrese su correo electronico");  //Solicita el correo del cliente | almacena en como variable tipo String en correo
            long cedula = Util.longInput("Ingrese su numero de cedula");        //Solicita el numero de cedula | almacena como variabgle tipo long en cedula
            long numeroCelular = Util.longInput("Ingrese su numero Telefonico");//Solicita el numero de celular | almacena como variabgle tipo long en numeroCelular
            int IDCliente = Util.campo(clienteArray);                           //Se genera el ID del cliente nuevo
                Cliente clienteOb = new Cliente(nombre, apellido, correo, cedula, numeroCelular, IDCliente);   //Se instancia Cliente clienteOb con el nombre y se envia por constructor Nombre, Apellido, Correo, Cedula, Numero Celular y ID del cliente
                int aux = Util.campo(clienteArray);     //Auxiliar para indicar un campo libre en el Array
                clienteArray[aux] = clienteOb;          //Se crea el Objeto en el Array
                    if (Util.campo(clienteArray) == 0) {//Crea TXT si el array esta en 0
                       Archivo.guardar(clienteArray);   //Crear TXT  
                    }
                Archivo.añadir(clienteArray, aux);      //Añade informacion al TXT
                int logArray = Pagar.tamano();          //Indica el tamaño del arreglo log(donde se encuentran los registros de pago) para agregarlos al archivo inicalizador  
        Archivo.añadirInicia((Util.campo(clienteArray)),Util.campo(accesoArray),Util.campo(cuentaArray),logArray); //Se añade al archivo inicializador el tamaño de los arreglos
    }
    
    public void crearAcceso() throws IOException{   //Metodo de creacion de Cuentas
        Pagar Pagar = new Pagar();                  //Instancia clase Pagar
        long cedula = Util.intInput("Ingrese su numero de cedula");     //Solicita numero de cuenta para saber si ya cuenta con un cliente creado
        boolean vali = false;                       //Inicializa validacion de usuario
        String usuario=" ",clave=" ";               //Se inicializa variables usuario y clave tipo String
        Archivo.leer(clienteArray);                 //Lee TXT Cliente
        Archivo.leer(accesoArray);                  //Lee TXT Claves
        Archivo.leer(cuentaArray);                  //Lee TXT Cuentas
        int logArray = Pagar.tamano();              //Indica el tamaño del arreglo log(donde se encuentran los registros de pago) para agregarlos al archivo inicalizado  
        for (int i = 0; i < clienteArray.length; i++) { //Recorre el Array de clientes
            if ((clienteArray[i]!=null) && (cedula == clienteArray[i].getCedula())) { //Se comprueba si el cliente existe por medio del numero de cedula
                usuario = Util.stringInput("Crea usuario \n Usuario");  //Solicita Usuario Nuevo
                clave = Util.stringInput("Crea usuario \n Contraseña"); //Solicita Contraseña Nueva
                int IDAcceso = clienteArray[i].getIDcliente();          //Crea ID de Acceso
                Acceso accesoOb = new Acceso(usuario,clave,IDAcceso);   //Se instancia Acceso y se envia al constructor Usuario, Contraseña y ID del cliente
                accesoArray[i] = accesoOb;                              //Se agrega Objeto al Array de Acceso
                Archivo.añadir(accesoArray, i);                         //Se añade accesos del cliente al TXT
                vali = true;                                            //Validacion para saber si se creo el acceso
                Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray),logArray); //Se añade al archivo inicializador el tamaño de los arreglos
            }
        }
        if (vali==false) {                              //Comprueba si se creo el acceso
            Util.ventanaMensa("Usted no es cliente");   //Indica que no tiene cliente creado
                boolean confirm = Util.confirmacion("Desea crearse un cliente en nuestra plataforma");  //Se solicita confirmacion para crear cliente
                    if (confirm) {                      //Si confirm es Verdadero
                        crearCliente();                 //Crea el cliente
                    }}
        }
    
    public boolean crearCuenta(int IDCliente) throws FileNotFoundException, IOException{ //Metodo de creacion de cuentas, recibe el IDCliente 
        Archivo.leer(cuentaArray);                                              //Leer TXT de cuentas y lo almacena en el arraay de cuentas
        String[] tipos = {"corriente","ahorros","debito","credito"};            //Se inicializan los tipos de cuentas que se pueden crear
            int selecTipo = Util.intInput("Seleccione un tipo de Cuenta Bancaria \n"+ Util.listado(tipos)); //Se solicita el tipo de cuenta que desea crear
            String tipoCuenta = tipos[selecTipo-1];                             //Se almacena el nombre de tipo String del tipo de cuenta que selecciono el usuario
                int IDCuenta = Util.campo(cuentaArray);                         //Se crea el ID de la Cuenta
                long numeroDeCuenta = (long) ((Math.random()*(12000-10000))+10000);  //Se crea el numero de Cuenta de la cuenta
                int monto = (int) (Math.random()*(150000-100000))+100000;       //Se incializa el monto con un numero Random de 100.000 hasta 150.000 para las cuentas corriente y debito
                    if (tipos[selecTipo-1].equals("credito")) {                 //Si requiere una tarjeta de credito se establecen los valores de las tarjetas
                            int[] montoCredito = {300000,400000,500000};        //Se inicializa los montos de las tarjetas de credito
                                int selec = Util.intInput("Monto tarjeta de la tarjeta de credito"+ Util.listado(tipos));   //Se solicita al usuario que indique el monto de la tarjeta que solicita
                            monto = montoCredito[selec-1];                      //Se almacena el monto con respecto al que solicito el usuario
                    } 
                if(tipos[selecTipo-1].equals("ahorros")){                       //Si el usuario requiere una tarjeta de ahorros
                        monto = 0;                                              //La cuenta inicia con un monto de 0
                }
                boolean cofirm = Util.confirmacion("Desea crear la cuenta");
                if (cofirm) {
                    Cuenta obCuenta = new Cuenta(IDCliente, IDCuenta, monto, numeroDeCuenta, tipoCuenta); //Se instancia la clase Cuenta con el nombre obCuenta y se encia al constructor el ID del cliente, ID de cuenta, el monto de la cuenta, el numero de cuenta y el tipo de cuenta
                    int aux = Util.campo(cuentaArray);                          //Auxiliar para indicar un campo libre en el Array
                    cuentaArray[aux] = obCuenta;                                //Se cra el objeto en el array 
                System.out.println(Util.campo(cuentaArray));
                Archivo.añadir(cuentaArray, (aux),true);                        //Crear Cuenta la cuenta en el TXT | se envia el Array de Cuentas, la pocision de la cuenta y el boolean True para que  no se sobreescriba
                Archivo.leer(cuentaArray);                                      //Leer TXT de las Cuentas
                Util.ventanaMensa(cuentaArray[aux].toString());
                }else{
                    Util.ventanaMensa("Proceso cancelado");
                }
                return cofirm;
    }
    
    public int mostrarCuenta(int IDCliente, boolean mostrar, String mensaje) throws FileNotFoundException, IOException{ //Metodo para mostrar cuentas, recibe el ID del Cliente, un boolean para saber si muestra todas las cuentas o solo las tarjetas y el mensaje que se desea cambiar
        Archivo.leer(cuentaArray);                                          //Leer TXT Cuentas
        int[] IDCuenta = new int[3];                                        //Array IDCuenta para almacenar los IDCuenta de las diferentess cuentas que puede tener un cliente
        int pos[] = new int[3];                                             //Array para saber la pocision que eligo el usuario
        int IDCuentaSelec = 0, selec = 0, aux = 0;                          //Se incializan variables
        String cuentas = "";                                                //Se inicaliza cuentas para almacenar la informacion de las cuentas y mostrarlas en una sola ventana

        for (int i = 0; i <  Util.campo(cuentaArray); i++) {                 //Se recorre el array de Cuentas
            if ((cuentaArray[i].getIDCliente()) == IDCliente) {             //Si el ID del cliente que se registro es igual a ID de las Cuentas
                IDCuenta[aux] = cuentaArray[i].getIDCuenta();               //se guarda el IDCuenta de las cuentas que tiene el cliente
                aux++;}}
        for (int i = 0; i < Util.campo(clienteArray); i++) {                //Se recorre el array de Clientes
            if (clienteArray[i].getIDcliente() == IDCliente) {              //Si el ID del cliente que se registro es igual a ID de las Cuentas
                cuentas = "\n \t Bienvenido "+clienteArray[i].getNombre()+" sus cuentas son \n";
                for (int j = 0; j < Util.campo(IDCuenta); j++) {            //Muestra todas las cuentas
                    if (clienteArray[i].getIDcliente() == IDCliente) {
                        pos[j] = IDCuenta[j];                               //se guarda en pos el ID de Cuenta en cada posicion
                        Cuenta cuenta = cuentaArray[ IDCuenta[j] ];
                        if (mostrar == true) {                              //true muestra todas las cuentas 
                            cuentas = cuentas + ("\n"+(j+1)+". Cuenta: "+cuenta.getTipo()+"        Numero de cuenta: "+cuenta.getNumeroDeCuenta()+"        Saldo: "+cuenta.getMonto());}
                        if(mostrar == false && (cuentaArray[IDCuenta[j]].getTipo().equals("credito"))){ //y false muestra solo las tarjetas
                            cuentas = cuentas + ((j+1)+". Cuenta: "+cuenta.getTipo()+"        Numero de cuenta: "+cuenta.getNumeroDeCuenta()+"        Saldo: "+cuenta.getMonto()+"\n");
                        }}
                    cuentas+="\n";
                }
                selec = Util.intInput(mensaje+"\n"+cuentas);                             //Muestra todas las cuentas del cliente y se solicita una
                Cuenta cuenSelec = cuentaArray[ pos[selec-1] ];
                Util.ventanaMensa("Datos de cuenta seleccionada \n"+"\nNumero de cuenta: "+cuenSelec.getNumeroDeCuenta()+"\nCuenta: "+cuenSelec.getTipo()+"\nSaldo: "+cuenSelec.getMonto()+"\nID del Cliente: "+cuenSelec.getIDCliente()+"\nID de la Cuenta: "+cuenSelec.getIDCuenta()); //se muestra la cuenta seleccionada
                IDCuentaSelec = cuentaArray[ pos[selec-1] ].getIDCuenta();  //Almacena el ID de la cuenta que se selecciono
            }
        }
        return IDCuentaSelec;                                                //Retorna el ID de la cuenta que se selecciono
    }
    
    public void modificarCuenta(int monto, int IDCuenta) throws FileNotFoundException, IOException{ //Metodo para modificar el monto de la cuenta
        Archivo.leer(cuentaArray);                                          //Lee TXT de cuentas y lo almacena en cuentaArray
        for (int i = 0; i < Util.campo(cuentaArray); i++) {                 //Recorre el array de las cuentas
            if (cuentaArray[i].IDCuenta == IDCuenta) {                      //Si el ID de la cuentas Seleccionada es igual al ID encontrado
                cuentaArray[i].setMonto(monto); }}                            //Coloca el monto que se recibio como un parametro
        boolean aux = false;                                                //Se inicializa auxciliar en False para que la primera vez que ingresa al metodo añadir sobreescriba todo el arreglo
        for (int i = 0; i < Util.campo(cuentaArray); i++) {                 //Recorre todo el array de cuentas para almacenarlo en el TXT de cuentas
            Archivo.añadir(cuentaArray, i, aux);                            //Añade al TXT | se envia el array de cuentas, la ubicacion en el array y el auxiliar
            aux = true;}                                                     //Cambia el auxiliar a True para que ya no se sobreescriba
        Archivo.leer(cuentaArray);                                          //Lee TXT de las cuentas
        System.out.println(cuentaArray[IDCuenta]);                          //Muestra la cuenta ya con el monto modificado
    }
    
    public void login() throws FileNotFoundException, IOException{
        Pagar Pagar = new Pagar();                  //Se instancia la clase Pagar
        Transferencias Transferencias = new Transferencias();//Se instancia Transferencia
        Archivo.leer(accesoArray);                  //Lee TXT de Accesos
        Archivo.leer(clienteArray);                 //Lee TXT de Clientes
        Archivo.leer(cuentaArray);                  //Lee TXT de Cuentas
        int logArray = Pagar.tamano();              //Lee el tamaño del array log                  
        int IDCuentaSelec = 0;                      //Inicializa el ID de la cuenta seleccionanda por el usuario
        int IDClient = Login.login(accesoArray);    //Realiza el proceso de Login y Validacion, y retorna el ID del cliente correpondiente a las credenciales de acceso
        boolean complete=false, continuar=false;    
        if ( (IDClient>=0) && (IDClient <= Util.campo(clienteArray))) {                         //Comprueba que el ID del cliente sea valido | Mayor a 0 y Menor que el tamaño de los campos utilizados en el arreglo
            Cliente cliente = clienteArray[IDClient-1];
            Util.ventanaMensa("Bienvenido a la plataforma web \n"+cliente.getNombre()+" "+cliente.getApellido());    //Muestra mensaje de bienvenida al usuario
                String[] opciones = {"Solicitar Cuenta" ,"Consultar Cuenta", "Transferir", "Pagar" , "Historial de Pagos", "Informacion Personal", "Salir"};    //Inicializa las opciones de seleccion
                do{
                int opcion = Util.intInput("Plataforma Bancaria \n"+Util.listado(opciones));    //Solicita al usuario una opocion
                    switch (opcion) {
                    case 1:                         //Opcion 1, Crear cuenta nueva
                        complete = crearCuenta(IDClient);                                                            //Llama al mentodo crerCuenta y le envia como parametro el ID de la cuenta
                        if (complete) {logArray = logArray+1;} 
                        Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray),logArray); //Actualiza el archivo inicializador
                        continuar=Util.confirmacion("Desea continuar");
                        break;
                    case 2:                         //Opcion 2, Mostrar las cuentas que tiene el cliente
                        mostrarCuenta(IDClient, true, "Seleccione la cuenta que desea consultar");        //Muestra las cuentas y solicita seleccionar una para mostrar mas informacion
                        continuar=Util.confirmacion("Desea continuar");
                        break;
                    case 3:                         //Opcion 3, Realizar transferencias a otras cuentas
                        int IDCuentaOrigen = mostrarCuenta(IDClient, true, "Seleccione la cuenta Origen");//Muestra las cuentas del cliente y retorna la cuenta com la cual se desea hacer la tranferencia
                        complete = Transferencias.tranferencia(clienteArray, cuentaArray, IDClient, IDCuentaOrigen); //Llama al metodo Transferencia | se envia el array de aclientes, array de cuentas, ID del cliente y ID de la cuenta que selecciono el usuairo para realizar la transferencia
                        Util.actualizarTXT(cuentaArray);                                                  //Medoto actulizarTXT, recorre todo el TXT y lo actuliza con los cambios realizados en la transferencia
                        if (complete) {logArray = logArray+1;}                                            //Si complete es true suma 1 a logArray, pára aumentar el archivo inicializador
                        Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray),logArray);  //Actualiza el archivo inicializador
                        continuar=Util.confirmacion("Desea continuar");
                        break;
                    case 4:                         //Opcion 4, Realizar pago de servicions o tarjeta
                        int selec = Util.intInput("1. Servicos \t 2. Tarjeta");                             //Se solicita una opcion
                        if (selec == 1) {                                                                   //Si selecciona 1, es para pagar un servicio
                           IDCuentaSelec = mostrarCuenta(IDClient, true, "Seleccione una cuenta");          //Muesta todas las cuentas y retorna la cuenta que seleccione el usuaruio
                           complete = Pagar.servicio(clienteArray, cuentaArray, IDCuentaSelec);             //Llama al metodo sercios | se envia el array de clentes, el array de cuentas y el ID de la cuenta que selecciono
                        }else if(selec == 2){                                                               //Si selecciona 2, es para pagar una tarjeta
                                IDCuentaSelec = mostrarCuenta(IDClient, false, "Seleccione la tarjeta que desea pagar"); //Solicita la tarjeta que se desea pagar
                           int IDCuentaPagar = mostrarCuenta(IDClient, true, "Seleccione la cuenta con la que desea pagar");//Solicta la cuenta con la que desea pagar la tarjeta previamente seleccionada
                           complete = Pagar.tarjeta(clienteArray, cuentaArray, IDCuentaSelec, IDCuentaPagar);         //Llama al metodo tarjeta | se envia el array cliente, array de cuentas, ID de la tarjeta que se desea pagar y el ID de la cuenta con la que se va a pagar
                        } 
                            Util.actualizarTXT(cuentaArray);                                                //Medoto actulizarTXT, recorre todo el TXT y lo actuliza con los cambios realizados en la transferencia
                            if (complete) {logArray = logArray+1;}  
                            Archivo.añadirInicia(Util.campo(clienteArray),(Util.campo(accesoArray)),Util.campo(cuentaArray),logArray);  //Actualiza el archivo inicializador
                            continuar=Util.confirmacion("Desea continuar");
                        break;
                    case 5:                         //Opcion 5, Ver historial de pagos de una cuenta
                        IDCuentaSelec = mostrarCuenta(IDClient,true, "Seleccione la cuenta que desea ver el historial");    //Se solicita una cuenta
                        String historial = Pagar.mostrarHistorial(IDClient, IDCuentaSelec);                                 //Metodo mostrarHistoral | se envia el ID del cliente y el ID de la cuenta selecciona para ver el historial
                        Util.ventanaMensa(historial);
                        continuar=Util.confirmacion("Desea continuar");
                        break;
                    case 6:
                        Util.ventanaMensa("Nombre Completo "+cliente.getNombre()+" "+cliente.getApellido()+"\nNumero de cedula "+cliente.getCedula()+"\nCorreo electronico "+cliente.getCorreo()+"\nNumero de telefonico "+cliente.getCelular());
                        continuar=Util.confirmacion("Desea continuar");
                        break;
                    }
                    }while(continuar);                                          //Hacer mientras continuar sea true
            }
        }
    }



