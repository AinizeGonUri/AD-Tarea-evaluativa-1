import java.io.IOException;
import java.io.RandomAccessFile;

public class DatosPersonajes {
    public static void main(String[] args) {
        int[] ids = {1, 2, 3, 4, 5, 6, 7};
        String[] dnis = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
        String[] noms = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
        String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe", "James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
        String[] tipos = {"heroe", "villano", "heroe", "heroe", "villano", "heroe", "villano"};
        int[] pesos = {76, 84, 66, 136, 78, 102, 70};
        int[] alturas = {178, 183, 156, 152, 177, 182, 188};

        String filePath = "Marvel.dat";

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "rw")) {
            for (int i = 0; i < ids.length; i++) {
                randomAccessFile.writeInt(ids[i]); 
                randomAccessFile.writeBytes(dnis[i] + "\0"); 
                randomAccessFile.writeBytes(noms[i] + "\0"); 
                randomAccessFile.writeBytes(identidades[i] + "\0");
                randomAccessFile.writeBytes(tipos[i] + "\0"); 
                randomAccessFile.writeInt(pesos[i]);
                randomAccessFile.writeInt(alturas[i]);
            }
            System.out.println("Carga de datos finalizada");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
