/* */
import java.io.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
public class fichero2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto;
        try(FileWriter fw = new FileWriter("C:\\Visual studio\\Java-practica\\Ficheros\\src\\fichero2.txt");
            FileReader fr = new FileReader("C:\\Visual studio\\Java-practica\\Ficheros\\src\\fichero2.txt")){

            System.out.println("Escribe el texto que quieras");
            texto = sc.nextLine();
                fw.write(texto);
                fw.flush();
                int contenido=fr.read();
                while(contenido!=-1){
                    System.out.print((char)contenido);
                    contenido=fr.read();
                }
        }catch(IOException e){
            System.out.println("Error archivo no encontrado"+e);
        }
    }
}