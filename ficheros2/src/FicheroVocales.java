package ficheroVocales;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FicheroVocales {
	
	static int menu (Scanner sc) {
		
		int op=0;
		
		System.out.println("\n\tMEN�\n\t====");
		
		System.out.println("1.Crear fichero.");
		System.out.println("2.Leer fichero.");
		System.out.println("3.Contar vocales.");
		System.out.println("4.Fin.");
		do {
			System.out.print("\n\tTeclee opci�n(1/4): ");
			op=sc.nextInt();
		}while (op<1||op>4);
		sc.nextLine();
		
		return op;
	}
	
	static void crearFichero() {
		int car=' ';
		System.out.println("\n\tCREAR FICHERO\n\t======");
		try {
			FileWriter esc=new FileWriter("E:\\CLASES\\PRG\\textoFicheros.txt");
			System.out.print("Teclear texto a grabar: ");
			while (car!='*') {
				esc.write(car);
				car=(char) System.in.read();
			}
			esc.close();
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		System.out.println("Fin del proceso de escritura");
		
	}
	
	static void leerFichero() {
		System.out.println("\n\tLEER FICHERO\n\t======");
		String texto="";
		int car=0;
		try {
			FileReader in=new FileReader("E:\\CLASES\\PRG\\textoFicheros.txt");
			car=in.read();
			while(car!=-1) {
				texto+=(char)car;
				car=in.read();
			}
			System.out.println("Texto leido caracter a caracter es   :"+texto);
			in.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	static int vocales () {
		int car=0, nv=0;
		System.out.println("\n\tCONTAR VOCALES\n\t======");
		try {
			FileReader in=new FileReader("E:\\CLASES\\PRG\\textoFicheros.txt");
			car=in.read();
			while(car!=-1) {
				switch(Character.toLowerCase((char)car)) {
				case 'a','e','i','o','u':
					nv++;
				}
				car=in.read();
			}
			in.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return nv;
		
	}

	static void fin() {
		System.out.println("\n\tFIN DEL PROGRAMA\n\t======");
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int op=0;
		do {
			op=menu(sc);
			switch (op) {
			case 1:
				crearFichero();
				break;
			case 2:
				leerFichero();
				break;
			case 3:
				
				System.out.println("Numero de vocales: "+vocales());
				break;

			default:
				fin();
			}
		}while(op!=4);
		
		
		
		
	}

}
