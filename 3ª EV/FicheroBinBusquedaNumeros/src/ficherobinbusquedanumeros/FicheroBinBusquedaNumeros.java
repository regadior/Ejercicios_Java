package ficherobinbusquedanumeros;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class FicheroBinBusquedaNumeros{
	static final String ruta="D:\\Ejercicios_Java\\3ª EV\\textos\\";
	static int menu(Scanner e) {
		int op = 0; 
		e= new Scanner(System.in);
		System.out.println("\n\tMEN�\n\t====");
		System.out.println("1.Crear fichero.");
		System.out.println("2.Listar fichero.");
		System.out.println("3.Buscar n�mero.");
		System.out.println("4.Buscar n�mero y las veces que se repite.");
		System.out.println("5.Fin.");
		do {
			System.out.print("\n\tTeclee opci�n(1-5): ");
			op = e.nextInt();
		} while (op < 1 || op > 5);	
		e.nextLine();//vaciar buffer entrada
		return op;
	}	
	static void crearFichero(Scanner e) {
		int num=0;
		try {
			//FileOutputStream archSal= new FileOutputStream(ruta + "numeros.dat")
			//ObjectOutputStream salida= new ObjectOutputStream(archSal)
			ObjectOutputStream salida= new ObjectOutputStream(new FileOutputStream(ruta + "numeros.dat"));
			System.out.println("Teclear n�meros (999 para finalizar:)");
			while(num!=999) {
				num= e.nextInt();
				salida.writeInt(num);
			}
			salida.close();
		}catch(IOException ioe) {
			System.out.println("El fichero no se ha podido abrir.");
		}
	}
	static void listarFichero() {		
		int num=0;
		try {
			//FileInputStream archEntr= new FileInputStream(ruta + "numeros.dat")
			//ObjectInputStream entrada= new ObjectInputStream(archEntr)
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
			System.out.println("\n\nLos n�meros grabados en el fichero son: \n");
			num=entrada.readInt();
			while(num!=999) {
				System.out.println(num);
				num=entrada.readInt();
			}
		}catch(IOException ioe) {
			System.out.println("El fichero no se ha podido abrir.");
		}
	}
	static boolean buscarNumero() {
		Scanner e= new Scanner (System.in);
		boolean existe=false;
		int num=0,nb=0;
		do {
			System.out.println("Introduce el n�mero a buscar: ");
			nb=e.nextInt();
		}while (nb<1||nb>998);
		try {
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
			num=entrada.readInt();
			do {
				if(nb==num) {
					existe=true;
				}
				num=entrada.readInt();
			}while (!existe);
		}catch(IOException ioe) {}
		return existe;
	}
	static void buscarNumeroVeces() {
		Scanner e = new Scanner (System.in);
		boolean existe=false;
		int num=0,nb=0,c=0;
		do {
			System.out.println("Introduce el n�mero a buscar: ");
			nb=e.nextInt();
		}while (nb<1||nb>998);
		try {
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "numeros.dat"));
			num=entrada.readInt();
			while(num!=999) {
				if(nb==num) 
					c++;				
				
				nb=entrada.readInt();
			}
		}catch(IOException ioe) {}
		if(c!=0) 
			System.out.println("El n�mero s� existe en el fichero y aparece "+c+" veces.");				
		else
			System.out.println("El n�mero no existe en el fichero");	
	}
	static void fin() {
		System.out.println("Fin del programa.");
	}
	public static void main(String []args) {
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op =menu(e);
			switch (op) {
			case 1:
				crearFichero(e);
				break;
			case 2:
				listarFichero();
				break;
			case 3:
				if(buscarNumero())
					System.out.println("El n�mero buscado est� grabado.");
				else
					System.out.println("El n�mero buscado no est� grabado.");
				break;
			case 4:
				buscarNumeroVeces();
				break;			
			case 5:
				fin();
			}
		}while(op!=5);
		e.close();
	}
}