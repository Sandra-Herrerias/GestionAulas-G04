package lectura_aulas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author G4
 */
public class LecturaAulas {

    //Declaramos un objeto Scanner para leer los datos
    public static Scanner sc = new Scanner(System.in);
    public static final int AULA_SIN_ORDENADORES = 0;
    public static final String RUTA_FICHEROS_CLASSROOM = "files/classrooms.csv";
    public static final String RUTA_FICHERO_USUARIOS = "files/users.dat";
    public static final int LONG_ARRAY_USUARIOS = 100;

    public static void main(String[] args) {
        /*MAPEADO DE NAVEGACIÓN
        
        - login()
            -menuTeacher()
                1.Listar todas las clases
                    -leer_archivo();
                2.Crear nueva clase
                    -addRecord();
                3.Modificar la clase
                    -modRecord();
                4.Eliminar la clase
                    -eliminar();
                5.Salir
                    -login();
            -menuAdmin()
                1.Alta usuario
                    -addUser();
                2.Listar todos los usuarios
                    -listarUsers();
                3.Modificar contraseña y usuario
                    -modificarUser();
                4.Eliminar usuario
                    -eliminarUsuario();
                5.Buscar usuario por nombre:
                    filtrarUser(user);
                6.Salir:
                    login();
                    
         */

        System.out.println("");
        System.out.println("+------------------+");
        System.out.println("| GESTIÓN DE AULAS |");
        System.out.println("+------------------+");
        System.out.println("");
        //En caso de no existir users.dat, te lo crea con usr:Admin psw:Admin1
        registrarAdmin();

        //Ejecuta el login, este llama a menuTeacher() o menuAdmin().
        login();
    }

    /**
     * Lee un archivo externo que contiene la gestion de aulas
     */
    public static void leer_archivo() {
        String classroom;
        String id_aula, descripcio_aula;
        int capacitat_aula, num_pc;
        boolean pc_aula, projector_aula, insonoritzada_aula;
        //File: clase que me guarda el fichero
        //classrooms: variable que me apunta al fichero de texto que quiero leer
        File classrooms = new File(RUTA_FICHEROS_CLASSROOM);
        //le decimos que intente hacer el siguiente codigo (abrir el fichero para
        //leerlo porque puede ser que de error)   
        String[] clase;
        
        try {
            Scanner sn = new Scanner(classrooms);
            boolean first_see = true;//variable que usaremos para no imprimir la 
            //cabecera en la primera vuelta del bucle
            while (sn.hasNextLine()) {//mientras haya una siguiente linea que 
                //leer ejecutar el siguiente codigo
                classroom = sn.nextLine();
                clase = classroom.split(",");//separamos String[] por comas para 
                //poder tratar cada valor por separado
                if (!first_see) {
                    //Asignamos el valor de la pos[x] del vector clase[] a una 
                    //variable determinada
                    id_aula = clase[0];
                    descripcio_aula = clase[1];
                    capacitat_aula = Integer.parseInt(clase[2]);
                    pc_aula = Boolean.parseBoolean(clase[3]);
                    num_pc = Integer.parseInt(clase[4]);
                    projector_aula = Boolean.parseBoolean(clase[5]);
                    insonoritzada_aula = Boolean.parseBoolean(clase[6]);
                    //Imprimimos por pantalla
                    System.out.println("Clase: " + id_aula);
                    System.out.println("Descripción: " + descripcio_aula);
                    System.out.println("Capacidad de aula: " + capacitat_aula);
                    System.out.println("Tiene Ordenador: " + pc_aula);
                    System.out.println("Numero de ordenador: " + num_pc);
                    System.out.println("Tiene projector: " + projector_aula);
                    System.out.println("Aula insonorizada: " + insonoritzada_aula);
                    System.out.println("");
                }
                first_see = false;
            }
            sn.close();//cerramos escaner   
        } catch (Exception e) {//CATCH: capturar posibles errores
            //Exception e: Cualquier tipo de error (no especifico) lo detecta
            System.out.println("No se lee el archivo.");//mostramos mensaje de error
            e.printStackTrace();
        }
    }

    /**
     * Funcion que permite crear a al usuario un nuevo registro de aula.
     */
    public static void addRecord() {

        File file_classrooms = new File(RUTA_FICHEROS_CLASSROOM);

        try {
            Scanner leerFichero = new Scanner(file_classrooms);
            String id_aula, descripcio_aula, pcAulaStr, projectorAulaStr, insonoritzadaAulaStr;
            int capacitat_aula, num_pc;
            boolean pc_aula = true, projector_aula = true, insonoritzada_aula = true;

            //Nueva entrada de id_aula
            System.out.println("Introduce el id de el Aula: ");
            System.out.println("*(Debe empezar con una letra y acabar con 2 dígitos)*");
            System.out.print(">");
            id_aula = getIdAulaNuevo();

            //Nueva entrada de descripcio_aula
            System.out.println("Introduce la descripcion de el Aula: ");
            System.out.println("*(Debe empezar con una letra y acabar con 2 dígitos)*");
            System.out.print(">");
            descripcio_aula = nuevaEntradaLetrasyNums();

            //Nueva entrada de capacitat_aula
            System.out.println("Introduce la capacidad de el Aula: ");
            System.out.print(">");
            capacitat_aula = nuevaEntradaDatosNums();

            //Nueva entrada de pc_aula 
            System.out.println("Introduce si el Aula tiene ordenadores o no: ");
            System.out.print(">");
            pc_aula = nuevaEntradaDatosBoolean();

            if (pc_aula == false) {
                num_pc = AULA_SIN_ORDENADORES;
            } else {
                //Nueva entrada de num_pc
                System.out.println("Introduce el numero de ordenadores que "
                        + "tiene el Aula: ");
                System.out.print(">");
                num_pc = nuevaEntradaDatosNums();
            }

            //Nueva entrada de projector_aula           
            System.out.println("Introduce si el Aula tiene proyector o no: ");
            System.out.print(">");
            projector_aula = nuevaEntradaDatosBoolean();

            //Nueva entrada de insonoritzada_aula
            System.out.println("Introduce si el Aula está insonorizada o no: ");
            System.out.print(">");
            insonoritzada_aula = nuevaEntradaDatosBoolean();

            // El true al final indica que escribiremos al final del fichero 
            //añadiendo texto
            FileWriter nuevaLinea = new FileWriter(file_classrooms, true);
            //Se secribe la nueva entrada del resto de atributos en el fichero     
            nuevaLinea.write(id_aula + "," +descripcio_aula + "," + capacitat_aula + ","
                    + pc_aula + "," + num_pc + "," + projector_aula + ","
                    + insonoritzada_aula + "\n");
            nuevaLinea.flush();//limpia la memoria del writer

            nuevaLinea.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al añadir el aula");
        }

    }

