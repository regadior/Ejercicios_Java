/*Crea un fichero de texto con el nombre y contenido que tu quieras. 
Ahora crea una aplicación que lea este fichero de texto carácter a carácter y muestre su contenido por pantalla  sin espacios. 
Por ejemplo, si un fichero tiene el siguiente texto “Esto es una prueba”, deberá mostrar “Estoesunaprueba”.*/
import java.io.FileReader;
import java.io.IOException;
public class fichero1 {
    public static void main(String[] args) {
        final String fichero="C:\\Visual studio\\Java-practica\\Ficheros\\bin\\fichero.txt";
        try(FileReader fr = new FileReader(fichero)) {
            int valor=fr.read();
            while(valor!=-1){
                if(valor!=32){ //32 es el spacio del codigo 
                    System.out.print((char)valor);
                }
                valor=fr.read();
            }
        } catch (Exception e) {
            System.out.println("Problemas con el E/S "+e);
        }
    }
}
