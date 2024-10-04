import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InvertirPalabras {
    public static void main(String[] args) {

        String rutaEntrada = args[0];
        String rutaSalida = args[1];

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo de salida: " + e.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaEntrada));
             FileWriter fileWriter = new FileWriter(rutaSalida)) {

            String linea = bufferedReader.readLine();

            if (linea != null) {
                String[] palabras = linea.split(" ");
                StringBuilder lineaInvertida = new StringBuilder();

                for (String palabra : palabras) {
                    lineaInvertida.append(invertirPalabra(palabra)).append(" ");
                }

                fileWriter.write(lineaInvertida.toString().trim());
                System.out.println("Palabras invertidas en: " + rutaSalida);
            } else {
                System.out.println("El archivo de entrada está vacío");
            }
        } catch (IOException e) {
            System.out.println("Error al leer o escribir archivos: " + e.getMessage());
        }
    }

    private static String invertirPalabra(String palabra) {
        return new StringBuilder(palabra).reverse().toString();
    }
}
