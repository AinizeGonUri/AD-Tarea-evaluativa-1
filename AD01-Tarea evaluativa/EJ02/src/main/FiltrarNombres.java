import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FiltrarNombres {
    public static void main(String[] args) {

        String rutaEntrada = args[0];
        String rutaSalida = args[1];

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaSalida))) {
        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo de salida: " + e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaEntrada));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(rutaSalida, true))) { 

            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] palabras = linea.split(" ");
                if (palabras.length > 0) {
                    String nombre = palabras[0];
                    if (nombre.length() == 5) {
                        bufferedWriter.write(nombre);
                        bufferedWriter.newLine(); 
                    }
                }
            }

            System.out.println("Nombres filtrados en el archivo de salida: " + rutaSalida);
        } catch (IOException e) {
            System.out.println("Error al leer o escribir archivos: " + e.getMessage());
        }
    }
}