    //--------------------------------
    // VALIDACIONES FUNCION (addRecord)
    //--------------------------------
    /**
     * Valida si el String es un numero (puede ser de 1 o mas digitos)o no
     *
     * @param cadena Comprobar que el String solo contenga numeros.
     * @return true si es un numero / false si no lo es
     */
    public static boolean validarStringNum(String cadena) {
        //validamos que sea un numero comparando con un patron 
        //hecho con expresiones regulares
        if (cadena.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Convierte un String a Int para poder seguir tratando con el tipo de datos
     * que queremos, una vez hemos hecho la validacion
     *
     * @param numero String que se convierte a Int.
     * @return numero convertido a tipo Int
     */
    public static int convertirStringaInt(String numero) {
        return Integer.parseInt(numero);
    }

    /**
     * Valida si empieza por lo menos por una letra mayúscula o minúscula y que
     * acabe con dos dígitos
     *
     * @param cadena Un String con formato descripción.
     * @return true si es valido / false si no lo es
     */
    public static boolean validarIdDescripcio(String cadena) {
        //validamos que los caracteres sean validos comparando con un patron 
        //hecho con expresiones regulares
        if (cadena.matches("^[a-zA-Z]+[0-9][0-9]$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Valida si el String tiene los caracteres validos para este campo
     *
     * @param cadena Valor Booleano de classroom.csv .
     * @return true si contiene si / false si contiene no.
     */
    public static boolean validarSiNo(String cadena) {
        //validamos que los caracteres introducidos sean validos 
        if (cadena.matches("(si|no|SI|NO)")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Crea una nueva entrada de id_aula y descripcio_aula validados
     *
     * @return dato de tipo String introducido (ya validado)
     */
    public static String nuevaEntradaLetrasyNums() {
        String dadaEntradaStr = sc.next(); 
        boolean esValido;
        do {
            //validaciones 
            esValido = validarIdDescripcio(dadaEntradaStr);

            if (esValido == false) {
                System.out.println("Se han introducido caracteres erróneos");
                System.out.println("Introduce caracteres correctos");
                dadaEntradaStr = sc.next();
            }
        } while (esValido == false);
        return dadaEntradaStr;
    }

    /**
     * Comprueba si el aula ya existe en el fichero
     *
     * @param dadaEntradaStr La id del aula a comprobar.
     * @return True si el aula existe y false si no existe.
     */
    public static boolean existeIdAula(String dadaEntradaStr) {
        File file_classrooms = new File(RUTA_FICHEROS_CLASSROOM);
        //Accedemos al fichero que queremos leer

        boolean resultat = false;
        try {
            Scanner lectura = new Scanner(file_classrooms);

            //Lee cada linea del fichero y la añade en un ArrayList
            while (lectura.hasNext() && resultat == false) {
                String linea = lectura.next();
                if (linea.startsWith(dadaEntradaStr)) {
                    resultat = true;
                }
            }
            lectura.close();
        } catch (Exception e) {
            System.out.println("Error, no se puede comprobar si el aula existe");
        }
        return resultat;
    }

    /**
     * Añade una nueva aula en el caso que no exista ya en el archivo
     * @return id del aula nueva
     */
    public static String getIdAulaNuevo() {

        String id_aula = nuevaEntradaLetrasyNums();
        try {
            while (existeIdAula(id_aula)) {
                System.out.println("El aula ya existe");
                System.out.println("Introduce un id de Aula nuevo: ");
                id_aula = nuevaEntradaLetrasyNums();
            }
        } catch (Exception e) {
            System.out.println("No se ha encontrado ningun fichero");
        }
        
        return id_aula;
    }

    /**
     * Crea una nueva entrada de datos numericos
     *
     * @return dato numerico (ya validado)
     */
    public static int nuevaEntradaDatosNums() {
        int numEntrada = -1;
        String dadaEntradaStr = sc.next();
        boolean esNumero;
        do {
            esNumero = validarStringNum(dadaEntradaStr);

            if (esNumero) {
                numEntrada = Integer.parseInt(dadaEntradaStr);
            } else {
                System.out.println("Se han introducido caracteres incorrectos");
                System.out.println("Introduce caracteres numéricos positivos");
                dadaEntradaStr = sc.next();
            }
        } while (esNumero == false);

        return numEntrada;
    }

    /**
     * Crea una nueva entrada de datos de tipo Boolean
     * @return dato de tipo Boolean (ya validado)
     */
    public static boolean nuevaEntradaDatosBoolean() {
        boolean dadaEntrada;
        String dadaEntradaStr = sc.next();
        do {
            if (!validarSiNo(dadaEntradaStr)) {
                System.out.println("Caracteres erroneos, sólo se aceptan (si|SI|no|NO)");
                System.out.println("Introduce caracteres correctos");
                dadaEntradaStr = sc.next();
            }
        } while (!validarSiNo(dadaEntradaStr));

        if ("si".equalsIgnoreCase(dadaEntradaStr)) {
            dadaEntrada = true;
        } else { //Siempre sera no porque se ha forzado en el do while.
            dadaEntrada = false;
        }

        return dadaEntrada;
    }

    /**
     * Funcion que modifica los registros de una clase y actualiza el fichero
     */
    public static void modRecord() {

        //LECTURA DE ARCHIVO
        File file_classrooms = new File(RUTA_FICHEROS_CLASSROOM);

        //ASIGNACION DE VARIABLES
        ArrayList<String> classroom_info = new ArrayList<>();
        String aula = null, lineaAula = null, valor;
        String[] aulaUpdate = null;
        boolean laClaseExiste = false, claseRepetida = false;
        boolean error = false;
        int opcion_menu = 0, valornum;

        //VARIABLES DE LA TABLA
        String id_aula = null, descripcio_aula = null;
        int capacitat_aula = 0, num_pc = 0;
        boolean pc_aula = false, projector_aula = false, insonoritzada_aula = false;

        //VOLCANDO DEL FICHERO A LA MEMORIA
        classroom_info = fileToArrayList(file_classrooms, classroom_info);

        //BLOQUE DE EDICIÓN
        try {
            Scanner sc = new Scanner(System.in);

            //MODIFICACIÓN DEL AULA SELECCIONADA
            do {
                //Introducimos el aula a modificar.
                System.out.print("Introduce el aula a modificar: ");
                aula = sc.next();

                //Recorremos el ArrayList
                for (String classroom : classroom_info) {
                    //Si el aula coincide con el id_aula del archivo lo registrará 
                    //en variables.
                    if (aula.equals(classroom.substring(0, classroom.indexOf(",")))) {
                        laClaseExiste = true;
                        lineaAula = classroom;
                        aulaUpdate = lineaAula.split(",");
                        id_aula = aulaUpdate[0];
                        descripcio_aula = aulaUpdate[1];
                        capacitat_aula = Integer.parseInt(aulaUpdate[2]);
                        pc_aula = Boolean.parseBoolean(aulaUpdate[3]);
                        num_pc = Integer.parseInt(aulaUpdate[4]);
                        projector_aula = Boolean.parseBoolean(aulaUpdate[5]);
                        insonoritzada_aula = Boolean.parseBoolean(aulaUpdate[6]);
                    }
                }

                //Si no ha encontrado ninguna coincidencia en el loop anterior
                //la clase no existe por lo tanto no se podrá editar nada.
                if (!laClaseExiste) {
                    System.out.println("ERROR: La clase introducida no existe!");
                } else {
                    //Si la clase existe, se abre el menú de edición.
                    //Insertamos el codigo del campo a modificar.
                    do {
                        System.out.println("\nAULA SELECCIONADA: " + aula);
                        System.out.println("CODIGO\tDESCRIPCION");
                        System.out.println("1\tid_aula\n"
                                + "2\tdescripcio_aula\n"
                                + "3\tcapacitat_aula\n"
                                + "4\tpc_aula(si|no)\n"
                                + "5\tnum_pc\n"
                                + "6\tprojector_aula(si|no)\n"
                                + "7\tinsonoritzada_aula(si|no)\n"
                                + "0\tACEPTAR Y ACTUALIZAR\n");
                        System.out.print("Codigo: ");
                        valor = sc.next();
                        if (validarStringNum(valor)) {
                            opcion_menu = convertirStringaInt(valor);
                        } else {
                            opcion_menu = 99;
                        }

                        //MENU
                        switch (opcion_menu) {
                            //MODIFICAR id_aula
                            case 1:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                id_aula = writeUniqueID(claseRepetida, valor,
                                        id_aula, classroom_info);
                                break;
                            //MODIFICAR descripcio_aula
                            case 2:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                descripcio_aula = writeCorrectDesc(valor,
                                        descripcio_aula);
                                break;
                            //MODIFICAR capacitat_aula
                            case 3:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                capacitat_aula = writeCorrectNum(valor,
                                        capacitat_aula);
                                break;

                            //MODIFICAR pc_aula
                            case 4:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                pc_aula = writeCorrectBoolean(valor, pc_aula);

                                break;
                            //MODIFICAR num_pc
                            case 5:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                num_pc = writeCorrectNum(valor, num_pc);
                                break;
                            //MODIFICAR projector_aula
                            case 6:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                projector_aula = writeCorrectBoolean(valor,
                                        projector_aula);
                                break;
                            //MODIFICAR insonoritzada_aula
                            case 7:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                insonoritzada_aula = writeCorrectBoolean(valor,
                                        insonoritzada_aula);
                                break;
                            //APLICAR MODIFICACIONES
                            case 0:
                                System.out.println("\nActualizando...");
                                break;
                            //OPCION INCORRECTA
                            default:
                                System.out.println("ERROR: OPCIÓN INCORECTA.\n<Presione Enter>");
                                try {
                                    System.in.read();
                                } catch (Exception e) {
                                }
                                break;
                        }//END switch
                    } while (opcion_menu != 0);//END do
                } //END else
            } while (!laClaseExiste);
            
            //ACTUALIZANDO EL ARCHIVO
        writeFile(file_classrooms, classroom_info, aula, laClaseExiste, error,
                id_aula, descripcio_aula, capacitat_aula, pc_aula, num_pc,
                projector_aula, insonoritzada_aula);

        } catch (Exception e) {

            System.out.println("¡HA OCURRIDO UN ERROR!");
            System.out.println("POSIBLEMENTE EL VALOR INTRODUCIDO NO SEA CORRECTO.");
            System.out.println("EL REGISTRO NO SE HA MODIFICADO");
            error = true;
        }

        

    }

    /**
     * Elimina el registro de un aula en el fichero classroom.csv
     */
    public static void eliminar() {
        File file_classrooms = new File(RUTA_FICHEROS_CLASSROOM);

        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> classroom_info = new ArrayList<>();
        String aula;
        boolean laClaseExiste = false;
        String lineaBorrar = null;

        // Abrimos el fichero de texto para leerlo en memoria
        try {

            Scanner leerFichero = new Scanner(file_classrooms);

            while (leerFichero.hasNext()) {
                classroom_info.add(leerFichero.next());
            }

            leerFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir o leer el fichero");
        }

        //Array para eliminar la linea.
        try {
            sc = new Scanner(System.in);

            //Introducimos el aula a modificar.
            System.out.print("Introduce el aula a eliminar: ");
            aula = sc.next();

            //Vemos si la clase existe o no existe
            for (String classroom : classroom_info) {
                //Si existe guardamos los datos del aula en sus variables.
                if (aula.equals(classroom.substring(0, classroom.indexOf(",")))) {
                    laClaseExiste = true;
                    lineaBorrar = classroom;

                    System.out.println("La linea ha sido eliminada");
                    

                } else {
                    System.out.println("");
                }

            }

        } catch (Exception e) {
            System.out.println("El aula que ha introducido no existe");
        }
        try {
            if (laClaseExiste) {
                FileWriter writer = new FileWriter(file_classrooms);
                for (String classroom : classroom_info) {
                    if (!lineaBorrar.equals(classroom)) {
                        writer.write(classroom + "\n");

                    }

                }
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al sobreescribir el fichero");
        }

    }

    //FUNCIONES PARA ID_AULA
    /**
     * checkUniqueID() Revisa que la id introducida no existe en la ArrayList.
     *
     * @param valor String id_aula
     * @param classroom_info ArrayList
     * @return boolean claseRepetida
     */
    public static boolean checkUniqueID(String valor, ArrayList<String> classroom_info) {
        boolean claseRepetida = false;
        for (String classroom : classroom_info) {
            if (valor.equals(classroom.substring(0, classroom.indexOf(",")))) {
                System.out.println("\nERROR:EL AULA YA EXISTE.\n<Presione Enter>");
                claseRepetida = true;
                try {
                    System.in.read();
                } catch (Exception e) {
                }
            }
        }
        return claseRepetida;
    }

    /**
     * writeUniqueID() Escribe una id correcta y única en una variable.
     *
     * @param claseRepetida boolean checkUniqueID()
     * @param valor String id_aula (temporal)
     * @param id_aula String id_aula (actual)/String valor[Si es nuevo registro]
     * @param classroom_info ArrayList
     * @return String id_aula (actualizada)/(nueva)
     */
    public static String writeUniqueID(boolean claseRepetida, String valor,
            String id_aula, ArrayList<String> classroom_info) {

        claseRepetida = checkUniqueID(valor, classroom_info);
        if (!claseRepetida) {
            id_aula = valor;
        }
        claseRepetida = false;

        return id_aula;
    }

    //FUNCIONES PARA DESCRIPCIO_AULA
    /**
     * writeCorrectDesc() Escribe una descripción correcta
     *
     * @param valor String descripcio_aula(temporal)
     * @param descripcio_aula String
     * descripcio_aula(actual)/descripcio_aula(temporal)[Si es nuevo registro]
     * @return String descripcio_aula(actualizada)/(nueva)
     */
    public static String writeCorrectDesc(String valor, String descripcio_aula) {
        if (validarIdDescripcio(valor)) {
            descripcio_aula = valor;
        } else {
            System.out.println("ERROR: SOLO FORMATO 'Ddddd99' ACEPTADO\n<Presione Enter>");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
        return descripcio_aula;
    }

    //FUNCIONES PARA NUMEROS
    /**
     * writeCorrectNum() Escribe un valor numérico correcto.
     *
     * @param valor int valor_numerico(temporal)
     * @param to_return int valor_numerico(actual)/int valor [Si es nuevo
     * registro]
     * @return int valor_numerico(actualizado)/(nuevo)
     */
    public static int writeCorrectNum(String valor, int to_return) {
        if (validarStringNum(valor)) {
            to_return = convertirStringaInt(valor);
        } else {
            System.out.println("\nERROR:VALOR INCORRECTO INSERTADO.\n<Presione Enter>");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
        return to_return;
    }

    //FUNCIONES PARA BOOLEANOS
    /**
     * writeCorrectBoolean() Escribe un boolean correcto
     *
     * @param valor boolean valor(temporal)"si"/"no"
     * @param to_return boolean valor(actual)/valor (temporal)[Si es nuevo
     * registro]
     * @return valor "true"/"false"
     */
    public static boolean writeCorrectBoolean(String valor, boolean to_return) {
        if (valor.equals("si") | valor.equals("no")) {
            if (valor.equals("si")) {
                to_return = true;
            } else if (valor.equals("no")) {
                to_return = false;
            }
        } else {
            System.out.println("\nERROR: VALOR INCORRECTO, SOLO SI O NO.\n<Presione Enter>");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
        return to_return;
    }

    //FUNCION ESCRIBIR ARCHIVO
    /**
     * fileToArrayList() Añade las lineas de un fichero a una ArrayList
     *
     * @param file_classrooms Variable del archivo cargado
     * @param classroom_info Variable de la ArrayList
     * @return ArrayList con las lineas del fichero
     */
    public static ArrayList<String> fileToArrayList(File file_classrooms,
            ArrayList<String> classroom_info) {
        try {
            Scanner leerFichero = new Scanner(file_classrooms);

            while (leerFichero.hasNext()) {
                classroom_info.add(leerFichero.next());
            }

            leerFichero.close();
        } catch (Exception e) {
            System.out.println("¡HA OCURRIDO UN ERROR!");
        }
        return classroom_info;
    }

    /**
     * writeFile() Escribe los registros alterados en memoria al archivo. NOTA:
     * ESTA FUNCION ELIMINA EL ARCHIVO ANTES DE ESCRIBIR
     *
     * @param file_classrooms Archivo classroom.
     * @param classroom_info Array list de classroom.
     * @param aula String de aula.
     * @param laClaseExiste Boolean si la clase existe o no
     * @param error Boolean si encuentra un error.
     * @param id_aula String Id aula.
     * @param descripcio_aula String descripcion aula.
     * @param capacitat_aula Int capacitat aula.
     * @param pc_aula Boolean oc aula.
     * @param num_pc Int numero pc.
     * @param projector_aula Boolean projector aula.
     * @param insonoritzada_aula Boolean insonoritzacion del aula.
     */
    public static void writeFile(File file_classrooms, ArrayList<String> classroom_info,
            String aula, boolean laClaseExiste, boolean error, String id_aula,
            String descripcio_aula, int capacitat_aula, boolean pc_aula, int num_pc,
            boolean projector_aula, boolean insonoritzada_aula) {
        try {
            FileWriter writer = new FileWriter(file_classrooms);

            //Recorremos la ArrayList
            for (String classroom : classroom_info) {
                //Si id_aula coincide con el aula modificada actualiza los campos
                if (aula.equals(classroom.substring(0, classroom.indexOf(",")))
                        && laClaseExiste && !error) {

                    writer.write(id_aula
                            + "," + descripcio_aula
                            + "," + capacitat_aula
                            + "," + pc_aula
                            + "," + num_pc
                            + "," + projector_aula
                            + "," + insonoritzada_aula + "\n");
                    //Si no coincide utiliza los datos de la ArrayList
                } else {
                    writer.write(classroom + "\n");
                }
            }
            //Si el programa finaliza sin problemas.
            if (laClaseExiste && !error) {
                System.out.println("\nREGISTRO ACTUALIZADO CON ÉXITO\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al escribir el archivo");
        }
    }

    /**
     * Genera el archivo users.dat con un Administrador por defecto si no existe
     * el archivo
     */
    public static void registrarAdmin() {
        //CREAR/GUARDAR FICHERO BINARIO

        try {
            //SI NO EXISTE EL ARCHIVO BINARIO, LO CREA.
            File ficheroUsers = new File(RUTA_FICHERO_USUARIOS);
            boolean existe = ficheroUsers.exists();
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO_USUARIOS, true));

            //CREAMOS UN ARRAY DE USUARIOS
            //Por defecto todas las posiciones del array valen null.
            User[] users = new User[LONG_ARRAY_USUARIOS];//TODO

            //PASAMOS DEL OBJETO BINARIO AL ARRAY.
            try {
                ObjectInputStream Openfichero = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_USUARIOS));
                users = (User[]) Openfichero.readObject();
                Openfichero.close();
            } catch (Exception e) {
            }

            //CREAMOS EL ADMIN
            if (!existe) {
                users[0] = new User();
                users[0].rol = "admin";
                System.out.println("***********************************");
                System.out.println("Bienvenido nuevo usuario.");
                System.out.println("Registre su cuenta de Administrador.");
                System.out.println("************************************\n");
                System.out.println("Introduce el nombre del Administrador");
                System.out.print("> ");
                String nombreUser = sc.next();
                users[0].nombre = nombreUser;
                System.out.println("Introduce la contraseña: ");
                System.out.print("> ");
                String Password = sc.next();
                users[0].password = Password;
                System.out.println("\nUsuario registrado correctamente."
                        + "\nIniciando el programa.");
            }

            //Con WriteObject escribimos directamente todo el array de empleados
            fichero.writeObject(users);

            //Cerramos el fichero
            fichero.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el fichero user.dat");
        }

    }

    /**
     * Funcion que añade un nuevo usuario al fichero
     */
    public static void addUser() {
        //Agafem la info del fitxer per a reutilitzarla un cop buidem 
        //el fitxer i el tornem a sobreescriure
        User[] users = cargarArrayUser();

        //si l'array users es null significa que no existeix l'array
        if (users != null) {

            //buscar la última posicio disponible
            //ALGORITMES DE CERCA
            //mentre no hagis arribat al final de l'array
            //i mentre hi hagi objectes en les posicions de l'array
            int i = 0;
            while (i < users.length && users[i] != null) {
                i++;
            }

            //Llegir un nou empleat i afegirlo en la ultima posicio disponible
            //Si hem trobat una posicio valida de l'array (posicio nula) guardem el nou empleat
            if (i < users.length) {
                users[i] = introducirUser();
                guardarArrayUsers(users);
            } else {
                System.out.println("No se admiten más usuarios, array lleno");
            }
        }
    }

    /**
     * Funcion que retorna el array guardado en el fichero
     *
     * @return array guardado en el fichero
     */
    public static User[] cargarArrayUser() {
        User[] resultat = null;
        try {
            //Carrrega el fitxer
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_USUARIOS));

            //Llegeix l'objecte i me'l carrega a la variable resultat
            resultat = (User[]) input.readObject();

            //tanca l'objecte amb el fitxer carregat
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    /**
     * Machaca el array entero, pero al tenerlo guardado en la función
     * cargarArrayUser() (en las primeras lineas del addUser()), al guardarlo de 
     * nuevo contiene lo que tenia y las novedades añadidas.
     *
     * @param users Array de usuarios a guardar.
     */
    public static void guardarArrayUsers(User[] users) {

        //Guardadr todo el array en el fichero IMPORTANTE:Tiene que sobrescribi
        //todo lo que  se habia cargado anteriormente.
        //Añade
        ObjectOutputStream input;
        try {
            input = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO_USUARIOS, false));
            //escribe
            input.writeObject(users);
            //Cierra
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Funcion que crea el fichero para guardar los usuarios en caso de que no
     * exista
     */
    public static void crearFicheroUsers() {
        try {
            ObjectOutputStream input = new ObjectOutputStream(new FileOutputStream(RUTA_FICHERO_USUARIOS, false));
            //Con WriteObject escribimos directamente todo el array de empleados
            input.writeObject(new User[LONG_ARRAY_USUARIOS]);
            //Cerramos el fichero
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion generica para introducir usuarios
     *
     * @return nuevo usuario.
     */
    public static User introducirUser() {
        //Con esta linea le indico que le añado un nuevo empleado.
        User user = new User();

        do {//Ha de demanar els camps per teclat i retornar una nova instància de user
            System.out.println("Introducir rol 'teacher' o 'admin': ");
            user.rol = sc.next().toLowerCase();
            if (!user.rol.equalsIgnoreCase("teacher") && !user.rol.equalsIgnoreCase("admin")) {
                System.out.println("Rol incorrecto");
                System.out.println("");
            }
        } while (!user.rol.equalsIgnoreCase("teacher") && !user.rol.equalsIgnoreCase("admin"));

        //comprobar que el nombre del usuario no existe en el .dat
        do {
            if (comprobarUser(user.nombre) == true) {
                System.out.println("El nombre de usuario ya existe, inserte uno nuevo");
                System.out.println("");
            }
            System.out.print("Introduce Nombre del usuario: ");
            user.nombre = sc.next();
        } while (comprobarUser(user.nombre) == true);

        System.out.print("Introduce Contraseña del usuario: ");
        user.password = sc.next();

        return user;
    }

    /**
     * Muestra los usuarios pasados en un array por parametro
     *
     * @param users Array de usuarios.
     */
    public static void mostrarUsers(User[] users) {
        int i = 0;
        //mirar que la i no es passi el tamany de l'array per tal d'evitar un error
        //mirar que la posicio no sigui null
        while (i < users.length && users[i] != null && !users[i].nombre.isEmpty()) {
            mostrarUser(users[i]);
            System.out.println();
            i++;
        }
    }

    /**
     * Muestra un usuario en concreto
     *
     * @param user Array de usuarios.
     */
    public static void mostrarUser(User user) {
        //System.out.println("Contraseña: " + user.password);
        System.out.println("Rol: " + user.rol);
        System.out.println("Nombre: " + user.nombre);
        System.out.print("Password: ");
        for (int i = 0; i < user.password.length(); i++) {
            System.out.print("*");
        }
        System.out.println("");
    }

    /**
     * Introducción del usuario con rol de admin o teacher Comprueba si el
     * usuario es correcto o no Según su rol, llamará a la función de Menú
     * Teacher o Menú Admin
     */
    public static void login() {
        String user, password, rol = "";
        boolean correctLogin = false;
        int intentos = 3;

        System.out.println("\nControl de acceso");
        System.out.println("***********************\n");
        do {
            System.out.print("Usuario: ");
            user = sc.next();
            if (comprobarUser(user)) {
                System.out.print("Password: ");
                password = sc.next();
                if (comprobarPassword(user, password)) {
                    rol = asignarRol(user, password);
                    correctLogin = true;
                } else {
                    System.out.println("\nCONTRASEÑA INCORRECTA");
                    intentos--;
                    System.out.println("Quedan " + intentos + " intentos.\n");
                }
            } else {
                System.out.println("\nUSUARIO INCORRECTO");
                intentos--;
                System.out.println("Quedan " + intentos + " intentos.\n");
            }
        } while (!correctLogin && intentos > 0);

        if (rol.equalsIgnoreCase("teacher")) {
            menuTeacher(user);
        } else if (rol.equalsIgnoreCase("admin")) {
            menuAdmin(user);
        } else if (intentos < 1) {
            System.out.println("ERROR: SE HAN ACABADO LOS INTENTOS.");
        } else {
            System.out.println("ERROR: EL ROL NO ES CORRECTO, MODIFICA EL ROL DEL USUARIO");
        }

    }

    /**
     * Se comprueba que el usuario exista en el fichero users.dat
     *
     * @param user el nombre del usuario por el que se va a buscar
     * @return true si existe el usuario || false si no existe el usuario
     */
    public static boolean comprobarUser(String user) {
        boolean correct = false;
        // LEER FICHERO
        try {
            // A partir de aquí accederemos al fichero a leer mediante la variable fichero
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_USUARIOS));

            // Creamos un nuevo array de usuarios
            // Y rellenamos con lo recuperado de leer el fichero mediante readObject
            // readObject recibe todo un array de Empleados y por eso lo casteamos (Users[])
            User[] rol = (User[]) fichero.readObject();

            // Recorremos todo el array del usuario
            for (User users : rol) {
                // Tenemos en cuenta que algunas posiciones del array valen null
                // En ese caso no leas la información del usuario
                if (users != null) {
                    if (users.nombre.equals(user)) {
                        correct = true;
                    }
                }
            }
            // Cerramos el fichero
            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer el fichero");
        }
        return correct;
    }

    /**
     * Se comprueba que el password exista en el fichero users.dat
     *
     * @param user String de usuario.
     * @param password String password introducido.
     * @return true si el password coincide || false si no existe
     */
    public static boolean comprobarPassword(String user, String password) {
        boolean correct = false;
        // LEER FICHERO
        try {
            // A partir de aquí accederemos al fichero a leer mediante la variable 
            //fichero
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_USUARIOS));

            // Creamos un nuevo array de usuarios
            // Y rellenamos con lo recuperado de leer el fichero mediante readObject
            // readObject recibe todo un array de Empleados y por eso lo casteamos (Users[])
            User[] rol = (User[]) fichero.readObject();

            // Recorremos todo el array del usuario
            for (User users : rol) {
                // Tenemos en cuenta que algunas posiciones del array valen null
                // En ese caso no leas la información del usuario
                if (users != null) {
                    if (users.nombre.equals(user) && users.password.equals(password)) {
                        correct = true;
                    }
                }
            }
            // Cerramos el fichero
            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer el fichero");
        }
        return correct;
    }

    /**
     * Comprueba si el el usuario y el password introducidos son correctos y
     * retorna su rol
     *
     * @param user String usuario.
     * @param password String password introducido.
     * @return el rol del usuario
     */
    public static String asignarRol(String user, String password) {
        String rolUser = "";
        // LEER FICHERO
        try {
            // A partir de aquí accederemos al fichero a leer mediante la variable fichero
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream(RUTA_FICHERO_USUARIOS));

            // Creamos un nuevo array de usuarios
            // Y rellenamos con lo recuperado de leer el fichero mediante readObject
            // readObject recibe todo un array de Empleados y por eso lo casteamos (Users[])
            User[] rol = (User[]) fichero.readObject();

            // Recorremos todo el array del usuario
            for (User users : rol) {
                // Tenemos en cuenta que algunas posiciones del array valen null
                // En ese caso no leas la información del usuario
                if (users != null) {
                    if (users.nombre.equals(user) && users.password.equals(password)) {
                        rolUser = users.rol;
                    }
                }
            }
            // Cerramos el fichero
            fichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer el fichero");
        }
        return rolUser;
    }

    /**
     * Esta Función muestra el menú de profesor.
     *
     * @param user Nombre de usuario logeado.
     */
    public static void menuTeacher(String user) {
        try {

            int opcion;
            // imprimir menú y pedir la opción.
            do {
                System.out.print("\nMenú de Teacher: "
                        + "\n-----------------------------------"
                        + "\nBienvenido usuario: " + user
                        + "\n-----------------------------------"
                        + "\n 1.Listar todas las clases  "
                        + "\n 2.Crear nueva clase "
                        + "\n 3.Modificar la clase "
                        + "\n 4.Eliminar la clase "
                        + "\n 5.Cerrar sesión"
                        + "\n-----------------------------------"
                        + "\n  Ingresa el numero de la opción: ");

                opcion = sc.nextInt();
                //Ponemos un switch con las funciones de cada opción.
                switch (opcion) {
                    case 1:
                        leer_archivo();

                        break;
                    case 2:
                        addRecord();
                        break;
                    case 3:
                        modRecord();
                        break;
                    case 4:
                        eliminar();
                        break;
                    case 5:
                        System.out.println("\nHasta la próxima " + user);
                        System.out.println("Saliendo del menu...");
                        login();
                        break;
                    default:
                        System.out.println("La opcion que has seleccionado es erronea");
                        break;
                }
                //Mientras que opción no sea 5 seguirá mostrando el menú.
            } while (opcion != 5);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la función Menú Teacher");
        }
    }

    /**
     * Esta Función muestra el menú de Administrador.
     * @param user String usuario.
     */
    public static void menuAdmin(String user) {
        Scanner sn = new Scanner(System.in);
        int opcion;
        // imprimir menú y pedir la opción.
        do {
            System.out.print("\nMenú de Admin: "
                    + "\n-----------------------------------"
                    + "\nBienvenido usuario: " + user
                    + "\n-----------------------------------"
                    + "\n 1.Alta usuario"
                    + "\n 2.Listar todos los usuarios"
                    + "\n 3.Modificar contraseña y usuario"
                    + "\n 4.Eliminar usuario"
                    + "\n 5.Mostrar un usuario"
                    + "\n 6.Cerrar sesión"
                    + "\n-----------------------------------"
                    + "\n  Ingresa el numero de la opción: ");
            opcion = sn.nextInt();
            //Ponemos un switch con las funciones de cada opción.
            switch (opcion) {
                case 1:
                    addUser();
                    break;
                case 2:
                    listarUsers();
                    break;
                case 3:
                    menuModificar();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    filtrarUser(user);
                    break;
                case 6:
                    System.out.println("\nHasta la próxima " + user);
                    System.out.println("Saliendo del menu...");
                    login();
                    break;
                default:
                    System.out.println("La opcion que has seleccionado es erronea");
                    break;
            }
            //Mientras que opción no sea 6 seguirá mostrando el menú.
        } while (opcion != 6);
    }

    /**
     * Lista los usuarios registrados en el fichero.dat
     */
    public static void listarUsers() {
        //Llamamos las funciones.
        User[] users = cargarArrayUser();
        mostrarUsers(users);
    }

    /**
     * Función que comprueba si existe o no un usuario en nuestro array de
     * usuarios.
     *
     * @param usuario Nombre del usuario a buscar.
     * @return true si ha encontrado el usuario || false si no lo ha encontrado
     */
    public static boolean buscarUser(String usuario) {

        // LEER FICHERO EN MEMORIA
        // Creamos la variable que contendrá el array de Usuario en memoria
        User[] users = cargarArrayUser();
        boolean encontrado = false;

        for (User user : users) {
            if (user != null && usuario.equals(user.nombre)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    /**
     * Esta función permite modificar un usuario mediante su nombre.
     */
    public static void modificarUser() {

        User[] users = cargarArrayUser();
        // ACTUALIZAR DATOS
        // Buscaremos por la clave primaria o varios campos, en este 
        //caso por Nombre y actualizaremos el nombre
        System.out.print("Introduce el nombre del usuario: ");
        String nombreActualizado = sc.next();

        // AMPLIACIÓN: Comprobar si se ha encontrado o no ese usuario a 
        //modificar e informar al usuario
        if (buscarUser(nombreActualizado)) {
            for (User user : users) {
                if (user != null && user.nombre.equals(nombreActualizado)) {
                    System.out.println("Usuario actual: " + user.nombre);
                    System.out.print("Introduce el nuevo nombre de usuario: ");
                    user.nombre = sc.next();
                    System.out.println("Registro actualizado correctamente!");
                }
            }
        } else {
            System.out.println("El usuario introducido no existe");
        }
        // GUARDAR FICHERO
        guardarArrayUsers(users);
    }

    /**
     * Esta función permite modificar un password mediante su nombre.
     */
    public static void modificarPassword() {

        User[] users = cargarArrayUser();
        // ACTUALIZAR DATOS
        // Buscaremos por la clave primaria o varios campos, en este 
        //caso por Nombre y actualizaremos el nombre
        System.out.print("Introduce el nombre del usuario: ");
        String nombreActualizado = sc.next();

        // AMPLIACIÓN: Comprobar si se ha encontrado o no ese usuario a 
        //modificar e informar al usuario
        if (buscarUser(nombreActualizado)) {
            for (User user : users) {
                if (user != null && user.nombre.equals(nombreActualizado)) {
                    System.out.println("Usuario actual: " + user.nombre);
                    System.out.print("Introduce el nuevo password de usuario: ");
                    user.password = sc.next();
                    System.out.println("Registro actualizado correctamente!");
                }
            }
        } else {
            System.out.println("El usuario introducido no existe");
        }
        // GUARDAR FICHERO
        guardarArrayUsers(users);
    }

    /**
     * Se crea un menú para modificar el password o el usuario
     */
    public static void menuModificar() {

        int opcion;
        String opcion_introducida;
        System.out.println("\n");
        System.out.println("MENU MODIFICAR");
        System.out.println("\n");

        System.out.println("1. Modificar usuario");
        System.out.println("2. Modificar password");
        System.out.println("");
        System.out.println("Qué quiere modificar: ");

        opcion_introducida = sc.next();

        if (validarStringNum(opcion_introducida)) {
            opcion = convertirStringaInt(opcion_introducida);
        } else {
            opcion = 99;
        }

        if (opcion == 1) {
            modificarUser();
        } else if (opcion == 2) {
            modificarPassword();
        } else {
            System.out.println("Opcion incorrecta, elige 1 o 2");
        }
    }

    /**
     * Función que elimina un usuario según su nombre.
     */
    public static void eliminarUsuario() {
        User[] users = cargarArrayUser();
        // ACTUALIZAR DATOS
        // Buscaremos por la clave primaria o varios campos, 
        //en este caso por Nombre.
        System.out.print("Qué usuario quiere borrar?: ");
        String nombreBorrar = sc.next();

        
        if (buscarUser(nombreBorrar) == true) {
            for (User user : users) {
                if (user != null && user.nombre.equals(nombreBorrar)) {
                    user.nombre = "";
                    user.rol = "";
                    user.password = "";

                    user = null;
                    System.out.println("Se ha eliminado el siguiente usuario: " + nombreBorrar);
                }
            }
        } else {
            System.out.println("El usuario introducido no existe");
        }
        // GUARDAR FICHERO
        guardarArrayUsers(users);
    }
    
    /**
     * Filtra un usuario mediante el nombre y lo muestra.
     * @param currentUser Usuario actual.
     */
    public static void filtrarUser(String currentUser) {
        User[] users = cargarArrayUser();
        String usuario;

        System.out.println("Dime el nombre del usuario que quiere ver: ");
        usuario = sc.next();

        for (User user : users) {
            if (comprobarUser(usuario) && user != null) {
                if (user.nombre.equalsIgnoreCase(usuario)) {
                    mostrarUser(user);
                    System.out.println("");
                }
            }
        }

        if (comprobarUser(usuario) == false) {
            System.out.println("El usuario no existe");
        }

        menuAdmin(currentUser);
    }
}
