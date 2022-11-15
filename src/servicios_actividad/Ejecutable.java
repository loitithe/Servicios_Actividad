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

/**
 *
 * @author rodri
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public void execProgram() throws IOException {
        // File directorio = new File("C:\\Users\\rodri\\Documents\\NetBeansProjects\\EjerciciosFPdistancia\\Servicios_Actividad\\build\\classes\\servicios_actividad");
        File directorio = new File("C:\\Users\\rodri\\Documents\\NetBeansProjects\\EjerciciosFPdistancia\\Servicios_Actividad\\build\\classes\\");
        // Proceso a ejecutar  

        ProcessBuilder pb = new ProcessBuilder("java", "Aleatorios");
        pb.directory(directorio);
        Process p = pb.start();
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String entrada = "";
        while (!entrada.equals("fin")) {
            try {

                System.out.printf("Directorio de trabajo : %s\n", pb.directory());
                System.out.println("Escribe fin para terminar");
                entrada = br.readLine();
                //capturamos el numero aleatorio
                InputStream is = p.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                    is.close();
                }
                int exitVal;
                exitVal = p.waitFor();
                System.out.println("Valor de salida:" + exitVal);
            } catch (InterruptedException e) {
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
