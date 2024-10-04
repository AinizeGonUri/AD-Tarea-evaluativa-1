import java.io.IOException;
import java.io.RandomAccessFile;

public class Visualizar {
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

        public static Personaje leerDe(RandomAccessFile randomAccessFile) throws IOException {
            int id = randomAccessFile.readInt();
            String dni = leerString(randomAccessFile, 9);
            String nombre = leerString(randomAccessFile, 10);
            String identidadSecreta = leerString(randomAccessFile, 20);
            String tipo = leerString(randomAccessFile, 10);
            int peso = randomAccessFile.readInt();
            int altura = randomAccessFile.readInt();
            return new Personaje(id, dni, nombre, identidadSecreta, tipo, peso, altura);
        }

        private static String leerString(RandomAccessFile randomAccessFile, int longitud) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                char character = (char) randomAccessFile.readByte();
                if (character == '\0') break;
                stringBuilder.append(character);
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {

        String tipoBuscado = args[0];
        int contador = 0;

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Marvel.dat", "r")) {
            boolean tipoEncontrado = false;

            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                Personaje personaje = Personaje.leerDe(randomAccessFile);
                if (personaje.tipo.equalsIgnoreCase(tipoBuscado)) {
                    tipoEncontrado = true;
                    contador++;

                    System.out.printf("ID: %d, DNI: %s, Nombre: %s, Identidad Secreta: %s, Tipo: %s, Peso: %d, Altura: %d%n",
                        personaje.id, personaje.dni, personaje.nombre, personaje.identidadSecreta, personaje.tipo, personaje.peso, personaje.altura);
                }
            }

            if (contador == 0) {
                System.out.println("No se encontraron personajes del tipo: " + tipoBuscado);
            } else {
                System.out.println("Total de personajes de tipo '" + tipoBuscado + "': " + contador);
            }

            if (!tipoEncontrado) {
                System.out.println("El tipo de personaje '" + tipoBuscado + "' no existe en el archivo.");
            }

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}
