package manipularficheros;
import java.io.File;
import java.util.Scanner;
public class ManipularFicheros{
	static final String ruta="G:\\Mi unidad\\PROGRAMACIÓN\\EJERCICIOS\\3ª EV\\textos\\";
	public static void main(String []args) {
		Scanner e= new Scanner(System.in);
		String n=null, nuevo=null;
		System.out.println("Nombre del fichero a borrar: ");
		n=e.nextLine();
		File fichero = new File(ruta,n); //puedes poner ruta,n o ruta+n
		if(fichero.exists()) {
			System.out.println("Directorio: "+fichero.getAbsolutePath());
			System.out.println("El fichero \""+n+"\" SI existe");
			if(fichero.delete())
				//si logra borrar el fichero devuelve true, sino false.
				System.out.println("El fichero \""+n+"\" SI ha sido borrado");
			else
				System.out.println("El fichero \""+n+"\" NO ha sido borrado");
		}
		else
			System.out.println("El fichero \""+n+"\" NO existe");
		//System.out.print("Nombre del fichero a renombrar?");
		n=e.nextLine();
		File fichero1= new File(ruta,n); //puedes poner ruta,n o ruta+n
		if(fichero1.exists()) {
			System.out.println("Teclee el nuevo nombre del fichero: ");
			nuevo=e.nextLine();
			File fichero2=new File(ruta,nuevo);
			if(fichero1.renameTo(fichero2))
				System.out.println("El fichero \""+fichero1.getName()+" ahora es:  \""+fichero2.getName()+"\"");
			else
				System.out.println("El fichero \""+fichero1.getName()+" no se ha podido renombrar.");
		}
		else
			System.out.println("El fichero \""+n+"\" NO existe");
		e.close();	

	}
}