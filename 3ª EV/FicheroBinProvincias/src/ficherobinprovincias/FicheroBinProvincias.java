package ficherobinprovincias;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FicheroBinProvincias{
	static final String ruta="G:\\Mi unidad\\PROGRAMACIÓN\\EJERCICIOS\\3ª EV\\textos\\";
	static int menu(Scanner e) {
		int op = 0; 
		System.out.println("\n\tMENÚ\n\t====");
		System.out.println("1.Altas. ");
		System.out.println("2.Listado provincias.");
		System.out.println("3.Crear lista.");
		System.out.println("4.Listado entre límites.");
		System.out.println("5.Visualizar lista.");
		System.out.println("6.Modificar el fichero.");
		System.out.println("7.Fin.");
		do {
			try {
				System.out.print("\n\tTeclee opción(1-6): ");
				op = e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;					
			}			
		}while (op < 1 || op > 7);
		e.nextLine();
		return op;
	}	
	static void altas(Scanner e) {
		int nProv=0;
		String prov=null;
		System.out.println("\n\tALTAS\n\t=====\n");
		try {			
			DataOutputStream esc = new DataOutputStream(new FileOutputStream(ruta + "provincias.dat",true));			
			System.out.println("Nombre provincia "+ ++nProv +"(* para acabar)");
			prov=e.nextLine();
			while(!prov.equals("*")){				
				esc.writeUTF(prov);
				System.out.println("Nombre provincia "+ ++nProv +"(* para acabar)");
				prov = e.nextLine();
			}			
			esc.close();
		}catch(IOException ioe) {}
	}
	static void listado(){
		int n=0;
		String p=null;
		try {
			DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
			System.out.println("\n\tLISTADO FICHERO\n\t===============\n\n\t");
			p = lee.readUTF();
			while(!p.equals(null)) {
				System.out.println(++n +"\t"+p);
				p = lee.readUTF();
			}
			lee.close();
		}catch(IOException ioe) {}

	}
	static void crearLista(List<String> l){
		String p=null;
		System.out.println("\nCreando lista...");
		//LLENAR LISTA
		try {
			DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
			p=lee.readUTF();
			while(!p.equals(null)) {
				l.add(p);
				p=lee.readUTF();
			}
			lee.close();
		}catch(IOException ioe) {}
		System.out.println("Lista creaada");
	}
	//si sé las veces que tiene que leer
	//	static void crearLista(List<String> l){
	//		String p=null;
	//		System.out.println("\nCreando lista...");
	//		//LLENAR LISTA
	//		DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
	//		try {
	//			for(int i=0
	//			p=lee.readUTF();
	//			while(!p.equals(null)) {
	//				l.add(p);
	//				p=lee.readUTF();
	//			}
	//			lee.close();
	//		}catch(IOException ioe) {}
	//		System.out.println("Lista creaada");
	//	}
	static void ordenarLista(List<String>l) {
		System.out.println("Ordenando lista...");
		Collections.sort(l);
	}
	static void grabarFichero(List <String> l, DataOutputStream f) throws IOException{
		for(int i=0;i<l.size();i++) 
			f.writeUTF(l.get(i));	
	}
	static void ordenarFichero() throws IOException{
		List <String>l= new ArrayList <String>();
		DataInputStream ficheroEnt = new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
		ordenarLista(l);
		ficheroEnt.close();
		DataOutputStream ficheroSal = new DataOutputStream(new FileOutputStream(ruta + "provincias.dat",true));			
		grabarFichero(l,ficheroSal);
		ficheroSal.close();
		System.out.println("Fichero ordenado...");
	}

	static List <String> visualizar (List <String> l){
		ordenarLista(l);
		System.out.println("\n\tVISUALIZAR LISTA\n\t===============");
		for(int i=0;i<l.size();i++) {
			if(l.isEmpty())
				System.out.println("Vacía");
			else {
				System.out.println(l.get(i));
			}

			System.out.println(l.get(i));
		}
		System.out.println("\n\n");
		System.out.println("\n\n"+l);
		return l;
	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
	public static void main (String[]args) throws IOException {
		Scanner e= new Scanner(System.in);
		int op=0;
		boolean fin=false;
		List<String> prov= new ArrayList<String>();
		do {
			op=menu(e);
			switch(op) {
			case 1:
				altas(e);
				break;
			case 2:
				listado();
				break;
			case 3:
				crearLista(prov);
				break;
			case 4:
				listadoLimites();
				break;
			case 5:
				visualizar(prov);
				break;
			case 6:
				ordenar();
				break;a

			default:
				fin();
				fin=true;
			}
		}while(!fin);
	}
}