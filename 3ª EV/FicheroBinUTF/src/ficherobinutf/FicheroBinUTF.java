package ficherobinutf;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
public class FicheroBinUTF{
	static final String ruta="C:\\Users\\DA-1.PUESTO222\\Desktop\\ejercicios programación\\textos";
	static int menu(Scanner e) {
		int op = 0; 
		e= new Scanner(System.in);
		System.out.println("\n\tMENÚ\n\t====");
		System.out.println("1.Altas.");
		System.out.println("2.Listado.");
		System.out.println("3.Crear fichero según inicial.");
		System.out.println("4.Listar fichero según inicial.");
		System.out.println("5.Fin.");
		do {
			System.out.print("\n\tTeclee opción(1-5): ");
			op = e.nextInt();
		} while (op < 1 || op > 5);	
		e.nextLine();//vaciar buffer entrada
		return op;
	}	
	static void altas(Scanner e)throws IOException {
		String nombre="";
		int edad=0;
		try {
			ObjectOutputStream salida= new ObjectOutputStream(new FileOutputStream(ruta + "\\personas.dat"));
			//DataOutputStream salida= new DataOutputStream(new FileOutputStream(ruta + "\\personas.dat"));
			System.out.println("Teclear nombre (* para finalizar:)");
			nombre=e.nextLine();
			while(!nombre.equals("*")) {
				System.out.println("Edad");
				edad=e.nextInt();
				salida.writeUTF(nombre);
				salida.writeInt(edad);
				e.nextLine();
				System.out.println("Teclee nombre");
				nombre=e.nextLine();
			}
			salida.close();
		}catch(EOFException eofe) {
			System.out.println("El fichero no se ha podido abrir.");
		}
	}
	static void listado() throws IOException {
		String nombre="";
		int edad=0;
		boolean fin=false;
		try {
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "\\personas.dat"));
			//DataInputStream entrada= new DataInputStream(new FileInputStream(ruta + "\\personas"+i+".dat"));

			System.out.println("Listado.");
			nombre=entrada.readUTF();
			while(!fin) {				
				edad=entrada.readInt();
				if(nombre.equals(""))
					fin=true;
				System.out.println("Nombre: "+nombre+"\tedad: "+edad);
				nombre=entrada.readUTF();
			}
			entrada.close();
		}catch(EOFException eofe) {
			fin=true;
		}

	}
	static char teclearLetra() {
		Scanner e= new Scanner(System.in);
		char l=' '; 
		do{
			System.out.println("Teclea letra?");
			l=Character.toUpperCase(e.next().charAt(0));		
		}while(!Character.isLetter(l));
		return l;
	}
	static void crearFicheroInicial() {
		String nombre=null;	
		int edad=0;
		char i=' ';
		boolean fin=false;
		i=teclearLetra();
		try {
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "\\personas.dat"));
			ObjectOutputStream salida= new ObjectOutputStream(new FileOutputStream(ruta + "\\personas"+i+".dat"));
			//DataInputStream entrada= new DataInputStream(new FileInputStream(ruta + "\\personas.dat"));
			//DataOutputStream salida= new DataOutputStream(new FileOutputStream(ruta + "\\personas.dat"));

			nombre=entrada.readUTF();
			while(!fin) {
				try {
					edad=entrada.readInt();				
					if(Character.toUpperCase(nombre.charAt(0))==i) {
						salida.writeUTF(nombre);
						salida.writeInt(edad);
					}
					nombre=entrada.readUTF();
				}catch(EOFException ioe){
					entrada.close(); // da igual cerrarlos aquí o donde está comentado porque la excepción lo que controla es el fin del fichero.
					salida.close();
					fin=true;
				}			
			}		
			//entrada.close();
			//salida.close();
		}catch(IOException ioe) {}
		System.out.println("Fichero con la letra "+i+" creado.");
	}
	static void listarFicheroInicial() throws IOException {
		String nombre="";
		int edad=0;
		boolean fin=false;
		char i=teclearLetra();
		System.out.println("Fichero con la letra "+i+".");
		try {
			ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta + "\\personas"+i+".dat"));
			//DataInputStream entrada= new DataInputStream(new FileInputStream(ruta + "\\personas"+i+".dat"));
			//Data y Object no se pueden usar a la vez, el object guarda en objetos(como son las variables) y el Data en variables primitivas(como son los String)
			nombre=entrada.readUTF();
			while(!fin) {				
				edad=entrada.readInt();
				System.out.println("Nombre: "+nombre+"\tedad: "+edad);
				nombre=entrada.readUTF();
			}
			entrada.close();
		}catch(EOFException eofe) {
			fin=true;
		}
	}	
	static void fin() {
		System.out.println("Fin del programa");
	}
	public static void main(String  []args) throws IOException{
		Scanner e= new Scanner(System.in);
		int op=0;
		do {
			op=menu(e);
			switch (op) {
			case 1:
				altas(e);
				break;
			case 2:
				listado();
				break;
			case 3:
				crearFicheroInicial();
				break;
			case 4:
				listarFicheroInicial();
				break;			
			default:
				fin();
			}
		}while(op!=5);
		e.close();
	}
}