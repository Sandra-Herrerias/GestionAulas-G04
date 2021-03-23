package lectura_aulas;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * @author G4
 */
public class LecturaAulas {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        AddRecord();
        leer_archivo();
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
            
            writer.write(id_aula+","+descripcio_aula+","+capacitat_aula+","+
                    pc_aula+","+num_pc+","+projector_aula+","+insonoritzada_aula + "\n");
            writer.flush();//limpia la memoria del writer
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }

    }
}
