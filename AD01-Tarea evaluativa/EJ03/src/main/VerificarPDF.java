import java.io.FileInputStream;
import java.io.IOException;

public class VerificarPDF {
    public static void main(String[] args) {

        String rutaArchivo = args[0];

        byte[] bytesPDF = {37, 80, 68, 70}; 

        try (FileInputStream fileInputStream = new FileInputStream(rutaArchivo)) {
            byte[] bytesLeidos = new byte[4];

            int bytesLeidosCount = fileInputStream.read(bytesLeidos);

            if (bytesLeidosCount == 4) {
                boolean esPDFValido = true;

                for (int i = 0; i < bytesPDF.length; i++) {
                    if (bytesLeidos[i] != bytesPDF[i]) {
                        esPDFValido = false;
                        break;
                    }
                }

                if (esPDFValido) {
                    System.out.println("El archivo es PDF.");
                } else {
                    System.out.println("El archivo no es PDF.");
                }
            } else {
                System.out.println("El archivo no comienza con la secuencia correcta para ser un PDF.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
