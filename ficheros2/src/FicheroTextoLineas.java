
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class FicheroTextoLineas{
	public static void main (String[]args) {
		Scanner e= new Scanner(System.in);
		String nombre=null;
		try {
			FileWriter f = new FileWriter("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt"); 
			BufferedWriter esc= new BufferedWriter(f);
			//BufferedWriter esc= new BufferedWriter("C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programaci�n\\textos\\textocadena.txt");
			System.out.print("Teclea nombre(fin pra acabar)");
			nombre = e.nextLine();
			while(!nombre.equalsIgnoreCase("fin")) {
				esc.write(nombre);
				esc.newLine();//grabo salto de l�nea
				System.out.println("Teclea nombre(fin pra acabar)");
				nombre = e.nextLine();
			}
			esc.close();
		}catch(IOException ioe) {}
		try {
			System.out.println("\n\t Fin de entradas.");
			FileReader f = new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt"); 
			BufferedReader lec= new BufferedReader(f);
			//BufferedReader lec= new BufferedReader("C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programaci�n\\textos\\textocadena.txt");
			nombre=lec.readLine();
			while (nombre!=null) {
				System.out.println(nombre);
				nombre =lec.readLine();
			}
			lec.close();
		}catch(IOException ioe) {}
		e.close();
	}
}