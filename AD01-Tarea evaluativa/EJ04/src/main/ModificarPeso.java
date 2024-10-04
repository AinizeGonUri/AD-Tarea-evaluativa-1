import java.io.IOException;
import java.io.RandomAccessFile;

public class ModificarPeso {
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
                char c = (char) randomAccessFile.readByte();
                if (c == '\0') break;
                stringBuilder.append(c);
            }
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {

        String dniBuscado = args[0];
        int pesoActual;
        try {
            pesoActual = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("El peso debe ser un número entero.");
            return;
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile("Marvel.dat", "rw")) {
            boolean personajeEncontrado = false;
            long posicion = 0;

            while (posicion < randomAccessFile.length()) {
                randomAccessFile.seek(posicion);
                Personaje personaje = Personaje.leerDe(randomAccessFile);
                if (personaje.dni.equals(dniBuscado)) {
                    personajeEncontrado = true;

                    int diferenciaPeso = pesoActual - personaje.peso;
                    personaje.peso = pesoActual; 

                    randomAccessFile.seek(posicion + 9 + 10 + 20 + 10 + 4); 
                    randomAccessFile.writeInt(personaje.peso);

                    if (diferenciaPeso > 0) {
                        System.out.println(personaje.nombre + " ha engordado " + diferenciaPeso + " kg.");
                    } else if (diferenciaPeso < 0) {
                        System.out.println(personaje.nombre + " ha adelgazado " + Math.abs(diferenciaPeso) + " kg.");
                    } else {
                        System.out.println(personaje.nombre + " se mantiene en su peso.");
                    }
                    break;
                }
                posicion += 9 + 10 + 20 + 10 + 4 + 4; 
            }

            if (!personajeEncontrado) {
                System.out.println("El personaje con DNI " + dniBuscado + " no existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}
