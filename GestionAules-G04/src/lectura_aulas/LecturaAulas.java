package lectura_aulas;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author G4
 */
public class LecturaAulas {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        modRecord();
        //leer_archivo();
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
    public static void AddRecord() {

        File fichero = new File("files/classrooms.csv");

        try {
            // El true al final indica que escribiremos al final del fichero añadiendo texto
            FileWriter writer = new FileWriter(fichero, true);

            String id_aula, descripcio_aula;
            int capacitat_aula, num_pc;
            boolean pc_aula, projector_aula, insonoritzada_aula;

            System.out.println("Introduce el id de el Aula: ");
            id_aula = sc.nextLine();
            System.out.println("Introduce la descripcion de el Aula: ");
            descripcio_aula = sc.nextLine();
            System.out.println("Introduce la capacidad de el Aula: ");
            capacitat_aula = sc.nextInt();
            System.out.println("Introduce si el Aula tiene ordenadores: ");
            pc_aula = sc.nextBoolean();
            System.out.println("Introduce el numero de ordenadores que tiene el Aula: ");
            num_pc = sc.nextInt();
            System.out.println("Introduce si el Aula tiene proyector: ");
            projector_aula = sc.nextBoolean();
            System.out.println("Introduce el Aula está insonorizada: ");
            insonoritzada_aula = sc.nextBoolean();

            writer.write(id_aula + "," + descripcio_aula + "," + capacitat_aula + ","
                    + pc_aula + "," + num_pc + "," + projector_aula + "," + insonoritzada_aula + "\n");
            writer.flush();//limpia la memoria del writer

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }

    }

    /**
     * Funcion que modifica los registros de una clase y actualiza el fichero
     */
    public static void modRecord() {
        File file_classrooms = new File("files/classrooms.csv");

        ArrayList<String> classroom_info = new ArrayList<>();
        String aula = null, lineaAula, valor;
        String[] aulaUpdate = null;
        boolean laClaseExiste = false, claseRepetida = false;
        boolean error = false;
        int opcion_menu = 0, valornum;

        //variables update
        String id_aula = null, descripcio_aula = null;
        int capacitat_aula = 0, num_pc = 0;
        boolean pc_aula = false, projector_aula = false, insonoritzada_aula = false;

        //File to ArrayList
        try {
            Scanner leerFichero = new Scanner(file_classrooms);

            while (leerFichero.hasNext()) {
                classroom_info.add(leerFichero.nextLine());
            }

            leerFichero.close();
        } catch (Exception e) {
            System.out.println("¡HA OCURRIDO UN ERROR!");
        }

        //ArrayList to updated File
        try {
            Scanner sc = new Scanner(System.in);

            do {
                //Introducimos el aula a modificar.
                System.out.print("Introduce el aula a modificar: ");
                aula = sc.next();

                //Vemos si la clase existe o no existe
                for (String classroom : classroom_info) {
                    //Si existe guardamos los datos del aula en sus variables.
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

                if (!laClaseExiste) {
                    System.out.println("ERROR: La clase introducida no existe!");
                } else {

                    //Si existe pedimos que campo se va a actualizar
                    do {
                        System.out.println("\nAULA SELECCIONADA: " + aula);
                        System.out.println("CODIGO\tDESCRIPCION");
                        System.out.println("1\tid_aula\n"
                                + "2\tdescripcio_aula\n"
                                + "3\tcapacitat_aula\n"
                                + "4\tpc_aula(true|false)\n"
                                + "5\tnum_pc\n"
                                + "6\tprojector_aula(true|false)\n"
                                + "7\tinsonoritzada_aula(true|false)\n"
                                + "0\tACEPTAR Y ACTUALIZAR\n");
                        System.out.print("Codigo: ");
                        opcion_menu = sc.nextInt();

                        switch (opcion_menu) {
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
                            case 2:
                                System.out.print("Inserte nuevo registro: ");
                                descripcio_aula = sc.next();
                                break;
                            case 3:
                                System.out.print("Inserte nuevo registro: ");
                                valornum = sc.nextInt();
                                if (valornum >= 0) {
                                    capacitat_aula = sc.nextInt();
                                } else {
                                    System.out.println("\nERROR:VALOR NEGATIVO INSERTADO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;

                            case 4:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("true") | valor.equals("false")) {
                                    pc_aula = Boolean.parseBoolean(valor);
                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO TRUE O FALSE.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            case 5:
                                System.out.print("Inserte nuevo registro: ");
                                valornum = sc.nextInt();
                                if (valornum >= 0) {
                                    num_pc = sc.nextInt();
                                } else {
                                    System.out.println("\nERROR:VALOR NEGATIVO INSERTADO.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;
                            case 6:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("true") | valor.equals("false")) {
                                    projector_aula = Boolean.parseBoolean(valor);
                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO TRUE O FALSE.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;
                            case 7:
                                System.out.print("Inserte nuevo registro: ");
                                valor = sc.next().toLowerCase();
                                if (valor.equals("true") | valor.equals("false")) {
                                    insonoritzada_aula = Boolean.parseBoolean(valor);
                                } else {
                                    System.out.println("\nERROR: VALOR INCORRECTO, SOLO TRUE O FALSE.\n<Presione Enter>");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                }

                                break;
                            case 0:
                                System.out.println("\nActualizando...");
                                break;
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

            for (String classroom : classroom_info) {
                if (aula.equals(classroom.substring(0, classroom.indexOf(","))) && laClaseExiste && !error) {

                    writer.write(id_aula
                            + "," + descripcio_aula
                            + "," + capacitat_aula
                            + "," + pc_aula
                            + "," + num_pc
                            + "," + projector_aula
                            + "," + insonoritzada_aula + "\n");
                } else {
                    writer.write(classroom + "\n");
                }
            }
            if (laClaseExiste && !error) {
                System.out.println("\nREGISTRO AZTUALIZADO CON ÉXITO\n");
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("¡HA OCURRIDO UN ERROR!");
        }

    }

}
