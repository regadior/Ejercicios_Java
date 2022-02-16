
//falta por terminar mucho. Imaginamos una matriz que tenga dos columnas, la 1 y la 0, en el caso de ordenar el fichero, �sto nos sirve 
//porque vamos a tener que ordenar por el m�todo de la burbuja por las dos columnas, haciendo intercambio de variables indicando de q columna lo estamos haciendo (la 0 o la 1)

//Si hay en la misma sentencia && y || , se hace primero el &&, y no hace falta poner nada entre par�ntesis porque se hace primero y ya.


import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NombreEdad{
	//aqu� se puede poner static final string ruta = se pone para poner una ruta y si en otro momento tengo que cambiarla s�lo la tengo q cambiar aqu� arriba, no en todo
	
	static int crearMenu(Scanner sc) {
		int op=0;
		System.out.println("\n\tMEN�\n\t-----");
		System.out.println("1.-Altas.");
		System.out.println("2.-Listado.");
		System.out.println("3.-Media de edad.");
		System.out.println("4.-Mayor y menor edad.");
		System.out.println("OrdenarFichero");
		System.out.println("6.-Final.");
		do {
			System.out.print("Elija una opcion(1/6): ");
			op=sc.nextInt();
		}while(op<1||op>5);
		
		return op; 
	}
	
	static void crearAltas() {
		Scanner sc=new Scanner(System.in);
		String nombre="";
		String edad="";
		try {
			FileWriter escritorAltas=new FileWriter("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
			BufferedWriter escritorNombre=new BufferedWriter(escritorAltas);
			System.out.print("Teclee nombre(fin para acabar): ");
			nombre=sc.nextLine();
			while(!nombre.equalsIgnoreCase("fin")) { //este m�todo equals compara solo si es igual o distinto, a diferencia de compareToIgnoreCase q nos va a decir si es mayor o menor
				System.out.print("Teclee edad: ");
				edad=sc.nextLine();
				escritorNombre.write(nombre);
				escritorNombre.newLine();
				escritorNombre.write(edad);
				escritorNombre.newLine();
				System.out.print("Teclee nombre(fin para acabar): ");
				nombre=sc.nextLine();
			}
			escritorNombre.close();
			
		}catch(IOException ioe) {}
	}

	static void visualizarListado() {
		String nombre="";
		String edad="";
		try {
			System.out.println("\nVISUALIZAR LISTADO\n");
			FileReader lectorListado=new FileReader("D:\\Java-practica\\ficheros2\\src\\Fichero1.txt");
			BufferedReader lectorLineas=new BufferedReader (lectorListado);
			nombre=lectorLineas.readLine();
			edad=lectorLineas.readLine();
			while(nombre!=null) {
				System.out.println(nombre+""+tabular(nombre)+""+edad);
				nombre=lectorLineas.readLine();
				edad=lectorLineas.readLine();
			}
			lectorLineas.close();
		}catch(IOException ioe) {}
	}
	
	static void fin() {
		System.out.println("\nFin del programa.");
	}
	
	static String tabular(String nombre) {
		String tabulaciones="";
		if (nombre.length()<8) {
			tabulaciones="\t\t\t";
		}else {
			tabulaciones="\t\t";
		}
		
		return tabulaciones;
	}
	
	static void media() {
		
	}
	
	public static void main(String[] args) {
		/*teclear nombre y grabar nombre y teclear edad y grabar edad*/
		/*Para pararlo cuando teclee el nombre fin*/
		Scanner sc=new Scanner (System.in);
		int opcionElegida=0;
		do {
		opcionElegida=crearMenu(sc);
		switch (opcionElegida) {
		case 1:
			crearAltas();
			break;

		case 2:
			visualizarListado();
			break;
		case 3:

		default:
			fin();
		}
		}while (opcionElegida!=6);
		sc.close();
		
		
	}

}
