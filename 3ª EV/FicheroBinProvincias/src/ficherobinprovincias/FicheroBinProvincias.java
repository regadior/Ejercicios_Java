package ficherobinprovincias;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
//import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FicheroBinProvincias{
	static final String ruta="D:\\Ejercicios_Java\\3ª EV\\textos\\";
	static int menu(Scanner e) {
		int op = 0; 
		System.out.println("\n\tMEN�\n\t====");
		System.out.println("1.Altas.");
		System.out.println("2.Listado provincias.");
		System.out.println("3.Listado provincias por letra (fichero).");
		System.out.println("4.Crear lista.");
		System.out.println("5.Listado entre l�mites.");
		System.out.println("6.Visualizar lista.");
		System.out.println("7.Modificar el fichero.");
		System.out.println("8.Listado con letra (lista).");
		System.out.println("9.Fin.");
		do {
			try {
				System.out.print("\n\tTeclee opci�n(1-9): ");
				op = e.nextInt();
			}catch(InputMismatchException ime) {
				op=Integer.MAX_VALUE;					
			}			
		}while (op < 1 || op > 9);
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
		System.out.println("Lista creada");
	}
	static void crearLaLista(List<String>l) {
		l.clear();
		System.out.println("\n\tCREAR LISTA\n\t===========");
		System.out.println("\n\tCreando lista....");
		crearLista(l);
		System.out.println("\n\tLista creada...."+l);
		System.out.println("\n\tTama�o de la lista...."+l.size());

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
			System.out.println("Vac�a");
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

	//si s� las veces que tiene que leer
	//	static void crearLista(List<String> l) throws IOException{
	//		String p=null;
	//		System.out.println("\nCreando lista...");
	//		//LLENAR LISTA
	//		DataInputStream lee= new DataInputStream(new FileInputStream(ruta + "provincias.dat"));
	//		try {
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
		System.out.println("\n\tLISTADO ENTRE L�MITES\n\t=====================");
		do {
			try {
				System.out.println("Teclee l�mite inferior.....: ");
				li=e.nextInt();
				System.out.println("Teclee l�mite superior.....: ");
				ls=e.nextInt();
			}catch(InputMismatchException ime) {
				li=Integer.MAX_VALUE;
				ls=Integer.MAX_VALUE;
				e.nextLine(); //para que no se quede con los valores anteriores
			}
		}while (li>ls);
		//no contempl� el indexoutofboundsexception, mira el siguiente comentario.
		if(ls>l.size())
			ls=l.size(); //si te pasas te pones al final, pero puede estar fuera de rango como cualquier array corriente.
		//as� imprimir� desde li hasta l.size();
		System.out.println("\n");
		if(!l.isEmpty())
			System.out.println("Lista ---->"+l.subList(li-1, ls));
		//li menos 1 porque el primer elemento es el cero
		//lista entre ambos el primero inclusive y el segundo exclusive.
		else 
			System.out.println("Lista ----> [Vac�a]");
	}
	//	static void visualizarLista(List <String> l){
	//		System.out.println("\n\tVISUALIZAR LISTA\n\t===============");
	//		System.out.println("\nNombre\n======");
	//		if(l.isEmpty())
	//			System.out.println("Vac�a");
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
	//			System.out.println("Vac�a");
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
		System.out.println("\nN�mero de provincia: ");
		n=e.nextInt();
		l.set(n-1,nomN);
		grabarFicheroDesdeLista(l);

	}
	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t================");
	}
	public static void main (String[]args) throws IOException {
		Scanner e= new Scanner(System.in);
		int op=0;
		boolean fin=false;
		List<String> l= new ArrayList<String>();
		do {
			op=menu(e);
			switch(op) {
			case 1:
				altas(e);
				break;
			case 2:
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
			default:
				fin();
				fin=true;
			}
		}while(!fin && op!=9);
	}
}