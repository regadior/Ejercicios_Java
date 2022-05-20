package list_02;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Listas_02{
	static final String ruta="G:\\Mi unidad\\PROGRAMACIÓN\\EJERCICIOS\\3ª EV\\textos\\";
	static int menu(Scanner e) {
		int op=0;
		System.out.println("\n\tMENU\n\t====");
		System.out.println("1.- Altas.");
		System.out.println("2.- Listado.");
		System.out.println("3.- Ordenar fichero.");
		System.out.println("4.- Fin.");
		do {
			try {
				System.out.println("\n\tTeclee opción (1-4): ");
				op=e.nextInt();
			}catch(NumberFormatException nfe) {
				op=Integer.MIN_VALUE;
			}
		}while(op<1||op>4);
		e.nextLine();
		return op;
	}
	static void altas(Scanner e) throws IOException {
		String n=null;
		System.out.println("\n\tALTAS\n\t=====\n");
		DataOutputStream ficheroSal = new DataOutputStream(new FileOutputStream(ruta + "persona.dat",true));			
		System.out.println("Teclea nombre: ");
		n = e.nextLine();
		while(!n.equalsIgnoreCase("fin")){
			ficheroSal.writeUTF(n);
			// de esta manera solamente se comprueba una vez
			System.out.println("Teclea nombre (fin para finalizar): ");
			n = e.nextLine(); 
		}
		ficheroSal.close();			
	}	
	static void lista() throws IOException{
		String n=null;
		boolean fin=false;
		DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "persona.dat"));
		System.out.println("\n\tLISTADO\n\t=======\n\n\tNombre\n\t------\n");
		n = ficheroEnt.readUTF();
		while(!fin) {
			try {
				n = ficheroEnt.readUTF();
				System.out.println(n);
			}catch(EOFException eofe) {
				fin = true;
			}

		}
		ficheroEnt.close();
	}
	static void llenarLista(List<String> l,DataInputStream leer) throws IOException {
		boolean fin=false;
		//LLENAR LISTA
		DataInputStream ficheroEnt= new DataInputStream(new FileInputStream(ruta + "persona.dat"));
		while(!fin) {
			try {
				l.add(ficheroEnt.readUTF());
			}catch(EOFException eofe) {
				fin=true;
			}
		}
		ficheroEnt.close();
	}
	static void ordenarLista(List<String>l) {
		System.out.println("Ordenando fichero...");
		Collections.sort(l);
	}
	static void grabarFichero(List <String> l, DataOutputStream f) throws IOException{
		for(int i=0;i<l.size();i++) 
			f.writeUTF(l.get(i));	
	}
	static void ordenarFichero() throws IOException{
		List <String>l= new ArrayList <String>();
		System.out.println("\n\tORDENAR FICHERO\n\t===============");
		DataInputStream ficheroEnt = new DataInputStream(new FileInputStream(ruta + "persona.dat"));
		llenarLista(l,ficheroEnt);
		ordenarLista(l);
		ficheroEnt.close();
		DataOutputStream ficheroSal = new DataOutputStream(new FileOutputStream(ruta + "persona.dat",true));			
		grabarFichero(l,ficheroSal);
		ficheroSal.close();
		System.out.println("Fichero ordenado...");
	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
	public static void main(String []args) throws IOException  {
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op=menu(e);
			switch(op){
			case 1:
				altas(e);
				break;
			case 2:
				lista();
				break;
			case 3:
				ordenarFichero();
				break;
			default:
				fin();
			}
		}while(op!=4);
	}
}