package fichero_01;
import java.io.*;
public class Fichero_01{
	public static void main(String []args) {
		System.out.println("INFORMACI�N SOBRE EL FICHERO. ");
		File f= new File("G:\\Mi unidad\\PROGRAMACI�N\\EJERCICIOS\\3� EV\\textos\\file.txt");
		if(f.exists()) {
			System.out.println("Nombre del fichero...."+f.getName());
			System.out.println("Ruta...."+f.getPath());
			System.out.println("Ruta absoluta...."+f.getAbsolutePath());			
			System.out.println("Se puede leer...."+f.canRead());
			System.out.println("Se puede escribir...."+f.canWrite());			
			System.out.println("Tama�o del fichero...."+f.length());
			System.out.println("Es un directorio...."+f.isDirectory());
			System.out.println("Es un fichjjhhhkhhhjjjjjhhhhhhhjero"+f.isFile());
		}

	}
}