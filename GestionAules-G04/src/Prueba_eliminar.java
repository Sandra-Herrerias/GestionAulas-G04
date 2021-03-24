/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba_eliminar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author soltani
 */
public class Prueba_eliminar {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        eliminar();
    }
     public static void eliminar() {
       File fichero = new File("C://files/classrooms.cvs");
        
        // Array para guardar todas las líneas leídas del fichero
        ArrayList<String> lineas = new ArrayList<>();
        
        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
                      
            while(lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }
            
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
        
        // Abrimos el fichero de texto para sobreescribirlo
        // Eliminaremos la línea 3
        try {
            FileWriter writer = new FileWriter(fichero);
            Scanner sc = new Scanner(System.in);
            
            for (String linea : lineas) {
                 System.out.print("Introduce la linea a eliminar: ");
                  linea = sc.next();
                  
                if (!linea.equals(linea)) {
                    writer.write(linea + "\n");
                }
            }
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }
}
