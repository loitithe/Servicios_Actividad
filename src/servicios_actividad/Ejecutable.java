/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servicios_actividad;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public void execProgram() throws IOException {
        File directorio = new File(".\\build\\classes\\");
        // Proceso a ejecutar  
        String entrada = "";

        System.out.println("Escribe fin para terminar");

        while (!entrada.equals("fin")) {
            ProcessBuilder pb = new ProcessBuilder("java", "servicios_actividad.Aleatorio");

            pb.directory(directorio);
            Process p = pb.start();

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            //capturamos el numero aleatorio
            InputStream is = p.getInputStream();
            entrada = br.readLine();

            try {
                int c;
                while ((c = is.read()) != -1) {
                    System.out.println("Nº aleatorio : " + (char) c);
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream er = p.getErrorStream();
                BufferedReader brer = new BufferedReader(new InputStreamReader(er));
                String liner = null;
                while ((liner = brer.readLine()) != null) {
                    System.out.println("ERROR >" + liner);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws IOException {

        Ejecutable ej = new Ejecutable();
        ej.execProgram();
    }

}
