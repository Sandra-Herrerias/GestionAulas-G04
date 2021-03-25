package lectura_aulas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author G4
 */
public class LecturaAulas {

    //Declaramos un objeto Scanner para leer los datos
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // addRecord();
        modRecord();
        // leer_archivo();
        //eliminar();
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
        File classrooms = new File("files/classrooms.csv");
        //le decimos que intente hacer el siguiente codigo (abrir el fichero para
        //leerlo porque puede ser que de error)   
        String[] clase;
        try {
            Scanner sc = new Scanner(classrooms);
            boolean first_see = true;//variable que usaremos para no imprimir la cabecera en la primera vuelta del bucle
            while (sc.hasNextLine()) {//mientras haya una siguiente linea que leer ejecutar el siguiente codigo
                classroom = sc.nextLine();
                clase = classroom.split(",");//separamos String[] por comas para poder tratar cada valor por separado
                if (!first_see) {
                    //Asignamos el valor de la pos[x] del vector clase[] a una variable determinada
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
            sc.close();//cerramos escaner   
        } catch (Exception e) {//CATCH: capturar posibles errores
            //Exception e: Cualquier tipo de error (no especifico) lo detecta
            System.out.println("No se lee el archivo.");//mostramos mensaje de error
        }
    }

    /**
     * Funcion que permite crear a la usuaria una nueva entrada en el fichero
     */
    public static void addRecord() {

        File fichero = new File("files/classrooms.csv");

        try {
            // El true al final indica que escribiremos al final del fichero añadiendo texto
            FileWriter writer = new FileWriter(fichero, true);

            String id_aula, descripcio_aula, capacitatAulaStr, numPcStr,
                    pcAulaStr, projectorAulaStr, insonoritzadaAulaStr;
            int capacitat_aula = -1, num_pc = -1;
            boolean pc_aula = true, projector_aula = true, insonoritzada_aula = true,
                    esNumero = true, esValido = true;

            //Nueva entrada de id_aula
            do {
                System.out.println("Introduce el id de el Aula: ");
                id_aula = sc.nextLine();
                //validaciones 
                esValido = validarIdDescripcio(id_aula);

                if (esValido == false) {
                    System.out.println("Se han introducido caracteres erróneos");
                }
            } while (esValido == false);

            //Nueva entrada de descripcio_aula
            do {
                System.out.println("Introduce la descripcion de el Aula: ");
                descripcio_aula = sc.nextLine();
                //validaciones 
                esValido = validarIdDescripcio(descripcio_aula);

                if (esValido == false) {
                    System.out.println("Se han introducido caracteres erróneos");
                }
            } while (esValido == false);

            //Nueva entrada de capacitat_aula
            do {
                System.out.println("Introduce la capacidad de el Aula: ");
                capacitatAulaStr = sc.nextLine();
                //validaciones 
                esNumero = validarStringNum(capacitatAulaStr);
                capacitat_aula = convertirStringaInt(capacitatAulaStr);
                if (esNumero == false) {
                    System.out.println("Se han introducido caracteres no numéricos");
                }
            } while (esNumero == false);

            //Nueva entrada de pc_aula 
            do {
                System.out.println("Introduce si el Aula tiene ordenadores o no: ");
                pcAulaStr = sc.nextLine();

                esValido = validarSiNo(pcAulaStr);

                if (esValido == false) {
                    System.out.println("Carácteres erróneos, sólo se aceptan (si|SI|no|NO|)");
                }
            } while (esValido == false);

            if ("si".equals(pcAulaStr) || "SI".equals(pcAulaStr)) {
                pc_aula = true;
            } else if ("no".equals(pcAulaStr) || "NO".equals(pcAulaStr)) {
                pc_aula = false;
            }

            //Nueva entrada de num_pc
            do {
                System.out.println("Introduce el numero de ordenadores que tiene el Aula: ");
                numPcStr = sc.nextLine();
                //validaciones 
                esNumero = validarStringNum(numPcStr);
                num_pc = convertirStringaInt(numPcStr);
                if (esNumero == false) {
                    System.out.println("Se han introducido caracteres no numéricos");
                }
            } while (esNumero == false);

            //Nueva entrada de projector_aula           
            do {
                System.out.println("Introduce si el Aula tiene proyector o no: ");
                projectorAulaStr = sc.nextLine();

                esValido = validarSiNo(projectorAulaStr);

                if (esValido == false) {
                    System.out.println("Carácteres erróneos, sólo se aceptan (si|SI|no|NO|)");
                }
            } while (esValido == false);

            if ("si".equals(projectorAulaStr) || "SI".equals(projectorAulaStr)) {
                projector_aula = true;
            } else if ("no".equals(projectorAulaStr) || "NO".equals(projectorAulaStr)) {
                projector_aula = false;
            }

            //Nueva entrada de insonoritzada_aula
            do {
                System.out.println("Introduce si el Aula está insonorizada o no: ");
                insonoritzadaAulaStr = sc.nextLine();

                esValido = validarSiNo(insonoritzadaAulaStr);

                if (esValido == false) {
                    System.out.println("Carácteres erróneos, sólo se aceptan (si|SI|no|NO|)");
                }
            } while (esValido == false);

            if ("si".equals(insonoritzadaAulaStr) || "SI".equals(insonoritzadaAulaStr)) {
                insonoritzada_aula = true;
            } else if ("no".equals(insonoritzadaAulaStr) || "NO".equals(insonoritzadaAulaStr)) {
                insonoritzada_aula = false;
            }

            //Se secribe la nueva entrada completa en el fichero     
            writer.write(id_aula + "," + descripcio_aula + "," + capacitat_aula + ","
                    + pc_aula + "," + num_pc + "," + projector_aula + "," + insonoritzada_aula + "\n");
            writer.flush();//limpia la memoria del writer

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }

    }

    //--------------------------------
    // VALIDACIONES FUNCION (addRecord)
    //--------------------------------
    /**
     * Valida si el String es un numero (puede ser de 1 o mas digitos)o no
     *
     * @param cadena
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
     * @param numero
     * @return numero convertido a tipo Int
     */
    public static int convertirStringaInt(String numero) {
        return Integer.parseInt(numero);
    }

    /**
     * Valida si empieza por lo menos por una letra mayúscula o minúscula y que
     * acabe con dos dígitos
     *
     * @param cadena
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
     * @param cadena
     * @return true si es valido / false si no lo es
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
     * Funcion que modifica los registros de una clase y actualiza el fichero
     */
    public static void modRecord() {
        
        //LECTURA DE ARCHIVO
        File file_classrooms = new File("files/classrooms.csv");

        //ASIGNACION DE VARIABLES
        ArrayList<String> classroom_info = new ArrayList<>();
        String aula = null, lineaAula, valor;
        String[] aulaUpdate = null;
        boolean laClaseExiste = false, claseRepetida = false;
        boolean error = false;
        int opcion_menu = 0, valornum;

        //VARIABLES DE LA TABLA
        String id_aula = null, descripcio_aula = null;
        int capacitat_aula = 0, num_pc = 0;
        boolean pc_aula = false, projector_aula = false, insonoritzada_aula = false;

        //VOLCANDO DEL FICHERO A LA MEMORIA
        
        try {
            Scanner leerFichero = new Scanner(file_classrooms);

            while (leerFichero.hasNext()) {
                classroom_info.add(leerFichero.nextLine());
            }

            leerFichero.close();
        } catch (Exception e) {
            System.out.println("¡HA OCURRIDO UN ERROR!");
        }

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
                    //Si el aula coincide con el id_aula del archivo lo registrará en variables.
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
                        if (validarStringNum(valor)){
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
                                if (!claseRepetida) {
                                    id_aula = valor;
                                }
                                claseRepetida = false;
                                break;
                            //MODIFICAR descripcio_aula
                            case 2:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                if (validarIdDescripcio(valor)) {
                                    descripcio_aula = valor;
                                } else {
                                    System.out.println("ERROR: SOLO FORMATO 'Ddddd99' ACEPTADO\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            //MODIFICAR capacitat_aula
                            case 3:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                if (validarStringNum(valor)) {
                                    capacitat_aula = convertirStringaInt(valor);
                                } else {
                                    System.out.println("\nERROR:VALOR INCORRECTO INSERTADO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;

                            //MODIFICAR pc_aula
                            case 4:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("si") | valor.equals("no")) {
                                    if (valor.equals("si")) {
                                        pc_aula = true;
                                    } else if (valor.equals("no")) {
                                        pc_aula = false;
                                    }
                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO SI O NO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            //MODIFICAR num_pc
                            case 5:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next();
                                if (validarStringNum(valor)) {
                                    num_pc = convertirStringaInt(valor);
                                } else {
                                    System.out.println("\nERROR:VALOR INCORRECTO INSERTADO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;
                            //MODIFICAR projector_aula
                            case 6:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("si") | valor.equals("no")) {
                                    if (valor.equals("si")) {
                                        projector_aula = true;
                                    } else if (valor.equals("no")) {
                                        projector_aula = false;
                                    }

                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO SI O NO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;
                            //MODIFICAR insonoritzada_aula
                            case 7:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("si") | valor.equals("no")) {
                                    if (valor.equals("si")) {
                                        insonoritzada_aula = true;
                                    } else if (valor.equals("no")) {
                                        insonoritzada_aula = false;
                                    }
                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO SI O NO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

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

        } catch (Exception e) {

            System.out.println("¡HA OCURRIDO UN ERROR!");
            System.out.println("POSIBLEMENTE EL VALOR INTRODUCIDO NO SEA CORRECTO.");
            System.out.println("EL REGISTRO NO SE HA MODIFICADO");
            error = true;
        }

        //ACTUALIZANDO EL ARCHIVO
        try {
            FileWriter writer = new FileWriter(file_classrooms);

            //Recorremos la ArrayList
            for (String classroom : classroom_info) {
                //Si id_aula coincide con el aula modificada actualiza los campos
                if (aula.equals(classroom.substring(0, classroom.indexOf(","))) && laClaseExiste && !error) {

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
                System.out.println("\nREGISTRO AZTUALIZADO CON ÉXITO\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("¡HA OCURRIDO UN ERROR!");
        }

    }

    public static void eliminar() {
      File file_classrooms = new File("files/classrooms.csv"");
        
        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> classroom_info = new ArrayList<>();
        String aula;
        boolean laClaseExiste = false;

        
        // Abrimos el fichero de texto para leerlo en memoria
        try {
            
            Scanner leerFichero = new Scanner(file_classrooms);
                      
            while (leerFichero.hasNext()) {
                classroom_info.add(leerFichero.nextLine());
            }
            
           leerFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir o leer el fichero");
        }
        
        //Array para eliminar la linea.
        try {
            Scanner sc = new Scanner(System.in);
            
            do {
                //Introducimos el aula a modificar.
                System.out.print("Introduce el aula a eliminar: ");
                aula = sc.next();

                //Vemos si la clase existe o no existe
                for (String classroom : classroom_info) {
                    //Si existe guardamos los datos del aula en sus variables.
                    if (aula.equals(classroom.substring(0, classroom.indexOf(",")))) {
                        laClaseExiste = true;
                       
                        
                    }else{
                        System.out.println("La linea que quieres eliminar no existe.");
                    }
                
           
                }      
            }while(!laClaseExiste);
            }catch (Exception e) {
            System.out.println("Ha ocurrido un error al sobreescribir el fichero");
        }
          try {
          
              
        }catch (Exception e) {
            System.out.println("Ha ocurrido un error al sobreescribir el fichero");
        }
    }}
    