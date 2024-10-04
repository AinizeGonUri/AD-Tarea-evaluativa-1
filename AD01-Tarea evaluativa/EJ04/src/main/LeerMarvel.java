import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerMarvel {
    static class Personaje {
        int id;
        String dni;
        String nombre;
        String identidadSecreta;
        String tipo;
        int peso;
        int altura;

        Personaje(int id, String dni, String nombre, String identidadSecreta, String tipo, int peso, int altura) {
            this.id = id;
            this.dni = dni;
            this.nombre = nombre;
            this.identidadSecreta = identidadSecreta;
            this.tipo = tipo;
            this.peso = peso;
            this.altura = altura;
        }

        public static Personaje leerDe(RandomAccessFile raf) throws IOException {
            int id = raf.readInt();
            String dni = leerString(raf, 9);
            String nombre = leerString(raf, 10);
            String identidadSecreta = leerString(raf, 20);
            String tipo = leerString(raf, 10);
            int peso = raf.readInt();
            int altura = raf.readInt();
            return new Personaje(id, dni, nombre, identidadSecreta, tipo, peso, altura);
        }

        private static String leerString(RandomAccessFile raf, int longitud) throws IOException {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                char c = (char) raf.readByte();
                if (c == '\0') break;
                sb.append(c);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("Marvel.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                Personaje personaje = Personaje.leerDe(raf);
                System.out.printf("ID: %d, DNI: %s, Nombre: %s, Identidad Secreta: %s, Tipo: %s, Peso: %d, Altura: %d%n",
                        personaje.id, personaje.dni, personaje.nombre, personaje.identidadSecreta, personaje.tipo, personaje.peso, personaje.altura);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
