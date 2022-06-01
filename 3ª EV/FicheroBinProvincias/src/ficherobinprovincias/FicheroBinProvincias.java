package ficherobinprovincias;

import java.io.DataInputStream;
import java.io.DataOutputStream;
<<<<<<< HEAD
=======
import java.io.EOFException;
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.InputMismatchException;
//import java.util.Iterator;
=======
import java.util.Collections;
import java.util.InputMismatchException;
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
import java.util.List;
import java.util.Scanner;

public class FicheroBinProvincias{
<<<<<<< HEAD
	static final String ruta="D:\\Ejercicios_Java\\3Âª EV\\textos\\";
	static int menu(Scanner e) {
		int op = 0; 
		System.out.println("\n\tMENï¿½\n\t====");
		System.out.println("1.Altas.");
		System.out.println("2.Listado provincias.");
		System.out.println("3.Listado provincias por letra (fichero).");
		System.out.println("4.Crear lista.");
		System.out.println("5.Listado entre lï¿½mites.");
		System.out.println("6.Visualizar lista.");
		System.out.println("7.Modificar el fichero.");
		System.out.println("8.Listado con letra (lista).");
		System.out.println("9.Fin.");
		do {
			try {
				System.out.print("\n\tTeclee opciï¿½n(1-9): ");
=======
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
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
				op = e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;					
			}			
<<<<<<< HEAD
		}while (op < 1 || op > 9);
=======
		}while (op < 1 || op > 7);
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
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
<<<<<<< HEAD
	static void listado(Scanner e, int tl){
		int n=0;
		String p=null,letra=null;
		try {
			DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
			if(tl==1) {
				System.out.println("\n\tLISTADO FICHERO LETRA \n\t=====================\n\n\t");
				do {
					System.out.println("Teclee letra: ");
					letra=e.next();
				}while(letra.length()>1);
			}
			p = lee.readUTF();
			while(!p.equals(null)) {
				if(tl==0||tl==1&&tieneLetra(letra,p)) 
					System.out.println(++n +"\t"+p);
				p = lee.readUTF();	
=======
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
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
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
<<<<<<< HEAD
		System.out.println("Lista creada");
	}
	static void crearLaLista(List<String>l) {
		l.clear();
		System.out.println("\n\tCREAR LISTA\n\t===========");
		System.out.println("\n\tCreando lista....");
		crearLista(l);
		System.out.println("\n\tLista creada...."+l);
		System.out.println("\n\tTamaï¿½o de la lista...."+l.size());

	}
	static boolean hayLetra(Scanner e,List<String>l) throws IOException {
		String c=null;
		boolean existe=false;
		do {
			System.out.println("Teclee una letra a buscar: ");
			c=e.nextLine();
		}while(c.length()>1);
		//		for(int j = 0; j < c.length(); j++) 
		//			if(!l.contains(c)) 
		//				existe=true;
		//		return existe;
		for(int j = 0; j < c.length(); j++) {
			for (int i = 0;i<l.size();i++) {
				existe = l.get(i).indexOf(c.charAt(j)) >= 0;
				if (existe)
					System.out.println(l.get(i));
			}
		}
		return existe;
	}
	static boolean tieneLetra(String l, String p) {
		boolean contiene=false;
		for(int i=0;i<p.length()&&!contiene;i++)
			if(p.substring(i, i+1).equalsIgnoreCase(l))
				contiene=true;
		return contiene;
	}
	static void visualizarLista(List<String>l, int tl,Scanner e) {
		String letra="";
		System.out.println("\n\tVISUALIZAR LISTA\n\t================");
		System.out.println("Nombre");
		if(l.isEmpty())
			System.out.println("Vacï¿½a");
		else {
			if(tl==1) {
				do {
					System.out.println("Teclee una letra a buscar: ");
					letra=e.nextLine();
				}while(letra.length()>1);
			}
			for(int i=0;i<l.size();i++)
				if(tl==0||tl==1 && tieneLetra(letra,l.get(i)))
					System.out.println(l.get(i));
		}
	}

	//si sï¿½ las veces que tiene que leer
	//	static void crearLista(List<String> l) throws IOException{
=======
		System.out.println("Lista creaada");
	}
	//si sé las veces que tiene que leer
	//	static void crearLista(List<String> l){
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
	//		String p=null;
	//		System.out.println("\nCreando lista...");
	//		//LLENAR LISTA
	//		DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
	//		try {
<<<<<<< HEAD
	//			for(int i=0;i<l.size();i++){
	//			p=lee.readUTF();
	//				l.add(p);
	//				}
	//		}catch(EOFException eofe) {}
	//			lee.close();
	//		System.out.println("Lista creada"+l);
	//	}
	static void listadoLimites(Scanner e,List<String>l) {
		int li=0,ls=0;
		System.out.println("\n\tLISTADO ENTRE Lï¿½MITES\n\t=====================");
		do {
			try {
				System.out.println("Teclee lï¿½mite inferior.....: ");
				li=e.nextInt();
				System.out.println("Teclee lï¿½mite superior.....: ");
				ls=e.nextInt();
			}catch(InputMismatchException ime) {
				li=Integer.MAX_VALUE;
				ls=Integer.MAX_VALUE;
				e.nextLine(); //para que no se quede con los valores anteriores
			}
		}while (li>ls);
		//no contemplï¿½ el indexoutofboundsexception, mira el siguiente comentario.
		if(ls>l.size())
			ls=l.size(); //si te pasas te pones al final, pero puede estar fuera de rango como cualquier array corriente.
		//asï¿½ imprimirï¿½ desde li hasta l.size();
		System.out.println("\n");
		if(!l.isEmpty())
			System.out.println("Lista ---->"+l.subList(li-1, ls));
		//li menos 1 porque el primer elemento es el cero
		//lista entre ambos el primero inclusive y el segundo exclusive.
		else 
			System.out.println("Lista ----> [Vacï¿½a]");
	}
	//	static void visualizarLista(List <String> l){
	//		System.out.println("\n\tVISUALIZAR LISTA\n\t===============");
	//		System.out.println("\nNombre\n======");
	//		if(l.isEmpty())
	//			System.out.println("Vacï¿½a");
	//		else {
	//			Iterator<String> it=l.iterator();
	//			while(it.hasNext())
	//				System.out.println(it.next());
	//		}
	//	}

	//	static void visualizarLista2(List <String> l){
	//		System.out.println("\n\tVISUALIZAR LISTA\n\t===============");
	//		System.out.println("\nNombre\n======");	
	//		if(l.isEmpty())
	//			System.out.println("Vacï¿½a");
	//		else {
	//			for(int i=0;i<l.size();i++)
	//				System.out.println(l.get(i));
	//		}
	//	}

	static void grabarFicheroDesdeLista(List <String>l)throws IOException{
		DataOutputStream esc=new DataOutputStream(new FileOutputStream(ruta+"provincias.dat",false));
		for(int i=0;i<l.size();i++)
			esc.writeUTF(l.get(i));
		esc.close();
	}
	static void modificar(Scanner e, List <String>l) throws IOException{
		String nomN=null;
		int n=0;
		crearLista(l);
		System.out.println("\n\tMODIFICACIONES\n\t");
		System.out.println("Nuevo nombre: ");
		nomN=e.nextLine();
		System.out.println("\nNï¿½mero de provincia: ");
		n=e.nextInt();
		l.set(n-1,nomN);
		grabarFicheroDesdeLista(l);

=======
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
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
	public static void main (String[]args) throws IOException {
		Scanner e= new Scanner(System.in);
		int op=0;
		boolean fin=false;
<<<<<<< HEAD
		List<String> l= new ArrayList<String>();
=======
		List<String> prov= new ArrayList<String>();
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
		do {
			op=menu(e);
			switch(op) {
			case 1:
				altas(e);
				break;
			case 2:
<<<<<<< HEAD
				listado(e,0);
				break;
			case 3:
				listado(e,1);
				break;
			case 4:
				crearLista(l);
				break;
			case 5:
				listadoLimites(e,l);
				break;
			case 6:
				visualizarLista(l, 0, e);
				break;
			case 7:
				l.clear();
				modificar(e,l);
				break;
			case 8:
				//				hayLetra(e,l);
				visualizarLista(l,1,e);
				break;
=======
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

>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
			default:
				fin();
				fin=true;
			}
<<<<<<< HEAD
		}while(!fin && op!=9);
=======
		}while(!fin);
>>>>>>> 8f9f658a3992992aa9b4df5229fd656b6de8eb71
	}
}